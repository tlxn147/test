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
	<input type="password" id="check_new_pw_" placeholder="새 비밀번호 확인"><br>
	<input type="submit" id="submit_bt" value="확인"><br>
	<input type="button" value="취소">
</form>
<script type="text/javascript">
$(function() {
	$('#submit_bt').click(function() {
		console.log("${pw_chan}");
		if(${pw_chan} == true) {
			alert("비밀번호가 변경되었습니다.");
		}
		if(${pw_chan} == false) {
			alert("비밀번호를 확인해주세요.");
			history.go(-1);
		}
	});
});
</script>
</body>
</html>	