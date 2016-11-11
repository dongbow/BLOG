package cn.ifxcode.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;

import cn.ifxcode.bean.PageInfo;
import cn.ifxcode.model.Friendlink;
import cn.ifxcode.service.FriendlinkService;

@Controller
@RequestMapping("/system/admin")
public class FriendLinkController {
	
	private Logger logger = Logger.getLogger(FriendLinkController.class);
	
	@Resource
	private FriendlinkService friendlinkService;
	
	@RequestMapping("/friendlink")
	public String friendlinkPage() throws Exception{
		return "system/config/link/friendlink";
	}
	
	@RequestMapping(value="/friendlink/dataGrid",method=RequestMethod.POST)
	@ResponseBody
	public PageInfo dataGrid(Friendlink friendlink,Integer page, Integer rows, String sort, 
			String order,String startcreatetime,String endcreatetime,
			String startEndtime,String endEndtime) throws Exception{
		PageInfo pageInfo = new PageInfo(page, rows);
		Map<String, Object> condition = Maps.newHashMap();
		
		if(StringUtils.isNotBlank(friendlink.getName())){
			condition.put("name", friendlink.getName());
		}
		if(StringUtils.isNotBlank(friendlink.getCustomer())){
			condition.put("customer", friendlink.getCustomer());
		}
		if(StringUtils.isNotBlank(startcreatetime)){
			condition.put("startcreatetime", startcreatetime);
		}
		if(StringUtils.isNotBlank(endcreatetime)){
			condition.put("endcreatetime", endcreatetime);
		}
		if(StringUtils.isNotBlank(startEndtime)){
			condition.put("startEndtime", startEndtime);
		}
		if(StringUtils.isNotBlank(endEndtime)){
			condition.put("endEndtime", endEndtime);
		}
		
		pageInfo.setCondition(condition);
		friendlinkService.findAllLink(pageInfo);
		return pageInfo;
	}

}
