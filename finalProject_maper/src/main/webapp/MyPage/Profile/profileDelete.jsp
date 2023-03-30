<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../Common/link.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Page - Delete Profile</title>
<!-- include the script file -->
<script type="text/javascript" src="profileDelete.js" charset="UTF-8"></script>
</head>
<body class="maper-body-background">
	<!-- header -->
	<%@ include file="/WEB-INF/views/Common/header.jsp" %>
	
	<!-- body/menu -->
	<jsp:include page="../menu.jsp"/>
	
	<!-- body -->
	<h2>계정 삭제</h2>
	<hr>
	<form action="${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyProfile.profileDelete" method="post">
		<p><strong>비밀번호 입력:</strong> <input type="password" name="password"></p>
		<p><input type="button" value="Delete Profile" onclick="confirmDelete()"></p>
		<p><input type="button" value="Cancel" onclick="history.back()"></p>
	</form>
	
	<!-- footer -->
	<%@ include file="/WEB-INF/views/Common/footer.jsp" %>
</body>
</html>
