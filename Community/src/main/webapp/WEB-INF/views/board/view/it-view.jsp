<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<script src="//code.jquery.com/jquery-3.5.1.min.js"></script>
<head>
<meta charset="UTF-8">
<title>it_category</title>
</head>
<body>
<fmt:formatDate var="time" value="${view.postDate}" pattern="yyyy.MM.dd" />
<label>제목</label>
<input type="text" name="postTitle" value="${view.postTitle}" readonly/><br/>
<label>글 분류</label>
<input type="text" name="postCategory" value="${view.postCategory}" readonly/><br/>
<label>조회수</label>
<input type="text" name="viewCount" value="${view.viewCount}" readonly/><br/>
<label>작성일</label>
<input type="text" name="postDate" value="${time}" readonly/><br/>
<label>작성자</label>
<input type="text" name="customerNickname" value="${view.customerNickname}" readonly/><br/>
<textarea name="contents" cols="40" rows="8">${view.contents}</textarea><br/>
<button type="button" id="btn_update" onclick="location.href='/kmweb/board/itCategoryUpdate?postNo=${view.postNo}'" >수정하기</button>
<button type="button" id="btn_delete" onclick="location.href='/kmweb/board/itCategoryDelete?postNo=${view.postNo}'">삭제하기</button>
<button type="button" onclick="location.href='/kmweb/board/itCategory'">목록</button>
<!-- 댓글 -->

<hr />

<ul>
	<c:forEach items="${comment}" var="comment">
<li>
	<div id='cmt1'>
		<p>${comment.customerNickname} / <fmt:formatDate value="${comment.postDate}" pattern="yyyy-MM-dd" /> </p>  
		<div id="comments"><p >${comment.comments}</p></div>
		<button type="button" id="btn_commentUpdate">수정</button> 
		<button type="button" id="btn_commentDelete" onclick="location.href='/kmweb/board/itCommentDelete?postNo=${comment.postNo}&commentsNo=${comment.commentsNo}'">삭제</button>    
	</div>
	<div id='cmt2' style ='display:none'>
	<form method='post' action ="/kmweb/board/itCommentUpdate">
	<p>${comment.customerNickname}</p>
	<input type='text' name='postNo' style='display:none' value='${comment.postNo}'>
	<input type='text' name='commentsNo' style='display:none' value='${comment.commentsNo}'>
	<textarea id='commentsUpdate' name='comments' rows='5' cols='50' >${comment.comments}</textarea><br>
	<button type='submit' id='btn_commentsUpdatePost'>저장</button>
	<button type='button' id='btn_commentsUpdateCancel'>취소</button>
	</form>
	</div>
</li>
<!-- 내 작성 댓글만 수정/삭제 버튼 보이기 -->
<script type="text/javascript">
$(document).ready(function(){
	var loginNo = '${login.customerNo}'
	var commentWriterNo = '${comment.customerNo}'
	if(loginNo != commentWriterNo){
        $("#btn_commentUpdate").hide();
		$("#btn_commentDelete").hide();
	}
});
</script>
<!-- 수정,저장,취소 버튼-->
<script>
$(document).ready(function() {
	$("#btn_commentUpdate").click(function(){
		$('#cmt1').hide();
		$('#cmt2').show();
		$('#commentsUpdate').focus();
    	});
})
</script>
<!-- 취소버튼 -->
<script>
$(document).ready(function() {
	$("#btn_commentsUpdateCancel").click(function(){
		$('#cmt1').show();
		$('#cmt2').hide();
    	
		});
	
})
</script>
</c:forEach>
</ul>
<div>
    <form method ="post" action = "/kmweb/board/itCommentReply">
    
    <label>댓글 작성자</label>
    <input type="text" name="customerNickname" value="${login.customerNickname}" readonly><br/>
    
    <textarea name="comments" rows="5" cols="50"></textarea>
	
	<input type ="hidden" name="customerNo" value="${login.customerNo}"/>
	<input type ="hidden" name="postNo" value="${view.postNo}"/>
	
	<button type="submit">댓글 작성</button>
	
	</form>
</div>
<!-- 자기 글만 수정 삭제 버튼 보이기 -->
<script type="text/javascript">
$(document).ready(function(){
	var loginNo = '${login.customerNo}'
	var writerNo = '${view.customerNo}' 
	if(loginNo != writerNo){
		$("#btn_update").hide()
		$("#btn_delete").hide()
		}
});
</script>
</body>
</html>