<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#delete").click(function() {
		
			$.ajax({
				type:"post",
				url:"memberDelete",
				data:{"memberNum" :"${memberCommand.memberNum}",
				dateType :"text",
				
				success : function (aaa) {
					if(aaa.trim() == '1'){
						alert("회원이 정상적으로 삭제 되었습니다.");
						location.href="../memberList";
					}else{
						alert("회원이 삭제되지 않았습니다.");
						return false;
					}

				},error : function () {
					alert("코드에러 코드레어 돔황챠!!!");
					return;
				}
			
			});
		});
	});
</script>	
<style type="text/css">
p{color:gray; 
	 font-size:13px;
	 font-family:돋움;}
</style>

</head>
<body>
<p>
회원 3등급 이상만 볼 수 있습니다.
	
   <br/> 회원 아이디 : ${memberCommand.memberId}
   <br /> 회원 이름 : ${memberCommand.memberName}
   <br /> 회원 주소 : ${memberCommand.memberAddr}
   <br /> 등록일 :
    <fmt:formatDate value="${memberCommand.memberRegist }"  pattern="yyyy-MM-dd"/>
    
   <br /> 성별 :
   <c:if test="${memberCommand.gender == 'M'}">남자</c:if>
   <c:if test="${memberCommand.gender == 'F'}">여자</c:if>

   <br> 연락처 : ${memberCommand.memberPhone}
   <br /> 생년월일 :
    <fmt:formatDate value="${memberCommand.memberBirth }"  pattern="yyyy-MM-dd"/>
   <br /> 회원 이메일 : ${memberCommand.memberEmail}
   <br /></p>
	
   <form action="../memberModify" method="get">
      <input type="hidden" name="memberNum" value="${memberCommand.memberNum}"><br /> 
      <input type="submit" value="수정해봐요" /> 
      <input type="button" value="jquery삭제" id="delete"> 

   </form>
</body>
</html>