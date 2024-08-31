<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<%
	if(session.getAttribute("login_id")==null)
		session.setAttribute("login_id", request.getParameter("id"));
	%>
	<table align=center>
		<tr>
			<td colspan=2 align=left height=40><b>�ۼ��� ����Ʈ</b></td>
			<form method="post" action="/jwbook/write.jsp">
			<td colspan=2 align=right height=40>
				<input type="submit" value="�۾���"></td>
			</form>
			<form method="post" action="/jwbook/SnsController?action=logout">
			<td colspan=2 align=right height=40><input type="submit"
				value="�α׾ƿ�"></td>
			</form>
		</tr>
		<c:forEach var="feeds" items="${feedlist}" varStatus="status">
			<tr>
				<td colspan=2 align=left height=40>${feeds.id}</td>
				<td colspan=2 align=right height=40>${feeds.ts}</td>
			</tr>
			<tr>
				<td><img src="${pageContext.request.contextPath}/${feeds.img}"></td>
			</tr>
			<tr>
				<td><b>${feeds.content}</b></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>