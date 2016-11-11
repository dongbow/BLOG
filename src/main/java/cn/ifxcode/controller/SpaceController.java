package cn.ifxcode.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;





import cn.ifxcode.bean.Page;
import cn.ifxcode.model.Message;
import cn.ifxcode.model.User;
import cn.ifxcode.service.MessageService;
import cn.ifxcode.service.UserService;
import cn.ifxcode.utils.RoleJudge;
import cn.ifxcode.utils.StringNumberUtils;

@Controller
public class SpaceController {
	
	private Logger logger = Logger.getLogger(SpaceController.class);
	
	@Resource
	private UserService userService;
	@Resource
	private MessageService messageService;
	
	int result;
	String status;
	
	@RequestMapping(value="/space/{nickname}")
	public String spaceIndex(
			@PathVariable(value="nickname")String nickname,
			Model model) throws Exception{
		User userinfo = userService.findUserByNickname(nickname);
		if(userinfo == null){
			return "redirect:/404";
		}
		model.addAttribute("userinfo", userinfo);
		return "public/space/info";
	}
	
	@RequestMapping("/space/{uid}/message")
	public String message(
			@RequestParam(required = false, defaultValue = "1") int pageNo,  
			@RequestParam(required = false, defaultValue = "20") int pageSize,
			@PathVariable(value="uid")String uid,Model model,
			HttpSession session,HttpServletRequest request,
			@RequestParam(value="msg",required=false)String msg) throws Exception{
		User user = (User)session.getAttribute("user");
		if(StringUtils.isNotBlank(uid) && user != null){
			boolean condition = (StringNumberUtils.getDigit(uid) == user.getUid() || RoleJudge.roleJudge(user.getUid()));
			if(condition){
				User userinfo = userService.getById(StringNumberUtils.getDigit(uid));
				model.addAttribute("userinfo", userinfo);
				List<Integer> messages = messageService.findAllStatusMessage(userinfo.getUid(),0);
				if(messages != null && messages.size() > 0){
					result = messageService.updateStatus0to1(messages);
				}
				StringBuffer contextPath = new StringBuffer();
				contextPath.append(request.getContextPath()+"/space/"+userinfo.getUid()+"/message");
				if(msg == null || "unread".equals(msg)){
					status = "1";
					contextPath.append("?msg=unread");
				} else if("read".equals(msg)){
					status = "2";
					contextPath.append("?msg=read");
				} else {
					status = "3";
					contextPath.append("?msg=delete");
				}
				Page page = Page.newBuilder(pageNo, pageSize, contextPath.toString());
				page.getParams().put("uid", Integer.toString(userinfo.getUid()));
				page.getParams().put("status", status);
				List<Message> messageList = messageService.findAllMessage(page,userinfo.getUid(),status);
				model.addAttribute("page", page);
				model.addAttribute("messageList", messageList);
				return "public/space/message";
			}
		}
		return "redirect:/404";
	}
	
	@RequestMapping("/space/{uid}/reply")
	public String reply(
			@RequestParam(required = false, defaultValue = "1") int pageNo,  
			@RequestParam(required = false, defaultValue = "20") int pageSize,
			@PathVariable(value="uid")String uid,Model model,
			HttpServletRequest request) throws Exception{
		if(StringUtils.isNotBlank(uid)){
			User userinfo = userService.getById(StringNumberUtils.getDigit(uid));
			if(userinfo != null){
				String contextPath=request.getContextPath()+"/space/"+userinfo.getUid()+"/reply";
				Page page = Page.newBuilder(pageNo, pageSize, contextPath);
				page.getParams().put("uid", Integer.toString(userinfo.getUid()));
				model.addAttribute("userinfo", userinfo);
				return "public/space/blogreply";
			}
		}
		return "redirect:/404";
	}
	
	@RequestMapping("/space/{uid}/book")
	public String book(
			@PathVariable(value="uid")String uid,Model model) throws Exception{
		if(StringUtils.isNotBlank(uid)){
			User userinfo = userService.getById(StringNumberUtils.getDigit(uid));
			if(userinfo != null){
				model.addAttribute("userinfo", userinfo);
				return "public/space/bookreply";
			}
		}
		return "redirect:/404";
	}

}
