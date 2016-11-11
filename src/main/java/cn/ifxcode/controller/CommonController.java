package cn.ifxcode.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.ifxcode.model.BlogTopic;
import cn.ifxcode.model.Friendlink;
import cn.ifxcode.model.Navigation;
import cn.ifxcode.model.User;
import cn.ifxcode.service.BlogTopicService;
import cn.ifxcode.service.FriendlinkService;
import cn.ifxcode.service.NavigationService;
import cn.ifxcode.service.UserService;

@Controller
public class CommonController {

	private Logger logger = Logger.getLogger(CommonController.class);
	
	@Resource
	private NavigationService navigationService;
	@Resource
	private UserService userService;
	@Resource
	private BlogTopicService blogTopicService;
	@Resource
	private FriendlinkService friendlinkService;
	
	@RequestMapping("/common/header")
	public String header(Model model,HttpServletRequest request) throws Exception{
		List<Navigation> navigationList = navigationService.findAll();
		model.addAttribute("navigationList",navigationList);
		logger.info("查询所有导航");
		String uid = (String) request.getParameter("uid");
		if(StringUtils.isNotBlank(uid)){
			User dynamicUser = userService.getById(Integer.parseInt(uid));
			request.getSession().setAttribute("user", dynamicUser);
			model.addAttribute("dynamicUser", dynamicUser);
		}	
		return "public/common/header";
	}
	
	@RequestMapping("/index")
	public String index(Model model) throws Exception{
		List<BlogTopic> topics = blogTopicService.findHomeBlogAndClassify();
		List<BlogTopic> topiclastest5 = blogTopicService.findBlogLastest5();
		List<BlogTopic> topicViewCountTop8 = blogTopicService.findBlogViewCountTop8();
		List<BlogTopic> topicReplyCountTop8 = blogTopicService.findBlogReplyCountTop8();
		List<Friendlink> friendlinks = friendlinkService.findNameAndUrlAll();
		model.addAttribute("topics", topics);
		logger.info("加载home博文主题");
		model.addAttribute("topiclastest5", topiclastest5);
		logger.info("获取最新的5篇博文");
		model.addAttribute("topicViewCountTop8", topicViewCountTop8);
		logger.info("获取浏览数最多的8篇博文");
		model.addAttribute("topicReplyCountTop8", topicReplyCountTop8);
		logger.info("获取评论数最多的8篇博文");
		model.addAttribute("friendlinks", friendlinks);
		logger.info("获取所有友情链接");
		return "public/index/index";
	}
	
	@RequestMapping("/welcome")
	public String welcome(HttpSession session) throws Exception{
		User user = (User)session.getAttribute("user");
		if(user != null){
			return "public/common/welcome";
		}
		return "redirect:/index";
	}
	
}
