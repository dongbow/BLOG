package cn.ifxcode.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;

import cn.ifxcode.dao.NavigationMapper;
import cn.ifxcode.model.Navigation;
import cn.ifxcode.service.NavigationService;


@Service("navigationService")
public class NavigationServiceImpl implements NavigationService{

	@Resource
	private NavigationMapper navigationDao ;

	@Cacheable(cacheName="baseCache")
	public List<Navigation> findAll() throws Exception {
		return navigationDao.findAll();
	}

}
