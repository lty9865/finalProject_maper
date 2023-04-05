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
    <hr>
	<table border="1">
	    <thead>
	        <tr>
	            <th>이미지</th>
	            <th>제목</th>
	            <th>작성자</th>
	            <th>작성일</th>
	        </tr>
	    </thead>
	    <tbody>
	        <c:forEach var="dto" items="${likeList}">
	            <tr>
	                <td>
	                    <img src="${dto.bookImg}" alt="Book Image" width="50" height="50">
	                </td>
	                <td>
	                    <a href="${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyLike.likedBookView&bookNum=${dto.bookNum}">
	                        ${dto.title}
	                    </a>
	                    <!-- Include the bookNum value as a hidden field -->
	                    <input type="hidden" name="bookNum" value="${dto.bookNum}">
	                </td>
	                <td>${dto.userId}</td>
	                <td>${dto.postDate}</td>
	            </tr>
	        </c:forEach>
	    </tbody>
	</table>
    
	<!-- Pagination -->
	<div class="pagingArea">
	    <ul class="pagination">
	        <c:if test="${page > 1}">
	            <li class="page-item">
	                <a class="page-link" href="${pageContext.request.contextPath}/MyPageFront?command=MyLike&pageNo=1">«</a>
	            </li>
	            <li class="page-item">
	                <a class="page-link" href="${pageContext.request.contextPath}/MyPageFront?command=MyLike&pageNo=${page - 1}">‹</a>
	            </li>
	        </c:if>
	        <c:forEach begin="1" end="${totalPages}" step="1" var="page">
	            <c:choose>
	                <c:when test="${page == currentPage}">
	                    <li class="page-item active">
	                        <a class="page-link visited-page" href="${pageContext.request.contextPath}/MyPageFront?command=MyLike&pageNo=${page}">
	                            <b>${page}</b>
	                        </a>
	                    </li>
	                </c:when>
	                <c:otherwise>
	                    <li class="page-item">
	                        <a class="page-link" href="${pageContext.request.contextPath}/MyPageFront?command=MyLike&pageNo=${page}">
	                            <span>${page}</span>
	                        </a>
	                    </li>
	                </c:otherwise>
	            </c:choose>
	        </c:forEach>
	        <c:if test="${page < totalPages}">
	            <li class="page-item">
	                <a class="page-link" href="${pageContext.request.contextPath}/MyPageFront?command=MyLike&pageNo=${page + 1}">›</a>
	            </li>
	            <li class="page-item">
	                <a class="page-link" href="${pageContext.request.contextPath}/MyPageFront?command=MyLike&pageNo=${totalPages}">»</a>
	            </li>
	        </c:if>
	    </ul>
	</div>

	<!-- footer -->
    <%@ include file="/WEB-INF/views/Common/footer.jsp"%>
</body>
</html>
