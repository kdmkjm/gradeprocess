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
	
	// 검색
	$("#skey").find("option[value='${skey}']").attr("selected", "selected");
	$("#svalue").val("${svalue}");
	$("#skey").on("click", function() {
		$("#svalue").val("");
	});
	
	// 수정, 삭제
	$("button.modify").on("click", function() {
		$("#updateId").val($(this).parents("tr").children().eq(0).text());
		$("#updateName").val($(this).parents("tr").children().eq(1).text());
		$("#updateTotal").val($(this).parents("tr").children().eq(2).text());
		$("#updateModal").modal();
	});
	
	$("button.delete").on("click", function() {
		$("#deleteId").val($(this).parents("tr").children().eq(0).text());
		$("#deleteName").val($(this).parents("tr").children().eq(1).text());
		$("#deleteTotal").val($(this).parents("tr").children().eq(2).text());
		$("#deleteModal").modal();
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
						<option value="classroom_id">강의실ID</option>
						<option value="class_name">강의실명</option>
      				</select>
      			</div>
              </form>
              </div>
            </div>
              <div class="clearfix"></div>
              <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>강의실 정보 조회</h2>
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
							<th>강의실ID</th>
							<th>강의실명</th>
							<th>강의실 정원</th>
							<th>수정/삭제</th>
						</tr>
					</thead>
					<tbody>
						<!-- 
						<tr>
							<td>classroom_001</td>
							<td>강의실1</td>
							<td>20</td>
							<td>
								<div class="btn-group">
									<button type="button" class="btn btn-default btn-xs"
										data-toggle="modal" data-target="#updateModal">수정</button>
									<button type="button" class="btn btn-default btn-xs"
										data-toggle="modal" data-target="#deleteModal">삭제</button>
								</div>
							</td>
						</tr>
						-->
						
						<c:forEach var="c" items="${list}">
							<tr>
								<td>${c.classroom_id}</td>
								<td>${c.class_name}</td>
								<td>${c.class_total}</td>
								<td><div class="btn-group"><button type="button" class="btn btn-default btn-xs modify">수정</button>
								<button type="button" class="btn btn-default btn-xs delete" ${(c.delCheck==0)?"":"disabled=\"disabled\""}>삭제</button></div></td>
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
		<!-- addModal -->
		<div id="addModal" class="modal fade" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title-text">강의실 정보 추가</h4>
					</div>
					<!-- form -->
					<form action="classroomAdd.do" method="post">
						<!-- Modal body -->
						<div class="modal-body">
							<div class="form-group">
								<label for="addName">강의실명</label><input type="text"
									class="form-control" id="addName" name="addName" required
									maxlength="20" placeholder="20자 이내">
									
							</div>
							<div class="form-group">
								<label for="addTotal">강의실 정원</label><input type="number"
									class="form-control" id="addTotal" name="addTotal" required
									maxlength="100" placeholder="100명 이하">
							</div>
						</div>
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
					<h4 class="modal-title-text">강의실 정보 수정</h4>
				</div>

				<!-- form -->
				<form action="classroomModify.do" method="post">
					<div class="modal-body">
						<!-- Modal body -->


						<div class="form-group">
							<label for="updateId">강의실ID</label><input type="text"
								class="form-control" id="updateId" name="updateId" required
								value="classroom_001" maxlength="20" readonly>
						</div>
						<div class="form-group">
							<label for="updateName">강의실명</label><input type="text"
								class="form-control" id="updateName" name="updateName" required
								value="강의실1" maxlength="20" placeholder="20자 이내">
						</div>
						<div class="form-group">
							<label for="updateTotal">강의실 정원</label><input type="number"
								class="form-control" id="updateTotal" name="updateTotal"
								required value="20" maxlength="100" placeholder="100명 이하">

						</div>
					</div>
					<!--end Modal body-->
					<!-- footer -->
					<div class="modal-footer">
						<button type="submit" class="btn-submit" value="">확인</button>
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

	<!-- deleteModal -->
		<div id="deleteModal" class="modal fade" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title-text">강의실 정보 삭제</h4>
					</div>
					<!-- form -->
					<form action="classroomRemove.do" method="post">
						<!-- Modal body -->
						<div class="modal-body">
							<div class="form-group">
								<label for="deleteId">강의실ID</label><input type="text"
									class="form-control" id="deleteId" name="deleteId" required
									value="classroom_001" maxlength="20" readonly>
							</div>
							<div class="form-group">
								<label for="deleteName">강의실명</label><input type="text"
									class="form-control" id="deleteName" name="deleteName" required
									value="강의실1" maxlength="20" readonly>
							</div>
							<div class="form-group">
								<label for="deleteTotal">강의실 정원</label><input type="number"
									class="form-control" id="deleteTotal" name="deleteTotal" required
									value="20" maxlength="20" readonly>
							</div>
						<p class="text-modal-bottom">해당 강의실을 삭제 하시겠습니까?</p>
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

