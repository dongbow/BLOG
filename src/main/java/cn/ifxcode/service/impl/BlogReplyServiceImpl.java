package cn.ifxcode.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;

import cn.ifxcode.bean.Page;
import cn.ifxcode.bean.PageInfo;
import cn.ifxcode.dao.BlogReplyMapper;
import cn.ifxcode.model.BlogReply;
import cn.ifxcode.service.BlogReplyService;
import cn.ifxcode.utils.StringHtmlUtils;

@Service("blogReplyService")
public class BlogReplyServiceImpl implements BlogReplyService {
	
	private Logger logger = Logger.getLogger(BlogReplyServiceImpl.class);
	
	@Resource
	private BlogReplyMapper blogReplyDao;

	public void convert(List<BlogReply> blogReplies){
		if(blogReplies != null && blogReplies.size() >0 ){
			for (BlogReply blogReply : blogReplies) {
				blogReply.setContent(StringHtmlUtils.stringHtmlUnescape(blogReply.getContent()));
			}
			logger.info("解析转义过后的用户评论");
		}
	}
	
	@Cacheable(cacheName="baseCache")
	public List<BlogReply> findAllReplyByBid(Integer bid,Page page) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("page", page);
		logger.info("分页查询博文评论，返回list");
		map.put("bid", bid);
		List<BlogReply> blogReplies = blogReplyDao.findAllReplyByBid(map);
		this.convert(blogReplies);
		return blogReplies;
	}

	@Cacheable(cacheName="baseCache")
	public void findAllEasyUI(PageInfo pageInfo) {
		pageInfo.setRows(blogReplyDao.findBlogReplyPageCondition(pageInfo));
		pageInfo.setTotal(blogReplyDao.findBlogReplyPageCount(pageInfo));
	}

	@TriggersRemove(cacheName="baseCache",removeAll=true)
	public int insert(BlogReply blogReply) {
		return blogReplyDao.insert(blogReply);
	}

}
