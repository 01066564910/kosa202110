<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <form:form action="stuMypageModify" method="post"  modelAttribute="pwCommand" >
<input type="hidden" name="newPwCon"  value="1234"/>
<input type="hidden" name="newPw" value="1564"/>
	<table>
		<tr>
			<td>
				수정하기위해 현재 비밀번호를 입력하세요 : <input type="password" name="oldPw" id="oldPw">
				<form:errors path="oldPw" />
			</td>
		</tr>
		<tr>
			<td>
				 <input type="submit" value="수정하러 가기">
			</td>
		</tr>
	</table>

</form:form>
</body>
</html>