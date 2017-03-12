package com.test;

import java.text.*;
import java.util.*;

public class Util {

	public static void dateCheck(String date) throws ParseException {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);
		sdf.parse(date); // ���� �߻�
	}

	public static void hourCheck(String date) throws ParseException {
		// 00 ~ 23
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH");
		sdf.setLenient(false);
		sdf.parse(date); // ���� �߻�
	}

	public static void lengthCheck(String content, int size) throws Exception {
		if (content.length() > size) {
			throw new Exception();
		}
	}

	public static void pressAnyKey(java.util.Scanner sc) {
		System.out.println();
		System.out.print("press any key to continue....");
		sc.nextLine(); // EnterŰ ����
	}

	public static void grammarCheck(String grammar, String content) throws Exception {

	}

	// ��½� ���� �ѱ� ���� ���߱�
	public static String stringBlank(int size, String str) {
		// ���ϴ� �׸��� ������ ����.
		// 1. '����ϴ� ��� ����'�� '���ڿ�'�� �ܺηκ��� �Ű������� �޾�
		// 2. '���ڿ�'�� byte ���̿� '����ϴ� ��� ����'�� ���Ͽ�
		// 3. ���� ���̸�ŭ ������ �����Ͽ�
		// 4. ���ڿ� ��, Ȥ�� �ڿ� �ٿ��� ��ȯ�Ѵ�.

		String result = str;
		int byteSize = result.getBytes().length;
		int length = size - byteSize;
		if (length < 0) {
			result = "�۾��� �ʹ� ŭ";
		}
		for(int i = 0; i < length; ++i) {
			result += " ";
		}
		return result;
	}
	/*
	 * 00�� ���۳�¥, ����¥ �Է� �� �ùٸ��� ���� ��¥(���۳�¥�� ����¥�� �ռ��� ���)�� �����ϴ� �޼ҵ带 UtilŬ������
	 * �־����. �����ϼ���~
	 */
	public static void date_Start_End_Check(String start_date, String end_date) throws Exception {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date1 = sdf.parse(start_date);
		java.util.Date date2 = sdf.parse(end_date);

		long result1 = date1.getTime();
		long result2 = date2.getTime();

		if (result1 > result2) {
			throw new Exception();
		}
	}
	// �ùٸ��� ���ϸ� ���� �߻�

	// ���ǽǸ� �Է� �ߺ�üũ
	public static void classroomUniqueCheck(String value) throws Exception {
		AdminBasicDAO dao = new AdminBasicDAO();
		List<AllList> list = dao.classroomList("all", "");
		for (AllList cr : list) {
			if (value.equals(cr.getClass_name())) {
				throw new Exception();
			}
		}
	}

	// ����� �Է� �ߺ� üũ
	public static void textbookUniqueCheck(String value) throws Exception {
		AdminBasicDAO dao = new AdminBasicDAO();
		List<AllList> list = dao.textbookList("all", "");
		for (AllList text : list) {
			if (value.equals(text.getText_name())) {
				throw new Exception();
			}
		}
	}

	// ����� �ߺ�üũ
	public static void subjectNameUniqueCheck(String value) throws Exception {
		AdminBasicDAO dao = new AdminBasicDAO();
		List<AllList> list = dao.subjectNameList("all", "");
		for (AllList sub : list) {
			if (value.equals(sub.getSub_name())) {
				throw new Exception();
			}
		}
	}

	// ������ �ߺ�üũ
	public static void courseNameUniqueCheck(String value) throws Exception {
		AdminBasicDAO dao = new AdminBasicDAO();
		List<AllList> list = dao.courseNameList("all", "");
		for (AllList course : list) {
			if (value.equals(course.getCou_name())) {
				throw new Exception();
			}
		}
	}
	
	//value DATE�� �����ϰ� �������� �������� Check
	public static void dateMidCheck(String start, String value, String end) throws Exception{

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			sdf.setLenient(false);

			Date startDate = sdf.parse(start);
			Date valueDate = sdf.parse(value);
			Date endDate = sdf.parse(end);

			if (startDate.getTime() <= valueDate.getTime() && valueDate.getTime() <= endDate.getTime()) {
			} else {
				throw new Exception();
			}
	}
	
	//ID ���� �˻� �޼ҵ�
	public static void idListCheck(List<String> id_List, String id_value) throws Exception {
		//subject_name ���̺��� �ڷḦ �о�ͼ�
		//����ڰ� �Է��� ����� ��ȣ�� �����ϴ��� Ȯ��
		//->�������� ������ ���� �߻�
		boolean result = false;
		for (String sn : id_List) {
			if (sn.equals(id_value)){
				result = true;
			}
		}
		if (!result) {
			throw new Exception();
		}
	}
	
	//today_date�� start_date���� ���� ��� true ��ȯ
	public static boolean dateBeforeCheck(String today_date, String start_date) {
		boolean result = true;
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date1 = null;
		java.util.Date date2 = null;
		try {
			date1 = sdf.parse(today_date);
			date2 = sdf.parse(start_date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long result1 = date1.getTime();
		long result2 = date2.getTime();
		if (result1 > result2) {
			result = false;
		}
		return result;
	}
		
}