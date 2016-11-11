package cn.ifxcode.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;

import cn.ifxcode.dao.DdMapper;
import cn.ifxcode.model.Dd;
import cn.ifxcode.service.DdService;

@Service("ddService")
public class DdServiceImpl implements DdService {

	private Logger logger = Logger.getLogger(DdServiceImpl.class);
	
	@Resource
	private DdMapper ddDao;

	@Cacheable(cacheName="baseCache")
	public List<Dd> findAllIdAndName() {
		return ddDao.findAllIdAndName();
	}
	
}
