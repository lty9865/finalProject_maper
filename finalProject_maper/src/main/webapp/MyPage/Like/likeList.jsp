<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Like List</title>
</head>
<body>
	<!-- header -->
    <%@ include file="/WEB-INF/views/Common/header.jsp"%>

    <!-- body/menu -->
    <jsp:include page="../menu.jsp" />

    <!-- body/main -->
    <h1>My Like List</h1>
	<!-- Display errorMsg if it exists -->
	<c:if test="${not empty errorMsg}">
		<div class="alert alert-danger" role="alert">${errorMsg}</div>
	</c:if>
	<hr>
	<table border="1">
	    <thead>
	        <tr>
	            <th>이미지</th>
	            <th>제목</th>
	            <th>작성자</th>
	            <th>좋아요</th>
	        </tr>
	    </thead>
	    <tbody>
            <c:forEach var="dto" items="${likeList}">
                <tr>
                    <td>
                        <c:choose>
                            <c:when test="${dto.sfile != null}">
                                <img src="${pageContext.request.contextPath}/Uploads/Profile/${dto.sfile}" alt="Book Image" width="50" height="50">
                            </c:when>
                            <c:otherwise>
                                <img src="${pageContext.request.contextPath}/Uploads/Profile/${dto.ofile}" alt="Book Image" width="50" height="50">
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/MyPage/MyPage/MyPageFront?command=MyLike.likedBookView&bookNum=${dto.bookNum}">
                            ${dto.title}
                        </a>
                        <!-- Include the bookNum value as a hidden field -->
                        <input type="hidden" name="listNum" value="${dto.listNum}">
                        <input type="hidden" name="bookNum" value="${dto.bookNum}">
                    </td>
                    <td>${dto.userId}</td>
                </tr>
            </c:forEach>
	    </tbody>
	</table>
    
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
