<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<h1>!! 여기가 메인화면 !! </h1>
<c:choose>
	<c:when test="${login != null}" >
		<span>
			${login.customerEmail}
			${login.customerId}
		</span><br>
		<a id="user_details" href="/kmweb/main/details"> 상세보기 </a><br>
		<a id="logout_user" href="/kmweb/main/logout"> 로그아웃 </a>
	</c:when>
	<c:otherwise>
		<a id="login_user" href="/kmweb/main/login"> 로그인 </a>
	</c:otherwise>
</c:choose><br>
<a href="/kmweb/main/signup"> 회원가입 </a>
	<a href="/kmweb/board/itCategory">it</a> <br>
	<a href="/kmweb/board/languageCategory">외국어</a> <br>
	<a href="/kmweb/board/publicServantCategory">공무원</a> <br>
	<a href="/kmweb/board/certificateCategory">자격증</a> <br>
	<a href="/kmweb/board/etcCategory">기타</a> <br>
</body>
</html>