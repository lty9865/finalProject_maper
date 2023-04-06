<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/Common/link.jsp"%>

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
	<div class="table maper-body" id="pageTitle">
		<h2>공지사항</h2>
		<hr>
		<table class="table table-hover maper-body">
			<thead>
				<tr align="center">
					<th scope="col">No</th>
					<th scope="col">제목</th>
					<th scope="col">작성일</th>
					<th scope="col">조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${ empty noticeLists }">
						<tr>
							<td colspan="4" align="center">등록된 게시물이 없습니다.</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${ noticeLists }" var="row" varStatus="loop">
							<tr align="center">
								<th scope="row">${ map.totalCount - (((map.pageNum-1)*map.pageSize)+loop.index) }</th>
								<td><a
									href="../Notice/notice.do?command=view&idx=${ row.idx }">${ row.title }</a></td>
								<td>${ row.postDate }</td>
								<td>${ row.visitCount }</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
		<c:if test="${ sessionScope.admis eq 1}">
			<table class="table maper-body">
				<tr align="center">
					<td align="right">
						<button type="button" class="btn btn-primary"
							onclick="NoticeListLoginConfirmed()">글쓰기</button>
					</td>
				</tr>
			</table>
		</c:if>
		<table class="table maper-body">
			<tr align="center">
				<td>${ map.pagingImg }</td>
			</tr>
		</table>
		<!-- 검색 폼 -->
		<form method="post" action="../Notice/notice.do?command=list">
			<div class="input-group" style="margin-top: 10px;">
				<select class="form-select circle" id="inputGroupSelect04"
					aria-label="Example select with button addon">
					<option value="title">제목</option>
					<option value="place">장소</option>
				</select> <input class="form-control" type="text" name="searchWord"
					style="width: 75%" placeholder="검색어를 입력하세요." />
				<button class="btn btn-outline-secondary circle" type="submit"
					style="width: 10%">검색</button>
			</div>
		</form>
	</div>
	<script type="text/javascript" src="../Resources/javascript/notice.js"></script>
</body>
</html>