<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../Common/link.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Page - Delete Profile</title>
<!-- include the script file -->
<script type="text/javascript" src="${pageContext.request.contextPath}/Resources/javascript/profileDelete.js" charset="UTF-8"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Resources/css/myPage.css">
<style type="text/css">
.marginBtn {
	margin-left: 7rem;
}
</style>
</head>
<body class="maper-body-background">
	<!-- header -->
	<%@ include file="/WEB-INF/views/Common/header.jsp" %>
	
	<!-- body/menu -->
	<jsp:include page="../menu.jsp"/>
	
	<!-- body -->
	<div class="maper-body">
		<h2>계정 삭제</h2>
		<hr>
		<form action="${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyProfile.profileDeleteProcess" method="post">
			<p>
				<strong class="input-label">아이디:</strong> 
				<input type="text" name="userId" value="${sessionScope.userId}">
			</p>
			<p>
				<strong class="input-label">비밀번호:</strong>
				<input type="password" name="password">
			</p>
			
			<p>
				<input type="submit" class="custom-button marginBtn" value="계정 삭제" onclick="confirmDelete()">
				<input type="button" class="custom-button" value="돌아가기" onclick="history.back()">
			</p>
		</form>
	</div>
	
	<!-- the result of delete -->
    <c:if test="${not empty msg}">
        <script type="text/javascript">
            alert("{${msg}")
        </script>
    </c:if>
    
	<!-- footer -->
	<%@ include file="/WEB-INF/views/Common/footer.jsp" %>
</body>
</html>
