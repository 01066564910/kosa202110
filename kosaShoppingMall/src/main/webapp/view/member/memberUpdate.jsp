<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
수정 페이지 입니다.
	<form:form action="memberModify" method="post" modelAttribute="memberCommand">
	<input type="hidden" name="memberPw" value="123">
	<input type="hidden" name="memberPwCon" value="123">
회원 번호 : <form:input path="memberNum" readonly="readonly" />:번호 자동부여
	<!--  path는 name(필드에 있는 멤버필드명 이다) -->
		<form:errors path="memberNum" />
		<br />
회원 이름 : <form:input path="memberName" />
		<form:errors path="memberName" />
		<br />
회원 생년월일 : <input type="date" name="memberBirth"
			value="<fmt:formatDate value='${memberCommand.memberBirth}' pattern='yyyy-MM-dd'/>">
		<form:errors path="memberBirth" />
		<br />
회원 성별 : <form:radiobutton path="gender" value="F" />여자
		<form:radiobutton path="gender" value="M" />남자 <br />
회원 가입일 : <input type="date" name="memberRegist"
			value="<fmt:formatDate value='${memberCommand.memberRegist}' pattern='yyyy-MM-dd'/>">
		<form:errors path="memberRegist" />
		<br />
회원 아이디 :  <form:input path="memberId" />
		<form:errors path="memberId" />
		<br />
		
회원 연락처 :  <input type="tel" name="memberPhone"
			placeholder="031-1235-1234"
			value="${memberCommand.memberPhone}"/>
		<form:errors path="memberPhone" />
		<br />
회원 주소 : <form:input path="memberAddr" />
		<form:errors path="memberAddr" />
		<br />
회원 이메일 : <input type="email" name="memberEmail"
			value="${memberCommand.memberEmail}" />
		<form:errors path="memberEmail" />
		<br />
		<input type="submit" value="회원 등록" />
	</form:form>

</form>
</body>
</html>