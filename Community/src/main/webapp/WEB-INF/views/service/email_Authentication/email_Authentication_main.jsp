<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<body>
	<div id="pop_sub">
	<div module="member_checkId">
		<h1>이메일 인증</h1>
		<div class="content">
			<fieldset>
				<legend>인증번호 입력</legend>
				<p>입력한 이메일로 전송된 인증번호를 입력하세요.</p>
				<form action="email_Authentication_check" method="post">
					<input type="email" name="e_mail" id="e_mail" value="${email}" readonly="readonly">
					<input type="button" id="send_authentication" value="인증번호 전송" ><br>
				</form>
				<form>
					<input type="text" id="email_injeung" name="email_injeung" placeholder="인증번호 입력">
					<input type="button" id="Certification_input" value="인증">
				</form>
			</fieldset>
		</div>
	</div>
	<script>
	var disc_value;
	$(function() {
		$('#send_authentication').click(function() {
			var url = "email_Authentication_check";
			var e_mail = $('#e_mail').val();
			$.ajax({
				type : "post",
				url : url,
				data: "e_mail=" + e_mail,
				dataType : "html"
			}).done(
				function(data) {
					disc_value = data;
					alert('이메일이 발송되었습니다. 인증번호를 입력해주세요.');
				}).fail(function(e) {
					alert(e.responseText);
			})
		});
	});
	
	$(function() {
		$('#Certification_input').click(function() {
			var email_injeung = $('#email_injeung').val();
			var disc = disc_value;
			if(email_injeung == "") {
    	    	emailFlag = false;
				alert('인증번호를 입력해주세요.');
			} else if(email_injeung == disc) {
    	    	emailFlag = true;
				alert('인증되었습니다.');
				$('#backLayer').css("display","none");
				$('#pop').css("display","none");
		        disc_value = "";
		        $('#certification').attr("disabled", true);
		        $('#sel_email').attr("disabled", true);
		        document.getElementById("user_email_1").readOnly = true;
		        $('#pop_sub').remove();
				$("#email_check_2").html("인증되었습니다.");
			} else if(email_injeung != disc) {
    	    	emailFlag = false;
				alert('인증번호가 틀렸습니다.');
			}
		});
	});
	</script>
	</div>
</body>