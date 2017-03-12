package com.test;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
 

public class AdminGradeDAO {


	// 관리자 - 성적조회- 과정정보조회
	public List<AllList> List(String key, String value) {
		List<AllList> result = new ArrayList<AllList>();

		String sql = "";

		switch (key) {
		// 모든 과정 정보 검색
		case "courseall":
			sql = "SELECT course_id, start_date, end_date, class_name, cou_name, sub_count, class_total FROM admin_cou_view ORDER BY course_id";
			break;

		// 과정 ID 선택 검색
		case "course_id":
			sql = "SELECT course_id, start_date, end_date, class_name, cou_name, sub_count, class_total FROM admin_cou_view";
			sql += " WHERE INSTR(LOWER(course_id), LOWER(?)) > 0";
			break;

		// 과정명 선택 검색
		case "cou_name":
			sql = "SELECT course_id, start_date, end_date, class_name, cou_name, sub_count, class_total FROM admin_cou_view";
			sql +=  " WHERE INSTR(LOWER(cou_name), LOWER(?)) > 0";
			break;
		}

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);

			switch (key) {
			case "course_id":
			case "cou_name":
				pstmt.setString(1, value);
				break;

			}

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				AllList a = new AllList();

				if (key.equals("courseall") || key.equals("course_id") || key.equals("cou_name")) {
					a.setCourse_id(rs.getString("course_id"));
					a.setCou_start_date(rs.getString("start_date"));
					a.setCou_end_date(rs.getString("end_date"));
					a.setClass_name(rs.getString("class_name"));
					a.setCou_name(rs.getString("cou_name"));
					a.setSub_count(rs.getInt("sub_count"));
					a.setClass_total(rs.getInt("class_total"));
				}
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
				if (conn != null)
					DatabaseConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	
	//과정 total
	public int grade_total() {
		int result = 0;
		String sql = "SELECT COUNT(*) AS \"total\" FROM course";
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
	
	
 
	
	//과목 total
	public int gradecourse_total(String course_id) {
		
		int result = 0;
		String sql = "SELECT COUNT(*) AS \"total\" FROM  subject WHERE course_id =?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, course_id);

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


	
	//수강인원 total
	public int gradecourse2_total(String subject_id) {
		int result = 0;
		String sql = "SELECT COUNT(*) AS \"total\" FROM students st, course cou, subject sub , history h"
				+ " WHERE cou.course_id(+) = h.course_id AND st.student_id = h.student_id(+) AND cou.course_id = sub.course_id(+)"
				+ " AND sub.subject_id = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, subject_id);

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


	// 관리자 - 성적조회 -과정ID로 과목조회
	public List<AllList> grade_courseList(String key, String value, String value2) {
		List<AllList> result = new ArrayList<AllList>();

		String sql = "SELECT subject_id, sub_name, sub_start_date, sub_end_date, po_attend, po_write, po_practice"
				+ ", grade_ox, text_name, inst_name, examination, course_id, exam_zip FROM admin_grade_view5";

		switch (key) {
  
		 //과정ID
		case "grade_course":
			sql += " WHERE course_id = ?";
			break;
		//과목ID
		case "subject_id":
			sql += " WHERE course_id = ? AND INSTR(LOWER(subject_id), LOWER(?)) > 0";
			break;
			
		//과목명
		case "sub_name":
			sql += " WHERE course_id = ? AND INSTR(LOWER(sub_name), LOWER(?)) > 0";
			break;
		}
		sql += " ORDER BY sub_start_date";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);

			switch (key) {
			case "grade_course":
				pstmt.setString(1, value);
				break;
			case "subject_id":
			case "sub_name":
				pstmt.setString(1, value);
				pstmt.setString(2, value2);
				break;
			}

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				AllList a = new AllList();

				if (key.equals("grade_course") || key.equals("subject_id") || key.equals("sub_name")) {
					a.setSubject_id(rs.getString("subject_id"));
					a.setSub_name(rs.getString("sub_name"));
					a.setSub_start_date(rs.getString("sub_start_date"));
					a.setSub_end_date(rs.getString("sub_end_date"));
					a.setPo_attend(rs.getString("po_attend"));
					a.setPo_write(rs.getString("po_write"));
					a.setPo_practice(rs.getString("po_practice"));
					a.setGrade_ox(rs.getString("grade_ox"));
					a.setText_name(rs.getString("text_name"));
					a.setInst_name(rs.getString("inst_name"));
					a.setExamination(rs.getString("examination"));
					a.setExam_zip(rs.getString("exam_zip"));
				}
				
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
				if (conn != null)
					DatabaseConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public List<AllList> grade_courseList2(String key, String value, String value2) {
		List<AllList> result = new ArrayList<AllList>();

		String sql = "SELECT agv.student_id AS student_id, st_name, st_pw, g.gr_attend, g.gr_write, g.gr_practice"
				+ " FROM admin_grade_view4 agv, grade g"
				+ " WHERE agv.course_id = g.course_id(+) AND agv.student_id = g.student_id(+)"
				+ " AND agv.subject_id = g.subject_id(+)";

		switch (key) {
         
		//과목ID
		case "grade_course2":
			sql += " AND agv.subject_id = ?";
			break;
			
	    //학생ID
		case "st_id":
			sql += " AND agv.subject_id =? AND INSTR(LOWER(agv.student_id), LOWER(?)) > 0";
			break;
			
	    //학생명
		case "st_name":
			sql += " AND agv.subject_id =? AND agv.st_name = ?";
			break;
			
		}

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);

			switch (key) {
			case "grade_course2":
				pstmt.setString(1, value);
				break;
			case "st_id":
			case "st_name":
				pstmt.setString(1, value);
				pstmt.setString(2, value2);
				break;
			}

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				AllList a = new AllList();

				if (key.equals("grade_course2") || key.equals("st_id") || key.equals("st_name")) {
					a.setStudent_id(rs.getString("student_id"));
					a.setSt_name(rs.getString("st_name"));
					a.setSt_pw(rs.getInt("st_pw"));
					a.setGr_attend(rs.getString("gr_attend"));
					a.setGr_write(rs.getString("gr_write"));
					a.setGr_practice(rs.getString("gr_practice"));
				}
		
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
				if (conn != null)
					DatabaseConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}


	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// 관리자 > 5. 성적 조회 > 2. 개인 성적 조회
	public List<AllList> gradePersonList(String key, String value) {

		List<AllList> result = new ArrayList<AllList>();

		String sql = "SELECT st.student_id AS student_id, st.st_name AS st_name, st.st_pw AS st_pw, st.phone AS phone"
				+ ", (SELECT COUNT(course_id) FROM history WHERE student_id = st.student_id) AS st_cou_count"
				+ ", TO_CHAR(st.register_date, 'YYYY-MM-DD') AS register_date FROM students st";

		switch (key) {
		case "all":
			break;
		case "student_id":
		case "st_name":
		case "phone":
			sql += String.format(" WHERE INSTR(LOWER(%s), LOWER(?)) > 0", key);
			break;
		}

		sql += " ORDER BY student_id";

		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);
			
			switch(key) {
			case "all":
				break;
			case "student_id":
			case "st_name":
			case "phone":
				pstmt.setString(1, value);
				break;
			}
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				AllList a = new AllList();
				a.setStudent_id(rs.getString("student_id"));
				a.setSt_name(rs.getString("st_name"));
				a.setSt_pw(rs.getInt("st_pw"));
				a.setSt_phone(rs.getString("phone"));
				a.setCou_count(rs.getInt("st_cou_count"));
				a.setRegister_date(rs.getString("register_date"));
			
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
				if(conn != null)
				DatabaseConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	// 관리자 > 5. 성적 조회 > 2. 개인 성적 조회 > total 메소드
	public int studentTotal() {
		int result = 0;
		String sql = "SELECT COUNT(*) AS \"total\" FROM students";
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
	
	
	// 관리자 > 5. 성적 조회 > 2. 개인 성적 조회 > 조회
		public List<AllList> gradePersonList2(String key, String value1, String value2) {
			List<AllList> result = new ArrayList<AllList>();
			
			String sql = "SELECT agv.course_id AS course_id, cou_name, class_name , sub_name, sub_start_date, sub_end_date, inst_name"
						+ " , po_attend, po_write, po_practice, gr_attend, gr_write, gr_practice"
						+ " , (SELECT examination FROM exams WHERE subject_id = agv.subject_id) AS examination"
						+ " , (SELECT exam_zip FROM exams WHERE subject_id = agv.subject_id) AS exam_zip"
						+ " FROM admin_grade_view2 agv, grade g "
						+ " WHERE agv.student_id = g.student_id(+)"
							+ " AND agv.subject_id = g.subject_id(+)"
							+ " AND agv.course_id = g.course_id(+)"
							+ " AND agv.student_id = ?";
						
			switch(key) {
			case "all": break;
			case "cou_name": sql += String.format(" AND INSTR(LOWER(agv.cou_name), LOWER(?)) > 0"); break;
			case "sub_name": sql += String.format(" AND LOWER(agv.sub_name) = LOWER(?)"); break;
			}
			
			sql += " ORDER BY sub_start_date";
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				
				conn = DatabaseConnection.connect();
				pstmt = conn.prepareStatement(sql);
				
				switch(key) {
				case "all":
					pstmt.setString(1, value1);
					break;
				case "cou_name":
				case "sub_name":
					pstmt.setString(1, value1);
					pstmt.setString(2, value2);
					break;
				}
				
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					AllList a = new AllList();
					a.setCourse_id(rs.getString("course_id"));
					a.setCou_name(rs.getString("cou_name"));
					a.setClass_name(rs.getString("class_name"));
					a.setSub_name(rs.getString("sub_name"));
					a.setSub_start_date(rs.getString("sub_start_date"));
					a.setSub_end_date(rs.getString("sub_end_date"));
					a.setInst_name(rs.getString("inst_name"));
					a.setPo_attend(rs.getString("po_attend"));
					a.setPo_write(rs.getString("po_write"));
					a.setPo_practice(rs.getString("po_practice"));
					a.setGr_attend(rs.getString("gr_attend"));
					a.setGr_write(rs.getString("gr_write"));
					a.setGr_practice(rs.getString("gr_practice"));
					a.setExamination(rs.getString("examination"));
					a.setExam_zip(rs.getString("exam_zip"));
					
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
					if (conn != null)
						DatabaseConnection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return result;
				
		}
	
	
	
	// 관리자 > 5. 성적 조회 > 2. 개인 성적 조회 > 개인 지정 > total 메소드
		public int personTotal(String student_id) {
			int result = 0;
			String sql = "SELECT COUNT(*) AS \"total\" FROM admin_grade_view2 agv, grade g"
						+ " WHERE agv.student_id = g.student_id(+)"
							+ " AND agv.subject_id = g.subject_id(+)"
							+ " AND agv.course_id = g.course_id(+)"
							+ " AND agv.student_id = ?";
			Connection conn = null;
			PreparedStatement pstmt = null;
			try {
				conn = DatabaseConnection.connect();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, student_id);

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
