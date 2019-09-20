<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<title>Insert title here</title>
</head>
<body>
	<table class="table table-striped" width="800" cellpadding="0" cellspacing="0" border="1">
		<thead>
			<tr>
				<th scope="col">번호</th>
				<th scope="col">이름</th>
				<th scope="col">제목</th>
				<th scope="col">날짜</th>
				<th scope="col">히트</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="dto">
		<tr>
			<td>${dto.bId} </td>
			<td>${dto.bName} </td>
			<td>
				<c:forEach begin="1" end="${dto.bIndent}">-</c:forEach>
				<a href="content_view.do?bId=${dto.bId}">${dto.bTitle}</a> </td>
			<td>${dto.bDate} </td>
			<td>${dto.bHit} </td>
		</tr>
		</c:forEach>
		</tbody>
		<tr>
			<td colspan = "5">
			<div style='display:inline;'>
			<div style='display:inline;float:left;'>
			<a class="btn btn-primary" href="write_view.do" role="button">글작성</a>
			</div>
			<div style='display:inline;'>
			<nav aria-label="Page navigation example">
			<!-- 처음  -->
			<ul class="pagination justify-content-center">
			<c:choose>
			<c:when test="${(page.curPage - 1) < 1}">
				<li class="page-item ">
					<a class="page-link" href="list.do?bType=3&page=1" tabindex="-1">&lt;&lt;</a>
				</li>
			</c:when>
			<c:otherwise>
			<li class="page-item ">
				<a class="page-link" href="list.do?bType=3&page=1" tabindex="-1">&lt;&lt;</a>
			</li>
			</c:otherwise>
			</c:choose>
			<!-- 이전 -->
			<c:choose>
			<c:when test="${(page.curPage - 1) < 1}">
				<li class="page-item ">
				<a class="page-link" href="list.do?bType=3&page=${page.curPage -1 }" tabindex="-1">&lt;</a>
				</li>
			</c:when>
			<c:otherwise>
			<li class="page-item ">
				<a class="page-link" href="list.do?bType=3&page=${page.curPage -1 }" tabindex="-1">&lt;</a>
			</li>
			</c:otherwise>
			</c:choose>
			
			<!-- 개별 페이지 -->
			<c:forEach var="fEach" begin="${page.startPage}" end="${page.endPage}" step="1">
				<c:choose>
				<c:when test="${page.curPage == fEach}">
					 <li class="page-item active disabled">
					 <a class="page-link" href="list.do?bType=3&page=${fEach}">${fEach}</a>&nbsp;
					  </li>
				</c:when>
				<c:otherwise>
				<li class="page-item"><a class="page-link" href="list.do?bType=3&page=${fEach}">${fEach}</a>&nbsp;</li>
				</c:otherwise>
				</c:choose>
			</c:forEach>
			
			<!-- 다음 -->
			<c:choose>
			<c:when test="${(page.curPage + 1) < page.totalPage}">
			<li class="page-item">
      			<a class="page-link" href="list.do?bType=3&page=${page.curPage + 1 }">&gt;</a>
    		</li>
			</c:when>
			<c:otherwise>
			<li class="page-item">
      			<a class="page-link" href="list.do?bType=3&page=${page.curPage + 1 }">&gt;</a>
    		</li>
			</c:otherwise>
			</c:choose>
			<!-- 끝 -->
			<c:choose>
			<c:when test="${page.curPage == page.totalPage}">
				<li class="page-item">
      			<a class="page-link" href="list.do?bType=3&page=${page.totalPage }">&gt;&gt;</a>
    		</li>	
			</c:when>
			<c:otherwise>
			<li class="page-item">
      			<a class="page-link" href="list.do?bType=3&page=${page.totalPage }">&gt;&gt;</a>
    		</li>
			</c:otherwise>
			</c:choose>
			  </ul>
			</nav>
			</div>
			</div>
			</td>
		</tr>		
	</table>
	<a href="main.jsp">메인</a>
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