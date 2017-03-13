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
<!-- ========================== jQuery ========================== -->
$(document).ready(function(){
	//조회 버튼
	$(".btn_info").on("click",function(){
		console.log($(this).val());
		$(".hidden_form .hidden_id").val($(this).parents("tr").children().eq(0).text());
		$(".hidden_form .hidden_name").val($(this).parents("tr").children().eq(1).text());
		$(".hidden_form .hidden_phone").val($(this).parents("tr").children().eq(2).text());
		$(".hidden_form .hidden_register").val($(this).parents("tr").children().eq(4).text());
	});
	//수정 버튼
	$(".btn_modify").on("click",function(){
		$("#modify_id").val($(this).parents("tr").children().eq(0).text());
		$("#modify_name").val($(this).parents("tr").children().eq(1).text());
		//$("#modify_pw").val($(this).parents("tr").children().eq(2).text());
		$("#modify_phone").val($(this).parents("tr").children().eq(2).text());
		$("#studentModifyModal").modal();
	});
	//삭제 버튼
	$(".btn_remove").on("click",function(){
		$("#student_id").val($(this).parents("tr").children().eq(0).text());
		$("#remove_name").val($(this).parents("tr").children().eq(1).text());
		//$("#remove_pw").val($(this).parents("tr").children().eq(2).text());
		$("#remove_phone").val($(this).parents("tr").children().eq(2).text());
		$("#studentRemoveModal").modal();
	});
	//과정 등록 버튼
	$(".btn_cours_add").on("click",function(){
		$("#student_id2").val($(this).parents("tr").children().eq(0).text());
		$("#courseAddModal").modal();
	});
	//수강생 추가 버튼
	$("#btn_studentAdd").on("click",function(){
		$("#studentAddModal").modal();
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
                  </div>
                </div>
                <!-- 검색 선택 -->
                <div class="col-md-2.5 col-sm-2.5 col-xs-2.5 form-group pull-right">
      				<select class="form-control top_search_select" name="skey" id="skey">
      					<option value="all">전체</option>
							<option value="sc_st-id">수강생ID</option>
							<option value="sc_st-name">수강생명</option>
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
			<a href="student_fail.do" class="btn btn-sm btn-round btn-danger pull-right">과정탈락</a>
			<button class="btn btn-sm btn-round btn-info pull-right" id="btn_studentAdd"><span class="fa fa-plus-circle" style="color: white;"></span> 수강생 추가</button>
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
              <th>수강생ID</th>
              <th>수강생 명</th>
              <!-- <th>주민번호 뒷자리</th> -->
              <th>전화번호</th>
              <th>수강횟수</th>
              <th>등록일</th>
              <!-- <th>수강상태</th> -->
              <!-- <th>과정명</th> -->
              <th></th>
            </tr>
          </thead>
          <tbody>
          <c:forEach var="st" items="${list}">
          	<tr>
              <td>${st.student_id}</td>
              <td>${st.st_name}</td>
              <%-- <td>${st.st_pw}</td> --%>
              <td>${st.st_phone}</td>
              <td>${st.cou_count}</td>
              <td>${st.register_date}</td>
              <%-- <td>${progressArr[st.progress_ox]}</td> --%>
              <!-- <td>쿼리 생성 필요함</td> -->
              <td>
              <form action="student_list2.do" method="post" class="hidden_form">
                <div class="btn-group">
                  <button type="submit" class="btn btn-default btn-xs btn_info" value="조회">조회</button>
                  <button type="button" class="btn btn-default btn-xs btn_modify">수정</button>
                  <button type="button" class="btn btn-default btn-xs btn_remove ${(st.cou_count == 0)?'':'disabled'}" ${(st.cou_count == 0)?'':'disabled'}>삭제</button>
                  <button type="button" class="btn btn-default btn-xs btn_cours_add">과정등록</button>
                  <input type="hidden" class="hidden_id" name="hidden_stId">
                  <input type="hidden" class="hidden_name" name="hidden_stName">
                  <input type="hidden" class="hidden_phone" name="hidden_stPhone">
                  <input type="hidden" class="hidden_register" name="hidden_stRegister">
                </div>
              </form>
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
  <!-- 수강생 추가 -->
  <div id="studentAddModal" class="modal fade" role="dialog">
    <div class="modal-dialog">
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="text-center">수강생 정보 추가</h4>
        </div>
        <!-- form -->
        <form action="studentAdd.do" method="post">
        <div class="modal-body">
          <!-- Modal body -->
            <div class="form-group">
              <label for="name">수강생 명</label><input type="text" class="form-control" id="name" name="name"   required maxlength="20" placeholder="20자 이내">
            </div>
            <div class="form-group">
              <label for="pw">주민번호 뒷자리(7자리)</label><input type="text" class="form-control" id="pw" name="pw"  required maxlength="20" placeholder="20자 이내">
            </div>
            <div class="form-group">
              <label for="phone">전화번호</label><input type="text" class="form-control" id="phone" name="phone"   required maxlength="20" placeholder="20자 이내">
            </div>
        </div> <!--end Modal body-->

        <!-- footer -->
        <div class="modal-footer">
          <input type="submit" class="btn-submit" value="확인">
          <button type="button" class="btn-cancle" data-dismiss="modal">취소</button>
        </div> <!--end footer-->
        </form>
      </div> <!--end modal-content-->
    </div> <!--end modal-dialog-->
  </div> <!--end Modal-->

  <!-- 수강생 수정 -->
  <div id="studentModifyModal" class="modal fade" role="dialog">
    <div class="modal-dialog">
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="text-center">수강생 정보 수정</h4>
        </div>
            <!-- form -->
            <form action="studentModify.do" method="post">
        <div class="modal-body">
          <!-- Modal body -->
              <div class="form-group">
                <label for="id">수강생ID</label><input type="text" class="form-control" id="modify_id" name="modify_id" required maxlength="20" readonly>
              </div>
              <div class="form-group">
                <label for="name">수강생 명</label><input type="text" class="form-control" id="modify_name" name="modify_name" required maxlength="20" placeholder="20자 이내">
              </div>
<!-- 
              <div class="form-group">
                <label for="pw">주민번호 뒷자리</label><input type="text" class="form-control" id="modify_pw" name="pw" required maxlength="20" placeholder="20자 이내">
              </div>
 -->
              <div class="form-group">
                <label for="phone">전화번호</label><input type="text" class="form-control" id="modify_phone" name="modify_phone" required maxlength="20" placeholder="20자 이내">
              </div>
          </div> <!--end Modal body-->
              <!-- footer -->
          <div class="modal-footer">
            <input type="submit" class="btn-submit" value="확인">
            <button type="button" class="btn-cancle" data-dismiss="modal">취소</button>
          </div> <!--end footer-->
          </form>
        </div> <!--end modal-content-->
      </div> <!--end modal-dialog-->
    </div> <!--end Modal-->

  <!-- 수강생 삭제 -->
  <div id="studentRemoveModal" class="modal fade" role="dialog">
    <div class="modal-dialog">
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="text-center">수강생 정보 삭제</h4>
        </div>
        <!-- form -->
        <form action="studentRemove.do" method="post">
        <div class="modal-body">
          <!-- Modal body -->
              <div class="form-group">
                <label for="id">수강생ID</label><input type="text" class="form-control" id="student_id" name="student_id" required maxlength="20" readonly>
              </div>
              <div class="form-group">
                <label for="name">수강생명</label><input type="text" class="form-control" id="remove_name" name="name" required maxlength="20" readonly>
              </div>
              <!-- 
              <div class="form-group">
                <label for="pw">주민번호 뒷자리</label><input type="text" class="form-control" id="remove_pw" name="pw" required maxlength="20" readonly>
              </div>
               -->
              <div class="form-group">
                <label for="phone">전화번호</label><input type="text" class="form-control" id="remove_phone" name="phone" required maxlength="20" readonly>
              </div>
              <p class="text-modal-bottom">해당 수강생 정보를 삭제 하시겠습니까?</p>

            </div> <!--end Modal body-->
                <!-- footer -->
            <div class="modal-footer">
              <input type="submit" class="btn-submit" value="확인">
              <button type="button" class="btn-cancle" data-dismiss="modal">취소</button>
            </div> <!--end footer-->
            </form>
          </div> <!--end modal-content-->
        </div> <!--end modal-dialog-->
      </div> <!--end Modal-->

  <!-- 수강생 신규 과정 등록 -->
  <div id="courseAddModal" class="modal fade" role="dialog">
    <div class="modal-dialog modal-lg">

      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="text-center">수강생 신규 과정 등록</h4>
        </div>
        <form action ="student_course_add.do" method="post">
        <div class="modal-body">
          <div id="box01">
            <table class="table table-striped">
            <thead>
              <tr>
                <th></th>
                <th>과정ID</th>
                <th>과정명</th>
                <th>과정시작</th>
                <th>과정종료</th>
                <th>강의실명</th>
                <th>수강인원</th>
              </tr>
            </thead>
            <tbody>
            <c:forEach var="c" items="${courselist}">
            	<tr>
	                <td>
	                  <label><input type="radio" id ="courseRadio" name="courseRadio" value="${c.course_id}"></label>
	                </td>
	                <td>${c.course_id}</td>
	                <td>${c.cou_name}</td>
	                <td>${c.cou_start_date}</td>
	                <td>${c.cou_end_date}</td>
	                <td>${c.class_name}</td>
	                <td>${c.class_total}</td>
	                <td><input type="hidden" id="student_id2" name="student_id2"></td>
            	</tr>
            </c:forEach>
            </tbody>
            </table>
          </div>
          <p class="text-modal-bottom">등록하실 신규 과정을 선택해 주세요.</p>
        </div> <!--end Modal body-->
            <!-- footer -->
        <div class="modal-footer">
          <input type="submit" class="btn-submit" value="확인">
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

