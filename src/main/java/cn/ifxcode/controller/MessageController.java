package cn.ifxcode.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ifxcode.model.User;
import cn.ifxcode.service.MessageService;

@Controller
public class MessageController {

	private Logger logger = Logger.getLogger(MessageController.class);
	
	@Resource
	private MessageService messageService;
	
	@RequestMapping(value="message/ajax",method = RequestMethod.POST)
	@ResponseBody
	public int messageCount(HttpSession session) throws Exception{
		User user = (User)session.getAttribute("user");
		int count = 0;
		if(user != null)
			count = messageService.findMsgCountByUid(user.getUid());
		return count;
	}
	
	@RequestMapping("system/admin/mymessage")
	public String mymessagePage() throws Exception{
		return "system/message/mymessage";
	}
	
}
