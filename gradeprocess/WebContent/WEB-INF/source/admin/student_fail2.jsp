<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>SIST | 성적관리시스템</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="http://fonts.googleapis.com/earlyaccess/nanumgothic.css">
        <link href="${pageContext.request.contextPath}/bootstrap/vendors/font-awesome/css/font-awesome.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="${pageContext.request.contextPath}/bootstrap/vendors/nprogress/nprogress.css" rel="stylesheet">
    <!-- Custom Theme Style -->
    <link href="${pageContext.request.contextPath}/bootstrap/build/css/custom.css" rel="stylesheet">
    <script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/jquery${pageContext.request.contextPath}.js"></script>
	<!-- 달력을 위한 소스 -->
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	
<style type="text/css">
.top_search_select {
  border-radius: 25px;
  border: 1px solid rgba(221, 226, 232, 0.49);
  border-left: 0;
  box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
  color: #93A2B2;
  margin-bottom: 0 !important;
  font-size: 1.8ex;
}
.table-responsive {
	max-height: 600px;
}

.container {
	height: 100%;
}
.right_col {
	padding-bottom: 10px;
}
@CHARSET "UTF-8";
table, #info-title {
    font-family: 'Nanum Gothic', serif;
    font-size: 1em;
}
</style>
<!-- ========================== jQuery ========================== -->
<script>
$(document).ready(function(){
	 //과정탈락
	$(".btn_fail").on("click",function(){
		$("#st_id").text($(this).parents("tr").children().eq(0).text());
		$("#st_name").text($(this).parents("tr").children().eq(1).text());
		$("#st_pw").text($(this).parents("tr").children().eq(2).text());
		$("#st_phone").text($(this).parents("tr").children().eq(3).text());
		$("#cou_count").text($(this).parents("tr").children().eq(4).text());
		$("#hidden_st_id").val($(this).parents("tr").children().eq(0).text());
		$("#studentfailModal").modal();
	});
	// 달력
	$(".datepicker").datepicker({
		changeMonth : true,
		changeYear : true,
		dateFormat : "yy-mm-dd"
	});
 	// 검색
	$("#skey").find("option[value='${skey}']").attr("selected", "selected");
	$("#svalue").val("${svalue}");
	$("#skey").on("change", function() {
		$("#svalue").val("");
	});
	
	

});
</script>

</head>

  <body class="nav-md">
    <div class="container body">
      <div class="main_container">
        <div class="col-md-3 left_col">
          <div class="left_col scroll-view">
            <div class="navbar nav_title" style="border: 0;">
              <a href="course_info.do" class="site_title"><img src="${pageContext.request.contextPath}/image/sist_symbol.png" id="sistlogo"> 쌍용교육센터</a>
            </div>

            <div class="clearfix"></div>

            <!-- menu profile quick info -->
            <div class="profile clearfix">
              <div class="profile_pic">
                <img src="${pageContext.request.contextPath}/image/profile_photo_admin.png" alt="profile_photo" class="img-circle profile_img">
              </div>
              <div class="profile_info">
                <span>관리자</span>
                <h2>Admin</h2>
              </div>
            </div>
            <!-- /menu profile quick info -->
            <br />
            <!-- sidebar menu -->
            <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
              <div class="menu_section">

                <ul class="nav side-menu">
                  <li><a href="course_info.do"><i class="fa fa-sitemap"></i> 기본정보관리 <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="course_info.do">과정 정보</a></li>
                      <li><a href="subject_info.do">과목 정보</a></li>
                      <li><a href="classroom_info.do">강의실 정보</a></li>
                      <li><a href="textbook_info.do">교재 정보</a></li>
                    </ul>
                  </li>
                  <li><a href="instructor_list.do"><i class="fa fa-graduation-cap"></i> 강사 계정 관리 </a></li>
                  <li><a href="course_list.do"><i class="fa fa-cogs"></i> 개설 과정 관리 </a></li>
                  <li><a href="student_list.do"><i class="fa fa-users"></i> 수강생 관리 </a></li>
                  <li><a href="grade_list.do"><i class="fa fa-pencil"></i> 성적 조회 <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="grade_list.do">과정성적조회</a></li>
                      <li><a href="grade_person.do">개인 성적 조회</a></li>
                    </ul>
                  </li>
                </ul>
              </div>
            </div>
            <!-- /sidebar menu -->
          </div>
        </div>

        <!-- top navigation -->
        <div class="top_nav">
          <div class="nav_menu">
            <nav>
              <div class="nav toggle">
                <a id="menu_toggle"><i class="fa fa-bars"></i></a>
              </div>
              <ul class="nav navbar-nav navbar-right">
                <li class="">
                  <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                    <img src="${pageContext.request.contextPath}/image/profile_photo_admin.png" alt="">Admin님
                    <span class=" fa fa-angle-down"></span>
                  </a>
                  <ul class="dropdown-menu dropdown-usermenu pull-right">
                    <li><a href="adminpwmodifyform.do?adminid=${loginkey}"> 비밀번호 변경</a></li>
                    <li><a href="logout.do"><i class="fa fa-sign-out pull-right"></i> Log Out</a></li>
                  </ul>
                </li>
              </ul>
            </nav>
          </div>
        </div>
        <!-- /top navigation -->

        <!-- page content -->
        <div class="right_col" role="main">
           <!--+++++++++++++++++ panel-header ++++++++++++++++++-->
            <div class="page-title">
              <div class="title_left">
                <h3>수강생 관리 <small></small></h3>
              </div>
              <div class="title_right">
              <form method="post" class="">
              	<!-- badge -->
                <div class="count col-md-2.5 col-sm-2.5 col-xs-2.5">
                  <span class="badge bg-green pull-right">${count}/${total}</span>
                  <button type="button" class="btn btn-sm btn-round btn-info pull-right" style="margin-top: 12px;">Count</button>
                </div>
                <!--검색-->
                <div class="col-md-7 col-sm-7 col-xs-7 form-group pull-right top_search">
                  <div class="input-group">
                    <input type="text" class="form-control" id="svalue" name="svalue" placeholder="Search for...">
                    <span class="input-group-btn">
                      <button class="btn btn-default" type="submit">Search</button>
                    </span>
                    <input type="hidden" name="hidden_couId" value="${cou_id}">
                  	<input type="hidden" name="hidden_couName" value="${cou_name}">
                  	<input type="hidden" name="hidden_couStart" value="${cou_start}">
                  	<input type="hidden" name="hidden_couEnd" value="${cou_end}">
                  </div>
                </div>
                <!-- 검색 선택 -->
                <div class="col-md-2.5 col-sm-2.5 col-xs-2.5 form-group pull-right">
      				<select class="form-control top_search_select" name="skey" id="skey">
      					<option value="all">전체</option>
						<option value="sc_st-id">수강생ID</option>
						<option value="sc_st-name">수강생 명</option>
      				</select>
      			</div>
              </form>
              </div>
            </div>
              <div class="clearfix"></div>
              <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>과정탈락</h2>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                    <!--타이틀정보-->
                    <p id="info-title"> <strong>과정ID: </strong><span id="cou_id">${cou_id}</span><strong> 과정명: </strong>
       <span id="cou_name">${cou_name}</span><strong> 과정시작: </strong><span id="cou_start">${cou_start}</span><strong> 과정종료:</strong><span id="cou_end">${cou_end}</span>
 		</p>
                    <div class="table-responsive">
                      <table class="table table-default jambo_table bulk_action">
					<!-- class="table table-bordered" -->
					<thead>
						<tr>
							<th>수강생ID</th>
							<th>수강생명</th>
							<!-- <th>주민번호뒷자리</th> -->
							<th>전화번호</th>
							<th>수강횟수</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="s" items="${listStudent}">
						<tr>
							<td>${s.student_id}</td>
							<td>${s.st_name}</td>
							<%-- <td>${s.st_pw}</td> --%>
							<td>${s.st_phone}</td>
							<td>${s.cou_count}</td>
							<td>
			<button type="button" class="btn btn-default btn-xs btn_fail ${(empty s.mid_fail_date)?'':'disabled'}" ${(empty s.mid_fail_date)?'':'disabled'}>과정탈락</button>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				      </table>
                    </div>
                  </div>
                </div>
              </div>
          </div><!-- end page content -->
        </div>
        <!-- /page content -->

        <!-- footer content -->
        <footer>
          <div class="pull-right">
            Design by <a href="https://www.sist.co.kr">SIST</a>
          </div>
          <div class="clearfix"></div>
        </footer>
        <!-- /footer content -->
      </div>

<!-- +++++++++++++++++++ modal part +++++++++++++++++++ -->
		<!-- 과정 탈락 -->
		<div id="studentfailModal" class="modal fade" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<!-- Modal header -->
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title-text">과정 탈락</h4>
					</div>

					<form action="student_course_fail.do" method="post">
						<!-- Modal body -->
						<div class="modal-body">
							<div class="form-group text-box-bg">
								<strong>수강생ID : </strong><span id="st_id"></span><br><strong> 수강생 명 : </strong><span id="st_name"></span><br>
								<strong> 주민번호 뒷자리 : </strong><span id="st_pw"></span><br><strong> 전화번호 : </strong><span id="st_phone"></span><br>
								<strong> 수강횟수 : </strong><span id="cou_count"></span>
								 <input type="hidden" id="hidden_couId2" name="hidden_couId2" value="${cou_id}">
								 <input type="hidden" id="hidden_st_id" name="st_id" value="">
							</div>
							<div class="form-group" id="date">
								<label for="mid_fail_date">과정 탈락 날짜 <img src="${pageContext.request.contextPath}/image/calendar.jpg" width="20px;" height="20px"></label>
								<input type="text" class="form-control datepicker" id="mid_fail_date" name="mid_fail_date" placeholder="YYYY-MM-DD" required>
					
							</div>
							<p class="text-modal-bottom">해당 수강생을 과정 탈락하시겠습니까?</p>
							
						</div> <!--end Modal body-->
							<!-- footer -->
							<div class="modal-footer">
                  	<input type="hidden" name="hidden_couName" value="${cou_name}">
                  	<input type="hidden" name="hidden_couStart" value="${cou_start}">
                  	<input type="hidden" name="hidden_couEnd" value="${cou_end}">
                 	<input type="submit" class="btn-submit" value="확인">
								<button type="button" class="btn-cancle" data-dismiss="modal">취소</button>
							</div> <!--end footer-->
							</form>
						</div> <!--end modal-content-->
					</div> <!--end modal-dialog-->
				</div> <!--end Modal-->

<!-- +++++++++++++++++++ modal part +++++++++++++++++++ -->

    <!-- jQuery -->
    <script src="${pageContext.request.contextPath}/bootstrap/vendors/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap -->
    <script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <!-- FastClick -->
    <script src="${pageContext.request.contextPath}/bootstrap/vendors/fastclick/lib/fastclick.js"></script>
    <!-- NProgress -->
    <script src="${pageContext.request.contextPath}/bootstrap/vendors/nprogress/nprogress.js"></script>
    <!-- iCheck -->
    <!--<script src="../bootstrap/vendors/iCheck/icheck.min.js"></script>-->

    <!-- Custom Theme Scripts -->
    <script src="${pageContext.request.contextPath}/bootstrap/build/js/custom.js"></script>
    <!-- 달력을 위한 소스 -->
	<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    
</body>
</html>

