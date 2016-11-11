package cn.ifxcode.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ifxcode.model.Loginlog;
import cn.ifxcode.model.User;
import cn.ifxcode.service.LoginlogService;
import cn.ifxcode.service.UserService;
import cn.ifxcode.utils.GetIPUtil;
import cn.ifxcode.utils.MD5Util;
import cn.ifxcode.utils.UptoLow;
import cn.ifxcode.utils.ValidataUtil;
import cn.ifxcode.utils.ValidateCode;

@Controller
public class LoginController {

	private Logger logger = Logger.getLogger(LoginController.class);
	
	@Resource
	private UserService userService;
	@Resource
	private LoginlogService loginlogService;
	
	@RequestMapping("/account/login")
	public String login(HttpSession session) throws Exception{
		User user = (User)session.getAttribute("user");
		if(user != null){
			return "redirect:/welcome";
		}
		return "public/account/login";
	}
	
	@RequestMapping(value="/account/dologin",method=RequestMethod.POST)
	public String dologin(String username,String password,@RequestParam("backurl")String backurl,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		if(StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)){
			User user = userService.login(username, MD5Util.getMD5String(password));
			if(user != null && user.getStatus().equals("0")){
				String ip = GetIPUtil.getIpAddr(request);
				Loginlog loginlog = new Loginlog();
				loginlog.setLoginname(user.getNickname());
				loginlog.setLoginip(ip);
				loginlog.setLoginfrom("/account/login");
				loginlog.setLogintime(new Date());
				loginlogService.insertLog(loginlog);
				request.getSession().setAttribute("user", user);
				response.sendRedirect(backurl);
				return null;
			} else {
				request.setAttribute("error", "用户名或密码不正确或账号停用");
				logger.info("用户名或密码不正确或账号停用");
				return "public/account/login";
			}
		}
		request.setAttribute("error", "用户名或密码不能为空");
		return "public/account/login";
	}
	
	@RequestMapping("/account/register")
	public String register() throws Exception{
		return "public/account/register";
	}
	
	@RequestMapping(value="/account/logout",method=RequestMethod.GET)
	public String logout(String backurl,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		request.getSession().invalidate();
		response.sendRedirect(backurl);
		return null;
	}
	
	@RequestMapping(value="/account/doregister",method=RequestMethod.POST)
	public String doRegister(User user,Model model,
			HttpServletRequest request) throws Exception{
		if(ValidataUtil.right(user)){
			if(this.dataExits(user.getUsername())
					&& this.dataExits(user.getNickname())
					&& this.dataExits(user.getEmail())){
				user.setPassword(MD5Util.getMD5String(user.getPassword()));
				user.setBlogcount(0);
				user.setCreatetime(new Date());
				user.setCreateip(GetIPUtil.getIpAddr(request));
				user.setUserimage("upload/userhead/default/default.jpg");
				user.setStatus("0");
				user.setReplycount(0);
				user.setIsdelete("0");
				user.setRole_id(3);
				int row = userService.insert(user);
				if(row == 1){
					return "redirect:/account/login?backurl=/ifxcode/index";
				}
			}
		}
		return "redirect:/account/register";
	}
	
	@RequestMapping(value="/account/dataExits")
	@ResponseBody
	public boolean dataExits(
			@RequestParam(value="data",required=true)String data) throws Exception{
		if(StringUtils.isNotBlank(data) && !userService.findDataExits(data)){
			return true;
		}
		return false;
	}
	
	@RequestMapping(value="/verifycode",method=RequestMethod.GET)
	@ResponseBody
	public boolean verifycode(@RequestParam(value ="verifyCode")String verifyCode,
			HttpServletRequest request) throws Exception{
		boolean result = false;
		if(ValidataUtil.verifycode(verifyCode)){
			String code = ValidateCode.getValidateCode(request);
			if(UptoLow.exChange(code).equals(UptoLow.exChange(verifyCode)))
				result =  true;
		}
		return result;
	}
	
}
