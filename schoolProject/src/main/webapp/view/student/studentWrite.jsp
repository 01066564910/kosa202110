<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
function checkId() {
	$.ajax({
		type :"post",
		url :"studentIdCk",
		dataType :"text",
		data :{"studentId" : $("#studentId").val()},
		success : function (result) {
			$("#ida").html(result)
			if(result == "사용 가능한 아이디입니다."){
				
			}
				
		}
		
	});
};
function checkEmail() {
	$.ajax({
		type :"post",
		url :"studentEmailCk",
		dataType :"text",
		data :{"studentEmail" : $("#studentEmail").val()},
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
/*
$(function () {
	$("departmentNum2").click(function () {
		$.ajax({
			type :"post",
			url :"studentNum",
			dataType :"text",
			data :{"num" : ("#departmentNum2").val()}
			success : function (result) {
				$("#studentNum").val(result)
			}
			 
		});
	});
});*/
function departmentNum1() {
	$.ajax({
		type :"post",
		url :"studentNum",
		dataType :"text",
		data :{"studentNum" : $("#departmentNum2").val()},
		success : function (result) {
			$("#studentNum").val(result)
		}
		
	});
};
</script>
</head>
<body>
<form:form action="studentWrite" method="post" modelAttribute="studentCommand">

<table border="1">
   <tr>
      <td>학과</td>
      <td>
         <select name="departmentNum" id="departmentNum2" oninput="departmentNum1();" >
            <c:forEach items="${list}" var="studentCommand"  >
               <option value="${studentCommand.departmentNum}" >${studentCommand.departmentName}</option>      
            </c:forEach>
         </select>
         <form:errors path="departmentNum"/>   
      </td>
   </tr>
   <tr>
      <td>학생 번호</td>
      <td>
         <input type="text" name="studentNum" id="studentNum"  />   
      </td>
   </tr>
   <tr>
      <td>학생 아이디</td>
      <td>
         <input type="text" name="studentId" id="studentId" value="${studentCommand.studentId}" oninput="checkId();" />
         <span id="ida"></span>
         <form:errors path="studentId"/>
      </td>
   </tr>
   <tr>
      <td>학생 비밀번호</td>
      <td>
         <input type="password" name="studentPw" value="${studentCommand.studentPw}"/>
         <form:errors path="studentPw"/>   
      </td>
   </tr>
   <tr>
      <td>학생 비밀번호 확인</td>
      <td>
         <input type="password" name="studentPwCon" />
         <form:errors path="studentPwCon"/>   
      </td>
   </tr>
   <tr>
      <td>학생 이름</td>
      <td>
         <input type="text" name="studentName" value="${studentCommand.studentName}"/>
         <form:errors path="studentName"/>   
      </td>
   </tr>
   <tr>
      <td>학생 연락처</td>
      <td>
         <input type="tel" name="studentPhone" value="${studentCommand.studentPhone}"/>
         <form:errors path="studentPhone"/>   
      </td>
   </tr>
   <tr>
      <td>학생 이메일</td>
      <td>
         <input type="email" name="studentEmail" id="studentEmail" value="${studentCommand.studentEmail}" oninput="checkEmail()"/>
         <span id="email"></span>
         <form:errors path="studentEmail"/>   
      </td>
   </tr>
   <tr>
      <td colspan="2" align="center">
         <input type="submit" value="학생 등록" />
      </td>
   </tr>
</table>
</form:form>

</body>
</html>