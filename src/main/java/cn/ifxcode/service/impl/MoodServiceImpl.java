package cn.ifxcode.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;

import cn.ifxcode.bean.Page;
import cn.ifxcode.bean.PageInfo;
import cn.ifxcode.dao.MoodMapper;
import cn.ifxcode.model.Mood;
import cn.ifxcode.service.MoodService;


@Service("moodService")
public class MoodServiceImpl implements MoodService{

	@Resource
	private MoodMapper moodDao;
	
	@Cacheable(cacheName="baseCache")
	public List<Mood> findAll(Page page) throws Exception {
		Map<String, Object> map = Maps.newHashMap();
		map.put("page", page);
		return moodDao.findAll(map);
	}

	@Cacheable(cacheName="baseCache")
	public void findAllCondition(PageInfo pageInfo) {
		pageInfo.setRows(moodDao.findMoodPageCondition(pageInfo));
		pageInfo.setTotal(moodDao.findMoodPageCount(pageInfo));
	}

	@TriggersRemove(cacheName="baseCache",removeAll=true)
	public void insertMood(Mood mood) {
		moodDao.insert(mood);
	}

}
