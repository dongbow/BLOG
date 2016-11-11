package cn.ifxcode.controller;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;

import cn.ifxcode.bean.PageInfo;
import cn.ifxcode.model.BlogReply;
import cn.ifxcode.model.BlogTopic;
import cn.ifxcode.model.Message;
import cn.ifxcode.model.User;
import cn.ifxcode.service.BlogReplyService;
import cn.ifxcode.service.BlogTopicService;
import cn.ifxcode.service.MessageService;
import cn.ifxcode.service.UserService;
import cn.ifxcode.utils.MessageTextUtil;
import cn.ifxcode.utils.StringHtmlUtils;
import cn.ifxcode.utils.StringNumberUtils;
import cn.ifxcode.utils.ValidataUtil;

@Controller
public class BlogReplyController {

	private Logger logger = Logger.getLogger(BlogReplyController.class);
	
	@Resource
	private BlogReplyService blogReplyService;
	@Resource
	private BlogTopicService blogTopicService;
	@Resource
	private UserService userService;
	@Resource
	private MessageService messageService;
	
	private String articlURL = "blog/article/";  // blog/article/${bid}
	
	@RequestMapping("/system/admin/blogreplylist")
	public String blogreplylistPage() throws Exception{
		return "system/blog/reply/blogreplylist";
	}
	
	@RequestMapping("/system/admin/blog/replyDataGrid")
	@ResponseBody
	public PageInfo dataGrid(BlogReply blogReply,Integer page, Integer rows, 
			String sort, String order,String starttime,String endtime,
			String blog_id,String user_id,String nickname) throws Exception{
		PageInfo pageInfo = new PageInfo(page, rows);
		Map<String, Object> condition = Maps.newHashMap();
		
		if(blogReply.getBg_rid() != null){
			condition.put("bg_rid", blogReply.getBg_rid());
		}
		if(StringUtils.isNotBlank(blogReply.getContent())){
			condition.put("content", blogReply.getContent());
		}
		if(StringUtils.isNotBlank(blog_id)){
			condition.put("blog_id", blog_id);
		}
		if(StringUtils.isNotBlank(blogReply.getIsdelete())){
			condition.put("isdelete", blogReply.getIsdelete());
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
		blogReplyService.findAllEasyUI(pageInfo);
		return pageInfo;
	}
	
	@RequestMapping(value="/blog/article/doreply",method=RequestMethod.POST)
	public String doBlogReply(@RequestParam(value="content",required=true)String content,
			@RequestParam(value="blog_id",required=true)String blog_id,
			@RequestParam(value="bfid",required=false)String bfid,
			@RequestParam(value="puid",required=false)String puid,
			HttpSession session) throws Exception{
		User user = (User)session.getAttribute("user");
		if(user != null){
			BlogTopic blogTopic;
			if(ValidataUtil.isNotBlank(blog_id)){
				blogTopic = blogTopicService.selectByPrimaryKey(StringNumberUtils.getDigit(blog_id));
				if(blogTopic != null){
					BlogReply blogReply = new BlogReply();
					blogReply.setContent(StringHtmlUtils.stringHtmlEscape(content));
					blogReply.setCreatetime(new Date());
					blogReply.setIsdelete("0");
					blogReply.setBlogtopic_id(blogTopic.getBid());
					blogReply.setUser_id(user.getUid());
					if(ValidataUtil.isNotBlank(bfid)){
						blogReply.setParent_id(StringNumberUtils.getDigit(bfid));
					}
					if(blogReplyService.insert(blogReply) == 1){
						blogTopic.setReplycount(blogTopic.getReplycount()+1);
						if(blogTopicService.updateTopic(blogTopic) == 1){
							User userNow = userService.findByUid(user.getUid());
							userNow.setReplycount(userNow.getReplycount()+1);
							if(userService.update(userNow) == 1){
								Message message = new Message();
								message.setSend_user_name(userNow.getUid());
								message.setSendtime(new Date());
								message.setStatus(0);
								if(ValidataUtil.isNotBlank(bfid)){
									message.setMessage(MessageTextUtil.message_childblog()
											+createBlogReplyMessageURL(blogTopic.getBid(),blogTopic.getTitle()));
									message.setReceive_user_name(StringNumberUtils.getDigit(puid));
								}else{
									message.setMessage(MessageTextUtil.message_blog()
											+createBlogReplyMessageURL(blogTopic.getBid(),blogTopic.getTitle()));
									message.setReceive_user_name(1);
								}
								if(messageService.insert(message) == 1){
									logger.info("评论成功");
									return "redirect:/blog/article/"+blogTopic.getBid()+"#comments";
								}
							}
						}
					} 
				}
			}
		}
		logger.error("评论插入失败");
		return "redirect:/404";
	}
	
	public String createBlogReplyMessageURL(Integer bid,String title){
		StringBuffer buffer = new StringBuffer();
		buffer.append("<br/>来自：<a style='color:#19b4ea' target='_blank' href='"+articlURL+bid+"#comments'>"+title+"</a>");
		return buffer.toString();
	}
}
