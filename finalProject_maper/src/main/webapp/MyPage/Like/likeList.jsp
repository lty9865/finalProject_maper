<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Like List</title>
</head>
<body>
	<h1>My Like List</h1>
	<hr>
	<table border="1">
		<thead>
			<tr>
				<th>페이지</th>
				<th>제목</th>
				<th>상태</th>
				<th>작성일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="dto" items="${lvo.list}">
				<tr>
					<td>${dto.requestNum}</td>
					<td>
						<a href="<c:url value='/MyPageFront?command=viewLike&requestNum=${dto.requestNum}'/>">
							${dto.title}
						</a>
					</td>
					<td>${dto.status == 0 ? 'Completed' : 'In Progress'}</td>
					<td>${dto.postDate}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br>
	<div>
		<c:if test="${lvo.pagingBean.previous == true}">
			<a href="<c:url value='/MyPageFront?command=MyLike&pageNo=${lvo.pagingBean.startPage - 1}'/>">
				Previous
			</a>
		</c:if>
		&nbsp;&nbsp;
		<c:forEach var="page" begin="${lvo.pagingBean.startPage}"
			end="${lvo.pagingBean.endPage}">
			<c:choose>
				<c:when test="${page == lvo.pagingBean.pageNo}">
                    ${page}
                </c:when>
				<c:otherwise>
					<a href="<c:url value='/MyPageFront?command=MyLike&pageNo=${page}'/>">
						${page}
					</a>
				</c:otherwise>
			</c:choose>
            &nbsp;&nbsp;
        </c:forEach>
		<c:if test="${lvo.pagingBean.next == true}">
			<a href="<c:url value='/MyPageFront?command=MyLike&pageNo=${lvo.pagingBean.endPage + 1}'/>">
				Next
			</a>
		</c:if>
	</div>
</body>
</html>
