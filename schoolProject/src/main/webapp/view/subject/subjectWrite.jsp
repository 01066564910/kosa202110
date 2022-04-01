<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="subjectWrite"  method="post" >
	<table>
		<tr><td>과목 번호</td><td><input type="text"  value="${subjectCommand}" name="subjectNum"> </td></tr>
		<tr><td>과목 이름</td><td><input type="text" name="subjectName"> </td> </tr>
		<tr><td>과목 제목</td><td><input type="text" name="subjectTitle"> </td> </tr>
		<tr><td>과목 내용</td><td><input type="text" name="subjectContent"> </td> </tr>
		<tr><td colspan="2"><input type="submit" value="과목 만들기"></td> </tr>
	</table>
</form>
</body>
</html>