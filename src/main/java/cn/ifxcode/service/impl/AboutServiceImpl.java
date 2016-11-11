package cn.ifxcode.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.googlecode.ehcache.annotations.Cacheable;

import cn.ifxcode.dao.AboutMapper;
import cn.ifxcode.model.About;
import cn.ifxcode.service.AboutService;

@Service("aboutService")
public class AboutServiceImpl implements AboutService {

	private Logger logger = Logger.getLogger(AboutServiceImpl.class);
	
	@Resource
	private AboutMapper aboutDao;
	
	@Cacheable(cacheName="baseCache")
	public About findAbout() {
		logger.info("查询about");
		return aboutDao.findAbout();
	}

}
