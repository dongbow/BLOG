package cn.ifxcode.dao;

import java.util.List;

import cn.ifxcode.bean.PageInfo;
import cn.ifxcode.model.Friendlink;

public interface FriendlinkMapper {
    int deleteByPrimaryKey(Integer fid);

    int insert(Friendlink record);

    int insertSelective(Friendlink record);

    Friendlink selectByPrimaryKey(Integer fid);

    int updateByPrimaryKeySelective(Friendlink record);

    int updateByPrimaryKey(Friendlink record);

	List<Friendlink> findNameAndUrlAll();

	List findFriendLinkPageCondition(PageInfo pageInfo);

	int findFriendLinkPageCount(PageInfo pageInfo);
}