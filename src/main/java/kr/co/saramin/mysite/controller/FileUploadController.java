package kr.co.saramin.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.saramin.mysite.service.FileUploadService;

@Controller
@RequestMapping("/fileupload")
public class FileUploadController {

	@Autowired
	private FileUploadService fileUploadService;

	@RequestMapping("/form")
	public String form() {
		return "fileupload/form";
	}

	@RequestMapping("/upload")
	public String upload(@RequestParam String email, @RequestParam("file1") MultipartFile file1, Model model) {
		String url = fileUploadService.restore(file1);
		model.addAttribute("urlImage", url);

		return "fileupload/result";
	}
}
