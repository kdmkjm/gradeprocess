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
td {
	white-space: nowrap;
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

#u_file, #i_file, #d_file {
	/* width: 270px; */
	width: 100%;
}
</style>
<script>
$(document).ready(function(){
	
	//jqueryUI datepicker 호출 코드
	//HTML5의 달력 기능은 구형 브라우저에서 지원 안됨.
	$("#i_exam, #u_exam").datepicker({
		changeMonth : true, //월 변경 가능
		changeYear : true, //년도 변경 가능
		dateFormat : "yy-mm-dd" //날짜 서식 지정
	});

   $(".p_add").on("click",function(){ // 배점 입력 버튼
	  	$("#pi_cou_name").text($(this).parents("tr").children().eq(0).text());
	    $("#pi_sub_name").text($(this).parents("tr").children().eq(2).text());
	    $("#pi_sub_start").text($(this).parents("tr").children().eq(6).text());
	    $("#pi_sub_end").text($(this).parents("tr").children().eq(7).text());
	    
	    $("#pi_subject_id").val($(this).val());
	   
	  });
  
  $(".p_modify").on("click",function(){ // 배점 수정 버튼
	  	$("#pu_cou_name").text($(this).parents("tr").children().eq(0).text());
	    $("#pu_sub_name").text($(this).parents("tr").children().eq(2).text());
	    $("#pu_sub_start").text($(this).parents("tr").children().eq(6).text());
	    $("#pu_sub_end").text($(this).parents("tr").children().eq(7).text());
	    
	    $("#u_point1").val($(this).parents("tr").children().eq(3).text());
	    $("#u_point2").val($(this).parents("tr").children().eq(4).text());
	    $("#u_point3").val($(this).parents("tr").children().eq(5).text());
	    
	    $("#pu_subject_id").val($(this).val());
	   
	  });
  
  $(".p_remove").on("click",function(){ // 배점 삭제 버튼
	    $("#pd_cou_name").text($(this).parents("tr").children().eq(0).text());
	    $("#pd_sub_name").text($(this).parents("tr").children().eq(2).text());
	    $("#pd_sub_start").text($(this).parents("tr").children().eq(6).text());
	    $("#pd_sub_end").text($(this).parents("tr").children().eq(7).text());
	   
	    $("#d_point1").val($(this).parents("tr").children().eq(3).text());
	    $("#d_point2").val($(this).parents("tr").children().eq(4).text());
	    $("#d_point3").val($(this).parents("tr").children().eq(5).text());
	    
	    $("#pd_subject_id").val($(this).val());
	    
	  });
  
  $(".e_add").on("click",function(){ // 시험 입력 버튼
	    
	    $("#ei_subject_id").val($(this).val());
	   
	  });
  
  $(".e_modify").on("click",function(){ // 시험 수정 버튼
	  	
	    $("#u_exam").val($(this).parents("tr").children().eq(11).text());
	    //$("#u_file").val($(this).parents("tr").children().eq(12).text());
	    
	    $("#eu_subject_id").val($(this).val());
	   
	  });
  
  $(".e_remove").on("click",function(){ // 시험 삭제 버튼
	    
	  	$("#d_exam").val($(this).parents("tr").children().eq(11).text());
	    $("#d_file").val($(this).parents("tr").children().eq(12).text());
	   
	    $("#ed_subject_id").val($(this).val());
	    
	  });
  
 
//검색 수행시 키워드 표시하는 과정 추가
$("#skey").find("option[value='${skey}']").attr("selected", "selected");
	$("#svalue").val("${svalue}");
	$("#skey").on("change", (function() {
		$("#svalue").val("");
	}));
	
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

$(".i_btn-point").on("click",function(){
	
	var total = Number($("#i_point1").val()) + Number($("#i_point2").val()) + Number($("#i_point3").val());
		
	if ( $("#i_point1").val() < 20 ) {
		alert("출결 배점은 최소 20점 이상입니다.");
	} else if ( total != 100 )  {
		alert("배점의 합이 올바르지 않습니다. (총 배점의 합은 100점)");
	} 
});

$(".u_btn-point").on("click",function(){
	
	var total = Number($("#u_point1").val()) + Number($("#u_point2").val()) + Number($("#u_point3").val());
	
	if ( $("#u_point1").val() < 20 ) {
		alert("출결 배점은 최소 20점 이상입니다.");
	} else if ( total != 100 ) {
		alert("배점의 합이 올바르지 않습니다. (총 배점의 합은 100점)");
	}
	 
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
              <a href="inst_schedule_all.do" class="site_title"><img src="${pageContext.request.contextPath}/image/sist_symbol.png" id="sistlogo"> 쌍용교육센터</a>
            </div>

            <div class="clearfix"></div>

            <!-- menu profile quick info -->
            <div class="profile clearfix">
              <div class="profile_pic">
                <img src="${pageContext.request.contextPath}/image/profile_photo_inst.jpg" alt="profile_photo" class="img-circle profile_img">
              </div>
              <div class="profile_info">
                <span>강사</span>
                <h2>${sessionScope.instloginkey}</h2>
              </div>
            </div>
            <!-- /menu profile quick info -->
            <br />
            <!-- sidebar menu -->
            <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
              <div class="menu_section">

                <ul class="nav side-menu">
                  <li><a href="inst_schedule_all.do"><i class="fa fa-sitemap"></i> 강의 스케줄 <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="inst_schedule_all.do">강의전체</a></li>
                      <li><a href="inst_schedule_start.do">강의예정</a></li>
                      <li><a href="inst_schedule_ing.do">강의중</a></li>
                      <li><a href="inst_schedule_end.do">강의종료</a></li>
                    </ul>
                  </li>
                  <li><a href="inst_point_exam.do"><i class="fa fa-graduation-cap"></i> 배점/시험문제 </a></li>
                  <li><a href="inst_grade.do"><i class="fa fa-cogs"></i> 성적 </a></li>
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
                    <img src="${pageContext.request.contextPath}/image/profile_photo_inst.jpg" alt="">${sessionScope.instloginkey} 강사님
                    <span class=" fa fa-angle-down"></span>
                  </a>
                  <ul class="dropdown-menu dropdown-usermenu pull-right">
                    <li><a href="instpwmodifyform.do?instructorid=${instructor_id}"> 비밀번호 변경</a></li>
                    <li><a href="logout_inst.do"><i class="fa fa-sign-out pull-right"></i> Log Out</a></li>
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
                <h3>배점/시험문제 <small></small></h3>
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
						<option value="cou_name">과정명</option>
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
                    <h2>배점/시험문제 조회</h2>
                    <!-- Top 버튼 -->
					<!-- <button class="btn btn-sm btn-round btn-info pull-right" id="btn_add"><span class="fa fa-plus-circle" style="color: white;"></span> 추가</button> -->
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                    <!--타이틀정보-->
                    <p></p>
                    <div class="table-responsive">
                      <table class="table table-default jambo_table bulk_action">
					<thead>
						<tr>
							<th>과정명</th>
							<th>과정 기간</th>
							<th>과목명</th>
							<th>출결 배점</th>
							<th>필기 배점</th>
							<th>실기 배점</th>
							<th>과목시작</th>
							<th>과목종료</th>
							<th>교재명</th>
							<th>강의실명</th>
							<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;배점 관리&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
							<th>시험날짜</th>
							<th>시험문제</th>
							<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;시험문제 관리&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
						</tr>
					</thead>
					<tbody>
						<!-- 
                  <tr>
                     <td>JAVA기반 응용 SW 엔지니어링 실무과정</td>
                     <td>JAVA</td>
                     <td>20</td>
                     <td>40</td>
                     <td>40</td>
                     <td>2016-04-05</td>
                     <td>2016-06-08</td>
                     <td><a href="#" data-toggle="modal" data-target="#bookPictureModal">이것이 자바다</a></td>
                     <td>강의실1</td>
                     <td>
                        <div class="btn-group" id="btn-group">
                           <button type="button" class="btn btn-default btn-xs"
                              data-toggle="modal" data-target="#piModal">입력</button>
                           <button type="button" class="btn btn-default btn-xs"
                              data-toggle="modal" data-target="#puModal">수정</button>
                           <button type="button" class="btn btn-default btn-xs"
                              data-toggle="modal" data-target="#pdModal">삭제</button>
                        </div>

                     </td>
                     <td><a href="#">160528_JAVA기반_응용_SW_고준희.zip</a></td>
                     <td>
                        <div class="btn-group" id="btn-group">
                           <button type="button" class="btn btn-default btn-xs"
                              data-toggle="modal" data-target="#eiModal">등록</button>
                           <button type="button" class="btn btn-default btn-xs"
                              data-toggle="modal" data-target="#euModal">수정</button>
                           <button type="button" class="btn btn-default btn-xs"
                              data-toggle="modal" data-target="#edModal">삭제</button>
                        </div>

                     </td>
                  </tr>
                     <tr>
                     <td>JAVA기반 응용 SW 엔지니어링 실무과정</td>
                     <td>JAVA</td>
                     <td>20</td>
                     <td>40</td>
                     <td>40</td>
                     <td>2016-04-05</td>
                     <td>2016-06-08</td>
                     <td><a href="#" data-toggle="modal" data-target="#bookPictureModal">이것이 자바다</a></td>
                     <td>강의실1</td>
                     <td>
                        <div class="btn-group" id="btn-group">
                           <button type="button" class="btn btn-default btn-xs"
                              data-toggle="modal" data-target="#piModal">입력</button>
                           <button type="button" class="btn btn-default btn-xs"
                              data-toggle="modal" data-target="#puModal">수정</button>
                           <button type="button" class="btn btn-default btn-xs"
                              data-toggle="modal" data-target="#pdModal">삭제</button>
                        </div>

                     </td>
                     <td><a href="#">160528_JAVA기반_응용_SW_고준희.zip</a></td>
                     <td>
                        <div class="btn-group" id="btn-group">
                           <button type="button" class="btn btn-default btn-xs"
                              data-toggle="modal" data-target="#eiModal">등록</button>
                           <button type="button" class="btn btn-default btn-xs"
                              data-toggle="modal" data-target="#euModal">수정</button>
                           <button type="button" class="btn btn-default btn-xs"
                              data-toggle="modal" data-target="#edModal">삭제</button>
                        </div>

                     </td>
                 
                  </tr>
                      <tr>
                     <td>JAVA기반 응용 SW 엔지니어링 실무과정</td>
                     <td>JAVA</td>
                     <td>20</td>
                     <td>40</td>
                     <td>40</td>
                     <td>2016-04-05</td>
                     <td>2016-06-08</td>
                     <td><a href="#" data-toggle="modal" data-target="#bookPictureModal">이것이 자바다</a></td>
                     <td>강의실1</td>
                     <td>
                        <div class="btn-group" id="btn-group">
                           <button type="button" class="btn btn-default btn-xs"
                              data-toggle="modal" data-target="#piModal">입력</button>
                           <button type="button" class="btn btn-default btn-xs"
                              data-toggle="modal" data-target="#puModal">수정</button>
                           <button type="button" class="btn btn-default btn-xs"
                              data-toggle="modal" data-target="#pdModal">삭제</button>
                        </div>

                     </td>
                     <td><a href="#">160528_JAVA기반_응용_SW_고준희.zip</a></td>
                     <td>
                        <div class="btn-group" id="btn-group">
                           <button type="button" class="btn btn-default btn-xs"
                              data-toggle="modal" data-target="#eiModal">등록</button>
                           <button type="button" class="btn btn-default btn-xs"
                              data-toggle="modal" data-target="#euModal">수정</button>
                           <button type="button" class="btn btn-default btn-xs"
                              data-toggle="modal" data-target="#edModal">삭제</button>
                        </div>

                     </td>
                  </tr> 
 				 -->

						<c:forEach var="i" items="${list}">
							<tr>
								<td>${i.cou_name}</td>
								<td>${i.cou_start_date} ~ ${i.cou_end_date}</td>
								<td>${i.sub_name}</td>
								<td>${ (empty i.po_attend )? "X" : i.po_attend }</td>
								<td>${ (empty i.po_write )? "X" : i.po_write }</td>
								<td>${ (empty i.po_practice )? "X" : i.po_practice }</td>
								<td>${i.sub_start_date}</td>
								<td>${i.sub_end_date}</td>
								<td>
									<a href="#" data-toggle="modal" data-target="#bookPictureModal" class="textbook">${i.text_name}</a>
									<input type="hidden" value="${i.publisher}" id="t_publisher" name="t_publisher">
									<!-- 책 표지 설정 부분 -->
									<%-- <input type="hidden" value="${i.publisher}" id="t_publisher" name="t_publisher"> --%>
								</td>
								<td>${i.class_name}</td>

								<td>
									<div class="btn-group" id="btn-group">
										<button type="button" class="btn btn-default btn-xs p_add ${ (empty i.po_attend) ? '' : 'disabled' }" ${ (empty i.po_attend) ? "" : "disabled" } 
											data-toggle="modal" data-target="#piModal" value="${i.subject_id}">입력</button>
										<button type="button" class="btn btn-default btn-xs p_modify ${ (empty i.po_attend) ? 'disabled' : '' }" ${ (empty i.po_attend) ? "disabled" : "" }
											data-toggle="modal" data-target="#puModal" value="${i.subject_id}">수정</button>
										<button type="button" class="btn btn-default btn-xs p_remove ${ (empty i.po_attend) ? 'disabled' : '' }" ${ (empty i.po_attend) ? "disabled" : "" }
											data-toggle="modal" data-target="#pdModal" value="${i.subject_id}">삭제</button>
									</div>
								</td>
								<td>${ (empty i.exam_date)? "X" : i.exam_date }</td>
								<td><c:choose>
										<c:when test="${empty i.examination}">
											<span class="err">X</span>
										</c:when>
										<c:otherwise><a href="exam/${i.exam_zip}">${i.examination}</a></c:otherwise>
									</c:choose></td>

								<td>
									<div class="btn-group" id="btn-group">
										<button type="button" class="btn btn-default btn-xs e_add ${ (empty i.examination) ? '' : 'disabled' }" ${ (empty i.examination) ? "" : "disabled" } 
											data-toggle="modal" data-target="#eiModal" value="${i.subject_id}">등록</button>
										<button type="button" class="btn btn-default btn-xs e_modify ${ (empty i.examination) ? 'disabled' : '' }" ${ (empty i.examination) ? "disabled" : "" }
											data-toggle="modal" data-target="#euModal" value="${i.subject_id}">수정</button>
										<button type="button" class="btn btn-default btn-xs e_remove ${ (empty i.examination) ? 'disabled' : '' }" ${ (empty i.examination) ? "disabled" : "" }
											data-toggle="modal" data-target="#edModal" value="${i.subject_id}">삭제</button>
									</div>
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
	<!-- 배점 입력 Modal -->
	<div id="piModal" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title-text">배점 입력</h4>
				</div>
				
				<form action="inst_pointAdd.do" method="post" onerror="message()">
				
					<div class="modal-body">
						<div class="text-box-bg">
							<div class="form-group" id="box02">
								<strong>과정명 :</strong><span id="pi_cou_name"></span><br> 
								<strong>과목명 :</strong><span id="pi_sub_name"></span><br> 
								<strong>과목 시작 :</strong><span id="pi_sub_start"></span><br> 
								<strong>과목 종료 :</strong><span id="pi_sub_end"></span><br><br>
							</div>
						</div>
					
						<input type="hidden" value="" name="pi_subject_id" id="pi_subject_id">

						<div class="form-group">
							<label for="i_point1">출결 배점: (최소 20점이상)</label> 
							<input type="number" class="form-control" id="i_point1" name="i_point1" 
								min="20" required maxlength="2"> 
							<span class="help-block">(최소 20점 이상)</span>
						</div>
						
						<div class="form-group">
							<label for="i_point2">필기 배점</label> 
							<input type="number" class="form-control" id="i_point2" name="i_point2" 
								required maxlength="2">
						</div>

						<div class="form-group">
							<label for="i_point3">실기 배점</label> 
							<input type="number" class="form-control" id="i_point3" name="i_point3" 
								required maxlength="2">
						</div>
						
						<span id="text">출결, 필기, 실기 배점의 합은 100 입니다.</span><br>

					</div>
					<!--end Modal body-->
					<!-- footer -->
					<div class="modal-footer">
						<input type="submit" class="btn-submit i_btn-point" value="확인">
						<button type="button" class="btn-cancle" data-dismiss="modal">취소</button>
					</div>
				</form>
				
			</div>
			
		</div>
	</div>
	

	<!-- 배점 수정 Modal -->
	<div id="puModal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title-text">배점 수정</h4>
				</div>
				<form action="inst_pointModify.do" method="post">
					<div class="modal-body">
						<div class="text-box-bg">
							<div class="form-group" id="box02">
								<strong>과정명 :</strong><span id="pu_cou_name">Java</span><br>
								<strong>과목명 :</strong><span id="pu_sub_name">Java</span><br>
								<strong>과목 시작 :</strong><span id="pu_sub_start">2016-04-05</span><br>
								<strong>과목 종료 :</strong><span id="pu_sub_end">2016-05-05</span><br>
								<br>
							</div>
						</div>
						
						<input type="hidden" value="" name="pu_subject_id" id="pu_subject_id">

						<div class="form-group">
							<label for="u_point1">출결 배점: (최소 20점이상)</label> 
							<input type="number" class="form-control" id="u_point1" name="u_point1" 
								min="20" required maxlength="2"> 
							<span class="help-block">(최소 20점 이상)</span>
						</div>
						<div class="form-group">
							<label for="u_point2">필기 배점</label> 
							<input type="number" class="form-control" id="u_point2" name="u_point2" 
								required maxlength="2">
						</div>

						<div class="form-group">
							<label for="u_point3">실기 배점</label> 
							<input type="number" class="form-control" id="u_point3" name="u_point3" 
								required maxlength="2">
						</div>
						<span id="text">출결, 필기, 실기 배점의 합은 100 입니다.</span><br>

					</div>
					<!--end Modal body-->
					<!-- footer -->
					<div class="modal-footer">
						<input type="submit" class="btn-submit u_btn-point" value="확인">
						<button type="button" class="btn-cancle" data-dismiss="modal">취소</button>
					</div>
				</form>
			</div>
		
		</div>
	</div>



	<!-- 배점 삭제 Modal -->
	<div id="pdModal" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title-text">배점 삭제</h4>
				</div>
				<form action="inst_pointRemove.do" method="post">
					<div class="modal-body">
						<div class="text-box-bg">
							<div class="form-group" id="box02">
								<strong>과정명 :</strong><span id="pd_cou_name">Java</span><br>
								<strong>과목명 :</strong><span id="pd_sub_name">Java</span><br>
								<strong>과목 시작 :</strong><span id="pd_sub_start">2016-04-05</span><br>
								<strong>과목 종료 :</strong><span id="pd_sub_end">2016-05-05</span><br>
								<br>
							</div>
						</div>

						<input type="hidden" value="" name="pd_subject_id" id="pd_subject_id">

						<div class="form-group">
							<label for="d_point1">출결 배점: (최소 20점이상)</label> 
							<input type="number" class="form-control" id="d_point1" name="d_point1" 
								min="20" required maxlength="2" readonly>
							<span class="help-block">(최소 20점 이상)</span>
						</div>
						<div class="form-group">
							<label for="d_point2">필기 배점</label> 
							<input type="number" class="form-control" id="d_point2" name="d_point2" 
								required maxlength="2" readonly>
						</div>

						<div class="form-group">
							<label for="d_point3">실기 배점</label> 
							<input type="number" class="form-control" id="d_point3" name="d_point3" 
								required maxlength="2" readonly>
						</div>
						
						<span id="text">출결, 필기, 실기 배점의 합은 100 입니다.</span><br>
						<p class="text-modal-bottom">배점 정보를 삭제 하시겠습니까?</p>

					</div>
					<!--end Modal body-->
					<!-- footer -->
					<div class="modal-footer">
						<input type="submit" class="btn-submit" value="확인">
						<button type="button" class="btn-cancle" data-dismiss="modal">취소</button>
					</div>
				</form>
				
			</div>
	
		</div>
	</div>



	<!-- 시험문제관리 등록 Modal -->
	<div id="eiModal" class="modal fade" role="dialog">
		<div class="modal-dialog modal-sm">

			<!-- Modal content-->
			<div class="modal-content">
				<!-- Modal header -->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title-text">시험 등록</h4>
				</div>
				
				<form role="form" action="inst_examAdd.do" method="post" class="form-inline" enctype="multipart/form-data">
					<!-- Modal body -->
					<div class="modal-body">
						
						<!-- 히든폼 -->
						<input type="hidden" value="" name="ei_subject_id" id="ei_subject_id">
						
						<%-- 시험날짜 --%>
						<div class="form-group">
							<label for="i_exam">시험날짜
							<img src="/gradeprocess/image/calendar.jpg" width="20px;" height="20px"></label><br> 
							<input type="text" class="form-control" id="i_exam" name="i_exam" placeholder="YYYY-MM-DD" required> 
							<span class="help-block"></span>
						</div>
						<br>
						
						<%-- 파일선택 --%>
						<div class="form-group">
							<label for="i_file">시험문제</label><br> 
							<input type="file" class="form-control" id="i_file" name="i_file" required> 
							<span class="help-block">100MB 이하의 .zip 파일만 업로드 가능합니다.</span>
						</div>
					</div>
					<!--end Modal body-->
					<!-- footer -->
					<div class="modal-footer">
						<input type="submit" class="btn-submit" value="확인">
						<button type="button" class="btn-cancle" data-dismiss="modal">취소</button>
					</div>
					<!--end Modal footer-->
				</form>
			</div>
			<!--end modal-content-->
		</div>
		<!--end modal-dialog-->
	</div>
	<!--end Modal-->

	<!-- 시험문제관리 수정 Modal -->
	<div id="euModal" class="modal fade" role="dialog">
		<div class="modal-dialog modal-sm">

			<!-- Modal content-->
			<div class="modal-content">
				<!-- Modal header -->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title-text">시험 수정</h4>
				</div>

				<form role="form" action="inst_examModify.do" method="post" class="form-inline" enctype="multipart/form-data">
					<!-- Modal body -->
					<div class="modal-body">
						
						<!-- 히든폼 -->
						<input type="hidden" value="" name="eu_subject_id" id="eu_subject_id">
						
						<%-- 시험날짜 --%>
						<div class="form-group">
							<label for="u_exam">시험날짜
							<img src="/gradeprocess/image/calendar.jpg" width="20px;" height="20px"></label><br> 
							<input type="text" class="form-control" id="u_exam" name="u_exam" placeholder="YYYY-MM-DD" required> 
							<span class="help-block"></span>
						</div>
						<br>
						
						<%-- 파일선택 --%>
						<div class="form-group">
							<label for="u_file">시험문제</label><br> 
							<input type="file" class="form-control" id="u_file" name="u_file" required> 
							<span class="help-block">100MB 이하의 .zip 파일만 업로드 가능합니다.</span>
						</div>
						
					</div>
					<!--end Modal body-->
					<!-- footer -->
					<div class="modal-footer">
						<input type="submit" class="btn-submit" value="확인">
						<button type="button" class="btn-cancle" data-dismiss="modal">취소</button>
					</div>
					<!--end Modal footer-->
				</form>
			</div>
			<!--end modal-content-->
		</div>
		<!--end modal-dialog-->
	</div>
	<!--end Modal-->

	<!-- 시험문제관리 삭제 Modal -->
	<div id="edModal" class="modal fade" role="dialog">
		<div class="modal-dialog modal-sm">

			<!-- Modal content-->
			<div class="modal-content">
				<!-- Modal header -->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title-text">시험 삭제</h4>
				</div>
				
				<form action="inst_examRemove.do" method="post" class="form-inline">
					<!-- Modal body -->
					<div class="modal-body">
					
						<input type="hidden" value="" name="ed_subject_id" id="ed_subject_id">
						<input type="hidden" value="${instructor_id}" name="ed_instructor_id" id="ed_instructor_id">					
					
						<%-- 시험날짜 --%>
						<div class="form-group">
							<label for="d_exam">시험날짜</label><br> 
							<input type="text" class="form-control" id="d_exam" name="d_exam" readonly> 
						</div>
						<br>
						
						<%-- 파일선택 --%>
						<div class="form-group">
							<label for="d_file">시험문제</label><br> 
							<input type="text" class="form-control" id="d_file" name="d_file" required readonly>
						</div>
						<p class="text-modal-bottom"><strong>현재 선택한 시험 정보를 삭제 하시겠습니까?</strong></p>
					</div>
					
					<!--end Modal body-->
					<!-- footer -->
					<div class="modal-footer">
						<input type="submit" class="btn-submit" value="확인">
						<button type="button" class="btn-cancle" data-dismiss="modal">취소</button>
					</div>
					<!--end Modal footer-->
				</form>
			</div>
			<!--end modal-content-->
		</div>
		<!--end modal-dialog-->
	</div>
	<!--end Modal-->

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
