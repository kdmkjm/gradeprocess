<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>SIST | 성적관리시스템</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="http://fonts.googleapis.com/earlyaccess/nanumgothic.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/vendors/font-awesome/css/font-awesome.css">
    <!-- NProgress -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/vendors/nprogress/nprogress.css">
    <!-- Custom Theme Style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/build/css/custom.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/jquery${pageContext.request.contextPath}.js"></script>

<style>
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
.right_col {
	padding-bottom: 10px;
}
@CHARSET "UTF-8";
table, #info-title {
    font-family: 'Nanum Gothic', serif;
    font-size: 1em;
}
#text_box1 {
  display: inline-block;
   overflow: auto;
}
#text_box2 {
  display: inline-block;
   overflow: auto;
   padding-left: 50px;
}
</style>
<script>
$(document).ready(function(){
	$(".btn_info").on("click",function(){
		$("#modal_st_name").text($("#st_name").text());
		$("#cou_name").text($(this).parents("tr").children().eq(1).text());
		$("#cou_start").text($(this).parents("tr").children().eq(2).text());
		$("#cou_end").text($(this).parents("tr").children().eq(3).text());
		$("#state").text($(this).parents("tr").children().eq(4).text());
		$("#fail_date").text($(this).parents("tr").children().eq(5).text());
		$("#studentlistModal").modal();
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
                    <input type="hidden" name="hidden_stId" value="${title_id}">
                  	<input type="hidden" name="hidden_stName" value="${title_name}">
                  	<input type="hidden" name="hidden_stPhone" value="${title_phone}">
                  	<input type="hidden" name="hidden_stRegister" value="${title_register}">
                  </div>
                </div>
                <!-- 검색 선택 -->
                <div class="col-md-2.5 col-sm-2.5 col-xs-2.5 form-group pull-right">
      				<select class="form-control top_search_select" name="skey" id="skey">
      					<option value="all">전체</option>
						<option value="sc_cou-id">과정ID</option>
						<option value="sc_cou-name">과정명</option>
      				</select>
      			</div>
              </form>
              </div>
            </div>
              <div class="clearfix"></div>
              <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>수강생 조회</h2>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                    <!--타이틀정보-->
                    <p id="info-title"><strong>수강생ID: </strong>${title_id}<strong> 수강생 명: </strong>
			<span id="st_name">${title_name}</span><strong> 전화번호: </strong>${title_phone}
			<strong> 등록일: </strong>${title_register}</p>
                    <div class="table-responsive">
                      <table class="table table-default jambo_table bulk_action">
					<!-- class="table table-bordered" -->
					<thead>
						<tr>
							<th>과정ID</th>
							<th>과정명</th>
							<th>과정시작</th>
							<th>과정종료</th>
							<th>과목명</th>
							<th>과목시작</th>
							<th>과목종료</th>
							<th>수강상태</th>
							<th>중도탈락일</th>
							<!-- <th>개설과목수</th> -->
							<th>강사명</th>
							<th>강의실명</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="c" items="${list}">
						<tr>
							<td>${c.course_id}</td>
							<td>${c.cou_name}</td>
							<td>${c.cou_start_date}</td>
							<td>${c.cou_end_date}</td>
							<td>${c.sub_name}</td>
							<td>${c.sub_start_date}</td>
							<td>${c.sub_end_date}</td>
							<td>${finArr[c.fin]}</td>
							<td>${(empty c.mid_fail_date)? "X":c.mid_fail_date}</td>
							<!-- <td>쿼리 추가 필요</td> -->
							<td>${c.inst_name}</td>
							<td>${c.class_name}</td>
							<!-- <td>
								<button type="button" class="btn btn-default btn-xs btn_info">조회</button>
							</td> -->
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
		<!-- 수강생 조회 -->
		<div id="studentlistModal" class="modal fade" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title-text">수강생 조회</h4>
					</div>
					<form method="post">
						<div class="modal-body">
							<div class="text-box-bg">
                				<div class="form-group" id="text_box1">
									수강생명 : <span id="modal_st_name"></span><br>
									수료여부 : <span id="state"></span><br>
									중도탈락날짜 : <span id="fail_date"></span>
								</div>
								<div class="form-group" id="text_box2">
									과정명 : <span id="cou_name"></span><br>
									과정시작 : <span id="cou_start"></span><br>
									과정종료 : <span id="cou_end"></span>
								</div>
							</div>
							<div id="box04">
								<table class="table table-striped">
									<tr>
										<th>과목ID</th>
										<th>과목명</th>
										<th>과목시작</th>
										<th>과목종료</th>
									</tr>
									<tr>
										<td>sub_002</td>
										<td>Java</td>
										<td>2016-05-05</td>
										<td>2016-07-04</td>
									</tr>
									<tr>
										<td>sub_009</td>
										<td>HTML</td>
										<td>2016-07-05</td>
										<td>2016-09-03</td>
									</tr>
									<tr>
										<td>sub_016</td>
										<td>JavaScript</td>
										<td>2016-09-04</td>
										<td>2016-11-01</td>
									</tr>
								</table>
							</div>
						</div>
							<!-- 닫기 버튼 클릭시 현재 모달창을 닫는다. -->
							<div class="modal-footer">
								<button type="button" class="btn btn-default btn_color"
									data-dismiss="modal">Close</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!-- +++++++++++++++++++ modal part +++++++++++++++++++ -->

    <!-- jQuery -->
    <script src="${pageContext.request.contextPath}/bootstrap/vendors/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap -->
    <script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <!-- FastClick -->
    <script src="${pageContext.request.contextPath}/bootstrap/vendors/fastclick/lib/fastclick.js"></script>
    <!-- NProgress -->
    <script src="${pageContext.request.contextPath}/bootstrap/vendors/nprogress/nprogress.js"></script>
    <!-- Custom Theme Scripts -->
    <script src="${pageContext.request.contextPath}/bootstrap/build/js/custom.js"></script>
</body>
</html>

