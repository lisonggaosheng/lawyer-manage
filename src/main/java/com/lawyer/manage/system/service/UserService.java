package com.lawyer.manage.system.service;

import java.util.List;
import java.util.Set;

import com.lawyer.manage.system.domain.UserInfo;

public interface UserService {

	/**
	 * 获取用户信息
	 * @param account  登录账号
	 * @param password  登录密码
	 * @return
	 */
	UserInfo queryUerInfo(String account,String password);

	/**
	 * 获取资源集合
	 * 
	 * @param account
	 * @return
	 */
	Set<String> findPermissions(String account);

	/**
	 * 获取URL权限
	 * 
	 * @param account
	 * @return
	 */
	List<String> findPermissionUrl(String account);
}
