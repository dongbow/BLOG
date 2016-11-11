package cn.ifxcode.controller;

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
import cn.ifxcode.model.BlogSign;
import cn.ifxcode.model.BlogTopic;
import cn.ifxcode.service.BlogSignService;
import cn.ifxcode.service.BlogTopicService;

@Controller
public class BlogSignController {

	private Logger logger = Logger.getLogger(BlogSignController.class);
	
	@Resource
	private BlogSignService blogSignService;
	@Resource
	private BlogTopicService blogTopicService;
	
	@RequestMapping("/system/admin/blogsignlist")
	public ModelAndView signlistPage() throws Exception{
		return new ModelAndView("system/blog/sign/blogsignlist");
	}
	
	@RequestMapping(value="/system/admin/blogsign/dataGrid",method = RequestMethod.POST)
	@ResponseBody
	public PageInfo dataGrid(BlogSign blogSign,Integer page, Integer rows, String sort, String order,
			String blog_id,String starttime,String endtime) throws Exception{
		PageInfo pageInfo = new PageInfo(page, rows);
		Map<String, Object> condition = Maps.newHashMap();
		
		if(StringUtils.isNotBlank(blog_id)){
			condition.put("blog_id", blog_id);
		}
		if(StringUtils.isNotBlank(blogSign.getBsname())){
			condition.put("bsname", blogSign.getBsname());
		}
		if(StringUtils.isNotBlank(starttime)){
			condition.put("starttime", starttime);
		}
		if(StringUtils.isNotBlank(endtime)){
			condition.put("endtime", endtime);
		}
		pageInfo.setCondition(condition);
		blogSignService.findAllSign(pageInfo);
		return pageInfo;
	}
	
	@RequestMapping("/blog/sign/{sign}/article")
	public String BlogTopicBySign(
			@RequestParam(required = false, defaultValue = "1") int pageNo,  
			@RequestParam(required = false, defaultValue = "20") int pageSize,
			@PathVariable(value="sign")String sign,Model model,
			HttpServletRequest request) throws Exception{
		if(StringUtils.isNotBlank(sign)){
			String contextPath=request.getContextPath()+"/blog/sign/"+sign+"/article";
			Page page = Page.newBuilder(pageNo, pageSize, contextPath);
			page.getParams().put("bsname", sign);
			List<BlogTopic> blogTopicList = blogTopicService.findTopicByBsname(page,sign);
			model.addAttribute("blogTopicList", blogTopicList);
			logger.info("分页查询结果，相应前台");
			model.addAttribute("page", page);
			return "public/blog/blog";
		}
		logger.error("sign为空，为结果");
		return "redirect:/404";
	}
	
}
