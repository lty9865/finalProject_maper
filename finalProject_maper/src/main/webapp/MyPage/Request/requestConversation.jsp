<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/Common/link.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
    href="${pageContext.request.contextPath}/Resources/css/myPage.css">
<title>Request Post View</title>
<style>
    .collapsed-content {
        display: none;
    }
</style>
<script type="text/javascript">
    var contextPath = "${pageContext.request.contextPath}";

    function toggleContent(cycleId) {
        var content = document.getElementById('cycle-' + cycleId);
        content.classList.toggle('collapsed-content');
    }
</script>
<script type="text/javascript">
	function handleReplyButtonClick(userId_admins) {
	    // Parse userId and admins values from userId_admins
	    var [userId, admins] = userId_admins.split('_');
	
	    // Check if the user is an administrator
	    var isAdmin = userId === 'admin' && admins === '1';
	
	    // Redirect to the appropriate JSP page
	    if (isAdmin) {
	        window.location.href = `${contextPath}/MyPage/Admins/adminsRequestReply.jsp`;
	    } else {
	        window.location.href = `${contextPath}/MyPage/Request/requestPost.jsp`;
	    }
	}
</script>
<script src="${pageContext.request.contextPath}/Resources/javascript/requestPostView.js"></script>
</head>
<body onload="showErrorAndGoBack()">
    <!-- error msg check -->
	<div style="display:none" id="error-message">
	   <c:out value="${requestScope.error}" />
	</div>
	
	<!-- header -->
	<%@ include file="/WEB-INF/views/Common/header.jsp"%>
	
	<!-- body/menu -->
	<jsp:include page="../menu.jsp" />
    
    <!-- body/main -->
    <h3 align="left">Details of ${rDTO.title}</h3>
    <ul class="list-group detailList">
        <!-- Assume that you fetched the messages grouped by title and conversation cycle -->
        <c:forEach var="conversation" items="${conversations}">
            <li class="list-group-item" ondblclick="toggleContent('${conversation.cycleId}')">
                <strong>
                    <c:choose>
                        <c:when test="${conversation.cycle == 0}">${conversation.title}</c:when>
                        <c:otherwise>Re<c:if test="${conversation.cycle > 1}">#${conversation.cycle - 1}</c:if>: ${conversation.title}</c:otherwise>
                    </c:choose>
                </strong>
            </li>
            <li class="list-group-item collapsed-content" id="cycle-${conversation.cycleId}"><pre>${conversation.content}</pre></li>
        </c:forEach>
    </ul>
    <button class="btn btn-primary" onclick="handleReplyButtonClick('${sessionScope.userId_admins}')">Reply</button>
    

    <!-- footer -->
	<%@ include file="/WEB-INF/views/Common/footer.jsp"%>
</body>
</html>
