package kr.co.saramin.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.saramin.mysite.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public UserVo get(UserVo vo) {
		UserVo userVo = sqlSession.selectOne("user.getByEmailAndPassword", vo);
		return userVo;
	}
	
	public UserVo get(Long no) {
		UserVo userVo = sqlSession.selectOne("user.getByNo", no);
		return userVo;
	}
	
	public UserVo get(String email) {
		UserVo userVo = sqlSession.selectOne("user.getByEmail", email);
		return userVo;
	}
	
	public boolean insert(UserVo vo) {
		int count = sqlSession.insert("user.insert", vo);
		return count > 0;
	}
	
}
