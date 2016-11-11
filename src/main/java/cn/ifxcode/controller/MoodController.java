package cn.ifxcode.controller;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;

import cn.ifxcode.bean.Page;
import cn.ifxcode.bean.PageInfo;
import cn.ifxcode.model.Mood;
import cn.ifxcode.result.Result;
import cn.ifxcode.service.MoodService;

@Controller
public class MoodController {

	private Logger logger = Logger.getLogger(MoodController.class);
	@Resource
	private MoodService moodService;
	
	@RequestMapping("/mood")
	public String publicMood(
			@RequestParam(required = false, defaultValue = "1") int pageNo,  
			@RequestParam(required = false, defaultValue = "10") int pageSize,
			Model model,HttpServletRequest request) throws Exception{
		String contextPath=request.getContextPath()+"/mood";
		Page page = Page.newBuilder(pageNo, pageSize, contextPath);
		model.addAttribute("moodList", moodService.findAll(page));
		model.addAttribute("page", page);
		logger.info("分页查询结果，相应前台");
		return "public/mood/mood";
	}
	
	@RequestMapping("system/admin/moodlist")
	public String systemMoodlist() throws Exception{
		return "system/mood/moodlist";
	}
	
	@RequestMapping("system/admin/mood/dataGrid")
	@ResponseBody
	public PageInfo dataGrid(Mood mood, Integer page, Integer rows, String sort, String order, 
			String starttime,String endtime) throws Exception{
		PageInfo pageInfo = new PageInfo(page, rows);
		Map<String, Object> condition = Maps.newHashMap();
		
		if(StringUtils.isNotBlank(starttime)){
			condition.put("starttime", starttime);
		}
		if(StringUtils.isNotBlank(endtime)){
			condition.put("endtime", endtime);
		}
		
		pageInfo.setCondition(condition);
		moodService.findAllCondition(pageInfo);
		return pageInfo;
	}
	
	@RequestMapping("system/admin/mood/addMoodPage")
	public String addMoodPage() throws Exception{
		return "system/mood/addMood";
	}
	
	@RequestMapping("system/admin/mood/addMood")
	@ResponseBody
	public Result addMood(Mood mood) throws Exception{
		Result result = new Result();
		if(StringUtils.isNotBlank(mood.getContent())){
			result.setMsg("不能为空!");
		}
		try {
            mood.setCreatetime(new Date());
            moodService.insertMood(mood);
            result.setSuccess(true);
            result.setMsg("发表成功");
        } catch (RuntimeException e) {
            logger.error("发表失败：{}", e);
            result.setMsg(e.getMessage());
        }
		return result;
	}
	
}
