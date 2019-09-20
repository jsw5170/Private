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
	<form id="reg_frm">
		아이디 : <%= dto.getId() %><br/>
		비밀번호 : <input type="password" id="pw" name="pw" size="20"><br/>
		비밀번호 확인 : <input type="password" id="pw_check" name="pw_check" size="20"><br/>
		이름 : <%= dto.getName() %><br/>
		메일 : <input type="text" name="eMail" id="eMail" size="20" value="<%= dto.geteMail() %>"><br/>
		주소 : <input type="text" name="address" id="address" size="50" value="<%= dto.getAddress() %>"><br/>
		<input type="button" value="수정" onclick="formcheck()">&nbsp;&nbsp;&nbsp;
		<input type="reset" value="취소" onclick="javascript:window.location='main.jsp'">
	</form>
</body>
</html>