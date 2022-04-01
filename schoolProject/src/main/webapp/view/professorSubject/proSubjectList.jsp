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
<script src="https://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
function PSdelete(professorNum,subjectNum) {
	$.ajax({
		type :"post",
		url : "pSDelete",
		dataType : "text",
		data : {"professorNum" : professorNum , "subjectNum" : subjectNum},
		success : function (result) {
			if(result =="1"){
				alert("삭제되었습니다");
				window.location.reload();
			}else if(result =="4"){
				alert("수강 취소해주세요 ");
			}else{
				alert("삭제가 안되었습니다.");
			}
		},error  : function () {
			alert("틀렸어");
		}
		
		
	});
}

</script>
</head>
<body>
	<table border="1">
		<tr><td>교수 번호</td><td>과목 번호</td><td>삭제</td><td>폐강</td> </tr>
		<c:forEach items="${list }" var="dto">
		<tr>
			<td>
				${dto.professorNum }
			</td>
			<td>
				${dto.subjectNum }
			</td>
			<c:if test="${dto.state != 1 }">
				<td> 
					<button onclick="PSdelete('${dto.professorNum}' ,'${dto.subjectNum }');">삭제하기</button>
				</td>
			</c:if>
			<c:if test="${dto.state == 1 }">
				<td>
					<a href="statusModifyToNull?professorNum=${dto.professorNum }&subjectNum=${dto.subjectNum }">폐강취소</a>
				</td>
			</c:if> 
			<c:if test="${dto.state != 1}"> 
				<td> 
					<a href="statusModifyTo1?professorNum=${dto.professorNum }&subjectNum=${dto.subjectNum }">폐강하기</a>
				</td>
			</c:if>
		</tr>
		</c:forEach>
	</table>

<a href="proSujectRegist">교수 과목 등록하기</a>
</body>
</html>