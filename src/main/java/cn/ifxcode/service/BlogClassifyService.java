package cn.ifxcode.service;

import java.util.List;

import cn.ifxcode.bean.PageInfo;
import cn.ifxcode.model.BlogClassify;

public interface BlogClassifyService {

	public List<BlogClassify> findAll();

	public void findAllClassify(PageInfo pageInfo);

	public BlogClassify findByCid(int parseInt);

	public int update(BlogClassify blogClassify);

	public int insert(BlogClassify classify);

}
