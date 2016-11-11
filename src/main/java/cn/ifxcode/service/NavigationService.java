package cn.ifxcode.service;

import java.util.List;

import cn.ifxcode.model.Navigation;


public interface NavigationService {

	public List<Navigation> findAll() throws Exception;
	
}
