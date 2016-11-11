package cn.ifxcode.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;

import cn.ifxcode.bean.Page;
import cn.ifxcode.bean.PageInfo;
import cn.ifxcode.model.BookReply;
import cn.ifxcode.model.Message;
import cn.ifxcode.model.User;
import cn.ifxcode.service.BookReplyService;
import cn.ifxcode.service.MessageService;
import cn.ifxcode.utils.MessageTextUtil;
import cn.ifxcode.utils.StringHtmlUtils;

@Controller
public class BookController {

	private Logger logger = Logger.getLogger(MoodController.class);
	
	@Resource
	private BookReplyService bookReplyService;
	@Resource
	private MessageService messageService;
	
	@RequestMapping("/book")
	public String publicBook(
			@RequestParam(required = false, defaultValue = "1") int pageNo,  
			@RequestParam(required = false, defaultValue = "10") int pageSize,
			Model model,HttpServletRequest request) throws Exception{
		String contextPath=request.getContextPath()+"/book";
		Page page = Page.newBuilder(pageNo, pageSize, contextPath);
		List<BookReply> bookList = bookReplyService.findAll(page);
		model.addAttribute("bookList", bookList);
		model.addAttribute("page", page);
		logger.info("分页查询结果，相应前台");
		return "public/book/book";
	}
	
	@RequestMapping("/system/admin/bookreplylist")
	public ModelAndView bookreplyPage() throws Exception{
		return new ModelAndView("system/book/bookreplylist");
	}
	
	@RequestMapping("/system/admin/book/dataGrid")
	@ResponseBody
	public PageInfo dataGrid(BookReply bookReply,Integer page, Integer rows, String sort, String order,
			String starttime,String endtime,String user_id,String nickname) throws Exception{
		PageInfo pageInfo = new PageInfo(page, rows);
		Map<String, Object> condition = Maps.newHashMap();
		
		if(bookReply.getId() != null){
			condition.put("id", bookReply.getId());
		}
		if(StringUtils.isNotBlank(bookReply.getContent())){
			condition.put("content", bookReply.getContent());
		}
		if(StringUtils.isNotBlank(bookReply.getIsdelete())){
			condition.put("isdelete", bookReply.getIsdelete());
		}
		if(StringUtils.isNotBlank(user_id)){
			condition.put("user_id", user_id);
		}
		if(StringUtils.isNotBlank(nickname)){
			condition.put("nickname", nickname);
		}
		if(StringUtils.isNotBlank(starttime)){
			condition.put("starttime", starttime);
		}
		if(StringUtils.isNotBlank(endtime)){
			condition.put("endtime", endtime);
		}
		
		pageInfo.setCondition(condition);
		bookReplyService.findAllEasyUI(pageInfo);
		
		return pageInfo;
	}
	
	@RequestMapping(value="/book/doreply",method = RequestMethod.POST)
	public String doreply(@RequestParam(value="bfid",required=false) String bfid,
			@RequestParam(value="content",required=false)String content,HttpSession session) throws Exception{
		
		User user = (User) session.getAttribute("user");
		if( user != null){
			BookReply bookReply = new BookReply();
			bookReply.setContent(StringHtmlUtils.stringHtmlEscape(content));
			bookReply.setReplycount(0);
			bookReply.setIsdelete("0");
			bookReply.setCreatetime(new Date());
			bookReply.setUser_id(user.getUid());
			if(StringUtils.isNotBlank(bfid)){
				bookReply.setParent_id(Integer.parseInt(bfid));
			}
			int insert = bookReplyService.insert(bookReply);
			if(insert == 1 ){
				BookReply parent = null;
				if ( StringUtils.isNotBlank(bfid)){
					parent = bookReplyService.findReplyBybfid(Integer.parseInt(bfid));
					parent.setReplycount(parent.getReplycount()+1);
					int update = bookReplyService.update(parent);
					if(update != 1){
						return "redirect:/404";
					}
				}
				Message message = new Message();
				message.setSend_user_name(user.getUid());
				if ( StringUtils.isNotBlank(bfid)){
					message.setReceive_user_name(parent.getUser_id());
				} else {
					message.setReceive_user_name(1);
				}
				message.setSendtime(new Date());
				message.setStatus(0);
				if ( StringUtils.isNotBlank(bfid)){
					message.setMessage(MessageTextUtil.message_childbook());
				} else {
					message.setMessage(MessageTextUtil.message_book());
				}
				int msgRow = messageService.insert(message);
				if(msgRow != 1){
					return "redirect:/404";
				}
				return "redirect:/book";
			}
		}
		return "redirect:/404";
	}
	
}
