package com.lawyer.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawyer.manage.system.dao.UserMapper;
import com.lawyer.manage.system.domain.UserInfo;
import com.lawyer.manage.system.service.UserService;

@Controller
public class HelloController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/hello",method=RequestMethod.GET)
    public String hello(Model model, @RequestParam(value="account", required=true) String account,@RequestParam(value="password", required=true) String password) {
    	UserInfo userInfo = userService.queryUerInfo(account, password);
    	
        model.addAttribute("name", userInfo.getUsername());
        return "hello";
    }
}
