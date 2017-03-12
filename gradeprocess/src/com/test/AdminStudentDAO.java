package com.test;

import java.sql.*;
import java.util.*;

public class AdminStudentDAO {

	public List<AllList> list1(String key, String key2, String value) {
		List<AllList> result = new ArrayList<>();

		String sql = "";
		// key
		switch (key) {
		case "studentAll":// 수강생 관리 - 전체 출력
			sql = "SELECT st.student_id AS student_id, st.st_name AS st_name, st.st_pw AS st_pw, st.phone AS phone, (SELECT COUNT(course_id) FROM history WHERE student_id = st.student_id) AS st_cou_count, TO_CHAR(st.register_date, 'YYYY-MM-DD') AS register_date FROM students st";
			break;
		case "studentAll2":// 수강생 관리 - 전체 출력 테스트
			sql = "SELECT student_id, st_name, st_pw, phone, progress"
				+ ", (SELECT COUNT(course_id) FROM history hh WHERE hh.student_id = st.student_id) AS st_cou_count"
				+ ", register_date FROM admin_st_view st";
			break;
		case "courseAddAll":// 수강생 관리 - 과정추가 과정 전체 출력
			sql = "SELECT course_id, start_date, end_date, class_name, cou_name, class_total FROM admin_cou_view";
			break;
		}
		// key2
		switch (key2) {
		case "studentAll":// 수강생 관리 - 전체 출력
			sql += " ORDER BY student_id";
			break;
		case "sc_st-id":// 수강생 관리 - 수강생ID로 검색 출력
			sql += " WHERE INSTR(LOWER(student_id), LOWER(?)) > 0"
				+ " ORDER BY student_id";
			break;
		case "sc_st-name":// 수강생 관리 - 수강생 명으로 검색 출력
			sql += " WHERE INSTR(LOWER(st_name), LOWER(?)) > 0"
				+ " ORDER BY student_id";
			break;
		case "courseAddAll":// 수강생 관리 - 전체 출력
			sql += " WHERE SYSDATE < TO_DATE(start_date, 'YYYY-MM-DD')"
				+ " ORDER BY course_id";
			break;
		}

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);
			// key2
			switch (key2) {
			case "sc_st-id"://수강생ID로 검색 출력
			case "sc_st-name"://수강생 명으로 검색 출력
				pstmt.setString(1, value);
				break;
			}
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				AllList student = new AllList();
				if(key.equals("studentAll")||key.equals("sc_st-id")||key.equals("sc_st-name")){
					student.setStudent_id(rs.getString("student_id"));
					student.setSt_name(rs.getString("st_name"));
					student.setSt_pw(rs.getInt("st_pw"));
					student.setSt_phone(rs.getString("phone"));
					//student.setProgress_ox(String.valueOf(rs.getInt("progress")));
					student.setCou_count(rs.getInt("st_cou_count"));
					student.setRegister_date(rs.getString("register_date"));
				}
				if (key.equals("courseAddAll")) {
					student.setCourse_id(rs.getString("course_id"));
					student.setCou_start_date(rs.getString("start_date"));
					student.setCou_end_date(rs.getString("end_date"));
					student.setClass_name(rs.getString("class_name"));
					student.setCou_name(rs.getString("cou_name"));
					student.setClass_total(rs.getInt("class_total"));
				}
				result.add(student);
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
		case "studentInfo":// 수강생 관리2 - 수강생ID로 검색 출력
			sql = "SELECT course_id, cou_name, cou_start_date, cou_end_date, subject_id, sub_name, sub_start_date, sub_end_date, inst_name, class_name, fin, TO_CHAR(mid_fail_date, 'YYYY-MM-DD') AS mid_fail_date FROM admin_mid_fail_view1";
			break;
		case "sc_cou-id":// 수강생 관리2 - 수강생ID로 검색후 과정ID검색 출력
			sql = "SELECT course_id, cou_name, cou_start_date, cou_end_date, subject_id, sub_name, sub_start_date, sub_end_date, inst_name, class_name, fin, TO_CHAR(mid_fail_date, 'YYYY-MM-DD') AS mid_fail_date FROM admin_mid_fail_view1"
				+ " WHERE student_id = ? AND INSTR(LOWER(course_id), LOWER(?)) > 0";
			break;
		case "sc_cou-name":// 수강생 관리2 - 수강생ID로 검색후 과정ID검색 출력
			sql = "SELECT course_id, cou_name, cou_start_date, cou_end_date, subject_id, sub_name, sub_start_date, sub_end_date, inst_name, class_name, fin, TO_CHAR(mid_fail_date, 'YYYY-MM-DD') AS mid_fail_date FROM admin_mid_fail_view1"
				+ " WHERE student_id = ? AND INSTR(LOWER(cou_name), LOWER(?)) > 0";
			break;
		}
	
		// key2
		switch (key2) {
		case "studentInfo":// 수강생 관리2 - 수강생ID로 검색 출력
			sql += " WHERE student_id = ?";
			break;
		}

		sql += " ORDER BY course_id";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);
			// key2
			switch (key) {
			case "studentInfo":// 수강생 관리2 - 수강생ID로 검색 출력
				pstmt.setString(1, value);
				break;
			case "sc_cou-id":// 수강생 관리2 - 수강생ID로 검색후 과정ID검색 출력
			case "sc_cou-name":// 수강생 관리2 - 수강생ID로 검색후 과정ID검색 출력
				pstmt.setString(1, value);
				pstmt.setString(2, key2);
				break;
			}
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				AllList student = new AllList();
				if(key.equals("studentInfo")||key.equals("sc_cou-id")||key.equals("sc_cou-name")){
					student.setCourse_id(rs.getString("course_id"));
					student.setCou_name(rs.getString("cou_name"));
					student.setCou_start_date(rs.getString("cou_start_date"));
					student.setCou_end_date(rs.getString("cou_end_date"));
					student.setSubject_id(rs.getString("subject_id"));
					student.setSub_name(rs.getString("sub_name"));
					student.setSub_start_date(rs.getString("sub_start_date"));
					student.setSub_end_date(rs.getString("sub_end_date"));
					student.setInst_name(rs.getString("inst_name"));
					student.setClass_name(rs.getString("class_name"));
					student.setFin(String.valueOf(rs.getInt("fin")));
					student.setMid_fail_date(rs.getString("mid_fail_date"));					
				}
				result.add(student);
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
	
	public List<AllList> listfail1(String key, String key2, String value) {
		List<AllList> result = new ArrayList<>();

		String sql = "";
		// key
		switch (key) {
		case "failcourseAll":// 수강생 과정탈락 - 과정 정보 오늘날짜 기준 출력
			sql += "SELECT course_id, start_date, end_date, class_name, cou_name, class_total, mid_fail FROM admin_mid_fail_view2";
			break;
		}
		// key2
		switch (key2) {
		case "failcourseAll":// 수강생 과정탈락 - 과정 정보 오늘날짜 기준 출력
			sql += " ORDER BY course_id";
			break;
		case "sc_cou-id":// 수강생 과정탈락 - 과정정보기준 과정ID검색 출력
			sql += " WHERE INSTR(LOWER(course_id), LOWER(?)) > 0"
				+ " ORDER BY course_id";
			break;
		case "sc_cou-name":// 수강생 과정탈락 - 과정정보기준 과정명검색 출력
			sql += " WHERE INSTR(LOWER(cou_name), LOWER(?)) > 0"
				+ " ORDER BY course_id";
			break;
		}

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);
			// key2
			switch (key2) {
			case "sc_cou-id":// 수강생 과정탈락 - 과정정보기준 과정ID검색 출력
			case "sc_cou-name":// 수강생 과정탈락 - 과정정보기준 과정명검색 출력
				pstmt.setString(1, value);
				break;
			}
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				AllList student = new AllList();
				if(key.equals("failcourseAll")||key.equals("sc_cou-id")||key.equals("sc_cou-name")){
					student.setCourse_id(rs.getString("course_id"));
					student.setCou_start_date(rs.getString("start_date"));
					student.setCou_end_date(rs.getString("end_date"));
					student.setClass_name(rs.getString("class_name"));
					student.setCou_name(rs.getString("cou_name"));
					student.setClass_total(rs.getInt("class_total"));
					student.setMid_fail_count(rs.getInt("mid_fail"));
				}
				result.add(student);
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
	
	public List<AllList> listfail2(String key, String key2, String value) {
		List<AllList> result = new ArrayList<>();

		String sql = "";
		// key
		switch (key) {
		case "failcourseId_studentAll":// 수강생 과정탈락2 - 과정 정보기준 수강생 전체 출력
			sql += "SELECT st.student_id AS student_id, st_name, st_pw, phone, (SELECT COUNT(course_id) FROM history WHERE student_id = st.student_id) AS st_cou_count, (SELECT mid_fail_date FROM mid_fail WHERE student_id = h.student_id AND course_id  = h.course_id) AS mid_fail_date  FROM students st, history h, course cou WHERE cou.course_id(+) = h.course_id AND st.student_id(+) = h.student_id AND h.course_id =?";  

			break;
		case "sc_st-id":// 수강생 과정탈락2 - 과정 정보기준 수강ID 검색 출력
			sql += "SELECT st.student_id AS student_id, st_name, st_pw, phone, (SELECT COUNT(course_id) FROM history WHERE student_id = st.student_id) AS st_cou_count, (SELECT mid_fail_date FROM mid_fail WHERE student_id = h.student_id AND course_id  = h.course_id) AS mid_fail_date  FROM students st, history h, course cou WHERE cou.course_id(+) = h.course_id AND st.student_id(+) = h.student_id"
					+ " AND h.course_id = ? AND INSTR(LOWER(h.student_id), LOWER(?)) > 0"
					+ " ORDER BY student_id";
			break;
		case "sc_st-name":// 수강생 과정탈락2 - 과정 정보기준 수강ID 검색 출력
			sql += "SELECT st.student_id AS student_id, st_name, st_pw, phone, (SELECT COUNT(course_id) FROM history WHERE student_id = st.student_id) AS st_cou_count, (SELECT mid_fail_date FROM mid_fail WHERE student_id = h.student_id AND course_id  = h.course_id) AS mid_fail_date  FROM students st, history h, course cou WHERE cou.course_id(+) = h.course_id AND st.student_id(+) = h.student_id"
					+ " AND h.course_id = ? AND INSTR(LOWER(st_name), LOWER(?)) > 0"
					+ " ORDER BY student_id";
			break;
		}
		// key2
		switch (key2) {
		case "failcourseId_studentAll":// 수강생 과정탈락2 - 과정 정보기준 수강생 전체 출력
			sql += " ORDER BY student_id";
			break;
		}

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);
			// key
			switch (key) {
			case "failcourseId_studentAll":// 수강생 과정탈락2 - 과정 정보 오늘날짜 기준 출력
				pstmt.setString(1, value);
				break;
			case "sc_st-id":// 수강생 과정탈락2 - 과정 정보기준 수강ID 검색 출력
			case "sc_st-name":// 수강생 과정탈락2 - 과정 정보기준 수강생 명 검색 출력
				pstmt.setString(1, value);
				pstmt.setString(2, key2);
				break;
			}
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				AllList student = new AllList();
				if(key.equals("failcourseId_studentAll")||key.equals("sc_st-id")||key.equals("sc_st-name")){
					student.setStudent_id(rs.getString("student_id"));
					student.setSt_name(rs.getString("st_name"));
					student.setSt_pw(rs.getInt("st_pw"));
					student.setSt_phone(rs.getString("phone"));
					student.setCou_count(rs.getInt("st_cou_count"));
					student.setMid_fail_date(rs.getString("mid_fail_date"));
				}
				result.add(student);
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
		case "studentAll":  //
			sql = "SELECT COUNT(*) AS \"total\" FROM students";
			break;
		case "studentInfo": // 
			sql = "SELECT COUNT(*) AS \"total\" FROM admin_mid_fail_view1 WHERE student_id = ?";
			break;
		case "failcourseAll": // 
			sql = "SELECT COUNT(*) AS \"total\" FROM admin_mid_fail_view2";
			break;
		case "failcourseId_studentAll": // 
			sql = "SELECT COUNT(*) AS \"total\" FROM students st, history h, course cou WHERE cou.course_id = h.course_id AND st.student_id = h.student_id AND h.course_id = ?";
			break;
		}
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);
			// key
			switch (key) {
			case "studentInfo":
			case "failcourseId_studentAll":
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


	//수강생 추가 
	public int studentAdd(AllList a) {
		int result = 0;
	 
		String sql1 = "SELECT student_id FROM studentSeqView";
		String sql2 = "INSERT INTO students (student_id, st_name, st_pw, phone) VALUES(?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		
		try {
			conn = DatabaseConnection.connect();
			//트랙잭션 처리
			conn.setAutoCommit(false);
			//학생 신규 번호 얻는 과정
			String student_id = "st_000";
			pstmt1 = conn.prepareStatement(sql1);
			ResultSet rs = pstmt1.executeQuery();
			while(rs.next()) {
				student_id = rs.getString("student_id");
			}
			rs.close();
			//학생 개인 정보 입력 과정
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, student_id);
			pstmt2.setString(2, a.getSt_name());
			pstmt2.setInt(3, a.getSt_pw());
			pstmt2.setString(4, a.getSt_phone());
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

      //학생 정보 수정
	public int studentModify(AllList m){
		int result = 0;
		String sql = "UPDATE students SET st_name=?, phone=? WHERE student_id =?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = DatabaseConnection.connect();
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getSt_name());
			pstmt.setString(2, m.getSt_phone());
			pstmt.setString(3, m.getStudent_id());
			
			result = pstmt.executeUpdate();
			
			conn.commit();
			result =1;
 
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
 
	    //학생 정보 삭제
		public int remove(AllList r) {
			int result = 0;
			String sql = "DELETE FROM students WHERE student_id= ?";

			Connection conn = null;
			PreparedStatement pstmt = null;

			try {

				conn = DatabaseConnection.connect();
				conn.setAutoCommit(false);
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, r.getStudent_id());
 
				result = pstmt.executeUpdate();
				
				conn.commit();
				
				System.out.println("result " + result);
	 
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
		
		//과정등록
		public int course_add(AllList a) {
			int result = 0;
			String sql = "INSERT INTO history(student_id, course_id) VALUES(?,?)";
			Connection conn = null;
			PreparedStatement pstmt = null;

			try {
				conn = DatabaseConnection.connect();
				//트랙잭션 처리
				conn.setAutoCommit(false);
 
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, a.getStudent_id());
				pstmt.setString(2, a.getCourse_id());
			
				result = pstmt.executeUpdate();

				//트랙잭션 처리
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
				
		//과정 탈락
		public int st_mid_fail(AllList a) {
			int result = 0;
			String sql = "INSERT INTO mid_fail(course_id , student_id , mid_fail_date) VALUES(? ,? ,?)";
 
			Connection conn = null;
			PreparedStatement pstmt = null;


			try {
				conn = DatabaseConnection.connect();
				//트랙잭션 처리
				conn.setAutoCommit(false);
 
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, a.getCourse_id());
				pstmt.setString(2, a.getStudent_id());
				pstmt.setString(3, a.getMid_fail_date());
				
				result = pstmt.executeUpdate();
 
				//트랙잭션 처리
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
		
}
