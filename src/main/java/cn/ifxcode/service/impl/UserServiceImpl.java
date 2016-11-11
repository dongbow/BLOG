package cn.ifxcode.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;

import cn.ifxcode.bean.PageInfo;
import cn.ifxcode.dao.DdMapper;
import cn.ifxcode.dao.UserMapper;
import cn.ifxcode.model.User;
import cn.ifxcode.service.UserService;

@Service("userService")
@SuppressWarnings("unused")
public class UserServiceImpl implements UserService{

	@Resource
	private UserMapper userDao;
	@Resource
	private DdMapper ddDao;
	
	private void convertDDL(List<User> userList){
		if( userList !=null && userList.size() > 0){
			Map<String, Object> map = Maps.newHashMap();
			for (User user : userList) {
				if(StringUtils.isNotBlank(user.getSex())){
					map.clear();
					map.put("name", "性别");
					map.put("sign", user.getSex());
					String sex = ddDao.findDDLValueByDDnameAndDDLSign(map);
					user.setSex(sex);
				}
				if(StringUtils.isNotBlank(user.getStatus())){
					map.clear();
					map.put("name", "用户状态");
					map.put("sign", user.getStatus());
					String status = ddDao.findDDLValueByDDnameAndDDLSign(map);
					user.setStatus(status);
				}
				if(StringUtils.isNotBlank(user.getIsdelete())){
					map.clear();
					map.put("name", "是否删除");
					map.put("sign", user.getIsdelete());
					String isdelete = ddDao.findDDLValueByDDnameAndDDLSign(map);
					user.setIsdelete(isdelete);
				}
			}
		}
	}
	
	@Cacheable(cacheName="baseCache")
	public List<User> findAllIsNotDelete() throws Exception{
		List<User> users = userDao.findAllIsNotDelete();
		this.convertDDL(users);
		return users;
	}
	
	public User login(String username, String password) throws Exception {
		Map<String,Object> map= Maps.newHashMap();
		map.put("username", username);
		map.put("password", password);
		return userDao.checkLogin(map);
	}

	@Cacheable(cacheName="baseCache")
	public User getById(Integer uid) throws Exception {
		return userDao.selectByPrimaryKey(uid);
	}

	@Override
	public User adminLogin(String username, String password) {
		Map<String,Object> map= Maps.newHashMap();
		map.put("username", username);
		map.put("password", password);
		return userDao.adminLogin(map);
	}

	@Cacheable(cacheName="baseCache")
	public User findUserByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Cacheable(cacheName="baseCache")
	public User findSuperAdminInfo() {
		return userDao.findSuperAdminInfo();
	}

	@Cacheable(cacheName="baseCache")
	public User findUserByNickname(String nickname) {
		User user = userDao.findUserByNickname(nickname);
		Map<String, Object> map = Maps.newHashMap();
		if(StringUtils.isNotBlank(user.getSex())){
			map.clear();
			map.put("name", "性别");
			map.put("sign", user.getSex());
			String sex = ddDao.findDDLValueByDDnameAndDDLSign(map);
			user.setSex(sex);
		}
		if(StringUtils.isNotBlank(user.getStatus())){
			map.clear();
			map.put("name", "用户状态");
			map.put("sign", user.getStatus());
			String status = ddDao.findDDLValueByDDnameAndDDLSign(map);
			user.setStatus(status);
		}
		if(StringUtils.isNotBlank(user.getIsdelete())){
			map.clear();
			map.put("name", "是否删除");
			map.put("sign", user.getIsdelete());
			String isdelete = ddDao.findDDLValueByDDnameAndDDLSign(map);
			user.setIsdelete(isdelete);
		}
		return user;
	}

	@Cacheable(cacheName="baseCache")
	public void findAllIsNotDelete(PageInfo pageInfo) throws Exception {
		pageInfo.setRows(userDao.findUserPageCondition(pageInfo));
		pageInfo.setTotal(userDao.findUserPageCount(pageInfo));
	}

	@Override
	public boolean findDataExits(String str) {
		return userDao.findDataExits(str);
	}

	@Cacheable(cacheName="baseCache")
	public User findByUid(Integer uid) {
		return userDao.selectByPrimaryKey(uid);
	}

	@TriggersRemove(cacheName="baseCache",removeAll=true)
	public int update(User upUser) {
		return userDao.updateByPrimaryKey(upUser);
	}

	@TriggersRemove(cacheName="baseCache",removeAll=true)
	public int insert(User user) {
		return userDao.insert(user);
	}

}
