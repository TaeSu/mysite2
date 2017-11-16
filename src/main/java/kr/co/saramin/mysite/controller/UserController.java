package kr.co.saramin.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.saramin.mysite.security.Auth;
import kr.co.saramin.mysite.security.Auth.Role;
import kr.co.saramin.mysite.security.AuthUser;
import kr.co.saramin.mysite.service.UserService;
import kr.co.saramin.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
//	@RequestMapping(value="/login", method=RequestMethod.POST)
//	public String login(HttpSession session, @ModelAttribute UserVo vo) {
//		UserVo authUser = userService.login(vo);
//		if (authUser == null) {
//			return "redirect:/user/login?result=fail";
//		}
//		
//		/* 인증처리 */
//		session.setAttribute("authUser", authUser);
//				
//		return "redirect:/main";
//	}
	
//	@RequestMapping("/logout")
//	public String logout(HttpSession session) {
//		session.removeAttribute("authUser");
//		session.invalidate();
//		
//		return "redirect:/main";
//	}
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join() {
		return "user/join";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@ModelAttribute UserVo vo) {
		userService.join(vo);
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping(value="/joinsuccess", method=RequestMethod.GET)
	public String joinsuccess() {
		return "user/joinsuccess";
	}
	
	@Auth(Role=Role.ADMIN)
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String modify(@AuthUser UserVo authUser, Model model) {
		Long no = authUser.getNo();
		UserVo userVo = userService.getUser(no);
		
		System.out.println(userVo);
		model.addAttribute("userVo", userVo);
		
		return "user/modify";
	}
}
