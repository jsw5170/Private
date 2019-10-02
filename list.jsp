<%@page import="com.study.project.dto.BDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
    <% 
	String id = (String)session.getAttribute("id");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<title>Insert title here</title>
</head>
<body>
<nav class="nav navbar-light" style="background-color: #e3f2fd;">
	<a href="main.jsp"  class="nav-link">Home</a>
	<a href="Notice.do?bType=2" class="nav-link">공지사항</a>
	<a href="list.do?bType=1" class="nav-link disabled" tabindex="-1" aria-disabled="true">자유게시판</a>
	<a href="referenceRoom.do?bType=3" class="nav-link">자료실</a>
	<a href="Map.jsp" class="nav-link">위치</a>
	<%if(id.equals("manager")) {%>
	<a href="manager.jsp" class="nav-link">관리자 메뉴</a>
	<%}%>
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
	<table class="table table-striped" width="800" cellpadding="0" cellspacing="0" border="1">
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
		</tbody>
		<tr>
			<td colspan = "6">
			<div style='display:inline;'>
			<div style='display:inline;float:left;'>
			<a class="btn btn-primary" href="write_view.do?bType=1" role="button">글작성</a>&nbsp;&nbsp;
			</div>
			<div class='aside_menu'>
			  <form action="list.do" method="post">
			    <aside style='float: right;'>
			      <select name='col'> <!-- 검색 컬럼 -->
					    <option value="">검색</option>
					    <option value="bTitle">제목</option>
					    <option value="bContent">내용</option>
					    <option value="bName">작성자</option>
			      </select>
			      <input type='text' name='search' size="50" value='' placeholder="특수문자는 사용할수 없습니다.">
			      <input name="bType" value="1"  type="hidden">
			      <input type='submit' value="검색">    
			     </aside> 
			  </form>
			  <div class='menu_line' style='clear: both;'></div>
			</div>			
			<hr>
			<div style='display:inline;'>
			<nav aria-label="Page navigation example">
			<!-- 처음  -->
			<ul class="pagination justify-content-center">
			<c:choose>
			<c:when test="${(page.curPage - 1) < 1}">
				<li class="page-item ">
					<a class="page-link" href="list.do?bType=1&page=1" tabindex="-1">&lt;&lt;</a>
				</li>
			</c:when>
			<c:otherwise>
			<li class="page-item ">
				<a class="page-link" href="list.do?bType=1&page=1" tabindex="-1">&lt;&lt;</a>
			</li>
			</c:otherwise>
			</c:choose>
			<!-- 이전 -->
			<c:choose>
			<c:when test="${(page.curPage - 1) < 1}">
				<li class="page-item ">
				<a class="page-link" href="list.do?bType=1&page=${page.curPage -1 }" tabindex="-1">&lt;</a>
				</li>
			</c:when>
			<c:otherwise>
			<li class="page-item ">
				<a class="page-link" href="list.do?bType=1&page=${page.curPage -1 }" tabindex="-1">&lt;</a>
			</li>
			</c:otherwise>
			</c:choose>
			
			<!-- 개별 페이지 -->
			<c:forEach var="fEach" begin="${page.startPage}" end="${page.endPage}" step="1">
				<c:choose>
				<c:when test="${page.curPage == fEach}">
					 <li class="page-item active disabled">
					 <a class="page-link" href="list.do?bType=1&page=${fEach}">${fEach}</a>&nbsp;
					  </li>
				</c:when>
				<c:otherwise>
				<li class="page-item"><a class="page-link" href="list.do?bType=1&page=${fEach}">${fEach}</a>&nbsp;</li>
				</c:otherwise>
				</c:choose>
			</c:forEach>
			
			<!-- 다음 -->
			<c:choose>
			<c:when test="${(page.curPage + 1) < page.totalPage}">
			<li class="page-item">
      			<a class="page-link" href="list.do?bType=1&page=${page.curPage + 1 }">&gt;</a>
    		</li>
			</c:when>
			<c:otherwise>
			<li class="page-item">
      			<a class="page-link" href="list.do?bType=1&page=${page.curPage + 1 }">&gt;</a>
    		</li>
			</c:otherwise>
			</c:choose>
			<!-- 끝 -->
			<c:choose>
			<c:when test="${page.curPage == page.totalPage}">
				<li class="page-item">
      			<a class="page-link" href="list.do?bType=1&page=${page.totalPage }">&gt;&gt;</a>
    		</li>	
			</c:when>
			<c:otherwise>
			<li class="page-item">
      			<a class="page-link" href="list.do?bType=1&page=${page.totalPage }">&gt;&gt;</a>
    		</li>
			</c:otherwise>
			</c:choose>
			  </ul>
			</nav>
			</div>
			</div>
			</td>
		</tr>		
		</tbody>
	</table>

	totalCount: ${page.totalCount}<br>
	listCount: ${page.listCount}<br>
	totalPage: ${page.totalPage}<br>
	curPage: ${page.curPage}<br>
	pageCount: ${page.pageCount}<br>
	startPage: ${page.startPage}<br>
	endPage: ${page.endPage}<br>
	
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
 
</body>
</html>