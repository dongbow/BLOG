package cn.ifxcode.service;

import java.util.List;

import cn.ifxcode.bean.Page;
import cn.ifxcode.bean.PageInfo;
import cn.ifxcode.model.BlogReply;

public interface BlogReplyService {

	public List<BlogReply> findAllReplyByBid(Integer bid, Page page);

	public void findAllEasyUI(PageInfo pageInfo);

	public int insert(BlogReply blogReply);

}
