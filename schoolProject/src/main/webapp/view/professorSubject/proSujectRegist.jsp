<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="proSujectRegist" method="post">
	<table border="1">
		<tr>
			<td>
				교수번호 :
			</td>
			<td>
				<input type="text" name="professorNum" value="${ professorSubjectCommand.professorNum}" readonly="readonly">
			</td>
		</tr>
		<tr>
			<td>
				과목 :
			</td>
			<td><select name="subjectNum">
			<c:forEach items="${list }" var="dto">
				<option value="${dto.subjectNum }">${dto.subjectName }</option>
			</c:forEach>
			</select>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="교수 과목 등록">
			</td>
		</tr>
	</table>
</form>
</body>
</html>