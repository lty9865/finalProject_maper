<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Resources/css/myPage.css">
<title>My Page with Admins mode</title>
</head>
<body>
	<!-- header -->
    <%@ include file="/WEB-INF/views/Common/header.jsp"%>

    <!-- body/menu -->
    <jsp:include page="/MyPage/adminsMenu.jsp" />
    
    <!-- body/main -->
    <div class="maper-body">
		<h2>회원 관리</h2>
		<hr><hr>
    
	    <table class="table maper-body table-hover boardlist">
	        <thead>
	            <tr align="center" class="warning header-row">
	                <th>아이디</th>
	                <th>이메일</th>
	                <th>회원 상태</th>
	                <th>가입 일자</th>
	                <th>탈퇴 일자</th>
	            </tr>
	        </thead>
	        <tbody>
	            <c:forEach items="${requestScope.memberBoard}" var="post">
	                <tr align="center" class="header-row">
	                    <td>${post.userId}</td>
	                    <td>${post.email}</td>
	                    <td>
	                        <c:choose>
	                            <c:when test="${post.state == 1}">
	                                가입 중
	                            </c:when>
	                            <c:otherwise>
	                                탈퇴
	                            </c:otherwise>
	                        </c:choose>
                    	</td>
	                    <td>${post.joinDate}</td>
	                    <td>${post.deleteDate}</td>
	                </tr>
	            </c:forEach>
	        </tbody>
	    </table>
	    <br>
	</div>

	<!-- Paging Process -->
    <div class="pagingArea">
        <ul class="pagination">
            <c:if test="${page > 1}">
                <li class="page-item">
                    <a class="page-link" href="${pageContext.request.contextPath}/MyPage/MyPageFront?command=Admins&page=1">«</a>
                </li>
                <li class="page-item">
                    <a class="page-link" href="${pageContext.request.contextPath}/MyPage/MyPageFront?command=Admins&page=${prevPage}">‹</a>
                </li>
            </c:if>
            <c:forEach begin="1" end="${totalPages}" step="1" var="page">
                <c:choose>
                    <c:when test="${page == currentPage}">
                        <li class="page-item active">
                            <a class="page-link visited-page" href="${pageContext.request.contextPath}/MyPage/MyPageFront?command=Admins&page=${page}">
                                <b>${page}</b>
                            </a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item">
                            <a class="page-link" href="${pageContext.request.contextPath}/MyPage/MyPageFront?command=Admins&page=${page}">
                                <span>${page}</span>
                            </a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${page < totalPages}">
                <li class="page-item">
                    <a class="page-link" href="${pageContext.request.contextPath}/MyPage/MyPageFront?command=Admins&page=${nextPage}">›</a>
                </li>
                <li class="page-item">
                    <a class="page-link" href="${pageContext.request.contextPath}/MyPage/MyPageFront?command=Admins&page=${totalPages}">»</a>
                </li>
            </c:if>
        </ul>
    </div>
    <br><br>
    
    <!-- footer -->
    <%@ include file="/WEB-INF/views/Common/footer.jsp"%>
</body>
</html>