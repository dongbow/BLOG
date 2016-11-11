package cn.ifxcode.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;

import cn.ifxcode.bean.PageInfo;
import cn.ifxcode.dao.DdlMapper;
import cn.ifxcode.service.DdlService;

@Service("ddlService")
public class DdlServiceImpl implements DdlService {

	private Logger logger = Logger.getLogger(DdlServiceImpl.class);
	
	@Resource
	private DdlMapper ddlDao;

	@Cacheable(cacheName="baseCache")
	public void findAllDDAndDDL(PageInfo pageInfo) {
		pageInfo.setRows(ddlDao.findDDLPageCondition(pageInfo));
		pageInfo.setTotal(ddlDao.findDDLPageCount(pageInfo));
	}

}
