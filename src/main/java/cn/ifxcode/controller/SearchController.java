package cn.ifxcode.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ifxcode.lucene.utils.LuceneIndexUtil;
import cn.ifxcode.model.BlogTopic;
import cn.ifxcode.result.Result;
import cn.ifxcode.service.BlogTopicService;

@Controller
public class SearchController {

	private Logger logger = Logger.getLogger(SearchController.class);
	@Resource
	private BlogTopicService blogTopicService;
	
	@RequestMapping("/system/admin/config/createIndex")
	@ResponseBody
	public Result createIndex() throws Exception{
		Result result = new Result();
		List<BlogTopic> blogTopics = blogTopicService.findAllNotCondition();
		result = LuceneIndexUtil.initIndex(blogTopics);
		return result;
	}
	
	@RequestMapping("/search")
	public String searchIndex(
			@RequestParam(value="keyword")String keyword,Model model) throws Exception{
		if(StringUtils.isNotBlank(keyword)){
			List<BlogTopic> blogTopics = LuceneIndexUtil.searchIndex(keyword);
			model.addAttribute("blogTopics", blogTopics);
		}		
		return "public/search/search";
	}
	
}
