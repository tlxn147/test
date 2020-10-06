<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<title>회원가입</title>
<style type="text/css">
#pop {
	display:none;
	width: 500px;
	height: 300px;
	background: #3d3d3d;
	color: #fff;
	position: absolute;
	align-content:center;
	top: 150px;
	left: 100px;
	border: 2px solid #000;
}
#backLayer {
	display:none;
	width: 100%;
	height: 100%;
	background: black;
	position: absolute;
	opacity: 0.5;
	top: 0px;
	left: 0px;
}
</style>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
	<form action="success_signup" id="signup_submit" method="post">
		<br> 아이디
		<br>
		<input type='text' name='customerId' id='user_id'><br>
		<div id="id_check"></div>
		<br> 비밀번호
		<br>
		<input type="password" name='customerPW' id='user_pw'>
		<div id="pw_check"></div>
		<br> 비밀번호 재입력
		<br>
		<input type='password' id='user_pw_sub'>
		<div id="pw_sub_check"></div>
		<br> 이름
		<br>
		<input type='text' name='customerName' id='user_name'>
		<div id="name_check"></div>
		<br> 닉네임
		<br>
		<input type='text' name='customerNickname' id='user_nickname'>
		<div id="nickname_check"></div>
		<br> 성별
		<br>
		<select id="user_gender" name='customerGender' onchange="gender_chan()">
			<option value="" selected>성별</option>
			<option value="M">남자</option>
			<option value="F">여자</option>
			<option value="U">선택안함</option>
		</select>
		<div id="gender_check"></div>
		<br> 이메일
		<br>
		<input type='text' id='user_email_1' size="8" name='customerEmail_1'> @
		<input type='text' id='user_email_2' size="8" name='customerEmail_2'>
		<select id="sel_email" onchange="sel_data()">
			<option value="0">직접입력</option>
			<option value="1">네이버</option>
			<option value="2">다음</option>
			<option value="3">구글</option>
		</select>
		<input type='button' id='certification' value='이메일 인증' onclick="email_Authentication()">
		<div id="email_check_1"></div>
		<div id="email_check_2"></div>
		<br>관심사
		<br>
		<c:forEach var="list" items="${signup_interest}">
			<input type="checkbox" name="customerInterest" value="${list}">${list}
		</c:forEach>
		<div id="interest_check"></div>
		<br>
		<input type="button" id="dataSubmit" value="회원가입">
		<input type="button" id="dataSubmit" value="뒤로가기" onclick="history.go(-1)">
		<div id="backLayer"></div>
		<div id="pop" style="display: none"></div>
	</form>
	<script type="text/javascript" src="resources/js/signup_script.js"></script>
	<script type="text/javascript">
	var idRegExp = /^[a-zA-z0-9]{5,20}$/; // 아이디 유효성 검사
	var pwRegExp = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/; // 비밀번호 유효성 검사
	var emailRegExp = /^[a-zA-z0-9]{3,12}$/; // 이메일 유효성 검사
	var idFlag = false;
	var pw1Flag = false;
	var pw2Flag = false;
	var nameFlag = false;
	var nickFlag = false;
	var genderFlag = false;
	var emailFlag = false;
	var interFlag = false;
	
	$(function() {
		$('#user_id').blur(function() { // 아이디
		    var url = "user_id_check";
		    var id_chk = $("#user_id").val();
		    $.ajax({
		        type: "post",
		        url: url,
		        data: "customerId=" + id_chk,
		        dataType: "html"
		    }).done(
		        function(data) {
			        console.log(data);
		        	if (id_chk == "") {
			            idFlag = false;
		                $("#id_check").html("필수정보 입니다.");
		            } else if(!idRegExp.test(id_chk)) {
			            idFlag = false;
		        		$("#id_check").html("5~20자의 영문 소문자, 숫자만 사용 가능합니다.");
		            } else if (id_chk == data) {
			            idFlag = false;
		                $("#id_check").html("중복된 아이디 입니다.");
		            } else if (id_chk != data) {
			            idFlag = true;
		                $("#id_check").html("사용가능한 아이디 입니다");
		            }
		        }).fail(function(e) {
		        alert(e.responseText);
		    })
		});
		$('#user_pw').blur(function() { // 비밀번호
		    var pw_chk = $("#user_pw").val();
		    if (pw_chk == "" || pw_chk == null) {
		    	pw1Flag = false;
                $("#pw_check").html("필수정보 입니다.");
            } else if(!pwRegExp.test(pw_chk)) {
            	pw1Flag = false;
        		$("#pw_check").html("8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.");
            } else if (pw_chk != "" || pw_chk != null) {
            	pw1Flag = true;
                $("#pw_check").html("");
            }   
		});
		$('#user_pw_sub').blur(function() { // 비밀번호 확인
		    var pw_sub_chk = $("#user_pw_sub").val();
		    var pw_chk = $("#user_pw").val();
		    if (pw_sub_chk == "" || pw_sub_chk == null) {
			    pw2Flag = false;
                $("#pw_sub_check").html("필수정보 입니다.");
            }else if (pw_sub_chk != "" || pw_sub_chk != null) {
            	pw2Flag = true;
                $("#pw_sub_check").html("");
            }
            if(pw_sub_chk != pw_chk) {
            	pw2Flag = false;
            	$("#pw_sub_check").html("비밀번호가 일치하지 않습니다.");
            } else if(pw_sub_chk != pw_chk) {
            	pw2Flag = true;
            	$("#pw_sub_check").html("");
            }
		});
		$('#user_name').blur(function() { // 이름
		    var name_chk = $("#user_name").val();
		    if (name_chk == "" || name_chk == null) {
            	nameFlag = false;
                $("#name_check").html("필수정보 입니다.");
            }else if (name_chk != "" || name_chk != null) {
            	nameFlag = true;
                $("#name_check").html("");
            }   
		});
		$('#user_nickname').blur(function() { // 닉네임
		    var url = "user_nickname_check";
		    var nickname_chk = $("#user_nickname").val();
		    $.ajax({
		        type: "post",
		        url: url,
		        data: "customerNickname=" + nickname_chk,
		        dataType: "html"
		    }).done(
		        function(data) {
		            if (nickname_chk == "") {
		            	nickFlag = false;
		                $("#nickname_check").html("필수정보 입니다.");
		            } else if (nickname_chk == data) {
		            	nickFlag = false;
		                $("#nickname_check").html("중복된 닉네임 입니다.");
		            } else if (nickname_chk != data) {
		            	nickFlag = true;
		                $("#nickname_check").html("사용가능한 닉네임 입니다");
		            }
		        }).fail(function(e) {
		        alert(e.responseText);
		    })
		});
		$('#user_name').blur(function() { // 이름
		    var name_chk = $("#user_name").val();
		    if (name_chk == "" || name_chk == null) {
            	nameFlag = false;
                $("#name_check").html("필수정보 입니다.");
            }else if (name_chk != "" || name_chk != null) {
            	nameFlag = true;
                $("#name_check").html("");
            }   
		});
		
		$('#user_email_1').blur(function() { // 이메일
		    var user_email_1 = $("#user_email_1").val();
		    var user_email_2 = $("#user_email_2").val();
		    var email_chk = user_email_1 + user_email_2;
		    if (email_chk == "" || email_chk == null) {
            	emailFlag = false;
                $("#email_check_1").html("필수정보 입니다.");
            } else if(!emailRegExp.test(user_email_1)) {
            	emailFlag = false;
        		$("#email_check_1").html("이메일 주소를 다시 확인해주세요.");
            } else if (email_chk != "" || email_chk != null) {
            	emailFlag = true;
                $("#email_check_1").html("");
            }
		});
		
		$('input[name=customerInterest]').blur(function() { // 관심사
			var inter_chk = $('input[name=customerInterest]:checked');
			console.log(inter_chk);
		    if (inter_chk.is(":checked") == false) {
				console.log(inter_chk);
		    	interFlag = false;
                $("#interest_check").html("최소 한개의 관심사를 선택해주세요.");
            }  else if (inter_chk.is(":checked") == true) {
    			console.log(inter_chk);
            	interFlag = true;
                $("#interest_check").html("");
            }
		});
	});
	function gender_chan() { // 성별
		var gender_chk = $("#user_gender").val();
	    if (gender_chk == "" || gender_chk == null) {
	    	genderFlag = false;
            $("#gender_check").html("필수정보 입니다.");
        }else if (gender_chk != "" || gender_chk != null) {
	    	genderFlag = true;
            $("#gender_check").html("");
        } 
	}
    
	// 이메일 인증
	function email_Authentication() {
	    var url = "email_Authentication";
	    var user_email_1 = $("#user_email_1").val();
	    var user_email_2 = $("#user_email_2").val();
	    var email_sub = user_email_1 + "@" + user_email_2;
	    $.ajax({
	        type: "post",
	        url: url,
	        data: "email_sub=" + email_sub,
	        dataType: "html"
	    }).done(
	        function(data) {
		        if(user_email_1 == "" || user_email_2 == "") {
		        	$("#email_check_1").html("이메일을 입력해주세요.");
			    } else {
			    	$('#backLayer').css("display", "block");
		            $('#pop').css("display", "block");
		            $('#pop').html(data);
				}
	            
	        }).fail(function(e) {
	        alert(e.responseText);
	    })
	}
	// 이메일 select
	function sel_data() {
		var platform = $("#sel_email option:checked").val();
		if (platform == 0) {
			document.getElementById("user_email_2").value = '';
			document.getElementById("user_email_2").readOnly = false;
		} else if (platform == 1) {
			document.getElementById("user_email_2").value = 'naver.com';
			document.getElementById("user_email_2").readOnly = true;
		} else if (platform == 2) {
			document.getElementById("user_email_2").value = 'daum.net';
			document.getElementById("user_email_2").readOnly = true;
		} else if (platform == 3) {
			document.getElementById("user_email_2").value = 'gmail.com';
			document.getElementById("user_email_2").readOnly = true;
		}
	}
	// 배경 클릭시 종료
	$(function() {
	    $('#backLayer').click(function() {
	        $('#backLayer').css("display", "none");
	        $('#pop').css("display", "none");
	        $('#pop_sub').remove();
	        disc_value = "";
	    });
	});
	
	var id = document.getElementById("user_id");
    var pw = document.getElementById("user_pw");
    var email = document.getElementById("user_email_1") + document.getElementById("user_email_2");

	$(function() { // 회원가입 버튼 클릭 시
		$('#dataSubmit').click(function() {
		    var id_chk = $("#user_id").val();
		    var pw_chk = $("#user_pw").val();
		    var pw_sub_chk = $("#user_pw_sub").val();
		    var name_chk = $("#user_name").val();
		    var nickname_chk = $("#user_nickname").val();
		    var gender_chk = $("#user_gender").val();
		    var email_chk = $("#user_email_1").val() + $("#user_email_2").val();
		    var inter_chk = $('input[name=customerInterest]:checked').val();
		    console.log(idFlag);
		    console.log(pw1Flag);
		    console.log(pw2Flag);
		    console.log(nameFlag);
		    console.log(nickFlag);
		    console.log(genderFlag);
		    console.log(emailFlag);
		    console.log(interFlag);
		    if (id_chk == "" || id_chk == null) {
    	    	idFlag = false;
                $("#id_check").html("필수정보 입니다.");
			}
		    if (pw_chk == "" || pw_chk == null) {
		    	pw1Flag = false;
                $("#pw_check").html("필수정보 입니다.");
			}
		    if (pw_sub_chk == "" || pw_sub_chk == null) {
		    	pw2Flag = false;
                $("#pw_sub_check").html("필수정보 입니다.");
			}
		    if (name_chk == "" || name_chk == null) {
		    	nameFlag = false;
                $("#name_check").html("필수정보 입니다.");
			}
		    if (nickname_chk == "" || nickname_chk == null) {
		    	nickFlag = false;
                $("#nickname_check").html("필수정보 입니다.");
            }
		    if (gender_chk == "" || gender_chk == null) {
		    	genderFlag = false;
                $("#gender_check").html("필수정보 입니다.");
            }
		    if (email_chk == "" || email_chk == null) {
    	    	emailFlag = false;
                $("#email_check_1").html("필수정보 입니다.");
                $("#email_check_2").html("인증이 필요합니다.");
            }
            if(document.getElementById('certification').disabled == true) {
    	    	emailFlag = true;
                $("#email_check_2").html("인증되었습니다.");
            } else {
    	    	emailFlag = false;
                $("#email_check_2").html("인증이 필요합니다.");
            }
            if(inter_chk == "" || inter_chk == null) {
            	interFlag = false;
                $("#interest_check").html("최소 한개의 관심사를 선택해주세요.");
            }
            if(idFlag && pw1Flag && pw2Flag && nameFlag && nickFlag && genderFlag && emailFlag && interFlag == true) {
				$("#signup_submit").submit();
            }
		});
	});
	</script>
</body>
</html>