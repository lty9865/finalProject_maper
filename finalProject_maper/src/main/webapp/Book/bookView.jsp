<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../Common/link.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="../Resource/css/myPage.css">
</head>
<body>
	<!-- header -->
	<%@ include file="/WEB-INF/views/Common/header.jsp"%>

	<!-- body -->
	<div class="table maper-body" id="pageTitle">
		<h2>상세보기</h2>
		<hr>
	</div>
	<div class="maper-body">
		<form method="post">
			<input type="hidden" value="${ bookDTO.bookNum }" name="bookNum">
			<input type="hidden" value="${ bookDTO.title }" name="title">
			<div class="container text-center">
				<div class="row">
					<div class="col-4">
						<img alt="" src="../Uploads/Book/${ bookDTO.sfile }"
							style="width: 100%; height: 100%; object-fit: cover;">
					</div>
					<div class="col-8" align="left" style="height: 400px; vertical-align: middle; margin: auto;">
						<div class="row" align="left">
							<div class="col">작성자 :&nbsp;</div>
							<div class="col">${ bookDTO.userId }</div>
						</div>
						<hr class="blue-line">
						<div class="row" align="left">
							<div class="col">제목 :&nbsp;</div>
							<div class="col">${ bookDTO.title }</div>
						</div>
						<hr class="blue-line">
						<div class="row" align="left">
							<div class="col">장소 :&nbsp;</div>
							<div class="col">${ bookDTO.country}&nbsp;${ bookDTO.city }</div>
						</div>
						<hr class="blue-line">
						<div class="row" align="left">
							<div class="col">작성일자 :&nbsp;</div>
							<div class="col">${ bookDTO.bookDate }</div>
						</div>
						<hr class="blue-line">
						<div class="row" align="left">
							<div class="col">만족도 :&nbsp;</div>
							<div class="col">${ bookDTO.rate }</div>
						</div>
						<hr class="blue-line">
						<div class="row" align="left">
							<div class="col">조회수 :&nbsp;</div>
							<div class="col">${ bookDTO.visitCount }</div>
						</div>
						<hr class="blue-line">
						<div class="row" align="left">
							<div class="col">좋아요 :&nbsp;</div>
							<div class="col">${ bookDTO.likesCount }</div>
						</div>
					</div>
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col" align="left">
					<div class="btn-group" role="group"
						aria-label="Basic outlined example">
						<button type="button" class="btn btn-outline-primary"
							onclick="location.href='../Book/book.do?command=bookList';">돌아가기</button>
						<button type="button" class="btn btn-outline-primary"
							onclick="location.href='../Page/page.do?command=pageList&idx=${bookDTO.bookNum}';">페이지리스트보기</button>
						<button type="button" class="btn btn-outline-primary"
							onclick="location.href='../Page/page.do?command=pageView&idx=${bookDTO.bookNum}';">페이지출력</button>
					</div>
				</div>
				<div class="col" align="right">
					<div class="btn-group" role="group"
						aria-label="Basic outlined example">
						<c:if test="${ sessionScope.allow eq 1}">
							<c:choose>
								<c:when test="${ empty sessionScope.userId }">
									<button type="button" onclick="emptySession()">수정하기</button>
								</c:when>
								<c:otherwise>
									<button type="button" class="btn btn-outline-primary"
										onclick="location.href='../Book/book.do?command=bookEditView';">수정하기</button>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${ empty sessionScope.userId }">
									<button type="button" onclick="emptySession()">삭제하기</button>
								</c:when>
								<c:otherwise>
									<button type="button" class="btn btn-outline-primary"
										onclick="bookDelete(${bookDTO.bookNum})">삭제하기</button>
								</c:otherwise>
							</c:choose>
						</c:if>
					</div>
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript" src="../Resources/javascript/book.js"></script>
</body>
</html>