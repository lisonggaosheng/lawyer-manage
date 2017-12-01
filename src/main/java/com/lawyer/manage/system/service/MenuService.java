package com.lawyer.manage.system.service;

import java.util.List;

import com.lawyer.manage.system.domain.MenuInfo;

public interface MenuService {
	/**
	 * 获取角色模块
	 * 
	 * @param userId
	 * @return
	 */
	List<MenuInfo> findModuleByUserId(int userId);
}
