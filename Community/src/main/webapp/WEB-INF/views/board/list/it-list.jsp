<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>it_category</title>
</head>
<body>

<h4>IT 게시판</h4>

<table>
 <thead>
  <tr>
   <th>번호</th>
   <th>글분류</th>
   <th>제목</th>
   <th>닉네임</th>
   <th>작성일</th>
   <th>총댓글수</th>
   
  </tr>
 </thead>
 
 <tbody>
  
<c:forEach items="${itList}" var="list">
<fmt:formatDate var="time" value="${list.postDate}" pattern="yyyy.MM.dd"/>
  
 <tr>
  <td>${list.postNo}</td>
  <td>${list.postCategory}</td>
  <td><a href="/kmweb/board/itCategoryView?postNo=${list.postNo}">${list.postTitle}</a></td>
  <td>${list.customerNickname}</td>
  <td>${time}</td>
  <td>${list.commentsCount}</td>
 </tr>

</c:forEach>
 
 </tbody>

</table>

<div>

<c:if test="${page.prev}">
 <span>[ <a href="/kmweb/board/itCategory?num=${page.startPageNum - 1}${page.searchTypeKeyword}">이전</a> ]</span>
</c:if>

<c:forEach begin="${page.startPageNum}" end="${page.endPageNum}" var="num">
 <span>
 
  <c:if test="${select != num}">
   <a href="/kmweb/board/itCategory?num=${num}${page.searchTypeKeyword}">${num}</a>
  </c:if>    
  
  <c:if test="${select == num}">
   <b>${num}</b>
  </c:if>
    
 </span>
</c:forEach>

<c:if test="${page.next}">
 <span>[ <a href="/kmweb/board/itCategory?num=${page.endPageNum + 1}${page.searchTypeKeyword}">다음</a> ]</span>
</c:if>

</div>

<button type="button" onclick="location.href='/kmweb/board/itCategoryWrite'">글쓰기</button> <br/>

<div>
  <select name="searchType">
      <option value="postTitle" <c:if test="${page.searchType eq 'postTitle'}">selected</c:if>>제목</option>
         <option value="contents" <c:if test="${page.searchType eq 'contents'}">selected</c:if>>내용</option>
      <option value="postTitle_contents" <c:if test="${page.searchType eq 'postTitle_contents'}">selected</c:if>>제목+내용</option>
      <option value="customerNickname" <c:if test="${page.searchType eq 'customerNickname'}">selected</c:if>>작성자</option>
  </select>
  
  <input type="text" name="keyword" value="${page.keyword}" />
  
  <button type="button" id="searchBtn">검색</button>
 </div>

<script>
document.getElementById("searchBtn").onclick = function () {
    
	  let searchType = document.getElementsByName("searchType")[0].value;
	  let keyword =  document.getElementsByName("keyword")[0].value;
	  
	  location.href = "/kmweb/board/itCategory?" + "searchType=" + searchType + "&keyword=" + keyword;
	 };
</script>

</body>
</html>