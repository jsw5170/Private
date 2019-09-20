<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>
<script src="http://code.jquery.com/jquery.js"></script>
	<script>

	function formcheck() {
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
		submit();
	}
	function submit() {
		$.ajax({
			data: $('#reg_frm').serialize(),
			url: 'JoinOutProcess',
			type: 'post',
			dataType: 'text',
			success: function (json) {
				var result = JSON.parse(json);
				if(result.code == "success"){
					alert(result.desc);
					window.location.replace("login.jsp");
				}else{
					alert(result.desc);
				}
			}
		});
	}
	</script>
</head>
<body>
	<form id="reg_frm">
		아이디 : <input type="text" id="id" name="id"><br/>
		비밀번호 : <input type="text" id="pw" name="pw"><br/>
		<input type="button" value="회원탈퇴" onclick="formcheck()">&nbsp;&nbsp;&nbsp;
		<input type="button" value="취소" onclick="javascript:window.location='main.jsp'">
	</form>
</body>
</html>