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
			<h2>책 목록</h2>
			<hr>
		</div>
		<div class="container text-center">
			<div class="row row-cols-auto">
				<c:choose>
					<c:when test="${ empty bookList }">
						<div class="col">게시물이 없습니다.</div>
					</c:when>
					<c:otherwise>
						<c:forEach items="${ bookList }" var="row" varStatus="loop">
							<div class="col">
								<div class="card" style="width: 18rem; margin: 0 auto 10px auto">
									<img src="../Uploads/Book/${ row.sfile }" class="card-img-top"
										alt="...">
									<div class="card-body">
										<h5 class="card-title">${ row.title }</h5>
										<a href="../Book/bookView.do?idx=${ row.bookNum }"
											class="btn btn-primary">Go somewhere</a>
									</div>
								</div>
							</div>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<table class="table maper-body">
			<tr align="center">
				<td>${ map.pagingImg }</td>
			</tr>
		</table>
		<input type="button" class="btn btn-primary" onclick="location.href='../Book/bookWrite.do';" value="책작성하기">
	</div>
</body>
</html>