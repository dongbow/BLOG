package cn.ifxcode.dao;

import java.util.List;

import cn.ifxcode.bean.PageInfo;
import cn.ifxcode.model.Backup;

public interface BackupMapper {
    int deleteByPrimaryKey(Integer backid);

    int insert(Backup record);

    int insertSelective(Backup record);

    Backup selectByPrimaryKey(Integer backid);

    int updateByPrimaryKeySelective(Backup record);

    int updateByPrimaryKey(Backup record);

	List findBackupPageCondition(PageInfo pageInfo);

	int findBackupPageCount(PageInfo pageInfo);
}