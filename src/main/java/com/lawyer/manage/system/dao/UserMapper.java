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
	
	@Select("select * from sys_user where login_account=#{account}")
	UserInfo findByAccount(String account);
}
