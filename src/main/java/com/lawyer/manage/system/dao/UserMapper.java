package com.lawyer.manage.system.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.lawyer.manage.system.domain.UserInfo;

//@Component
@Mapper
public interface UserMapper {

	@Select("select * from sys_user where login_account=#{account} and login_password=#{password}")
	UserInfo queryUerInfo(@Param("account")String account,@Param("password")String password);
	
	@Select("select id,login_account as account,login_password as password,username,phone,email,wechat_number as wechatNumber,status,session_id as sessionId,last_login_time as lastLoginTime,login_times as loginTimes,login_ip as loginIp\n" + 
			" from sys_user where login_account=#{account}")
	UserInfo findByAccount(String account);
}
