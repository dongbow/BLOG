package cn.ifxcode.dao;

import java.util.List;
import java.util.Map;

import cn.ifxcode.bean.PageInfo;
import cn.ifxcode.model.Dd;

public interface DdMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Dd record);

    int insertSelective(Dd record);

    Dd selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Dd record);

    int updateByPrimaryKey(Dd record);

	String findDDLValueByDDnameAndDDLSign(Map<String, Object> map);

	List<Dd> findAllIdAndName();
}