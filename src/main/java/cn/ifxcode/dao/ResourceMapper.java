package cn.ifxcode.dao;

import java.util.List;

import cn.ifxcode.model.Resource;

public interface ResourceMapper {
    int deleteByPrimaryKey(Integer resid);

    int insert(Resource record);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(Integer resid);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);

	List<Resource> findAllResource();
}