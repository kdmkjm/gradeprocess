package com.test;

import java.sql.*;
import java.util.*;

public class AdminCourseDAO {
	
	// 과정 정보 출력 및 조회
	public List<AllList> list(String key, String key2, String value) {
		
		List<AllList> result = new ArrayList<>();
		String sql = "";
		// key
		switch (key) {
		case "courseAll": // 개설 과정 관리 - 과정 전체 출력
			sql = "SELECT rn, course_id, start_date, end_date, class_name, cou_name, sub_count, class_total FROM admin_cou_view";
			break;
		case "courseNameAll": // 개설 과정 관리 - 과정명 전체 출력
			sql = "SELECT coursename_id, cou_name FROM coursename";
			break;
		case "classroomAll": // 개설 과정 관리 - 강의실 전체 출력
			sql = "SELECT classroom_id, class_name, total FROM classroom";
			break;
		case "courseId_sub":  // 개설 과정 관리 - 과정ID로 과목정보 출력
			sql = "SELECT subject_id, sub_name, sub_start_date, sub_end_date, text_name, inst_name"
					+ " FROM admin_sub_view4";
			break;
		case "courseId_student" : // 개설 과정 관리 - 과정ID로 수강생정보 출력
			sql = "SELECT st_name, st_pw,  phone, register_date, fin FROM admin_st_view";
			break;
		}
		
		// key2
		switch (key2) {
		case "courseAll": // 개설 과정 관리 - 과정 전체 출력
			sql += " ORDER BY rn";
			break;
		case "sc_course-id": // 개설 과정 관리 - 과정id검색 출력
			sql += " WHERE INSTR(LOWER(course_id), LOWER(?)) > 0 ORDER BY start_date, end_date";
			break;
		case "sc_course-name": // 개설 과정 관리 - 과정명검색 출력
			sql += " WHERE INSTR(LOWER(cou_name), LOWER(?)) > 0 ORDER BY start_date, end_date";
			break;
		case "courseNameAll": // 개설 과정 관리 - 과정명 전체 출력
			sql += " ORDER BY coursename_id";
			break;
		case "classroomAll": // 개설 과정 관리 - 강의실 전체 출력
			sql += " ORDER BY classroom_id";
			break;
		case "courseId_sub": // 개설 과정 관리 - 과정ID로 과목정보 출력
			sql += " WHERE course_id = ?"
					+ " ORDER BY subject_id";
			break;
		case "courseId_student": // 개설 과정 관리 - 과정ID로 수강생정보 출력
			sql += " WHERE course_id = ?";
			break;
		}
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);
			// key2
			switch (key2) {
			case "sc_course-id":
			case "sc_course-name":
			case "courseId_sub":
			case "courseId_student":
				pstmt.setString(1,  value); 
				break;
			}
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				AllList course = new AllList();
				if (key.equals("courseAll")) {
					course.setRn(Integer.parseInt(rs.getString("rn")));
					course.setCourse_id(rs.getString("course_id"));
					course.setCou_name(rs.getString("cou_name"));
					course.setCou_start_date(rs.getString("start_date"));
					course.setCou_end_date(rs.getString("end_date"));
					course.setClass_total(rs.getInt("sub_count"));
					course.setSt_total(rs.getInt("class_total"));
					course.setClass_name(rs.getString("class_name"));
				}
				if (key.equals("courseNameAll")) {
					course.setCourseName_id(rs.getString("coursename_id"));
					course.setCou_name(rs.getString("cou_name"));
				}
				if (key.equals("classroomAll")) {
					course.setClassroom_id(rs.getString("classroom_id"));
					course.setClass_name(rs.getString("class_name"));
					course.setClass_total(rs.getInt("total"));
				}
				if (key.equals("courseId_sub")){
					course.setSubject_id(rs.getString("subject_id"));
					course.setSub_name(rs.getString("sub_name"));
					course.setSub_start_date(rs.getString("sub_start_date"));
					course.setSub_end_date(rs.getString("sub_end_date"));
					course.setInst_name(rs.getString("inst_name"));
					course.setText_name(rs.getString("text_name"));
				}
				if (key.equals("courseId_student")) {
					course.setSt_name(rs.getString("st_name"));
					course.setSt_pw(rs.getInt("st_pw"));
					course.setSt_phone(rs.getString("phone"));
					course.setRegister_date(rs.getString("register_date"));
					course.setFin(rs.getString("fin"));
				}
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
	
	// 과정 정보 출력 및 조회
		public List<AllList> list2(String key, String key2, String value) {
			
			List<AllList> result = new ArrayList<>();
			String sql = "";
			// key
			switch (key) {
			case "course_id_sub":  // 개설 과정 관리2 - 과정ID로 과목정보 출력
				sql = "SELECT subject_id, sub_name, sub_start_date, sub_end_date, text_name, publisher, inst_name, text_img"
						+ " FROM admin_sub_view4";
				break;
			case "subjectNameAll": // 개설 과정 관리2 - 과목명 전체 출력
				sql = "SELECT subjectName_id, sub_name FROM subjectName";
				break;
			case "instAll":// 강사 계정 관리2 - 전체 출력
				sql = "SELECT instructor_id, inst_name, inst_pw, phone, teachable_sub FROM admin_inst_view";
				break;
			case "textbookAll":// 강사 계정 관리2 - 전체 출력
				sql = "SELECT textbook_id, text_name, publisher FROM textbook";
				break;
			}
			
			// key2
			switch (key2) {
			case "course_id_sub": // 개설 과정 관리2 - 과정ID로 과목정보 출력
				sql += " WHERE course_id = ?"
						+ " ORDER BY sub_start_date, sub_end_date";
				break;
			case "sc_sub-id": // 개설 과정 관리2 - 과정ID로 과목정보를 과목ID로 검색 출력
				sql = "SELECT subject_id, sub_name, sub_start_date, sub_end_date, text_name, publisher, inst_name, text_img"
						+ " FROM admin_sub_view4"
						+ " WHERE course_id = ? AND INSTR(LOWER(subject_id), LOWER(?)) > 0"
						+ " ORDER BY sub_start_date, sub_end_date";
				break;
			case "sc_sub-name": // 개설 과정 관리2 - 과정ID로 과목정보를 과목명로 검색 출력
				sql = "SELECT subject_id, sub_name, sub_start_date, sub_end_date, text_name, publisher, inst_name, text_img"
						+ " FROM admin_sub_view4"
						+ " WHERE course_id = ? AND INSTR(LOWER(sub_name), LOWER(?)) > 0"
						+ " ORDER BY sub_start_date, sub_end_date";
				break;
			case "subjectNameAll": // 개설 과정 관리 - 과목명 전체 출력
				sql += " ORDER BY subjectName_id";
				break;
			case "instAll":// 개설 과정 관리 - 강사ID기준 전체출력
				sql += " ORDER BY instructor_id";
				break;
			case "textbookAll":// 개설 과정 관리 - 강사ID기준 전체출력
				sql += " ORDER BY textbook_id";
				break;
			}
			Connection conn = null;
			PreparedStatement pstmt = null;
			try {
				conn = DatabaseConnection.connect();
				pstmt = conn.prepareStatement(sql);
				// key2
				switch (key2) {
				case "course_id_sub":
					pstmt.setString(1,  value);
					break;
				case "sc_sub-id":
					pstmt.setString(1,  value); //과정ID
					pstmt.setString(2,  key); //과목ID
					break;
				case "sc_sub-name":
					pstmt.setString(1,  value); //과정ID
					pstmt.setString(2,  key); //과목명
					break;
				}
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					AllList course = new AllList();
					if (key.equals("course_id_sub")||key2.equals("sc_sub-id")||key2.equals("sc_sub-name")){
						course.setSubject_id(rs.getString("subject_id"));
						course.setSub_name(rs.getString("sub_name"));
						course.setSub_start_date(rs.getString("sub_start_date"));
						course.setSub_end_date(rs.getString("sub_end_date"));
						course.setInst_name(rs.getString("inst_name"));
						course.setText_name(rs.getString("text_name"));
						course.setPublisher(rs.getString("publisher"));
						course.setText_img(rs.getString("text_img"));
					}
					if (key.equals("subjectNameAll")) {
						course.setSubjectName_id(rs.getString("subjectName_id"));
						course.setSub_name(rs.getString("sub_name"));
					}
					if (key.equals("instAll")) {
						course.setInstructor_id(rs.getString("instructor_id"));//강사id
						course.setInst_name(rs.getString("inst_name"));//강사명
						course.setInst_pw(rs.getInt("inst_pw"));//강사암호
						course.setInst_phone(rs.getString("phone"));//강사전화번호
						course.setSub_name(rs.getString("teachable_sub"));//강의 가능 과목
					}
					if (key.equals("textbookAll")) {
						course.setTextbook_id(rs.getString("textbook_id"));
						course.setText_name(rs.getString("text_name"));
						course.setPublisher(rs.getString("publisher"));
					}
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
		
		// Total (전체 출력 개수 반환) 메소드
		public int total(String key, String value) {
			int result = 0;
			String sql = "";
			// key
			switch (key) {
			case "courseAll_total":  // 개설 과정 관리 - 과정 전체 출력
				sql = "SELECT COUNT(*) AS \"total\" FROM admin_cou_view";
				break;
			case "course_id_sub_total": // 개설 과정 관리2 - 과정ID로 과목정보 출력
				sql = "SELECT COUNT(*) AS \"total\" FROM admin_sub_view4 WHERE course_id = ?";
				break;
			
			}
			Connection conn = null;
			PreparedStatement pstmt = null;
			try {
				conn = DatabaseConnection.connect();
				pstmt = conn.prepareStatement(sql);
				// key
				switch (key) {
				case "course_id_sub_total":
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
		
		// 관리자 > 개설 과정 관리 > 개설 과정 추가
		public int courseAdd(AllList a) {
			int result = 0;
			
			String sql1 = "SELECT course_id FROM courseSeqView";
			String sql2 = "INSERT INTO course (course_id, courseName_id, start_date, end_date, classroom_id) VALUES (?, ?, ?, ?, ?)";
			
			Connection conn = null;
			PreparedStatement pstmt1 = null;
			PreparedStatement pstmt2 = null;
			
			try {
				
				conn = DatabaseConnection.connect();
				conn.setAutoCommit(false);
				
				String course_id = "";
				pstmt1 = conn.prepareStatement(sql1);
				ResultSet rs = pstmt1.executeQuery();
				
				while(rs.next()) {
					course_id = rs.getString("course_id");
				}
				
				pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setString(1, course_id);
				pstmt2.setString(2, a.getCourseName_id());
				pstmt2.setString(3, a.getCou_start_date());
				pstmt2.setString(4, a.getCou_end_date());
				pstmt2.setString(5, a.getClassroom_id());
				
				result = pstmt2.executeUpdate();
				conn.commit();
				
			} catch (ClassNotFoundException | SQLException e) {

			} finally {
				try {
					if (pstmt2 != null)
						pstmt2.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}try {
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
		
		
	// 관리자 > 개설 과정 관리 > 개설 과정 수정
	public int courseModify(AllList a) {
		int result = 0;

		String sql = "UPDATE course SET courseName_id = ?, start_date = ?, end_date = ?, classroom_id = ? WHERE course_id = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = DatabaseConnection.connect();
			conn.setAutoCommit(false);

			pstmt = conn.prepareStatement(sql);

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, a.getCourseName_id());
			pstmt.setString(2, a.getCou_start_date());
			pstmt.setString(3, a.getCou_end_date());
			pstmt.setString(4, a.getClassroom_id());
			pstmt.setString(5, a.getCourse_id());

			result = pstmt.executeUpdate();
			conn.commit();

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

	// 관리자 > 개설 과정 관리 > 개설 과정 삭제
	public int courseRemove(AllList a) {
		int result = 0;

		String sql = "DELETE FROM course WHERE course_id = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, a.getCourse_id());

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

	// 관리자 > 개설 과정 관리 > 조회 > 과목 추가 > 개설 과목 추가
	public int subjectAdd(AllList a) {
		int result = 0;

		String sql1 = "SELECT subject_id FROM subjectSeqView";
		String sql2 = "INSERT INTO subject (course_id, subject_id, subjectName_id, start_date, end_date, textbook_id, instructor_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;

		try {

			conn = DatabaseConnection.connect();
			pstmt1 = conn.prepareStatement(sql1);
			String subject_id = "";
			ResultSet rs = pstmt1.executeQuery();
			
			while (rs.next()) {
				subject_id = rs.getString("subject_id");
			}
			
			conn.setAutoCommit(false);
			
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, a.getCourse_id());
			pstmt2.setString(2, subject_id);
			pstmt2.setString(3, a.getSubjectName_id());
			pstmt2.setString(4, a.getSub_start_date());
			pstmt2.setString(5, a.getSub_end_date());
			pstmt2.setString(6, a.getTextbook_id());
			pstmt2.setString(7, a.getInstructor_id());

			result = pstmt2.executeUpdate();
			conn.commit();

		} catch (ClassNotFoundException | SQLException e) {

		} finally {
			try {
				if (pstmt2 != null)
					pstmt2.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
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

	
	// 관리자 > 개설 과정 관리 > 조회 > 과목 추가 > 개설 과목 수정
	public int subjectModify(AllList a) {
		int result = 0;

		String sql = "UPDATE subject SET subjectName_id = ?, start_date = ?, end_date = ?, textbook_id = ?, instructor_id = ? WHERE course_id = ? AND subject_id = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = DatabaseConnection.connect();
			conn.setAutoCommit(false);

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, a.getSubjectName_id());
			pstmt.setString(2, a.getSub_start_date());
			pstmt.setString(3, a.getSub_end_date());
			pstmt.setString(4, a.getTextbook_id());
			pstmt.setString(5, a.getInstructor_id());
			pstmt.setString(6, a.getCourse_id());
			pstmt.setString(7, a.getSubject_id());
			pstmt.executeUpdate();
			
			conn.commit();
			result = 1;

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
	
	
	// 관리자 > 개설 과정 관리 > 조회 > 과목 추가 > 개설 과목 수정
	public int subjectRemove(AllList a) {
		int result = 0;

		String sql = "DELETE FROM subject WHERE course_id = ? AND subject_id = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = DatabaseConnection.connect();
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, a.getCourse_id());
			pstmt.setString(2, a.getSubject_id());
			pstmt.executeUpdate();
			
			conn.commit();
			result = 1;
			
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
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// 과정 정보 출력 및 조회 (페이지기능 추가)
	public List<AllList> list(String key, String key2, String value, int start, int end) {
		
		List<AllList> result = new ArrayList<>();
		String sql = "";
		// key
		switch (key) {
		case "courseAll": // 개설 과정 관리 - 과정 전체 출력
			sql = "SELECT rn, course_id, start_date, end_date, class_name, cou_name, sub_count, class_total FROM admin_cou_view";
			break;
		case "courseNameAll": // 개설 과정 관리 - 과정명 전체 출력
			sql = "SELECT coursename_id, cou_name FROM coursename";
			break;
		case "classroomAll": // 개설 과정 관리 - 강의실 전체 출력
			sql = "SELECT classroom_id, class_name, total FROM classroom";
			break;
		case "courseId_sub":  // 개설 과정 관리 - 과정ID로 과목정보 출력
			sql = "SELECT subject_id, sub_name, sub_start_date, sub_end_date, text_name, inst_name"
					+ " FROM admin_sub_view4";
			break;
		case "courseId_student" : // 개설 과정 관리 - 과정ID로 수강생정보 출력
			sql = "SELECT st_name, st_pw,  phone, register_date, fin FROM admin_st_view";
			break;
		}
		
		// key2
		switch (key2) {
		case "courseAll": // 개설 과정 관리 - 과정 전체 출력
			sql += " WHERE rn>=? AND rn<=?"
				+ " ORDER BY rn";
			break;
		case "sc_course-id": // 개설 과정 관리 - 과정id검색 출력
			sql += " WHERE INSTR(LOWER(course_id), LOWER(?)) > 0 ORDER BY start_date, end_date";
			break;
		case "sc_course-name": // 개설 과정 관리 - 과정명검색 출력
			sql += " WHERE INSTR(LOWER(cou_name), LOWER(?)) > 0 ORDER BY start_date, end_date";
			break;
		case "courseNameAll": // 개설 과정 관리 - 과정명 전체 출력
			sql += " ORDER BY coursename_id";
			break;
		case "classroomAll": // 개설 과정 관리 - 강의실 전체 출력
			sql += " ORDER BY classroom_id";
			break;
		case "courseId_sub": // 개설 과정 관리 - 과정ID로 과목정보 출력
			sql += " WHERE course_id = ?"
					+ " ORDER BY subject_id";
			break;
		case "courseId_student": // 개설 과정 관리 - 과정ID로 수강생정보 출력
			sql += " WHERE course_id = ?";
			break;
		}
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);
			// key2
			switch (key2) {
			case "sc_course-id":
			case "sc_course-name":
			case "courseId_sub":
			case "courseId_student":
				pstmt.setString(1, value); 
				break;
			case "courseAll":
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
				break;
			}
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				AllList course = new AllList();
				if (key.equals("courseAll")) {
					course.setRn(Integer.parseInt(rs.getString("rn")));
					course.setCourse_id(rs.getString("course_id"));
					course.setCou_name(rs.getString("cou_name"));
					course.setCou_start_date(rs.getString("start_date"));
					course.setCou_end_date(rs.getString("end_date"));
					course.setClass_total(rs.getInt("sub_count"));
					course.setSt_total(rs.getInt("class_total"));
					course.setClass_name(rs.getString("class_name"));
				}
				if (key.equals("courseNameAll")) {
					course.setCourseName_id(rs.getString("coursename_id"));
					course.setCou_name(rs.getString("cou_name"));
				}
				if (key.equals("classroomAll")) {
					course.setClassroom_id(rs.getString("classroom_id"));
					course.setClass_name(rs.getString("class_name"));
					course.setClass_total(rs.getInt("total"));
				}
				if (key.equals("courseId_sub")){
					course.setSubject_id(rs.getString("subject_id"));
					course.setSub_name(rs.getString("sub_name"));
					course.setSub_start_date(rs.getString("sub_start_date"));
					course.setSub_end_date(rs.getString("sub_end_date"));
					course.setInst_name(rs.getString("inst_name"));
					course.setText_name(rs.getString("text_name"));
				}
				if (key.equals("courseId_student")) {
					course.setSt_name(rs.getString("st_name"));
					course.setSt_pw(rs.getInt("st_pw"));
					course.setSt_phone(rs.getString("phone"));
					course.setRegister_date(rs.getString("register_date"));
					course.setFin(rs.getString("fin"));
				}
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
}
