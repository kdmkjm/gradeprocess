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
<script>
<!-- ========================== jQuery ========================== -->
$(document).ready(function(){
	$(".datepicker").datepicker({
		changeMonth : true,
		changeYear : true,
		dateFormat : "yy-mm-dd"
	});
	//추가 버튼
  $("#btn_add").on("click",function(){
    $("#add_id").text($("#cou_id").text());
    $("#add_name").text($("#cou_name").text());
    $("#add_start").text($("#cou_start").text());
    $("#add_end").text($("#cou_end").text());
    $("#hidden_couId").val($("#cou_id").text());
    $("#hidden_couName").val($("#cou_name").text());
    $("#hidden_couStart").val($("#cou_start").text());
    $("#hidden_couEnd").val($("#cou_end").text());
    $("#addModal").modal();
  });
	
  //수정 버튼
  $(".btn_modify").on("click",function(){
    $("#modify_id").text($("#cou_id").text());
    $("#modify_name").text($("#cou_name").text());
    $("#modify_start").text($("#cou_start").text());
    $("#modify_end").text($("#cou_end").text());
    $("#modify_sub_id").val($(this).parents("tr").children().eq(0).text());
    $("#modify_sub_start").val($(this).parents("tr").children().eq(2).text());
    $("#modify_sub_end").val($(this).parents("tr").children().eq(3).text());
    $("#m_hidden_couId").val($("#cou_id").text());
    $("#m_hidden_couName").val($("#cou_name").text());
    $("#m_hidden_couStart").val($("#cou_start").text());
    $("#m_hidden_couEnd").val($("#cou_end").text());
    
    //과목 Selected
    var thisSubName = $(this).parents("tr").children().eq(1).text();
    $("#modify_sub_name option[id='"+thisSubName+"']").prop("selected", true);
    /* 
    var subNamelen = $("#modify_sub_name option").length;
    var subNameId = "";
    for (var i=0; i<subNamelen; ++i) {
    	var option_subname = $("#modify_sub_name option").eq(i).text();
    	option_subname = option_subname.slice(15);
    	if (option_subname == thisSubName) {
    		subNameId = $("#modify_sub_name option").eq(i).attr("value");
    	}
    }
    $("#modify_sub_name").val(subNameId); */
    
    //강사 Selected
    var thisInst = $(this).parents("tr").children().eq(4).text();
    $("#modify_inst_name option[id='"+thisInst+"']").prop("selected", true);
    /* 
    var instlen = $("#modify_inst_name option").length;
    var instId = "";
    for (var i=0; i<instlen; ++i) {
        var option_inst = $("#modify_inst_name option").eq(i).text();
        if (option_inst == thisInst) {
        	instId = $("#modify_inst_name option").eq(i).attr("value");
        }
    }
    $("#modify_inst_name").val(instId); */
    
     //교재명 Selected
    var thisTextbook= $(this).parents("tr").children().eq(5).find("a").text();
    $("#modify_textbook option[id='"+thisTextbook+"']").prop("selected", true);

    /* 
    var textbooklen = $("#modify_textbook option").length;
    var textbookId = "";
    for (var i=0; i<textbooklen; ++i) {
        var option_textbook = $("#modify_textbook option").eq(i).text();
        if (option_textbook == thisTextbook) {
        	textbookId = $("#modify_textbook option").eq(i).attr("value");
        }
    }
    $("#modify_textbook").val(textbookId); */
    $("#modifyModal").modal();
    
  });
  //삭제 버튼
  $(".btn_remove").on("click",function(){
    $("#remove_id").text($("#cou_id").text());
    $("#remove_name").text($("#cou_name").text());
    $("#remove_start").text($("#cou_start").text());
    $("#remove_end").text($("#cou_end").text());
    $("#remove_sub_id").val($(this).parents("tr").children().eq(0).text());
    $("#remove_sub_name").val($(this).parents("tr").children().eq(1).text());
    $("#remove_sub_start").val($(this).parents("tr").children().eq(2).text());
    $("#remove_sub_end").val($(this).parents("tr").children().eq(3).text());
    $("#remove_text_name").val($(this).parents("tr").children().eq(5).text());
    $("#remove_inst_name").val($(this).parents("tr").children().eq(4).text());   
    $("#d_hidden_couId").val($("#cou_id").text());
    $("#d_hidden_couName").val($("#cou_name").text());
    $("#d_hidden_couStart").val($("#cou_start").text());
    $("#d_hidden_couEnd").val($("#cou_end").text());    
    $("#removeModal").modal();  
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
                     	<option value="sc_sub-id">과목ID</option>
                     	<option value="sc_sub-name">과목명</option>
      				</select>
      			</div>
              </form>
              </div>
            </div>
              <div class="clearfix"></div>
              <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>과목 정보 조회</h2>
                    <!-- Top 버튼 -->
			<button class="btn btn-sm btn-round btn-info pull-right" id="btn_add"><span class="fa fa-plus-circle" style="color: white;"></span> 과목 추가</button>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                    <!--타이틀정보-->
                    <p id="info-title"> <strong>과정ID: </strong><span id="cou_id">${cou_id}</span>&nbsp;&nbsp;<strong> 과정명: </strong>
       				<span id="cou_name">${cou_name}</span>&nbsp;&nbsp;<strong> 과정시작: </strong><span id="cou_start">${cou_start}</span>&nbsp;&nbsp;<strong> 과정종료:</strong><span id="cou_end">${cou_end}</span>
 					</p>
                    <div class="table-responsive">
                      <table class="table table-default jambo_table bulk_action">
               <!-- class="table table-bordered" -->
               <thead>
                  <tr>
                     <th>과목ID</th>
                     <th>과목명</th>
                     <th>과목 시작</th>
                     <th>과목 종료</th>
                     <th>강사명</th>
                     <th>교재명</th>
                     <th></th>
                  </tr>
               </thead>
               <tbody>
               <c:choose>
               		<c:when test="${list.size() == 0}">
               			<tr><td colspan="7">등록된 과목 정보가 없습니다.</td></tr>
               		</c:when>
               		<c:otherwise>
						<c:forEach var="s" items="${list}">
					<tr>
						<td>${s.subject_id}</td>
						<td>${s.sub_name}</td>
						<td>${s.sub_start_date}</td>
						<td>${s.sub_end_date}</td>
						<td>${s.inst_name}</td>
						<td><a href="#" data-toggle="modal" data-target="#bookPictureModal" class="textbook">${s.text_name}</a> </td>
						<td>
                        <div class="btn-group">
                           <button type="button" class="btn btn-default btn-xs btn_modify">수정</button>
                           <button type="button" class="btn btn-default btn-xs btn_remove">삭제</button>
                        </div>
                     </td>
					</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
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
   
	<!-- 추가 Modal -->
  <div id="addModal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title-text">개설 과목 추가</h4>
				</div>
        <!-- form -->
        <form action="subjectAdd.do" method="post">
          <!-- Modal body -->
				<div class="modal-body">
          <div class="text-box-bg">
            <strong>과정ID : </strong><span id="add_id"></span><br>
            <strong>과정명 : </strong><span id="add_name"></span><br>
            <strong>과정시작 : </strong><span id="add_start"></span><br>
            <strong>과정종료 : </strong><span id="add_end"></span>
            <input type="hidden" id="hidden_couId" name="hidden_couId" value="">
            <input type="hidden" id="hidden_couName" name="hidden_couName" value="">
			<input type="hidden" id="hidden_couStart" name="hidden_couStart" value="">
            <input type="hidden" id="hidden_couEnd" name="hidden_couEnd" value="">

		  </div>
            <!-- 과목명 선택 -->
            <div class="form-group">
              <label for="sub_name">과목명 선택</label>
              <select class="form-control" id="sub_name" name="sub_name">
              <c:forEach var="s" items="${subNamelist}">
                <option value="${s.subjectName_id}">(${s.subjectName_id}) ${s.sub_name}</option>
              </c:forEach>
              </select>
            </div>

            <!-- 달력 -->
            <div class="form-group" style="margin-bottom:90px">
              <div class="col-sm-6">
                <label for="subject_start">과목 시작 <img src="/gradeprocess/image/calendar.jpg" width="20px;" height="20px"></label>
                <input type="text" class="form-control datepicker" id="subject_start" name="start" required placeholder="YYYY-MM-DD">
              </div>
              <div class="col-sm-6">
                <label for="subject_end" class="">과목 종료 <img src="/gradeprocess/image/calendar.jpg" width="20px;" height="20px"></label>
                <input type="text" class="form-control datepicker" id="subject_end" name="end" required placeholder="YYYY-MM-DD">
              </div>
            </div>
            <!-- 교재명 선택 -->
            <div class="form-group col-sm-6">
              <label for="textbook">교재명 선택</label>
              <select class="form-control" id="textbook" name="textbook">
              <c:forEach var="t" items="${textlist}">
                <option value="${t.textbook_id}">${t.text_name}</option>
              </c:forEach>
              </select>
            </div>
            <!-- 강사 선택 -->
            <div class="form-group col-sm-6">
              <label for="instructor">강사 선택</label>
              <select class="form-control" id="instructor" name="instructor">
              <c:forEach var="i" items="${instlist}">
                <option value="${i.instructor_id}">${i.inst_name}</option>
              </c:forEach>
              </select>
            </div>
            <p>&nbsp;</p> <!-- 절대 지우지 말것 -->
          </div> <!--end Modal body-->
              <!-- footer -->
          <div class="modal-footer">
            <button type="submit" class="btn-submit" value="">확인</button>
            <button type="button" class="btn-cancle" data-dismiss="modal">취소</button>
          </div> <!--end Modal footer-->
          </form>
        </div> <!--end modal-content-->
      </div> <!--end modal-dialog-->
    </div> <!--end Modal-->


	<!-- 수정 Modal -->
  <div id="modifyModal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title-text">개설 과목 수정</h4>
				</div>
        <!-- form -->
        <form action="subjectModify.do" method="post">
          <!-- Modal body -->
			  	<div class="modal-body">
            <div class="text-box-bg">
              <strong>과정ID : </strong><span id="modify_id"></span><br>
              <strong>과정명 : </strong><span id="modify_name"></span><br>
              <strong>과정시작 : </strong><span id="modify_start"></span><br>
              <strong>과정종료 : </strong><span id="modify_end"></span>
              <input type="hidden" id="m_hidden_couId" name="m_hidden_couId" value="">
           	  <input type="hidden" id="m_hidden_couName" name="m_hidden_couName" value="">
			  <input type="hidden" id="m_hidden_couStart" name="m_hidden_couStart" value="">
              <input type="hidden" id="m_hidden_couEnd" name="m_hidden_couEnd" value="">
            </div>

              <div class="form-group">
								<label for="modify_sub_id">과목ID</label><input type="text"
									class="form-control" id="modify_sub_id" name="id" required maxlength="20" readonly>
							</div>
              <!-- 과목명 선택 -->
              <div class="form-group">
                <label for="modify_sub_name">과목명 선택</label>
                <select class="form-control" id="modify_sub_name" name="sub_name">
                <c:forEach var="s" items="${subNamelist}">
                    <option value="${s.subjectName_id}" id="${s.sub_name}">(${s.subjectName_id}) ${s.sub_name}</option>
                </c:forEach>
                </select>
              </div>
              <!-- 과정 선택 -->
              <!-- <div class="form-group col-sm-6">
                <label for="modify_cou_name">과정 선택</label>
                <select class="form-control" id="modify_cou_name" name="cou_name">
                  <option value="Java기반 응용 실무과정">(cou_name_001) Java기반 응용 실무과정</option>
                  <option value="Java기반 응용 SW">(cou_name_002) Java기반 응용 SW</option>
                  <option value="Java기반 응용 SW">(cou_name_003) Java기반 응용 SW</option>
                </select>
              </div> -->

              <!-- 달력 -->
              <div class="form-group" style="margin-bottom:90px">
                <div class="col-sm-6">
                  <label for="modify_sub_start">과목 시작 <img src="/gradeprocess/image/calendar.jpg" width="20px;" height="20px"></label>
                  <input type="text" class="form-control datepicker" id="modify_sub_start" name="sub_start" required placeholder="YYYY-MM-DD">
                </div>
                <div class="col-sm-6">
                  <label for="modify_sub_end" class="">과목 종료 <img src="/gradeprocess/image/calendar.jpg" width="20px;" height="20px"></label>
                  <input type="text" class="form-control datepicker" id="modify_sub_end" name="sub_end" required placeholder="YYYY-MM-DD">
                </div>
              </div>
              <!-- 교재명 선택 -->
              <div class="form-group col-sm-6">
                <label for="modify_textbook">교재명 선택</label>
                <select class="form-control" id="modify_textbook" name="textbook">
                <c:forEach var="t" items="${textlist}">
                    <option value="${t.textbook_id}" id="${t.text_name}">${t.text_name}</option>
                </c:forEach>
                </select>
              </div>
              <!-- 강사 선택 -->
              <div class="form-group col-sm-6">
                <label for="modify_inst_name">강사 선택</label>
                <select class="form-control" id="modify_inst_name" name="instructor">
                <c:forEach var="i" items="${instlist}">
                  <option value="${i.instructor_id}" id="${i.inst_name}">${i.inst_name}</option>
                </c:forEach>
                </select>
              </div>
              <p>&nbsp;</p> <!-- 절대 지우지 말것 -->
            </div> <!--end Modal body-->
                <!-- footer -->
            <div class="modal-footer">
              <button type="submit" class="btn-submit" value="">확인</button>
              <button type="button" class="btn-cancle" data-dismiss="modal">취소</button>
            </div> <!--end Modal footer-->
            </form>
          </div> <!--end modal-content-->
        </div> <!--end modal-dialog-->
      </div> <!--end Modal-->


	<!-- remove Modal -->
  <div id="removeModal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title-text">개설 과목 삭제</h4>
				</div>
        <!-- form -->
        <form action="subjectRemove.do" method="post">
          <!-- Modal body -->
				  <div class="modal-body">
            <div class="text-box-bg">
              <strong>과정ID : </strong><span id="remove_id"></span><br>
              <strong>과정명 : </strong><span id="remove_name"></span><br>
              <strong>과정시작 : </strong><span id="remove_start"></span><br>
              <strong>과정종료 : </strong><span id="remove_end"></span>
              
            <input type="hidden" id="d_hidden_couId" name="d_hidden_couId" value="">
            <input type="hidden" id="d_hidden_couName" name="d_hidden_couName" value="">
			<input type="hidden" id="d_hidden_couStart" name="d_hidden_couStart" value="">
            <input type="hidden" id="d_hidden_couEnd" name="d_hidden_couEnd" value="">
            
            </div>
             <div class="form-group">
							<label for="remove_sub_id">과목ID</label><input type="text"
								class="form-control" id="remove_sub_id" name="id" readonly>
						</div>
             <div class="form-group">
							<label for="remove_sub_name">과목명</label><input type="text"
								class="form-control" id="remove_sub_name" name="sub_name" readonly>
						</div>

            <!-- 달력 -->
            <div class="form-group" style="margin-bottom:90px">
              <div class="col-sm-6">
                <label for="remove_sub_start">과목 시작 </label>
                <input type="text" class="form-control" id="remove_sub_start" name="sub_start" readonly>
              </div>
              <div class="col-sm-6">
                <label for="remove_sub_end" class="">과목 종료 </label>
                <input type="text" class="form-control" id="remove_sub_end" name="sub_end" readonly>
              </div>
            </div>
            <div class="form-group">
							<label for="remove_text_name">교재명</label><input type="text"
								class="form-control" id="remove_text_name" name="text_name" readonly>
			</div>
            <div class="form-group">
							<label for="remove_inst_name">강사명</label><input type="text"
								class="form-control" id="remove_inst_name" name="inst_name" readonly>
			</div>
            <p class="text-modal-bottom">해당 과목을 삭제 하시겠습니까? </p>
          </div> <!--end Modal body-->
              <!-- footer -->
          <div class="modal-footer">
            <button type="submit" class="btn-submit" value="">확인</button>
            <button type="button" class="btn-cancle" data-dismiss="modal">취소</button>
          </div> <!--end Modal footer-->
          </form>
        </div> <!--end modal-content-->
      </div> <!--end modal-dialog-->
    </div> <!--end Modal-->
    
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
					<button type="button" class="btn btn-default btn-sm btn_color" data-dismiss="modal">Close</button>
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
    <!-- 달력을 위한 소스 -->
	<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
</body>
</html>

