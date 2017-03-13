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
$(document).ready(function() {
	
	// 검색 시 검색값 고정
	$("#skey").find("option[value='${skey}']").attr("selected", "selected");
	$("#svalue").val("${svalue}");
	$("#skey").on("change", function() {
		$("#svalue").val("");
	});
	
	// 수정 버튼
	$("button.modify").on("click", function() {
		$("#updateId").val($(this).parents("tr").children().eq(0).text());
		$("#updateName").val($(this).parents("tr").children().eq(1).text());
		$("#updatePublisher").val($(this).parents("tr").children().eq(2).text());
		$("#updateModal #file").val($(this).parents("tr").children().eq(3).val());
		console.log($(this).parents("tr").find("td a.textbook").length);
		$("#updateModal").modal();
	});
	
	// 삭제 버튼
	$("button.delete").on("click", function() {
		$("#deleteModal #deleteId").val($(this).parents("tr").children().eq(0).text());
		$("#deleteModal #deleteName").val($(this).parents("tr").children().eq(1).text());
		$("#deleteModal #deletePublisher").val($(this).parents("tr").children().eq(2).text());
		//$("#deleteModal #file").val($(this).parents("tr").children().eq(3).val());
		console.log($(this).parents("tr").find("td a.textbook").length);
		$("#deleteModal").modal();
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
	$("#btn_add").on("click", function() {
		$("#addModal").modal();
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
                <h3>기본정보관리 <small></small></h3>
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
							<option value="textbook_id">교재ID</option>
							<option value="text_name">교재명</option>
      				</select>
      			</div>
              </form>
              </div>
            </div>
              <div class="clearfix"></div>
              <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>교재 정보 조회</h2>
                    <!-- Top 버튼 -->
					<button class="btn btn-sm btn-round btn-info pull-right" id="btn_add"><span class="fa fa-plus-circle" style="color: white;"></span> 추가</button>
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
							<th>교재ID</th>
							<th>교재명</th>
							<th>출판사명</th>
							<th>수정/삭제</th>
						</tr>
					</thead>
					<tbody>
					
						<c:forEach var="t" items="${list}">
							<tr>
								<td>${t.textbook_id}</td>
								<td><a href="#" data-toggle="modal" data-target="#bookPictureModal" class="textbook">${t.text_name}</a></td>
								<td>${t.publisher}</td>
								<td><div class="btn-group"><button type="button" class="btn btn-default btn-xs modify">수정</button>
								<button type="button" class="btn btn-default btn-xs delete" ${(t.delCheck == 0)?"":"disabled=\"disabled\""}>삭제</button></div></td>
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
		
	<!-- /////////////////////////////////////////////////////////////////////////////////////////////////// -->
		<!-- +++++++++++++++++++ modal part +++++++++++++++++++ -->
		<!-- bookPicture Modal -->
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
	
	
	<!-- /////////////////////////////////////////////////////////////////////////////////////////////////// -->
		<%-- 
		+++++++++++++++++++ modal part +++++++++++++++++++
		bookPicture Modal
		<div id="bookPictureModal" class="modal fade" role="dialog">

			<div class="modal-dialog ">

				Modal content
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title-text">Book Picture</h4>
					</div>

					<div class="modal-body text-center">
						<img src="/gradeprocess/image/java.gif" alt="java.gif" class="textbook_img">
						<p><strong>이것이 자바다&nbsp;/&nbsp;한빛미디어</strong></p>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default btn-sm"
							data-dismiss="modal">Close</button>
					</div>
				</div>

			</div>
		</div>
		--%>
		
		
		<!-- addModal -->
		<div id="addModal" class="modal fade" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title-text">교재 정보 추가</h4>
					</div>
					<!-- form -->
					<form action="textbookAdd.do" method="post" enctype="multipart/form-data">
						<!-- Modal body -->
						<div class="modal-body">
								<div class="form-group">
									<label for="addName">교재명</label><input type="text"
										class="form-control" id="addName" name="addName" required
										maxlength="20" placeholder="20자 이내">
										
								</div>
								<div class="form-group">
									<label for="addPublisher">출판사명</label><input type="text"
										class="form-control" id="addPublisher" name="addPublisher" required
										maxlength="20" placeholder="20자 이내">
									
								</div>
								<!-- 파일선택 -->
								<div class="form-group">
									<label>교재 표지</label>
									<input type="file" class="form-control" id="file" name="file"
										required> <span class="help-block">.jpg 파일만 업로드 가능합니다.</span>
								</div>

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

		<!-- updateModal -->
		<div id="updateModal" class="modal fade" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title-text">교재 정보 수정</h4>
					</div>
					<!-- form -->
					<form action="textbookModify.do" method="post" enctype="multipart/form-data">
						<!-- Modal body -->
						<div class="modal-body">
								<div class="form-group">
									<label for="updateId">교재ID</label><input type="text"
										class="form-control" id="updateId" name="updateId" required
										value="textbook_001" maxlength="20" placeholder="20자 이내"
										readonly>
								</div>
								<div class="form-group">
									<label for="updateName">교재명</label><input type="text"
										class="form-control" id="updateName" name="updateName" required
										value="이것이 자바다" maxlength="20" placeholder="20자 이내">
								</div>
								<div class="form-group">
									<label for="updatePublisher">출판사명</label><input type="text"
										class="form-control" id="updatePublisher" name="updatePublisher" required
										value="한빛미디어" maxlength="20" placeholder="20자 이내">
								</div>
								<!-- 파일선택 -->
								<div class="form-group">
									<input type="file" class="form-control" id="file" name="file"
										required>
								</div>
								
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

		<!-- deleteModal -->
		<div id="deleteModal" class="modal fade" role="dialog" enctype="multipart/form-data">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title-text">교재 정보 삭제</h4>
					</div>
					<!-- form -->
					<form action="textbookRemove.do" method="post">
						<!-- Modal body -->
						<div class="modal-body">
								<div class="form-group">
									<label for="deleteId">교재ID</label><input type="text"
										class="form-control" id="deleteId" name="deleteId" required
										value="textbook_001" maxlength="20" placeholder="20자 이내"
										readonly>
								</div>
								<div class="form-group">
									<label for="deleteName">교재명</label><input type="text"
										class="form-control" id="deleteName" name="deleteName" required
										value="이것이 자바다" maxlength="20" placeholder="20자 이내" readonly>
								</div>
								<div class="form-group">
									<label for="deletePublisher">출판사명</label><input type="text"
										class="form-control" id="deletePublisher" name="deletePublisher" required
										value="한빛미디어" maxlength="20" placeholder="20자 이내" readonly>
								</div>
								<!-- 파일선택 -->
								<div class="form-group">
									<input type="file" class="form-control" id="file" name="file"
										required readonly disabled>
								</div>
							<p class="text-modal-bottom">해당 교재를 삭제 하시겠습니까?</p>
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
<!-- +++++++++++++++++++ script part +++++++++++++++++++ -->
    <!-- jQuery -->
    <script src="${pageContext.request.contextPath}/bootstrap/vendors/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <!-- FastClick -->
    <script src="${pageContext.request.contextPath}/bootstrap/vendors/fastclick/lib/fastclick.js"></script>
    <!-- NProgress -->
    <script src="${pageContext.request.contextPath}/bootstrap/vendors/nprogress/nprogress.js"></script>
	<!-- Custom Theme Scripts -->
    <script src="${pageContext.request.contextPath}/bootstrap/build/js/custom.js"></script>
</body>
</html>
