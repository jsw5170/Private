<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.study.project.dto.BDto"%>
 <%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<% 
	if(session.getAttribute("ValidMem") == null) { 
%>
	<jsp:forward page="login.jsp" />
<% 
	} 
	
	String name = (String)session.getAttribute("name");
	String id = (String)session.getAttribute("id");
	String pw = (String)session.getAttribute("pw");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

<title>JPS/Servlet 21-1</title>
<style>
		div .board {
	    	height: 100px;
	    	weight: 100px;
	    	border-width: 1px;
	    	border-style: solid;
	    	border-color: black;
    	}
 
</style>
</head>
<body>
<nav class="nav navbar-light" style="background-color: #e3f2fd;">
	<a href="main.jsp"  class="nav-link disabled" tabindex="-1" aria-disabled="true">Home</a>
	<a href="Notice.do?bType=2" class="nav-link">공지사항</a>
	<a href="list.do?bType=1" class="nav-link">자유게시판</a>
	<a href="referenceRoom.do?bType=3" class="nav-link">자료실</a>
	<a href="Map.jsp" class="nav-link">위치</a>
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
		<input type="button" value="회원탈퇴" class="btn btn-primary"
		onclick="javascript:window.location='joinout.jsp'">
		
		</c:if>
	</form>
</ul>
  	  <div class="row">
  	  <div class="board align-self-start"></div>
  	  	<div class="board align-self-start">
  	  		<table class="table table-striped" cellpadding="0" cellspacing="0" border="1">
  	  		<form action="list.do?bType=1"></form>
  	  		<input type="hidden" name="bType" value="1">
			<thead>
				<tr>
					<th scope="col" class="text-center">번호</th>
					<th scope="col" class="text-center">이름</th>
					<th scope="col" class="text-center">제목</th>
					<th scope="col" class="text-center">날짜</th>
					<th scope="col" class="text-center">조회수</th>
					<th scope="col" class="text-center">추천</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${list}" var="dto">
			<tr>
				<%
					BDto dto = (BDto)pageContext.getAttribute("dto");
					java.util.Date date = dto.getbDate();
					long now = System.currentTimeMillis();
					long inputDate = date.getTime();
					long cha = ((now - inputDate) - (1000*60*60*24*3));
				%>
				<td class="text-center">${dto.bId} </td>
				<td class="text-center">${dto.bName} </td>
				<td>
					<c:forEach begin="1" end="${dto.bIndent}">-</c:forEach>
					<a href="content_view.do?bType=1&bId=${dto.bId}" class="text-center">${dto.bTitle}
						<c:if test="<%=cha < 0%>"><span class="badge badge-danger">New</span></c:if>
					</a> 
				</td>
				<td class="text-center">${dto.bDate} </td>
				<td class="text-center">${dto.bHit} </td>
				<td class="text-center">${dto.bLike} </td>
			</tr>
			</c:forEach>		
		</table>
  	  	</div>
  	  	<div class="board align-self-center">
  	  			<table class="table table-striped" cellpadding="0" cellspacing="0" border="1">
			<thead>
				<tr>
					<th scope="col" class="text-center">번호</th>
					<th scope="col" class="text-center">이름</th>
					<th scope="col" class="text-center">제목</th>
					<th scope="col" class="text-center">날짜</th>
					<th scope="col" class="text-center">조회수</th>
					<th scope="col" class="text-center">추천</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${list}" var="dto">
			<tr>
				<%
					BDto dto = (BDto)pageContext.getAttribute("dto");
					java.util.Date date = dto.getbDate();
					long now = System.currentTimeMillis();
					long inputDate = date.getTime();
					long cha = ((now - inputDate) - (1000*60*60*24*3));
				%>
				<td class="text-center">${dto.bId} </td>
				<td class="text-center">${dto.bName} </td>
				<td>
					<c:forEach begin="1" end="${dto.bIndent}">-</c:forEach>
					<a href="content_view.do?bType=1&bId=${dto.bId}" class="text-center">${dto.bTitle}
						<c:if test="<%=cha < 0%>"><span class="badge badge-danger">New</span></c:if>
					</a> 
				</td>
				<td class="text-center">${dto.bDate} </td>
				<td class="text-center">${dto.bHit} </td>
				<td class="text-center">${dto.bLike} </td>
			</tr>
			</c:forEach>		
		</table>
  	  	</div>
  	  	<div class="board align-self-end">
  	  			<table class="table table-striped" cellpadding="0" cellspacing="0" border="1">
			<thead>
				<tr>
					<th scope="col" class="text-center">번호</th>
					<th scope="col" class="text-center">이름</th>
					<th scope="col" class="text-center">제목</th>
					<th scope="col" class="text-center">날짜</th>
					<th scope="col" class="text-center">조회수</th>
					<th scope="col" class="text-center">추천</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${list}" var="dto">
			<tr>
				<%
					BDto dto = (BDto)pageContext.getAttribute("dto");
					java.util.Date date = dto.getbDate();
					long now = System.currentTimeMillis();
					long inputDate = date.getTime();
					long cha = ((now - inputDate) - (1000*60*60*24*3));
				%>
				<td class="text-center">${dto.bId} </td>
				<td class="text-center">${dto.bName} </td>
				<td>
					<c:forEach begin="1" end="${dto.bIndent}">-</c:forEach>
					<a href="content_view.do?bType=1&bId=${dto.bId}" class="text-center">${dto.bTitle}
						<c:if test="<%=cha < 0%>"><span class="badge badge-danger">New</span></c:if>
					</a> 
				</td>
				<td class="text-center">${dto.bDate} </td>
				<td class="text-center">${dto.bHit} </td>
				<td class="text-center">${dto.bLike} </td>
			</tr>
			</c:forEach>		
		</table>
  	  	</div>
  	  </div>
   <!-- Optional JavaScript -->
		    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
		    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
		    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
		    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
	
</body>
</html>