package cn.ifxcode.service;

import java.util.List;

import cn.ifxcode.bean.Page;
import cn.ifxcode.bean.PageInfo;
import cn.ifxcode.model.Mood;

public interface MoodService {

	public List<Mood> findAll(Page page) throws Exception;

	public void findAllCondition(PageInfo pageInfo);

	public void insertMood(Mood mood);
}
