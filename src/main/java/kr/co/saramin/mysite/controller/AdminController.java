package kr.co.saramin.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.saramin.mysite.security.Auth;

@Auth(Role=Auth.Role.ADMIN)
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@RequestMapping("/user/list")
	public String userList() {
		return "";
	}
	
	@RequestMapping("/user/view")
	public String userView() {
		return "";
	}
}
