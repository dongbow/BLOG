package cn.ifxcode.dao;

import java.util.List;
import java.util.Map;

import cn.ifxcode.bean.PageInfo;
import cn.ifxcode.model.BookReply;

public interface BookReplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BookReply record);

    int insertSelective(BookReply record);

    BookReply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BookReply record);

    int updateByPrimaryKey(BookReply record);

	List findBookPageCondition(PageInfo pageInfo);

	int findBookPageCount(PageInfo pageInfo);

	List<BookReply> findParent(Map<String, Object> map);

	List<BookReply> findChild();
}