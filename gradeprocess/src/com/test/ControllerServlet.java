package com.test;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


//javax.servlet.http.HttpServlet 클래스를 상속 받은 클래스는 서블릿 클래스가 된다.
public class ControllerServlet extends HttpServlet {

	private static final long serialVersionUID = 2768162625969196558L;

	//서블릿에는 main() 메소드 대신 doGet(), doPost() 메소드가 있다.
	//웹서버가 클라이언트에게 요청을 받으면 doGet() 또는 doPost() 메소드가 자동 호출된다.
	//주의) 서블릿 클래스는 웹페이지가 아니므로 브라우저에서 직접 요청 불가 -> 톰캣서버에 서블릿 주소와 클라이언트 요청 주소를 매핑하는 과정 필요 -> web.xml
	//doGet() 메소드의 매개변수명을 request, response로 수정할 것
	@Override
	protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
	
		//사용자 요청 주소 분석
		String uri = request.getRequestURI();
		System.out.println(uri); //uri : /controllerservlet_20170131/memberlist.do
		String methodName = uri.substring(uri.lastIndexOf("/")+1, uri.lastIndexOf("."));
		//System.out.println(methodName); //methodName : memberlist 메소드 이름만 저장
				
		Action action = new Action();
		/*
		if (methodName.equals("memberlist")) {
			action.memberlist();
		}
		if (methodName.equals("memberinsert")) {
			action.memberinsert();
		}
		*/
		//->주소 분석 통해서 얻은 메소드 이름을 가지고 위임 호출 -> invoke()
		String result = "WEB-INF/source/error.jsp";
		java.lang.reflect.Method m;
		try {
			m = Action.class.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			result = (String)m.invoke(action, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//페이지 이동
		if (result.contains("redirect:")) {
			//서블릿 주소 요청
			response.sendRedirect(result.substring(9)); // redirect:memberlist.do중 redirect:다음부터인 memberlist.do를 전달
		} else {
			//JSP 페이지 요청
			RequestDispatcher dispatcher = request.getRequestDispatcher(result);
			dispatcher.forward(request, response);
		}
		
	}

	//doPost() 메소드의 매개변수명을 request, response로 수정할 것
	@Override
	protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	
}
