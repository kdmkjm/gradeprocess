package com.test;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.oreilly.servlet.*;

public class Action {
	/*-----------------------------------*/
	/*              login                */
	/*-----------------------------------*/
	// login_student.do
	public String login_student(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("login_student �޼ҵ� ȣ��");

		// ��� ��ȯ->������ �̵� ����->JSP ������
		return "WEB-INF/source/student/login_student.jsp"; // loginform.jsp
	}
	// login_inst.do
	public String login_inst(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("login_inst �޼ҵ� ȣ��");

		// ��� ��ȯ->������ �̵� ����->JSP ������
		return "WEB-INF/source/instructor/login_inst.jsp"; // loginform.jsp
	}

	// login_admin.do
	public String login_admin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("login_admin �޼ҵ� ȣ��");

		// ��� ��ȯ->������ �̵� ����->JSP ������
		return "WEB-INF/source/admin/login_admin.jsp"; // loginform.jsp
	}


	// login.do
	public String login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("login �޼ҵ� ȣ��");

		request.setCharacterEncoding("UTF-8");

		String url = "";

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");

		LoginDAO dao = new LoginDAO();
		String result = dao.login(id, pw);

		System.out.println(result);
		if (result == null) {

			url = "redirect:loginfail.do";
		} else {

			if(id.contains("inst")){
				HttpSession session1 = request.getSession();
				session1.setAttribute("instloginkey", result);
				session1.setAttribute("instid", id);
				url = "redirect:inst_schedule_all.do";
			} else if(id.contains("st")){
				HttpSession session2 = request.getSession();
				session2.setAttribute("stloginkey", result);
				session2.setAttribute("stid", id);
				url = "redirect:student_score_list.do";
			} else{
				HttpSession session = request.getSession();
				session.setAttribute("loginkey", result);
				session.setAttribute("id", id);
				url = "redirect:course_info.do";			
			}
		}

		// ��� ��ȯ->������ �̵� ����->���� �ּ�
		return url; //
	}


	// logout.do
	public String logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("logout �޼ҵ� ȣ��");

		HttpSession session = request.getSession();

		session.removeAttribute("loginkey");
		session.removeAttribute("id");


		// ��� ��ȯ->������ �̵� ����->���� �ּ�
		return "redirect:login_student.do"; //
	}

	// logout_inst.do
	public String logout_inst(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("logout_inst �޼ҵ� ȣ��");

		HttpSession session1 = request.getSession();

		session1.removeAttribute("instloginkey");
		session1.removeAttribute("instid");


		// ��� ��ȯ->������ �̵� ����->���� �ּ�
		return "redirect:login_student.do"; //
	}

	// logout_st.do
	public String logout_st(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("logout_st �޼ҵ� ȣ��");

		HttpSession session2 = request.getSession();

		session2.removeAttribute("stloginkey");
		session2.removeAttribute("stid");

		// ��� ��ȯ->������ �̵� ����->���� �ּ�
		return "redirect:login_student.do"; //
	}

	// loginfail.do
	public String loginfail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("loginfail �޼ҵ� ȣ��");

		// ��� ��ȯ->������ �̵� ����->JSP ������
		return "WEB-INF/source/loginfail.jsp"; // loginfail.jsp
	}

	// error.do
	public String error(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("error �޼ҵ� ȣ��");

		// ��� ��ȯ->������ �̵� ����->JSP ������
		return "WEB-INF/source/error.jsp"; // error.jsp
	}
	
	// adminpwmodifyform.do
	public String adminpwmodifyform(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String loginkey = (String) session.getAttribute("loginkey");
		String url = "";

		if (loginkey == null) {
			// �α��� ���� ���� ������� ����
			url = "redirect:error.do";

		} else {

			// ������ ���� -> employeeId
			// �����ͺ��̽� �׼� -> employeeList() �޼ҵ�
			System.out.println("adminpwmodifyform �޼ҵ� ȣ��");

			request.setCharacterEncoding("UTF-8");
			
			LoginDAO dao = new LoginDAO();
			List<AllList> list = dao.pwCheck("admin", loginkey);

			
			// ��� ���� -> Employee ��ü
			request.setAttribute("all", list.get(0));

			url = "WEB-INF/source/admin/adminpwmodifyform.jsp";
		}

		// ��� ��ȯ->������ �̵� ����->���� �ּ�
		return url;
	}
	
	// adminpwmodify.do
	public String adminpwmodify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String loginkey = (String) session.getAttribute("loginkey");
		String url = "";

		if (loginkey == null) {
			// �α��� ���� ���� ������� ����
			url = "redirect:error.do";

		} else {

			// ������ ���� -> employeeId
			// �����ͺ��̽� �׼� -> employeeList() �޼ҵ�
			System.out.println("adminpwmodify �޼ҵ� ȣ��");

			request.setCharacterEncoding("UTF-8");

			String pw = request.getParameter("pw");
			String newpw = request.getParameter("newpw");
			String newpw1 = request.getParameter("newpw1");

			LoginDAO dao = new LoginDAO();
			List<AllList> list = dao.pwCheck("admin", loginkey);

			AllList all = list.get(0);
			String pw1 = String.valueOf(all.getAdmin_pw());
			System.out.println(pw1);
			System.out.println(loginkey);
			System.out.println(pw);
			System.out.println(newpw);
			System.out.println(newpw1);

			if (pw.equals(pw1) && newpw.equals(newpw1)) {
				all.setAdmin_pw(Integer.parseInt(newpw));
				dao.pwModify(all, "admin");
				url = "WEB-INF/source/adminpwmodifysuccess.jsp";
			} else {
				url = "WEB-INF/source/adminpwmodifyfail.jsp";
			}

			request.setAttribute("loginkey", loginkey);

		}

		// ��� ��ȯ->������ �̵� ����->���� �ּ�
		return url;
	}
	
	
	// instpwmodifyform.do
	public String instpwmodifyform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String instloginkey = (String) session.getAttribute("instloginkey");
		String url = "";

		if (instloginkey == null) {
			// �α��� ���� ���� ������� ����
			url = "redirect:error.do";

		} else {

			// ������ ���� -> employeeId
			// �����ͺ��̽� �׼� -> employeeList() �޼ҵ�
			System.out.println("instpwmodifyform �޼ҵ� ȣ��");

			request.setCharacterEncoding("UTF-8");

			HttpSession session1 = request.getSession();
			String instructorid = (String) session1.getAttribute("instid");

			LoginDAO dao = new LoginDAO();
			List<AllList> list = dao.pwCheck("instructor", instructorid);

			
			// ��� ���� -> Employee ��ü
			request.setAttribute("all", list.get(0));

			url = "WEB-INF/source/instructor/instpwmodifyform.jsp";
		}

		// ��� ��ȯ->������ �̵� ����->���� �ּ�
		return url;
	}
	
	// instpwmodify.do
	public String instpwmodify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String instloginkey = (String) session.getAttribute("instloginkey");
		String url = "";

		if (instloginkey == null) {
			// �α��� ���� ���� ������� ����
			url = "redirect:error.do";

		} else {

			// ������ ���� -> employeeId
			// �����ͺ��̽� �׼� -> employeeList() �޼ҵ�
			System.out.println("instpwmodify �޼ҵ� ȣ��");

			request.setCharacterEncoding("UTF-8");

			HttpSession session1 = request.getSession();
			String instructorid = (String) session1.getAttribute("instid");
			String pw = request.getParameter("pw");
			String newpw = request.getParameter("newpw");
			String newpw1 = request.getParameter("newpw1");

			LoginDAO dao = new LoginDAO();
			List<AllList> list = dao.pwCheck("instructor", instructorid);

			AllList all = list.get(0);
			String pw1 = String.valueOf(all.getInst_pw());
			System.out.println(pw1);
			System.out.println(instructorid);
			System.out.println(pw);
			System.out.println(newpw);
			System.out.println(newpw1);

			if (pw.equals(pw1) && newpw.equals(newpw1)) {
				all.setInst_pw(Integer.parseInt(newpw));
				dao.pwModify(all, "inst");
				url = "WEB-INF/source/instpwmodifysuccess.jsp";
			} else {
				url = "WEB-INF/source/instpwmodifyfail.jsp";
			}

			request.setAttribute("instructorid", instructorid);

		}

		// ��� ��ȯ->������ �̵� ����->���� �ּ�
		return url;
	}
	
	// stpwmodifyform.do
	public String stpwmodifyform(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String stloginkey = (String) session.getAttribute("stloginkey");
		String url = "";

		if (stloginkey == null) {
			// �α��� ���� ���� ������� ����
			url = "redirect:error.do";

		} else {

			// ������ ���� -> employeeId
			// �����ͺ��̽� �׼� -> employeeList() �޼ҵ�
			System.out.println("stpwmodifyform �޼ҵ� ȣ��");

			request.setCharacterEncoding("UTF-8");

			String studentid = request.getParameter("stId");
			System.out.println(studentid);

			LoginDAO dao = new LoginDAO();
			List<AllList> list = dao.pwCheck("student", studentid);

			// ��� ���� -> Employee ��ü
			request.setAttribute("all", list.get(0));
			

			url = "WEB-INF/source/student/stpwmodifyform.jsp";
		}

		// ��� ��ȯ->������ �̵� ����->���� �ּ�
		return url;
	}
	
	// stpwmodify.do
	public String stpwmodify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String stloginkey = (String) session.getAttribute("stloginkey");
		String url = "";

		if (stloginkey == null) {
			// �α��� ���� ���� ������� ����
			url = "redirect:error.do";

		} else {

			// ������ ���� -> employeeId
			// �����ͺ��̽� �׼� -> employeeList() �޼ҵ�
			System.out.println("stpwmodify �޼ҵ� ȣ��");

			request.setCharacterEncoding("UTF-8");

			String studentid = request.getParameter("studentid");
			String pw = request.getParameter("pw");
			String newpw = request.getParameter("newpw");
			String newpw1 = request.getParameter("newpw1");
			
			
			LoginDAO dao = new LoginDAO();
			List<AllList> list = dao.pwCheck("student", studentid);

			AllList all = list.get(0);
			String pw1 = String.valueOf(all.getSt_pw());
			System.out.println(pw1);
			System.out.println(studentid);
			System.out.println(pw);
			System.out.println(newpw);
			System.out.println(newpw1);
			
			if(pw.equals(pw1) && newpw.equals(newpw1)){
				all.setSt_pw(Integer.parseInt(newpw));
				dao.pwModify(all, "student");
				url = "WEB-INF/source/stpwmodifysuccess.jsp";
			} else {
				url = "WEB-INF/source/stpwmodifyfail.jsp";
			}
			
			request.setAttribute("studentid", studentid);
			
		}

		// ��� ��ȯ->������ �̵� ����->���� �ּ�
		return url;
	}



	/*-----------------------------------*/
	/*            course_info            */
	/*-----------------------------------*/
	public String course_info(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String loginkey = (String) session.getAttribute("loginkey");
		String url = "";
		if (loginkey == null) {
			url = "redirect:error.do";
		} else {

			request.setCharacterEncoding("UTF-8");

			System.out.println("course_info �޼ҵ� ȣ��");

			String total = "0";
			String count = "0";

			List<AllList> list = null;
			AdminBasicDAO dao = new AdminBasicDAO();

			String skey = request.getParameter("skey");
			String svalue = request.getParameter("svalue");

			if (svalue == null) {
				skey = "all";
				svalue = "";
			}
			list = dao.courseNameList(skey, svalue);
			total = String.valueOf(dao.courseNameTotal());
			count = String.valueOf(list.size());

			request.setAttribute("list", list);
			request.setAttribute("skey", skey);
			request.setAttribute("svalue", svalue);
			request.setAttribute("total", total);
			request.setAttribute("count", count);

			url = "WEB-INF/source/admin/course_info.jsp";
		}

		// ��� ��ȯ->������ �̵� ����->JSP ������
		return url; // .jsp
	}

	public String couNameAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String loginkey = (String) session.getAttribute("loginkey");
		String url = "";

		if(loginkey == null) {
			url = "redirect:error.do";
		} else {

			request.setCharacterEncoding("UTF-8");

			System.out.println("couNameAdd �޼ҵ� ȣ��");

			String cou_name = request.getParameter("addName");
			System.out.println(cou_name);

			AllList a = new AllList();
			a.setCou_name(cou_name);

			AdminBasicDAO dao = new AdminBasicDAO();
			dao.courseNameAdd(a);

			url = "redirect:course_info.do";
		}

		//��� ��ȯ->������ �̵� ����->���� �ּ�
		return url; //.do
	}

	public String couNameModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String loginkey = (String) session.getAttribute("loginkey");
		String url = "";

		if (loginkey == null) {
			url = "redirect:error.do";
		} else {

			request.setCharacterEncoding("UTF-8");

			System.out.println("couNameModify �޼ҵ� ȣ��");

			String courseName_id = request.getParameter("updateNid");
			String cou_name = request.getParameter("updateName");

			AllList a = new AllList();
			a.setCourseName_id(courseName_id);
			a.setCou_name(cou_name);

			AdminBasicDAO dao = new AdminBasicDAO();
			dao.courseNameModify(a);

			url = "redirect:course_info.do";
		}

		//��� ��ȯ->������ �̵� ����->���� �ּ�
		return url; //.do
	}

	public String couNameRemove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String loginkey = (String) session.getAttribute("loginkey");
		String url = "";

		if (loginkey == null) {
			url = "redirect:error.do";
		} else {

			request.setCharacterEncoding("UTF-8");

			System.out.println("couNameRemove �޼ҵ� ȣ��");

			String courseName_id = request.getParameter("deleteNid");
			String cou_name = request.getParameter("deleteName");

			AllList a = new AllList();
			a.setCourseName_id(courseName_id);
			a.setCou_name(cou_name);

			AdminBasicDAO dao = new AdminBasicDAO();
			dao.courseNameDelete(a);

			url = "redirect:course_info.do";
		}

		//��� ��ȯ->������ �̵� ����->���� �ּ�
		return url; //.do
	}

	/*-----------------------------------*/
	/*            subject_info            */
	/*-----------------------------------*/
	public String subject_info(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String loginkey = (String) session.getAttribute("loginkey");
		String url = "";
		if (loginkey == null) {
			url = "redirect:error.do";
		} else {

			request.setCharacterEncoding("UTF-8");

			System.out.println("subject_info �޼ҵ� ȣ��");

			String total = "0";
			String count = "0";

			List<AllList> list = null;

			AdminBasicDAO dao = new AdminBasicDAO();

			String skey = request.getParameter("skey");
			String svalue = request.getParameter("svalue");

			if (skey == null) {
				skey = "all";
				svalue = "";
			}

			list = dao.subjectNameList(skey, svalue);
			total = String.valueOf(dao.subjectNameTotal());
			count = String.valueOf(list.size());

			request.setAttribute("list", list);
			request.setAttribute("total", total);
			request.setAttribute("count", count);
			request.setAttribute("skey", skey);
			request.setAttribute("svalue", svalue);

			url = "WEB-INF/source/admin/subject_info.jsp";
		}
		// ��� ��ȯ->������ �̵� ����->JSP ������
		return url; // .jsp
	}

	public String subNameAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String loginkey = (String) session.getAttribute("loginkey");
		String url = "";

		if(loginkey == null) {
			url = "redirect:error.do";
		} else {

			request.setCharacterEncoding("UTF-8");

			System.out.println("subNameAdd �޼ҵ� ȣ��");

			String sub_name = request.getParameter("addName");

			AllList a = new AllList();
			a.setSub_name(sub_name);

			AdminBasicDAO dao = new AdminBasicDAO();
			dao.subjectNameAdd(a);

			url = "redirect:subject_info.do";
		}

		//��� ��ȯ->������ �̵� ����->���� �ּ�
		return url; //.do
	}
	public String subNameModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String loginkey = (String) session.getAttribute("loginkey");
		String url = "";

		if(loginkey == null) {
			url = "redirect:error.do";
		} else {

			request.setCharacterEncoding("UTF-8");

			System.out.println("subNameModify �޼ҵ� ȣ��");

			String subjectName_id = request.getParameter("updateNid");
			String sub_name = request.getParameter("updateName");

			AllList a = new AllList();
			a.setSubjectName_id(subjectName_id);
			a.setSub_name(sub_name);

			AdminBasicDAO dao = new AdminBasicDAO();
			dao.subjectNameModify(a);

			url = "redirect:subject_info.do";
		}

		//��� ��ȯ->������ �̵� ����->���� �ּ�
		return url; //.do
	}


	public String subNameRemove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String loginkey = (String) session.getAttribute("loginkey");
		String url = "";

		if (loginkey == null) {
			url = "redirect:error.do";
		} else {

			request.setCharacterEncoding("UTF-8");

			System.out.println("subNameRemove �޼ҵ� ȣ��");

			String subjectName_id = request.getParameter("deleteNid");
			String sub_name = request.getParameter("deleteName");

			AllList a = new AllList();
			a.setSubjectName_id(subjectName_id);
			a.setSub_name(sub_name);

			AdminBasicDAO dao = new AdminBasicDAO();
			dao.subjectNameDelete(a);

			url = "redirect:subject_info.do";
		} 

		//��� ��ȯ->������ �̵� ����->���� �ּ�
		return url; //.do
	}

	/*-----------------------------------*/
	/*            classroom_info            */
	/*-----------------------------------*/
	public String classroom_info(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String loginkey = (String) session.getAttribute("loginkey");
		String url = "";
		if (loginkey == null) {
			url = "redirect:error.do";
		} else {

			request.setCharacterEncoding("UTF-8");

			System.out.println("classroom_info �޼ҵ� ȣ��");

			String total = "0";
			String count = "0";

			List<AllList> list = null;
			AdminBasicDAO dao = new AdminBasicDAO();

			String skey = request.getParameter("skey");
			String svalue = request.getParameter("svalue");

			if (skey == null) {
				skey = "all";
				svalue = "";
			}

			list = dao.classroomList(skey, svalue);
			total = String.valueOf(dao.classroomTotal());
			count = String.valueOf(list.size());

			request.setAttribute("list", list);
			request.setAttribute("total", total);
			request.setAttribute("count", count);
			request.setAttribute("skey", skey);
			request.setAttribute("svalue", svalue);

			url = "WEB-INF/source/admin/classroom_info.jsp";
		}

		// ��� ��ȯ->������ �̵� ����->JSP ������
		return url; // .jsp
	}

	public String classroomAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String loginkey = (String) session.getAttribute("loginkey");
		String url = "";

		if (loginkey == null) {
			url = "redirect:error.do";
		} else {

			request.setCharacterEncoding("UTF-8");

			System.out.println("classroomAdd �޼ҵ� ȣ��");

			String class_name = request.getParameter("addName");
			String class_total = request.getParameter("addTotal");

			AllList a = new AllList();
			a.setClass_name(class_name);
			a.setClass_total(Integer.parseInt(class_total));

			AdminBasicDAO dao = new AdminBasicDAO();
			dao.classroomAdd(a);

			url = "redirect:classroom_info.do";
		}

		//��� ��ȯ->������ �̵� ����->���� �ּ�
		return url; //.do

	}


	public String classroomModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("classroomModify �޼ҵ� ȣ��1111");

		HttpSession session = request.getSession();
		String loginkey = (String) session.getAttribute("loginkey");
		String url = "";

		if (loginkey == null) {
			url = "redirect:error.do";
		} else {

			request.setCharacterEncoding("UTF-8");

			System.out.println("classroomModify �޼ҵ� ȣ��");

			String classroom_id = request.getParameter("updateId");
			String class_name = request.getParameter("updateName");
			String class_total = request.getParameter("updateTotal");

			AllList a = new AllList();
			a.setClassroom_id(classroom_id);
			a.setClass_name(class_name);
			a.setClass_total(Integer.parseInt(class_total));

			AdminBasicDAO dao = new AdminBasicDAO();
			dao.classroomModify(a);

			url = "redirect:classroom_info.do";
		}

		//��� ��ȯ->������ �̵� ����->���� �ּ�
		return url; //.do

	}

	public String classroomRemove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String loginkey = (String) session.getAttribute("loginkey");
		String url = "";

		if (loginkey == null) {
			url = "redirect:error.do";
		} else {

			request.setCharacterEncoding("UTF-8");

			System.out.println("classroomRemove �޼ҵ� ȣ��");

			String classroom_id = request.getParameter("deleteId");
			String class_name = request.getParameter("deleteName");
			String class_total = request.getParameter("deleteTotal");

			AllList a = new AllList();
			a.setClassroom_id(classroom_id);
			a.setClass_name(class_name);
			a.setClass_total(Integer.parseInt(class_total));

			AdminBasicDAO dao = new AdminBasicDAO();
			dao.classroomDelete(a);

			url = "redirect:classroom_info.do";
		}

		//��� ��ȯ->������ �̵� ����->���� �ּ�
		return url; //.do
	}

	/*-----------------------------------*/
	/*            textbook_info          */
	/*-----------------------------------*/
	public String textbook_info(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String loginkey = (String) session.getAttribute("loginkey");
		String url = "";
		if (loginkey == null) {
			url = "redirect:error.do";
		} else {

			request.setCharacterEncoding("UTF-8");

			System.out.println("textbook_info �޼ҵ� ȣ��");

			String total = "0";
			String count = "0";

			List<AllList> list = null;
			AdminBasicDAO dao = new AdminBasicDAO();

			String skey = request.getParameter("skey");
			String svalue = request.getParameter("svalue");

			if (skey == null) {
				skey = "all";
				svalue = "";
			}

			list = dao.textbookList(skey, svalue);
			total = String.valueOf(dao.textbookTotal());
			count = String.valueOf(list.size());

			request.setAttribute("list", list);
			request.setAttribute("total", total);
			request.setAttribute("count", count);
			request.setAttribute("skey", skey);
			request.setAttribute("svalue", svalue);
			url = "WEB-INF/source/admin/textbook_info.jsp";

		}

		// ��� ��ȯ->������ �̵� ����->JSP ������
		return url; // .jsp
	}

	public String textbookAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String loginkey = (String) session.getAttribute("loginkey");
		String url = "";

		if (loginkey == null) {
			url = "redirect:error.do";
		} else {

			request.setCharacterEncoding("UTF-8");

			System.out.println("textbook image �߰� ������");
			
			// ���� ���ε� �׼�
			try {
				
				String savePath = request.getServletContext().getRealPath("image");
				System.out.println(savePath);
				
				java.io.File f = new java.io.File(savePath);
				if (!f.exists()) {
					f.mkdir();
				}
				
				int sizeLimit = 1024 * 100; // 100K byte
				
				MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, "UTF-8", new MyFileRenamePolicy());
				
				String filesystemName = multi.getFilesystemName("file"); // ���� �̹��� ����
				String contentType = multi.getContentType("file");
				String originalFileName = multi.getOriginalFileName("file");
				
				if( !(contentType.equals("image/jpeg"))) {
					String deleteFileName = savePath + "\\" + filesystemName;
					
					java.io.File temp = new java.io.File(deleteFileName);
					temp.delete();
					System.out.println("deleteFilename:" + deleteFileName);
					
					throw new Exception("JPG �̹��� ������ �ƴϰų� �߸��� �̹����Դϴ�.");
				}

				String text_name = multi.getParameter("addName");
				String publisher = multi.getParameter("addPublisher");
				
				AllList a = new AllList();
				a.setText_name(text_name);
				a.setPublisher(publisher);
				a.setText_img(filesystemName);
				
				System.out.println("system file name:" + filesystemName);
				System.out.println("text_name:" + text_name);
				System.out.println("publisher:" + publisher);

				AdminBasicDAO dao = new AdminBasicDAO();
				System.out.println("textbookAdd �޼ҵ� ȣ��");
				int result = dao.textbookAdd(a);
				
				if (result == 0) {
					String deleteFileName = savePath + "\\" + filesystemName;
					java.io.File temp = new java.io.File(deleteFileName);
					temp.delete();
					throw new Exception("���ε�� ������ ������ �����ͺ��̽��� ������� ���߽��ϴ�.");
				}
				
				url = "redirect:textbook_info.do";
				
			} catch (Exception e) {
				url = "redirect:fileuploaderror.do";
				e.printStackTrace();
			}
		}

		//��� ��ȯ->������ �̵� ����->���� �ּ�
		return url; //.do
	}

	public String textbookModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String loginkey = (String) session.getAttribute("loginkey");
		String url = "";

		if (loginkey == null) {
			url = "redirect:error.do";
		} else {

			request.setCharacterEncoding("UTF-8");

			System.out.println("���� ���ε� ���� �׼� ������");
			
			try {
			// ���� ���ε� �׼�
			String savePath = request.getServletContext().getRealPath("image");
			System.out.println(savePath);
			
			java.io.File f = new java.io.File(savePath);
			if(!f.exists()) {
				f.mkdir();
			}
			
			int sizeLimit = 1024 * 100; // 100K byte
			
			MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, "UTF-8", new MyFileRenamePolicy());
			
			String filesystemName = multi.getFilesystemName("file");
			String contentType = multi.getContentType("file");
			String OriginalFileName = multi.getOriginalFileName("file");
			
			// String textbookId = multi.getParameter("textbookId");
			
			if (!contentType.equals("image/jpeg")) {
				String deleteFilename = savePath + "\\" + filesystemName;
				
				java.io.File temp = new java.io.File(deleteFilename);
				temp.delete();
				System.out.println("deleteFileName:" + deleteFilename);
				
				throw new Exception("JPG �̹��� ������ �ƴϰų� �߸��� �̹����Դϴ�.");
			}
			
			String textbook_id = multi.getParameter("updateId");
			String text_name = multi.getParameter("updateName");
			String publisher = multi.getParameter("updatePublisher");
			
			System.out.println(textbook_id);
			System.out.println(text_name);
			System.out.println(publisher);
			
			AllList a = new AllList();
			a.setTextbook_id(textbook_id);
			a.setText_name(text_name);
			a.setPublisher(publisher);
			a.setText_img(filesystemName);

			AdminBasicDAO dao = new AdminBasicDAO();
			System.out.println("textbookModify �޼ҵ� ȣ��");
			int result = dao.textbookModify(a);

			if (result == 0) {
				String deleteFileName = savePath + "\\" + filesystemName;
				java.io.File temp = new java.io.File(deleteFileName);
				temp.delete();
				throw new Exception("���ε�� ������ ������ �����ͺ��̽��� ������� ���߽��ϴ�.");
			}
			url = "redirect:textbook_info.do";			
			
			} catch(Exception e) {
				url = "redirect:fileuploaderror.do";
				e.printStackTrace();
			}
		}

		//��� ��ȯ->������ �̵� ����->���� �ּ�
		return url; //.do
	}

	public String textbookRemove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String loginkey = (String) session.getAttribute("loginkey");
		String url = "";

		if (loginkey == null) {
			url = "redirect:error.do";
		} else {

			request.setCharacterEncoding("UTF-8");

			System.out.println("textbookRemove �޼ҵ� ȣ��");

			String textbook_id = request.getParameter("deleteId");

			AllList a = new AllList();
			a.setTextbook_id(textbook_id);

			AdminBasicDAO dao = new AdminBasicDAO();
			List<AllList> list = dao.textbookList("textbook_id", textbook_id);
			String fileName = "";
			
			System.out.println("���� ���� ����:" + list.size());
			
			if (list.size() > 0) {
				fileName = list.get(0).getText_img();
			}
			
			System.out.println("textbookRemove �޼ҵ� ȣ��");
			int result = dao.textbookDelete(a);
			
			if (list.size() > 0 && result > 0) {
				String savePath = request.getServletContext().getRealPath("image");
				System.out.println(savePath);
				
				String deleteFileName = savePath + "\\" + fileName;
				java.io.File temp = new java.io.File(deleteFileName);
				temp.delete();
			}

			url = "redirect:textbook_info.do";
		}

		//��� ��ȯ->������ �̵� ����->���� �ּ�
		return url; //.do
	}

	
	// fileuploaderror.do
	public String fileuploaderror(HttpServletRequest request, HttpServletResponse response) throws IOException {

		request.setCharacterEncoding("UTF-8");

		System.out.println("fileupload �޼ҵ� ȣ��");

		return "WEB-INF/source/fileuploaderror.jsp";
	}
	

	/*-----------------------------------*/
	/*            instructor_list        */
	/*-----------------------------------*/
	public String instructor_list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String loginkey = (String) session.getAttribute("loginkey");
		String url = "";
		//if (loginkey == null) {
		//	url = "redirect:error.do";
		//} else {
		request.setCharacterEncoding("UTF-8");

		AdminInstructorDAO dao = new AdminInstructorDAO();
		String skey2 = request.getParameter("skey");
		String svalue = request.getParameter("svalue");
		String skey = "";
		int total = 0;
		List<AllList> list = null;
		skey = "instAll";
		if (svalue == null) {
			skey2 = "instAll";
			svalue = "";
		}
		list = dao.list1(skey, skey2, svalue);
		String count = String.valueOf(list.size());
		total = dao.total("instAll", "");

		AdminInstructorDAO subNameDao = new AdminInstructorDAO();
		List<AllList> subNamelist = null;
		subNamelist = subNameDao.list1("subjectNameAll", "subjectNameAll", "");

		request.setAttribute("list", list);
		request.setAttribute("count", count);
		request.setAttribute("total", total);
		request.setAttribute("skey", skey2);
		request.setAttribute("svalue", svalue);

		request.setAttribute("subNamelist", subNamelist);

		url = "WEB-INF/source/admin/instructor_list.jsp";
		//}
		// ��� ��ȯ->������ �̵� ����->JSP ������
		return url;// .jsp
	}

	public String instAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String name = request.getParameter("name");
		String pw = request.getParameter("pw");//UNIQUE �ߺ� �Ұ�
		String phone = request.getParameter("phone");
		String[] teacheSub = request.getParameterValues("subName_checkbox");// ���ǰ��ɰ���� �迭

		List<String> subjects_available = new ArrayList<String>();
		int len = teacheSub.length;
		for (int i = 0; i < len; ++i) {
			subjects_available.add(teacheSub[i]);
		}
		AllList inst = new AllList();
		inst.setInst_name(name);
		inst.setInst_pw(Integer.parseInt(pw));
		inst.setInst_phone(phone);

		AdminInstructorDAO dao = new AdminInstructorDAO();
		dao.add(inst, subjects_available);

		//��� ��ȯ->������ �̵� ����->���� �ּ�
		return "redirect:instructor_list.do"; //.do
	}
	public String instModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String[] teacheSub = request.getParameterValues("subName_checkbox");// ���ǰ��ɰ���� �迭

		List<String> subjects_available = new ArrayList<String>();
		int len = teacheSub.length;
		for (int i = 0; i < len; ++i) {
			subjects_available.add(teacheSub[i]);
		}
		AllList inst = new AllList();
		inst.setInstructor_id(id);
		inst.setInst_name(name);
		inst.setInst_phone(phone);
		AdminInstructorDAO dao = new AdminInstructorDAO();
		System.out.println("modify = "+dao.modify(inst, subjects_available));
		/*
		System.out.println("modifyRemoveSub = "+dao.modifyRemoveSub(id));
		System.out.println("modifySubAdd = "+dao.modifySubAdd(inst, subjects_available));
		System.out.println("modifyInst = "+dao.modifyInst(inst, subjects_available));
		 */
		//��� ��ȯ->������ �̵� ����->���� �ּ�
		return "redirect:instructor_list.do"; //.do
	}

	public String instRemove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String inst_id = request.getParameter("id");
		System.out.println(inst_id);
		AdminInstructorDAO dao = new AdminInstructorDAO();
		dao.remove("instructor_id", inst_id);

		//��� ��ȯ->������ �̵� ����->���� �ּ�
		return "redirect:instructor_list.do"; //.do
	}

	/*-----------------------------------*/
	/*            instructor_list2       */
	/*-----------------------------------*/
	public String instructor_list2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String loginkey = (String) session.getAttribute("loginkey");
		String url = "";
		if (loginkey == null) {
			url = "redirect:error.do";
		} else {

			request.setCharacterEncoding("UTF-8");

			String instId = request.getParameter("hidden_instId");
			String name = request.getParameter("hidden_instName");
			String phone = request.getParameter("hidden_instPhone");
			String sub_name = request.getParameter("hidden_instTeachable");

			String skey = request.getParameter("skey");
			String svalue = request.getParameter("svalue");
			String skey2 = "";
			int total = 0;
			if (svalue == null) {
				// ���� ���� ����2 - ����ID�� �������� ��ü ���
				skey = "instId_course";
				skey2 = "instId_course";
				svalue = "";
			} else {
				skey2 = svalue; // �˻���
			}
			AdminInstructorDAO dao = new AdminInstructorDAO();
			List<AllList> list = null;
			list = dao.list2(skey, skey2, instId);
			String count = String.valueOf(list.size());
			total = dao.total("instId_course", instId);
			String[] progressArr = { "���� ����", "���� ������", "���� ����" };

			request.setAttribute("titleInfo_id", instId);
			request.setAttribute("titleInfo_name", name);
			request.setAttribute("titleInfo_phone", phone);
			request.setAttribute("titleInfo_sub_name", sub_name);

			request.setAttribute("list", list);
			request.setAttribute("count", count);
			request.setAttribute("total", total);
			request.setAttribute("skey", skey);
			request.setAttribute("svalue", svalue);
			request.setAttribute("progressArr", progressArr);
			url = "WEB-INF/source/admin/instructor_list2.jsp";
		}

		// ��� ��ȯ->������ �̵� ����->JSP ������
		return url;// .jsp
	}

	public String inst_subjectajax(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		AdminInstructorDAO dao = new AdminInstructorDAO();
		List<AllList> list = null;
		String inst_id = request.getParameter("inst_id");
		String course_id = request.getParameter("course_id");
		list = dao.list2("instId_courseId_sub", inst_id, course_id);
		//JSON ��ü ����
		JSONObject subjects = new JSONObject();
		//JSON �迭 ��ü ����
		JSONArray subject = new JSONArray();
		//JSON ��ü ����
		for (AllList s : list) {
			JSONObject sub = new JSONObject();
			sub.put("subject_id", s.getSubject_id());
			sub.put("sub_name", s.getSub_name());
			sub.put("sub_start_date", s.getSub_start_date());
			sub.put("sub_end_date", s.getSub_end_date());
			sub.put("textbook_name", s.getText_name());
			subject.add(sub);
		}
		subjects.put("subject", subject);
		String result = subjects.toJSONString();

		request.setAttribute("result", result);

		//��� ��ȯ->������ �̵� ����->JSP ������
		return "WEB-INF/source/admin/ajax.jsp"; //.jsp
	}

	/*-----------------------------------*/
	/*            course_list            */
	/*-----------------------------------*/
	public String course_list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String loginkey = (String) session.getAttribute("loginkey");
		String url = "";
		//if (loginkey == null) {
		//	url = "redirect:error.do";
		//} else {

			request.setCharacterEncoding("UTF-8");
			System.out.println("course_list �޼ҵ� ȣ��");
			String skey2 = request.getParameter("skey");
			String svalue = request.getParameter("svalue");
			
			String page = request.getParameter("page");
			int pagePerCount = 13;
			if (page == null) {
				page = "1";
			}
			int start = (Integer.parseInt(page) - 1) * pagePerCount + 1;
			int end= start + pagePerCount - 1;
			
			String skey = "courseAll";
			int total = 0;
			if (svalue == null) {
				skey2 = "courseAll";
				svalue = "";
				/*
				 * Date now = new Date(); java.text.SimpleDateFormat date = new
				 * java.text.SimpleDateFormat("YYYY-MM-DD");
				 * System.out.println(date.format(now));
				 */
			}
			AdminCourseDAO dao = new AdminCourseDAO();
			List<AllList> list = null;
			//list = dao.list(skey, skey2, svalue);
			list = dao.list(skey, skey2, svalue, start, end);
			AdminCourseDAO daoAddCourseName = new AdminCourseDAO();
			List<AllList> addCourseName = null;
			addCourseName = daoAddCourseName.list("courseNameAll", "courseNameAll", svalue);

			AdminCourseDAO daoAddClassRoom = new AdminCourseDAO();
			List<AllList> addClassroom = null;
			addClassroom = daoAddClassRoom.list("classroomAll", "classroomAll", svalue);

			String count = String.valueOf(list.size());
			total = dao.total("courseAll_total", "");
			
			int pageCount = (int)(Math.ceil(total / (double)pagePerCount));
			System.out.println("total "+total);
			System.out.println("pagePerCount "+pagePerCount);
			System.out.println("pageCount "+pageCount);
			
			request.setAttribute("list", list);
			request.setAttribute("addCourseName", addCourseName);// �߰� modal ������
			// ����ڽ�
			request.setAttribute("addClassroom", addClassroom);// �߰� modal ���ǽ�
			// ����ڽ�
			request.setAttribute("total", total);
			request.setAttribute("count", count);
			request.setAttribute("skey", skey2);
			request.setAttribute("svalue", svalue);
			
			request.setAttribute("page", page);
			request.setAttribute("pageCount", pageCount);

			url = "WEB-INF/source/admin/course_list.jsp";
		//}

		// ��� ��ȯ->������ �̵� ����->JSP ������
		return url; // .jsp
	}

	public String courseAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String loginkey = (String) session.getAttribute("loginkey");
		String url = "";
		if (loginkey == null) {
			url = "redirect:error.do";
		} else {

			request.setCharacterEncoding("UTF-8");
			
			System.out.println("courseAdd �޼ҵ� ȣ��");
			
			String courseName_id = request.getParameter("cou_name");
			String cou_start_date = request.getParameter("start");
			String cou_end_date = request.getParameter("end");
			String classroom_id = request.getParameter("classroom");
			
			System.out.println("������:" + courseName_id);
			System.out.println("����������:" + cou_start_date);
			System.out.println("����������:" + cou_end_date);
			System.out.println("���ǽǸ�:" + classroom_id);
			
			AllList a = new AllList();
			a.setCourseName_id(courseName_id);
			a.setCou_start_date(cou_start_date);
			a.setCou_end_date(cou_end_date);
			a.setClassroom_id(classroom_id);
			
			AdminCourseDAO dao = new AdminCourseDAO();
			dao.courseAdd(a);
	
			url = "redirect:course_list.do";
		}

		//��� ��ȯ->������ �̵� ����->���� �ּ�
		return url; //.do
	}
	public String courseModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String loginkey = (String) session.getAttribute("loginkey");
		String url = "";
		if (loginkey == null) {
			url = "redirect:error.do";
		} else {

			request.setCharacterEncoding("UTF-8");
			
			System.out.println("courseModify �޼ҵ� ȣ��");
			
			String course_id = request.getParameter("id");
			String courseName_id = request.getParameter("cou_name");
			String cou_start_date = request.getParameter("cou_start");
			String cou_end_date = request.getParameter("cou_end");
			String classroom_id = request.getParameter("classroom");
			
			System.out.println("����ID:" + course_id);
			System.out.println("������ID:" + courseName_id);
			System.out.println("����������:" + cou_start_date);
			System.out.println("����������:" + cou_end_date);
			System.out.println("���ǽǸ�:" + classroom_id);
			
			
			AllList a = new AllList();
			a.setCourse_id(course_id);
			a.setCourseName_id(courseName_id);
			a.setCou_start_date(cou_start_date);
			a.setCou_end_date(cou_end_date);
			a.setClassroom_id(classroom_id);
			
			AdminCourseDAO dao = new AdminCourseDAO();
			dao.courseModify(a);
			
			url = "redirect:course_list.do";
		}
		//��� ��ȯ->������ �̵� ����->���� �ּ�
		return url; //.do
	}
	public String courseRemove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String loginkey = (String) session.getAttribute("loginkey");
		String url = "";
		if (loginkey == null) {
			url = "redirect:error.do";
		} else {

			request.setCharacterEncoding("UTF-8");
			
			System.out.println("courseRemove �޼ҵ� ȣ��");
			
			String course_id = request.getParameter("id");
			
			AllList a = new AllList();
			a.setCourse_id(course_id);
			
			System.out.println(course_id);
			
			AdminCourseDAO dao = new AdminCourseDAO();
			int result = dao.courseRemove(a);
			
			System.out.println(result);
						
			url = "redirect:course_list.do";
		}
		//��� ��ȯ->������ �̵� ����->���� �ּ�
		return url; //.do
	}

	@SuppressWarnings("unchecked")
	
	public String course_list_infoajax(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String result = "";
		String cou_id = request.getParameter("cou_id");
		AdminCourseDAO dao_sub= new AdminCourseDAO();
		AdminCourseDAO dao_st = new AdminCourseDAO();

		List<AllList> list_sub = null;
		List<AllList> list_st = null;

		list_sub = dao_sub.list("courseId_sub","courseId_sub",cou_id);//���� ���� �������� ���
		list_st = dao_st.list("courseId_student","courseId_student",cou_id);//���� ���� �л����� ���
		System.out.println("cou_id = "+cou_id);
		System.out.println("list_sub.size() = "+list_sub.size());
		JSONObject course_modals = new JSONObject();//JSON ��ü ����
		JSONArray course_modal = new JSONArray();
		JSONObject subjects = new JSONObject();
		JSONArray subject = new JSONArray();//JSON ���� �迭 ��ü ����
		JSONObject students = new JSONObject();
		JSONArray student = new JSONArray();//JSON �л� �迭 ��ü ����

		// ���� ���� JSON
		for (AllList a : list_sub) {
			JSONObject sub = new JSONObject();
			sub.put("sub_name", a.getSub_name());
			sub.put("sub_start", a.getSub_start_date());
			sub.put("sub_end", a.getSub_end_date());
			sub.put("inst_name", a.getInst_name());
			sub.put("text_name", a.getText_name());
			subject.add(sub);
		}
		//�л����� JSON
		String[] finArr = {"�ߵ�Ż��","����","����"};// ���� ����
		for (AllList a : list_st) {
			JSONObject st = new JSONObject();
			st.put("st_name", a.getSt_name());
			st.put("st_pw", a.getSt_pw());
			st.put("st_phone", a.getSt_phone());
			st.put("st_regist", a.getRegister_date());
			st.put("st_fin", finArr[Integer.parseInt(a.getFin())]);
			student.add(st);
		}
		subjects.put("subject", subject);// ���� ����Ʈ ����
		students.put("student", student);// �л� ����Ʈ ����

		course_modal.add(subjects);
		course_modal.add(students);
		course_modals.put("course_modal", course_modal);

		result = course_modals.toJSONString();// ���ڿ��� ����
		request.setAttribute("result", result);
		// ��� ��ȯ->������ �̵� ����->���� �ּ�
		return "WEB-INF/source/admin/ajax.jsp"; // course_list.jsp
	}

	/*-----------------------------------*/
	/*            course_list2            */
	/*-----------------------------------*/
	public String course_list2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String loginkey = (String) session.getAttribute("loginkey");
		String url = "";
		if (loginkey == null) {
			url = "redirect:error.do";
		} else {
			request.setCharacterEncoding("UTF-8");
			System.out.println("course_list2 �޼ҵ� ȣ��");
			
			// Ÿ��Ʋ ���� ���
			String couId = request.getParameter("hidden_couId");
			String couName = request.getParameter("hidden_couName");
			String couStart = request.getParameter("hidden_couStart");
			String couEnd = request.getParameter("hidden_couEnd");

			System.out.println("couId="+couId);
			
			request.setAttribute("cou_id", couId);
			request.setAttribute("cou_name", couName);
			request.setAttribute("cou_start", couStart);
			request.setAttribute("cou_end", couEnd);

			String skey2 = request.getParameter("skey");
			String svalue = request.getParameter("svalue");
			String skey = "";
			String total = "0";
			if (svalue == null) {
				// ���� ���� ����2 - ����ID�� �������� ��ü ���
				skey = "course_id_sub";
				skey2 = "course_id_sub";
				svalue = "";
			} else {
				skey = svalue; // �˻���
			}
			AdminCourseDAO dao = new AdminCourseDAO();
			List<AllList> list = null;
			list = dao.list2(skey, skey2, couId);// �˻���, DAOŰ����, ����ID
			
			String count = String.valueOf(list.size());
			total = String.valueOf(dao.total("course_id_sub_total", couId));
			
			System.out.println(couId);
			System.out.println(count);
			System.out.println(total);
			
			
			// �����
			AdminCourseDAO subNameDao = new AdminCourseDAO();
			List<AllList> subNamelist = null;
			subNamelist = subNameDao.list2("subjectNameAll", "subjectNameAll", "");
			// �����
			AdminCourseDAO instDao = new AdminCourseDAO();
			List<AllList> instlist = null;
			instlist = instDao.list2("instAll", "instAll", "");
			// �����
			AdminCourseDAO textDao = new AdminCourseDAO();
			List<AllList> textlist = null;
			textlist = textDao.list2("textbookAll", "textbookAll", "");
			
			request.setAttribute("list", list);
			request.setAttribute("count", count);
			request.setAttribute("total", total);
			request.setAttribute("skey", skey2);
			request.setAttribute("svalue", svalue);

			request.setAttribute("subNamelist", subNamelist);// ����� ��ü
			request.setAttribute("instlist", instlist);// ����� ��ü
			request.setAttribute("textlist", textlist);// ����� ��ü

			url = "WEB-INF/source/admin/course_list2.jsp";
		}
		// ��� ��ȯ->������ �̵� ����->JSP ������
		return url; // .jsp
	}

	public String subjectAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String loginkey = (String) session.getAttribute("loginkey");
		String url = "";
		if (loginkey == null) {
			url = "redirect:error.do";
		} else {
			request.setCharacterEncoding("UTF-8");
			
			System.out.println("subjectAdd �޼ҵ� ȣ��");
			
			// hidden form ���� �ѱ��
			String course_id = request.getParameter("hidden_couId");
			String cou_name = request.getParameter("hidden_couName"); 
			String cou_start_date = request.getParameter("hidden_couStart");
			String cou_end_date = request.getParameter("hidden_couEnd");			

			String subjectName_id = request.getParameter("sub_name");
			String sub_start_date = request.getParameter("start");
			String sub_end_date = request.getParameter("end");
			String textbook_id = request.getParameter("textbook");
			String instructor_id = request.getParameter("instructor");
			
			AllList a = new AllList();
			a.setCourse_id(course_id);
			a.setSubjectName_id(subjectName_id);
			a.setSub_start_date(sub_start_date);
			a.setSub_end_date(sub_end_date);
			a.setTextbook_id(textbook_id);
			a.setInstructor_id(instructor_id);
			
			System.out.println(course_id);
			System.out.println(subjectName_id);
			System.out.println(sub_start_date);
			System.out.println(sub_end_date);
			System.out.println(textbook_id);
			System.out.println(instructor_id);
			
			AdminCourseDAO dao = new AdminCourseDAO();
			int result = dao.subjectAdd(a);
			
			System.out.println("add�� �����?" + result);
			
			request.setAttribute("course_id", course_id);
			request.setAttribute("subjectName_id", subjectName_id);
			request.setAttribute("sub_start_date", sub_start_date);
			request.setAttribute("sub_end_date", sub_end_date);
			request.setAttribute("textbook_id", textbook_id);
			request.setAttribute("instructor_id", instructor_id);
			
			
			request.setAttribute("hidden_couId", course_id);
			request.setAttribute("hidden_couName", cou_name);
			request.setAttribute("hidden_couStart", cou_start_date);
			request.setAttribute("hidden_couEnd", cou_end_date);
			
			url = String.format("redirect:course_list2.do?hidden_couId=%s&hidden_couName=%s&hidden_couStart=%s&hidden_couEnd=%s"
					, course_id, java.net.URLEncoder.encode(cou_name, "UTF-8"), cou_start_date, cou_end_date);
			
		}
		//��� ��ȯ->������ �̵� ����->���� �ּ�
		return url; //.do
	}
	public String subjectModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String loginkey = (String) session.getAttribute("loginkey");
		String url = "";
		if (loginkey == null) {
			url = "redirect:error.do";
		} else {
			request.setCharacterEncoding("UTF-8");
			
			System.out.println("subjectModify �޼ҵ� ȣ��");
			
			String course_id = request.getParameter("m_hidden_couId");
			String cou_name = request.getParameter("m_hidden_couName");
			String cou_start_date = request.getParameter("m_hidden_couStart");
			String cou_end_date = request.getParameter("m_hidden_couEnd");
			
			String subject_id = request.getParameter("id");
			String subjectName_id = request.getParameter("sub_name");
			String sub_start_date = request.getParameter("sub_start");
			String sub_end_date = request.getParameter("sub_end");
			String textbook_id = request.getParameter("textbook");
			String instructor_id = request.getParameter("instructor");
			
			AllList a = new AllList();
			a.setCourse_id(course_id);
			a.setSubject_id(subject_id);
			a.setSubjectName_id(subjectName_id);
			a.setSub_start_date(sub_start_date);
			a.setSub_end_date(sub_end_date);
			a.setTextbook_id(textbook_id);
			a.setInstructor_id(instructor_id);
			
			System.out.println("����ID:" + course_id);
			System.out.println("����ID:" +subject_id);
			System.out.println("�����ID:" +subjectName_id);
			System.out.println("���������:" +sub_start_date);
			System.out.println("����������:" +sub_end_date);
			System.out.println("����ID:" +textbook_id);
			System.out.println("����ID" +instructor_id);
			
			AdminCourseDAO dao = new AdminCourseDAO();
			int result = dao.subjectModify(a);
			
			System.out.println("������ ��� ����?" + result);
			
			request.setAttribute("course_id", course_id);
			request.setAttribute("subject_id", subject_id);
			request.setAttribute("subjectName_id", subjectName_id);
			request.setAttribute("sub_start_date", sub_start_date);
			request.setAttribute("sub_end_date", sub_end_date);
			request.setAttribute("textbook_id", textbook_id);
			request.setAttribute("instructor_id", instructor_id);

			request.setAttribute("hidden_couId", course_id);
			request.setAttribute("hidden_couName", cou_name);
			request.setAttribute("hidden_couStart", cou_start_date);
			request.setAttribute("hidden_couEnd", cou_end_date);
			
			url = String.format("redirect:course_list2.do?hidden_couId=%s&hidden_couName=%s&hidden_couStart=%s&hidden_couEnd=%s"
					, course_id, java.net.URLEncoder.encode(cou_name, "UTF-8"), cou_start_date, cou_end_date);
			
		}
		//��� ��ȯ->������ �̵� ����->���� �ּ�
		return url; //.do
	}
	public String subjectRemove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String loginkey = (String) session.getAttribute("loginkey");
		String url = "";
		if (loginkey == null) {
			url = "redirect:error.do";
		} else {
			request.setCharacterEncoding("UTF-8");
			
			System.out.println("subjectRemove �޼ҵ� ȣ��");
			
			String course_id = request.getParameter("d_hidden_couId");
			String cou_name = request.getParameter("d_hidden_couName");
			String cou_start_date = request.getParameter("d_hidden_couStart");
			String cou_end_date = request.getParameter("d_hidden_couEnd");
			
			String subject_id = request.getParameter("id");
			
			String subjectName_id = request.getParameter("sub_name");
			String sub_start_date = request.getParameter("sub_start");
			String sub_end_date = request.getParameter("sub_end");
			String textbook_id = request.getParameter("text_name");
			String instructor_id = request.getParameter("inst_name");
			
			
			
			System.out.println("����ID:" + course_id);
			System.out.println("����ID:" + subject_id);
			
			System.out.println("�����ID:" + subjectName_id);
			System.out.println("���������:" + sub_start_date);
			System.out.println("����������:" + sub_end_date);
			System.out.println("����ID:" + textbook_id);
			System.out.println("����ID:" + instructor_id);
			
			
			AllList a = new AllList();
			a.setCourse_id(course_id);
			a.setSubject_id(subject_id);
			
			AdminCourseDAO dao = new AdminCourseDAO();
			int result = dao.subjectRemove(a);
			
			System.out.println("������ ����� ����?" + result);
			
			request.setAttribute("hidden_couId", course_id);
			request.setAttribute("hidden_couName", cou_name);
			request.setAttribute("hidden_couStart", cou_start_date);
			request.setAttribute("hidden_couEnd", cou_end_date);
			
			request.setAttribute("subject_id", subject_id);
			
			request.setAttribute("subjectName_id", subjectName_id);
			request.setAttribute("sub_start_date", sub_start_date);
			request.setAttribute("sub_end_date", sub_end_date);
			request.setAttribute("textbook_id", textbook_id);
			request.setAttribute("instructor_id", instructor_id);
			
			url = String.format("redirect:course_list2.do?hidden_couId=%s&hidden_couName=%s&hidden_couStart=%s&hidden_couEnd=%s"
					, course_id, java.net.URLEncoder.encode(cou_name, "UTF-8"), cou_start_date, cou_end_date);
		}
		
		//��� ��ȯ->������ �̵� ����->���� �ּ�
		return url; //.do
	}

	
	/*-----------------------------------*/
	/*            student_list           */
	/*-----------------------------------*/
	public String student_list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String loginkey = (String) session.getAttribute("loginkey");
		String url = "";
		if (loginkey == null) {
			url = "redirect:error.do";
		} else {
			request.setCharacterEncoding("UTF-8");
			String skey2 = request.getParameter("skey");
			String svalue = request.getParameter("svalue");
			String skey = "";
			int total = 0;
			skey = "studentAll";
			if (svalue == null) {
				skey2 = "studentAll";
				svalue = "";
			}
			//String[] progressArr = { "����", "������", "����" };

			AdminStudentDAO dao = new AdminStudentDAO();
			List<AllList> list = null;
			list = dao.list1(skey, skey2, svalue);// ������ ���� �߰� �ʿ�
			String count = String.valueOf(list.size());
			total = dao.total("studentAll", "");

			// ���� ��� ��ư ���
			AdminStudentDAO coursedao = new AdminStudentDAO();
			List<AllList> courselist = null;
			courselist = coursedao.list1("courseAddAll", "courseAddAll", "");

			request.setAttribute("list", list);
			request.setAttribute("count", count);
			request.setAttribute("total", total);
			request.setAttribute("skey", skey2);
			request.setAttribute("svalue", svalue);
			//request.setAttribute("progressArr", progressArr);

			request.setAttribute("courselist", courselist);
			request.setAttribute("loginkey", loginkey);

			url = "WEB-INF/source/admin/student_list.jsp";
		}

		// ��� ��ȯ->������ �̵� ����->JSP ������
		return url; // .jsp
	}

	public String studentAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
			request.setCharacterEncoding("UTF-8");
			
			String st_name = request.getParameter("name");
			String st_pw = request.getParameter("pw");
			String phone = request.getParameter("phone");

			System.out.println("st_name "+ st_name);
			System.out.println("st_pw "+ st_pw);
			System.out.println("phone "+ phone);
 
			AllList a = new AllList();

			a.setSt_name(st_name);
			a.setSt_pw(Integer.parseInt(st_pw));
			a.setSt_phone(phone);
			
			AdminStudentDAO sdao = new AdminStudentDAO();
			sdao.studentAdd(a);
			
			
			//��� ��ȯ->������ �̵� ����->���� �ּ�
			return "redirect:student_list.do";
		}
 
	public String studentModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 
			request.setCharacterEncoding("UTF-8");
			
			String st_name = request.getParameter("modify_name");
			String st_phone = request.getParameter("modify_phone");
			String student_id = request.getParameter("modify_id");
			
			AllList m = new AllList();
			m.setSt_name(st_name);
			m.setSt_phone(st_phone);
			m.setStudent_id(student_id);
			
			AdminStudentDAO stdao = new AdminStudentDAO();
			stdao.studentModify(m);
 
			return "redirect:student_list.do";
		}
		 
	
	public String studentRemove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  

			request.setCharacterEncoding("UTF-8");

			String student_id = request.getParameter("student_id");
			System.out.println("student_id " + student_id);
 
			AllList m = new AllList();
			m.setStudent_id(student_id);
			
			AdminStudentDAO sdao = new AdminStudentDAO();
			sdao.remove(m);
 
			//��� ��ȯ->������ �̵� ����->���� �ּ�
			
			return "redirect:student_list.do";
		}
	
	//�������
	public String student_course_add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		System.out.println("student_course_add �޼ҵ� ȣ��");
		
		String student_id = request.getParameter("student_id2");
		String course_id = request.getParameter("courseRadio");
		String mid_fail_data = request.getParameter("mid_fail_data");
		
		
		System.out.println("student_id " + student_id);
		System.out.println("course_id " + course_id);
		
		AllList ga = new AllList();
		ga.setStudent_id(student_id);
		ga.setCourse_id(course_id);
		
		AdminStudentDAO sdao = new AdminStudentDAO();
		sdao.course_add(ga);

		request.setAttribute("student_id", student_id);
		request.setAttribute("course_id", course_id);
		request.setAttribute("mid_fail_data", mid_fail_data);
		
		
		//��� ��ȯ->������ �̵� ����->���� �ּ�
		return "redirect:student_list.do";
	}
	
   //���� Ż��
	public String student_course_fail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		System.out.println("student_course_fail �޼ҵ� ȣ��");
		
		String course_id = request.getParameter("hidden_couId2");
		String student_id = request.getParameter("st_id");
		String mid_fail_date = request.getParameter("mid_fail_date");
		
        //
		String couName = request.getParameter("hidden_couName");
		String couStart = request.getParameter("hidden_couStart");
		String couEnd = request.getParameter("hidden_couEnd");
		String skey = request.getParameter("skey");
		String svalue = request.getParameter("svalue");
		String skey2 = "";
 
		if (svalue == null) {
			// ���� ���� ����2 - ����ID�� �������� ��ü ���
			skey = "failcourseId_studentAll";
			skey2 = "failcourseId_studentAll";
			svalue = "";
		} else {
			skey2 = svalue; // �˻���
		}
		//
		System.out.println("skey " + skey);
		System.out.println("svalue " + svalue);
		System.out.println("skey2 " + skey2);
 
		System.out.println("couName " + couName);
		System.out.println("couStart " + couStart);
		System.out.println("couEnd " + couEnd);
		//
		System.out.println("student_id " + student_id);
		System.out.println("course_id " + course_id);
		System.out.println("mid_fail_date " + mid_fail_date);
		
		AllList cf = new AllList();
		cf.setCourse_id(course_id);
		cf.setStudent_id(student_id);
		cf.setMid_fail_date(mid_fail_date);

		AdminStudentDAO sdao = new AdminStudentDAO();
		sdao.st_mid_fail(cf);
		//
		List<AllList> listStudent = null;
		listStudent = sdao.listfail2(skey, skey2, course_id);
 
		request.setAttribute("listStudent", listStudent);
		request.setAttribute("skey", skey);
		request.setAttribute("svalue", svalue);

		request.setAttribute("cou_id", course_id);
		request.setAttribute("cou_name", couName);
		request.setAttribute("cou_start", couStart);
		request.setAttribute("cou_end", couEnd);
        //
		//��� ��ȯ->������ �̵� ����->���� �ּ�
		return "WEB-INF/source/admin/student_fail2.jsp";
	}
	

	/*-----------------------------------*/
	/*            student_list2            */
	/*-----------------------------------*/
	public String student_list2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String loginkey = (String) session.getAttribute("loginkey");
		String url = "";
		if (loginkey == null) {
			url = "redirect:error.do";
		} else {

			request.setCharacterEncoding("UTF-8");

			String st_id = request.getParameter("hidden_stId");
			String st_name = request.getParameter("hidden_stName");
			String st_phone = request.getParameter("hidden_stPhone");
			String register = request.getParameter("hidden_stRegister");

			AdminStudentDAO dao = new AdminStudentDAO();
			List<AllList> list = null;

			String skey = request.getParameter("skey");
			String svalue = request.getParameter("svalue");
			String skey2 = "";
			int total = 0;
			if (svalue == null) {
				// ���� ���� ����2 - ����ID�� �������� ��ü ���
				skey = "studentInfo";
				skey2 = "studentInfo";
				svalue = "";
			} else {
				skey2 = svalue; // �˻���
			}
			System.out.println(skey);
			System.out.println(skey2);
			System.out.println(st_id);
			list = dao.list2(skey, skey2, st_id);
			String count = String.valueOf(list.size());
			total = dao.total("studentInfo", st_id);
			String[] finArr = { "�ߵ�Ż��", "����", "����" };
			request.setAttribute("list", list);
			request.setAttribute("count", count);
			request.setAttribute("total", total);
			request.setAttribute("skey", skey);
			request.setAttribute("svalue", svalue);
			request.setAttribute("finArr", finArr);

			request.setAttribute("title_id", st_id);
			request.setAttribute("title_name", st_name);
			request.setAttribute("title_phone", st_phone);
			request.setAttribute("title_register", register);
			request.setAttribute("loginkey", loginkey);

			url = "WEB-INF/source/admin/student_list2.jsp";
		}

		// ��� ��ȯ->������ �̵� ����->JSP ������
		return url; // .jsp
	}

	/*-----------------------------------*/
	/*            student_fail            */
	/*-----------------------------------*/
	public String student_fail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String loginkey = (String) session.getAttribute("loginkey");
		String url = "";
		if (loginkey == null) {
			url = "redirect:error.do";
		} else {
			request.setCharacterEncoding("UTF-8");
			String skey2 = request.getParameter("skey");
			String svalue = request.getParameter("svalue");
			String skey = "";
			int total = 0;
			skey = "failcourseAll";
			if (svalue == null) {
				skey2 = "failcourseAll";
				svalue = "";
			}
			AdminStudentDAO dao = new AdminStudentDAO();
			List<AllList> list = null;

			list = dao.listfail1(skey, skey2, svalue);
			String count = String.valueOf(list.size());
			total = dao.total("failcourseAll", "");
			request.setAttribute("list", list);
			request.setAttribute("count", count);
			request.setAttribute("total", total);
			request.setAttribute("skey", skey2);
			request.setAttribute("svalue", svalue);
			request.setAttribute("loginkey", loginkey);

			url = "WEB-INF/source/admin/student_fail.jsp";
		}
		// ��� ��ȯ->������ �̵� ����->JSP ������
		return url; // .jsp
	}

	public String student_fail2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String loginkey = (String) session.getAttribute("loginkey");
		String url = "";
		if (loginkey == null) {
			url = "redirect:error.do";
		} else {
			request.setCharacterEncoding("UTF-8");

			String couId = request.getParameter("hidden_couId");
			String couName = request.getParameter("hidden_couName");
			String couStart = request.getParameter("hidden_couStart");
			String couEnd = request.getParameter("hidden_couEnd");

			
			String skey = request.getParameter("skey");
			String svalue = request.getParameter("svalue");
			String skey2 = "";
			int total = 0;
			if (svalue == null) {
				// ���� ���� ����2 - ����ID�� �������� ��ü ���
				skey = "failcourseId_studentAll";
				skey2 = "failcourseId_studentAll";
				svalue = "";
			} else {
				skey2 = svalue; // �˻���
			}
			AdminStudentDAO dao = new AdminStudentDAO();
			List<AllList> listStudent = null;
			listStudent = dao.listfail2(skey, skey2, couId);

			String count = String.valueOf(listStudent.size());
			total = dao.total("failcourseId_studentAll", couId);
			request.setAttribute("listStudent", listStudent);
			request.setAttribute("count", count);
			request.setAttribute("total", total);
			request.setAttribute("skey", skey);
			request.setAttribute("svalue", svalue);

			request.setAttribute("cou_id", couId);
			request.setAttribute("cou_name", couName);
			request.setAttribute("cou_start", couStart);
			request.setAttribute("cou_end", couEnd);
			request.setAttribute("loginkey", loginkey);

			url = "WEB-INF/source/admin/student_fail2.jsp";
		}
		// ��� ��ȯ->������ �̵� ����->JSP ������
		return url; // .jsp
	}
	
	
	/*-----------------------------------*/
	/*            grade_list            */
	/*-----------------------------------*/
	public String grade_list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String loginkey = (String) session.getAttribute("loginkey");
		String url = "";
		if (loginkey == null) {
			url = "redirect:error.do";
		} else {

			request.setCharacterEncoding("UTF-8");
			// ��ü ��� �� �˻� ��� ��� �ڵ� �ۼ�
			String total = "0"; // ��ü �ο���
			String count = "0"; // �˻� ��� �ο���

			AdminGradeDAO dao = new AdminGradeDAO();
			List<AllList> list = null;

			// �˻� ��û ������ ����
			String skey = request.getParameter("skey");
			String svalue = request.getParameter("svalue");

			if (skey == null) {
				skey = "courseall";
				svalue = "";
			}

			list = dao.List(skey, svalue);// �˻� �� ��ü ���
			total = String.valueOf(dao.grade_total());
			count = String.valueOf(list.size());

			request.setAttribute("list", list);
			request.setAttribute("count", count);
			request.setAttribute("total", total);
			request.setAttribute("skey", skey);
			request.setAttribute("svalue", svalue);

			url = "WEB-INF/source/admin/grade_list.jsp";
		}

		// ��� ��ȯ->������ �̵� ����->JSP ������
		return url; // .jsp
	}

	public String grade_course(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String loginkey = (String) session.getAttribute("loginkey");
		String url = "";
		if (loginkey == null) {
			url = "redirect:error.do";
		} else {

			request.setCharacterEncoding("UTF-8");
			// ��ü ��� �� �˻� ��� ��� �ڵ� �ۼ�
			String total = "0"; // ��ü �ο���
			String count = "0"; // �˻� ��� �ο���

			AdminGradeDAO dao = new AdminGradeDAO();
			List<AllList> list = null;

			String skey = request.getParameter("skey");
			String svalue2 = request.getParameter("svalue");
			String svalue = request.getParameter("course_id");
			String course_name = request.getParameter("course_name");
			String course_start_date = request.getParameter("course_start_date");
			String course_end_date = request.getParameter("course_end_date");

			if (skey == null) {
				skey = "grade_course";
				svalue2 = "";
			}

			list = dao.grade_courseList(skey, svalue, svalue2);// �˻� �� ��ü ���
			count = String.valueOf(list.size());
			total = String.valueOf(dao.gradecourse_total(svalue));

			request.setAttribute("list", list);
			request.setAttribute("count", count);
			request.setAttribute("total", total);
			request.setAttribute("course_id", svalue);
			request.setAttribute("course_name", course_name);
			request.setAttribute("course_start_date", course_start_date);
			request.setAttribute("course_end_date", course_end_date);

			url = "WEB-INF/source/admin/grade_course.jsp";
		}

		// ��� ��ȯ->������ �̵� ����->JSP ������
		return url;// .jsp
	}


	public String grade_course2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String loginkey = (String) session.getAttribute("loginkey");
		String url = "";
		if (loginkey == null) {
			url = "redirect:error.do";
		} else {

			request.setCharacterEncoding("UTF-8");

			String total = "0"; // ��ü �ο���
			String count = "0"; // �˻� ��� �ο���

			AdminGradeDAO dao = new AdminGradeDAO();
			List<AllList> list = null;

			String skey = request.getParameter("skey");
			String svalue2 = request.getParameter("svalue");
			String svalue = request.getParameter("subject_id");
			String sub_name = request.getParameter("sub_name");
			String sub_start_date = request.getParameter("sub_start_date");
			String sub_end_date = request.getParameter("sub_end_date");
			String po_attend = request.getParameter("po_attend");
			String po_write = request.getParameter("po_write");
			String po_practice = request.getParameter("po_practice");

			if (skey == null) {
				skey = "grade_course2";
				svalue2 = "";
			}

			list = dao.grade_courseList2(skey, svalue, svalue2);// �˻� �� ��ü ���
			count = String.valueOf(list.size());

			total = String.valueOf(dao.gradecourse2_total(svalue));

			request.setAttribute("list", list);
			request.setAttribute("count", count);
			request.setAttribute("total", total);
			request.setAttribute("subject_id", svalue);
			request.setAttribute("sub_name", sub_name);
			request.setAttribute("sub_start_date", sub_start_date);
			request.setAttribute("sub_end_date", sub_end_date);
			request.setAttribute("po_attend", po_attend);
			request.setAttribute("po_write", po_write);
			request.setAttribute("po_practice", po_practice);

			url = "WEB-INF/source/admin/grade_course2.jsp";
		}

		// ��� ��ȯ->������ �̵� ����->JSP ������
		return url; // .jsp
	}
	/*-----------------------------------*/
	/*            grade_person           */
	/*-----------------------------------*/
	public String grade_person(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String loginkey = (String) session.getAttribute("loginkey");
		String url = "";
		if (loginkey == null) {
			url = "redirect:error.do";
		} else {

			request.setCharacterEncoding("UTF-8");

			String total = "0";
			String count = "0";

			List<AllList> list = null;

			AdminGradeDAO dao = new AdminGradeDAO();

			String skey = request.getParameter("skey");
			String svalue = request.getParameter("svalue");

			if (skey == null) {
				skey = "all";
				svalue = "";
			}

			list = dao.gradePersonList(skey, svalue);
			total = String.valueOf(dao.studentTotal());
			count = String.valueOf(list.size());

			request.setAttribute("list", list);
			request.setAttribute("count", count);
			request.setAttribute("total", total);
			request.setAttribute("skey", skey);
			request.setAttribute("svalue", svalue);

			url = "WEB-INF/source/admin/grade_person.jsp";
		}

		// ��� ��ȯ->������ �̵� ����->JSP ������

		return url; // .jsp

	}

	/*-----------------------------------*/
	/*            grade_person2          */
	/*-----------------------------------*/
	public String grade_person2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String loginkey = (String) session.getAttribute("loginkey");
		String url = "";
		if (loginkey == null) {
			url = "redirect:error.do";
		} else {

			request.setCharacterEncoding("UTF-8");

			System.out.println("grade_person2 �޼ҵ� ȣ��!");

			String list_value1 = null;
			String total = "0";
			String count = "0";

			List<AllList> list = null;

			AdminGradeDAO dao = new AdminGradeDAO();

			String skey = request.getParameter("skey");
			String svalue = request.getParameter("svalue");

			// hidden form �޾ƿ��� ���� ����
			String titleId = request.getParameter("titleId");
			String titleName = request.getParameter("titleName");
			String titlePhone = request.getParameter("titlePhone");

			System.out.println("skey:" + skey);
			System.out.println("svalue:" + svalue);
			System.out.println("titleId:" + titleId);
			System.out.println("titleName:" + titleName);
			System.out.println("titlePhone:" + titlePhone);

			if (skey == null) {
				skey = "all";
				svalue = "";
			}

			list = dao.gradePersonList2(skey, titleId, svalue);
			total = String.valueOf(dao.personTotal(titleId));
			count = String.valueOf(list.size());

			System.out.println("count:" + count);

			request.setAttribute("list", list);
			request.setAttribute("count", count);
			request.setAttribute("total", total);
			request.setAttribute("skey", skey);
			request.setAttribute("svalue", svalue);

			// hidden form �ѱ�� ����
			request.setAttribute("titleId", titleId);
			request.setAttribute("titleName", titleName);
			request.setAttribute("titlePhone", titlePhone);

			url = "WEB-INF/source/admin/grade_person2.jsp";
		}

		// ��� ��ȯ->������ �̵� ����->JSP ������
		return url; // .jsp
	}

	/*-----------------------------------*/
	/*            inst_schedule            */
	/*-----------------------------------*/
	public String inst_schedule_all(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session1 = request.getSession();
		String instloginkey = (String) session1.getAttribute("instloginkey");
		String url = "";
		if (instloginkey == null) {
			url = "redirect:error.do";
		} else {

			request.setCharacterEncoding("UTF-8");

			String total = "0"; // ��ü �ο���
			String count = "0"; // �˻� ��� �ο���

			InstructorDAO dao = new InstructorDAO();

			List<AllList> list = null;

			// �˻� ��û ������ ����
			String skey = request.getParameter("skey");
			String svalue = request.getParameter("svalue");

			// �α��� ó���� ������ ���� ���̵� -> hidden form
			HttpSession session_id = request.getSession();
			String instructor_id = (String) session_id.getAttribute("instid");

			String subject_id = request.getParameter("subject_id");

			if (skey == null) {
				skey = "all";
				svalue = "";
			}

			list = dao.schedule_allList(instructor_id, skey, svalue); 
			total = String.valueOf(dao.total("instructor_schedule", instructor_id));																		
			count = String.valueOf(list.size());

			request.setAttribute("list", list);
			request.setAttribute("count", count);
			request.setAttribute("total", total);
			request.setAttribute("skey", skey);
			request.setAttribute("svalue", svalue);

			// �α��� ó���� ������ ���� ���̵� -> hidden form
			request.setAttribute("instructor_id", instructor_id);

			request.setAttribute("subject_id", subject_id);

			url = "WEB-INF/source/instructor/inst_schedule_all.jsp";
		}

		// ��� ��ȯ->������ �̵� ����->JSP ������
		return url; // .jsp
	}

	public String inst_schedule_start(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session1 = request.getSession();
		String instloginkey = (String) session1.getAttribute("instloginkey");
		String url = "";
		if (instloginkey == null) {
			url = "redirect:error.do";
		} else {

			request.setCharacterEncoding("UTF-8");

			String total = "0"; // ��ü �ο���
			String count = "0"; // �˻� ��� �ο���

			InstructorDAO dao = new InstructorDAO();

			List<AllList> list = null;

			// �˻� ��û ������ ����
			String skey = request.getParameter("skey");
			String svalue = request.getParameter("svalue");

			// �α��� ó���� ������ ���� ���̵� -> hidden form
			HttpSession session_id = request.getSession();
			String instructor_id = (String) session_id.getAttribute("instid");

			String subject_id = request.getParameter("subject_id");

			if (skey == null) {
				skey = "start";
				svalue = "";
			}

			list = dao.schedule_startList(instructor_id, skey, svalue);			
			total = String.valueOf(dao.total("instructor_schedule", instructor_id));																				
			count = String.valueOf(list.size());

			request.setAttribute("list", list);
			request.setAttribute("count", count);
			request.setAttribute("total", total);
			request.setAttribute("skey", skey);
			request.setAttribute("svalue", svalue);

			// �α��� ó���� ������ ���� ���̵� -> hidden form
			request.setAttribute("instructor_id", instructor_id);

			request.setAttribute("subject_id", subject_id);

			url = "WEB-INF/source/instructor/inst_schedule_start.jsp";
		}

		// ��� ��ȯ->������ �̵� ����->JSP ������
		return url; // .jsp

	}

	public String inst_schedule_ing(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session1 = request.getSession();
		String instloginkey = (String) session1.getAttribute("instloginkey");
		String url = "";
		if (instloginkey == null) {
			url = "redirect:error.do";
		} else {

			request.setCharacterEncoding("UTF-8");

			String total = "0"; // ��ü �ο���
			String count = "0"; // �˻� ��� �ο���

			InstructorDAO dao = new InstructorDAO();

			List<AllList> list = null;

			// �˻� ��û ������ ����
			String skey = request.getParameter("skey");
			String svalue = request.getParameter("svalue");

			// �α��� ó���� ������ ���� ���̵� -> hidden form
			HttpSession session_id = request.getSession();
			String instructor_id = (String) session_id.getAttribute("instid");

			String subject_id = request.getParameter("subject_id");

			if (skey == null) {
				skey = "ing";
				svalue = "";
			}

			list = dao.schedule_ingList(instructor_id, skey, svalue);														
			total = String.valueOf(dao.total("instructor_schedule", instructor_id));																				
			count = String.valueOf(list.size());

			request.setAttribute("list", list);
			request.setAttribute("count", count);
			request.setAttribute("total", total);
			request.setAttribute("skey", skey);
			request.setAttribute("svalue", svalue);

			// �α��� ó���� ������ ���� ���̵� -> hidden form
			request.setAttribute("instructor_id", instructor_id);

			request.setAttribute("subject_id", subject_id);

			url = "WEB-INF/source/instructor/inst_schedule_ing.jsp";
		}

		// ��� ��ȯ->������ �̵� ����->JSP ������
		return url; // .jsp
	}

	public String inst_schedule_end(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session1 = request.getSession();
		String instloginkey = (String) session1.getAttribute("instloginkey");
		String url = "";
		if (instloginkey == null) {
			url = "redirect:error.do";
		} else {

			request.setCharacterEncoding("UTF-8");

			String total = "0"; // ��ü �ο���
			String count = "0"; // �˻� ��� �ο���

			InstructorDAO dao = new InstructorDAO();

			List<AllList> list = null;

			// �˻� ��û ������ ����
			String skey = request.getParameter("skey");
			String svalue = request.getParameter("svalue");

			// �α��� ó���� ������ ���� ���̵� -> hidden form
			HttpSession session_id = request.getSession();
			String instructor_id = (String) session_id.getAttribute("instid");

			String subject_id = request.getParameter("subject_id");

			if (skey == null) {
				skey = "end";
				svalue = "";
			}

			list = dao.schedule_endList(instructor_id, skey, svalue); 																
			total = String.valueOf(dao.total("instructor_schedule", instructor_id));																	
			count = String.valueOf(list.size());

			request.setAttribute("list", list);
			request.setAttribute("count", count);
			request.setAttribute("total", total);
			request.setAttribute("skey", skey);
			request.setAttribute("svalue", svalue);

			// �α��� ó���� ������ ���� ���̵� -> hidden form
			request.setAttribute("instructor_id", instructor_id);

			request.setAttribute("subject_id", subject_id);

			url = "WEB-INF/source/instructor/inst_schedule_end.jsp";
		}

		// ��� ��ȯ->������ �̵� ����->JSP ������
		return url; // .jsp
	}

	public String inst_ajax(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		InstructorDAO mdao = new InstructorDAO();

		List<AllList> modalList = null;

		//��������ȸ ��� 
		String subject_id = request.getParameter("subject_id");
		System.out.println(subject_id);

		modalList = mdao.schedule_modalList(subject_id);

		//JSON ��ü ����
		JSONObject students = new JSONObject();

		//JSON �迭 ��ü ����
		JSONArray array = new JSONArray();

		//JSON ��ü ����
		String[] arr = {"�ߵ�Ż��","����","����"};
		for (AllList m : modalList) {

			JSONObject student = new JSONObject();

			student.put("student_id", m.getStudent_id());
			student.put("st_name", m.getSt_name());
			student.put("st_phone", m.getSt_phone());
			student.put("st_regist", m.getRegister_date());
			student.put("st_fin", arr[Integer.parseInt(m.getFin())]);

			array.add(student);
		}
		students.put("student", array);
		System.out.println(students.toJSONString());
		String result = students.toJSONString();

		request.setAttribute("result", result);

		return "WEB-INF/source/instructor/inst_ajax.jsp"; 
	}

	/*-----------------------------------*/
	/*            inst_point_exam        */
	/*-----------------------------------*/
	public String inst_point_exam(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session1 = request.getSession();
		String instloginkey = (String) session1.getAttribute("instloginkey");
		String url = "";
		if (instloginkey == null) {
			url = "redirect:error.do";
		} else {

			request.setCharacterEncoding("UTF-8");

			String total = "0";
			String count = "0";

			List<AllList> list = null;

			InstructorDAO dao = new InstructorDAO();

			String skey = request.getParameter("skey");
			String svalue = request.getParameter("svalue");

			// �α��� ó���� ������ ���� ���̵� -> hidden form
			HttpSession session_id = request.getSession();
			String instructor_id = (String) session_id.getAttribute("instid");

			if (skey == null) {
				skey = "all";
				svalue = "";
			}

			list = dao.point_examList(instructor_id, skey, svalue); 
			total = String.valueOf(dao.total("instructor_id", instructor_id));
			count = String.valueOf(list.size());

			request.setAttribute("list", list);
			request.setAttribute("count", count);
			request.setAttribute("total", total);
			request.setAttribute("skey", skey);
			request.setAttribute("svalue", svalue);

			// �α��� ó���� ������ ���� ���̵� -> hidden form
			request.setAttribute("instructor_id", instructor_id);

			url = "WEB-INF/source/instructor/inst_point_exam.jsp";
		}

		// ��� ��ȯ->������ �̵� ����->JSP ������
		return url; // .jsp
	}

	public String inst_pointAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session1 = request.getSession();
		String instloginkey = (String) session1.getAttribute("instloginkey");
		String url = "";
		if (instloginkey == null) {
			url = "redirect:error.do";
		} else {

			request.setCharacterEncoding("UTF-8");

			String pi_subject_id = request.getParameter("pi_subject_id");
			String i_point1 = request.getParameter("i_point1");
			String i_point2 = request.getParameter("i_point2");
			String i_point3 = request.getParameter("i_point3");

			AllList point = new AllList();
			point.setSubject_id(pi_subject_id);
			point.setPo_attend((i_point1));
			point.setPo_write((i_point2));
			point.setPo_practice((i_point3));

			InstructorDAO inst = new InstructorDAO();
			inst.pointAdd(point);

			url = "redirect:inst_point_exam.do";
		}

		//��� ��ȯ->������ �̵� ����->���� �ּ�
		return url; //.do
	}
	public String inst_pointModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session1 = request.getSession();
		String instloginkey = (String) session1.getAttribute("instloginkey");
		String url = "";
		if (instloginkey == null) {
			url = "redirect:error.do";
		} else {

			request.setCharacterEncoding("UTF-8");

			String pu_subject_id = request.getParameter("pu_subject_id");
			String u_point1 = request.getParameter("u_point1");
			String u_point2 = request.getParameter("u_point2");
			String u_point3 = request.getParameter("u_point3");

			AllList point = new AllList();
			point.setSubject_id(pu_subject_id);
			point.setPo_attend((u_point1));
			point.setPo_write((u_point2));
			point.setPo_practice((u_point3));

			InstructorDAO inst = new InstructorDAO();
			inst.pointModify(point);

			url = "redirect:inst_point_exam.do";
		}

		//��� ��ȯ->������ �̵� ����->���� �ּ�
		return url; //.do
	}
	public String inst_pointRemove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session1 = request.getSession();
		String instloginkey = (String) session1.getAttribute("instloginkey");
		String url = "";
		if (instloginkey == null) {
			url = "redirect:error.do";
		} else {

			request.setCharacterEncoding("UTF-8");

			String pd_subject_id = request.getParameter("pd_subject_id");

			AllList point = new AllList();
			point.setSubject_id(pd_subject_id);

			InstructorDAO inst = new InstructorDAO();
			inst.pointRemove(point);

			url = "redirect:inst_point_exam.do";
		}

		//��� ��ȯ->������ �̵� ����->���� �ּ�
		return url;
	}
	public String inst_examAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session1 = request.getSession();
		String instloginkey = (String) session1.getAttribute("instloginkey");
		String url = "";
		if (instloginkey == null) {
			url = "redirect:error.do";
		} else {

			//���� ���ε� �׼�
			try {
				request.setCharacterEncoding("UTF-8");

				String savePath = request.getServletContext().getRealPath("exam");
				System.out.println(savePath);

				java.io.File f = new java.io.File(savePath);
				if (!f.exists()) {
					f.mkdir();
				}

				//���ε� ������ ũ�� ����
				int sizeLimit = 1024 * 1024 * 100; //100MB

				//���ε� ���Ͽ� ���� ������ ó�� �� ���� Ȯ���� ���� ��ü ����
				//���ε� ���Ͽ� ���� ������ ����
				//���� ���� �̸��� ���� ���� ���ε忡 ����ؼ� ���� �̸� ���� ����
				MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, "UTF-8", new MyFileRenamePolicy());

				//���ε� ���Ͽ� ���� ���� Ȯ��
				String filesystemName = multi.getFilesystemName("i_file");
				String contentType = multi.getContentType("i_file");
				String originalFileName = multi.getOriginalFileName("i_file");
				//System.out.println(filesystemName);
				//System.out.println(originalFileName);

				String ei_subject_id = multi.getParameter("ei_subject_id");
				String i_exam = multi.getParameter("i_exam");

				if (   !(contentType.equals("application/x-zip-compressed"))   ) {

					//���������� ���ε�� ���Ͽ� ���� ���� �׼� �߰�
					String deleteFilename = savePath + "\\" + filesystemName;
					java.io.File temp = new java.io.File(deleteFilename);
					temp.delete();

					throw new Exception(".zip ������ �ƴմϴ�.");
				}

				AllList exam = new AllList();
				exam.setSubject_id(ei_subject_id);
				exam.setExam_date(i_exam);
				exam.setExamination(originalFileName);
				exam.setExam_zip(filesystemName);

				InstructorDAO inst = new InstructorDAO();
				int result = inst.examAdd(exam);

				if (result == 0) {
					String deleteFileName = savePath + "\\" + filesystemName;
					java.io.File temp = new java.io.File(deleteFileName);
					temp.delete();
					throw new Exception("���ε�� ������ ������ �����ͺ��̽��� ������� ���߽��ϴ�.");
				}

				url = "redirect:inst_point_exam.do";

			} catch (Exception e) {
				url = "redirect:fileuploaderror.it";
				e.printStackTrace();
			}

		}

		//��� ��ȯ->������ �̵� ����->���� �ּ�
		return url; 
	}
	public String inst_examModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session1 = request.getSession();
		String instloginkey = (String) session1.getAttribute("instloginkey");
		String url = "";
		if (instloginkey == null) {
			url = "redirect:error.do";
		} else {

			//���� ���ε� �׼�
			try {
				request.setCharacterEncoding("UTF-8");

				String savePath = request.getServletContext().getRealPath("exam");
				System.out.println(savePath);

				java.io.File f = new java.io.File(savePath);
				if (!f.exists()) {
					f.mkdir();
				}

				//���ε� ������ ũ�� ����
				int sizeLimit = 1024 * 1024 * 100; //100MB

				//���ε� ���Ͽ� ���� ������ ó�� �� ���� Ȯ���� ���� ��ü ����
				//���ε� ���Ͽ� ���� ������ ����
				//���� ���� �̸��� ���� ���� ���ε忡 ����ؼ� ���� �̸� ���� ����
				MultipartRequest multi = new MultipartRequest(request, savePath, sizeLimit, "UTF-8", new MyFileRenamePolicy());

				//���ε� ���Ͽ� ���� ���� Ȯ��
				String filesystemName = multi.getFilesystemName("u_file");
				String contentType = multi.getContentType("u_file");
				String originalFileName = multi.getOriginalFileName("u_file");
				//System.out.println(filesystemName);
				//System.out.println(originalFileName);

				String eu_subject_id = multi.getParameter("eu_subject_id");
				String u_exam = multi.getParameter("u_exam");

				if (   !(contentType.equals("application/x-zip-compressed"))   ) {

					//���������� ���ε�� ���Ͽ� ���� ���� �׼� �߰�
					String deleteFilename = savePath + "\\" + filesystemName;
					java.io.File temp = new java.io.File(deleteFilename);
					temp.delete();

					throw new Exception(".zip ������ �ƴմϴ�.");
				}

				AllList exam = new AllList();
				exam.setSubject_id(eu_subject_id);
				exam.setExam_date(u_exam);
				exam.setExamination(originalFileName);
				exam.setExam_zip(filesystemName);

				InstructorDAO inst = new InstructorDAO();
				int result = inst.examModify(exam);

				if (result == 0) {
					String deleteFileName = savePath + "\\" + filesystemName;
					java.io.File temp = new java.io.File(deleteFileName);
					temp.delete();
					throw new Exception("���ε�� ������ ������ �����ͺ��̽��� ������� ���߽��ϴ�.");
				}

				url = "redirect:inst_point_exam.do";

			} catch (Exception e) {
				url = "redirect:fileuploaderror.it";
				e.printStackTrace();
			}

		}

		//��� ��ȯ->������ �̵� ����->���� �ּ�
		return url; 
	}
	public String inst_examRemove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session1 = request.getSession();
		String instloginkey = (String) session1.getAttribute("instloginkey");
		String url = "";
		if (instloginkey == null) {
			url = "redirect:error.do";
		} else {

			try{
				request.setCharacterEncoding("UTF-8");

				String ed_subject_id = request.getParameter("ed_subject_id");
				String ed_instructor_id = request.getParameter("ed_instructor_id");

				AllList exam = new AllList();
				exam.setSubject_id(ed_subject_id);

				InstructorDAO inst = new InstructorDAO();

				List<AllList> list = inst.point_examList(ed_instructor_id, "subject_id", ed_subject_id);
				String fileName = "";
				
				if (list.size() > 0) {
					fileName = list.get(0).getExam_zip();
				}
				
				//System.out.println(fileName);
				int result = inst.examRemove(exam);

				if (list.size() > 0 && result > 0) {

					String savePath = request.getServletContext().getRealPath("exam");
					System.out.println(savePath);

					String deleteFileName = savePath + "\\" + fileName;
					java.io.File temp = new java.io.File(deleteFileName);
					temp.delete();
				}

				url = "redirect:inst_point_exam.do";

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		//��� ��ȯ->������ �̵� ����->���� �ּ�
		return url; 
	}

	/*-----------------------------------*/
	/*            inst_grade             */
	/*-----------------------------------*/

	public String inst_grade(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session1 = request.getSession();
		String instloginkey = (String) session1.getAttribute("instloginkey");
		String url = "";
		if (instloginkey == null) {
			url = "redirect:error.do";
		} else {
			request.setCharacterEncoding("UTF-8");

			String total = "0";
			String count = "0";

			List<AllList> list = null;

			InstructorDAO dao = new InstructorDAO();

			String skey = request.getParameter("skey");
			String svalue = request.getParameter("svalue");

			// �α��� ó���� ������ ���� ���̵� -> hidden form
			HttpSession session_id = request.getSession();
			String instructor_id = (String) session_id.getAttribute("instid");

			if (skey == null) {
				skey = "all";
				svalue = "";
			}

			list = dao.gradeList(instructor_id, skey, svalue);
			total = String.valueOf(dao.total("instructor_id", instructor_id));
			count = String.valueOf(list.size());

			request.setAttribute("list", list);
			request.setAttribute("count", count);
			request.setAttribute("total", total);
			request.setAttribute("skey", skey);
			request.setAttribute("svalue", svalue);

			// �α��� ó���� ������ ���� ���̵� -> hidden form
			request.setAttribute("instructor_id", instructor_id);
			
			url = "WEB-INF/source/instructor/inst_grade.jsp";
		}

		// ��� ��ȯ->������ �̵� ����->JSP ������
		return url; // .jsp
	}

	public String inst_grade_st(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session2 = request.getSession();
		String instloginkey = (String) session2.getAttribute("instloginkey");
		String url = "";
		if (instloginkey == null) {
			url = "redirect:error.do";
		} else {

			request.setCharacterEncoding("UTF-8");

			String total = "0";
			String count = "0";

			List<AllList> list = null;

			InstructorDAO dao = new InstructorDAO();

			String skey = request.getParameter("skey");
			String svalue = request.getParameter("svalue");

			// hidden form �޾ƿ��� ���� ����
			String subject_id = request.getParameter("h_subject_id");
			String course_id = request.getParameter("h_course_id");
			String cou_name = request.getParameter("h_cou_name");
			String sub_name = request.getParameter("h_sub_name");
			String sub_start_date = request.getParameter("h_sub_start_date");
			String sub_end_date = request.getParameter("h_sub_end_date");
			String po_attend = request.getParameter("h_po_attend");
			String po_write = request.getParameter("h_po_write");
			String po_practice = request.getParameter("h_po_practice");

			// �α��� ó���� ������ ���� ���̵� -> hidden form
			HttpSession session_id = request.getSession();
			String instructor_id = (String) session_id.getAttribute("instid");

			if (skey == null) {
				skey = "all";
				svalue = "";
			}

			list = dao.grade_subList(subject_id, skey, svalue);

			total = String.valueOf(dao.total("subject_id", subject_id));
			count = String.valueOf(list.size());

			request.setAttribute("list", list);
			request.setAttribute("count", count);
			request.setAttribute("total", total);
			request.setAttribute("skey", skey);
			request.setAttribute("svalue", svalue);

			// hidden form �ѱ�� ����
			request.setAttribute("subject_id", subject_id);
			request.setAttribute("course_id", course_id);
			request.setAttribute("cou_name", cou_name);
			request.setAttribute("sub_name", sub_name);
			request.setAttribute("sub_start_date", sub_start_date);
			request.setAttribute("sub_end_date", sub_end_date);
			request.setAttribute("po_attend", po_attend);
			request.setAttribute("po_write", po_write);
			request.setAttribute("po_practice", po_practice);

			// �α��� ó���� ������ ���� ���̵� -> hidden form
			request.setAttribute("instructor_id", instructor_id);

			url = "WEB-INF/source/instructor/inst_grade_st.jsp";

		}

		// ��� ��ȯ->������ �̵� ����->JSP ������
		return url; // .jsp
	}


	public String inst_gradeAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session1 = request.getSession();
		String instloginkey = (String) session1.getAttribute("instloginkey");
		String url = "";
		if (instloginkey == null) {
			url = "redirect:error.do";
		} else {

			request.setCharacterEncoding("UTF-8");

			// hidden form �޾ƿ��� ���� ����
			String cou_name = request.getParameter("h_cou_name");
			String sub_name = request.getParameter("h_sub_name");
			String sub_start_date = request.getParameter("h_sub_start_date");
			String sub_end_date = request.getParameter("h_sub_end_date");
			String po_attend = request.getParameter("h_po_attend");
			String po_write = request.getParameter("h_po_write");
			String po_practice = request.getParameter("h_po_practice");
			String course_id = request.getParameter("h_course_id");
			String subject_id  = request.getParameter("h_subject_id");
			String student_id = request.getParameter("gi_student_id");
			
			String i_score1 = request.getParameter("i_score1");
			String i_score2 = request.getParameter("i_score2");
			String i_score3 = request.getParameter("i_score3");

			AllList grade = new AllList();
			grade.setCourse_id(course_id);
			grade.setSubject_id(subject_id);
			grade.setStudent_id(student_id);
			grade.setGr_attend(i_score1);
			grade.setGr_write(i_score2);
			grade.setGr_practice(i_score3);

			InstructorDAO inst = new InstructorDAO();
			inst.gradeAdd(grade);

			// hidden form �ѱ�� ����
			request.setAttribute("course_id", course_id);
			request.setAttribute("subject_id", subject_id);
			request.setAttribute("student_id", student_id);
			request.setAttribute("cou_name", cou_name);
			request.setAttribute("sub_name", sub_name);
			request.setAttribute("sub_start_date", sub_start_date);
			request.setAttribute("sub_end_date", sub_end_date);
			request.setAttribute("po_attend", po_attend);
			request.setAttribute("po_write", po_write);
			request.setAttribute("po_practice", po_practice);

			url = String.format("redirect:inst_grade_st.do?h_course_id=%s&h_subject_id=%s&h_cou_name=%s&h_sub_name=%s&h_sub_start_date=%s&h_sub_end_date=%s&h_po_attend=%s&h_po_write=%s&h_po_practice=%s"
					, course_id, subject_id, java.net.URLEncoder.encode(cou_name, "UTF-8"), java.net.URLEncoder.encode(sub_name, "UTF-8"), sub_start_date, sub_end_date, po_attend, po_write, po_practice);
		
			}

		//��� ��ȯ->������ �̵� ����->���� �ּ�
		return url; 
	}
	public String inst_gradeModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session1 = request.getSession();
		String instloginkey = (String) session1.getAttribute("instloginkey");
		String url = "";
		if (instloginkey == null) {
			url = "redirect:error.do";
		} else {

			request.setCharacterEncoding("UTF-8");

			// hidden form �޾ƿ��� ���� ����
			String cou_name = request.getParameter("h_cou_name");
			String sub_name = request.getParameter("h_sub_name");
			String sub_start_date = request.getParameter("h_sub_start_date");
			String sub_end_date = request.getParameter("h_sub_end_date");
			String po_attend = request.getParameter("h_po_attend");
			String po_write = request.getParameter("h_po_write");
			String po_practice = request.getParameter("h_po_practice");
			String course_id = request.getParameter("h_course_id");
			String subject_id  = request.getParameter("h_subject_id");
			String student_id = request.getParameter("gu_student_id");
			
			String u_score1 = request.getParameter("u_score1");
			String u_score2 = request.getParameter("u_score2");
			String u_score3 = request.getParameter("u_score3");

			AllList grade = new AllList();
			grade.setCourse_id(course_id);
			grade.setSubject_id(subject_id);
			grade.setStudent_id(student_id);
			grade.setGr_attend(u_score1);
			grade.setGr_write(u_score2);
			grade.setGr_practice(u_score3);

			InstructorDAO inst = new InstructorDAO();
			inst.gradeModify(grade);

			// hidden form �ѱ�� ����
			request.setAttribute("course_id", course_id);
			request.setAttribute("subject_id", subject_id);
			request.setAttribute("student_id", student_id);
			request.setAttribute("cou_name", cou_name);
			request.setAttribute("sub_name", sub_name);
			request.setAttribute("sub_start_date", sub_start_date);
			request.setAttribute("sub_end_date", sub_end_date);
			request.setAttribute("po_attend", po_attend);
			request.setAttribute("po_write", po_write);
			request.setAttribute("po_practice", po_practice);

			url = String.format("redirect:inst_grade_st.do?h_course_id=%s&h_subject_id=%s&h_cou_name=%s&h_sub_name=%s&h_sub_start_date=%s&h_sub_end_date=%s&h_po_attend=%s&h_po_write=%s&h_po_practice=%s"
					, course_id, subject_id, java.net.URLEncoder.encode(cou_name, "UTF-8"), java.net.URLEncoder.encode(sub_name, "UTF-8"), sub_start_date, sub_end_date, po_attend, po_write, po_practice);
		}

		//��� ��ȯ->������ �̵� ����->���� �ּ�
		return url; 
	}
	public String inst_gradeRemove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session1 = request.getSession();
		String instloginkey = (String) session1.getAttribute("instloginkey");
		String url = "";
		if (instloginkey == null) {
			url = "redirect:error.do";
		} else {

			request.setCharacterEncoding("UTF-8");

			// hidden form �޾ƿ��� ���� ����
			String cou_name = request.getParameter("h_cou_name");
			String sub_name = request.getParameter("h_sub_name");
			String sub_start_date = request.getParameter("h_sub_start_date");
			String sub_end_date = request.getParameter("h_sub_end_date");
			String po_attend = request.getParameter("h_po_attend");
			String po_write = request.getParameter("h_po_write");
			String po_practice = request.getParameter("h_po_practice");
			String course_id = request.getParameter("h_course_id");
			String subject_id  = request.getParameter("h_subject_id");
			String student_id = request.getParameter("gd_student_id");
			
			String d_score1 = request.getParameter("d_score1");
			String d_score2 = request.getParameter("d_score2");
			String d_score3 = request.getParameter("d_score3");

			AllList grade = new AllList();
			grade.setCourse_id(course_id);
			grade.setSubject_id(subject_id);
			grade.setStudent_id(student_id);
			grade.setGr_attend(d_score1);
			grade.setGr_write(d_score2);
			grade.setGr_practice(d_score3);

			InstructorDAO inst = new InstructorDAO();
			inst.gradeRemove(grade);

			// hidden form �ѱ�� ����
			request.setAttribute("course_id", course_id);
			request.setAttribute("subject_id", subject_id);
			request.setAttribute("student_id", student_id);
			request.setAttribute("cou_name", cou_name);
			request.setAttribute("sub_name", sub_name);
			request.setAttribute("sub_start_date", sub_start_date);
			request.setAttribute("sub_end_date", sub_end_date);
			request.setAttribute("po_attend", po_attend);
			request.setAttribute("po_write", po_write);
			request.setAttribute("po_practice", po_practice);

			url = String.format("redirect:inst_grade_st.do?h_course_id=%s&h_subject_id=%s&h_cou_name=%s&h_sub_name=%s&h_sub_start_date=%s&h_sub_end_date=%s&h_po_attend=%s&h_po_write=%s&h_po_practice=%s"
					, course_id, subject_id, java.net.URLEncoder.encode(cou_name, "UTF-8"), java.net.URLEncoder.encode(sub_name, "UTF-8"), sub_start_date, sub_end_date, po_attend, po_write, po_practice);

		}

		//��� ��ȯ->������ �̵� ����->���� �ּ�
		return url; 
	}

	/*-----------------------------------*/
	/*         student_score_list  	     */
	/*-----------------------------------*/
	public String student_score_list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session2 = request.getSession();
		String stloginkey = (String) session2.getAttribute("stloginkey");
		String url = "";
		if (stloginkey == null) {
			url = "redirect:error.do";
		} else {

			request.setCharacterEncoding("UTF-8");

			
			// �α��� ó���� ������ �л� ���̵� ���� �������� �ٲٱ�(���߿�)
			HttpSession session_id2 = request.getSession();
			String id = (String) session_id2.getAttribute("stid");

			StudentsDAO dao = new StudentsDAO();
			List<AllList> list = dao.subList(id);

			
			request.setAttribute("list", list);
			request.setAttribute("svalue", id);
			url = "WEB-INF/source/student/student_score_list.jsp";
		}
		// ��� ��ȯ->������ �̵� ����->JSP ������
		return url;
	}

	public String student_score_list2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String stloginkey = (String) session.getAttribute("stloginkey");
		String url = "";
		if (stloginkey == null) {
			url = "redirect:error.do";
		} else {

			request.setCharacterEncoding("UTF-8");

			HttpSession session_id2 = request.getSession();
			String id = (String) session_id2.getAttribute("stid");

			String cou_id = request.getParameter("cou_id");
			String name = request.getParameter("name");
			String start = request.getParameter("start");
			String end = request.getParameter("end");
			String sub_count = request.getParameter("sub_count");
			String class_name = request.getParameter("class_name");

			// String st_id = request.getParameter("st_id"); �α��� ���� ������ ������ id
			// ��������

			StudentsDAO dao = new StudentsDAO();
			List<AllList> list = dao.studentGrade(cou_id, id);
		
			request.setAttribute("list", list);
			request.setAttribute("name", name);
			request.setAttribute("end", end);
			request.setAttribute("start", start);
			request.setAttribute("sub_count", sub_count);
			request.setAttribute("class_name", class_name);
			request.setAttribute("id", id);
			url = "WEB-INF/source/student/student_score_list2.jsp";
		}

		// ��� ��ȯ->������ �̵� ����->JSP ������
		return url;
	}

	/*-----------------------------------*/
	/*        textbookajax       		 */
	/*-----------------------------------*/
	public String textbookajax(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("textbookajax ȣ��");
		
		List<AllList> list = null;
		
		String textbookName = request.getParameter("textbookName");
		
		AdminBasicDAO dao = new AdminBasicDAO();
		list = dao.textbookList("textbook_name", textbookName);
		
		System.out.println(list.size());
		System.out.println(textbookName);
		
		//JSON ��ü ����
		JSONObject textbooks = new JSONObject();
		
		//JSON �迭 ��ü ����
		JSONArray textbook = new JSONArray();
		
		//JSON ��ü ����
		for (AllList t : list) {
			JSONObject txt = new JSONObject();
			txt.put("textbook_id", t.getTextbook_id());
			txt.put("textbook_name", t.getText_name());
			txt.put("publisher", t.getPublisher());
			txt.put("text_img", t.getText_img());
			textbook.add(txt);
		}
		
		textbooks.put("textbook", textbook);
		String result = textbooks.toJSONString();

		request.setAttribute("result", result);

		//��� ��ȯ->������ �̵� ����->JSP ������
		return "WEB-INF/source/admin/ajax.jsp"; //.jsp
	}

	//Sample
	///*-----------------------------------*/
	///*            �޼ҵ�1            */
	///*-----------------------------------*/
	/*public String �޼ҵ�1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//��� ��ȯ->������ �̵� ����->JSP ������
		return "WEB-INF/source/instructor/�޼ҵ�1.jsp"; //.jsp
	}

	public String �޼ҵ�2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//��� ��ȯ->������ �̵� ����->���� �ּ�
		return "redirect:�޼ҵ�1.do"; //.do
	}*/
}

