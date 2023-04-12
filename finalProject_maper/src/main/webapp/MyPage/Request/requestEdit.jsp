<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ include file="/Common/link.jsp"%>  
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Resources/css/myPage.css">
  <script src="${pageContext.request.contextPath}/Resources/javascript/requestEdit.js"></script>
  <title>Request Post</title>
</head>
<body>
  <!-- header -->
  <%@ include file="/WEB-INF/views/Common/header.jsp"%>

  <!-- body/menu -->
  <jsp:include page="../menu.jsp" />

  <!-- body/main -->
  <div class="maper-body">
	  <form action="${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyRequest.requestEditProcess" method="POST">
	    <input type="hidden" name="requestNum" value="${rDTO.requestNum}">
	    <table class="table">
	      <tr>
	        <td><strong>문의 번호</strong></td>
	        <td>${rDTO.requestNum}</td>
	      </tr>
	      <tr>
	        <td><strong>작성자</strong></td>
	        <td>${rDTO.userId}</td>
	      </tr>
	      <tr>
	        <td><strong>작성일</strong></td>
	        <td>${rDTO.postDate}</td>
	      </tr>
	      <tr>
	        <td><strong>제목</strong></td>
	        <td><input type="text" name="title" value="${rDTO.title}" required="required"></td>
	      </tr>
	      <tr>
	        <td><strong>내용</strong></td>
	        <td><textarea cols="90" rows="15" name="content" required="required">${rDTO.content}</textarea></td>
	      </tr>
	      <tr>
	        <td colspan="2">
	          <div class="btnArea">
	            <button type="submit" class="custom-button">수정 완료</button>
	            <button type="button" class="custom-button" onclick="resetForm();">글 초기화</button>
	          </div>
	        </td>
	      </tr>
	    </table>
	  </form>
  </div>
  
  <!-- footer -->
  <%@ include file="/WEB-INF/views/Common/footer.jsp"%>
</body>
</html>
