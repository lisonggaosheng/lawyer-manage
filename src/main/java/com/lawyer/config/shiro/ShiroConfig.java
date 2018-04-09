package com.lawyer.config.shiro;

import java.util.Map;

import javax.servlet.DispatcherType;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.ServletContainerSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import com.google.common.collect.Maps;
import com.lawyer.config.redis.MyRedisProperties;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class ShiroConfig {

	/**
	 * 加载属性文件数据
	 * 
	 * @return
	 */
	@Bean
	public MyRedisProperties shiroProperties() {
		return new MyRedisProperties();
	}

	/**
	 * FilterRegistrationBean
	 * 
	 * @return
	 */
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
		filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilterFactory"));
		filterRegistration.setEnabled(true);
		filterRegistration.addUrlPatterns("/*");
		filterRegistration.setDispatcherTypes(DispatcherType.REQUEST);
		return filterRegistration;
	}

	/**
	 * @see org.apache.shiro.spring.web.ShiroFilterFactoryBean
	 * @return
	 */
	@Autowired
	@Bean(name = "shiroFilterFactory")
	public ShiroFilterFactoryBean getShiroFilterFactory() {
		ShiroFilterFactoryBean shiroFilterFactoryBean  = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager());
		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/main");
      	//未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthor");

		Map<String, String> filterChainDefinitionMap  = Maps.newHashMap();

		// 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/static/*", "anon");
        filterChainDefinitionMap.put("/templates/*", "anon");
        filterChainDefinitionMap.put("/toLogin", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");
		filterChainDefinitionMap.put("/error", "anon");
        // <!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        // <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
		//配置记住我或认证通过可以访问的地址
		filterChainDefinitionMap.put("/", "user");
        filterChainDefinitionMap.put("/**", "authc");
		filterChainDefinitionMap.put("/*.*", "authc");
        
		
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	/**
	 * 权限管理器
	 * 
	 * @return
	 */
	@Bean(name = "securityManager")
	public DefaultWebSecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 数据库认证的实现
		securityManager.setRealm(userRealm());
		// session 管理器
//		securityManager.setSessionManager(sessionManager());
		// 缓存管理器
		securityManager.setCacheManager(ehCacheManager());
		//注入记住我管理器
		securityManager.setRememberMeManager(rememberMeManager());
		return securityManager;
	}

	/**
	 * DefaultWebSessionManager
	 * 
	 * @return
	 */
	@Bean(name = "sessionManager")
	public ServletContainerSessionManager sessionManager() {
		ServletContainerSessionManager sessionManager = new ServletContainerSessionManager();
		return sessionManager;
	}

	/**
	 * @see UserRealm--->AuthorizingRealm
	 * @return
	 */
	@Bean
//	@DependsOn(value = { "lifecycleBeanPostProcessor", "shrioRedisCacheManager" })
	public UserRealm userRealm() {
		UserRealm userRealm = new UserRealm();
//		userRealm.setCacheManager(redisCacheManager());
		userRealm.setCachingEnabled(true);
		userRealm.setAuthenticationCachingEnabled(false);//禁用认证缓存
		userRealm.setAuthorizationCachingEnabled(true);
		return userRealm;
	}
	
	/**  
	    * shiro缓存管理器;  
	    * 需要注入对应的其它的实体类中：  
	    * 1、安全管理器：securityManager  
	    * 可见securityManager是整个shiro的核心；  
	    * @return  
	    */  
	  
	   @Bean  
	   public EhCacheManager ehCacheManager(){  
	      System.out.println("ShiroConfiguration.getEhCacheManager()");  
	      EhCacheManager cacheManager = new EhCacheManager();  
	      cacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");  
	      return cacheManager;  
	  
	   }

	/**
	 * cookie对象;
	 * rememberMeCookie()方法是设置Cookie的生成模版，比如cookie的name，cookie的有效时间等等。
	 * @return
	 */
	@Bean
	public SimpleCookie rememberMeCookie(){
		//这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
		SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
		//<!-- 记住我cookie生效时间3天 ,单位秒;-->
		simpleCookie.setMaxAge(259200);
		return simpleCookie;
	}

	/**
	 * cookie管理对象;
	 * rememberMeManager()方法是生成rememberMe管理器，而且要将这个rememberMe管理器设置到securityManager中
	 * @return
	 */
	@Bean
	public CookieRememberMeManager rememberMeManager(){
		//System.out.println("ShiroConfiguration.rememberMeManager()");
		CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
		cookieRememberMeManager.setCookie(rememberMeCookie());
		//rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
		cookieRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
		return cookieRememberMeManager;
	}

}