package cn.ifxcode.controller;

import java.util.List;
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
import cn.ifxcode.model.Role;
import cn.ifxcode.service.RoleService;

@Controller
@RequestMapping("/system/admin")
public class RoleController {

	private Logger logger = Logger.getLogger(RoleController.class);
	
	@Resource
	private RoleService roleService;
	
	@RequestMapping("/rolelist")
	public String rolelist() throws Exception{
		return "system/manager/role/rolelist";
	}
	
	@RequestMapping("/role/dataGrid")
	@ResponseBody
	public PageInfo dataGrid(Role role,Integer page, Integer rows, String sort, String order) throws Exception{
		PageInfo pageInfo = new PageInfo(page, rows);
		Map<String, Object> condition = Maps.newHashMap();
		
		if(StringUtils.isNotBlank(role.getName())){
			condition.put("name", role.getName());
		}
		
		pageInfo.setCondition(condition);
		roleService.findAll(pageInfo);
		return pageInfo;
	}
	
	@RequestMapping(value="rolenamelist",method=RequestMethod.POST)
	@ResponseBody
	public List<Role> roleNamelist() throws Exception{
		List<Role> roles = roleService.findAllRolename();
		return roles;
	}
	
}
