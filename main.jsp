<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<% 
	if(session.getAttribute("ValidMem") == null) { 
%>
	<jsp:forward page="login.jsp" />
<% 
	} 
	
	String name = (String)session.getAttribute("name");
	String id = (String)session.getAttribute("id");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>JPS/Servlet 21-1</title>
</head>
<body>
	<h1><%= name %>님 안녕하세요.</h1><br>
	<a href="Notice.do?bType=2">공지사항</a>
	<a href="list.do?bType=1">자유게시판</a>
	<a href="referenceRoom.do?bType=3">자료실</a>
	
	<form action="logout.jsp" method="post">
		<input type="button" value="로그아웃" onclick="javascript:window.location='logout.jsp'">&nbsp; &nbsp; &nbsp;
		<input type="button" value="정보수정"
				onclick="javascript:window.location='modify.jsp'">
	</form>
</body>
</html>