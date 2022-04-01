<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
         


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
function update2() {
	
	if($("#oldPw").val() == ""){
		alert("비밀번호를 입력해주세요");
	}else{
		location.href="professorMyPageUpdate?num=${professorCommand.professorNum}&pw="+$("#oldPw").val();
		
	}
}

</script>
</head>
<body>

<form >
	<table>
		<tr>
			<td>
				수정하기위해 현재 비밀번호를 입력하세요 : <input type="password" name="oldPw" id="oldPw">
			</td>
		</tr>
		<tr>
			<td>
				 <input type="button" value="수정하러 가기" onclick="update2();">
				 
			</td>
		</tr>
	</table>

</form>

</body>
</html>