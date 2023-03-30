<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../Common/link.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Page - My Profile</title>
</head>
<body class="maper-body-background">
	<!-- header -->
	<%@ include file="/WEB-INF/views/Common/header.jsp" %>
	
	<!-- body/menu -->
	<jsp:include page="../menu.jsp"/>
	
	<!-- body -->
	<h2>My Profile</h2>
	<hr>
	<form action="${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyProfile.checkRealAccount" method="post">
		<p><strong>User ID:</strong> ${userId}</p>
		<p><strong>Email:</strong> ${email}</p>
		<p><strong>Birth:</strong> ${birth}</p>
		<p><strong>Profile Image:</strong> <img src="${pageContext.request.contextPath}${image}" alt="Profile Image"></p>
    
		<input type="submit" value="내 정보 수정">
		<hr>
	</form>
	
	<!-- notification message for empty password -->
	<c:if test="${not empty msg}">
		<script type="text/javascript">
			alert("{${msg}")
		</script>
	</c:if>
	
	<!-- footer -->
	<%@ include file="/WEB-INF/views/Common/footer.jsp" %>
</body>
</html>
