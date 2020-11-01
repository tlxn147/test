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
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.2.js" charset="utf-8"></script>
</head>
<body>
	<div>
		<!-- 기본 로그인 -->
		<form action="user_login_chk" method="post">
			<input type="text" name="customerId" id="id_login" placeholder="아이디"><br>
			<div id="id_login_chk"></div>
			<input type="password" name="customerPW" id="pw_login" placeholder="비밀번호"><br>			
			<div id="pw_login_chk"></div>
			<input type="submit" id="login_chk" value="로그인">
			<span>|</span>
			<a href="/kmweb/main/find_id" onclick="window.open(this.href,'','width=500,height=600'); return false;">ID 찾기</a>
			<span>|</span>
			<a href="/kmweb/main/find_pw" onclick="window.open(this.href,'','width=500,height=600'); return false;">비밀번호 찾기</a>
		</form>
		<!-- 카카오 로그인 -->
		<div id="btn_kakao_login">
			<a href="${kakao_url}">
				<img width="223" src="https://kauth.kakao.com/public/widget/login/kr/kr_02_medium.png"
				onmouseover="this.src='https://kauth.kakao.com/public/widget/login/kr/kr_02_medium_press.png'"
				onmouseout="this.src='https://kauth.kakao.com/public/widget/login/kr/kr_02_medium.png'"
				style="cursor: pointer" />
			</a>
		</div>
		<!-- 네이버 로그인 -->
		<div id="naver_id_login">
			<a href="${naver_url}">
				<img width="223" src="${pageContext.request.contextPath}/resources/images/naver_Bn_Green.PNG"/>
			</a>
		</div>
	</div>
<script type='text/javascript'>
        Kakao.init('YOUR KEY NUMBER');
        // 카카오 로그인 버튼을 생성합니다.
        Kakao.Auth.createLoginButton({
            container: '#kakao-login-btn',
            success: function (authObj) {
                alert(JSON.stringify(authObj));
            },
            fail: function (err) {
                alert(JSON.stringify(err));
            }
        });
</script>
<script type="text/javascript">
$(function() {
	if("${fail}" != "" || "${fail}" == null){
		pw_login_chk.innerHTML='가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.';
	}
	$('#login_chk').click(function() {
		var id_login = document.getElementById('id_login').value;
		var pw_login = document.getElementById('pw_login').value;
		var id_login_chk = document.getElementById('id_login_chk');
		var pw_login_chk = document.getElementById('pw_login_chk');
		if(id_login == "") {
			id_login_chk.innerHTML='아이디를 입력하세요.';
			pw_login_chk.innerHTML='';
			return false;
		} else if(pw_login == "") {
			pw_login_chk.innerHTML='비밀번호를 입력하세요.';
			id_login_chk.innerHTML='';
			return false;
		} else if(pw_login != "") {
			pw_login_chk.innerHTML='';
		}
	});
});
</script>
</body>
</html>