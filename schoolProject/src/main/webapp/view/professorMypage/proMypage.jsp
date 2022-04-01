<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

번호:	${fn:substring(professorCommand.professorNum,4,11)}</a><br/>
이름:	${professorCommand.professorName } <br/>
연락처:	${professorCommand.professorPhone } <br/>
아이디 :	${professorCommand.professorId }<br/>
학과번호 : ${professorCommand.departmentNum } <Br/>
이메일 : ${professorCommand.professorEmail }<br/>
<a href="professorMyPagePw?num=${professorCommand.professorNum }">수정</a>|| <a href="professorPwChange">비밀번호 변경</a>


</body>
</html>