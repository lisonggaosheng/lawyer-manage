package com.lawyer.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.lawyer.manage.system.domain.UserInfo;
import com.lawyer.manage.system.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lawyer.util.MD5Util;

@Controller
@ComponentScan
public class LoginController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private UserService userService;

	/**
	 * Go login.jsp
	 * @return
	 */
	@RequestMapping(value = "toLogin", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	/**
	 * 登录验证
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(@Valid UserInfo user, BindingResult bindingResult,
						RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()){
			return "redirect:toLogin";
		}
		if(StringUtils.isBlank(user.getAccount()) || StringUtils.isBlank(user.getPassword())){
			logger.info("用户名或密码为空! ");
			redirectAttributes.addFlashAttribute("message", "用户名或密码为空!");
			return "redirect:login";
		}
		String accountName = user.getAccount();
		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken upToken = new UsernamePasswordToken(user.getAccount(), MD5Util.gerHsahMd5(user.getPassword()));
		try {
			logger.info("对用户[" + accountName + "]进行登录验证..验证开始");
			currentUser.login(upToken);
			logger.info("对用户[" + accountName + "]进行登录验证..验证通过");
		} catch(UnknownAccountException uae){
			logger.info("对用户[" + accountName + "]进行登录验证..验证未通过,未知账户");
			redirectAttributes.addFlashAttribute("message", "未知账户");
		}catch(IncorrectCredentialsException ice){
			logger.info("对用户[" + accountName + "]进行登录验证..验证未通过,错误的凭证");
			redirectAttributes.addFlashAttribute("message", "密码不正确");
		}catch(LockedAccountException lae){
			logger.info("对用户[" + accountName + "]进行登录验证..验证未通过,账户已锁定");
			redirectAttributes.addFlashAttribute("message", "账户已锁定");
		}catch(ExcessiveAttemptsException eae){
			logger.info("对用户[" + accountName + "]进行登录验证..验证未通过,错误次数大于5次,账户已锁定");
			redirectAttributes.addFlashAttribute("message", "用户名或密码错误次数大于5次,账户已锁定");
		}catch (DisabledAccountException sae){
			logger.info("对用户[" + accountName + "]进行登录验证..验证未通过,帐号已经禁止登录");
			redirectAttributes.addFlashAttribute("message", "帐号已经禁止登录");
		}catch(AuthenticationException ae){
			//通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
			logger.info("对用户[" + accountName + "]进行登录验证..验证未通过,堆栈轨迹如下");
			ae.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "用户名或密码不正确");
		}

		//验证是否登录成功
		if(currentUser.isAuthenticated()){
			logger.info("用户[" + accountName + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
			//把当前用户放入session
			Session session = currentUser.getSession();
			UserInfo tUser = userService.findByAccount(accountName);
			session.setAttribute("currentUser",tUser);
			return "redirect:main";
//      return "/main";
		}else{
			upToken.clear();
			return "redirect:/toLogin";
		}
	}

	/**
	 * Exit
	 * 
	 * @return
	 */
	@RequestMapping("logout")
	public String logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "redirect:/index";
	}
}
