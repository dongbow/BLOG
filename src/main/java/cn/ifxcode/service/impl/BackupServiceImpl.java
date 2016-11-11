package cn.ifxcode.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;
import com.sun.istack.internal.logging.Logger;

import cn.ifxcode.bean.PageInfo;
import cn.ifxcode.dao.BackupMapper;
import cn.ifxcode.model.Backup;
import cn.ifxcode.service.BackupService;

@Service("backupService")
public class BackupServiceImpl implements BackupService{
	
	private Logger logger = Logger.getLogger(BackupServiceImpl.class);
	
	@Resource
	private BackupMapper backupDao;

	@TriggersRemove(cacheName="baseCache",removeAll=true)
	public int insert(Backup backup) {
		return backupDao.insert(backup);
	}

	@Cacheable(cacheName="baseCache")
	public void findAll(PageInfo pageInfo) {
		pageInfo.setRows(backupDao.findBackupPageCondition(pageInfo));
		pageInfo.setTotal(backupDao.findBackupPageCount(pageInfo));
	}

}
