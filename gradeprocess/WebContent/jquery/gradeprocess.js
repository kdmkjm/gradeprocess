// ==================== nav ====================
$(document).ready(function() {
  $(".btn_nav").on({
    mouseenter: function(){
        $(this).css("background-color", "#E7E7E7");
    },
    mouseleave: function(){
        $(this).css("background-color", "#F8F8F8");
    },
  });

//  로그아웃 버튼
  $(".logout").on("click", function(){
	  var homeUrl = "login_student.jsp";
	  $(location).attr("href",homeUrl);
  });
});

// ====================  ====================

