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
		<a id="user_details" href="/kmweb/main/user-details"> 상세보기 </a><br>
		<a id="logout_user" href="/kmweb/main/logout"> 로그아웃 </a>
	</c:when>
	<c:otherwise>
		<a id="login_user" href="/kmweb/main/login"> 로그인 </a><br>
		<a href="/kmweb/main/signup"> 회원가입 </a>
	</c:otherwise>
</c:choose><br>
	<a href="/kmweb/board/itCategory">it</a> <br>
	<a href="/kmweb/board/languageCategory">외국어</a> <br>
	<a href="/kmweb/board/publicServantCategory">공무원</a> <br>
	<a href="/kmweb/board/certificateCategory">자격증</a> <br>
	<a href="/kmweb/board/etcCategory">기타</a> <br>

<p>IT</p>	
<table>
<c:forEach items="${itPost}" var="itPost">
<tr>
  <td><a href="/kmweb/board/itCategoryView?postNo=${itPost.postNo}">${itPost.postTitle}</a></td>
  <td>${itPost.customerNickname}</td>
  <td>${itPost.viewCount}명 봄</td>
  <td>${itPost.postDateStr}</td>
</tr>
</c:forEach>
</table><br>
<br>
<br>

<p>외국어</p>
<table>
<c:forEach items="${languagePost}" var="languagePost">
<tr>
  <td><a href="/kmweb/board/languageCategoryView?postNo=${languagePost.postNo}">${languagePost.postTitle}</a></td>
  <td>${languagePost.customerNickname}</td>
  <td>${languagePost.viewCount}명 봄</td>
  <td>${languagePost.postDateStr}</td>
</tr>
</c:forEach>
</table><br/>
<br>
<br>

<p>공무원</p>
<table>
<c:forEach items="${publicServantPost}" var="publicServantPost">
<tr>
  <td><a href="/kmweb/board/publicServantCategoryView?postNo=${publicServantPost.postNo}">${publicServantPost.postTitle}</a></td>
  <td>${publicServantPost.customerNickname}</td>
  <td>${publicServantPost.viewCount}명 봄</td>
  <td>${publicServantPost.postDateStr}</td>
</tr>
</c:forEach>
</table><br/>
<br>
<br>

<p>자격증</p>
<table>
<c:forEach items="${certificatePost}" var="certificatePost">
<tr>
  <td><a href="/kmweb/board/certificateCategoryView?postNo=${certificatePost.postNo}">${certificatePost.postTitle}</a></td>
  <td>${certificatePost.customerNickname}</td>
  <td>${certificatePost.viewCount}명 봄</td>
  <td>${certificatePost.postDateStr}</td>
</tr>
</c:forEach>
</table><br/>
<br>
<br>

<p>기타/자유</p>
<table>
<c:forEach items="${etcPost}" var="etcPost">
<tr>
  <td><a href="/kmweb/board/etcCategoryView?postNo=${etcPost.postNo}">${etcPost.postTitle}</a></td>
  <td>${etcPost.customerNickname}</td>
  <td>${etcPost.viewCount}명 봄</td>
  <td>${etcPost.postDateStr}</td>
</tr>
</c:forEach>
</table><br/>
<br>
<br>

</body>
</html>