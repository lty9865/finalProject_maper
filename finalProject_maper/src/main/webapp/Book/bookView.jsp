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
					<div class="col-4">사진</div>
					<div class="col-8" align="left">
						<p>
							번호 : ${ bookDTO.bookNum }<br> 작성자 : ${ bookDTO.userId }<br>
							제목 : ${ bookDTO.title }<br> 장소 : ${ bookDTO.country } +
							${bookDTO.city}<br> 일자 : ${ bookDTO.bookDate }<br> 차단 :
							${ bookDTO.block }<br> 만족도 : ${ bookDTO.rate }<br> 조회수
							: ${ bookDTO.visitCount }<br> 좋아요 : ${ bookDTO.likesCount }<br>
							이미지명 : ${ bookDTO.sfile }<br> ${ bookDTO.ofile }
						</p>
					</div>
				</div>
			</div>
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
									<button type="button" onclick="emptySession()">페이지
										작성하기</button>
								</c:when>
								<c:otherwise>
									<button type="button" class="btn btn-outline-primary"
										onclick="location.href='../Page/page.do?command=pageWriteView';">페이지
										작성하기</button>
								</c:otherwise>
							</c:choose>
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