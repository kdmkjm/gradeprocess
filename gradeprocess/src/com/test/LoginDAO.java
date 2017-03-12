package com.test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class LoginDAO {
		
	
	// 로그인
	public String login(String id, String pw) {
		String result = null;

		String sql = "";
		
		if(id.contains("inst")){
			sql = "SELECT instructor_id, inst_name FROM instructor WHERE instructor_id = ? AND inst_pw= ?";
		} else if(id.contains("st")){
			sql = "SELECT student_id, st_name FROM students WHERE student_id = ? AND st_pw= ?";
		} else{
			sql = "SELECT admin_id, admin_pw FROM admin WHERE admin_id=? AND admin_pw=?";		
		}
		
		System.out.println(id);
		System.out.println(pw);
		
	

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DatabaseConnection.connect();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				
				if(id.contains("inst")){
					result = rs.getString("inst_name");
				} else if(id.contains("st")){
					result = rs.getString("st_name");
				} else{
					result = rs.getString("admin_id");
				}
				
				
			}
			rs.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				DatabaseConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return result;
	}
	
	// 비밀번호 수정 메소드
	public int pwModify(AllList all, String key) {
		int result = 0;

		String sql = "";
		
		switch (key) {
		case "admin":
			sql = "UPDATE admin SET admin_pw=? WHERE admin_id=?";
			break;
		case "inst":
			sql = "UPDATE instructor SET inst_pw=? WHERE instructor_id=?";
			break;
		case "student":
			sql = "UPDATE students SET st_pw=? WHERE student_id=?";
			break;
		}

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DatabaseConnection.connect();

			pstmt = conn.prepareStatement(sql);
			switch (key) {
			case "admin":
				pstmt.setInt(1, all.getAdmin_pw());
				pstmt.setString(2, all.getAdmin_id());
				break;
			case "inst":
				pstmt.setInt(1, all.getInst_pw());
				pstmt.setString(2, all.getInstructor_id());
				break;
			case "student":
				pstmt.setInt(1, all.getSt_pw());
				pstmt.setString(2, all.getStudent_id());
				break;
			}
			result = pstmt.executeUpdate();

		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			try {
				DatabaseConnection.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
	
	//관리자/강사/수강생 아이디 패스워드 확인
	public List<AllList> pwCheck(String key, String value) {
		ArrayList<AllList> result = new ArrayList<AllList>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "";
		
		try {

			conn = DatabaseConnection.connect();

			// 쿼리 준비 및 실행
			switch (key) {
			case "admin":
				sql = "SELECT admin_id, admin_pw FROM admin WHERE admin_id=?";
				break;
			case "instructor":
				sql = "SELECT instructor_id, inst_pw, inst_name FROM instructor WHERE instructor_id = ?";
				break;
			case "student":
				sql = "SELECT student_id, st_pw, st_name FROM students WHERE student_id = ?";
				break;

			}
			
			

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, value);
				
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				AllList all = new AllList();
				
				if (key.equals("admin")) {
					all.setAdmin_id(rs.getString("admin_id"));
					all.setAdmin_pw(rs.getInt("admin_pw"));
				} else if (key.equals("instructor")) {
					all.setInstructor_id(rs.getString("instructor_id"));
					all.setInst_pw(rs.getInt("inst_pw"));
					all.setInst_name(rs.getString("inst_name"));
				} else if (key.equals("student")) {
					all.setStudent_id(rs.getString("student_id"));
					all.setSt_pw(rs.getInt("st_pw"));
					all.setSt_name(rs.getString("st_name"));
				}

				result.add(all);
			}
			rs.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
			}
			try {
				DatabaseConnection.close();
			} catch (SQLException e) {
			}
		}

		return result;

	}
	
	

}
