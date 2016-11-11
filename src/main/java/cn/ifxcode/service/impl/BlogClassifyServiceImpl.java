package cn.ifxcode.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;

import cn.ifxcode.bean.PageInfo;
import cn.ifxcode.dao.BlogClassifyMapper;
import cn.ifxcode.model.BlogClassify;
import cn.ifxcode.service.BlogClassifyService;

@Service("blogClassifyService")
public class BlogClassifyServiceImpl implements BlogClassifyService {
	
	private Logger logger = Logger.getLogger(BlogClassifyServiceImpl.class);
	
	@Resource
	private BlogClassifyMapper blogClassifyDao;

	@Cacheable(cacheName="baseCache")
	public List<BlogClassify> findAll() {
		return blogClassifyDao.findAll();
	}

	@Cacheable(cacheName="baseCache")
	public void findAllClassify(PageInfo pageInfo) {
		pageInfo.setRows(blogClassifyDao.findClassifyPageCondition(pageInfo));
		pageInfo.setTotal(blogClassifyDao.findClassifyPageCount(pageInfo));
	}

	@Cacheable(cacheName="baseCache")
	public BlogClassify findByCid(int cid) {
		return blogClassifyDao.selectByPrimaryKey(cid);
	}

	@TriggersRemove(cacheName="baseCache",removeAll=true)
	public int update(BlogClassify blogClassify) {
		return blogClassifyDao.updateByPrimaryKey(blogClassify);
	}

	@TriggersRemove(cacheName="baseCache",removeAll=true)
	public int insert(BlogClassify classify) {
		return blogClassifyDao.insert(classify);
	}
	

}
