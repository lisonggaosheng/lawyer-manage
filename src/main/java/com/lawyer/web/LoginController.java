package com.lawyer.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lawyer.util.MD5Util;

@Controller
@ComponentScan
public class LoginController {

	/**
	 * Go login.html
	 * @return
	 */
	@RequestMapping(value = "toLogin", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	/**
	 * Go login
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, RedirectAttributes rediect,Model model) {
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		try {
			Subject currentUser = SecurityUtils.getSubject();
	        if (!currentUser.isAuthenticated()) {
	        	UsernamePasswordToken upToken = new UsernamePasswordToken(account, MD5Util.gerHsahMd5(password));
	        	upToken.setRememberMe(false);
	        	
	        	currentUser.login(upToken);
	        }
		} catch (AuthenticationException e) {
			
			rediect.addFlashAttribute("errorText", "您的账号或密码输入错误!");
			return "redirect:/toLogin";
		}
		return "redirect:/main";
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
