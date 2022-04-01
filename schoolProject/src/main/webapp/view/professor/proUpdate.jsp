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
<script src="https://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
function checkEmail() {
	$.ajax({
		type :"post",
		url :"professorUpdateEmailCk",
		dataType :"text",
		data :{"professorEmailCk" : $("#professorEmail").val()},
		success : function (result) {
			$("#email").html(result)
			if(result == "사용 가능한 이메일입니다."){
				$("#email").css("color" ,"blue");
			}else{
				$("#email").css("color" ,"red");
			}
		},error : function name() {
			alert("코드에러 코드에러");
			return;
		}
			
		
		
	});
};
</script>
</head>
<body>
<form:form action="professUpdate" method="post" modelAttribute="professorCommand">
	<input type="hidden" name="professorPw" value="123">
	<input type="hidden" name="professorPwCon" value="1234">
	<table>
		<tr>
			<td>
				번호:<input type="text" name="professorNum" value="${professorCommand.professorNum}" readonly="readonly">
				
			</td>
		</tr>
		<tr>
			<td>
				이름:<input type="text" name="professorName" value="${professorCommand.professorName}">
				<form:errors path="professorName" />
			</td>
		</tr>
		<tr>
			<td>
				연락처:<input type="tel" name="professorPhone" value="${professorCommand.professorPhone}">
				<form:errors path="professorPhone" />
			</td>
		</tr>
		<tr>
			<td>
				이메일:<input type="email" name="professorEmail" id="professorEmail" oninput="checkEmail();" value="${professorCommand.professorEmail}">
				<span id="email"></span>
				<form:errors path="professorEmail" />
			</td>
		</tr>
		<tr>
			<td>
				아이디:<input type="text" name="professorId" value="${professorCommand.professorId}" readonly="readonly">
				<form:errors path="professorId" />
			</td>
		</tr>
		<tr>
			<td>
				학과 번호:${list.size() }
				<select name="departmentNum">
					<c:forEach items="${list }" var="dto">
						<option value="${dto.departmentNum }">${dto.departmentName }</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td>
				<input type="submit" value="교수 정보 바꾸기">
			</td>
		</tr>
	
		
	</table>
</form:form>
</body>
</html>