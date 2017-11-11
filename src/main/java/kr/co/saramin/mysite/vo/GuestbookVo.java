package kr.co.saramin.mysite.vo;

import java.util.Date;

public class GuestbookVo {
	private Long no;
	private String name;
	private Date regDate;
	private String message;
	private String password;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "GuestbookVo [no=" + no + ", name=" + name + ", regDate=" + regDate + ", message=" + message
				+ ", password=" + password + "]";
	}
}
