package cn.ifxcode.service;

import java.util.List;

import cn.ifxcode.bean.Page;
import cn.ifxcode.bean.PageInfo;
import cn.ifxcode.model.BlogTopic;
import cn.ifxcode.vo.BlogTopicTime;

public interface BlogTopicService {

	public List<BlogTopic> findAll(Page page);

	public BlogTopic findBlogTopicAndSignAndClassifyByBid(int bid);

	public List<BlogTopic> findHomeBlogAndClassify();

	public List<BlogTopic> findBlogLastest5();

	public List<BlogTopic> findBlogViewCountTop8();

	public List<BlogTopic> findBlogReplyCountTop8();

	public int updateTopic(BlogTopic blogTopicy);

	public BlogTopic selectByPrimaryKey(int parseInt);

	public List<BlogTopicTime> findAllGroupDate();

	public BlogTopic findMinBlogTopic(Integer bid);

	public BlogTopic findMaxBlogTopic(Integer bid);

	public List<BlogTopic> findAllBlogByClassifyId(Integer cid);

	public void findAllEasyUI(PageInfo pageInfo);

	public void findAllBlogEasyUI(PageInfo pageInfo);

	public int insert(BlogTopic blogTopic);

	public List<BlogTopic> findTopicByBsname(Page page, String sign);

	public List<BlogTopic> findTopicByCid(Page page, String cid);

	public List<BlogTopic> findTopicByDate(Page page, String date);

	public List<BlogTopic> findAllNotCondition();
}
