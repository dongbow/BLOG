package cn.ifxcode.dao;

import java.util.List;
import java.util.Map;

import cn.ifxcode.model.Message;

public interface MessageMapper {
    int deleteByPrimaryKey(Integer messageid);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer messageid);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);

	int findMsgCountByUid(int uid);

	List<Integer> findAllStatusMessage(Map<String, Object> map);

	int updateStatus0to1(List<Integer> messages);

	List<Message> findAllMessage(Map<String, Object> map);
}