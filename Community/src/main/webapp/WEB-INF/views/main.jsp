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
<script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
</head>
<body>
<!--  <h1>!! 여기가 메인화면 !! ${customerEmail} ${customerId}</h1> -->
<span>
	<a id="login_user" href="/kmweb/main/login"> 로그인 </a>
	<a id="logout_user" href="/kmweb/main/logout"> 로그아웃 </a>
</span>
<script type="text/javascript">
	var login_user = $('#login_user');
	var logout_user = $('#logout_user');
	if("${customerId}" == "") {
		logout_user.remove();
	} else {
		login_user.remove();
	}
</script>
</body>
</html>