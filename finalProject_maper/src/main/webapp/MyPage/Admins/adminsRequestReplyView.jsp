<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="/Common/link.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
    href="${pageContext.request.contextPath}/Resources/css/myPage.css">
<title>Admin Reply View</title>
<script type="text/javascript">
    var contextPath = "${pageContext.request.contextPath}";
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
   	<c:choose>
	    <c:when test="${fn:contains(sessionScope.userId, 'admins_1')}">
	        <jsp:include page="/MyPage/adminsMenu.jsp"/>
	    </c:when>
	    <c:otherwise>
	        <jsp:include page="/MyPage/menu.jsp"/>
	    </c:otherwise>
	</c:choose>

    <!-- body/main -->
    <div class="maper-body">
	    <h3 align="left">문의하신 ${rDTO.requestNum}번 글에 대한 답변</h3>
	    <br>
	    <ul class="list-group detailList">
	        <li class="list-group-item"><strong>제목</strong></li>
	        <li class="list-group-item">Re: ${rDTO.title}</li>
	        <li class="list-group-item"><strong>작성자</strong></li>
	        <li class="list-group-item">admins</li>
	        <li class="list-group-item"><strong>작성일</strong></li>
	        <li class="list-group-item">${rDTO.replyDate}</li>
	        <li class="list-group-item"><strong>내용</strong></li>
	        <li class="list-group-item"><pre>${rDTO.content}</pre></li>
	
	        <c:if test="${fn:substringBefore(sessionScope.userId_admins, '_') == 'admins' and fn:substringAfter(sessionScope.userId_admins, '_') == '1'}">
	            <div class="text-end">
	                <form id="editForm" class="d-inline-block"
	                    action="${pageContext.request.contextPath}/MyPage/MyPageFront?command=Admins.requestReplyEdit"
	                    method="POST">
	                    <input type="hidden" name="requestNum" value="${rDTO.requestNum}" />
	                    <input type="hidden" name="title" value="Re: ${rDTO.title}" /> <input
	                        type="hidden" name="postDate" value="${rDTO.replyDate}" /> <input
	                        type="hidden" name="content" value="${rDTO.content}" /> <input
	                        type="submit" class="btn btn-info" value="수정하기"
	                        onclick="return confirmEdit();" />
	                </form>
	                <form id="deleteForm" class="d-inline-block"
	                    action="${pageContext.request.contextPath}/MyPage/MyPageFront?command=Admins.requestReplyDelete"
	                    method="POST">
	                    <input type="hidden" name="requestNum" value="${rDTO.requestNum}" />
	                    <input type="button" class="btn btn-danger" value="삭제하기"
	                        onclick="confirmDelete();" />
	                </form>
	            </div>
	        </c:if>
	    </ul>
	</div>

    <!-- footer -->
    <%@ include file="/WEB-INF/views/Common/footer.jsp"%>
</body>
</html>
                        
