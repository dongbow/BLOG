package cn.ifxcode.service;

import java.util.List;

import cn.ifxcode.bean.Page;
import cn.ifxcode.bean.PageInfo;
import cn.ifxcode.model.BlogSign;
import cn.ifxcode.model.BlogTopic;

public interface BlogSignService {

	public void findAllSign(PageInfo pageInfo);

	public int insertList(List<BlogSign> blogSigns);

	public List<BlogTopic> findTopicByBsname(Page page);

}
