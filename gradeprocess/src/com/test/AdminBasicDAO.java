package com.test;

import java.sql.*;
import java.util.*;

public class AdminBasicDAO {

	// 관리자 > 1.기초정보관리 > 1. 과정명 정보 조회
	public List<AllList> courseNameList(String key, String value) {
		List<AllList> result = new ArrayList<>();

		String sql = "SELECT coursename_id, cou_name, delCheck FROM courseNameView";

		switch (key) {
		case "all": // 전체(중복제거)
			break;
		case "coursename_id": // 선택한 과정명ID로 검색 출력
		case "cou_name": // 선택한 과정명으로 검색 출력
			sql += String.format(" WHERE INSTR(LOWER(%s), LOWER(?)) > 0", key);
			break;
		}
		sql += " ORDER BY coursename_id";

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);

			switch (key) {
			case "all":
				break;
			case "coursename_id":// 강의실ID
			case "cou_name":
				pstmt.setString(1, value);
				break;
			}
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				AllList course = new AllList();
				course.setCourseName_id(rs.getString("coursename_id"));
				course.setCou_name(rs.getString("cou_name"));
				course.setDelCheck(rs.getInt("delCheck"));
				result.add(course);
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

	
	// 관리자 > 1.기초정보관리 > 1. 과정명 정보 추가
	public int courseNameAdd(AllList courseName) {
		int result = 0;

		// id 자동증가 뷰 쿼리로 대체요망
		String sql1 = "SELECT courseName_id FROM courseNameSeqView";
		String sql2 = "INSERT INTO courseName(courseName_id , cou_name) VALUES (?, ?)";
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		
		try {
			conn = DatabaseConnection.connect();
			// 트랙잭션 처리
			conn.setAutoCommit(false);
			
			// 과정명 신규 번호 얻는 과정
			String courseName_id = "cou_name_000";
			pstmt1 = conn.prepareStatement(sql1);
			ResultSet rs = pstmt1.executeQuery();
			
			while (rs.next()) {
				courseName_id = rs.getString("courseName_id");
			}
			rs.close();
			
			// 과정 정보 입력 과정
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, courseName_id);
			pstmt2.setString(2, courseName.getCou_name());
			pstmt2.executeUpdate();
			// 트랙잭션 처리
			conn.commit();
			result = 1;
		} catch (ClassNotFoundException | SQLException e) {
			// 트랙잭션 처리
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {

			try {
				if (pstmt2 != null)
					pstmt2.close();
				if (pstmt1 != null)
					pstmt1.close();
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
	
	
	// 관리자 > 1.기초정보관리 > 1. 과정명 정보 수정
	public int courseNameModify(AllList a) {
		int result = 0;
		
		String sql = "UPDATE courseName SET cou_name = ? WHERE coursename_id = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			
			conn = DatabaseConnection.connect();
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, a.getCou_name());
			pstmt.setString(2, a.getCourseName_id());
			
			result = pstmt.executeUpdate();
			conn.commit();
			
		} catch(ClassNotFoundException | SQLException e) {
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

	
	// 관리자 > 1.기초정보관리 > 1. 과정명 정보 삭제
	public int courseNameDelete(AllList a) {
		int result = 0;
		String sql = "DELETE FROM courseName WHERE coursename_id = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, a.getCourseName_id());
			
			result = pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("> 정보가 사용되고 있어 삭제가 불가능 합니다.");
		} catch (SQLException e) {
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

	
	// 관리자 > 1.기초정보관리 > 1. 과정명 정보 > Total (전체 출력 개수 반환) 메소드
	public int courseNameTotal() {
		int result = 0;
		String sql = "SELECT COUNT(*) AS \"total\" FROM courseName";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				result = rs.getInt("total");
			}
			rs.close();
		} catch (ClassNotFoundException | SQLException e) {

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

//////////////////////////////////////////////////////////////////////////////////////////
	
	// 관리자 > 1. 기초정보관리 > 2. 과목명 정보 조회
	public List<AllList> subjectNameList(String key, String value) {
		List<AllList> result = new ArrayList<>();

		String sql = "SELECT subjectName_id, sub_name, delCheck FROM subjectNameView";

		switch (key) {

		// 관리자 로그인> 2. 강사 계정 관리> 1. 입력> 과목 출력
		case "all":
			sql += " ORDER BY subjectName_id";
			break;
		case "subjectName_id": // 관리자 로그인 > 1. 기본정보관리 >//과목명id
		case "sub_name":
			sql += String.format(" WHERE INSTR(LOWER(%s), LOWER(?)) > 0", key);
			break;
		}

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);

			switch (key) {
			case "all":
				break;

			case "subjectName_id": // 과목명id
			case "sub_name":
				pstmt.setString(1, value);
				break;
			}
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				AllList a = new AllList();
				a.setSubjectName_id(rs.getString("subjectName_id"));
				a.setSub_name(rs.getString("sub_name"));
				a.setDelCheck(rs.getInt("delCheck"));

				result.add(a);
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

	// 관리자 > 1. 기초정보관리 > 2. 과목명 정보 추가
	public int subjectNameAdd(AllList a) {
		int result = 0;

		// id 자동증가 뷰 쿼리로 대체요망
		String sql1 = "SELECT subjectName_id FROM subjectNameSeqView";
		String sql2 = "INSERT INTO subjectName(subjectName_id , sub_name) VALUES (?, ?)";
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;

		try {
			conn = DatabaseConnection.connect();

			// 트랙잭션 처리
			conn.setAutoCommit(false);

			// 과목 신규 번호 얻는 과정
			String subjectName_id = "sub_name_000";
			pstmt1 = conn.prepareStatement(sql1);
			ResultSet rs = pstmt1.executeQuery();
			while (rs.next()) {
				subjectName_id = rs.getString("subjectName_id");
			}
			rs.close();

			// 과목 정보 입력 과정
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, subjectName_id);
			pstmt2.setString(2, a.getSub_name());

			pstmt2.executeUpdate();

			// 트랙잭션 처리
			conn.commit();
			result = 1;

		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("> 정보가 사용되고 있어 삭제가 불가능 합니다.");

		} catch (ClassNotFoundException | SQLException e) {
			// 트랙잭션 처리
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {

			try {
				if (pstmt2 != null)
					pstmt2.close();
				if (pstmt1 != null)
					pstmt1.close();
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

	
	// 관리자 > 1. 기초 정보 관리 > 2. 과목명 정보 수정
	public int subjectNameModify(AllList a) {
		int result = 0;
		
		String sql = "UPDATE subjectName SET sub_name = ? WHERE subjectName_id = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, a.getSub_name());
			pstmt.setString(2, a.getSubjectName_id());
			
			result = pstmt.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			// 트랙잭션 처리
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
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
	
	
	
	// 관리자 > 1. 기초정보관리 > 2. 과목명 정보 삭제
	public int subjectNameDelete(AllList a) {
		int result = 0;
		String sql = "DELETE FROM subjectName WHERE subjectName_id = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, a.getSubjectName_id());
		
			result = pstmt.executeUpdate();

		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("> 정보가 사용되고 있어 삭제가 불가능 합니다.");
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

	// 관리자 > 1. 기초정보관리 > 2. 과목명 정보 > Total (전체 출력 개수 반환) 메소드
	public int subjectNameTotal() {
		int result = 0;
		String sql = "SELECT COUNT(*) AS \"total\" FROM subjectName";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				result = rs.getInt("total");
			}
			rs.close();

		} catch (ClassNotFoundException | SQLException e) {

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
	

//////////////////////////////////////////////////////////////////////////////////////////

	// 관리자 > 1. 기초정보관리 > 3. 강의실 정보 조회
	public List<AllList> classroomList(String key, String value) {
		List<AllList> result = new ArrayList<>();

		String sql = "SELECT classroom_id, class_name, total, delCheck FROM classroomView";

		switch (key) {

		case "all": // 전체
			break;

		case "classroom_id": // 부분 출력
		case "class_name":
			sql += String.format(" WHERE INSTR(LOWER(%s), LOWER(?)) > 0", key);
			break;
		}

		sql += " ORDER BY classroom_id";

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);

			switch (key) {

			case "all": // 전체
				break;
			case "classroom_id": // 부분 출력
			case "class_name":
				pstmt.setString(1, value);
				break;
			}

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				AllList a = new AllList();
				a.setClassroom_id(rs.getString("classroom_id"));
				a.setClass_name(rs.getString("class_name"));
				a.setClass_total(rs.getInt("total"));
				a.setDelCheck(rs.getInt("delCheck"));
				
				result.add(a);
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

	
	// 관리자 > 1. 기초정보관리 > 3. 강의실 정보 입력
	public int classroomAdd(AllList a) {
		int result = 0;

		// classRoom_id는 자동증가, className, total
		String sql1 = "SELECT classroom_id FROM classroomSeqView"; // classRoom_id 자동증가 SELECT 쿼리
		String sql2 = "INSERT INTO classroom (classroom_id, class_name, total) VALUES (?, ?, ?)"; // INSERT
																									// 쿼리

		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;

		try {
			conn = DatabaseConnection.connect();

			// SELECT 과정
			String classRoom_id = null;
			pstmt1 = conn.prepareStatement(sql1);
			ResultSet rs = pstmt1.executeQuery();

			while (rs.next()) {
				classRoom_id = rs.getString("classroom_id");
			}
			rs.close();

			// INSERT 과정
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, classRoom_id);
			pstmt2.setString(2, a.getClass_name());
			pstmt2.setInt(3, a.getClass_total());

			result = pstmt2.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt2 != null)
					pstmt2.close();
				if (pstmt1 != null)
					pstmt1.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("pstmt 잘못");
			}
			try {
				DatabaseConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("DatabaseConnection.close() 잘못");
			}
		}

		return result;
	}
	
	// 관리자 > 1. 기초정보관리 > 3. 강의실 정보 수정
	public int classroomModify(AllList a) {
		int result = 0;
		
		String sql = "UPDATE classroom SET class_name = ?, total = ? WHERE classroom_id = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, a.getClass_name());
			pstmt.setInt(2, a.getClass_total());
			pstmt.setString(3, a.getClassroom_id());
			
			result = pstmt.executeUpdate();
			
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
	

	// 관리자 > 1. 기초정보관리 > 3. 강의실 정보 삭제
	public int classroomDelete(AllList a) {

		int result = 0;

		String sql = "DELETE FROM classroom WHERE classroom_id = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, a.getClassroom_id());

			result = pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("> 정보가 사용되고 있어 삭제가 불가능 합니다.");
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

	
	// 관리자 > 1. 기초정보관리 > 3. 강의실 정보 > Total (전체 출력 개수 반환) 메소드
	public int classroomTotal() {
		int result = 0;
		String sql = "SELECT COUNT(*) AS \"total\" FROM classroom";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				result = rs.getInt("total");
			}
			rs.close();

		} catch (ClassNotFoundException | SQLException e) {

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

	
//////////////////////////////////////////////////////////////////////////////////////////

	// 관리자 > 1. 기초정보관리 > 4. 교재 정보 출력
	public List<AllList> textbookList(String key, String value) {

		List<AllList> result = new ArrayList<AllList>();

		String sql = "SELECT textbook_id, text_name, publisher, text_img, delCheck FROM textbookView";

		switch (key) {
		case "all":
			break;
		case "textbook_id":
		case "text_name":
			sql += String.format(" WHERE INSTR(LOWER(%s), LOWER(?)) > 0", key);
			break;
		case "textbook_name":
			sql += " WHERE text_name = ?";
			break;
		}
		sql += " ORDER BY textbook_id";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);

			switch (key) {
			case "all":
				break;
			case "textbook_id":
			case "text_name":
			case "textbook_name":
				pstmt.setString(1, value);
				break;
			}

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				AllList a = new AllList();
				a.setTextbook_id(rs.getString("textbook_id"));
				a.setText_name(rs.getString("text_name"));
				a.setPublisher(rs.getString("publisher"));
				a.setText_img(rs.getString("text_img"));
				a.setDelCheck(rs.getInt("delCheck"));
				
				result.add(a);
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

	
	// 관리자 > 1. 기초정보관리 > 4. 교재 정보 추가
	public int textbookAdd(AllList a) {

		int result = 0;

		String sql1 = "SELECT textbook_id FROM textbookSeqView";
		String sql2 = "INSERT INTO textbook (textbook_id, text_name, publisher, text_img) VALUES (?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;

		try {
			conn = DatabaseConnection.connect();
			conn.setAutoCommit(false);
			
			// SELECT 과정
			String textbook_id = null;
			pstmt1 = conn.prepareStatement(sql1);
			ResultSet rs = pstmt1.executeQuery();

			while (rs.next()) {
				textbook_id = rs.getString("textbook_id");
			}
			rs.close();

			// INSERT 과정
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, textbook_id);
			pstmt2.setString(2, a.getText_name());
			pstmt2.setString(3, a.getPublisher());
			pstmt2.setString(4, a.getText_img());
			
			result = pstmt2.executeUpdate();
			
			conn.commit();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if (pstmt2 != null)
					pstmt2.close();
				if (pstmt1 != null)
					pstmt1.close();
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

	
	// 관리자 > 1. 기초 정보 관리 > 4. 교재 정보 수정
	public int textbookModify(AllList a) {
		int result = 0;
		
		String sql = "UPDATE textbook SET text_name = ?, publisher = ?, text_img = ? WHERE textbook_id = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, a.getText_name());
			pstmt.setString(2, a.getPublisher());
			pstmt.setString(3, a.getText_img());
			pstmt.setString(4, a.getTextbook_id());
			
			result = pstmt.executeUpdate();
			
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
	
	
	// 관리자 > 1. 기초정보관리 > 4. 교재 정보 삭제
	public int textbookDelete(AllList a) {

		int result = 0;

		String sql = "DELETE FROM textbook WHERE textbook_id = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, a.getTextbook_id());

			result = pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} /*catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("> 정보가 사용되고 있어 삭제가 불가능 합니다.");
		}*/ catch (SQLException e) {
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

	// 관리자 > 1. 기초정보관리 > 4. 교재 정보 > Total (전체 출력 개수 반환) 메소드
	public int textbookTotal() {
		int result = 0;
		String sql = "SELECT COUNT(*) AS \"total\" FROM textbook";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				result = rs.getInt("total");
			}
			rs.close();

		} catch (ClassNotFoundException | SQLException e) {

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
}
