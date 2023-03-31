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
	<%@ include file="/WEB-INF/views/Common/header.jsp" %>
	
	<!-- body -->
	<div class="table maper-body" id="pageTitle">
		<h2>공지사항</h2>
		<hr>
	</div>
	<table class="table maper-body">
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
							<td><a href="../Notice/notice.do?command=view&idx=${ row.idx }">${ row.title }</a></td>
							<td>${ row.postDate }</td>
							<td>${ row.visitCount }</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	<table class="table maper-body">
		<tr align="center">
			<td align="right">
				<button type="button" class="btn btn-primary"
					onclick="location.href='../Notice/notice.do?command=writeView';">글쓰기</button>
			</td>
		</tr>
	</table>
	<table class="table maper-body">
		<tr align="center">
			<td>${ map.pagingImg }</td>
		</tr>
	</table>
	<!-- 검색 폼 -->
	<form method="get">
		<table class="table maper-body">
			<tr>
				<td>
					<div class="input-group mb-3">
						<span class="input-group-text" id="inputGroup-sizing-default">
							<select class="form-select" aria-label="Default select example"
							name="searchField">
								<option value="title" selected>제목</option>
								<option value="content">내용</option>
						</select>
						</span> <input type="text" class="form-control"
							aria-label="Sizing example input"
							aria-describedby="inputGroup-sizing-default" name="searchWord">
						<input class="input-group-text" type="submit" value="검색하기">
					</div>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>