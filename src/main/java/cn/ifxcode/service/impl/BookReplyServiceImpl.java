package cn.ifxcode.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;

import cn.ifxcode.bean.Page;
import cn.ifxcode.bean.PageInfo;
import cn.ifxcode.dao.BookReplyMapper;
import cn.ifxcode.model.BookReply;
import cn.ifxcode.service.BookReplyService;
import cn.ifxcode.utils.StringHtmlUtils;

@Service("bookReplyService")
public class BookReplyServiceImpl implements BookReplyService {

	private Logger logger = Logger.getLogger(BookReplyServiceImpl.class);
	
	@Resource
	private BookReplyMapper bookReplyDao;
	
	public void convert(List<BookReply> bookReplies){
		if(bookReplies != null && bookReplies.size() >= 0){
			for (BookReply bookReply : bookReplies) {
				bookReply.setContent(StringHtmlUtils.stringHtmlUnescape(bookReply.getContent()));
			}
		}
	}
	
	public void tree(List<BookReply> parentBookReplies, List<BookReply> childBookReplies){
		if(parentBookReplies != null && parentBookReplies.size() > 0
				&& childBookReplies != null && childBookReplies.size() > 0){
			for (BookReply P_bookReply : parentBookReplies) {
				P_bookReply.setParentReply(null);
				List<BookReply> flagBook = new ArrayList<BookReply>();
				for (BookReply C_bookReply : childBookReplies) {
					if(P_bookReply.getId() == C_bookReply.getParent_id()){
						C_bookReply.setParentReply(P_bookReply);
						flagBook.add(C_bookReply);
						//tree(childBookReplies, childBookReplies);
					}	
				}
				P_bookReply.setChildBookreplies(flagBook);
			}
		}
	}
	
	@Cacheable(cacheName="baseCache")
	public List<BookReply> findAll(Page page) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("page", page);
		logger.info("分页查询留言板内容，返回list");
		List<BookReply> parentBookReplies = bookReplyDao.findParent(map);
		List<BookReply> childBookReplies = bookReplyDao.findChild();
		this.convert(parentBookReplies);
		this.convert(childBookReplies);
		this.tree(parentBookReplies, childBookReplies);
		return parentBookReplies;
	}

	@Cacheable(cacheName="baseCache")
	public void findAllEasyUI(PageInfo pageInfo) {
		pageInfo.setRows(bookReplyDao.findBookPageCondition(pageInfo));
		pageInfo.setTotal(bookReplyDao.findBookPageCount(pageInfo));
	}

	@TriggersRemove(cacheName="baseCache",removeAll=true)
	public int insert(BookReply bookReply) {
		return bookReplyDao.insert(bookReply);
	}

	@Cacheable(cacheName="baseCache")
	public BookReply findReplyBybfid(int parseInt) {
		return bookReplyDao.selectByPrimaryKey(parseInt);
	}

	@TriggersRemove(cacheName="baseCache",removeAll=true)
	public int update(BookReply parent) {
		return bookReplyDao.updateByPrimaryKey(parent);
	}

}
