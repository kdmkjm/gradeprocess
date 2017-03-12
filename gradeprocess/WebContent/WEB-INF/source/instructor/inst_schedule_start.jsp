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
</style>
<script>
$(document).ready(function(){
	
	$(".st_info").on("click",function(){ // 수강생 조회
		
		var subject_id = $(this).val();
		console.log(subject_id);
		
	  	$("#ss_cou_name").text($(this).parents("tr").children().eq(0).text());
	    $("#ss_sub_name").text($(this).parents("tr").children().eq(2).text());
	    $("#ss_sub_start_date").text($(this).parents("tr").children().eq(3).text());
	    $("#ss_sub_end_date").text($(this).parents("tr").children().eq(4).text());
	    $("#ss_st_total").text($(this).parents("tr").children().eq(7).text());
	    
	    $.post("inst_ajax.do",{ subject_id:subject_id },function(data) {
	  		
	  		console.log("data : "+data);
	  		
	  		var students = JSON.parse(data);//JSON obj
	  		/* console.log("JSON.parse(data) : "+students);
	  		console.log(students.student); 
			console.log(students.student.length); */
			
	  		var student = students.student;//Array obj
	  		
	  		
	  		var len = student.length;
	  		
	  		var st_info = '';
	 
	  		for (var i=0; i<len; ++i) {
	  			st_info += '                  <tr>';
	  			st_info += '                    <td>'+student[i].student_id+'</td>';
	  			st_info += '                    <td>'+student[i].st_name+'</td>';
	  			st_info += '                    <td>'+student[i].st_phone+'</td>';
	  			st_info += '                    <td>'+student[i].st_regist+'</td>';
	  			st_info += '                    <td>'+student[i].st_fin+'</td>';
	  			st_info += '                  </tr>';
	  		}
	  		$("#st_info_tbody").html(st_info);//조회 modal 수강생 테이블
	    });
	    
	  });	

  $(".textbook").on("click", function() { // 교재 사진 모달
	  $("#bookPictureModal #text_name").text($(this).parents("tr").children().eq(5).text());
	  $("#bookPictureModal #publisher").text($(this).next().val());
	  //$("#bookPictureModal #publisher").text($(this).next().val()); //책표지 부분
	  
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
                <h3>강의 스케줄 <small></small></h3>
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
      					<option value="start">전체</option>
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
                    <h2>강의예정</h2>
                    <!-- Top 버튼 -->
					
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
							<th>과목 시작</th>
							<th>과목 종료</th>
							<th>강의실명</th>
							<th>교재명</th>
							<th>수강인원</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<!-- 
                  <tr>
                     <td>정보보안</td>
                     <td>JAVA</td>
                     <td>2017-01-02</td>
                     <td>2017-01-02</td>
                     <td>강의실1</td>
                     <td>자바를 잡아라</td>
                     <td>16</td>
                     <td>강의종료</td>
                     <td>
                        <div class="btn-group" id="btn-group">
                           <button type="button" class="btn btn-default btn-xs"
                              data-toggle="modal" data-target="#ssModal">수강생 조회</button>
                        </div>
                     </td>
                  </tr>
            <tr>
                     <td>정보보안</td>
                     <td>JAVA</td>
                     <td>2017-01-02</td>
                     <td>2017-01-02</td>
                     <td>강의실1</td>
                     <td>자바를 잡아라</td>
                     <td>16</td>
                     <td>강의중</td>
                     <td>
                        <div class="btn-group" id="btn-group">
                           <button type="button" class="btn btn-default btn-xs"
                              data-toggle="modal" data-target="#ssModal">수강생 조회</button>
                        </div>
                     </td>
                  </tr>
            <tr>
                     <td>정보보안</td>
                     <td>JAVA</td>
                     <td>2017-01-02</td>
                     <td>2017-01-02</td>
                     <td>강의실1</td>
                     <td>자바를 잡아라</td>
                     <td>16</td>
                     <td>강의중</td>
                     <td>
                        <div class="btn-group" id="btn-group">
                           <button type="button" class="btn btn-default btn-xs"
                              data-toggle="modal" data-target="#ssModal">수강생 조회</button>
                        </div>
                     </td>
                  </tr>
            <tr>
                     <td>정보보안</td>
                     <td>JAVA</td>
                     <td>2017-01-02</td>
                     <td>2017-01-02</td>
                     <td>강의실1</td>
                     <td>자바를 잡아라</td>
                     <td>16</td>
                     <td>강의예정</td>
                     <td>
                        <div class="btn-group" id="btn-group">
                           <button type="button" class="btn btn-default btn-xs"
                              data-toggle="modal" data-target="#ssModal">수강생 조회</button>
                        </div>
                     </td>
                  </tr>
			 -->
						<c:forEach var="i" items="${list}">
							<tr>
								<td>${i.cou_name}</td>
								<td>${i.cou_start_date} ~ ${i.cou_end_date}</td>
								<td>${i.sub_name}</td>
								<td>${i.sub_start_date}</td>
								<td>${i.sub_end_date}</td>
								<td>${i.class_name}</td>
								<td><a href="#" data-toggle="modal" data-target="#bookPictureModal" class="textbook">${i.text_name}</a>
									<input type="hidden" value="${i.publisher}" id="t_publisher"
									name="t_publisher"> <!-- 책 표지 설정 부분 --> <%-- <input type="hidden" value="${i.publisher}" id="t_publisher" name="t_publisher"> --%>
								</td>
								<td>${i.st_total}</td>
								<td>
									<div class="btn-group" id="btn-group">
										<button type="button" class="btn btn-default btn-xs st_info"
											data-toggle="modal" data-target="#ssModal" value="${i.subject_id}">수강생 조회</button>
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


	<!-- 수강생 조회 Modal -->
	<div id="ssModal" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title-text">수강생 정보 조회</h4>
				</div>
				<div class="modal-body">
					<div class="panel-body">
						<div class="text-box-bg">
							<div class="form-group" id="box02">
								과정명 :<span id="ss_cou_name">정보보안</span><br> 과목명 :<span
									id="ss_sub_name">Java</span><br> 과목 시작 :<span
									id="ss_sub_start_date">2016-04-05</span><br> 과목 종료 :<span
									id="ss_sub_end_date">2016-05-05</span><br> 수강인원 :<span
									id="ss_st_total">4</span><br>
							</div>
						</div>

						<div id="sc2">
							<table class="table table-striped">
								<thead>
									<tr>
										<th>수강생 ID</th>
										<th>수강생 명</th>
										<th>전화번호</th>
										<th>등록일</th>
										<th>수료여부</th>
									</tr>
								</thead>
								<tbody id="st_info_tbody">
								<!-- 
									<tr>
										<td>st_001</td>
										<td>송중기</td>
										<td>010-7777-7777</td>
										<td>2016-01-01</td>
										<td>null</td>
									</tr>
								 -->
								</tbody>
							</table>

						</div>
					</div>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-default navbar-rigth btn_color"
						data-dismiss="modal">Close</button>
				</div>

			</div>
		</div>
	</div>

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
</body>
</html>
