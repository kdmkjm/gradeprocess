package com.test;

import java.text.*;
import java.util.*;

public class Util {

	public static void dateCheck(String date) throws ParseException {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);
		sdf.parse(date); // 예외 발생
	}

	public static void hourCheck(String date) throws ParseException {
		// 00 ~ 23
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH");
		sdf.setLenient(false);
		sdf.parse(date); // 예외 발생
	}

	public static void lengthCheck(String content, int size) throws Exception {
		if (content.length() > size) {
			throw new Exception();
		}
	}

	public static void pressAnyKey(java.util.Scanner sc) {
		System.out.println();
		System.out.print("press any key to continue....");
		sc.nextLine(); // Enter키 포함
	}

	public static void grammarCheck(String grammar, String content) throws Exception {

	}

	// 출력시 영어 한글 공백 맞추기
	public static String stringBlank(int size, String str) {
		// 원하는 그림은 다음과 같다.
		// 1. '희망하는 출력 길이'와 '문자열'을 외부로부터 매개변수로 받아
		// 2. '문자열'의 byte 길이와 '희망하는 출력 길이'를 비교하여
		// 3. 비교한 차이만큼 공백을 생성하여
		// 4. 문자열 앞, 혹은 뒤에 붙여서 반환한다.

		String result = str;
		int byteSize = result.getBytes().length;
		int length = size - byteSize;
		if (length < 0) {
			result = "글씨가 너무 큼";
		}
		for(int i = 0; i < length; ++i) {
			result += " ";
		}
		return result;
	}
	/*
	 * 00의 시작날짜, 끝날짜 입력 시 올바르지 못한 날짜(시작날짜가 끝날짜를 앞서는 경우)를 방지하는 메소드를 Util클래스에
	 * 넣었어요. 참고하세요~
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
	// 올바르지 못하면 예외 발생

	// 강의실명 입력 중복체크
	public static void classroomUniqueCheck(String value) throws Exception {
		AdminBasicDAO dao = new AdminBasicDAO();
		List<AllList> list = dao.classroomList("all", "");
		for (AllList cr : list) {
			if (value.equals(cr.getClass_name())) {
				throw new Exception();
			}
		}
	}

	// 교재명 입력 중복 체크
	public static void textbookUniqueCheck(String value) throws Exception {
		AdminBasicDAO dao = new AdminBasicDAO();
		List<AllList> list = dao.textbookList("all", "");
		for (AllList text : list) {
			if (value.equals(text.getText_name())) {
				throw new Exception();
			}
		}
	}

	// 과목명 중복체크
	public static void subjectNameUniqueCheck(String value) throws Exception {
		AdminBasicDAO dao = new AdminBasicDAO();
		List<AllList> list = dao.subjectNameList("all", "");
		for (AllList sub : list) {
			if (value.equals(sub.getSub_name())) {
				throw new Exception();
			}
		}
	}

	// 과정명 중복체크
	public static void courseNameUniqueCheck(String value) throws Exception {
		AdminBasicDAO dao = new AdminBasicDAO();
		List<AllList> list = dao.courseNameList("all", "");
		for (AllList course : list) {
			if (value.equals(course.getCou_name())) {
				throw new Exception();
			}
		}
	}
	
	//value DATE가 시작일과 종료일의 사이인지 Check
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
	
	//ID 범위 검사 메소드
	public static void idListCheck(List<String> id_List, String id_value) throws Exception {
		//subject_name 테이블의 자료를 읽어와서
		//사용자가 입력한 과목명 번호가 존재하는지 확인
		//->존재하지 않으면 예외 발생
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
	
	//today_date가 start_date보다 작을 경우 true 반환
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