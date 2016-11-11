package cn.ifxcode.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.ifxcode.model.About;
import cn.ifxcode.model.User;
import cn.ifxcode.service.AboutService;
import cn.ifxcode.service.UserService;

@Controller
public class AboutController {

	private Logger logger = Logger.getLogger(AboutController.class);
	
	@Resource
	private AboutService aboutService;
	@Resource
	private UserService userService;
	
	@RequestMapping("/about")
	public String publicAbout() throws Exception{
		return "public/about/about";
	}
	
	@RequestMapping("about/right")
	public String right(Model model) throws Exception{
		User superAdminInfo = userService.findSuperAdminInfo();
		model.addAttribute("superAdminInfo", superAdminInfo);
		logger.info("获取超级管理部分信息");
		return "public/about/right";
	}
	
	@RequestMapping("/system/admin/about")
	public String about() throws Exception{
		return "system/config/about";
	}
	
	@RequestMapping("/about/left")
	public String aboutLeft(Model model) throws Exception{
		About about = aboutService.findAbout();
		logger.info("取到about");
		model.addAttribute("about", about);
		return "public/about/left";
	}
	
}
