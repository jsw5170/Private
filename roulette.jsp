<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String name = (String)session.getAttribute("name");
	String id = (String)session.getAttribute("id");
%>
<!DOCTYPE HTML>
<html lang="ko">
<head>
<title> prototype about roulette </title>
<meta charset="utf-8">
<script type="text/javascript" src="./js/jquery-1.11.3.min.js"></script> 
<script type="text/javascript" src="./js/jQueryRotateCompressed.js"></script>
  <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

<style>
#image{
  margin:50px 50px;z-index:10;
}
#n_id{position:absolute;left:286px;top:75px;z-index:20;}
  #container{
  	 position:absolute; 
 	 top:15%; left:45%; 
  }
</style>
</head>
<body>
<nav class="nav navbar-light" style="background-color: #e3f2fd;">
	<a href="main.jsp"  class="nav-link" tabindex="-1" aria-disabled="true">Home</a>
	<a href="Notice.do?bType=2" class="nav-link">공지사항</a>
	<a href="list.do?bType=1" class="nav-link">자유게시판</a>
	<a href="referenceRoom.do?bType=3" class="nav-link">자료실</a>
	<a href="Map.jsp" class="nav-link">위치</a>
	<a href="roulette.jsp" class="nav-link disabled">룰렛</a>
	<%if(id.equals("manager")) {%>
	<a href="manager.jsp" class="nav-link">관리자 메뉴</a>
	<%}%>
	<a href="#" class="nav-link disabled" tabindex="-1" aria-disabled="true"> <%= name %>님 안녕하세요.</a>
</nav>
<ul class="nav justify-content-end navbar-light" style="background-color: #e3f2fd;">
	<form action="logout.jsp" method="post" class="justify-content-end">
		<input type="button" value="로그아웃" onclick="javascript:window.location='logout.jsp'" class="btn btn-primary">&nbsp; &nbsp; &nbsp;
		<c:if test="${pw != null}">
		<input type="button" value="정보수정" class="btn btn-primary"
		onclick="javascript:window.location='modify.jsp'">
		</c:if>
	</form>
</ul>
<img src="img/roulette.png" id="image">
<img src="img/niddle.png" id="n_id">
<br />
<div id="container">
<input type='button' value='시작' id='start_btn' class="btn btn-primary"></input>
<div id="result_id"></div>
<div id="result_id2"></div>
<div id="result_id3"></div>
</div>
<script>
/* serpiko.tistory.com */
window.onload = function(){
     
    var pArr = ["0","1","2","3","4:꽝","5","6","7","8","9"];
 
    $('#start_btn').click(function(){
        rotation();
    });
 
    function rotation(){
        $("#image").rotate({
          angle:0,
          animateTo:360 * 5 + randomize(0, 360),
          center: ["50%", "50%"],
          easing: $.easing.easeInOutElastic,
          callback: function(){
                        var n = $(this).getRotateAngle();
                        endAnimate(n);
                    },
          duration:5000
       });
    }
 
    function endAnimate($n){
        var n = $n;
        $('#result_id').html("<p>움직인각도:" + n + "</p>");
        var real_angle = n%360 +18;
        var part = Math.floor(real_angle/36);
     
        $('#result_id2').html("<p>상품범위:" + part + "</p>");
 
        if(part < 1){
            $('#result_id3').html("<p>당첨내역:" + pArr[0] + "</p>");
            return;
        }
 
        if(part >= 10){
            $('#result_id3').html("<p>당첨내역:" + pArr[pArr.length-1] + "</p>");
            return;
        }
 
        $('#result_id3').html("<p>당첨내역:" + pArr[part] + "</p>");
    }
 
    function randomize($min, $max){
        return Math.floor(Math.random() * ($max - $min + 1)) + $min;
    }
};
</script>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
	
</body>
</html>