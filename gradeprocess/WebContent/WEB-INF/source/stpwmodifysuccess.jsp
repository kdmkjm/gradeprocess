<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>PW Modify Success</title>

<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

</head>
<body>
	<div class="modal-dialog">

			<div>
				<h1 style="font-size: x-large;">
					<img src="${pageContext.request.contextPath}/image/sist_logo.png" alt="logo"
					style="vertical-align: bottom;">성적처리시스템<small>v3.0</small>
				</h1>
			</div>

		<!-- Modal content-->
		<div class="modal-content">

			<div class="modal-header" style="padding: 35px 50px;">
				<h4>
					<span class="glyphicon glyphicon-lock"></span> 비밀번호 변경 성공
				</h4>
				<label>비밀번호가 수정되었습니다.</label>
			</div>
			<div class="modal-body" style="padding: 40px 50px;">
					<div class="form-group">
					</div>
					<a href="student_score_list.do" class="btn btn-default btn-block">Main</a>
			</div>
			<div class="modal-footer"></div>
		</div>

	</div>
</body>
</html>