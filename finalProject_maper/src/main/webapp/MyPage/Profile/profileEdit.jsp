<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../Common/link.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Page - Edit Profile</title>
</head>
<body class="maper-body-background">
	<!-- header -->
	<%@ include file="/WEB-INF/views/Common/header.jsp" %>
	
	<!-- body/menu -->
	<jsp:include page="../menu.jsp"/>
	
	<!-- body -->
	<h2>Edit Profile</h2>
	<hr>
	<form action="${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyProfile.profileEdit" method="post" enctype="multipart/form-data">
		<p><strong>User ID:</strong> ${userId}</p>
		<p><strong>Password:</strong> <input type="password" name="password" value="${password}"></p>
		<p><strong>Email:</strong> <input type="text" name="email" value="${email}"></p>
		<p><strong>Birth:</strong> <input type="text" name="birth" value="${birth}"></p>
		<p><strong>Profile Image:</strong> <input type="file" name="image"></p>
    
		<hr>
		<input type="submit" value="Save">
		<input type="button" value="Cancel" onclick="location.href='${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyProfile'">
		<button type="button" onclick="location.href='${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyProfile.profileDelete';">계정 삭제</button>
	</form>
	
	<!-- footer -->
	<%@ include file="/WEB-INF/views/Common/footer.jsp" %>
</body>
</html>
