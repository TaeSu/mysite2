package kr.co.saramin.mysite.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	
	@RequestMapping({"", "/main"})
	public String main() {
//		return "/WEB-INF/views/main/index.jsp";
		return "main/index";
	}
	
	@ResponseBody
	@RequestMapping("/hello")
	public String hello() {
		return "안녕하세요~";
	}
	
	@ResponseBody
	@RequestMapping("/hello2")
	public Map<String, Object> hello2() {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("data", "안녕하세요.");
		
		return map;
	}
}
