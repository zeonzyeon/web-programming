<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Login Page</h2><hr>
<h3>로그인</h3>
아이디 <input type="text" placeholder="Email address" name="id"><br>
패스워드 <input type="text" name="pw"><br>
<input type="submit" value="로그인하기">
<h3>User ID : <%= request.getParameter("id") %></h3>
</body>
</html>