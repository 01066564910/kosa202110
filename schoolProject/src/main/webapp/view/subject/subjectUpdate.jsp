<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="subjectUpdate"  method="post" >
	<table>
		<tr><td>과목 번호</td><td><input type="text"  value="${SubjectCommand.subjectNum}" name="subjectNum" readonly="readonly"> </td></tr>
		<tr><td>과목 이름</td><td><input type="text" name="subjectName" value="${SubjectCommand.subjectName}"> </td> </tr>
		<tr><td>과목 제목</td><td><input type="text" name="subjectTitle" value="${SubjectCommand.subjectTitle}"> </td> </tr>
		<tr><td>과목 내용</td><td><input type="text" name="subjectContent" value="${SubjectCommand.subjectContent}" > </td> </tr>
		<tr><td colspan="2"><input type="submit" value="과목 만들기"></td> </tr>
	</table>
</form>
</body>
</html>