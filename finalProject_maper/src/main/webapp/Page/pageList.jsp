<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../Common/link.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>
		세션 :
		<%=session.getAttribute("userId")%>
		<br>
		북넘버 :
		<%=session.getAttribute("bookNum") %>
		<br>
		북제목 :
		<%=session.getAttribute("title") %>
		<br>
		허용 :
		<%=session.getAttribute("allow") %>
		<br>
		어플리케이션 :
		<%=application.getAttribute("userId")%>
	</p>

	<!-- header -->
	<%@ include file="../Common/header.jsp"%>
	
	<!-- body -->
	<div class="maper-body">
		<div class="table maper-body" id="pageTitle">
			<h2>페이지 목록</h2>
			<hr>
		</div>
		<table border="1" style="width: 100%">
			<tr>
				<th scope="col">순서</th>
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
							<th scope="row">${ map.totalCount - (((map.pageNums-1)*map.pageSize)+loop.index) }</th>
							<td>${ row.pageNum }</td>
							<td>${ row.subTitle }</td>
							<td>${ row.postDate }</td>
							<td>${ row.rate }</td>
							<td>${ row.sfile }</td>
							<c:if test="${ sessionScope.allow eq 1 }">
								<td>
									<a href="../Page/page.do?command=pageEditView&idx=${ row.pageNum }">[ 수정 ]</a>
								</td>
								<td>
									<a href="#" onclick="pageDelete(${row.pageNum})">[ 삭제 ]</a>
								</td>
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
		<c:if test="${ sessionScope.allow eq 1 }">
			<input type="button" onclick="location.href='../Page/page.do?command=pageWriteView';" value="페이지 작성하기">
		</c:if>
		<input type="button" onclick="location.href='../Book/book.do?command=bookView&idx=${ sessionScope.bookNum }';" value="돌아가기">
	</div>
		<script type="text/javascript" src="../Resources/javascript/pageScript.js"></script>
</body>
</html>