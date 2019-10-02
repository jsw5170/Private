<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
String ctx = request.getContextPath();  //콘텍스트명 얻어오기.
response.setHeader("Pragma-directive", "no-cache");
response.setHeader("Cache-directive", "no-cache");
response.setHeader("Pragma", "no-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires",0);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery.js"></script>
  <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Imagetoolbar" content="no" />
<script type="text/javascript">
var rand;
//캡차 오디오 요청
function audioCaptcha(type) {
       var kor = (type > 0) ? "lan=kor&":"";
       $.ajax({
             url: 'captcha/CaptChaAudio.jsp',
             type: 'POST',
             dataType: 'text',
             data: 'rand=' + rand + '&ans=y',
             async: false,      
             success: function(resp) {
                    var uAgent = navigator.userAgent;
                    var soundUrl = 'captcha/CaptChaAudio.jsp?' + kor + 'rand=' + rand + '&ans=' + resp;
                    //console.log(soundUrl);
                    if (uAgent.indexOf('Trident') > -1 || uAgent.indexOf('MSIE') > -1) {
                           winPlayer(soundUrl+'&agent=msie');
                    } else if (!!document.createElement('audio').canPlayType) {
                           try { new Audio(soundUrl).play(); } catch(e) { winPlayer(soundUrl); }
                    } else window.open(soundUrl, '', 'width=1,height=1');
             }
       });
}
function winPlayer(objUrl) {
       $('#audiocatpch').html(' <bgsound src="' + objUrl + '">');
}
//캡차 이미지 요청 (캐쉬문제로 인해 이미지가 변경되지 않을수있으므로 요청시마다 랜덤숫자를 생성하여 요청)
function changeCaptcha() {
       rand = Math.random();
       $('#catpcha').html('<img src="<%=ctx%>/captcha/CaptChaImg.jsp?rand=' + rand + '"/>');
}
$(document).ready(function() {
       changeCaptcha(); //캡차 이미지 요청
       $('#reLoad').click(function(){ changeCaptcha(); }); //새로고침버튼에 클릭이벤트 등록
       $('#soundOn').click(function(){ audioCaptcha(0); }); //음성듣기버튼에 클릭이벤트 등록
       //확인 버튼 클릭시
       $('#frmSubmit').click(function(){
             if ( !$('#answer').val() ) {
                    alert('이미지에 보이는 숫자 또는 스피커를 통해 들리는 숫자를 입력해 주세요.');
             } else {
                    $.ajax({
                           url: 'captcha/captchaSubmit.jsp',
                           type: 'POST',
                           dataType: 'text',
                           data: 'answer=' + $('#answer').val(),
                           async: false,      
                           success: function(resp) {
                                 alert(resp);
                                 $('#check').val(resp) 
                                 $('#reLoad').click();
                                 $('#answer').val('');
                           }
                    });
             }
       });
});
</script>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<script>
	function form_check() {
		if($('#id').val().length == 0){
			alert("아이디는 필수사항입니다.");
			$('#id').focus();
			return;
		}
		if($('#id').val().length < 4){
			alert("아이디는 4글자 이상이어야 합니다.");
			$('#id').focus();
			return;
		}
		if($('#pw').val().length == 0){
			alert("비밀번호는 필수사항입니다.");
			$('#pw').focus();
			return;
		}
		if($('#pw').val() != $('#pw_check').val()){
			alert("비밀번호가 일치하지 않습니다.");
			$('#pw').focus();
			return;
		}
		if($('#name').val().length == 0){
			alert("이름은 필수사항입니다.");
			$('#name').focus();
			return;
		}
		if($('#eMail').val().length == 0){
			alert("메일은 필수사항입니다.");
			$('#eMail').focus();
			return;
		}
		if($('#check').val() != '확인'){
			alert("catpcha");
			$('#check').focus();
			return;
		}
		submit();
	}
	function submit() {
		$.ajax({
			data: $('#joinForm').serialize(),
			url: 'joinProcess',
			dataType: 'text',
			type: 'post',
			success: function (json) {
				var result = JSON.parse(json);
				if(result.code == 'success'){
					alert(result.desc);
					window.location.replace("login.jsp");				
				} else{
					alert(result.desc);
				}
			}
		});
	}
</script>
</head>
<body>
<div class="container">
	 <div class="row justify-content-center">
		 <div class="form-group">
			<form id="joinForm" class="text-center">
				아이디 : <input type="text" id="id" name="id" class="form-control">
				비밀번호 : <input type="password" id="pw" name="pw" class="form-control">
				비밀번호 확인 : <input type="password" id="pw_check" name="pw" class="form-control">
				이름 : <input type="text" id="name" name="name" class="form-control">
				이메일 : <input type="text" id="eMail" name="eMail" class="form-control">
				주소 : <input type="text" id="address" name="address" class="form-control">
				  <div id="catpcha"></div>
			       <div id="audiocatpch" style="display: none;"></div>
			       <input id="reLoad" type="button" value="새로고침" />
			       <input id="soundOn" type="button" value="음성듣기" />
			       <br />
			       <input type="text" id="answer" name="answer" value="" />
			       <input type="button" id="frmSubmit" value="확인" />
			       <input id="check" type="hidden">
				<input type="button" value="회원가입"  onclick="form_check()" class="form-control"> &nbsp;&nbsp;&nbsp;
				<input type="button" value="로그인" onclick="javascript:window.location='login.jsp'" class="form-control"> &nbsp;&nbsp;&nbsp;
				<input type="reset" class="form-control" value="취소">		
			</form>
		    <!-- Optional JavaScript -->
		    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
		    <script integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
		    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
		    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
		</div>
	</div>
</div>
</body>
</html>