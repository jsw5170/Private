<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% String name = (String)session.getAttribute("name");
String bType = (String)session.getAttribute("bType");
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
	<table id="content" width="800" cellpadding="0" cellspacing="0" border="1" >
		<form action="modify.do" method="post">
			<input type="hidden" name="bId" value="${content_view.bId}">
			<tr>
				<th class="text-center"> 번호 </th>
				<td> ${content_view.bId} </td>
			</tr>
			<tr>
				<th class="text-center"> 히트 </th>
				<td> ${content_view.bHit} </td>
			</tr>
			<tr>
				<th class="text-center"> 이름 </th>
				<td><%= name%></td>
			</tr>
			<tr>
				<th class="text-center"> 제목 </th>
				<td> <input type="text" name="bTitle" value="${content_view.bTitle}"> </td>
			</tr>
			<tr>
				<th class="text-center"> 내용 </th>
				<td> 
					<textarea rows="10" name="bContent" id="bContent">${content_view.bContent}</textarea>
				</td>
				<script>
				CKEDITOR.replace( 'bContent' );
				</script>
			</tr>
			<tr>
				<td colspan="2"> <input type="submit" value="수정" class="btn btn-primary">&nbsp;&nbsp;
					<a href="content_view.do?bId=${content_view.bId}" class="btn btn-primary">취소</a>&nbsp;&nbsp;
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