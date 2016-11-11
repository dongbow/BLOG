package cn.ifxcode.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;

import cn.ifxcode.bean.PageInfo;
import cn.ifxcode.model.Loginlog;
import cn.ifxcode.service.LoginlogService;

@Controller
public class LogController {

	private Logger logger = Logger.getLogger(LogController.class);
	
	@Resource
	private LoginlogService loginlogService;
	
	@RequestMapping("/system/admin/loginloglist")
	public String loginLogList() throws Exception{
		return "system/log/loginloglist";
	}
	
	@RequestMapping("/system/admin/log/loginDataGrid")
	@ResponseBody
	public PageInfo loginDateGrid(Loginlog loginlog,Integer page, Integer rows, 
			String sort, String order,String starttime,String endtime) throws Exception{
		
		PageInfo pageInfo = new PageInfo(page, rows);
		Map<String, Object> condition = Maps.newHashMap();
		
		if(StringUtils.isNotBlank(loginlog.getLoginname())){
			condition.put("loginname", loginlog.getLoginname());
		}
		if(StringUtils.isNotBlank(starttime)){
			condition.put("starttime", starttime);
		}
		if(StringUtils.isNotBlank(endtime)){
			condition.put("endtime", endtime);
		}
		
		pageInfo.setCondition(condition);
		loginlogService.findAllLoginlog(pageInfo);
		return pageInfo;
	}
}
