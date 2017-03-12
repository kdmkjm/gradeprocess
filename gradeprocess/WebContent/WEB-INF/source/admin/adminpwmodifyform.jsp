<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>성적처리시스템</title>

<!-- jqueryUI 환경 설정 추가 -->
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">

<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<!-- jqueryUI 환경 설정 추가 -->
<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

<script>
	$(document).ready(function() {
		
		
		
	});
	
</script>

</head>
<body>
	<div class="container">
		<div style="margin-bottom: 1%;">
			<div>
				<h1 style="font-size: x-large;">
					<a href="course_info.do"><img src="image/sist_logo.png" alt="logo"
					style="vertical-align: bottom;"></a> 성적처리시스템<small>v3.0</small>
				</h1>
			</div>


		</div>
		<div class="panel-group">
			<div class="panel panel-default">
				<div class="panel-heading">비밀번호 변경</div>
				<div class="panel-body">
					<form role="form" action="adminpwmodify.do" method="post">
					
						<%-- 수정 액션을 위한 직원번호 폼 추가 --%>
						<%-- 
						value="${emp.employeeId}" 속성을 지정하면
						서블릿에서 전달받은 Employee 객체의 employeeId 속성의 값을 
						value="" 속성의 값으로 지정하게 된다.
						--%>
						<div class="form-group">
							<label for="employeeId">관리자ID:</label> <input type="text"  value="${all.admin_id}"
								class="form-control" id="adminid" name="adminid"
								placeholder="직원번호 (수정시 사용)" required="required" readonly="readonly">
						</div>
						
						<div class="form-group">
							<label for="pw">현재 비밀번호:</label> <input type="Password"  value=""
								class="form-control" id="pw" name="pw"
								placeholder="비밀번호 (숫자 7자리 이내)" maxlength="7" required="required">
						</div>
						
						<div class="form-group">
							<label for="newpw">새로운 비밀번호:</label> <input type="Password"  value=""
								class="form-control" id="newpw" name="newpw"
								placeholder="비밀번호 (숫자 7자리 이내)" maxlength="7" required="required">
						</div>
						
						<div class="form-group">
							<label for="newpw1">새로운 비밀번호 확인:</label> <input type="Password"  value=""
								class="form-control" id="newpw1" name="newpw1"
								placeholder="비밀번호 (숫자 7자리 이내)" maxlength="7" required="required">
						</div>

						<button type="submit" class="btn btn-default">Submit</button>
						
					</form>

				</div>
			</div>
		</div>
	</div>

</body>
</html>