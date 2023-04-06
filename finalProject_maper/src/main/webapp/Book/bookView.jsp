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
		세션 : ${ sessionScope.userId } <br> 작성자: ${ bookDTO.userId } <br>
		북넘버 : ${ bookDTO.bookNum } <br> 북제목 : ${ bookDTO.title } <br>
		허용 : ${ sessionScope.allow } <br> 좋아요 : ${ bookDTO.likesCount } <br>
		조회수 : ${ bookDTO.visitCount }
	</p>

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
			<p>
				번호 : ${ bookDTO.bookNum }<br> 작성자 : ${ bookDTO.userId }<br>
				제목 : ${ bookDTO.title }<br> 장소 : ${ bookDTO.country } +
				${bookDTO.city}<br> 일자 : ${ bookDTO.bookDate }<br> 차단 : ${ bookDTO.block }<br>
				만족도 : ${ bookDTO.rate }<br> 조회수 : ${ bookDTO.visitCount }<br>
				좋아요 : ${ bookDTO.likesCount }<br> 이미지명 : ${ bookDTO.sfile }<br>
				${ bookDTO.ofile }
			</p>

			<c:if test="${ sessionScope.allow eq 1}">
				<c:choose>
					<c:when test="${ empty sessionScope.userId }">
						<input type="button" onclick="emptySession()" value="페이지 작성하기">
					</c:when>
					<c:otherwise>
						<input type="button"
							onclick="location.href='../Page/page.do?command=pageWriteView';"
							value="페이지 작성하기">
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${ empty sessionScope.userId }">
						<input type="button" onclick="emptySession()" value="수정하기">
					</c:when>
					<c:otherwise>
						<input type="button"
							onclick="location.href='../Book/book.do?command=bookEditView';"
							value="수정하기">
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${ empty sessionScope.userId }">
						<input type="button" onclick="emptySession()" value="삭제하기">
					</c:when>
					<c:otherwise>
						<input type="button" onclick="bookDelete(${bookDTO.bookNum})"
							value="삭제하기">
					</c:otherwise>
				</c:choose>
			</c:if>
			<input type="button"
				onclick="location.href='../Book/book.do?command=bookList';"
				value="돌아가기">
			<input type="button"
				onclick="location.href='../Page/page.do?command=pageList&idx=${bookDTO.bookNum}';"
				value="페이지리스트보기"> 
			<input type="button"
				onclick="location.href='../Page/page.do?command=pageView&idx=${bookDTO.bookNum}';"
				value="페이지출력">
		</form>
	</div>
	<script type="text/javascript" src="../Resources/javascript/book.js"></script>
</body>
</html>