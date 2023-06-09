<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../Common/link.jsp"%>
<%
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);
response.setHeader("Cache-Control", "no-cache");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- header -->
	<%@ include file="/WEB-INF/views/Common/header.jsp"%>

	<!-- body -->
	<div class="maper-body">
		<div class="table maper-body" id="pageTitle">
			<h2>페이지 목록</h2>
			<hr>
		</div>
		<table class="table table-hover boardlist" >
			<tr align="center">
				<th scope="col">순서</th>
				<th scope="col">제목</th>
				<th scope="col">일자</th>
				<th scope="col">만족도</th>
				<th scope="col">이미지</th>
				<th scope="col" colspan="2"></th>
			</tr>
			<c:choose>
				<c:when test="${ empty pageList }">
					<tr>
						<td colspan="5">게시물이 없습니다.</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${ pageList }" var="row" varStatus="loop">
						<tr align="center">
							<th scope="row">${ map.totalCount - (((map.pageNums-1)*map.pageSize)+loop.index) }</th>
							<td>${ row.subTitle }</td>
							<td>${ row.postDate }</td>
							<td>${ row.rate }</td>
							<td>${ row.sfile }</td>
							<c:if test="${ sessionScope.allow eq 1 }">
								<td><c:choose>
										<c:when test="${ empty sessionScope.userId }">
											<a href="#" onclick="emptySesssion()">[ 수정 ]</a>
										</c:when>
										<c:otherwise>
											<a
												href="../Page/page.do?command=pageEditView&idx=${ row.pageNum }">[
												수정 ]</a>
										</c:otherwise>
									</c:choose></td>
								<td><c:choose>
										<c:when test="${ empty sessionScope.userId	 }">
											<a href="#" onclick="emptySession()">[ 삭제 ]</a>
										</c:when>
										<c:otherwise>
											<a href="#" onclick="pageDelete(${row.pageNum})">[ 삭제 ]</a>
										</c:otherwise>
									</c:choose></td>
							</c:if>
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
		<br>
		<div class="btn-group" role="group"
			aria-label="Basic outlined example">
			<c:if test="${ sessionScope.allow eq 1 }">
				<c:choose>
					<c:when test="${ empty sessionScope.userId }">
						<button type="button" class="btn btn-outline-primary"
							onclick="emptySession()">페이지 작성하기</button>
					</c:when>
					<c:otherwise>
						<button type="button" class="btn btn-outline-primary"
							onclick="location.href='../Page/page.do?command=pageWriteView';">페이지
							작성하기</button>
					</c:otherwise>
				</c:choose>
			</c:if>
			<button type="button" class="btn btn-outline-primary"
				onclick="location.href='../Book/book.do?command=bookView&idx=${ bookDTO.bookNum }';">돌아가기</button>
		</div>
	</div>
	<script type="text/javascript"
		src="../Resources/javascript/pageScript.js"></script>
</body>
</html>