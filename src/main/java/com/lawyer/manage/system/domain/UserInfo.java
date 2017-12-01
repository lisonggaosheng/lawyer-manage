package com.lawyer.manage.system.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class UserInfo implements Serializable {

	private static final long serialVersionUID = -2736075850489736894L;

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
	
	

}