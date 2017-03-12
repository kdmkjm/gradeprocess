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

#text_box1 {
	margin-left: 50px;
}
</style>
<script>
<!-- ========================== jQuery ========================== -->
$(document).ready(function(){
	
	   $(".g_add").on("click",function(){ // 성적 입력 버튼
		 
		    $("#gi_st_name").text($(this).parents("tr").children().eq(0).text());
		    $("#gi_fin").text($(this).parents("tr").children().eq(3).text());
		    $("#gi_mid_fail_date").text($(this).parents("tr").children().eq(4).text());
		    
		    $("#gi_student_id").val($(this).val());
		   
		  });
	  
	  $(".g_modify").on("click",function(){ // 성적 수정 버튼
		  
		  	$("#gu_st_name").text($(this).parents("tr").children().eq(0).text());
		    $("#gu_fin").text($(this).parents("tr").children().eq(3).text());
		    $("#gu_mid_fail_date").text($(this).parents("tr").children().eq(4).text());
		    
		    $("#u_score1").val($(this).parents("tr").children().eq(5).text());
		    $("#u_score2").val($(this).parents("tr").children().eq(6).text());
		    $("#u_score3").val($(this).parents("tr").children().eq(7).text());
		    
		    $("#gu_student_id").val($(this).val());
		   
		  });
	  
	  $(".g_remove").on("click",function(){ // 성적 삭제 버튼
		  
		  	$("#gd_st_name").text($(this).parents("tr").children().eq(0).text());
		    $("#gd_fin").text($(this).parents("tr").children().eq(3).text());
		    $("#gd_mid_fail_date").text($(this).parents("tr").children().eq(4).text());
		    
		    $("#d_score1").val($(this).parents("tr").children().eq(5).text());
		    $("#d_score2").val($(this).parents("tr").children().eq(6).text());
		    $("#d_score3").val($(this).parents("tr").children().eq(7).text());
		    
		    $("#gd_student_id").val($(this).val());
		   
		  });
	  
	//검색 수행시 키워드 표시하는 과정 추가
	$("#skey").find("option[value='${skey}']").attr("selected", "selected");
		$("#svalue").val("${svalue}");
		$("#skey").on("change", (function() {
			$("#svalue").val("");
		}));
	 
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
                <h3>성적 <small></small></h3>
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
                    <input type="hidden" value="${course_id}" name="h_course_id">
					<input type="hidden" value="${subject_id}" name="h_subject_id">
					<input type="hidden" value="${cou_name}" name="h_cou_name">
					<input type="hidden" value="${sub_name}" name="h_sub_name">
					<input type="hidden" value="${sub_start_date}" name="h_sub_start_date">
					<input type="hidden" value="${sub_end_date}" name="h_sub_end_date">
					<input type="hidden" value="${po_attend}" name="h_po_attend">
					<input type="hidden" value="${po_write}" name="h_po_write">
					<input type="hidden" value="${po_practice}" name="h_po_practice">
                  </div>
                </div>
                <!-- 검색 선택 -->
                <div class="col-md-2.5 col-sm-2.5 col-xs-2.5 form-group pull-right">
      				<select class="form-control top_search_select" name="skey" id="skey">
      					<option value="all">전체</option>
						<option value="st_name">수강생명</option>
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
                    <!-- Top 버튼 -->
					
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                    <!--타이틀정보-->
                    <p id="info-title">
			<strong>과정명:</strong><span>${cou_name}</span>&nbsp; 
			<strong>과목명:</strong><span>${sub_name}</span>&nbsp;
			<strong>과목시작:</strong><span>${sub_start_date}</span>&nbsp;
			<strong>과목종료:</strong><span>${sub_end_date}</span>
		</p>
                    <div class="table-responsive">
                      <table class="table table-default jambo_table bulk_action">
					<thead>
						<tr>
							<th>수강생명</th>
							<th>전화번호</th>
							<th>등록일</th>
							<th>수료여부</th>
							<th>중도탈락날짜</th>
							<th>출결성적</th>
							<th>필기성적</th>
							<th>실기성적</th>
							<th>성적</th>
						</tr>
					</thead>
					<tbody>

					<c:forEach var="i" items="${list}">
							<tr>
								<td>${i.st_name}</td>
								<td>${i.st_phone}</td>
								<td>${i.register_date}</td>
								<td><c:choose>
										<c:when test="${i.fin == 0}">
											중도탈락
										</c:when>
										<c:when test="${i.fin == 1}">
											수료
										</c:when>
										<c:otherwise>미정</c:otherwise>
									</c:choose>
								</td>
								<td>${ (empty i.mid_fail_date) ? "X" : i.mid_fail_date }</td>
								<td>${ (empty i.gr_attend) ? "X" : i.gr_attend }</td>
								<td>${ (empty i.gr_write) ? "X" : i.gr_write }</td>
								<td>${ (empty i.gr_practice) ? "X" : i.gr_practice }</td>
								<td>
									<div class="btn-group" id="btn-group">
										<button type="button" class="btn btn-default btn-xs g_add ${ (empty i.gr_attend) && (empty i.mid_fail_date) && (not empty i.po_attend) ? '' : 'disabled' }" ${ (empty i.gr_attend) && (empty i.mid_fail_date) && (not empty i.po_attend) ? '' : 'disabled' } 
											data-toggle="modal" data-target="#giModal" value="${i.student_id}">입력</button>
										<button type="button" class="btn btn-default btn-xs g_modify ${ (empty i.gr_attend) ? 'disabled' : '' }" ${ (empty i.gr_attend) ? "disabled" : "" }
											data-toggle="modal" data-target="#guModal" value="${i.student_id}">수정</button>
										<button type="button" class="btn btn-default btn-xs g_remove ${ (empty i.gr_attend) ? 'disabled' : '' }" ${ (empty i.gr_attend) ? "disabled" : "" }
											data-toggle="modal" data-target="#gdModal" value="${i.student_id}">삭제</button>
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
		<!-- 성적 입력 Modal -->
		<div id="giModal" class="modal fade" role="dialog">
			 <div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<!-- Modal header -->
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title-text">성적 입력</h4>
					</div>

					<form action="inst_gradeAdd.do" method="post">
					
						<input type="hidden" value="${cou_name}" name="h_cou_name">
						<input type="hidden" value="${sub_name}" name="h_sub_name">
						<input type="hidden" value="${sub_start_date}" name="h_sub_start_date">
						<input type="hidden" value="${sub_end_date}" name="h_sub_end_date">
						<input type="hidden" value="${po_attend}" name="h_po_attend">
						<input type="hidden" value="${po_write}" name="h_po_write">
						<input type="hidden" value="${po_practice}" name="h_po_practice">
					
						<!-- Modal body -->
						<div class="modal-body">
							<div class="text-box-bg">
								<div class="form-group" id="text_box1">
									수강생명 : <span id="gi_st_name"></span><br>
									수료여부 : <span id="gi_fin"></span><br>
									중도탈락날짜 : <span id="gi_mid_fail_date"></span>
									<br><br><br><br>
								</div>
								<div class="form-group" id="text_box2">
									과목명 : <span>${sub_name}</span><br>
									과목시작 : <span>${sub_start_date}</span><br>
									과목종료 : <span>${sub_end_date}</span><br>
									출결배점 : <span>${ (empty po_attend)? "X" : po_attend }</span><br>
									필기배점 : <span>${ (empty po_write)? "X" : po_write }</span><br>
									실기배점 : <span>${ (empty po_practice)? "X" : po_practice }</span>
								</div>
							</div>
							
								<input type="hidden" value="${course_id}" name="h_course_id">
								<input type="hidden" value="${subject_id}" name="h_subject_id">
								<input type="hidden" value="" name="gi_student_id" id="gi_student_id">
								
								<div class="form-group">
	
									<label for="i_score1">출결점수:</label> 
										<input type="text" class="form-control" id="i_score1" name="i_score1" 
											required maxlength="2" min="20"> 
								</div>
								<div class="form-group">
									<label for="i_score2">필기점수:</label> 
										<input type="text" class="form-control" id="i_score2" name="i_score2" 
											required maxlength="2">
								</div>
								<div class="form-group">
									<label for="i_score3">실기점수:</label> 
										<input type="text" class="form-control" id="i_score3" name="i_score3" 
											required maxlength="2">
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

		<!-- 성적 수정 Modal -->
		<div id="guModal" class="modal fade" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="modal-content">
					<!-- Modal header -->
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title-text">성적 수정</h4>
					</div>
					<form action="inst_gradeModify.do" method="post">
					
					<input type="hidden" value="${cou_name}" name="h_cou_name">
					<input type="hidden" value="${sub_name}" name="h_sub_name">
					<input type="hidden" value="${sub_start_date}" name="h_sub_start_date">
					<input type="hidden" value="${sub_end_date}" name="h_sub_end_date">
					<input type="hidden" value="${po_attend}" name="h_po_attend">
					<input type="hidden" value="${po_write}" name="h_po_write">
					<input type="hidden" value="${po_practice}" name="h_po_practice">
					
					<!-- Modal body -->
					<div class="modal-body">
						<div class="text-box-bg">
			                <div class="form-group" id="text_box1">
			                   수강생명 : <span id="gu_st_name"></span><br>
								수료여부 : <span id="gu_fin"></span><br>
								중도탈락날짜 : <span id="gu_mid_fail_date"></span>
								<br><br><br><br>
			                </div>
			                <div class="form-group" id="text_box2">
			                   과목명 : <span>${sub_name}</span><br>
								과목시작 : <span>${sub_start_date}</span><br>
								과목종료 : <span>${sub_end_date}</span><br>
								출결배점 : <span>${ (empty po_attend)? "X" : po_attend }</span><br>
								필기배점 : <span>${ (empty po_write)? "X" : po_write }</span><br>
								실기배점 : <span>${ (empty po_practice)? "X" : po_practice }</span>
			                </div>
			               </div>
							
							<input type="hidden" value="${course_id}" name="h_course_id">
							<input type="hidden" value="${subject_id}" name="h_subject_id">
							<input type="hidden" value="" name="gu_student_id" id="gu_student_id">
							
							
								<div class="form-group">

									<label for="u_score1">출결점수:</label> 
										<input type="text" class="form-control" id="u_score1" name="u_score1" 
											required maxlength="2"> 
								</div>
								<div class="form-group">
									<label for="u_score2">필기점수:</label> 
										<input type="text" class="form-control" id="u_score2" name="u_score2" 
											required maxlength="2">
								</div>
								<div class="form-group">
									<label for="u_score3">실기점수:</label> 
										<input type="text" class="form-control" id="u_score3" name="u_score3" 
											required maxlength="2">
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

		<!-- 성적 삭제 Modal -->
		<div id="gdModal" class="modal fade" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<!-- Modal header -->
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title-text">성적 삭제</h4>
					</div>

					<form action="inst_gradeRemove.do" method="post">
					
					<input type="hidden" value="${cou_name}" name="h_cou_name">
					<input type="hidden" value="${sub_name}" name="h_sub_name">
					<input type="hidden" value="${sub_start_date}" name="h_sub_start_date">
					<input type="hidden" value="${sub_end_date}" name="h_sub_end_date">
					<input type="hidden" value="${po_attend}" name="h_po_attend">
					<input type="hidden" value="${po_write}" name="h_po_write">
					<input type="hidden" value="${po_practice}" name="h_po_practice">
					
					<!-- Modal body -->
					<div class="modal-body">
							<div class="text-box-bg">
								<div class="form-group" id="text_box1">
									수강생명 : <span id="gd_st_name"></span><br>
									수료여부 : <span id="gd_fin"></span><br>
									중도탈락날짜 : <span id="gd_mid_fail_date"></span>
									<br><br><br><br>
								</div>
								<div class="form-group" id="text_box2">
									과목명 : <span>${sub_name}</span><br>
									과목시작 : <span>${sub_start_date}</span><br>
									과목종료 : <span>${sub_end_date}</span><br>
									출결배점 : <span>${ (empty po_attend)? "X" : po_attend }</span><br>
									필기배점 : <span>${ (empty po_write)? "X" : po_write }</span><br>
									실기배점 : <span>${ (empty po_practice)? "X" : po_practice }</span>
								</div>
							</div>
							
							<input type="hidden" value="${course_id}" name="h_course_id">
							<input type="hidden" value="${subject_id}" name="h_subject_id">
							<input type="hidden" value="" name="gd_student_id" id="gd_student_id">
							
							<div class="form-group">

								<label for="d_score1">출결점수:</label> 
								<input type="text" class="form-control" id="d_score1" name="d_score1" 
									required maxlength="2" readonly> 
							</div>
							<div class="form-group">
								<label for="d_score2">필기점수:</label> 
								<input type="text" class="form-control" id="d_score2" name="d_score2" 
									required maxlength="2" readonly>
							</div>
							<div class="form-group">
								<label for="d_score3">실기점수:</label> 
								<input type="text" class="form-control" id="d_score3" name="d_score3" 
									required maxlength="2" readonly>
							</div>
							<p class="text-modal-bottom">해당 수강생의 성적을 삭제하시겠습니까?</p>

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
