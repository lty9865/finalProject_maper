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
	<div class="maper-body">
		<h2>계정 정보 확인</h2>
		<hr>
		<form action="${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyProfile.checkRealAccount" method="post">
			<p>
				<span>
					<strong>아이디:</strong> ${sessionScope.userId}
				</span>
			</p>
			<p><strong>비밀번호:</strong> <input type="password" name="password"></p>
			<input type="submit" value="확인" class="custom-button">
			<hr>
		</form>
	</div>
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
