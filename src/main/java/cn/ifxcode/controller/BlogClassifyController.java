package cn.ifxcode.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;

import cn.ifxcode.bean.Page;
import cn.ifxcode.bean.PageInfo;
import cn.ifxcode.model.BlogClassify;
import cn.ifxcode.model.BlogTopic;
import cn.ifxcode.result.Result;
import cn.ifxcode.service.BlogClassifyService;
import cn.ifxcode.service.BlogTopicService;

@Controller
public class BlogClassifyController {

	private Logger logger = Logger.getLogger(BlogClassifyController.class);
	
	@Resource
	private BlogClassifyService blogClassifyService;
	@Resource
	private BlogTopicService blogTopicService;
	
	@RequestMapping("/system/admin/blogclassifylist")
	public ModelAndView classifylistPage() throws Exception{
		return new ModelAndView("system/blog/classify/blogclassifylist");
	}
	
	@RequestMapping(value="/system/admin/blogclassify/dataGrid",method=RequestMethod.POST)
	@ResponseBody
	public PageInfo dataGrid(BlogClassify blogClassify,Integer page, Integer rows, String sort, String order,
			String starttime,String endtime) throws Exception{
		PageInfo pageInfo = new PageInfo(page, rows);
		Map<String, Object> condition = Maps.newHashMap();
		
		if(StringUtils.isNotBlank(blogClassify.getName())){
			condition.put("name", blogClassify.getName());
		}
		if(StringUtils.isNotBlank(blogClassify.getIsdelete())){
			condition.put("isdelete", blogClassify.getIsdelete());
		}
		if(StringUtils.isNotBlank(starttime)){
			condition.put("starttime", starttime);
		}
		if(StringUtils.isNotBlank(endtime)){
			condition.put("endtime", endtime);
		}
		
		pageInfo.setCondition(condition);
		blogClassifyService.findAllClassify(pageInfo);
		
		return pageInfo;
	}
	
	@RequestMapping(value="/system/admin/classify/json",method= RequestMethod.POST)
	@ResponseBody
	public List<BlogClassify> findAllClassify() throws Exception{
		List<BlogClassify> blogClassifies = blogClassifyService.findAll();
		return blogClassifies;
	}
	
	@RequestMapping("/blog/classify/{cid}/article")
	public String BlogTopicByClassifyid(
			@RequestParam(required = false, defaultValue = "1") int pageNo,  
			@RequestParam(required = false, defaultValue = "20") int pageSize,
			@PathVariable(value="cid")String cid,Model model,
			HttpServletRequest request) throws Exception{
		if(StringUtils.isNotBlank(cid)){
			String contextPath=request.getContextPath()+"/blog/classify/"+cid+"/article";
			Page page = Page.newBuilder(pageNo, pageSize, contextPath);
			page.getParams().put("cid", cid);
			List<BlogTopic> blogTopicList = blogTopicService.findTopicByCid(page,cid);
			model.addAttribute("blogTopicList", blogTopicList);
			logger.info("分页查询结果，相应前台");
			model.addAttribute("page", page);
			return "public/blog/blog";
		}
		logger.error("分类为空，为结果");
		return "redirect:/404";
	}
	
	@RequestMapping("/system/admin/classify/addClassifyPage")
	public String addClassifyPage() throws Exception{
		return "system/blog/classify/addClassify";
	}
	
	@RequestMapping("/system/admin/classify/addClassify")
	@ResponseBody
	public Result addClassify(BlogClassify classify) throws Exception{
		Result result = new Result();
		classify.setBlogcount(0);
		classify.setCreatetime(new Date());
		int row = blogClassifyService.insert(classify);
		if(row == 1){
			result.setSuccess(true);
			result.setMsg("插入成功");
		} else{
			logger.error("插入失败");
			result.setSuccess(false);
			result.setMsg("插入失败");
		}
		return result;
	}
	
}
