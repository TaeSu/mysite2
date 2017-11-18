package kr.co.saramin.mysite.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.saramin.mysite.exception.FileUploadException;

@Service
public class FileUploadService {

	private static final String SAVE_PATH = "/uploads/";
	private static final String PREFIX_URL = "/uploads/images/";

	public String restore(MultipartFile multipartFile) {
		String url = "";

		try {
			String originalFilename = multipartFile.getOriginalFilename();
			String extName = originalFilename.substring(originalFilename.lastIndexOf('.'), originalFilename.length());
			String saveFilename = genSaveFilename(extName);
			Long size = multipartFile.getSize();

			System.out.println("#### " + originalFilename);
			System.out.println("#### " + saveFilename);
			System.out.println("#### " + size);

			writeFile(multipartFile, saveFilename);
			url = PREFIX_URL + saveFilename;
		} catch (IOException e) {
			throw new FileUploadException();
		}

		return url;
	}

	private void writeFile(MultipartFile multipartFile, String saveFilename) throws IOException {
		byte[] bytes = multipartFile.getBytes();
		FileOutputStream fis = new FileOutputStream(SAVE_PATH + saveFilename);
		fis.write(bytes);
		fis.close();
	}

	private String genSaveFilename(String extName) {
		String filename = "";

		Calendar cal = Calendar.getInstance();
		filename += cal.get(Calendar.YEAR);
		filename += cal.get(Calendar.MONTH);
		filename += cal.get(Calendar.DATE);
		filename += cal.get(Calendar.HOUR);
		filename += cal.get(Calendar.MINUTE);
		filename += cal.get(Calendar.SECOND);
		filename += cal.get(Calendar.MILLISECOND);
		filename += extName;

		return filename;
	}
}
