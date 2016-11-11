package cn.ifxcode.dao;

import java.util.List;
import java.util.Map;

import cn.ifxcode.bean.PageInfo;
import cn.ifxcode.model.BlogSign;
import cn.ifxcode.model.BlogTopic;

public interface BlogSignMapper {
    int deleteByPrimaryKey(Integer bsid);

    int insert(BlogSign record);

    int insertSelective(BlogSign record);

    BlogSign selectByPrimaryKey(Integer bsid);

    int updateByPrimaryKeySelective(BlogSign record);

    int updateByPrimaryKey(BlogSign record);

	List findSignPageCondition(PageInfo pageInfo);

	int findSignPageCount(PageInfo pageInfo);

	int insertList(List<BlogSign> blogSigns);

	List<BlogTopic> findTopicByBsname(Map<String, Object> map);
}