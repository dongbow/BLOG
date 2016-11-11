package cn.ifxcode.dao;

import java.util.List;

import cn.ifxcode.bean.PageInfo;
import cn.ifxcode.model.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer rid);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer rid);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

	List findRolePageCondition(PageInfo pageInfo);

	int findRolePageCount(PageInfo pageInfo);

	List<Role> findAllRolename();
}