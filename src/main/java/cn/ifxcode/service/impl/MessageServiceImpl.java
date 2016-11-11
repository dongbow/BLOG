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
import cn.ifxcode.dao.MessageMapper;
import cn.ifxcode.model.Message;
import cn.ifxcode.service.MessageService;

@Service("messageService")
public class MessageServiceImpl implements MessageService{

	private Logger logger = Logger.getLogger(MessageServiceImpl.class);
	
	@Resource
	private MessageMapper messageDao;

	@Override
	public int findMsgCountByUid(int uid) {
		return  messageDao.findMsgCountByUid(uid);
	}

	@Cacheable(cacheName="baseCache")
	public List<Integer> findAllStatusMessage(Integer uid, int status) {
		Map<String,Object> map= Maps.newHashMap();
		map.put("uid", uid);
		map.put("status", status);
		return messageDao.findAllStatusMessage(map);
	}

	@TriggersRemove(cacheName="baseCache",removeAll=true)
	public int updateStatus0to1(List<Integer> messages) {
		return messageDao.updateStatus0to1(messages);
	}

	@Cacheable(cacheName="baseCache")
	public List<Message> findAllMessage(Page page, Integer uid, String status) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("page", page);
		map.put("uid", uid);
		map.put("status", status);
		return  messageDao.findAllMessage(map);
	}

	@TriggersRemove(cacheName="baseCache",removeAll=true)
	public int insert(Message message) {
		return messageDao.insert(message);
	}

}
