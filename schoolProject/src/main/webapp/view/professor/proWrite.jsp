<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.1.min.js"></script>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
span{color:red};
</style>
<script src="https://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
function checkId() {
	$.ajax({
		type :"post",
		url :"professorIdCk",
		dataType :"text",
		data :{"professorId" : $("#professorId").val()},
		success : function (result) {
			$("#ids").html(result)
			if(result == "사용 가능한 아이디입니다."){
				$("#ids").css("color" , "blue");
			}else{
				$("#ids").css("color" , "red");
			}
				
				
		}
		
	});
};
function checkEmail() {
	$.ajax({
		type :"post",
		url :"professorEmailCk",
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

<form:form action="proWrite" method="post" modelAttribute="professorCommand">
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
				이메일:<input type="email" name="professorEmail" id="professorEmail" oninput="checkEmail();"  value="${professorCommand.professorEmail}">
				<span id="email"></span>
				<form:errors path="professorEmail" />
			</td>
		</tr>
		<tr>
			<td>
				아이디:<input type="text" name="professorId" id="professorId" oninput="checkId();" value="${professorCommand.professorId}">
				<span id="ids"></span>
				<form:errors path="professorId" />
			</td>
		</tr>
		<tr>
			<td>
				비밀번호:<input type="password" name="professorPw" >
				<form:errors path="professorPw" />
			</td>
		</tr>
		<tr>
			<td>
				비밀번호 확인:<input type="password" name="professorPwCon">
				<form:errors path="professorPwCon" />
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
				<input type="submit" value="교수 등록">
			</td>
		</tr>

	</table>
</form:form>
</body>
</html>