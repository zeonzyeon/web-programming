<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<table align=center>
		<tr>
			<td colspan=2 align=center height=40><b> ���ۼ�</b></td>
		</tr>
		<form method="post" action="/jwbook/SnsController?action=write" enctype="multipart/form-data">	
		<tr>
			<td align=center>�ۼ���</td>
			<td>
				<textarea name="content" cols="40" rows="6"></textarea><br>
    		</td>
		</tr>
		<tr>
			<td algin=left>�̹���</td>
			<td><input type="file" name="img"></td>
		</tr>
		<tr>
		<td colspan=2 align=center height=40>
		<input type="submit" value="���ε��ϱ�"></td>
		<tr>
		</form>
	</table>
</body>
</html>