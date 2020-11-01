<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Insert title here</title>
<link href="<c:url value="/resources/css/profile_style.css" />" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
<form action="">
	<table>
		<tr>
			<td colspan="2"><img alt="User Pic" src="https://x1.xingassets.com/assets/frontend_minified/img/users/nobody_m.original.jpg"
							 id="profile-image" class="img-circle img-responsive">
							<input id="profile-image-upload"
							 class="hidden img-circle img-responsive" type="file">
			</td>
		</tr>
		<tr>
			<th>회원번호</th>
			<td>${login.customerNo}</td>
		</tr>
		<tr>
			<th>아이디</th>
			<td>${login.customerId}</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>${login.customerName}</td>
		</tr>
		<tr>
			<th>닉네임</th>
			<td>${login.customerNickname}</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${login.customerEmail}</td>
		</tr>
		<tr>
			<th>관심사</th>
			<td>${login.customerInterest}</td>
		</tr>
		<tr>
			<th>인사말</th>
			<td><textarea style="col:8; row:10; resize:none;" placeholder="인사말을 입력하세요."></textarea></td>
		</tr>
	</table>
	<input type="submit" value="수정하기">
</form>
	<a id="user_details" href="/kmweb/main/user-pw-chan"> 비밀번호 변경 </a><br>
</body>
<script>
$(function() {
	$('#profile-image').on('click', function() {
		var url="estimationPro.do";
		$.ajax({
			type:"post"
			,url:url	
			,dataType:"text"})
			.done(function(args){
				$('#profile-image-upload').click();
			})
		    .fail(function(e) {
		    	alert(e.responseText);
		    });
	});
});
</script>
</html>