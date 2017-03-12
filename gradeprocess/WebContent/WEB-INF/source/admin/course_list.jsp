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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
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
<script>
<!-- ========================== jQuery ========================== -->
$(document).ready(function(){
	
	var currentPage = ${page};
	console.log(currentPage);
	
	
	//추가 버튼
	 $("#btn_add").on("click",function(){
	   $("#addModal").modal();
	 });
	
	// 달력
	$(".datepicker").datepicker({
		changeMonth : true,
		changeYear : true,
		dateFormat : "yy-mm-dd"
	});
	
	//조회 버튼
	$(".btn_info").on("click",function(){
		var cou_id = $(this).parents("tr").children().eq(0).text();
	    $("#info_id").text(cou_id);
	    $("#info_name").text($(this).parents("tr").children().eq(1).text());
	    $("#info_start").text($(this).parents("tr").children().eq(2).text());
	    $("#info_end").text($(this).parents("tr").children().eq(3).text());
	    $("#info_sub_total").text($(this).parents("tr").children().eq(4).text());
	    $("#info_st_total").text($(this).parents("tr").children().eq(5).text());
	    $("#info_classroom").text($(this).parents("tr").children().eq(6).text());
	    
		$("#hidden_couId").val(cou_id);
		$("#hidden_couName").val($(this).parents("tr").children().eq(1).text());
		$("#hidden_couStart").val($(this).parents("tr").children().eq(2).text());
		$("#hidden_couEnd").val($(this).parents("tr").children().eq(3).text());
		
		/* Ajax, JSOM */
	  	$.post("course_list_infoajax.do",{cou_id:cou_id},function(data) {
	  		var modalJson = JSON.parse(data);//JSON obj
	  		console.log(data);
	  		console.log(modalJson);
	  		var subjects = modalJson.course_modal[0];//Array obj subject
	  		var students = modalJson.course_modal[1];//Array obj student
 
	  		var subjectList = subjects.subject;
	  		var studentList = students.student;
	  		
	  		var subject_table = '';
	  		var student_table = '';
	  		var len_st = studentList.length;
	  		var len_sub = subjectList.length;
	  		
	  		if (len_sub == 0) {
	  			subject_table = '<tr><td colspan="5">등록된 과목 정보가 없습니다.</td></tr>';
	  		} else {
	  			//과목정보 모달 테이블 출력
		  		for (var i=0; i<len_sub; ++i) {
		  			subject_table += '                  <tr>';
		  			subject_table += '                    <td>'+subjectList[i].sub_name+'</td>';
		  			subject_table += '                    <td>'+subjectList[i].sub_start+'</td>';
		  			subject_table += '                    <td>'+subjectList[i].sub_end+'</td>';
		  			subject_table += '                    <td>'+subjectList[i].inst_name+'</td>';
		  			subject_table += '                    <td>'+subjectList[i].text_name+'</td>';
		  			subject_table += '                  </tr>';
		  		}
	  		}
	  		if (len_st == 0) {
	  			student_table = '<tr><td colspan="4">등록된 수강생 정보가 없습니다.</td></tr>';
	  		} else {
		  		//수강생정보 모달 테이블 출력
		  		for (var i=0; i<len_st; ++i) {
		  			student_table += '                  <tr>';
		  			student_table += '                    <td>'+studentList[i].st_name+'</td>';
		  			student_table += '                    <td>'+studentList[i].st_phone+'</td>';
		  			student_table += '                    <td>'+studentList[i].st_regist+'</td>';
		  			student_table += '                    <td>'+studentList[i].st_fin+'</td>';
		  			student_table += '                  </tr>';
		  		}
	  		}
	  		
	  		$("#subject_tableBody").html(subject_table);//조회 modal 과목 테이블
	  		$("#student_tableBody").html(student_table);//조회 modal 수강생 테이블
	    });
		
	  	var toDay = new Date(); // 오늘 날자 반환
	  	var endDay = new Date($(this).parents("tr").children().eq(3).text()); // 과정종료일 반환
	  	// 과정 종료일 - 오늘날짜
	  	var resultDay = (endDay.getTime()/1000/60/60/24).toFixed(0) - (toDay.getTime()/1000/60/60/24).toFixed(0);
	  	if (resultDay < 0) {
	  		$("#btn_subAdd_div").empty();//과정 이후면 과목추가 버튼 삭제
	  	} else {
	  		$("#btn_subAdd_div").html('<button type="submit" class="btn-submit" id="btn_subAdd"><span class="glyphicon glyphicon-plus-sign" style="color:#00d1e6; vertical-align:middle"></span> 과목추가</button>');//과정 이전면 과목추가 버튼 삭제
	  	}
	    $("#infoModal").modal();
	});
	
	// 수정 버튼
	$(".btn_modify").on("click",function(){
		var thisElement = $(this).parents("tr").children()
		$("#modify_id").val(thisElement.eq(0).text());
		$("#modify_start").val(thisElement.eq(2).text());
		$("#modify_end").val(thisElement.eq(3).text());
		
		// 강의실 seleted하기
		var thisClassroomName = thisElement.eq(6).text();
		$("#modify_classroom option[id='"+thisClassroomName+"']").prop("selected", true);
		// 과정명 seleted하기
		var thiscourseName = thisElement.eq(1).text();
		$("#modify_couname option[id='"+thiscourseName+"']").prop("selected", true);
		$("#modifyModal").modal();
	});
	
	// 삭제 버튼
	$(".btn_remove").on("click",function(){
	  $("#remove_id").val($(this).parents("tr").children().eq(0).text());
	  $("#remove_name").val($(this).parents("tr").children().eq(1).text());
	  $("#remove_start").val($(this).parents("tr").children().eq(2).text());
	  $("#remove_end").val($(this).parents("tr").children().eq(3).text());
	  $("#remove_sub_total").val($(this).parents("tr").children().eq(4).text());
	  $("#remove_classroom").val($(this).parents("tr").children().eq(6).text());
	  $("#removeModal").modal();
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
                <h3>개설 과정 관리 <small></small></h3>
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
                  </div>
                </div>
                <!-- 검색 선택 -->
                <div class="col-md-2.5 col-sm-2.5 col-xs-2.5 form-group pull-right">
      				<select class="form-control top_search_select" name="skey" id="skey">
      					<option value="all">전체</option>
                     	<option value="sc_course-id">과정ID</option>
                     	<option value="sc_course-name">과정명</option>
      				</select>
      			</div>
              </form>
              </div>
            </div>
              <div class="clearfix"></div>
              <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>과정 정보 조회</h2>
                    <!-- Top 버튼 -->
					<button class="btn btn-sm btn-round btn-info pull-right" id="btn_add"><span class="fa fa-plus-circle" style="color: white;"></span> 과정추가</button>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                    <!--타이틀정보-->
                    <p></p>
                    <div class="table-responsive">
                    <table class="table table-default jambo_table bulk_action">
					<!-- class="table table-bordered" -->
					<thead>
						<tr>
							<th>과정ID</th>
							<th>과정명</th>
							<th>과정 시작</th>
							<th>과정 종료</th>
							<th>개설과목 수</th>
							<th>수강 인원</th>
							<th>강의실명</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="c" items="${list}">
						<tr>
							<td>${c.course_id}</td>
							<td>${c.cou_name}</td>
							<td>${c.cou_start_date}</td>
							<td>${c.cou_end_date}</td>
							<td>${c.class_total}</td>
							<td>${c.st_total}</td>
							<td>${c.class_name}</td>
							<td>
								<div class="btn-group">
									<button type="button" class="btn btn-default btn-xs btn_info">조회</button>
									<button type="button" class="btn btn-default btn-xs btn_modify">수정</button>
									<button type="button" class="btn btn-default btn-xs btn_remove" ${((c.class_total == 0) && (c.st_total == 0))?'':'disabled'}>삭제</button>
								</div>
							</td>
						</tr>
						</c:forEach>
					</tbody>
				    </table>
                    </div>
                  </div>
                  <div class="btn-group btnPage pull-right">
                  <c:forEach var="p" begin="1" end="${pageCount}" step="1">
                    <a href="course_list.do?page=${p}" class="btn btn-info btn-sm ${(p==1)? 'active':'' }" id="btnPage${p}">${p}</a>
                  </c:forEach>
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
		<!-- addModal -->
  <div id="addModal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title-text">개설 과정 추가</h4>
				</div>
        <!-- form -->
        <form action="courseAdd.do" method="post">
          <!-- Modal body -->
				  <div class="modal-body">
            <div class="form-group">
              <label for="cou_name">과정명 선택</label>
              <select class="form-control" id="cou_name" name="cou_name">
              <c:forEach var="c" items="${addCourseName}">
                <option value="${c.courseName_id}">(${c.courseName_id})${c.cou_name}</option>
              </c:forEach>
              </select>
            </div>
            <!-- 달력 -->
            <div class="form-group" style="margin-bottom:90px">
              <div class="col-sm-6">
                <label for="course_start">과정 시작 <img src="/gradeprocess/image/calendar.jpg" width="20px;" height="20px"></label>
                <input type="text" class="form-control datepicker" id="course_start" name="start" placeholder="YYYY-MM-DD" required>
              </div>
              <div class="col-sm-6">
                <label for="course_end" class="">과정 종료 <img src="/gradeprocess/image/calendar.jpg" width="20px;" height="20px"></label>
                <input type="text" class="form-control datepicker" id="course_end" name="end" placeholder="YYYY-MM-DD" required>
              </div>
            </div>
            <!-- 강의실 -->
            <div class="form-group">
              <label for="classroom">강의실 선택</label>
              <select class="form-control" id="classroom" name="classroom">
              <c:forEach var="r" items="${addClassroom}">
              	<option value="${r.classroom_id}">${r.class_name}</option>
              </c:forEach>
              </select>
            </div>
          </div> <!--end Modal body-->
              <!-- footer -->
          <div class="modal-footer">
            <button type="submit" class="btn-submit">확인</button>
            <button type="button" class="btn-cancle" data-dismiss="modal">취소</button>
          </div> <!--end Modal footer-->
        </form>
      </div> <!--end modal-content-->
    </div> <!--end modal-dialog-->
  </div> <!--end Modal-->
 
	<!-- modifyModal -->
  <div id="modifyModal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title-text">개설 과정 수정</h4>
				</div>
        <!-- form -->
        <form action="courseModify.do" method="post">
          <!-- Modal body -->
  			<div class="modal-body">
              <div class="form-group">
				<label for="modify_id">과정ID</label><input type="text"	class="form-control" id="modify_id" name="id" required maxlength="20" readonly>
				</div>
              <div class="form-group">
                <label for="modify_couname">과정명 선택</label>
                <select class="form-control" id="modify_couname" name="cou_name">
                <c:forEach var="c" items="${addCourseName}">
                  <option value="${c.courseName_id}" id="${c.cou_name}">(${c.courseName_id}) ${c.cou_name}</option>
                </c:forEach>
                </select>
              </div>
              <!-- 달력 -->
              <div class="form-group" style="margin-bottom:90px">
                <div class="col-sm-6">
                  <label for="modify_start">과정 시작 <img src="/gradeprocess/image/calendar.jpg" width="20px;" height="20px"></label>
                  <input type="text" class="form-control datepicker" id="modify_start" name="cou_start" required placeholder="YYYY-MM-DD">
                </div>
                <div class="col-sm-6">
                  <label for="modify_end" class="">과정 종료 <img src="/gradeprocess/image/calendar.jpg" width="20px;" height="20px"></label>
                  <input type="text" class="form-control datepicker" id="modify_end" name="cou_end" required placeholder="YYYY-MM-DD">
                </div>
              </div>
              <!-- 강의실 -->
              <div class="form-group">
                <label for="modify_classroom">강의실 선택</label>
                <select class="form-control" id="modify_classroom" name="classroom">
                <c:forEach var="r" items="${addClassroom}">
                  <option value="${r.classroom_id}" id="${r.class_name}">${r.class_name}</option>
                </c:forEach>
                </select>
              </div>
            </div> <!--end Modal body-->
                <!-- footer -->
            <div class="modal-footer">
            	<button type="submit" class="btn-submit">확인</button>
            	<button type="button" class="btn-cancle" data-dismiss="modal">취소</button>
            </div> <!--end Modal footer-->
            </form>
          </div> <!--end modal-content-->
        </div> <!--end modal-dialog-->
      </div> <!--end Modal-->
 
	<!-- removeModal -->
  <div id="removeModal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title-text">개설 과정 삭제</h4>
				</div>
        <!-- form -->
        <form action="courseRemove.do" method="post">
          <!-- Modal body -->
  				<div class="modal-body">
              <div class="form-group">
								<label for="remove_id">과정ID</label><input type="text"
									class="form-control" id="remove_id" name="id" required readonly>
							</div>
              <div class="form-group">
								<label for="remove_name">과정명</label><input type="text"
									class="form-control" id="remove_name" name="cou_name" required readonly>
							</div>
 
              <!-- 달력 -->
              <div class="form-group" style="margin-bottom:90px">
                <div class="col-sm-6">
                  <label for="remove_start">과정 시작 </label>
                  <input type="text" class="form-control" id="remove_start" name="cou_start" required readonly>
                </div>
                <div class="col-sm-6">
                  <label for="remove_end" class="">과정 종료 </label>
                  <input type="text" class="form-control" id="remove_end" name="cou_end" required readonly>
                </div>
              </div>
              <!-- 강의실 -->
              <div class="form-group col-sm-6">
								<label for="remove_classroom">강의실</label><input type="text"
									class="form-control" id="remove_classroom" name="classroom" required readonly>
							</div>
              <div class="form-group col-sm-6">
								<label for="remove_sub_total">개설 과목 수</label><input type="text"
									class="form-control" id="remove_sub_total" name="sub_total" required readonly>
							</div>
            <p class="text-modal-bottom">해당 과정을 삭제 하시겠습니까? </p>
          </div> <!--end Modal body-->
              <!-- footer -->
          <div class="modal-footer">
            <button type="submit" class="btn-submit">확인</button>
            <button type="button" class="btn-cancle" data-dismiss="modal">취소</button>
          </div> <!--end Modal footer-->
          </form>
        </div> <!--end modal-content-->
      </div> <!--end modal-dialog-->
    </div> <!--end Modal-->
 
	<!-- infoModal -->
	<div id="infoModal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title-text">개설 과정 조회</h4>
				</div>
        	<!-- form -->
        	<form action="course_list2.do" method="post">
          	<!-- Modal body -->
			<div class="modal-body">
              <div class="text-box-bg">
                <div class="form-group" id="text_box1">
                   <strong>과정ID : </strong><span id="info_id"></span><br>
                   <strong>과정명 : </strong><span id="info_name"></span><br>
                   <strong>과정시작 : </strong><span id="info_start"></span><br>
                   <strong>과정종료 : </strong><span id="info_end"></span>
                </div>
                <div class="form-group" id="text_box2">
                   <strong>개설과목 수 : </strong><span id="info_sub_total"></span><br>
                   <strong>수강인원 : </strong><span id="info_st_total"></span><br>
                   <strong>강의실명 : </strong><span id="info_classroom"></span><br>
                   <strong>&nbsp;</strong>
                </div>
               </div>
               <div id="modal_table">
                <table class="table">
                <thead>
                  <tr>
                    <th>과목명</th>
                    <th>과목시작</th>
                    <th>과목종료</th>
                    <th>강사명</th>
                    <th>교재명</th>
                  </tr>
                 </thead>
                 <tbody id="subject_tableBody">
                 </tbody>
                </table>
                <table class="table">
                <thead>
                  <tr>
                    <th>수강생 명</th>
                    <!-- <th>주민번호뒷자리</th> -->
                    <th>전화번호</th>
                    <th>등록일</th>
                    <th>수료여부</th>
                  </tr>
                 </thead>
                 <tbody id="student_tableBody">
                 </tbody>
                </table>
               </div>
 
          </div> <!--end Modal body-->
                <!-- footer -->
          <div class="modal-footer">
          <div id="btn_subAdd_div">
          	<button type="submit" class="btn-submit" id="btn_subAdd"><span class="glyphicon glyphicon-plus-sign" style="color:#00d1e6; vertical-align:middle"></span> 과목추가</button>
          </div>
            <button type="button" class="btn-cancle" data-dismiss="modal">취소</button>
          </div> <!--end Modal footer-->
                  <input type="hidden" id="hidden_couId" name="hidden_couId">
                  <input type="hidden" id="hidden_couName" name="hidden_couName">
                  <input type="hidden" id="hidden_couStart" name="hidden_couStart">
                  <input type="hidden" id="hidden_couEnd" name="hidden_couEnd">
		  	</form>
		  </div> <!--end modal-content-->
        
      </div> <!-- end modal-dialog -->
    </div> <!--end infoModal-->
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
    <!-- 달력을 위한 소스 -->
	<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
</body>
</html>