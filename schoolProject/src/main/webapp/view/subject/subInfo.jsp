<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	과목 번호 : ${SubjectCommand.subjectNum } <br/>
	과목 제목 : ${SubjectCommand.subjectName } <br/>
	과목 이름 : ${SubjectCommand.subjectTitle } <br/>
	과목 내용 : ${SubjectCommand.subjectContent } <br/>
	<a href="subjectUpdate?num=${SubjectCommand.subjectNum }">수정</a>|| 
	<a href="subjectDelete?num=${SubjectCommand.subjectNum }">삭제</a>
</body>
</html>