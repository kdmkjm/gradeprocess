package com.test;

import java.sql.*;
import java.util.*;

public class StudentsDAO {

	// 해당 key의 하위 목록 출력 메소드
	public List<AllList> subList(String value) {
		List<AllList> result = new ArrayList<AllList>();

		String sql = "SELECT course_id, cou_name, start_date, end_date, class_name, sub_count FROM st_cou_view WHERE course_id IN (SELECT course_id FROM history WHERE student_id = ?) ORDER BY course_id";

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, value);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				AllList a = new AllList();

				a.setCourse_id(rs.getString("course_id"));
				a.setCou_name(rs.getString("cou_name"));
				a.setCou_start_date(rs.getString("start_date"));
				a.setCou_end_date(rs.getString("end_date"));
				a.setSub_count(rs.getInt("sub_count"));
				a.setClass_name(rs.getString("class_name"));

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

	// 해당 학생의 과정과 과목을 선택하여 성적을 출력하는 메소드
	public List<AllList> studentGrade(String course_id, String student_id) {
		List<AllList> result = new ArrayList<AllList>();

		String sql = "SELECT sub_name, sub_start_date, sub_end_date, inst_name, po_attend, po_write, po_practice, gr_attend, gr_write, gr_practice, text_name, exam_date, examination, exam_zip, publisher FROM st_sub_view_final WHERE student_id = ? AND course_id = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DatabaseConnection.connect();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, student_id);
			pstmt.setString(2, course_id);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				AllList a = new AllList();

				a.setSub_name(rs.getString("sub_name"));
				a.setSub_start_date(rs.getString("sub_start_date"));
				a.setSub_end_date(rs.getString("sub_end_date"));
				a.setText_name(rs.getString("text_name"));
				a.setInst_name(rs.getString("inst_name"));
				a.setPo_attend(rs.getString("po_attend"));
				a.setPo_write(rs.getString("po_write"));
				a.setPo_practice(rs.getString("po_practice"));
				a.setGr_attend(rs.getString("gr_attend"));
				a.setGr_write(rs.getString("gr_write"));
				a.setGr_practice(rs.getString("gr_practice"));
				a.setExam_date(rs.getString("exam_date"));
				a.setExamination(rs.getString("examination"));
				a.setExam_zip(rs.getString("exam_zip"));
				a.setPublisher(rs.getString("publisher"));

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

}
