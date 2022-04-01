<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<table border="1">
   <tr><td>학번</td><td>이름</td><td>학과번호</td></tr>
   <c:forEach items="${list }" var="dto" >
   <tr>
      <td><a href="studentDetail?studentId=${dto.studentId}">${dto.studentNum}</a></td>
      <td>${dto.studentName}</td>
      <td>${dto.departmentNum}</td>
   </tr>
   </c:forEach>
</table>
<a href="studentWrite">학생 등록</a>
</body>
</html>