<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<% if(session.getAttribute("login_id")==null){%>
	<table align=center>
		<tr>
			<td colspan=2 align=center height=40><b>�α���</b>
			<td>
		</tr>
		<form method="post" action="/jwbook/SnsController?action=login">
		<tr>
			<td align=right>���̵�&nbsp;</td>
			<td><input type="text" name="id" placeholder="Email address"
				required></td>
		</tr>
		<tr>
			<td align=right>�н�����&nbsp;</td>
			<td><input type="password" name="password" required></td>
		</tr>
		<tr>
			<td colspan=2 align=center height=50>
			<input type="submit" value="�α����ϱ�"></td>
		</tr>
		</form>
		<tr>
			<td colspan=2 align=center><small> <br>
				<br>����ȸ���� �ƴϼ���?<br>
				<br> <a href="signup.jsp">ȸ������</a></small></td>
		</tr>
	</table>
<%}else{
	response.sendRedirect("/jwbook/SnsController");
} %>
</body>
</html>