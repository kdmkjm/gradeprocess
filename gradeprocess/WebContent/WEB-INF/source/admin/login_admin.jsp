<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>


<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>성적관리시스템</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href="/gradeprocess/css/login.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="/gradeprocess/jquery/gradeprocess.js"></script>
</head>
<style>
.panel-login>.panel-heading a#admin-form-link {
	color: #fff;/*활성화 상태*/
}
.panel-login>.panel-heading a#student-form-link
, #teacher-form-link
, #admin-form-link {
	width: 100%;
	text-align: center;
}
.panel-login>.panel-heading .student {
  border-top-left-radius: 5px;
  font-size: 0.9em;
  padding: 20px 30px;
}
.panel-login>.panel-heading .teacher {
  font-size: 0.9em;
  padding: 20px 30px;
}
.panel-login>.panel-heading .admin {
  font-size: 0.9em;
  border-top-right-radius: 5px;
  padding: 20px 30px;
	background: #2d3b55; /*활성화 상태*/
}
</style>
<body>
<div class="container">
	<div class="container">
   <div class="row">
    <div class="col-md-6 col-md-offset-3">
      <div class="panel panel-login">
				<div class="panel-heading">
          <div class="row">
            <div class="col-xs-4 tabs">
              <a href="login_student.do"  id="student-form-link"><div class="student">수강생</div></a>
            </div>
            <div class="col-xs-4 tabs">
              <a href="login_inst.do" id="teacher-form-link"><div class="teacher">강사</div></a>
            </div>
            <div class="col-xs-4 tabs">
              <a href="login_admin.do" class="active" id="admin-form-link"><div class="admin">관리자</div></a>
            </div>
          </div>
        </div>
        <div class="panel-body">
          <div class="row">
            <div class="col-lg-12">
              <form id="login-form" action="login.do" method="post" role="form" style="display: block;">
                <h2>LOGIN</h2>
                  <div class="form-group">
                    <input type="text" name="id" id="id" tabindex="1" class="form-control" placeholder="Username" value="">
                  </div>
                  <div class="form-group">
                    <input type="password" name="pw" id="pw" tabindex="2" class="form-control" placeholder="Password">
                  </div>

                  <div class="col-xs-6 form-group pull-right">
                        <input type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-login" value="Log In">
                  </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<footer>
    <div class="container">
        <div class="col-md-10 col-md-offset-1 text-center">
            <h6 style="font-size:14px;font-weight:100;color: #fff;">Coded with <i class="fa fa-heart red" style="color: #BC0213;"></i> by <a href="http://naver.com" style="color: #fff;" target="_blank">SIST</a></h6>
        </div>
    </div>
</footer>
</div>
</body>
</html>
