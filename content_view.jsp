<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html>
<% String name = (String)session.getAttribute("name");
String bType = (String)session.getAttribute("bType");
String id = (String)session.getAttribute("id");
pageContext.setAttribute("br", "<br/>");
pageContext.setAttribute("cn", "\n");
%>
<html>
<head>
<meta charset="UTF-8">
  <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

<script src="http://code.jquery.com/jquery.js"></script>
<title>Insert title here</title>
<script>
function updateComment() {
	if($('#check').val() != '확인'){
		alert("catpcha");
		$('#check').focus();
		return;
	}
}
</script>
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
<iframe id="ifrm_filedown" style="position:absolute; z-index:1; visibility:hidden;"></iframe>
	<table id="content" width="800" cellpadding="0" cellspacing="0" border="1" >
		<tr>
			<th class="text-center"> 번호 </th>
			<td colspan="2" class="mid"> ${content_view.bId} </td>
		</tr>
		<tr>
			<th class="text-center"> 히트 </th>
			<td colspan="2" class="mid"> ${content_view.bHit} </td>
		</tr>
		<tr>
			<th class="text-center"> 이름 </th>
			<td colspan="2" class="mid"> ${content_view.bName} </td>
		</tr>
		<tr>
			<th class="text-center"> 제목 </th>
			<td colspan="2" class="mid"> ${content_view.bTitle} </td>
		</tr>
		<tr>
			<%if(bType.equals("3")) {%>
			<th class="text-center"> 첨부파일 </th>
			<td colspan="2" class="mid"> 
				<form action="download.do" method="post">
				<input type="hidden" name="bId" value="${content_view.bId}">
				<p><input type="submit" value="${content_view.filePath}"></p>
				</form>
			</td>
			<%}%>
		</tr>
		<tr>
			<th class="text-center"> 내용 </th>
			<td colspan="2" class="mid"> ${fn:replace(content_view.bContent, cn, br)} </td>
		</tr>
		<tr>
		<td colspan="3" class="text-center">
			<form action="like.do" method="post">
				<input type="hidden" name="bId" value="${content_view.bId}">
				<input type="submit" value="추천" class="btn btn-primary">
				<span class="btn btn-primary">${content_view.bLike}</span>
			</form>
		</td>
		</tr>
		<tr>
			<c:if test="${cList != null }">
			<c:forEach var="comment" items="${cList}">
				<tr>
					<!-- 아이디, 작성 날짜 -->
					<th width="150" class="text-center">
						<div>
							${comment.cId}<br>
							${comment.cDate}<br>
						</div>
					</th>
					<!-- 댓글 내용 -->
					<td>
						<div class="text_wrapper">
							 ${fn:replace(comment.cContent, cn, br)}
						</div>
					</td>
					<td width="100">
						<div id="btn">
							<c:if test="${comment.cId == name}">
								<form action="deleteComment.do" method="post">
									<input type="hidden" name="bId" value="${content_view.bId}">
									<input type="hidden" name="cNum" value="${comment.cNum}">
									<input type="submit" value="삭제" class="btn btn-primary">
								</form>
								<%-- <div class="card-header" id="headingOne">
								 <form id="manager" action="" method="post">
								<input type="submit" onclick="updateComment(${comment.cId})" value="수정">
							  	</form>
						        </div> --%>
							</c:if>
						</div>
					</td>
				</tr>
				<c:if test="${comment.cId == name}">
				<form action="updateComment.do" method="post">
					<div id="modiOkbutton${comment.cId}" style="visibility:hidden;">
						<input type="hidden" name="bId" value="${content_view.bId}">
						<input type="hidden" name="cNum" value="${comment.cNum}">
						<input type="hidden" name="cId" value="<%=name%>">
						<input type="hidden" name="bType" value="<%=bType%>">
						<th width="100" class="text-center">
							<div>
								${comment.cId}
							</div>	
						</th>
						<td>
							<div>
								<textarea name="cContent" rows="2" cols="70">${fn:replace(comment.cContent, cn, br)}</textarea>
							</div>	
						</td>
						<td width="100">
							<div id="btn" class="text-center">
								<p><input type="submit" value="[댓글수정]" class="btn btn-primary"></p>
							</div>	
						</td>
					</div>
				</form>
				</c:if>
			</c:forEach>
			</c:if>
			<tr>
			<form id="writeComment" action="writeComment.do" method="post">
				<input type="hidden" name="bId" value="${content_view.bId}">
				<input type="hidden" name="cName" value="<%=name%>">
				<input type="hidden" name="cId" value="${comment.cId}">
				<input type="hidden" name="bType" value="<%=bType%>">
					<th width="50">
						<div class="text-center">
							<%=name%>
						</div>	
					</th>
					<td>
						<div>
							<textarea name="cContent" rows="4" cols="70"></textarea>
						</div>	
					</td>
					<td width="30">
						<div id="btn">
							<p><input type="submit" value="[댓글등록]" class="btn btn-primary"></p>
						</div>	
					</td>
			</form>
			</tr>
		</tr>
		<tr>
			<td colspan="3">
				<c:if test="${name == content_view.bName}">
				<a href="modify_view.do?bType=<%=bType%>&bId=${content_view.bId}" class="btn btn-primary">수정</a>&nbsp;&nbsp;
				<a href="delete.do?bId=${content_view.bId}" class="btn btn-primary">삭제</a>&nbsp;&nbsp;
				</c:if>
				<%if(bType.equals("1")) {%>
					<a href="list.do?bType=1" class="btn btn-primary">목록보기</a>&nbsp;&nbsp;
					<%} else if(bType.equals("2")) {%>
					<a href="Notice.do?bType=2" class="btn btn-primary">목록보기</a>&nbsp;&nbsp;
					<%} else if(bType.equals("3")) {%>
					<a href="referenceRoom.do?bType=3" class="btn btn-primary">목록보기</a>&nbsp;&nbsp;
					<%} %>
				<a href="reply_view.do?bType=<%=bType%>&bId=${content_view.bId}" class="btn btn-primary">답변</a>&nbsp;&nbsp;
			</td>
		</tr>
	</table>
 <!-- Optional JavaScript -->
   <!-- jQuery first, then Popper.js, then Bootstrap JS -->
   <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
   <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
   <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

</body>
</html>