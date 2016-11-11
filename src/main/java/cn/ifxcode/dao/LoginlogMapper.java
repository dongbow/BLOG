package cn.ifxcode.dao;

import java.util.List;

import cn.ifxcode.bean.PageInfo;
import cn.ifxcode.model.Loginlog;

public interface LoginlogMapper {
    int deleteByPrimaryKey(Integer loginid);

    int insert(Loginlog record);

    int insertSelective(Loginlog record);

    Loginlog selectByPrimaryKey(Integer loginid);

    int updateByPrimaryKeySelective(Loginlog record);

    int updateByPrimaryKey(Loginlog record);

	List findLoginlogPageCondition(PageInfo pageInfo);

	int findLoginlogPageCount(PageInfo pageInfo);
}