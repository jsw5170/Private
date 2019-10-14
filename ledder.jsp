<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery.js"></script>
<script>
	function number () {
		var mem = $('#num').val()
		alert(mem);
		alert("dd");
		$.ajax({
			data: $('#ledder').serialize(),
			url: 'ledder',
			dataType: 'text',
			type: 'post',
			success: function (json) {
				window.location.replace("ledder.jsp");				
			}
		});
	}
</script>
</head>
<body>
<form method="post" id="ledder">
<input type="text" id="num" name="num">사용자 수
<input type="button" onclick="number()"> 
</form>

</body>
</html>