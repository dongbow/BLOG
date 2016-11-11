package cn.ifxcode.service;

import java.util.List;

import cn.ifxcode.bean.Page;
import cn.ifxcode.model.Message;

public interface MessageService {

	public int findMsgCountByUid(int uid);

	public List<Integer> findAllStatusMessage(Integer uid, int status);

	public int updateStatus0to1(List<Integer> messages);

	public List<Message> findAllMessage(Page page, Integer uid, String status);

	public int insert(Message message);

}
