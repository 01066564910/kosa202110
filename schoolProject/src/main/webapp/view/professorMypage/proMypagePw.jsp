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
<form:form action="professorPwChange" method="post" modelAttribute="pwCommand" >
	<table>
		<tr>
			<td>
				기존 비밀번호 : <input type="password" name="oldPw" />
				<form:errors path="oldPw" />
			</td>
		</tr>
		<tr>
			<td>
				변경하실 비밀번호 : <input type="password" name="newPw" />
				<form:errors path="newPw" />
			</td>
		</tr>
		<tr>
			<td>
				변경하실 비밀번호 확인:<input type="password" name="newPwCon" /> 
				<form:errors path="newPwCon" />
			</td>
		</tr>
		<tr>
			<td>
				<input type="submit" value="변경하기">
			</td>
		</tr>
	</table>
</form:form>
</body>
</html>