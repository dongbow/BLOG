package cn.ifxcode.dao;

import java.util.List;
import java.util.Map;

import cn.ifxcode.bean.Page;
import cn.ifxcode.bean.PageInfo;
import cn.ifxcode.model.Mood;


public interface MoodMapper {
    int deleteByPrimaryKey(Integer mid);

    int insert(Mood record);

    int insertSelective(Mood record);

    Mood selectByPrimaryKey(Integer mid);

    int updateByPrimaryKeySelective(Mood record);

    int updateByPrimaryKey(Mood record);

	List<Mood> findAll(Map<String, Object> map);

	List findMoodPageCondition(PageInfo pageInfo);

	int findMoodPageCount(PageInfo pageInfo);
}