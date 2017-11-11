package kr.co.saramin.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.saramin.mysite.repository.UserDao;
import kr.co.saramin.mysite.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
//	@Autowired
//	private Mailer mailer;
	
	public UserVo login(UserVo vo) {
		UserVo authUser = userDao.get(vo);
		return authUser;
	}
	
	public boolean join(UserVo vo) {
		boolean result = userDao.insert(vo);
//		mailer.sendMail();
		return result;
	}
	
	public UserVo getUser(Long no) {
		UserVo vo = userDao.get(no);
		return vo;
	}
}
