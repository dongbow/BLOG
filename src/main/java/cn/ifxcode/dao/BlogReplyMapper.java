package cn.ifxcode.dao;

import java.util.List;
import java.util.Map;

import cn.ifxcode.bean.PageInfo;
import cn.ifxcode.model.BlogReply;

public interface BlogReplyMapper {
    int deleteByPrimaryKey(Integer bg_rid);

    int insert(BlogReply record);

    int insertSelective(BlogReply record);

    BlogReply selectByPrimaryKey(Integer bg_rid);

    int updateByPrimaryKeySelective(BlogReply record);

    int updateByPrimaryKey(BlogReply record);

	List<BlogReply> findAllReplyByBid(Map<String, Object> map);

	List findBlogReplyPageCondition(PageInfo pageInfo);

	int findBlogReplyPageCount(PageInfo pageInfo);
}