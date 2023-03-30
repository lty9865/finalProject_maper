<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../Common/link.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Check Real Account</title>
</head>
<body class="maper-body-background">
	<!-- header -->
	<%@ include file="/WEB-INF/views/Common/header.jsp" %>
	
	<!-- body/menu -->
	<jsp:include page="../menu.jsp"/>
	
	<!-- body -->
	<h2>Check Real Account</h2>
	<hr>
	<form action="${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyProfile.checkRealAccount" method="post">
		<p><strong>User ID:</strong> ${sessionScope.userId}</p>
		<p><strong>Password:</strong> <input type="password" name="password"></p>
		<input type="submit" value="확인">
		<hr>
	</form>
	
	<!-- notification message for wrong password -->
	<c:if test="${not empty errMsg}">
		<script>
			alert("${errMsg}");
		</script>
	</c:if>
	
	<!-- footer -->
	<%@ include file="/WEB-INF/views/Common/footer.jsp" %>
</body>
</html>
