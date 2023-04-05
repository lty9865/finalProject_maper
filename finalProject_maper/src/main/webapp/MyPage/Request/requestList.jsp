<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/Common/link.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Resources/css/myPage.css">
<title>Request List</title>
<script>
    window.onload = function() {
        var message = "${message}";
        if (message) {
            alert(message);
        }
    }
</script>
</head>
<body>
    <!-- header -->
    <%@ include file="/WEB-INF/views/Common/header.jsp"%>

    <!-- body/menu -->
    <jsp:include page="../menu.jsp" />

    <!-- body/main -->
    <table class="table table-hover boardlist">
        <thead>
            <tr align="center" class="warning header-row">
                <th>문의 번호</th>
                <th>문의 제목</th>
                <th>문의 일자</th>
                <th>진행 상태</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${requestScope.requestList}" var="post">
                <tr align="center" class="header-row">
                    <td>${post.requestNum}</td>
                    <td>
                    	<a href="${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyRequest.requestPostView&requestNum=${post.requestNum}">
		                    ${post.title}
                    	</a>
                    </td>
                    <td>${post.postDate}</td>
                    <td>${post.status == 1 ? "진행 중" : "답변 완료"}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

	<!-- Paging Process -->
    <div class="pagingArea">
        <ul class="pagination">
            <c:if test="${page > 1}">
                <li class="page-item">
                    <a class="page-link" href="${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyRequest&page=1">«</a>
                </li>
                <li class="page-item">
                    <a class="page-link" href="${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyRequest&page=${prevPage}">‹</a>
                </li>
            </c:if>
            <c:forEach begin="1" end="${totalPages}" step="1" var="page">
                <c:choose>
                    <c:when test="${page == currentPage}">
                        <li class="page-item active">
                            <a class="page-link visited-page" href="${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyRequest&page=${page}">
                                <b>${page}</b>
                            </a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item">
                            <a class="page-link" href="${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyRequest&page=${page}">
                                <span>${page}</span>
                            </a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${page < totalPages}">
                <li class="page-item">
                    <a class="page-link" href="${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyRequest&page=${nextPage}">›</a>
                </li>
                <li class="page-item">
                    <a class="page-link" href="${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyRequest&page=${totalPages}">»</a>
                </li>
            </c:if>
        </ul>
	    <!-- Write button -->
	    <c:if test="${not empty sessionScope.userId}">
	        <div class="write-button">
				<form action="${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyRequest.requestPost" method="POST">
					<input type="hidden" name="userId" value="sessionScope.userId"/>
					<input type="submit" class="btn btn-primary" value="글쓰기"/>
				</form>
			</div>
	    </c:if>
    </div>

    <!-- footer -->
    <%@ include file="/WEB-INF/views/Common/footer.jsp"%>
</body>
</html>
            
