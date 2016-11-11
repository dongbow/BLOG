package cn.ifxcode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

	@RequestMapping("/404")
	public String error_404() throws Exception{
		return "error/404";
	}
	
	@RequestMapping("/500")
	public String error_500() throws Exception{
		return "error/500";
	}

}
