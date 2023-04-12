<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Resources/css/myPage.css">
<title>Insert title here</title>
</head>
<body>
	<!-- header -->
    <%@ include file="/WEB-INF/views/Common/header.jsp"%>

    <!-- body/menu -->
    <jsp:include page="/MyPage/adminsMenu.jsp" />
    
    <!-- body/main -->
    <div class="maper-body">
		<h2>접수된 신고 관리</h2>
		<hr><hr>
	    <table class="table maper-body table-hover boardlist">
	        <thead>
	            <tr align="center" class="warning header-row">
	                <th>신고 번호</th>
	                <th>제목</th>
	                <th>작성자</th>
	                <th>신고 횟수</th>
	                <th>처리</th>
	            </tr>
	        </thead>
	        <tbody>
	            <c:forEach items="${requestScope.reportBoard}" var="post">
	                <tr align="center" class="header-row">
	                    <td>${post.reportNum}</td>
	                    <td>${post.bookTitle}</td>
	                    <td>${post.userId}</td>
	                    <td>${post.count}</td>
	                    <td><a href="${pageContext.request.contextPath}/MyPage/MyPageFront?command=Admins.reportDelete&reportNum=${post.reportNum}">[완료]</a>
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
                    <a class="page-link" href="${pageContext.request.contextPath}/MyPage/MyPageFront?command=Admins.reportBoard&page=1">«</a>
                </li>
                <li class="page-item">
                    <a class="page-link" href="${pageContext.request.contextPath}/MyPage/MyPageFront?command=Admins.reportBoard&page=${prevPage}">‹</a>
                </li>
            </c:if>
            <c:forEach begin="1" end="${totalPages}" step="1" var="page">
                <c:choose>
                    <c:when test="${page == currentPage}">
                        <li class="page-item active">
                            <a class="page-link visited-page" href="${pageContext.request.contextPath}/MyPage/MyPageFront?command=Admins.reportBoard&page=${page}">
                                <b>${page}</b>
                            </a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item">
                            <a class="page-link" href="${pageContext.request.contextPath}/MyPage/MyPageFront?command=Admins.reportBoard&page=${page}">
                                <span>${page}</span>
                            </a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${page < totalPages}">
                <li class="page-item">
                    <a class="page-link" href="${pageContext.request.contextPath}/MyPage/MyPageFront?command=Admins.reportBoard&page=${nextPage}">›</a>
                </li>
                <li class="page-item">
                    <a class="page-link" href="${pageContext.request.contextPath}/MyPage/MyPageFront?command=Admins.reportBoard&page=${totalPages}">»</a>
                </li>
            </c:if>
        </ul>
    </div>
    <br><br>
    
    <!-- footer -->
    <%@ include file="/WEB-INF/views/Common/footer.jsp"%>
</body>
</html>