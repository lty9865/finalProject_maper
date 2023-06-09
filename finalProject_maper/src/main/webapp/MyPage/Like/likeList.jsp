<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Like List</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Resources/css/myPage.css">
<style>
    .table.boardlist td {
        text-align: center;
        vertical-align: middle;
    }
</style>
</head>
<body>
    <!-- header -->
    <%@ include file="/WEB-INF/views/Common/header.jsp"%>

    <!-- body/menu -->
    <jsp:include page="../menu.jsp" />

    <!-- body/main -->
    <div class="maper-body">
        <h1>My Like List</h1>
        <!-- Display errorMsg if it exists -->
        <c:if test="${not empty errorMsg}">
            <div class="alert alert-danger" role="alert">${errorMsg}</div>
        </c:if>
        <hr>
        <table class="table maper-body table-hover boardlist">
            <thead>
                <tr align="center" class="warning header-row">
                    <th>책 제목</th>
                    <th>페이지</th>
                    <th>작성자</th>
                    <th>작성일</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="dto" items="${likeList}" varStatus="status">
                    <tr>
                        <td>
                            <form method="post" action="${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyLike.likedBookView" class="book-title-form">
							    <input type="hidden" name="idx" value="${dto.bookNum}">
							    <button type="submit" class="custom-button">${dto.title}</button>
							</form>
                        </td>
                        <td>${bookPageMap[dto.bookNum]}</td>
                        <td>${dto.userId}</td>
                        <td>${dto.postDate}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <br>
    </div>
    
	<!-- Pagination -->
	<div class="pagingArea">
	    <ul class="pagination">
	        <c:if test="${page > 1}">
	            <li class="page-item">
	                <a class="page-link" href="${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyLike&pageNo=1">«</a>
	            </li>
	            <li class="page-item">
	                <a class="page-link" href="${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyLike&pageNo=${page - 1}">‹</a>
	            </li>
	        </c:if>
	        <c:forEach begin="1" end="${totalPages}" step="1" var="page">
	            <c:choose>
	                <c:when test="${page == currentPage}">
	                    <li class="page-item active">
	                        <a class="page-link visited-page" href="${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyLike&pageNo=${page}">
	                            <b>${page}</b>
	                        </a>
	                    </li>
	                </c:when>
	                <c:otherwise>
	                    <li class="page-item">
	                        <a class="page-link" href="${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyLike&pageNo=${page}">
	                            <span>${page}</span>
	                        </a>
	                    </li>
	                </c:otherwise>
	            </c:choose>
	        </c:forEach>
	        <c:if test="${page < totalPages}">
	            <li class="page-item">
	                <a class="page-link" href="${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyLike&pageNo=${page + 1}">›</a>
	            </li>
	            <li class="page-item">
	                <a class="page-link" href="${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyLike&pageNo=${totalPages}">»</a>
	            </li>
	        </c:if>
	    </ul>
	</div>

	<!-- footer -->
    <%@ include file="/WEB-INF/views/Common/footer.jsp"%>
</body>
</html>
