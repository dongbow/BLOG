package cn.ifxcode.dao;

import java.util.List;
import java.util.Map;

import cn.ifxcode.bean.PageInfo;
import cn.ifxcode.model.BlogTopic;
import cn.ifxcode.vo.BlogTopicTime;

public interface BlogTopicMapper {
    int deleteByPrimaryKey(Integer bid);

    int insert(BlogTopic record);

    int insertSelective(BlogTopic record);

    BlogTopic selectByPrimaryKey(Integer bid);

    int updateByPrimaryKeySelective(BlogTopic record);

    int updateByPrimaryKeyWithBLOBs(BlogTopic record);

    int updateByPrimaryKey(BlogTopic record);

	List<BlogTopic> findAll(Map<String, Object> map);

	BlogTopic findBlogTopicAndSignAndClassifyByBid(int bid);

	List<BlogTopic> findHomeBlogAndClassify();

	List<BlogTopic> findBlogLastest5();

	List<BlogTopic> findBlogViewCountTop8();

	List<BlogTopic> findBlogReplyCountTop8();

	List<BlogTopicTime> findAllGroupDate();

	BlogTopic findMinBlogTopic(Integer bid);

	BlogTopic findMaxBlogTopic(Integer bid);

	List<BlogTopic> findAllBlogByClassifyId(Integer cid);

	List findhomePageCondition(PageInfo pageInfo);

	int findhomePageCount(PageInfo pageInfo);

	List findBlogPageCondition(PageInfo pageInfo);

	int findBlogPageCount(PageInfo pageInfo);

	List<BlogTopic> findTopicByBsname(Map<String, Object> map);

	List<BlogTopic> findTopicByCid(Map<String, Object> map);

	List<BlogTopic> findTopicByDate(Map<String, Object> map);

	List<BlogTopic> findAllNotCondition();

}