package com.lawyer.manage.system.domain;

import java.io.Serializable;
import java.util.Date;

public class UserInfo implements Serializable {

	private int id;
	/*
	 * 登录账号
	 */
	private String account;
	/*
	 * 登录密码
	 */
	private String password;
	/*
	 * 姓名
	 */
	private String username;
	/*
	 * 电话
	 */
	private String phone;
	/*
	 * 邮箱
	 */
	private String email;
	/*
	 * 微信openid
	 */
	private String wechatNumber;
	/*
	 * 状态（0有效  1无效）
	 */
	private int status;
	/*
	 * 会话ID
	 */
	private String sessionId;
	/*
	 * 最后一次登录时间
	 */
	private String lastLoginTime;
	/*
	 * 登录次数
	 */
	private String loginTimes;
	/*
	 * 登录
	 */
	private String loginIp;
	
	private Date createTime;
	private Date update_time;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWechatNumber() {
		return wechatNumber;
	}

	public void setWechatNumber(String wechatNumber) {
		this.wechatNumber = wechatNumber;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLoginTimes() {
		return loginTimes;
	}

	public void setLoginTimes(String loginTimes) {
		this.loginTimes = loginTimes;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
}