<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<%
		session.invalidate();
	%>
	<table align=center>
		<tr>
			<td>
			<b> �α׾ƿ��� �Ϸ��Ͽ����ϴ�.</b>
			</td>
		</tr>
		<form method="post" action="/jwbook/SnsController?action=login">
		<tr>
			<td colspan=2 align=center height=50><input type="submit"
				value="�α����ϱ�"></td>
		</tr>
		</form>
	</table>
</body>
</html>