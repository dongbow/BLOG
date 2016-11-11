package cn.ifxcode.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;

import cn.ifxcode.bean.PageInfo;
import cn.ifxcode.dao.LoginlogMapper;
import cn.ifxcode.model.Loginlog;
import cn.ifxcode.service.LoginlogService;

@Service("loginlogService")
public class LoginlogServiceImpl implements LoginlogService {

	private Logger logger = Logger.getLogger(LoginlogServiceImpl.class);
	
	@Resource
	private LoginlogMapper loginlogDao;

	@TriggersRemove(cacheName="baseCache",removeAll=true)
	public void insertLog(Loginlog loginlog) {
		loginlogDao.insert(loginlog);
	}

	@Cacheable(cacheName="baseCache")
	public void findAllLoginlog(PageInfo pageInfo) {
		pageInfo.setRows(loginlogDao.findLoginlogPageCondition(pageInfo));
		pageInfo.setTotal(loginlogDao.findLoginlogPageCount(pageInfo));
	}
	
}
