<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>Action Tag : forward, param</h3>
<jsp:forward page = "actionResult.jsp">
<jsp:param name = "id" value = "admin" />
<jsp:param name = "name" value = "관리자" />
</jsp:forward>
</body>
</html>