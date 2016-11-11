package cn.ifxcode.service;

import cn.ifxcode.bean.PageInfo;
import cn.ifxcode.model.Loginlog;

public interface LoginlogService {

	public void insertLog(Loginlog loginlog);

	public void findAllLoginlog(PageInfo pageInfo);

}
