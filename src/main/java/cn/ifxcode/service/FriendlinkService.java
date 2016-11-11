package cn.ifxcode.service;

import java.util.List;

import cn.ifxcode.bean.PageInfo;
import cn.ifxcode.model.Friendlink;

public interface FriendlinkService {

	public List<Friendlink> findNameAndUrlAll();

	public void findAllLink(PageInfo pageInfo);

}
