<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/Common/link.jsp" %>
<%
	String userId = "test1";
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
    <jsp:include page="/MyPage/menu.jsp"/>
    
    
    <!-- footer -->
    <%@ include file="/WEB-INF/views/Common/footer.jsp" %>
</body>
</html>
