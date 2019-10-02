<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="captcha.CaptCha"%>
<%
try{
        out.clear();
        pageContext.pushBody();
        new CaptCha().getCaptCha(request, response);
} catch(Exception e){
        e.printStackTrace();
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>