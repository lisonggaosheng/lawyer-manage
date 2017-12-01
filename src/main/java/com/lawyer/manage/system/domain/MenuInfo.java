package com.lawyer.manage.system.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class MenuInfo implements Serializable {

	private static final long serialVersionUID = 2354275897681109437L;

	private int id;
	/*
	 * 父节点id
	 */
	private int parentId;
	/*
	 * 菜单名称
	 */
	private String menuName;
	/*
	 * 菜单请求地址
	 */
	private String menuUrl;
	/*
	 * 菜单类型
	 */
	private int menuType;
	/*
	 * 有效性（1为无效，0为有效）
	 */
	private int isVaild;
	/*
	 * 菜单功能说明
	 */
	private String menuInfo;
	private Date createTime;
	private Date UpdateTime;
}