package cn.ifxcode.service;


import cn.ifxcode.bean.PageInfo;
import cn.ifxcode.model.User;

public interface UserService {
	
	/**
	 * 用户登录验证
	 * params: username, password
	 * return: user
	 * */
	public User login(String username, String password) throws Exception;

	public User getById(Integer uid) throws Exception;

	public User adminLogin(String username, String password) throws Exception;

	public User findUserByUsername(String username);
	
	public User findUserByNickname(String nickname);

	public User findSuperAdminInfo();
	
	public void findAllIsNotDelete(PageInfo pageInfo) throws Exception;

	public boolean findDataExits(String str);

	public User findByUid(Integer uid);

	public int update(User upUser);

	public int insert(User user);
	
}
