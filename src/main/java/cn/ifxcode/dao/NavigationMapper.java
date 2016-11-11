package cn.ifxcode.dao;

import java.util.List;

import cn.ifxcode.model.Navigation;

public interface NavigationMapper {
    int deleteByPrimaryKey(Integer nid);

    int insert(Navigation record);

    int insertSelective(Navigation record);

    Navigation selectByPrimaryKey(Integer nid);

    int updateByPrimaryKeySelective(Navigation record);

    int updateByPrimaryKey(Navigation record);

	List<Navigation> findAll();
}