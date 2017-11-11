package kr.co.saramin.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.saramin.mysite.vo.GuestbookVo;

@Repository
public class GuestbookDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public boolean insert(GuestbookVo vo) {
		int count = sqlSession.insert("guestbook.insert", vo);
		return count > 0;
	}
	
	public boolean delete(Long no, String password) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("no", no);
		map.put("password", password);
		
		int count = sqlSession.delete("guestbook.delete", map);
		return count > 0;
	}
	
	public List<GuestbookVo> getList() {
		List<GuestbookVo> list = sqlSession.selectList("guestbook.getList");
		return list;
	}
}
