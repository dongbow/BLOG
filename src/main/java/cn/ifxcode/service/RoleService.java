package cn.ifxcode.service;

import java.util.List;

import cn.ifxcode.bean.PageInfo;
import cn.ifxcode.model.Role;

public interface RoleService {

	public void findAll(PageInfo pageInfo);

	public List<Role> findAllRolename();

}
