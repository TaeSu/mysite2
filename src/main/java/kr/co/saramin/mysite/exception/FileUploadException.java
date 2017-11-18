package kr.co.saramin.mysite.exception;

@SuppressWarnings("serial")
public class FileUploadException extends RuntimeException {
	
	public FileUploadException() {
		super("FileUploadException Exception");
	}
	
	public FileUploadException(String message) {
		super("FileUploadException Exception: " + message);
	}
}
