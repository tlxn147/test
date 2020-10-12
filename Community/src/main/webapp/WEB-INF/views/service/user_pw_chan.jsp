<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
<form action="user_pw_chan_update" id="pw_chan_submit" method="post">
	<input type="password" id="current_pw" name="current_pw" placeholder="현재 비밀번호"><br>
	<input type="password" id="new_pw" name="new_pw" placeholder="새 비밀번호"><br>
	<input type="password" id="check_new_pw" placeholder="새 비밀번호 확인"><br>
	<input type="button" id="submit_bt" value="확인"><br>
	<input type="button" value="취소">
</form>
<script type="text/javascript">
$(function() {
	$('#submit_bt').click(function() { 
		var current_pw = document.getElementById('current_pw').value;
		var new_pw = document.getElementById('new_pw').value;
		var check_new_pw = document.getElementById('check_new_pw').value;
		var pwRegExp = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/; // 비밀번호 유효성 검사
		if(!pwRegExp.test(new_pw)) {
			alert("8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.");
			window.location.reload();
		} else if(new_pw != check_new_pw) {
			alert("새 비밀번호와 비밀번호 확인이 일치하지 않습니다.");
			window.location.reload();
		} else {
			$('#pw_chan_submit').submit();
		}
	});
});
</script>
</body>
</html>	