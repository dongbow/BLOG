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
import cn.ifxcode.dao.BlogTopicMapper;
import cn.ifxcode.model.BlogTopic;
import cn.ifxcode.service.BlogTopicService;
import cn.ifxcode.vo.BlogTopicTime;

@Service("blogTopicService")
public class BlogTopicServiceImpl implements BlogTopicService {

	private Logger logger = Logger.getLogger(BlogTopicServiceImpl.class);
	
	@Resource
	private BlogTopicMapper topicDao;
	
	@Cacheable(cacheName="baseCache")
	public List<BlogTopic> findAll(Page page) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("page", page);
		logger.info("分页查询博文主题，返回list");
		return topicDao.findAll(map);
	}

	@Cacheable(cacheName="baseCache")
	public BlogTopic findBlogTopicAndSignAndClassifyByBid(int bid) {
		BlogTopic blogTopic = topicDao.findBlogTopicAndSignAndClassifyByBid(bid);
		if(blogTopic != null){
			logger.info("博文存在");
			return blogTopic;
		}
		logger.info("博文不存在");
		return null;
	}

	@Cacheable(cacheName="baseCache")
	public List<BlogTopic> findHomeBlogAndClassify() {
		return topicDao.findHomeBlogAndClassify();
	}

	@Cacheable(cacheName="baseCache")
	public List<BlogTopic> findBlogLastest5() {
		return topicDao.findBlogLastest5();
	}

	@Cacheable(cacheName="baseCache")
	public List<BlogTopic> findBlogViewCountTop8() {
		return topicDao.findBlogViewCountTop8();
	}

	@Cacheable(cacheName="baseCache")
	public List<BlogTopic> findBlogReplyCountTop8() {
		return topicDao.findBlogReplyCountTop8();
	}

	@TriggersRemove(cacheName="baseCache",removeAll=true)
	public int updateTopic(BlogTopic blogTopic) {
		return topicDao.updateByPrimaryKey(blogTopic);
	}

	@Cacheable(cacheName="baseCache")
	public BlogTopic selectByPrimaryKey(int bid) {
		return topicDao.selectByPrimaryKey(bid);
	}

	@Cacheable(cacheName="baseCache")
	public List<BlogTopicTime> findAllGroupDate() {
		return topicDao.findAllGroupDate();
	}

	@Cacheable(cacheName="baseCache")
	public BlogTopic findMinBlogTopic(Integer bid) {
		return topicDao.findMinBlogTopic(bid);
	}

	@Cacheable(cacheName="baseCache")
	public BlogTopic findMaxBlogTopic(Integer bid) {
		return topicDao.findMaxBlogTopic(bid);
	}

	@Cacheable(cacheName="baseCache")
	public List<BlogTopic> findAllBlogByClassifyId(Integer cid) {
		return topicDao.findAllBlogByClassifyId(cid);
	}

	@Cacheable(cacheName="baseCache")
	public void findAllEasyUI(PageInfo pageInfo) {
		pageInfo.setRows(topicDao.findhomePageCondition(pageInfo));
		pageInfo.setTotal(topicDao.findhomePageCount(pageInfo));
	}

	@Cacheable(cacheName="baseCache")
	public void findAllBlogEasyUI(PageInfo pageInfo) {
		pageInfo.setRows(topicDao.findBlogPageCondition(pageInfo));
		pageInfo.setTotal(topicDao.findBlogPageCount(pageInfo));
	}

	@TriggersRemove(cacheName="baseCache",removeAll=true)
	public int insert(BlogTopic blogTopic) {
		return topicDao.insert(blogTopic);
	}

	@Cacheable(cacheName="baseCache")
	public List<BlogTopic> findTopicByBsname(Page page, String sign) {
		Map<String,Object> map = Maps.newHashMap();
		map.put("page", page);
		map.put("bsname", sign);
		return topicDao.findTopicByBsname(map);
	}

	@Cacheable(cacheName="baseCache")
	public List<BlogTopic> findTopicByCid(Page page, String cid) {
		Map<String,Object> map = Maps.newHashMap();
		map.put("page", page);
		map.put("cid", cid);
		return topicDao.findTopicByCid(map);
	}

	@Cacheable(cacheName="baseCache")
	public List<BlogTopic> findTopicByDate(Page page, String date) {
		Map<String,Object> map = Maps.newHashMap();
		map.put("page", page);
		map.put("date", date);
		return topicDao.findTopicByDate(map);
	}

	@Cacheable(cacheName="baseCache")
	public List<BlogTopic> findAllNotCondition() {
		return topicDao.findAllNotCondition();
	}

}
