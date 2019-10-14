<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% String name = (String)session.getAttribute("name");
String bType = (String)session.getAttribute("bType");
String id = (String)session.getAttribute("id");
%>
<html>
<head>
<meta charset="UTF-8">
 <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

<title>Insert title here</title>
<script src="https://cdn.ckeditor.com/4.12.1/standard/ckeditor.js"></script>
<style>
	th {
    width: 150px;
    padding: 10px;
    font-weight: bold;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
    background: #e3f2fd;
	}
	.mid {
    width: 350px;
    padding: 10px;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
    }
	#content {
		position:absolute; 
		top:10%; left:10%; 
		width: 800px;
		height: 500px;
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
		</c:if>
	</form>
</ul>
	<table id="content" width="800" cellpadding="0" cellspacing="0" border="1" >
		<form action="reply.do" method="post">
			<input type="hidden" name="bId" value="${reply_view.bId}">
			<input type="hidden" name="bGroup" value="${reply_view.bGroup}">
			<input type="hidden" name="bStep" value="${reply_view.bStep}">
			<input type="hidden" name="bIndent" value="${reply_view.bIndent}">
			<tr>
				<th class="text-center"> 번호 </th>
				<td> ${reply_view.bId} </td>
			</tr>
			<tr>
				<th class="text-center"> 히트 </th>
				<td> ${reply_view.bHit} </td>
			</tr>
			<tr>
				<th class="text-center"> 이름 </th>
				<td><%= name%></td>
			</tr>
			<tr>
				<th class="text-center"> 제목 </th>
				<td> <input type="text" name="bTitle" value="${reply_view.bTitle}"> </td>
			</tr>
			<tr>
				<th class="text-center"> 내용 </th>
				<td><textarea rows="10" name="bContent" id="bContent">${reply_view.bContent}</textarea></td>
				<script>
				CKEDITOR.replace( 'bContent' );
				</script>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="답변" class="btn btn-primary"> 
					<%if(bType.equals("1")) {%>
					<a href="list.do?bType=1&page=<%= session.getAttribute("cpage") %>" class="btn btn-primary">목록보기</a>
					<%} else if(bType.equals("2")) {%>
					<a href="Notice.do?bType=2&page=<%= session.getAttribute("cpage") %>" class="btn btn-primary">목록보기</a>
					<%} else if(bType.equals("3")) {%>
					<a href="referenceRoom.do?bType=3&page=<%= session.getAttribute("cpage") %>" class="btn btn-primary">목록보기</a>
					<%} %>
				</td>
			</tr>
		</form>
	</table>
	<!-- Optional JavaScript -->
   <!-- jQuery first, then Popper.js, then Bootstrap JS -->
   <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
   <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
   <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
	
</body>
</html>