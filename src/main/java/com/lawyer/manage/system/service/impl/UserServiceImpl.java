package com.lawyer.manage.system.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.lawyer.manage.system.dao.UserMapper;
import com.lawyer.manage.system.domain.UserInfo;
import com.lawyer.manage.system.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

//	@Autowired
//	private MenuService menuService;

	/**
	 * 根据账号Account查询当前用户
	 * 
	 * @param account
	 * @return
	 */
	@Override
	public UserInfo queryUerInfo(String account,String password) {
		return userMapper.queryUerInfo(account,password);
	}
	
	@Override
	public UserInfo findByAccount(String account){
		return userMapper.findByAccount(account);
	}

	/**
	 * 获取资源集合
	 * 
	 * @param account
	 * @return
	 */
	@Override
	public Set<String> findPermissions(String account) {
		Set<String> set = Sets.newHashSet();
		UserInfo user = findByAccount(account);
//		List<MenuInfo> menus = menuService.findModuleByUserId(user.getId());
//
//		for (MenuInfo info : menus) {
//			set.add(String.valueOf(info.getId()));
//		}
		return set;
	}

	/**
	 * 获取URL权限
	 * 
	 * @param account
	 * @return
	 */
	@Override
	public List<String> findPermissionUrl(String account) {
		List<String> list = Lists.newArrayList();
		UserInfo user = findByAccount(account);
//		List<MenuInfo> modules = menuService.findModuleByUserId(user.getId());
//
//		for (MenuInfo info : modules) {
//			if (info.getMenuType() != MenuTypeEnum.Function.getCode()) {
//				list.add(info.getMenuUrl());
//			}
//		}
		return list;
	}
}