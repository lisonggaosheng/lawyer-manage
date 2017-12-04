package com.lawyer.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

	/**
	 * Go Index
	 * 
	 * @return
	 */
	@RequestMapping(value = { "", "/", "index" })
	public String index() {
		return "index";
	}

	/**
	 * Go Index
	 * 
	 * @return
	 */
//	@RequestMapping(value = {"main" })
	@RequestMapping(value = "main", method = RequestMethod.GET)
	public String main() {
		return "main";
	}
	
	/**
	 * unauthor
	 * 
	 * @return
	 */
	@RequestMapping("unauthor")
	public String unauthor() {
		return "unauthor";
	}

	/**
	 * reports
	 * 
	 * @return
	 */
	@RequestMapping("reports")
	public String reports() {
		return "reports";
	}
}
