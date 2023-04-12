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
<title>My Page with Admins mode</title>
</head>
<body>
	<!-- header -->
	<%@ include file="/WEB-INF/views/Common/header.jsp"%>

	<!-- body/menu -->
	<jsp:include page="/MyPage/adminsMenu.jsp" />

	<!-- body/main -->
	<div class="maper-body">
		<h2>접수된 문의 관리</h2>
		<hr><hr>
		<table class="table maper-body table-hover boardlist">
			<thead>
				<tr align="center" class="warning header-row">
					<th>문의 번호</th>
					<th>문의 제목</th>
					<th>작성자</th>
					<th>문의 일자</th>
					<th>진행 상태</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.requestBoard}" var="post">
					<tr align="center" class="header-row">
						<td>${post.requestNum}</td>
						<td>
							<c:url value="/MyPage/MyPageFront?command=MyRequest.requestTitleClick" var="titleClickUrl">
								<c:param name="requestNum" value="${post.requestNum}" />
								<c:param name="postUserId" value="${post.userId}" />
							</c:url> 
							<a href="${titleClickUrl}">${post.title}</a></td>
						<td>${post.userId}</td>
						<td>${post.postDate}</td>
						<td>${post.status == 1 ? "진행 중" : "답변 완료"}</td>
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
				<li class="page-item"><a class="page-link"
					href="${pageContext.request.contextPath}/MyPage/MyPageFront?command=Admins.requestBoard&page=1">«</a>
				</li>
				<li class="page-item"><a class="page-link"
					href="${pageContext.request.contextPath}/MyPage/MyPageFront?command=Admins.requestBoard&page=${prevPage}">‹</a>
				</li>
			</c:if>
			<c:forEach begin="1" end="${totalPages}" step="1" var="page">
				<c:choose>
					<c:when test="${page == currentPage}">
						<li class="page-item active"><a
							class="page-link visited-page"
							href="${pageContext.request.contextPath}/MyPage/MyPageFront?command=Admins.requestBoard&page=${page}">
								<b>${page}</b>
						</a></li>
					</c:when>
					<c:otherwise>
						<li class="page-item"><a class="page-link"
							href="${pageContext.request.contextPath}/MyPage/MyPageFront?command=Admins.requestBoard&page=${page}">
								<span>${page}</span>
						</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${page < totalPages}">
				<li class="page-item"><a class="page-link"
					href="${pageContext.request.contextPath}/MyPage/MyPageFront?command=Admins.requestBoard&page=${nextPage}">›</a>
				</li>
				<li class="page-item"><a class="page-link"
					href="${pageContext.request.contextPath}/MyPage/MyPageFront?command=Admins.requestBoard&page=${totalPages}">»</a>
				</li>
			</c:if>
		</ul>
	</div>
	<br><br>

	<!-- footer -->
	<%@ include file="/WEB-INF/views/Common/footer.jsp"%>
</body>
</html>

