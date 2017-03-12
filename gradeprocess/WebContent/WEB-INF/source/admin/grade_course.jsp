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
<script>
$(document).ready(function(){
	 
	// 검색
	$("#skey").find("option[value='${skey}']").attr("selected", "selected");
	$("#svalue").val("${svalue}");
	$("#skey").on("change", function() {
		$("#svalue").val("");
	});
	
	//조회 버튼 클릭시 해당 정보를 다음 페이지에 전달
	$(".btn_info").on("click",function(){
		$("#hidden_form .hidden_subject_id").val($(this).parents("tr").children().eq(0).text());
		$("#hidden_form .hidden_sub_name").val($(this).parents("tr").children().eq(1).text());
		$("#hidden_form .hidden_sub_start_date").val($(this).parents("tr").children().eq(2).text());
		$("#hidden_form .hidden_sub_end_date").val($(this).parents("tr").children().eq(3).text());
		$("#hidden_form .hidden_po_attend").val($(this).parents("tr").children().eq(4).text());
		$("#hidden_form .hidden_po_write").val($(this).parents("tr").children().eq(5).text());
		$("#hidden_form .hidden_po_practice").val($(this).parents("tr").children().eq(6).text());
	});
	
	  // 교재 사진 모달
	  $(".textbook").on("click", function() {
		  var textbookName = $(this).text();
		  console.log(textbookName);
		  $.post("textbookajax.do",{textbookName:textbookName},function(data) {
			  var textbooks = JSON.parse(data);//JSON obj
			  var textbook = textbooks.textbook;//Array obj
			  var textbook_modal = "";
			  var len = textbook.length;
			  for (var i=0; i<len; ++i) {
				  
				  textbook_modal += '				<img src="/gradeprocess/image/'+textbook[i].text_img+'" alt="'+textbook[i].textbook_name+'">';
				  textbook_modal += '				<p><strong><span id="text_name">'+textbook[i].textbook_name+'</span>&nbsp;/&nbsp;<span id="publisher">'+textbook[i].publisher+'</span></strong></p>';
			  }
			  $("#textbook-modal-body").html(textbook_modal);//교재 정보 modal 바디 추가
		  });
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
                      <li><a href="grade_list.do">과정 성적조회</a></li>
                      <li><a href="grade_person.do">개인 성적조회</a></li>
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
                <h3>성적 조회 <small></small></h3>
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
                    <%--hidden에 넣어버리기--%>
					<input type="hidden" id="course_id" name="course_id" value="${course_id}">
					<input type="hidden" id="course_name" name="course_name" value="${course_name}">
					<input type="hidden" id="course_start_date" name="course_start_date" value="${course_start_date}">
					<input type="hidden" id="course_end_date" name="course_end_date" value="${course_end_date}">
                  </div>
                </div>
                <!-- 검색 선택 -->
                <div class="col-md-2.5 col-sm-2.5 col-xs-2.5 form-group pull-right">
      				<select class="form-control top_search_select" name="skey" id="skey">
      					<option value="grade_course">전체</option>
							<option value="subject_id">과목ID</option>
							<option value="sub_name">과목명</option>
      				</select>
      			</div>
              </form>
              </div>
            </div>
              <div class="clearfix"></div>
              <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>과정 성적조회</h2>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                    <!--타이틀정보-->
                    <p id="info-title"><strong>과정ID: <span>${course_id}</span> 과정명: <span>${course_name}</span> 과정시작: <span>${course_start_date}</span> 과정종료: <span>${course_end_date}</span></strong></p>
                    <div class="table-responsive">
                      <table class="table table-default jambo_table bulk_action">
					<!-- class="table table-bordered" -->
					<thead>
						<tr>
							<th>과목ID</th>
							<th>과목명</th>
							<th>과목 시작</th>
							<th>과목 종료</th>
							<th>출결배점</th>
							<th>필기배점</th>
							<th>실기배점</th>
							<th>성적 등록여부</th>
							<th>교재명</th>
							<th>강사명</th>
							<th>시험문제</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
<!-- 
						<tr>
							<td>sub_001</td>
							<td>Oracle</td>
							<td>2017-01-02</td>
							<td>2017-05-02</td>
							<td>20</td>
							<td>40</td>
							<td>40</td>
							<td>O</td>
							<td><a href="#" data-toggle="modal"
								data-target="#bookPictureModal2">오라클 SQL 튜닝</a></td>
							<td>강동원</td>
							<td><a href="#">20160707_Java프로그래밍.zip</a></td>
							<td>
							<div class="btn-group">
										<a href="grade_course2.do" class="btn btn-default btn-xs">조회</a>
								</div>
								</td>
						</tr> -->
						
							<c:forEach var="s" items="${list}">
							<tr>
								<td>${s.subject_id}</td>
								<td>${s.sub_name}</td>
								<td>${s.sub_start_date}</td>
								<td>${s.sub_end_date}</td>
							    <td>${(empty s.po_attend)? "X" : s.po_attend}</td>
								<td>${(empty s.po_write )?  "X" :s.po_write}</td>
								<td>${(empty s.po_practice)? "X": s.po_practice}</td>
							    <td>${(s.grade_ox == 0)? "X" :"O"}</td> 
							    <!-- 책 이미지 -->				  
								<td><a href="#" data-toggle="modal" data-target="#bookPictureModal" class="textbook">${s.text_name}</a></td>
								<td>${s.inst_name}</td>
								<!--문제 다운로드-->
								<td><c:choose>
										<c:when test="${empty s.examination}">
											<span class="err">X</span>
										</c:when>
										<c:otherwise><a href="${pageContext.request.contextPath}/exam/${s.exam_zip}">${s.examination}</a></c:otherwise>
									</c:choose></td>
								<td><form action="grade_course2.do" method="post" id="hidden_form">
								<div class="btn-group" id="btn-group"><button type="submit" class="btn btn-default btn-xs btn_info">조회</button></div>
							<input type="hidden" class="hidden_subject_id" name="subject_id">
							<input type="hidden" class="hidden_sub_name" name="sub_name">
							<input type="hidden" class="hidden_sub_start_date" name="sub_start_date">
							<input type="hidden" class="hidden_sub_end_date" name="sub_end_date">
							<input type="hidden" class="hidden_po_attend" name="po_attend">
							<input type="hidden" class="hidden_po_write" name="po_write">
							<input type="hidden" class="hidden_po_practice" name="po_practice">
							</form></td>
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
	
		<!-- modal part -->
	<!-- bookPictuer Modal -->
	<div id="bookPictureModal" class="modal fade" role="dialog">
		<div class="modal-dialog modal-sm">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title-text">Book Picture</h4>
				</div>
				<div class="modal-body text-center" id="textbook-modal-body">
					<!-- 교재 표지 textbook 테이블에 컬럼 생성 후 작성 필요
					<img src="" alt="java.gif" id="textbook_img" class="textbook_img">
					<p>
						<strong><span id="text_name"></span>&nbsp;/&nbsp;<span id="publisher"></span></strong>
					</p> -->
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn_color" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
<!-- +++++++++++++++++++ script part +++++++++++++++++++ -->
    <!-- jQuery -->
    <script src="${pageContext.request.contextPath}/bootstrap/vendors/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <!-- FastClick -->
    <script src="${pageContext.request.contextPath}/bootstrap/vendors/fastclick/lib/fastclick.js"></script>
    <!-- NProgress -->
    <script src="${pageContext.request.contextPath}/bootstrap/vendors/nprogress/nprogress.js"></script>
    <!-- iCheck -->
    <!--<script src="${pageContext.request.contextPath}/bootstrap/vendors/iCheck/icheck.min.js"></script>-->

    <!-- Custom Theme Scripts -->
    <script src="${pageContext.request.contextPath}/bootstrap/build/js/custom.js"></script>
</body>
</html>
