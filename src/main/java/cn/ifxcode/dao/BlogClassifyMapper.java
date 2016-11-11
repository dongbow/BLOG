package cn.ifxcode.dao;

import java.util.List;

import cn.ifxcode.bean.PageInfo;
import cn.ifxcode.model.BlogClassify;

public interface BlogClassifyMapper {
    int deleteByPrimaryKey(Integer cid);

    int insert(BlogClassify record);

    int insertSelective(BlogClassify record);

    BlogClassify selectByPrimaryKey(Integer cid);

    int updateByPrimaryKeySelective(BlogClassify record);

    int updateByPrimaryKey(BlogClassify record);

	List<BlogClassify> findAll();

	List findClassifyPageCondition(PageInfo pageInfo);

	int findClassifyPageCount(PageInfo pageInfo);
}