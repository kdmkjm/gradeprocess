package com.test;

import java.sql.*;
import java.util.*;

public class AdminCourseDAO {
	
	// ���� ���� ��� �� ��ȸ
	public List<AllList> list(String key, String key2, String value) {
		
		List<AllList> result = new ArrayList<>();
		String sql = "";
		// key
		switch (key) {
		case "courseAll": // ���� ���� ���� - ���� ��ü ���
			sql = "SELECT rn, course_id, start_date, end_date, class_name, cou_name, sub_count, class_total FROM admin_cou_view";
			break;
		case "courseNameAll": // ���� ���� ���� - ������ ��ü ���
			sql = "SELECT coursename_id, cou_name FROM coursename";
			break;
		case "classroomAll": // ���� ���� ���� - ���ǽ� ��ü ���
			sql = "SELECT classroom_id, class_name, total FROM classroom";
			break;
		case "courseId_sub":  // ���� ���� ���� - ����ID�� �������� ���
			sql = "SELECT subject_id, sub_name, sub_start_date, sub_end_date, text_name, inst_name"
					+ " FROM admin_sub_view4";
			break;
		case "courseId_student" : // ���� ���� ���� - ����ID�� ���������� ���
			sql = "SELECT st_name, st_pw,  phone, register_date, fin FROM admin_st_view";
			break;
		}
		
		// key2
		switch (key2) {
		case "courseAll": // ���� ���� ���� - ���� ��ü ���
			sql += " ORDER BY rn";
			break;
		case "sc_course-id": // ���� ���� ���� - ����id�˻� ���
			sql += " WHERE INSTR(LOWER(course_id), LOWER(?)) > 0 ORDER BY start_date, end_date";
			break;
		case "sc_course-name": // ���� ���� ���� - ������˻� ���
			sql += " WHERE INSTR(LOWER(cou_name), LOWER(?)) > 0 ORDER BY start_date, end_date";
			break;
		case "courseNameAll": // ���� ���� ���� - ������ ��ü ���
			sql += " ORDER BY coursename_id";
			break;
		case "classroomAll": // ���� ���� ���� - ���ǽ� ��ü ���
			sql += " ORDER BY classroom_id";
			break;
		case "courseId_sub": // ���� ���� ���� - ����ID�� �������� ���
			sql += " WHERE course_id = ?"
					+ " ORDER BY subject_id";
			break;
		case "courseId_student": // ���� ���� ���� - ����ID�� ���������� ���
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
	
	// ���� ���� ��� �� ��ȸ
		public List<AllList> list2(String key, String key2, String value) {
			
			List<AllList> result = new ArrayList<>();
			String sql = "";
			// key
			switch (key) {
			case "course_id_sub":  // ���� ���� ����2 - ����ID�� �������� ���
				sql = "SELECT subject_id, sub_name, sub_start_date, sub_end_date, text_name, publisher, inst_name, text_img"
						+ " FROM admin_sub_view4";
				break;
			case "subjectNameAll": // ���� ���� ����2 - ����� ��ü ���
				sql = "SELECT subjectName_id, sub_name FROM subjectName";
				break;
			case "instAll":// ���� ���� ����2 - ��ü ���
				sql = "SELECT instructor_id, inst_name, inst_pw, phone, teachable_sub FROM admin_inst_view";
				break;
			case "textbookAll":// ���� ���� ����2 - ��ü ���
				sql = "SELECT textbook_id, text_name, publisher FROM textbook";
				break;
			}
			
			// key2
			switch (key2) {
			case "course_id_sub": // ���� ���� ����2 - ����ID�� �������� ���
				sql += " WHERE course_id = ?"
						+ " ORDER BY sub_start_date, sub_end_date";
				break;
			case "sc_sub-id": // ���� ���� ����2 - ����ID�� ���������� ����ID�� �˻� ���
				sql = "SELECT subject_id, sub_name, sub_start_date, sub_end_date, text_name, publisher, inst_name, text_img"
						+ " FROM admin_sub_view4"
						+ " WHERE course_id = ? AND INSTR(LOWER(subject_id), LOWER(?)) > 0"
						+ " ORDER BY sub_start_date, sub_end_date";
				break;
			case "sc_sub-name": // ���� ���� ����2 - ����ID�� ���������� ������ �˻� ���
				sql = "SELECT subject_id, sub_name, sub_start_date, sub_end_date, text_name, publisher, inst_name, text_img"
						+ " FROM admin_sub_view4"
						+ " WHERE course_id = ? AND INSTR(LOWER(sub_name), LOWER(?)) > 0"
						+ " ORDER BY sub_start_date, sub_end_date";
				break;
			case "subjectNameAll": // ���� ���� ���� - ����� ��ü ���
				sql += " ORDER BY subjectName_id";
				break;
			case "instAll":// ���� ���� ���� - ����ID���� ��ü���
				sql += " ORDER BY instructor_id";
				break;
			case "textbookAll":// ���� ���� ���� - ����ID���� ��ü���
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
					pstmt.setString(1,  value); //����ID
					pstmt.setString(2,  key); //����ID
					break;
				case "sc_sub-name":
					pstmt.setString(1,  value); //����ID
					pstmt.setString(2,  key); //�����
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
						course.setInstructor_id(rs.getString("instructor_id"));//����id
						course.setInst_name(rs.getString("inst_name"));//�����
						course.setInst_pw(rs.getInt("inst_pw"));//�����ȣ
						course.setInst_phone(rs.getString("phone"));//������ȭ��ȣ
						course.setSub_name(rs.getString("teachable_sub"));//���� ���� ����
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
		
		// Total (��ü ��� ���� ��ȯ) �޼ҵ�
		public int total(String key, String value) {
			int result = 0;
			String sql = "";
			// key
			switch (key) {
			case "courseAll_total":  // ���� ���� ���� - ���� ��ü ���
				sql = "SELECT COUNT(*) AS \"total\" FROM admin_cou_view";
				break;
			case "course_id_sub_total": // ���� ���� ����2 - ����ID�� �������� ���
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
		
		// ������ > ���� ���� ���� > ���� ���� �߰�
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
		
		
	// ������ > ���� ���� ���� > ���� ���� ����
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

	// ������ > ���� ���� ���� > ���� ���� ����
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

	// ������ > ���� ���� ���� > ��ȸ > ���� �߰� > ���� ���� �߰�
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

	
	// ������ > ���� ���� ���� > ��ȸ > ���� �߰� > ���� ���� ����
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
	
	
	// ������ > ���� ���� ���� > ��ȸ > ���� �߰� > ���� ���� ����
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
	// ���� ���� ��� �� ��ȸ (��������� �߰�)
	public List<AllList> list(String key, String key2, String value, int start, int end) {
		
		List<AllList> result = new ArrayList<>();
		String sql = "";
		// key
		switch (key) {
		case "courseAll": // ���� ���� ���� - ���� ��ü ���
			sql = "SELECT rn, course_id, start_date, end_date, class_name, cou_name, sub_count, class_total FROM admin_cou_view";
			break;
		case "courseNameAll": // ���� ���� ���� - ������ ��ü ���
			sql = "SELECT coursename_id, cou_name FROM coursename";
			break;
		case "classroomAll": // ���� ���� ���� - ���ǽ� ��ü ���
			sql = "SELECT classroom_id, class_name, total FROM classroom";
			break;
		case "courseId_sub":  // ���� ���� ���� - ����ID�� �������� ���
			sql = "SELECT subject_id, sub_name, sub_start_date, sub_end_date, text_name, inst_name"
					+ " FROM admin_sub_view4";
			break;
		case "courseId_student" : // ���� ���� ���� - ����ID�� ���������� ���
			sql = "SELECT st_name, st_pw,  phone, register_date, fin FROM admin_st_view";
			break;
		}
		
		// key2
		switch (key2) {
		case "courseAll": // ���� ���� ���� - ���� ��ü ���
			sql += " WHERE rn>=? AND rn<=?"
				+ " ORDER BY rn";
			break;
		case "sc_course-id": // ���� ���� ���� - ����id�˻� ���
			sql += " WHERE INSTR(LOWER(course_id), LOWER(?)) > 0 ORDER BY start_date, end_date";
			break;
		case "sc_course-name": // ���� ���� ���� - ������˻� ���
			sql += " WHERE INSTR(LOWER(cou_name), LOWER(?)) > 0 ORDER BY start_date, end_date";
			break;
		case "courseNameAll": // ���� ���� ���� - ������ ��ü ���
			sql += " ORDER BY coursename_id";
			break;
		case "classroomAll": // ���� ���� ���� - ���ǽ� ��ü ���
			sql += " ORDER BY classroom_id";
			break;
		case "courseId_sub": // ���� ���� ���� - ����ID�� �������� ���
			sql += " WHERE course_id = ?"
					+ " ORDER BY subject_id";
			break;
		case "courseId_student": // ���� ���� ���� - ����ID�� ���������� ���
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
