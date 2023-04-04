<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/Common/link.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- header -->
	<div class="maper-header">
		<div class="maper-header-font">
			<div class="left">
				<a class="maper-header-font-1" href="login.jsp">MAPER</a>
			</div>
			<div class="right maper-header-font-2" align="right">
				<button type="button" class="btn btn-outline-primary" id="login">로그인</button>
			</div>
		</div>
	</div>
	<!-- body -->
	<div class="maper-body">
		<div class="table maper-body" id="pageTitle">
			<h2>페이지 목록</h2>
			<hr>
		</div>
		<table border="1" style="width: 100%">
			<tr>
				<th scope="col">순서</th>
				<th scope="col">번호</th>
				<th scope="col">책번호</th>
				<th scope="col">제목</th>
				<th scope="col">일자</th>
				<th scope="col">만족도</th>
				<th scope="col">이미지</th>
			</tr>
			<c:choose>
				<c:when test="${ empty pageList }">
					<tr>
						<td colspan="5">게시물이 없습니다.</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${ pageList }" var="row" varStatus="loop">
						<tr>
							<th scope="row">${ map.totalCount - (((map.pageNum-1)*map.pageSize)+loop.index) }</th>
							<td>${ row.pageNum }</td>
							<td>${ row.bookNum }</td>
							<td>${ row.subTitle }</td>
							<td>${ row.postDate }</td>
							<td>${ row.rate }</td>
							<td>${ row.sfile }</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
		<table class="table maper-body">
			<tr align="center">
				<td>${ map.pagingImg }</td>
			</tr>
		</table>
		<input type="button" onclick="../Book/bookwrite.do">
	</div>
</body>
</html>