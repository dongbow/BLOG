package cn.ifxcode.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.ifxcode.model.Loginlog;
import cn.ifxcode.model.User;
import cn.ifxcode.service.LoginlogService;
import cn.ifxcode.service.UserService;
import cn.ifxcode.utils.GetIPUtil;
import cn.ifxcode.utils.MD5Util;
import cn.ifxcode.utils.RoleJudge;

@Controller
public class SystemController {

	private Logger logger = Logger.getLogger(SystemController.class);
	
	@Resource
	private UserService userService;
	@Resource
	private LoginlogService loginlogService;
	
	@RequestMapping("/system/admin/login")
	public ModelAndView systemLogin(HttpSession session) throws Exception{
		User user = (User)session.getAttribute("user");
		if(user != null &&  RoleJudge.roleJudge(user.getRole().getRid())){
			return new ModelAndView("redirect:/welcome");
		}
		return new ModelAndView("system/login");
	}
	
	@RequestMapping("/system/admin/index")
	public ModelAndView systemIndex(Model model,HttpSession session) throws Exception{
		User user = (User) session.getAttribute("user");
		if(user != null &&  RoleJudge.roleJudge(user.getRole().getRid())){
			session.setAttribute("user", user);
			model.addAttribute("user", user);
			return new ModelAndView("system/index");
		}
		return new ModelAndView("redirect:/system/admin/login");
	}
	
	@RequestMapping(value="/system/admin/dologin",method=RequestMethod.POST)
	public String systemLoginCheck(String username,String password,Model model,HttpSession session,
			HttpServletRequest request) throws Exception{
		if(StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)){
			User user = userService.adminLogin(username, MD5Util.getMD5String(password));
			if(user != null && user.getStatus().equals("0") && RoleJudge.roleJudge(user.getRole().getRid())){
				String ip = GetIPUtil.getIpAddr(request);
				Loginlog loginlog = new Loginlog();
				loginlog.setLoginname(user.getNickname());
				loginlog.setLoginip(ip);
				loginlog.setLoginfrom("/system/admin/login");
				loginlog.setLogintime(new Date());
				loginlogService.insertLog(loginlog);
				session.setAttribute("user", user);
				model.addAttribute("user", user);
				return "redirect:/system/admin/index";
			} else {
				request.setAttribute("error", "用户名或密码不正确或没有权限");
				logger.info("用户名或密码不正确或没有权限");
				return "system/login";
			}
		}
		request.setAttribute("error", "用户名或密码不能为空");
		return "system/login";
	}

}
