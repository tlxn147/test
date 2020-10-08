<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>

<form method="post">

<label>제목</label>
<input type="text" name="postTitle" value="${view.postTitle}"/><br/>

<label>글 분류</label>
<select name="postCategory">
  <option value='' selected>-- 선택 --</option>
  <option value="문의글">문의글</option>
  <option value="질문">질문</option>
  <option value="기타">기타</option>
</select><br/>
<textarea name="contents" cols="40" rows="8">${view.contents}</textarea><br/>
<input type="hidden" name="postNo" value="${view.postNo}"/>

<button type="submit">수정</button>
<button type="button" onclick="history.go(-1)">취소</button>
</form>

</body>
</html>