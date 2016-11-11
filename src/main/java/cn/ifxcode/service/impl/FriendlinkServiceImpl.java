package cn.ifxcode.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;

import cn.ifxcode.bean.PageInfo;
import cn.ifxcode.dao.FriendlinkMapper;
import cn.ifxcode.model.Friendlink;
import cn.ifxcode.service.FriendlinkService;

@Service("friendlinkService")
public class FriendlinkServiceImpl implements FriendlinkService {
	
	private Logger logger = Logger.getLogger(FriendlinkServiceImpl.class);
	
	@Resource
	private FriendlinkMapper friendlinkDao;

	@Cacheable(cacheName="baseCache")
	public List<Friendlink> findNameAndUrlAll() {
		return friendlinkDao.findNameAndUrlAll();
	}

	@Cacheable(cacheName="baseCache")
	public void findAllLink(PageInfo pageInfo) {
		pageInfo.setRows(friendlinkDao.findFriendLinkPageCondition(pageInfo));
		pageInfo.setTotal(friendlinkDao.findFriendLinkPageCount(pageInfo));
	}

}
