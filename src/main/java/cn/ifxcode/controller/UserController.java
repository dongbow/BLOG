package cn.ifxcode.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;

import cn.ifxcode.bean.PageInfo;
import cn.ifxcode.model.User;
import cn.ifxcode.service.UserService;
import cn.ifxcode.utils.MD5Util;
import cn.ifxcode.vo.UserVo;

@Controller
public class UserController {

	private Logger logger = Logger.getLogger(UserController.class);
	
	@Resource
	private UserService userService;
	
	@RequestMapping("/system/admin/userlist")
	public String showAllUsers() throws Exception{
		return "system/manager/user/userlist";
	}
	
	@RequestMapping(value="/system/admin/user/dataGrid",method = RequestMethod.POST)
	@ResponseBody
	public PageInfo dataGrid(UserVo userVo, Integer page, Integer rows, String sort, String order, 
			String starttime,String endtime,String rid) throws Exception{
		PageInfo pageInfo = new PageInfo(page, rows);
		Map<String, Object> condition = Maps.newHashMap();

		if(StringUtils.isNotBlank(rid)){
			condition.put("rid", rid);
		}
		if(userVo.getUid() != null){
			condition.put("uid", userVo.getUid());
		}
		if(StringUtils.isNotBlank(userVo.getUsername())){
			condition.put("username", userVo.getUsername());
		}
		if(StringUtils.isNotBlank(userVo.getNickname())){
			condition.put("nickname", userVo.getNickname());
		}
		if(StringUtils.isNotBlank(userVo.getStatus())){
			condition.put("status", userVo.getStatus());
		}
		if(StringUtils.isNotBlank(starttime)){
			condition.put("starttime", starttime);
		}
		if(StringUtils.isNotBlank(endtime)){
			condition.put("endtime", endtime);
		}
		
		pageInfo.setCondition(condition);
		userService.findAllIsNotDelete(pageInfo);
		
		return pageInfo;
	}
	
	@RequestMapping("/system/admin/userinfo")
	public String MyInfo(Model model,HttpSession session) throws Exception{
		User user = (User)session.getAttribute("user");
		model.addAttribute("myinfo", userService.getById(user.getUid()));
		return "system/config/info/userinfo";
	}
	
	@RequestMapping("system/admin/user/exportPage")
	public String addMoodPage() throws Exception{
		return "system/manager/user/export";
	}
	
	@RequestMapping("/system/admin/user/addPage")
	public String addPage() throws Exception{
		return "system/manager/user/addUser";
	}
	
	@RequestMapping("/system/admin/user/editPwdPage")
	public String editPwdPage() throws Exception{
		return "system/manager/user/editPwd";
	}
	
	@RequestMapping(value="/system/admin/user/editPwd",method= RequestMethod.POST)
	public String editPwd(String password,HttpSession session) throws Exception{
		User sUser = (User)session.getAttribute("user");
		User user = userService.findByUid(sUser.getUid());
		if(user != null) {
			user.setPassword(MD5Util.getMD5String(password));
			int row = userService.update(user);
			if(row ==1){
				session.invalidate();
				return "redirect:/system/admin/login";
			}
		}
		return "redirect:/404";
	}
	
}
