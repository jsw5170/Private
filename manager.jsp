<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<script src="http://code.jquery.com/jquery.js" type="text/javascript"></script>
<title>Insert title here</title>
<style>
	div .row {
	    	background-color: yellow;
	    	height: 100px;
	    	border-width: 1px;
	    	border-style: dashed;
	    	border-color: red;
    	}
</style>
</head>
<body>
<nav class="nav navbar-light" style="background-color: #e3f2fd;">
	<a href="main.jsp"  class="nav-link">Home</a>
	<a href="Notice.do?bType=2" class="nav-link">공지사항</a>
	<a href="list.do?bType=1" class="nav-link">자유게시판</a>
	<a href="referenceRoom.do?bType=3" class="nav-link">자료실</a>
	<a href="Map.jsp" class="nav-link">위치</a>
	<a href="manager.jsp" class="nav-link disabled" tabindex="-1" aria-disabled="true">관리자 메뉴</a>
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

<div class="row">
<div id="menu" class="col align-self-start" >
<ul class="nav flex-column">
 <li class="nav-item">
	  <form id="manager" action="manager.mo" method="post">
	  	 <input type="hidden" value="allMember" name="com">
	  	 <input type="submit" class="btn btn-default" value="전체회원 조회">
	  </form>
	  </li>
	  <li class="nav-item">
	  <form id="manager" action="manager.mo" method="post">
	  	 <input type="hidden" value="getMember" name="com">
	  	 <input class="form-control mr-sm-2" type="search" placeholder="개별 회원 조회" aria-label="Search" name="id">
	  	 <input type="submit" class="btn btn-default" value="개별 회원 조회">
	  </form>
	  </li>
	  <!-- <li class="nav-item">
	    <a class="nav-link" href="#">회원 검색?</a>
	  </li >-->
	  <li class="nav-item">
	  <form id="manager" action="manager.mo" method="post">
	  	 <input type="hidden" value="blockMember" name="com">
	  	 <input class="form-control mr-sm-2" type="search" placeholder="회원 로그인 정지" aria-label="Search" name="id">
	  	 <input type="submit" class="btn btn-default" value="회원 로그인 정지">
	  </form>
	  </li> 
	  <li class="nav-item">
	  <form id="manager" action="manager.mo" method="post">
	  	<input type="hidden" value="deleteMember" name="com">
	  	<input class="form-control mr-sm-2" type="search" placeholder="회원 강제 탈퇴" aria-label="Search" name="id">
	  	<input type="submit" class="btn btn-default" value="회원 강제 탈퇴">
	  </form>
	  </li>
	  <li class="nav-item">
	  <form id="manager" action="manager.mo" method="post">
	  	<input type="hidden" value="deleteBoard" name="com">
	  	<input class="form-control mr-sm-2" type="search" placeholder="게시글 삭제(게시글 번호)" aria-label="Search" name="bId">
	  	<input type="submit" class="btn btn-default" value="게시글 삭제(게시글 번호)">
	  </form>
	  </li>
	  <li class="nav-item">
	   <form id="manager" action="manager.mo" method="post">
	  	<select name='date'>
		    <option value="">기간</option>
		    <option value="week">1주</option>
		    <option value="month">1달</option>
		    <option value="year">1년</option>
		</select>
		<input type="hidden" value="maxBoard" name="com">
	  	<input type="submit" class="btn btn-default" value="(기간 정해) 게시글 가장 많이 올린 사람">
	  </form>
	  </li>
	  <li class="nav-item">
	  	 <form id="manager" action="manager.mo" method="post">
	  	<select name='date'>
		    <option value="">기간</option>
		    <option value="week">1주</option>
		    <option value="month">1달</option>
		    <option value="year">1년</option>
		</select>
		<input type="hidden" value="maxComment" name="com">
	  	<input type="submit" class="btn btn-default" value="(기간 정해) 댓글 가장 많이 단 사람">
	  </form>
	  </li>
</ul>
</div>
<div id="con" class="col align-self-center">
<table class="table"style="text-align: center; ">
<c:if test="${allMember != null}">
	<thead class="thead-light">
		<tr>
		      <th scope="col">Id</th>
		      <th scope="col">Name</th>
		      <th scope="col">EMail</th>
		      <th scope="col">RDate</th>
		      <th scope="col">Address</th>
		      <th scope="col">BlockMember</th>
		</tr>
	</thead>
	 <tbody>
	<c:forEach var="allMember" items="${allMember}">
		<tr>
			<th>${allMember.id }</th>
			<td>${allMember.name }</td>
			<td>${allMember.eMail }</td>
			<td>${allMember.rDate }</td>
			<td>${allMember.address }</td>
			<td>${allMember.blockMember }</td>
		</tr>
	</c:forEach>
	</tbody>
</c:if>
<c:if test="${getMember != null}">
	<thead class="thead-light">
		<tr>
		      <th scope="col">Id</th>
		      <th scope="col">Name</th>
		      <th scope="col">EMail</th>
		      <th scope="col">RDate</th>
		      <th scope="col">Address</th>
		      <th scope="col">BlockMember</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<th>${getMember.id }</th>
			<td>${getMember.name }</td>
			<td>${getMember.eMail }</td>
			<td>${getMember.rDate }</td>
			<td>${getMember.address }</td>
			<td>${getMember.blockMember }</td>
		</tr>
	</tbody>
</c:if>
<c:if test="${maxValue != null}">
	<thead class="thead-light">
		<tr>
		      <th scope="col">Name</th>
		      <th scope="col">Count</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>${maxValue}</td>
			<td>${maxCount}</td>
		</tr>
	</tbody>
</c:if>
</table>
</div>
<div id="bar" class="col align-self-end"></div>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

</body>
</html>