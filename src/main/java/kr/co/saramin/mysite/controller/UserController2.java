package kr.co.saramin.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import kr.co.saramin.mysite.service.UserService;
import kr.co.saramin.mysite.vo.UserVo;

@Controller
@SessionAttributes("authUser")
@RequestMapping("/user2")
public class UserController2 {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "user/login2";
	}
	
	@RequestMapping("/auth")
	public String auth(@ModelAttribute UserVo vo, Model model) {
		UserVo authUser = userService.login(vo);
		model.addAttribute("authUser", authUser);
		System.out.println("auth : " + authUser);
		return "redirect:/";
	}
	
	@ResponseBody
	@RequestMapping("/modify")
	public String modify(@ModelAttribute("authUser") UserVo authUser) {
		System.out.println("modify : " + authUser);
		return "UserController2:modify";
	}
}
