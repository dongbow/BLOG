package cn.ifxcode.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;

import cn.ifxcode.bean.PageInfo;
import cn.ifxcode.dao.RoleMapper;
import cn.ifxcode.model.Role;
import cn.ifxcode.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService{

	private Logger logger = Logger.getLogger(RoleServiceImpl.class);
	
	@Resource
	private RoleMapper roleDao;

	@Cacheable(cacheName="baseCache")
	public void findAll(PageInfo pageInfo) {
		pageInfo.setRows(roleDao.findRolePageCondition(pageInfo));
		pageInfo.setTotal(roleDao.findRolePageCount(pageInfo));
	}

	@Cacheable(cacheName="baseCache")
	public List<Role> findAllRolename() {
		return roleDao.findAllRolename();
	}
	
}
