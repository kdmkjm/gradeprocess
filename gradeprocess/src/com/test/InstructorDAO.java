package com.test;

import java.sql.*;
import java.util.*;


public class InstructorDAO {

	//스케줄(전체) 출력 및 검색 
	public List<AllList> schedule_allList(String instructor_id, String key, String value) {
		List<AllList> result = new ArrayList<>();

		String sql = "SELECT subject_id, cou_name, class_name, sub_name, sub_start_date, sub_end_date, text_name, st_total, schedule, publisher, cou_start_date, cou_end_date FROM int_schedule WHERE instructor_id = ?";

		switch (key) {

		case "all": // 강사 로그인> 1. 강의스케줄 조회> 1. 전체
			break;
		case "cou_name":
			sql += String.format(" AND INSTR(UPPER(%s), UPPER(?)) > 0", key);
			break;
		case "sub_name":	
			sql += String.format(" AND UPPER(%s) = UPPER(?)", key);
			break;
		}

		sql += " ORDER BY cou_name, sub_start_date";
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);

			switch (key) {

			case "all":
				pstmt.setString(1, instructor_id);
				break;
			case "cou_name":
			case "sub_name":	
				pstmt.setString(1, instructor_id);
				pstmt.setString(2, value);
				break;
			}

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				AllList inst = new AllList();

				inst.setSubject_id(rs.getString("subject_id"));
				inst.setCou_name(rs.getString("cou_name"));
				inst.setClass_name(rs.getString("class_name"));
				inst.setSub_name(rs.getString("sub_name"));
				inst.setSub_start_date(rs.getString("sub_start_date"));
				inst.setSub_end_date(rs.getString("sub_end_date"));
				inst.setText_name(rs.getString("text_name"));
				inst.setSt_total(rs.getInt("st_total"));
				inst.setSchedule(rs.getInt("schedule"));
				inst.setPublisher(rs.getString("publisher"));
				inst.setCou_start_date(rs.getString("cou_start_date"));
				inst.setCou_end_date(rs.getString("cou_end_date"));
				
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

	//스케줄(강의예정) 출력 및 검색 
	public List<AllList> schedule_startList(String instructor_id, String key, String value) {
		List<AllList> result = new ArrayList<>();

		String sql = "SELECT subject_id, cou_name, class_name, sub_name, sub_start_date, sub_end_date, text_name, st_total, schedule, publisher, cou_start_date, cou_end_date FROM int_schedule WHERE instructor_id = ?";

		switch (key) {

		case "start": // 강사 로그인> 1. 강의스케줄 조회> 2. 강의예정
			sql += " AND schedule = 0";
			break;
		case "cou_name":
			sql += String.format(" AND schedule = 0 AND INSTR(UPPER(%s), UPPER(?)) > 0", key);
			break;
		case "sub_name":	
			sql += String.format(" AND schedule = 0 AND UPPER(%s) = UPPER(?)", key);
			break;
		}

		sql += " ORDER BY cou_name, sub_start_date";
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);

			switch (key) {

			case "start":
				pstmt.setString(1, instructor_id);
				break;
			case "cou_name":
			case "sub_name":	
				pstmt.setString(1, instructor_id);
				pstmt.setString(2, value);
				break;
			}

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				AllList inst = new AllList();

				inst.setSubject_id(rs.getString("subject_id"));
				inst.setCou_name(rs.getString("cou_name"));
				inst.setClass_name(rs.getString("class_name"));
				inst.setSub_name(rs.getString("sub_name"));
				inst.setSub_start_date(rs.getString("sub_start_date"));
				inst.setSub_end_date(rs.getString("sub_end_date"));
				inst.setText_name(rs.getString("text_name"));
				inst.setSt_total(rs.getInt("st_total"));
				inst.setSchedule(rs.getInt("schedule"));
				inst.setPublisher(rs.getString("publisher"));
				inst.setCou_start_date(rs.getString("cou_start_date"));
				inst.setCou_end_date(rs.getString("cou_end_date"));

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

	//스케줄(강의중) 출력 및 검색 
	public List<AllList> schedule_ingList(String instructor_id, String key, String value) {
		List<AllList> result = new ArrayList<>();

		String sql = "SELECT subject_id, cou_name, class_name, sub_name, sub_start_date, sub_end_date, text_name, st_total, schedule, publisher, cou_start_date, cou_end_date FROM int_schedule WHERE instructor_id = ?";

		switch (key) {

		case "ing": // 강사 로그인> 1. 강의스케줄 조회> 3. 강의중
			sql += " AND schedule = 1";
			break;
		case "cou_name":
			sql += String.format(" AND schedule = 1 AND INSTR(UPPER(%s), UPPER(?)) > 0", key);
			break;
		case "sub_name":	
			sql += String.format(" AND schedule = 1 AND UPPER(%s) = UPPER(?)", key);
			break;
		}

		sql += " ORDER BY cou_name, sub_start_date";
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);

			switch (key) {

			case "ing":
				pstmt.setString(1, instructor_id);
				break;
			case "cou_name":
			case "sub_name":	
				pstmt.setString(1, instructor_id);
				pstmt.setString(2, value);
				break;
			}

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				AllList inst = new AllList();

				inst.setSubject_id(rs.getString("subject_id"));
				inst.setCou_name(rs.getString("cou_name"));
				inst.setClass_name(rs.getString("class_name"));
				inst.setSub_name(rs.getString("sub_name"));
				inst.setSub_start_date(rs.getString("sub_start_date"));
				inst.setSub_end_date(rs.getString("sub_end_date"));
				inst.setText_name(rs.getString("text_name"));
				inst.setSt_total(rs.getInt("st_total"));
				inst.setSchedule(rs.getInt("schedule"));
				inst.setPublisher(rs.getString("publisher"));
				inst.setCou_start_date(rs.getString("cou_start_date"));
				inst.setCou_end_date(rs.getString("cou_end_date"));

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

	//스케줄(강의종료) 출력 및 검색 
	public List<AllList> schedule_endList(String instructor_id, String key, String value) {
		List<AllList> result = new ArrayList<>();

		String sql = "SELECT subject_id, cou_name, class_name, sub_name, sub_start_date, sub_end_date, text_name, st_total, schedule, publisher, cou_start_date, cou_end_date FROM int_schedule WHERE instructor_id = ?";

		switch (key) {

		case "end": // 강사 로그인> 1. 강의스케줄 조회> 4. 강의종료
			sql += " AND schedule = 2";
			break;	
		case "cou_name":
			sql += String.format(" AND schedule = 2 AND INSTR(UPPER(%s), UPPER(?)) > 0", key);
			break;
		case "sub_name":	
			sql += String.format(" AND schedule = 2 AND UPPER(%s) = UPPER(?)", key);
			break;
		}

		sql += " ORDER BY cou_name, sub_start_date";
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);

			switch (key) {

			case "end":	
				pstmt.setString(1, instructor_id);
				break;
			case "cou_name":
			case "sub_name":	
				pstmt.setString(1, instructor_id);
				pstmt.setString(2, value);
				break;
			}

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				AllList inst = new AllList();

				inst.setSubject_id(rs.getString("subject_id"));
				inst.setCou_name(rs.getString("cou_name"));
				inst.setClass_name(rs.getString("class_name"));
				inst.setSub_name(rs.getString("sub_name"));
				inst.setSub_start_date(rs.getString("sub_start_date"));
				inst.setSub_end_date(rs.getString("sub_end_date"));
				inst.setText_name(rs.getString("text_name"));
				inst.setSt_total(rs.getInt("st_total"));
				inst.setSchedule(rs.getInt("schedule"));
				inst.setPublisher(rs.getString("publisher"));
				inst.setCou_start_date(rs.getString("cou_start_date"));
				inst.setCou_end_date(rs.getString("cou_end_date"));

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


	//수강생 조회 출력 모달
	public List<AllList> schedule_modalList(String subject_id) {
		List<AllList> result = new ArrayList<>();

		String sql = "SELECT student_id, st_name, phone, register_date, fin FROM inst_st_view WHERE subject_id = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, subject_id);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				AllList inst = new AllList();

				inst.setStudent_id(rs.getString("student_id"));//수강생ID
				inst.setSt_name(rs.getString("st_name"));//수강생 명
				inst.setSt_phone(rs.getString("phone"));//전화번호
				inst.setRegister_date(rs.getString("register_date"));//등록일
				inst.setFin(rs.getString("fin"));//수료 여부

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

	//배점/시험 출력 및 검색 
	public List<AllList> point_examList(String instructor_id, String key, String value) {
		List<AllList> result = new ArrayList<>();

		String sql = "SELECT isv.subject_id AS subject_id, cou_name, class_name, sub_name, start_date, end_date, textbook_name, p.po_attend AS po_attend, p.po_write AS po_write, p.po_practice AS po_practice, examination, publisher, exam_date, exam_zip, cou_start_date, cou_end_date"
				+ " FROM inst_sub_view isv, points p"
				+ " WHERE isv.subject_id = p.subject_id(+)"
				+ " AND isv.end_date < TO_CHAR(SYSDATE, 'YYYY-MM-DD')"
				+ " AND instructor_id = ?";

		switch (key) {

		case "all":
			break;
		case "cou_name":
			sql += String.format(" AND INSTR(UPPER(%s), UPPER(?)) > 0", key);
			break;
		case "sub_name":	
			sql += String.format(" AND UPPER(%s) = UPPER(?)", key);
			break;
		case "subject_id":	
			sql += String.format(" AND isv.%s = ?", key);
			break;	
		}

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);

			switch (key) {

			case "all":
				pstmt.setString(1, instructor_id);
				break;
			case "cou_name":
			case "sub_name":
			case "subject_id":		
				pstmt.setString(1, instructor_id);
				pstmt.setString(2, value);
				break;
			}

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				AllList inst = new AllList();

				inst.setSubject_id(rs.getString("subject_id"));//과목ID
				inst.setSub_name(rs.getString("sub_name"));//과목명
				inst.setPo_attend(rs.getString("po_attend"));//출결배점
				inst.setPo_write(rs.getString("po_write"));//필기배점
				inst.setPo_practice(rs.getString("po_practice"));//실기배점
				inst.setSub_start_date(rs.getString("start_date"));//과목 시작
				inst.setSub_end_date(rs.getString("end_date"));//과목 종료
				inst.setText_name(rs.getString("textbook_name"));//교재명
				inst.setCou_name(rs.getString("cou_name"));//과정명
				inst.setClass_name(rs.getString("class_name"));//강의실명
				inst.setPublisher(rs.getString("publisher"));//출판사
				inst.setExamination(rs.getString("examination"));//시험문제	
				inst.setExam_date(rs.getString("exam_date")); //시험날짜
				inst.setExam_zip(rs.getString("exam_zip")); //시험문제(.zip)
				inst.setCou_start_date(rs.getString("cou_start_date"));
				inst.setCou_end_date(rs.getString("cou_end_date"));

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

	//성적 출력 및 검색 
	public List<AllList> gradeList(String instructor_id, String key, String value) {

		List<AllList> result = new ArrayList<>();

		String sql = "SELECT DISTINCT isv.subject_id AS subject_id, isv.course_id, cou_name, class_name, sub_name, start_date, end_date, textbook_name, p.po_attend AS po_attend, p.po_write AS po_write, p.po_practice AS po_practice, DECODE(g.gr_practice, null, 0, 1) AS grade_ox, publisher, cou_start_date, cou_end_date"
				+ " FROM inst_sub_view isv, points p, grade g"
				+ " WHERE isv.subject_id = p.subject_id(+)"
				+ " AND isv.subject_id = g.subject_id(+)"
				+ " AND isv.end_date < TO_CHAR(SYSDATE, 'YYYY-MM-DD')"
				+ " AND isv.instructor_id = ?";

		switch (key) {

		case "all": 
			break;
		case "cou_name":
			sql += String.format(" AND INSTR(UPPER(%s), UPPER(?)) > 0", key);
			break;
		case "sub_name":	
			sql += String.format(" AND UPPER(%s) = UPPER(?)", key);
			break;
		}

		sql += " ORDER BY subject_id";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);

			switch (key) {

			case "all":
				pstmt.setString(1, instructor_id);
				break;
			case "cou_name":
			case "sub_name":	
				pstmt.setString(1, instructor_id);
				pstmt.setString(2, value);
				break;
			}

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				AllList inst = new AllList();

				inst.setCourse_id(rs.getString("course_id")); //과정ID
				inst.setSubject_id(rs.getString("subject_id"));//과목ID
				inst.setSub_name(rs.getString("sub_name"));//과목명
				inst.setPo_attend(rs.getString("po_attend"));//출결배점
				inst.setPo_write(rs.getString("po_write"));//필기배점
				inst.setPo_practice(rs.getString("po_practice"));//실기배점
				inst.setSub_start_date(rs.getString("start_date"));//과목 시작
				inst.setSub_end_date(rs.getString("end_date"));//과목 종료
				inst.setText_name(rs.getString("textbook_name"));//교재명
				inst.setCou_name(rs.getString("cou_name"));//과정명
				inst.setClass_name(rs.getString("class_name"));//강의실명
				inst.setPublisher(rs.getString("publisher"));//출판사
				inst.setGrade_ox(rs.getString("grade_ox"));//성적등록여부
				inst.setCou_start_date(rs.getString("cou_start_date"));
				inst.setCou_end_date(rs.getString("cou_end_date"));

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

	//성적서브 출력 및 검색 
	public List<AllList> grade_subList(String subject_id, String key, String value) {

		List<AllList> result = new ArrayList<>();

		String sql = "SELECT isv.student_id AS student_id, isv.course_id AS course_id, st_name, phone, register_date, fin, mid_fail_date, g.gr_attend AS gr_attend, g.gr_write AS gr_write, g.gr_practice AS gr_practice, isv.po_attend"
				+ " FROM inst_st_view isv, grade g"
				+ " WHERE isv.student_id = g.student_id(+)"
				+ " AND isv.course_id = g.course_id(+)"
				+ " AND isv.subject_id = g.subject_id(+)"
				+ " AND isv.subject_id = ?";

		switch (key) {

		case "all": 
			break;
		case "st_name":
			sql += String.format(" AND %s = ?", key);
			break;
		}

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);

			switch (key) {

			case "all":
				pstmt.setString(1, subject_id);
				break;
			case "st_name":
				pstmt.setString(1, subject_id);
				pstmt.setString(2, value);
				break;
			}

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				AllList inst = new AllList();

				inst.setCourse_id(rs.getString("course_id")); //과정ID
				inst.setStudent_id(rs.getString("student_id"));//수강생ID
				inst.setSt_name(rs.getString("st_name"));//수강생 명
				inst.setSt_phone(rs.getString("phone"));//전화번호
				inst.setRegister_date(rs.getString("register_date"));//등록일
				inst.setFin(rs.getString("fin"));//수료 여부
				inst.setMid_fail_date(rs.getString("mid_fail_date"));//중도탈락날짜
				inst.setGr_attend(rs.getString("gr_attend"));//출결성적
				inst.setGr_write(rs.getString("gr_write"));//필기성적
				inst.setGr_practice(rs.getString("gr_practice"));//실기성적
				inst.setPo_attend(rs.getString("po_attend"));//출결배점
				

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

	//배점 추가
	public int pointAdd(AllList point) {

		int result = 0;

		String sql = "INSERT INTO points(subject_id, po_attend, po_write, po_practice) VALUES(?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DatabaseConnection.connect();

			// 트랙잭션 처리
			conn.setAutoCommit(false);

			// 배점 및 과목 번호 입력
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, point.getSubject_id());
			pstmt.setString(2, point.getPo_attend());
			pstmt.setString(3, point.getPo_write());
			pstmt.setString(4, point.getPo_practice());
			pstmt.executeUpdate();

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

	//배점 수정
	public int pointModify(AllList point) {

		int result = 0;

		String sql = "UPDATE points SET po_attend=?, po_write=?, po_practice=? WHERE subject_id=?";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DatabaseConnection.connect();

			// 트랙잭션 처리
			conn.setAutoCommit(false);

			// 배점 및 과목 번호 입력
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, point.getPo_attend());
			pstmt.setString(2, point.getPo_write());
			pstmt.setString(3, point.getPo_practice());
			pstmt.setString(4, point.getSubject_id());
			pstmt.executeUpdate();

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

	//배점 삭제
	public int pointRemove(AllList point) {

		int result = 0;

		String sql = "DELETE FROM points WHERE subject_id=?";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DatabaseConnection.connect();

			// 배점 및 과목 번호 입력
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, point.getSubject_id());

			result = pstmt.executeUpdate();

		} catch(ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		} finally {
			try{
				if(pstmt != null) {
					pstmt.close();
				}
			} catch(SQLException ex) {
				ex.printStackTrace();
			}
			try {
				DatabaseConnection.close();
			} catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	//시험 추가
	public int examAdd(AllList exam) {

		int result = 0;

		String sql = "INSERT INTO exams(subject_id, exam_date, examination, exam_zip) VALUES(?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DatabaseConnection.connect();

			// 트랙잭션 처리
			conn.setAutoCommit(false);

			// 배점 및 과목 번호 입력
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, exam.getSubject_id());
			pstmt.setString(2, exam.getExam_date());
			pstmt.setString(3, exam.getExamination());
			pstmt.setString(4, exam.getExam_zip());
			pstmt.executeUpdate();

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

	//시험 수정
	public int examModify(AllList exam) {

		int result = 0;

		String sql = "UPDATE exams SET exam_date=?, examination=?, exam_zip=? WHERE subject_id=?";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DatabaseConnection.connect();

			// 트랙잭션 처리
			conn.setAutoCommit(false);

			// 배점 및 과목 번호 입력
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, exam.getExam_date());
			pstmt.setString(2, exam.getExamination());
			pstmt.setString(3, exam.getExam_zip());
			pstmt.setString(4, exam.getSubject_id());
			pstmt.executeUpdate();

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

	//시험 삭제
	public int examRemove(AllList exam) {

		int result = 0;

		String sql = "DELETE FROM exams WHERE subject_id=?";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DatabaseConnection.connect();

			// 배점 및 과목 번호 입력
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, exam.getSubject_id());

			result = pstmt.executeUpdate();

		} catch(ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		} finally {
			try{
				if(pstmt != null) {
					pstmt.close();
				}
			} catch(SQLException ex) {
				ex.printStackTrace();
			}
			try {
				DatabaseConnection.close();
			} catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	//성적 추가
	public int gradeAdd(AllList grade) {

		int result = 0;

		String sql = "INSERT INTO grade(course_id, student_id, subject_id, gr_attend, gr_write, gr_practice) VALUES (?, ?, ?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DatabaseConnection.connect();

			// 트랙잭션 처리
			conn.setAutoCommit(false);

			// 배점 및 과목 번호 입력
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, grade.getCourse_id());
			pstmt.setString(2, grade.getStudent_id());
			pstmt.setString(3, grade.getSubject_id());
			pstmt.setString(4, grade.getGr_attend());
			pstmt.setString(5, grade.getGr_write());
			pstmt.setString(6, grade.getGr_practice());
			pstmt.executeUpdate();

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

	//성적 수정
	public int gradeModify(AllList grade) {

		int result = 0;

		String sql = "UPDATE grade SET gr_attend=?, gr_write=?, gr_practice=? WHERE course_id=? AND subject_id=? AND student_id =?";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DatabaseConnection.connect();

			// 트랙잭션 처리
			conn.setAutoCommit(false);

			// 배점 및 과목 번호 입력
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, grade.getGr_attend());
			pstmt.setString(2, grade.getGr_write());
			pstmt.setString(3, grade.getGr_practice());
			pstmt.setString(4, grade.getCourse_id());
			pstmt.setString(5, grade.getSubject_id());
			pstmt.setString(6, grade.getStudent_id());
			pstmt.executeUpdate();

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

	//성적 삭제
	public int gradeRemove(AllList grade) {

		int result = 0;

		String sql = "DELETE FROM grade WHERE course_id=? AND subject_id=? AND student_id =?";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DatabaseConnection.connect();

			// 배점 및 과목 번호 입력
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, grade.getCourse_id());
			pstmt.setString(2, grade.getSubject_id());
			pstmt.setString(3, grade.getStudent_id());

			result = pstmt.executeUpdate();

		} catch(ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		} finally {
			try{
				if(pstmt != null) {
					pstmt.close();
				}
			} catch(SQLException ex) {
				ex.printStackTrace();
			}
			try {
				DatabaseConnection.close();
			} catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	// Total (전체 출력 개수 반환) 메소드
	public int total(String key, String value) {
		int result = 0;

		String sql = "";

		switch (key) {
		case "instructor_id":// 강사 배점/시험, 성적_메인페이지
			sql = "SELECT COUNT(*) AS \"total\" FROM inst_sub_view isv, points p"
					+ " WHERE isv.subject_id = p.subject_id(+) AND isv.instructor_id = ?";
			break;
		case "subject_id":// 강사 성적_서브페이지
			sql += "SELECT COUNT(*) AS \"total\" FROM inst_st_view isv, grade g "
					+ "WHERE isv.student_id = g.student_id(+) AND isv.course_id = g.course_id(+) "
					+ "AND isv.subject_id = g.subject_id(+) AND isv.subject_id = ?";
			break;
		case "instructor_schedule":// 강사 스케줄
			sql = "SELECT COUNT(*) AS \"total\" FROM inst_sub_view isv, points p"
					+ " WHERE isv.subject_id = p.subject_id(+) AND isv.instructor_id = ?";
			break;	
		}

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);

			switch (key) {
			case "instructor_id":// 강사 배점/시험, 성적_메인페이지
			case "subject_id":// 강사 성적_서브페이지
			case "instructor_schedule":// 강사 스케줄	
				pstmt.setString(1, value);
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

}