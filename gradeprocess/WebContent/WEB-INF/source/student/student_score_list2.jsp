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
td {
	white-space: nowrap;
}
.table-responsive {
	max-height: 600px;
}

</style>
<script>
$(document).ready(function(){
    $(".bookinfo").on("click",function(){// 삭제 버튼
            $("#bookname").text($(this).parents("tr").children().eq(3).text());
            $("#bookpublisher").text($(this).parents("tr").children().eq(3).find("#publisher").val());
            $("#bookPictureModal").modal();
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
  		  $("#bookPictureModal").modal();
  	  });
    });
    
});
</script>
</head>
<body>
  <body class="nav-md">
    <div class="container body">
      <div class="main_container">
        <div class="col-md-3 left_col">
          <div class="left_col scroll-view">
            <div class="navbar nav_title" style="border: 0;">
              <a href="student_score_list.do" class="site_title"><img src="/gradeprocess/image/sist_symbol.png" id="sistlogo"> 쌍용교육센터</a>
            </div>

            <div class="clearfix"></div>

            <!-- menu profile quick info -->
            <div class="profile clearfix">
              <div class="profile_pic">
                <img src="/gradeprocess/image/profile_photo_student.png" alt="profile_photo" class="img-circle profile_img">
              </div>
              <div class="profile_info">
                <span>수강생</span>
                <h2>${sessionScope.stloginkey}</h2>
              </div>
            </div>
            <!-- /menu profile quick info -->
            <br />
            <!-- sidebar menu -->
            <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
              <div class="menu_section">

                <ul class="nav side-menu">
                  <li><a href="student_score_list.do"><i class="fa fa-pencil"></i> 성적 조회 </a></li>
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
                    <img src="/gradeprocess/image/profile_photo_student.png" alt="">${sessionScope.stloginkey}님
                    <span class=" fa fa-angle-down"></span>
                  </a>
                  <ul class="dropdown-menu dropdown-usermenu pull-right">
                    <li><a href="stpwmodifyform.do?stId=${id}"><span class="glyphicon glyphicon-wrench"></span> 비밀번호 변경</a></li>
                    <li><a href="logout_st.do"><i class="fa fa-sign-out"></i> Log Out</a></li>
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
            
              <div class="clearfix"></div>
              <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>성적 조회</h2>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                    <!--타이틀정보-->
                    <span id="title"><strong>과정명:</strong>${name}&nbsp;<strong>과정시작:</strong>${start}&nbsp;
			<strong>과정종료:</strong>${end}&nbsp; <strong>과목수:</strong>${sub_count}&nbsp; <strong>강의실명:</strong>${class_name}</span>
                    <div class="table-responsive">
                      <table class="table table-default jambo_table bulk_action">
					<thead>
						<tr>
							<th>과목명</th>
							<th>과목시작</th>
							<th>과목종료</th>
							<th>교재명</th>
							<th>강사명</th>
							<th>출결 배점</th>
							<th>필기 배점</th>
							<th>실기 배점</th>
							<th>출결 점수</th>
							<th>필기 점수</th>
							<th>실기 점수</th>
							<th>시험 날짜</th>
							<th>시험 문제</th>
						</tr>
					</thead>
					<tbody>

                        <!-- 
						<tr>
							<td>Java</td>
							<td>2016-04-05</td>
							<td>2016-07-04</td>
							<td><a href="#" data-toggle="modal"
								data-target="#bookPictureModal">이것이 자바다</a></td>
							<td>정우성</td>
							<td>20</td>
							<td>40</td>
							<td>40</td>
							<td>18</td>
							<td>35</td>
							<td>36</td>
							<td>2016-07-04</td>
							<td><a href="#">160101 정보보안취업과정 Linux 이제훈.ZIP</a></td>
						</tr>
						 -->

						<c:forEach var="g" items="${list}">
							<tr>
								<td>${g.sub_name}</td>
								<td>${g.sub_start_date}</td>
								<td>${g.sub_end_date}</td>
								<td><a href="#" class="textbook">${g.text_name}</a>
									
								</td>
								<td>${g.inst_name}</td>
								<td><c:choose>
										<c:when test="${empty g.po_attend}">
											<span class="err">X</span>
										</c:when>
										<c:otherwise>${g.po_attend}</c:otherwise>
									</c:choose></td>
								<td><c:choose>
										<c:when test="${empty g.po_write}">
											<span class="err">X</span>
										</c:when>
										<c:otherwise>${g.po_write}</c:otherwise>
									</c:choose></td>
						        <td><c:choose>
										<c:when test="${empty g.po_practice}">
											<span class="err">X</span>
										</c:when>
										<c:otherwise>${g.po_practice}</c:otherwise>
									</c:choose></td>
								<td><c:choose>
										<c:when test="${empty g.gr_attend}">
											<span class="err">X</span>
										</c:when>
										<c:otherwise>${g.gr_attend}</c:otherwise>
									</c:choose></td>
								<td><c:choose>
										<c:when test="${empty g.gr_write}">
											<span class="err">X</span>
										</c:when>
										<c:otherwise>${g.gr_write}</c:otherwise>
									</c:choose></td>
								<td><c:choose>
										<c:when test="${empty g.gr_practice}">
											<span class="err">X</span>
										</c:when>
										<c:otherwise>${g.gr_practice}</c:otherwise>
									</c:choose></td>
								
								<td><c:choose>
										<c:when test="${empty g.exam_date}">
											<span class="err">X</span>
										</c:when>
										<c:otherwise>${g.exam_date}</c:otherwise>
									</c:choose></td>
								<td><c:choose>
										<c:when test="${empty g.examination}">
											<span class="err">X</span>
										</c:when>
										<c:otherwise><a href="${pageContext.request.contextPath}/exam/${g.exam_zip}">${g.examination}</a></c:otherwise>
									</c:choose></td>
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
					<button type="button" class="btn btn-default btn-sm btn_color"
						data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
    <!-- jQuery -->
    <script src="/gradetest/bootstrap/vendors/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap -->
    <script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <!-- FastClick -->
    <script src="/gradetest/bootstrap/vendors/fastclick/lib/fastclick.js"></script>
    <!-- NProgress -->
    <script src="/gradetest/bootstrap/vendors/nprogress/nprogress.js"></script>
    <!-- iCheck -->
    <!--<script src="../bootstrap/vendors/iCheck/icheck.min.js"></script>-->

    <!-- Custom Theme Scripts -->
    <script src="/gradetest/bootstrap/build/js/custom.js"></script>

  </body>
</html>

