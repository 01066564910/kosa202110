<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
				$("#ida").css("color" ,"blue");
			}else{
				$("#ida").css("color" ,"red");
			}
		},error : function name() {
			alert("코드에러 코드에러");
			return;
		}
		
		
		
	});
};
function checkEmail() {
	$.ajax({
		type :"post",
		url :"studentUpEmailCk",
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
	$("#checkEmail").keyup(function () {
		$.ajax({
			type :"post",
			url :"studentEmailCk",
			dataType :"text",
			data :{"studentEmail" : $("#checkEmail").val()},
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
	});
});*/
function departmentNum1() {
	$.ajax({
		type :"post",
		url :"studentNum",
		dataType :"text",
		data :{"studentNum" : $("#departmentNum2").val()},
		beforeSend: function () {
			if($("#departmentNum2").val() == "${studentCommand.departmentNum}"){
				$("#studentNum").val("${studentCommand.studentNum}");
				return false;
			}
				
		},
		success : function (result) {
			$("#studentNum").val(result)
		},
		error : function(){
			alert("너래널")
		}
		
	});
}
</script>
</head>
<body>
<form:form action="studentUpdate" method=  "post" modelAttribute="studentCommand">
   <input type="hidden" name="studentPwCon" value="123"/>
   <input type="hidden" name="studentPw" value="1234"/>
<table border="1">
   <tr>
      <td>학과</td>
      <td>
         <select name="departmentNum" id="departmentNum2" oninput="departmentNum1();" >
            <c:forEach items="${list}" var="dto"  >
               <option value="${dto.departmentNum}"
               		<c:if test="${dto.departmentNum == fn:substring(studentCommand.studentNum ,2 , 2 + fn:length(studentCommand.departmentNum)) }" >selected</c:if> >
              		 ${dto.departmentName}</option>
            </c:forEach>
            	
         </select>
         <form:errors path="departmentNum"/>   
      </td>
   </tr>
   <tr>
      <td>학생 번호</td>
      <td>
       	  <input type="text" name="studentNum" id="studentNum" value="${studentCommand.studentNum}"  /> 
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
         <input type="email" name="studentEmail" id="studentEmail" value="${studentCommand.studentEmail}" oninput="checkEmail();"/>
         <span id="email"></span>
         <form:errors path="studentEmail"/>   
      </td>
   </tr>
   <tr>
      <td colspan="2" align="center">
         <input type="submit" value="학생 정보 수정" />
      </td>
   </tr>
</table>
</form:form>

</body>
</html>