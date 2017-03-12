package com.test;

import java.sql.*;
import java.util.*;

public class AdminInstructorDAO {

	// 강사 정보 검색 및 출력
	public List<AllList> list1(String key, String key2, String value) {
		List<AllList> result = new ArrayList<>();

		String sql = "";

		switch (key) {
		case "instAll":// 강사 계정 관리 - 전체 출력
			sql = "SELECT instructor_id, inst_name, inst_pw, phone, teachable_sub, (SELECT COUNT(DISTINCT course_id)"
				+ " FROM admin_sub_view WHERE instructor_id = inst.instructor_id) AS courseCount FROM admin_inst_view inst";
			break;
		/*case "instAll":// 강사 계정 관리 - 전체 출력(백업용)
			sql = "SELECT instructor_id, inst_name, inst_pw, phone, teachable_sub FROM admin_inst_view";
			break;*/
		case "subjectNameAll": // 강사 계정 관리 - 과목명 전체 출력
			sql = "SELECT subjectName_id, sub_name FROM subjectName";
			break;
		}
		
		switch (key2) {
		case "instAll":// 강사 계정 관리 - 강사ID기준 전체 출력
			sql += " ORDER BY instructor_id";
			break;
		case "sc_inst-id":// 강사 계정 관리 - 강사ID로 검색 출력
			sql += " WHERE INSTR(LOWER(instructor_id), LOWER(?)) > 0"
				+ " ORDER BY instructor_id";
			break;
		case "sc_inst-name":// 강사 계정 관리 - 강사ID로 검색 출력
			sql += " WHERE INSTR(LOWER(inst_name), LOWER(?)) > 0"
				+ " ORDER BY instructor_id";
			break;
		case "subjectNameAll":// 강사 계정 관리 - 과목명 전체 출력
			sql += " ORDER BY subjectName_id";
			break;
		case "all_search_instructorId":// 강사 계정 관리 - 강사ID기준 전체출력
			sql += " WHERE instructorId = ?";
			break;
		}

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);

			switch (key2) {
			case "all_search_instructorId":// 강사ID
			case "sc_inst-id":// 강사ID로 검색
			case "sc_inst-name":// 강사명으로 검색
				pstmt.setString(1, value);
				break;
			}
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				AllList inst = new AllList();
				if (key.equals("instAll")||key.equals("sc_inst-id")||key.equals("sc_inst-name")) {
					inst.setInstructor_id(rs.getString("instructor_id"));//강사id
					inst.setInst_name(rs.getString("inst_name"));//강사명
					inst.setInst_pw(rs.getInt("inst_pw"));//강사암호
					inst.setInst_phone(rs.getString("phone"));//강사전화번호
					inst.setSub_name(rs.getString("teachable_sub"));//강의 가능 과목
					inst.setCou_count(Integer.parseInt(rs.getString("courseCount")));//강의 횟수
				}
				if (key.equals("subjectNameAll")) {
					inst.setSubjectName_id(rs.getString("subjectName_id"));
					inst.setSub_name(rs.getString("sub_name"));
				}
				result.add(inst);
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
	
	public List<AllList> list2(String key, String key2, String value) {
		List<AllList> result = new ArrayList<>();

		String sql = "";
		// key
		switch (key) {
		case "instId_course":// 강사 계정 관리2 - 강사ID로 과정정보 출력
			sql = "SELECT sub_name ,sub_start_date ,sub_end_date, course_id, cou_name, cou_start_date, cou_end_date, text_name, class_name, progress"
					+ " FROM admin_sub_view";
			break;
		case "instId_courseId_sub":// 강사 계정 관리2 - 강사ID와 과정ID로 과목정보 출력
			sql = "SELECT subject_id, sub_name ,sub_start_date ,sub_end_date, text_name"
					+ " FROM admin_sub_view"
					+ " WHERE instructor_id = ? AND course_id = ?";
			break;
		case "sc_course-id":// 강사 계정 관리2 - 강사ID로 검색후 과정ID로 검색 출력
			sql = "SELECT sub_name ,sub_start_date ,sub_end_date, course_id, cou_name, cou_start_date, cou_end_date, text_name, class_name, progress"
					+ " FROM admin_sub_view WHERE INSTR(LOWER(course_id), LOWER(?)) > 0 AND instructor_id = ?";
			break;
		case "sc_course-name":// 강사 계정 관리2 - 강사ID로 검색후 과정ID로 검색 출력
			sql = "SELECT sub_name ,sub_start_date ,sub_end_date, course_id, cou_name, cou_start_date, cou_end_date, text_name, class_name, progress"
					+ " FROM admin_sub_view WHERE INSTR(LOWER(cou_name), LOWER(?)) > 0 AND instructor_id = ?";
			break;
		}
		// key2
		switch (key2) {
		case "instId_course":// 강사 계정 관리2 - 강사ID로 과정정보 출력
			sql += " WHERE instructor_id = ?";
			break;
		}

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);
			// key
			switch (key) {
			case "instId_course":// 강사 계정 관리2 - 강사ID로 과정정보 출력
				pstmt.setString(1, value);
				break;
			case "instId_courseId_sub":// 강사 계정 관리 2- 강사ID와 과정ID로 과목정보 출력
			case "sc_course-id":// 강사 계정 관리 2- 강사ID와 과정ID로 과목정보 출력
			case "sc_course-name":// 강사 계정 관리 2- 강사ID와 과정명로 과목정보 출력
				pstmt.setString(1, key2);
				pstmt.setString(2, value);
				break;
			}
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				AllList inst = new AllList();
				if (key.equals("instId_course")||key.equals("sc_course-id")||key.equals("sc_course-name")) {
					inst.setSub_name(rs.getString("sub_name"));//과목명
					inst.setCou_name(rs.getString("cou_name"));//과정명
					inst.setCourse_id(rs.getString("course_id"));//과정ID
					inst.setClass_name(rs.getString("class_name"));//강의실명
					inst.setCou_start_date(rs.getString("cou_start_date"));//과정시작
					inst.setCou_end_date(rs.getString("cou_end_date"));//과정종료
					inst.setProgress_ox(rs.getString("progress"));//강의진행여부
					inst.setSub_start_date(rs.getString("sub_start_date"));//과목 시작
					inst.setSub_end_date(rs.getString("sub_end_date"));//과목 종료
					inst.setText_name(rs.getString("text_name"));//교재명
				}
				if (key.equals("instId_courseId_sub")) {
					inst.setSubject_id(rs.getString("subject_id"));//과목ID
					inst.setSub_name(rs.getString("sub_name"));//과목명
				}
				result.add(inst);
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
	// Total (전체 출력 개수 반환) 메소드
	public int total(String key, String value) {
		int result = 0;
		String sql = "";
		// key
		switch (key) {
		case "instAll":  // 개설 과정 관리 - 과정 전체 출력
			sql = "SELECT COUNT(*) AS \"total\" FROM admin_inst_view";
			break;
		case "instId_course": // 개설 과정 관리2 - 과정ID로 과목정보 출력
			sql = "SELECT COUNT(*) AS \"total\" FROM admin_sub_view WHERE instructor_id = ?";
			break;
		}
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);
			// key
			switch (key) {
			case "instId_course":
				pstmt.setString(1,  value);
				break;
			}
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

	 
	// 강사 정보 추가
	public int add(AllList inst, List<String> subjects_available) {
		int result = 0;
		//id 자동증가
		String sql1 = "SELECT instructor_id FROM instructorSeqView";
		String sql2 = "INSERT INTO instructor (instructor_id, inst_name, inst_pw, phone) VALUES (?, ?, ?, ?)";
		String sql3 = "INSERT INTO teachable_sub (instructor_id, subjectName_id) VALUES (?, ?)";
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		try {
			conn = DatabaseConnection.connect();
			//트랙잭션 처리
			conn.setAutoCommit(false);
			//강사 신규 번호 얻는 과정
			String instructor_id = "inst_000";
			pstmt1 = conn.prepareStatement(sql1);
			ResultSet rs = pstmt1.executeQuery();
			while(rs.next()) {
				instructor_id = rs.getString("instructor_id");
			}
			rs.close();
			//강사 개인 정보 입력 과정
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, instructor_id);
			pstmt2.setString(2, inst.getInst_name());
			pstmt2.setInt(3, inst.getInst_pw());
			pstmt2.setString(4, inst.getInst_phone());
			pstmt2.executeUpdate();
			//강의 가능 과목 입력 과정
			pstmt3 = conn.prepareStatement(sql3);
			for (String subjectName_id : subjects_available) {
				pstmt3.setString(1, instructor_id);
				pstmt3.setString(2, subjectName_id);
				System.out.println(subjectName_id);
				pstmt3.executeUpdate();
			}
			//트랙잭션 처리
			conn.commit();
			result = 1;
		} catch (ClassNotFoundException | SQLException e) {
			//트랙잭션 처리
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.out.println("잘못 1");
			}
			e.printStackTrace();
		} finally {
			try {
				if (pstmt3 != null)
					pstmt3.close();
				if (pstmt2 != null)
					pstmt2.close();
				if (pstmt1 != null)
					pstmt1.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("잘못 2");
			}
			try {
				DatabaseConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("잘못 3");
			}
		}
		return result;
	}
	// 강사 정보 삭제
	public int remove(String key, String value) {
		int result = 0;
		String sql = "DELETE FROM";

		switch (key) {
		case "instructor_id":// 강사ID
			sql += " instructor WHERE instructor_id = ?";
			break;
		}
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DatabaseConnection.connect();
			//트랙잭션 처리
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			switch (key) {
			case "instructor_id":// 강사ID
				pstmt.setString(1, value);
				break;
			}
			result = pstmt.executeUpdate();
			//트랙잭션 처리
			conn.commit();
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("정보가 사용되고 있어 삭제가 불가능 합니다.");
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
	
	// 강사 정보 수정
	public int modify(AllList inst, List<String> subjects_available) {
		int result = 0;
		String sql = "DELETE FROM teachable_sub WHERE instructor_id = ?";// 기존 강의가능과목 삭제
		String sql2 = "UPDATE instructor SET inst_name=?, phone=? WHERE instructor_id=?";// 강사정보 수정
		String sql3 = "INSERT INTO teachable_sub (instructor_id, subjectName_id) VALUES (?, ?)";// 강의가능과목 추가
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		try {
			conn = DatabaseConnection.connect();
			//트랙잭션 처리
			conn.setAutoCommit(false);
			String instructor_id = inst.getInstructor_id();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, instructor_id);
			result = pstmt.executeUpdate();
			
			//강사 개인 정보 수정 과정
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, inst.getInst_name());
			pstmt2.setString(2, inst.getInst_phone());
			pstmt2.setString(3, instructor_id);
			pstmt2.executeUpdate();
			
			//강의 가능 과목 입력 과정
			pstmt3 = conn.prepareStatement(sql3);
			for (String subjectName_id : subjects_available) {
				pstmt3.setString(1, instructor_id);
				pstmt3.setString(2, subjectName_id);
				System.out.println(subjectName_id);
				pstmt3.executeUpdate();
			}
			//트랙잭션 처리
			conn.commit();
			result = 1;
		} catch (ClassNotFoundException | SQLException e) {
			//트랙잭션 처리
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.out.println("잘못 1");
			}
			e.printStackTrace();
		} finally {
			try {
				if (pstmt3 != null)
					pstmt3.close();
				if (pstmt2 != null)
					pstmt2.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("잘못 2");
			}
			try {
				DatabaseConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("잘못 3");
			}
		}
		return result;
	}
		
	/*
	// 강사 정보 추가
	public int modifyInst(AllList inst, List<String> subjects_available) {
		int result = 0;
		//id 자동증가
		String sql2 = "UPDATE instructor SET inst_name=?, phone=? WHERE instructor_id=?";
		Connection conn = null;
		PreparedStatement pstmt2 = null;
		try {
			conn = DatabaseConnection.connect();
			//트랙잭션 처리
			conn.setAutoCommit(false);
			
			String instructor_id = inst.getInstructor_id();
			
			//강사 개인 정보 수정 과정
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, inst.getInst_name());
			pstmt2.setString(2, inst.getInst_phone());
			pstmt2.setString(3, instructor_id);
			pstmt2.executeUpdate();
			
			//트랙잭션 처리
			conn.commit();
			result = 1;
		} catch (ClassNotFoundException | SQLException e) {
			//트랙잭션 처리
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.out.println("잘못 1");
			}
			e.printStackTrace();
		} finally {
			try {
				if (pstmt2 != null)
					pstmt2.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("잘못 2");
			}
			try {
				DatabaseConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("잘못 3");
			}
		}
		return result;
	}
	
	// 강사 정보 삭제
	public int modifyRemoveSub(String value) {
		int result = 0;
		String sql = "DELETE FROM teachable_sub WHERE instructor_id = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DatabaseConnection.connect();
			//트랙잭션 처리
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, value);
			result = pstmt.executeUpdate();
			//트랙잭션 처리
			conn.commit();
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("정보가 사용되고 있어 삭제가 불가능 합니다.");
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
	
	// 강사 정보 추가
	public int modifySubAdd(AllList inst, List<String> subjects_available) {
		int result = 0;
		//id 자동증가
		String sql3 = "INSERT INTO teachable_sub (instructor_id, subjectName_id) VALUES (?, ?)";
		Connection conn = null;
		PreparedStatement pstmt3 = null;
		try {
			conn = DatabaseConnection.connect();
			//트랙잭션 처리
			conn.setAutoCommit(false);
			//강의 가능 과목 입력 과정
			String instructor_id = inst.getInstructor_id();
			pstmt3 = conn.prepareStatement(sql3);
			for (String subjectName_id : subjects_available) {
				pstmt3.setString(1, instructor_id);
				pstmt3.setString(2, subjectName_id);
				System.out.println(subjectName_id);
				pstmt3.executeUpdate();
			}
			//트랙잭션 처리
			conn.commit();
			result = 1;
		} catch (ClassNotFoundException | SQLException e) {
			//트랙잭션 처리
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.out.println("잘못 1");
			}
			e.printStackTrace();
		} finally {
			try {
				if (pstmt3 != null)
					pstmt3.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("잘못 2");
			}
			try {
				DatabaseConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("잘못 3");
			}
		}
		return result;
	}
	 */
	
}
