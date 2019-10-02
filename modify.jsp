<%@page import="com.study.project.DAOEx" %>
<%@page import="com.study.project.DTOEx" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	String id = (String)session.getAttribute("id");
	DAOEx dao = DAOEx.getInstance();
	DTOEx dto = dao.getMember(id);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JPS/Servlet 21-1</title>
<script src="http://code.jquery.com/jquery.js"></script>
 <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<script>
	function formcheck() {
		if($('#pw').val() == ""){
			alert("패스워드를 입력하세요.");
			$('#pw').focus();
			return;
		}
		if($('#pw').val() != $('#pw_check').val()){
			alert("비밀번호가 일치하지 않습니다.");
			$('#pw').focus();
			return;
		}
		/* if($('#eMail').val().length == 0){
			alert("메일은 필수사항입니다.");
			$('#eMail').focus();
			return;
		} */
		submit();
	}
	function submit() {
		$.ajax({
			data: $('#reg_frm').serialize(),
			url: 'modifyProcess',
			type: 'post',
			dataType: 'text',
			success: function (json) {
				var result = JSON.parse(json);
				if(result.code == "success"){
					alert(result.desc);
					window.location.replace("main.jsp");
				}else{
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
	<form id="reg_frm" class="text-center">
		아이디 : <span class="form-control"><%= dto.getId() %></span><br/>
		비밀번호 : <input type="password" id="pw" name="pw" size="20" class="form-control"><br/>
		비밀번호 확인 : <input type="password" id="pw_check" name="pw_check" size="20" class="form-control"><br/>
		이름 :<span class="form-control">  <%= dto.getName() %> </span><br/>
		메일 : <input type="text" name="eMail" id="eMail" size="20" value="<%= dto.geteMail() %>" class="form-control"><br/>
		주소 : <input type="text" name="address" id="address" size="50" value="<%= dto.getAddress() %>" class="form-control"><br/>
		<input type="button" value="수정" onclick="formcheck()" class="form-control">&nbsp;&nbsp;&nbsp;
		<input type="reset" value="취소" onclick="javascript:window.location='main.jsp'" class="form-control">
	</form>
	</div>
	</div>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
		    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
		    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>