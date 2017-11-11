package kr.co.saramin.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.saramin.mysite.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private DataSource dataSource;
	
	public UserVo get(String email, String password) {
		UserVo vo = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			
			//3. Statement 생성
			String sql = "select no, name from user where email=? and password=password(?)";
			pstmt = conn.prepareStatement(sql);
			
			//4. binding
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			
			//5. SQL문 실행
			rs = pstmt.executeQuery();
			if (rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				
				vo = new UserVo();
				vo.setNo(no);
				vo.setName(name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return vo;
	}
	
	public boolean insert(UserVo vo) {
		boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			
			//3. Statement 생성
			String sql = "insert into user values(null, ?, ?, password(?), ?)";
			pstmt = conn.prepareStatement(sql);
			
			//4. binding
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getGender());
			
			//5. SQL문 실행
			int updateCount = pstmt.executeUpdate();
			result = (updateCount > 0);
			System.out.println("insert " + updateCount + "개 성공");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
}
