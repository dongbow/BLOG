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
import cn.ifxcode.dao.BlogSignMapper;
import cn.ifxcode.model.BlogSign;
import cn.ifxcode.model.BlogTopic;
import cn.ifxcode.service.BlogSignService;

@Service("blogSignServiceImpl")
public class BlogSignServiceImpl implements BlogSignService{

	private Logger logger = Logger.getLogger(BlogSignServiceImpl.class);
	
	@Resource
	private BlogSignMapper blogSignDao;
	
	@Cacheable(cacheName="baseCache")
	public void findAllSign(PageInfo pageInfo) {
		pageInfo.setRows(blogSignDao.findSignPageCondition(pageInfo));
		pageInfo.setTotal(blogSignDao.findSignPageCount(pageInfo));
	}

	@TriggersRemove(cacheName="baseCache",removeAll=true)
	public int insertList(List<BlogSign> blogSigns) {
		return blogSignDao.insertList(blogSigns);
	}

	@Cacheable(cacheName="baseCache")
	public List<BlogTopic> findTopicByBsname(Page page) {
		Map<String,Object> map = Maps.newHashMap();
		map.put("page", page);
		return blogSignDao.findTopicByBsname(map);
	}

}
