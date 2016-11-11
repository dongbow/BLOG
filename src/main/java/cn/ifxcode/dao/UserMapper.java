package cn.ifxcode.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.ifxcode.bean.PageInfo;
import cn.ifxcode.model.User;


public interface UserMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

	User checkLogin(Map<String, Object> map);

	User findSuperAdminInfo();

	User adminLogin(Map<String, Object> map);

	User findUserByNickname(String nickname);

	List<User> findAllIsNotDelete();

	List findUserPageCondition(PageInfo pageInfo);

	int findUserPageCount(PageInfo pageInfo);

	boolean findDataExits(String str);
}