<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/Common/link.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>My Page</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>

<style>
	.nav {
		--bs-nav-link-color: #0078ff;	
	}	
</style>
</head>
<body>
  <ul class="nav maper-body nav-tabs">
    <li class="nav-item">
      <a class="nav-link <c:if test="${selectedMenuItem == 'AdminsMemberBoard'}">active</c:if>" href="${pageContext.request.contextPath}/MyPage/MyPageFront?command=Admins.memberBoard">회원 리스트</a>
    </li>
    <li class="nav-item">
      <a class="nav-link <c:if test="${selectedMenuItem == 'AdminsRequestBoard'}">active</c:if>" href="${pageContext.request.contextPath}/MyPage/MyPageFront?command=Admins.requestBoard">문의 리스트</a>
    </li>
    <li class="nav-item">
      <a class="nav-link <c:if test="${selectedMenuItem == 'AdminsReportBoard'}">active</c:if>" href="${pageContext.request.contextPath}/MyPage/MyPageFront?command=Admins.reportBoard">신고 리스트</a>
    </li>
  </ul>
  <br>
</body>
</html>
