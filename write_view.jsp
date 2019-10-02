<%@page import="java.util.Enumeration" %>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@page import="com.oreilly.servlet.MultipartRequest" %>
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
		<form action="write<%=bType %>.do" method="post" enctype="multipart/form-data">
			<tr>
				<th class="text-center"> 이름 </th>
				<td><%= name%></td>
			</tr>
			<tr>
				<th class="text-center"> 제목 </th>
				<td><input type="text" name="bTitle" size="50"></td>
			</tr>
			<tr>
				<th class="text-center"> 내용 </th>
				<td><textarea name="bContent" rows="10" id="bContent"></textarea></td>
				<script>
				CKEDITOR.replace( 'bContent' );
				</script>
			</tr>
			<tr>
				<%if(bType.equals("3")) {%>
				<td colspan="2" class="text-center">
					파일 : <input type="file" name="filename">
				</td>
				<%}%>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="입력" class="btn btn-primary">&nbsp;&nbsp;
					<%if(bType.equals("1")) {%>
					<a href="list.do?bType=1" class="btn btn-primary">목록보기</a>
					<%} else if(bType.equals("2")) {%>
					<a href="Notice.do?bType=2" class="btn btn-primary">목록보기</a>
					<%} else if(bType.equals("3")) {%>
					<a href="referenceRoom.do?bType=3" class="btn btn-primary">목록보기</a>
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