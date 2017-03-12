package com.test;

import java.sql.*;
import java.util.*;

public class AdminInstructorDAO {

	// ���� ���� �˻� �� ���
	public List<AllList> list1(String key, String key2, String value) {
		List<AllList> result = new ArrayList<>();

		String sql = "";

		switch (key) {
		case "instAll":// ���� ���� ���� - ��ü ���
			sql = "SELECT instructor_id, inst_name, inst_pw, phone, teachable_sub, (SELECT COUNT(DISTINCT course_id)"
				+ " FROM admin_sub_view WHERE instructor_id = inst.instructor_id) AS courseCount FROM admin_inst_view inst";
			break;
		/*case "instAll":// ���� ���� ���� - ��ü ���(�����)
			sql = "SELECT instructor_id, inst_name, inst_pw, phone, teachable_sub FROM admin_inst_view";
			break;*/
		case "subjectNameAll": // ���� ���� ���� - ����� ��ü ���
			sql = "SELECT subjectName_id, sub_name FROM subjectName";
			break;
		}
		
		switch (key2) {
		case "instAll":// ���� ���� ���� - ����ID���� ��ü ���
			sql += " ORDER BY instructor_id";
			break;
		case "sc_inst-id":// ���� ���� ���� - ����ID�� �˻� ���
			sql += " WHERE INSTR(LOWER(instructor_id), LOWER(?)) > 0"
				+ " ORDER BY instructor_id";
			break;
		case "sc_inst-name":// ���� ���� ���� - ����ID�� �˻� ���
			sql += " WHERE INSTR(LOWER(inst_name), LOWER(?)) > 0"
				+ " ORDER BY instructor_id";
			break;
		case "subjectNameAll":// ���� ���� ���� - ����� ��ü ���
			sql += " ORDER BY subjectName_id";
			break;
		case "all_search_instructorId":// ���� ���� ���� - ����ID���� ��ü���
			sql += " WHERE instructorId = ?";
			break;
		}

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);

			switch (key2) {
			case "all_search_instructorId":// ����ID
			case "sc_inst-id":// ����ID�� �˻�
			case "sc_inst-name":// ��������� �˻�
				pstmt.setString(1, value);
				break;
			}
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				AllList inst = new AllList();
				if (key.equals("instAll")||key.equals("sc_inst-id")||key.equals("sc_inst-name")) {
					inst.setInstructor_id(rs.getString("instructor_id"));//����id
					inst.setInst_name(rs.getString("inst_name"));//�����
					inst.setInst_pw(rs.getInt("inst_pw"));//�����ȣ
					inst.setInst_phone(rs.getString("phone"));//������ȭ��ȣ
					inst.setSub_name(rs.getString("teachable_sub"));//���� ���� ����
					inst.setCou_count(Integer.parseInt(rs.getString("courseCount")));//���� Ƚ��
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
		case "instId_course":// ���� ���� ����2 - ����ID�� �������� ���
			sql = "SELECT sub_name ,sub_start_date ,sub_end_date, course_id, cou_name, cou_start_date, cou_end_date, text_name, class_name, progress"
					+ " FROM admin_sub_view";
			break;
		case "instId_courseId_sub":// ���� ���� ����2 - ����ID�� ����ID�� �������� ���
			sql = "SELECT subject_id, sub_name ,sub_start_date ,sub_end_date, text_name"
					+ " FROM admin_sub_view"
					+ " WHERE instructor_id = ? AND course_id = ?";
			break;
		case "sc_course-id":// ���� ���� ����2 - ����ID�� �˻��� ����ID�� �˻� ���
			sql = "SELECT sub_name ,sub_start_date ,sub_end_date, course_id, cou_name, cou_start_date, cou_end_date, text_name, class_name, progress"
					+ " FROM admin_sub_view WHERE INSTR(LOWER(course_id), LOWER(?)) > 0 AND instructor_id = ?";
			break;
		case "sc_course-name":// ���� ���� ����2 - ����ID�� �˻��� ����ID�� �˻� ���
			sql = "SELECT sub_name ,sub_start_date ,sub_end_date, course_id, cou_name, cou_start_date, cou_end_date, text_name, class_name, progress"
					+ " FROM admin_sub_view WHERE INSTR(LOWER(cou_name), LOWER(?)) > 0 AND instructor_id = ?";
			break;
		}
		// key2
		switch (key2) {
		case "instId_course":// ���� ���� ����2 - ����ID�� �������� ���
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
			case "instId_course":// ���� ���� ����2 - ����ID�� �������� ���
				pstmt.setString(1, value);
				break;
			case "instId_courseId_sub":// ���� ���� ���� 2- ����ID�� ����ID�� �������� ���
			case "sc_course-id":// ���� ���� ���� 2- ����ID�� ����ID�� �������� ���
			case "sc_course-name":// ���� ���� ���� 2- ����ID�� ������� �������� ���
				pstmt.setString(1, key2);
				pstmt.setString(2, value);
				break;
			}
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				AllList inst = new AllList();
				if (key.equals("instId_course")||key.equals("sc_course-id")||key.equals("sc_course-name")) {
					inst.setSub_name(rs.getString("sub_name"));//�����
					inst.setCou_name(rs.getString("cou_name"));//������
					inst.setCourse_id(rs.getString("course_id"));//����ID
					inst.setClass_name(rs.getString("class_name"));//���ǽǸ�
					inst.setCou_start_date(rs.getString("cou_start_date"));//��������
					inst.setCou_end_date(rs.getString("cou_end_date"));//��������
					inst.setProgress_ox(rs.getString("progress"));//�������࿩��
					inst.setSub_start_date(rs.getString("sub_start_date"));//���� ����
					inst.setSub_end_date(rs.getString("sub_end_date"));//���� ����
					inst.setText_name(rs.getString("text_name"));//�����
				}
				if (key.equals("instId_courseId_sub")) {
					inst.setSubject_id(rs.getString("subject_id"));//����ID
					inst.setSub_name(rs.getString("sub_name"));//�����
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
	// Total (��ü ��� ���� ��ȯ) �޼ҵ�
	public int total(String key, String value) {
		int result = 0;
		String sql = "";
		// key
		switch (key) {
		case "instAll":  // ���� ���� ���� - ���� ��ü ���
			sql = "SELECT COUNT(*) AS \"total\" FROM admin_inst_view";
			break;
		case "instId_course": // ���� ���� ����2 - ����ID�� �������� ���
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

	 
	// ���� ���� �߰�
	public int add(AllList inst, List<String> subjects_available) {
		int result = 0;
		//id �ڵ�����
		String sql1 = "SELECT instructor_id FROM instructorSeqView";
		String sql2 = "INSERT INTO instructor (instructor_id, inst_name, inst_pw, phone) VALUES (?, ?, ?, ?)";
		String sql3 = "INSERT INTO teachable_sub (instructor_id, subjectName_id) VALUES (?, ?)";
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		try {
			conn = DatabaseConnection.connect();
			//Ʈ����� ó��
			conn.setAutoCommit(false);
			//���� �ű� ��ȣ ��� ����
			String instructor_id = "inst_000";
			pstmt1 = conn.prepareStatement(sql1);
			ResultSet rs = pstmt1.executeQuery();
			while(rs.next()) {
				instructor_id = rs.getString("instructor_id");
			}
			rs.close();
			//���� ���� ���� �Է� ����
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, instructor_id);
			pstmt2.setString(2, inst.getInst_name());
			pstmt2.setInt(3, inst.getInst_pw());
			pstmt2.setString(4, inst.getInst_phone());
			pstmt2.executeUpdate();
			//���� ���� ���� �Է� ����
			pstmt3 = conn.prepareStatement(sql3);
			for (String subjectName_id : subjects_available) {
				pstmt3.setString(1, instructor_id);
				pstmt3.setString(2, subjectName_id);
				System.out.println(subjectName_id);
				pstmt3.executeUpdate();
			}
			//Ʈ����� ó��
			conn.commit();
			result = 1;
		} catch (ClassNotFoundException | SQLException e) {
			//Ʈ����� ó��
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.out.println("�߸� 1");
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
				System.out.println("�߸� 2");
			}
			try {
				DatabaseConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("�߸� 3");
			}
		}
		return result;
	}
	// ���� ���� ����
	public int remove(String key, String value) {
		int result = 0;
		String sql = "DELETE FROM";

		switch (key) {
		case "instructor_id":// ����ID
			sql += " instructor WHERE instructor_id = ?";
			break;
		}
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DatabaseConnection.connect();
			//Ʈ����� ó��
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			switch (key) {
			case "instructor_id":// ����ID
				pstmt.setString(1, value);
				break;
			}
			result = pstmt.executeUpdate();
			//Ʈ����� ó��
			conn.commit();
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("������ ���ǰ� �־� ������ �Ұ��� �մϴ�.");
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
	
	// ���� ���� ����
	public int modify(AllList inst, List<String> subjects_available) {
		int result = 0;
		String sql = "DELETE FROM teachable_sub WHERE instructor_id = ?";// ���� ���ǰ��ɰ��� ����
		String sql2 = "UPDATE instructor SET inst_name=?, phone=? WHERE instructor_id=?";// �������� ����
		String sql3 = "INSERT INTO teachable_sub (instructor_id, subjectName_id) VALUES (?, ?)";// ���ǰ��ɰ��� �߰�
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		try {
			conn = DatabaseConnection.connect();
			//Ʈ����� ó��
			conn.setAutoCommit(false);
			String instructor_id = inst.getInstructor_id();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, instructor_id);
			result = pstmt.executeUpdate();
			
			//���� ���� ���� ���� ����
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, inst.getInst_name());
			pstmt2.setString(2, inst.getInst_phone());
			pstmt2.setString(3, instructor_id);
			pstmt2.executeUpdate();
			
			//���� ���� ���� �Է� ����
			pstmt3 = conn.prepareStatement(sql3);
			for (String subjectName_id : subjects_available) {
				pstmt3.setString(1, instructor_id);
				pstmt3.setString(2, subjectName_id);
				System.out.println(subjectName_id);
				pstmt3.executeUpdate();
			}
			//Ʈ����� ó��
			conn.commit();
			result = 1;
		} catch (ClassNotFoundException | SQLException e) {
			//Ʈ����� ó��
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.out.println("�߸� 1");
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
				System.out.println("�߸� 2");
			}
			try {
				DatabaseConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("�߸� 3");
			}
		}
		return result;
	}
		
	/*
	// ���� ���� �߰�
	public int modifyInst(AllList inst, List<String> subjects_available) {
		int result = 0;
		//id �ڵ�����
		String sql2 = "UPDATE instructor SET inst_name=?, phone=? WHERE instructor_id=?";
		Connection conn = null;
		PreparedStatement pstmt2 = null;
		try {
			conn = DatabaseConnection.connect();
			//Ʈ����� ó��
			conn.setAutoCommit(false);
			
			String instructor_id = inst.getInstructor_id();
			
			//���� ���� ���� ���� ����
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setString(1, inst.getInst_name());
			pstmt2.setString(2, inst.getInst_phone());
			pstmt2.setString(3, instructor_id);
			pstmt2.executeUpdate();
			
			//Ʈ����� ó��
			conn.commit();
			result = 1;
		} catch (ClassNotFoundException | SQLException e) {
			//Ʈ����� ó��
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.out.println("�߸� 1");
			}
			e.printStackTrace();
		} finally {
			try {
				if (pstmt2 != null)
					pstmt2.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("�߸� 2");
			}
			try {
				DatabaseConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("�߸� 3");
			}
		}
		return result;
	}
	
	// ���� ���� ����
	public int modifyRemoveSub(String value) {
		int result = 0;
		String sql = "DELETE FROM teachable_sub WHERE instructor_id = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DatabaseConnection.connect();
			//Ʈ����� ó��
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, value);
			result = pstmt.executeUpdate();
			//Ʈ����� ó��
			conn.commit();
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("������ ���ǰ� �־� ������ �Ұ��� �մϴ�.");
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
	
	// ���� ���� �߰�
	public int modifySubAdd(AllList inst, List<String> subjects_available) {
		int result = 0;
		//id �ڵ�����
		String sql3 = "INSERT INTO teachable_sub (instructor_id, subjectName_id) VALUES (?, ?)";
		Connection conn = null;
		PreparedStatement pstmt3 = null;
		try {
			conn = DatabaseConnection.connect();
			//Ʈ����� ó��
			conn.setAutoCommit(false);
			//���� ���� ���� �Է� ����
			String instructor_id = inst.getInstructor_id();
			pstmt3 = conn.prepareStatement(sql3);
			for (String subjectName_id : subjects_available) {
				pstmt3.setString(1, instructor_id);
				pstmt3.setString(2, subjectName_id);
				System.out.println(subjectName_id);
				pstmt3.executeUpdate();
			}
			//Ʈ����� ó��
			conn.commit();
			result = 1;
		} catch (ClassNotFoundException | SQLException e) {
			//Ʈ����� ó��
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.out.println("�߸� 1");
			}
			e.printStackTrace();
		} finally {
			try {
				if (pstmt3 != null)
					pstmt3.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("�߸� 2");
			}
			try {
				DatabaseConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("�߸� 3");
			}
		}
		return result;
	}
	 */
	
}
