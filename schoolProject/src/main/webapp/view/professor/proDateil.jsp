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
<script type="text/javascript">
function del1() {
	location.href="professDelete?num=${professorCommand.professorNum}";
}


</script>
</head>
<body>

	
		
번호:	${fn:substring(professorCommand.professorNum,4,11)}</a><br/>
이름:	${professorCommand.professorName } <br/>
연락처:	${professorCommand.professorPhone } <br/>
아이디 :	${professorCommand.professorId }<br/>
학과번호 : ${professorCommand.departmentNum } <Br/>
이메일 : ${professorCommand.professorEmail }<br/>
<a href="professUpdate?num=${professorCommand.professorNum }">수정</a>|| <input type="button" value="삭제" onclick="del1();">


</body>
</html>