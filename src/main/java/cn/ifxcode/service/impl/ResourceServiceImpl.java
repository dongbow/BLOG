package cn.ifxcode.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;

import cn.ifxcode.dao.ResourceMapper;
import cn.ifxcode.model.Resource;
import cn.ifxcode.service.ResourceService;

@Service("resourceService")
public class ResourceServiceImpl implements ResourceService{

	private Logger logger = Logger.getLogger(ResourceServiceImpl.class);
	
	@javax.annotation.Resource
	private ResourceMapper resourceDao;

	@Cacheable(cacheName="baseCache")
	public List<Resource> findAllResource() {
		return resourceDao.findAllResource();
	}
	
}
