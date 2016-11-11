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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;

import cn.ifxcode.bean.Page;
import cn.ifxcode.bean.PageInfo;
import cn.ifxcode.lucene.utils.LuceneIndexUtil;
import cn.ifxcode.model.BlogClassify;
import cn.ifxcode.model.BlogReply;
import cn.ifxcode.model.BlogSign;
import cn.ifxcode.model.BlogTopic;
import cn.ifxcode.model.User;
import cn.ifxcode.service.BlogClassifyService;
import cn.ifxcode.service.BlogReplyService;
import cn.ifxcode.service.BlogSignService;
import cn.ifxcode.service.BlogTopicService;
import cn.ifxcode.service.UserService;
import cn.ifxcode.utils.HtmlToStringUtil;
import cn.ifxcode.utils.SignSplitUtil;
import cn.ifxcode.utils.StringNumberUtils;
import cn.ifxcode.utils.ValidataUtil;
import cn.ifxcode.vo.BlogTopicTime;

@Controller
public class BlogController {

	private Logger logger = Logger.getLogger(BlogController.class);
	
	@Resource
	private BlogTopicService blogTopicService;
	@Resource
	private BlogClassifyService blogClassifyService;
	@Resource
	private UserService userService;
	@Resource
	private BlogReplyService blogReplyService;
	@Resource
	private BlogSignService blogSignService;
	
	@RequestMapping("/blog")
	public String publicBlog(
			@RequestParam(required = false, defaultValue = "1") int pageNo,  
			@RequestParam(required = false, defaultValue = "20") int pageSize,
			Model model,HttpServletRequest request) throws Exception{
		String contextPath=request.getContextPath()+"/blog";
		Page page = Page.newBuilder(pageNo, pageSize, contextPath);
		List<BlogTopic> blogTopicList = blogTopicService.findAll(page);
		model.addAttribute("blogTopicList", blogTopicList);
		logger.info("分页查询结果，相应前台");
		model.addAttribute("page", page);
		return "public/blog/blog";
	}
	
	@RequestMapping("/blog/left")
	public String left(Model model) throws Exception{
		List<BlogTopic> topiclastest5 = blogTopicService.findBlogLastest5();
		List<BlogTopic> topicViewCountTop8 = blogTopicService.findBlogViewCountTop8();
		List<BlogTopic> topicReplyCountTop8 = blogTopicService.findBlogReplyCountTop8();
		List<BlogClassify> blogClassifies = blogClassifyService.findAll();
		List<BlogTopicTime> blogTopicTimes = blogTopicService.findAllGroupDate();
		User superAdminInfo = userService.findSuperAdminInfo();
		model.addAttribute("superAdminInfo", superAdminInfo);
		logger.info("获取超级管理部分信息");
		model.addAttribute("blogClassifies", blogClassifies);
		logger.info("获取所有分类");
		model.addAttribute("blogTopicTimes", blogTopicTimes);
		logger.info("获取所有时间分组");
		model.addAttribute("topiclastest5", topiclastest5);
		logger.info("获取最新的5篇博文");
		model.addAttribute("topicViewCountTop8", topicViewCountTop8);
		logger.info("获取浏览数最多的8篇博文");
		model.addAttribute("topicReplyCountTop8", topicReplyCountTop8);
		logger.info("获取评论数最多的8篇博文");
		return "public/blog/left";
	}
	
	@RequestMapping(value="/blog/article/{bid}")
	public String article(
			@RequestParam(required = false, defaultValue = "1") int pageNo,  
			@RequestParam(required = false, defaultValue = "10") int pageSize,
			@PathVariable(value="bid")String bid,
			HttpServletRequest request,
			Model model) throws Exception{
		BlogTopic blogTopicy =  blogTopicService.selectByPrimaryKey(StringNumberUtils.getDigit(bid));
		if(blogTopicy == null){
			logger.info("博文不存在");
			return "redirect:/404";
		}
		blogTopicy.setViewcount(blogTopicy.getViewcount()+1);
		int result = blogTopicService.updateTopic(blogTopicy);
		if(result != 1){
			return "redirect:/500";
		}
		String contextPath=request.getContextPath()+"/blog/article/"+bid;
		Page page = Page.newBuilder(pageNo, pageSize, contextPath);
		BlogTopic blogTopic = blogTopicService.findBlogTopicAndSignAndClassifyByBid(StringNumberUtils.getDigit(bid));
		BlogTopic minBlogTopic = blogTopicService.findMinBlogTopic(blogTopic.getBid());
		if(minBlogTopic == null){
			logger.info("上一篇博文不存在");
		}
		BlogTopic maxBlogTopic = blogTopicService.findMaxBlogTopic(blogTopic.getBid());
		if(minBlogTopic == null){
			logger.info("下一篇博文不存在");
		}
		List<BlogTopic> classifyBlogTopic = blogTopicService.findAllBlogByClassifyId(blogTopic.getBlogClassify().getCid());
		List<BlogReply> blogReplyList = blogReplyService.findAllReplyByBid(blogTopic.getBid(),page);
		model.addAttribute("blogTopic", blogTopic);
		model.addAttribute("minBlogTopic", minBlogTopic);
		model.addAttribute("maxBlogTopic", maxBlogTopic);
		model.addAttribute("classifyBlogTopic", classifyBlogTopic);
		model.addAttribute("blogReplyList", blogReplyList);
		model.addAttribute("page", page);
		logger.info("查询所属分类下所有topic");
		logger.info("博文查询成功，执行跳转");
		logger.info("查询博文下所有评论");
		return "public/blog/article";
	}
	
	@RequestMapping("/system/admin/homeBloglist")
	public String homeBlogPage() throws Exception{
		return "system/blog/home/homeBloglist";
	}
	
	@RequestMapping("/system/admin/home/dataGrid")
	@ResponseBody
	public PageInfo homeDataGrid(BlogTopic blogTopic,Integer page, Integer rows, String sort, String order,
			String starttime,String endtime) throws Exception{
		PageInfo pageInfo = new PageInfo(page, rows);
		Map<String, Object> condition = Maps.newHashMap();
		
		if(blogTopic.getBid() != null){
			condition.put("bid", blogTopic.getBid());
		}
		if(StringUtils.isNotBlank(starttime)){
			condition.put("starttime", starttime);
		}
		if(StringUtils.isNotBlank(endtime)){
			condition.put("endtime", endtime);
		}
		pageInfo.setCondition(condition);
		blogTopicService.findAllEasyUI(pageInfo);
		return pageInfo;
	}
	
	@RequestMapping("/system/admin/bloglist")
	public String bloglistPage() throws Exception{
		return "system/blog/blogManage/bloglist";
	}
	
	@RequestMapping("/system/admin/blog/dataGrid")
	@ResponseBody
	public PageInfo dataGrid(BlogTopic blogTopic,Integer page, Integer rows, String sort, String order,
			String cid,String name,String starttime,String endtime) throws Exception{
		PageInfo pageInfo = new PageInfo(page, rows);
		Map<String, Object> condition = Maps.newHashMap();

		if(blogTopic.getBid() != null){
			condition.put("bid", blogTopic.getBid());
		}
		if(StringUtils.isNotBlank(cid)){
			condition.put("cid", cid);
		}
		if(StringUtils.isNotBlank(name)){
			condition.put("name", name);
		}
		if(StringUtils.isNotBlank(blogTopic.getCreateOrRepost())){
			condition.put("createOrRepost", blogTopic.getCreateOrRepost());
		}
		if(StringUtils.isNotBlank(blogTopic.getIsdelete())){
			condition.put("isdelete", blogTopic.getIsdelete());
		}
		if(StringUtils.isNotBlank(starttime)){
			condition.put("starttime", starttime);
		}
		if(StringUtils.isNotBlank(endtime)){
			condition.put("endtime", endtime);
		}
		pageInfo.setCondition(condition);
		blogTopicService.findAllBlogEasyUI(pageInfo);
		return pageInfo;
	}
	
	@RequestMapping("/system/admin/blog/addBlogPage")
	public String addBlogPage() throws Exception{
		return "system/blog/blogManage/addBlog";
	}
	
	@RequestMapping(value="/system/admin/blog/addBlogTopic",method=RequestMethod.POST)
	public String addBlog(BlogTopic blogTopic,
			@RequestParam(value="signs",required=false)String signs,
			@RequestParam(value="cid",required=true)String cid,
			HttpSession session) throws Exception{
		User user = (User)session.getAttribute("user");
		blogTopic.setDescription(HtmlToStringUtil.NoHTML(blogTopic.getContent()));
		blogTopic.setCreatetime(new Date());
		blogTopic.setIsdelete("0");
		blogTopic.setViewcount(0);
		blogTopic.setReplycount(0);
		blogTopic.setPraisecount(0);
		blogTopic.setNotpraisecount(0);
		blogTopic.setIshome("0");
		blogTopic.setClassify_id(Integer.parseInt(cid));
		
		int resultBid = blogTopicService.insert(blogTopic);
		
		if(resultBid == 1){
			BlogClassify blogClassify = new BlogClassify();
			blogClassify = blogClassifyService.findByCid(Integer.parseInt(cid));
			blogClassify.setBlogcount(blogClassify.getBlogcount()+1);
			int row = blogClassifyService.update(blogClassify);
			if(row == 1){
				List<BlogSign> blogSigns = SignSplitUtil.signSplit(signs, blogTopic.getBid());
				if(blogSigns != null && blogSigns.size() > 0){
					int insert = blogSignService.insertList(blogSigns);
					if(insert != blogSigns.size()){
						logger.error("signs插入失败");
						return "redirect:/404";	
					}
				}
				User upUser = userService.findByUid(user.getUid());
				upUser.setBlogcount(upUser.getBlogcount()+1);
				int lastUprow = userService.update(upUser);
				if(lastUprow != 1){
					logger.error("用户博客数量总数+1更新失败");
					return "redirect:/404";	
				}
				LuceneIndexUtil.addIndex(blogTopic);
				logger.info("博文发表成功");
				return "redirect:/blog/article/"+blogTopic.getBid();
			}
			logger.error("分类博文数量更新失败");
			return "redirect:/404";	
		}
		logger.error("博文发表失败");
		return "redirect:/404";
	}
	
	@RequestMapping(value="/blog/praise",method=RequestMethod.POST)
	@ResponseBody
	public boolean addPraise(String bid,HttpSession session) throws Exception{
		boolean result = false;
		User user = (User)session.getAttribute("user");
		if(ValidataUtil.isNotBlank(bid) && user != null){
			BlogTopic blogTopic = blogTopicService.selectByPrimaryKey(Integer.parseInt(bid));
			blogTopic.setPraisecount(blogTopic.getPraisecount()+1);
			int row = blogTopicService.updateTopic(blogTopic);
			if(row == 1){
				result = true; 
			}
		}
		return result;
	}
	
	@RequestMapping(value="/blog/notpraise",method=RequestMethod.POST)
	@ResponseBody
	public boolean addNotPraise(String bid,HttpSession session) throws Exception{
		boolean result = false;
		User user = (User)session.getAttribute("user");
		if(ValidataUtil.isNotBlank(bid) && user != null){
			BlogTopic blogTopic = blogTopicService.selectByPrimaryKey(Integer.parseInt(bid));
			blogTopic.setNotpraisecount(blogTopic.getNotpraisecount()+1);
			int row = blogTopicService.updateTopic(blogTopic);
			if(row == 1){
				result = true; 
			}
		}
		return result;
	}
	
	@RequestMapping("/blog/date/{date}/article")
	public String blogByDate(
			@RequestParam(required = false, defaultValue = "1") int pageNo,  
			@RequestParam(required = false, defaultValue = "20") int pageSize,
			@PathVariable(value="date")String date,Model model,
			HttpServletRequest request) throws Exception{
		if(StringUtils.isNotBlank(date)){
			String contextPath=request.getContextPath()+"/blog/date/"+date+"/article";
			Page page = Page.newBuilder(pageNo, pageSize, contextPath);
			page.getParams().put("date", date);
			List<BlogTopic> blogTopicList = blogTopicService.findTopicByDate(page,date);
			model.addAttribute("blogTopicList", blogTopicList);
			logger.info("分页查询结果，相应前台");
			model.addAttribute("page", page);
			return "public/blog/blog";
		}
		logger.error("时间为空，为结果");
		return "redirect:/404";
	}
	
}
