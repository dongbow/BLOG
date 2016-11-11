package cn.ifxcode.service;

import java.util.List;

import cn.ifxcode.bean.Page;
import cn.ifxcode.bean.PageInfo;
import cn.ifxcode.model.BookReply;

public interface BookReplyService {

	public void findAllEasyUI(PageInfo pageInfo);

	public int insert(BookReply bookReply);

	public BookReply findReplyBybfid(int parseInt);

	public int update(BookReply parent);

	public List<BookReply> findAll(Page page);
}
