package com.lawyer.config.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lawyer.manage.system.domain.UserInfo;
import com.lawyer.manage.system.service.UserService;
import com.lawyer.util.MD5Util;

/**
 * 验证用户登录
 * 
 * @author Administrator
 */
@Component("userRealm")
public class UserRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;

	public UserRealm() {
		setName("UserRealm");
		// 采用MD5加密
		setCredentialsMatcher(new HashedCredentialsMatcher("md5"));
	}

	// 权限资源角色
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
		
		String username = (String) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		//设置权限列表
		info.setStringPermissions(userService.findPermissions(username));
		// add Roles String[Set<String> roles]
		// info.setRoles(roles);
		return info;
	}

	// 登录验证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("###【开始认证[SessionId]】"+SecurityUtils.getSubject().getSession().getId());
		
	    UsernamePasswordToken upt = (UsernamePasswordToken) token;
		String loginAcount = upt.getUsername();
		UserInfo userInfo = userService.findByAccount(loginAcount);
		if (userInfo == null) {
			throw new AccountException("帐号或密码不正确！");
		}
		if(userInfo.getStatus() != 0){
			throw new DisabledAccountException("帐号已经禁止登录，请联系管理员！");
		}
		System.out.println("DB中密码:"+MD5Util.gerHsahMd5(userInfo.getPassword()));
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userInfo, MD5Util.gerHsahMd5(userInfo.getPassword()), getName());
		
		return authenticationInfo;
	}
}