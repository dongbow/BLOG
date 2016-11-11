package cn.ifxcode.service;

import cn.ifxcode.bean.PageInfo;
import cn.ifxcode.model.Backup;

public interface BackupService {

	public int insert(Backup backup);

	public void findAll(PageInfo pageInfo);

}
