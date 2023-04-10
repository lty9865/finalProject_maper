<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/Common/link.jsp" %>
<%
	String userId = "admins_1";
	session.setAttribute("userId", userId);
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<!-- header -->
    <%@ include file="/WEB-INF/views/Common/header.jsp" %>
    
    <!-- body/menu -->
    <c:choose>
	    <c:when test="${sessionScope.userId == 'admins_1'}">
	        <jsp:include page="/MyPage/adminsMenu.jsp"/>
	    </c:when>
	    <c:otherwise>
	        <jsp:include page="/MyPage/menu.jsp"/>
	    </c:otherwise>
	</c:choose>
    
    <!-- footer -->
    <%@ include file="/WEB-INF/views/Common/footer.jsp" %>
</body>
</html>
