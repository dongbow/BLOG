package cn.ifxcode.dao;

import java.util.List;

import cn.ifxcode.bean.PageInfo;
import cn.ifxcode.model.Ddl;

public interface DdlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Ddl record);

    int insertSelective(Ddl record);

    Ddl selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Ddl record);

    int updateByPrimaryKey(Ddl record);

	List findDDLPageCondition(PageInfo pageInfo);

	int findDDLPageCount(PageInfo pageInfo);
}