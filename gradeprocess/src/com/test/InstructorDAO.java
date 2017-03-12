package com.test;

import java.sql.*;
import java.util.*;


public class InstructorDAO {

	//������(��ü) ��� �� �˻� 
	public List<AllList> schedule_allList(String instructor_id, String key, String value) {
		List<AllList> result = new ArrayList<>();

		String sql = "SELECT subject_id, cou_name, class_name, sub_name, sub_start_date, sub_end_date, text_name, st_total, schedule, publisher, cou_start_date, cou_end_date FROM int_schedule WHERE instructor_id = ?";

		switch (key) {

		case "all": // ���� �α���> 1. ���ǽ����� ��ȸ> 1. ��ü
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

	//������(���ǿ���) ��� �� �˻� 
	public List<AllList> schedule_startList(String instructor_id, String key, String value) {
		List<AllList> result = new ArrayList<>();

		String sql = "SELECT subject_id, cou_name, class_name, sub_name, sub_start_date, sub_end_date, text_name, st_total, schedule, publisher, cou_start_date, cou_end_date FROM int_schedule WHERE instructor_id = ?";

		switch (key) {

		case "start": // ���� �α���> 1. ���ǽ����� ��ȸ> 2. ���ǿ���
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

	//������(������) ��� �� �˻� 
	public List<AllList> schedule_ingList(String instructor_id, String key, String value) {
		List<AllList> result = new ArrayList<>();

		String sql = "SELECT subject_id, cou_name, class_name, sub_name, sub_start_date, sub_end_date, text_name, st_total, schedule, publisher, cou_start_date, cou_end_date FROM int_schedule WHERE instructor_id = ?";

		switch (key) {

		case "ing": // ���� �α���> 1. ���ǽ����� ��ȸ> 3. ������
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

	//������(��������) ��� �� �˻� 
	public List<AllList> schedule_endList(String instructor_id, String key, String value) {
		List<AllList> result = new ArrayList<>();

		String sql = "SELECT subject_id, cou_name, class_name, sub_name, sub_start_date, sub_end_date, text_name, st_total, schedule, publisher, cou_start_date, cou_end_date FROM int_schedule WHERE instructor_id = ?";

		switch (key) {

		case "end": // ���� �α���> 1. ���ǽ����� ��ȸ> 4. ��������
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


	//������ ��ȸ ��� ���
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

				inst.setStudent_id(rs.getString("student_id"));//������ID
				inst.setSt_name(rs.getString("st_name"));//������ ��
				inst.setSt_phone(rs.getString("phone"));//��ȭ��ȣ
				inst.setRegister_date(rs.getString("register_date"));//�����
				inst.setFin(rs.getString("fin"));//���� ����

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

	//����/���� ��� �� �˻� 
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

				inst.setSubject_id(rs.getString("subject_id"));//����ID
				inst.setSub_name(rs.getString("sub_name"));//�����
				inst.setPo_attend(rs.getString("po_attend"));//������
				inst.setPo_write(rs.getString("po_write"));//�ʱ����
				inst.setPo_practice(rs.getString("po_practice"));//�Ǳ����
				inst.setSub_start_date(rs.getString("start_date"));//���� ����
				inst.setSub_end_date(rs.getString("end_date"));//���� ����
				inst.setText_name(rs.getString("textbook_name"));//�����
				inst.setCou_name(rs.getString("cou_name"));//������
				inst.setClass_name(rs.getString("class_name"));//���ǽǸ�
				inst.setPublisher(rs.getString("publisher"));//���ǻ�
				inst.setExamination(rs.getString("examination"));//���蹮��	
				inst.setExam_date(rs.getString("exam_date")); //���賯¥
				inst.setExam_zip(rs.getString("exam_zip")); //���蹮��(.zip)
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

	//���� ��� �� �˻� 
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

				inst.setCourse_id(rs.getString("course_id")); //����ID
				inst.setSubject_id(rs.getString("subject_id"));//����ID
				inst.setSub_name(rs.getString("sub_name"));//�����
				inst.setPo_attend(rs.getString("po_attend"));//������
				inst.setPo_write(rs.getString("po_write"));//�ʱ����
				inst.setPo_practice(rs.getString("po_practice"));//�Ǳ����
				inst.setSub_start_date(rs.getString("start_date"));//���� ����
				inst.setSub_end_date(rs.getString("end_date"));//���� ����
				inst.setText_name(rs.getString("textbook_name"));//�����
				inst.setCou_name(rs.getString("cou_name"));//������
				inst.setClass_name(rs.getString("class_name"));//���ǽǸ�
				inst.setPublisher(rs.getString("publisher"));//���ǻ�
				inst.setGrade_ox(rs.getString("grade_ox"));//������Ͽ���
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

	//�������� ��� �� �˻� 
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

				inst.setCourse_id(rs.getString("course_id")); //����ID
				inst.setStudent_id(rs.getString("student_id"));//������ID
				inst.setSt_name(rs.getString("st_name"));//������ ��
				inst.setSt_phone(rs.getString("phone"));//��ȭ��ȣ
				inst.setRegister_date(rs.getString("register_date"));//�����
				inst.setFin(rs.getString("fin"));//���� ����
				inst.setMid_fail_date(rs.getString("mid_fail_date"));//�ߵ�Ż����¥
				inst.setGr_attend(rs.getString("gr_attend"));//��Ἲ��
				inst.setGr_write(rs.getString("gr_write"));//�ʱ⼺��
				inst.setGr_practice(rs.getString("gr_practice"));//�Ǳ⼺��
				inst.setPo_attend(rs.getString("po_attend"));//������
				

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

	//���� �߰�
	public int pointAdd(AllList point) {

		int result = 0;

		String sql = "INSERT INTO points(subject_id, po_attend, po_write, po_practice) VALUES(?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DatabaseConnection.connect();

			// Ʈ����� ó��
			conn.setAutoCommit(false);

			// ���� �� ���� ��ȣ �Է�
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, point.getSubject_id());
			pstmt.setString(2, point.getPo_attend());
			pstmt.setString(3, point.getPo_write());
			pstmt.setString(4, point.getPo_practice());
			pstmt.executeUpdate();

			// Ʈ����� ó��
			conn.commit();
			result = 1;

		} catch (ClassNotFoundException | SQLException e) {
			// Ʈ����� ó��
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

	//���� ����
	public int pointModify(AllList point) {

		int result = 0;

		String sql = "UPDATE points SET po_attend=?, po_write=?, po_practice=? WHERE subject_id=?";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DatabaseConnection.connect();

			// Ʈ����� ó��
			conn.setAutoCommit(false);

			// ���� �� ���� ��ȣ �Է�
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, point.getPo_attend());
			pstmt.setString(2, point.getPo_write());
			pstmt.setString(3, point.getPo_practice());
			pstmt.setString(4, point.getSubject_id());
			pstmt.executeUpdate();

			// Ʈ����� ó��
			conn.commit();
			result = 1;

		} catch (ClassNotFoundException | SQLException e) {
			// Ʈ����� ó��
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

	//���� ����
	public int pointRemove(AllList point) {

		int result = 0;

		String sql = "DELETE FROM points WHERE subject_id=?";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DatabaseConnection.connect();

			// ���� �� ���� ��ȣ �Է�
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

	//���� �߰�
	public int examAdd(AllList exam) {

		int result = 0;

		String sql = "INSERT INTO exams(subject_id, exam_date, examination, exam_zip) VALUES(?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DatabaseConnection.connect();

			// Ʈ����� ó��
			conn.setAutoCommit(false);

			// ���� �� ���� ��ȣ �Է�
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, exam.getSubject_id());
			pstmt.setString(2, exam.getExam_date());
			pstmt.setString(3, exam.getExamination());
			pstmt.setString(4, exam.getExam_zip());
			pstmt.executeUpdate();

			// Ʈ����� ó��
			conn.commit();
			result = 1;

		} catch (ClassNotFoundException | SQLException e) {
			// Ʈ����� ó��
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

	//���� ����
	public int examModify(AllList exam) {

		int result = 0;

		String sql = "UPDATE exams SET exam_date=?, examination=?, exam_zip=? WHERE subject_id=?";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DatabaseConnection.connect();

			// Ʈ����� ó��
			conn.setAutoCommit(false);

			// ���� �� ���� ��ȣ �Է�
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, exam.getExam_date());
			pstmt.setString(2, exam.getExamination());
			pstmt.setString(3, exam.getExam_zip());
			pstmt.setString(4, exam.getSubject_id());
			pstmt.executeUpdate();

			// Ʈ����� ó��
			conn.commit();
			result = 1;

		} catch (ClassNotFoundException | SQLException e) {
			// Ʈ����� ó��
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

	//���� ����
	public int examRemove(AllList exam) {

		int result = 0;

		String sql = "DELETE FROM exams WHERE subject_id=?";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DatabaseConnection.connect();

			// ���� �� ���� ��ȣ �Է�
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

	//���� �߰�
	public int gradeAdd(AllList grade) {

		int result = 0;

		String sql = "INSERT INTO grade(course_id, student_id, subject_id, gr_attend, gr_write, gr_practice) VALUES (?, ?, ?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DatabaseConnection.connect();

			// Ʈ����� ó��
			conn.setAutoCommit(false);

			// ���� �� ���� ��ȣ �Է�
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, grade.getCourse_id());
			pstmt.setString(2, grade.getStudent_id());
			pstmt.setString(3, grade.getSubject_id());
			pstmt.setString(4, grade.getGr_attend());
			pstmt.setString(5, grade.getGr_write());
			pstmt.setString(6, grade.getGr_practice());
			pstmt.executeUpdate();

			// Ʈ����� ó��
			conn.commit();
			result = 1;

		} catch (ClassNotFoundException | SQLException e) {
			// Ʈ����� ó��
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

	//���� ����
	public int gradeModify(AllList grade) {

		int result = 0;

		String sql = "UPDATE grade SET gr_attend=?, gr_write=?, gr_practice=? WHERE course_id=? AND subject_id=? AND student_id =?";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DatabaseConnection.connect();

			// Ʈ����� ó��
			conn.setAutoCommit(false);

			// ���� �� ���� ��ȣ �Է�
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, grade.getGr_attend());
			pstmt.setString(2, grade.getGr_write());
			pstmt.setString(3, grade.getGr_practice());
			pstmt.setString(4, grade.getCourse_id());
			pstmt.setString(5, grade.getSubject_id());
			pstmt.setString(6, grade.getStudent_id());
			pstmt.executeUpdate();

			// Ʈ����� ó��
			conn.commit();
			result = 1;

		} catch (ClassNotFoundException | SQLException e) {
			// Ʈ����� ó��
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

	//���� ����
	public int gradeRemove(AllList grade) {

		int result = 0;

		String sql = "DELETE FROM grade WHERE course_id=? AND subject_id=? AND student_id =?";

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DatabaseConnection.connect();

			// ���� �� ���� ��ȣ �Է�
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

	// Total (��ü ��� ���� ��ȯ) �޼ҵ�
	public int total(String key, String value) {
		int result = 0;

		String sql = "";

		switch (key) {
		case "instructor_id":// ���� ����/����, ����_����������
			sql = "SELECT COUNT(*) AS \"total\" FROM inst_sub_view isv, points p"
					+ " WHERE isv.subject_id = p.subject_id(+) AND isv.instructor_id = ?";
			break;
		case "subject_id":// ���� ����_����������
			sql += "SELECT COUNT(*) AS \"total\" FROM inst_st_view isv, grade g "
					+ "WHERE isv.student_id = g.student_id(+) AND isv.course_id = g.course_id(+) "
					+ "AND isv.subject_id = g.subject_id(+) AND isv.subject_id = ?";
			break;
		case "instructor_schedule":// ���� ������
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
			case "instructor_id":// ���� ����/����, ����_����������
			case "subject_id":// ���� ����_����������
			case "instructor_schedule":// ���� ������	
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