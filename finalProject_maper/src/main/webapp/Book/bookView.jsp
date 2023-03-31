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
	<%@ include file="../Common/header.jsp"%>

	<form method="post">
		<input type="hidden" value="${ dto.bookNum }">
		<p>
			번호 : ${ dto.bookNum }<br>
			작성자 : ${ dto.userId }<br>
			제목 : ${ dto.title }<br>
			장소 : ${dto.country } + ${dto.city}<br>
			일자 : ${dto.bookDate }<br>
			차단 : ${dto.block }<br>
			만족도 : ${dto.rate }<br>
			조회수 : ${dto.visitCount }<br>
			좋아요 : ${dto.likesCount }<br>
			이미지명 : 	${dto.sfile }<br>
			${dto.ofile }
		</p>

		<input type="button" onclick="location.href='../Page/pageWrite.do?idx=${ dto.bookNum }&title=${ dto.title }';" value="페이지 작성하기">
		<input type="button" onclick="location.href='../Book/book.do?command=bookEditView&idx=${ dto.bookNum }';" value="수정하기">
		<input type="button" onclick="location.href='../Book/book.do?command=bookDelete&idx=${ dto.bookNum }';" value="삭제하기">
		<input type="button" onclick="location.href='../Book/book.do?command=bookList';" value="돌아가기">
		<input type="button" onclick="location.href='../Page/page.do?command=pageList&idx=${dto.bookNum}';" value="페이지리스트보기">
		<input type="button" onclick="location.href='../Page/page.do?command=pageView&idx=${dto.bookNum}';" value="페이지출력">
	</form>
</body>
</html>