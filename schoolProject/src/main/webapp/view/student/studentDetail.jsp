<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>          
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1">
   <tr>
      <td>학생 번호</td>
      <td>${studentCommand.studentNum}</td>
   </tr>
   <tr>
      <td>학생 이름</td>
      <td>${studentCommand.studentName}</td>
   </tr>
   <tr>
      <td>학생 아이디</td>
      <td>${studentCommand.studentId}</td>
   </tr>
   <tr>
      <td>학생 연락처</td>
      <td>${studentCommand.studentPhone}</td>
   </tr>
   <tr>
      <td>학생 메일</td>
      <td>${studentCommand.studentEmail}</td>
   </tr>
   <tr>
      <td>학과 번호</td>
      <td>${studentCommand.departmentNum}</td>
   </tr>
   <tr>
      <td colspan="2" align="center">
         <a href="studentList">목록</a>
         
            | <a href="studentUpdate?studentId=${studentCommand.studentId}&studentNum=${studentCommand.studentNum}">수정</a>
            | <a href="studentDel?num=${studentCommand.studentNum}">삭제</a>
         
      </td>
   </tr>
</table>
</body>
</html>