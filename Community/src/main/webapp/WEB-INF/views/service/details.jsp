<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<table>
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
</table>
</body>
</html>