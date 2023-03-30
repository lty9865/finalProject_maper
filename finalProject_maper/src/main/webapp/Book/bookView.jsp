<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="../Book/bookDelete.do" method="post">
<input type="hidden" value="${ dto.bookNum }">
<p>
번호 : ${ dto.bookNum }<br>
작성자 : ${ dto.userId }<br>
제목 : ${ dto.title }<br>
장소 : ${dto.place }<br>
일자 : ${dto.bookDate }<br>
차단 : ${dto.block }<br>
만족도 : ${dto.rate }<br>
조회수 : ${dto.visitCount }<br>
좋아요 : ${dto.likesCount }<br>
이미지명 : ${dto.sfile }<br>
</p>

<input type="button" onclick="location.href='../Page/pageWrite.do?idx=${ dto.bookNum }&title=${ dto.title }';" value="페이지 작성하기">
<input type="button" onclick="location.href='../Book/bookEdit.do?idx=${ dto.bookNum }';" value="수정하기">
<input type="submit" onclick="location.href='../Book/bookDelete.do';"value="삭제하기">
<input type="button" onclick="location.href='../Book/bookList.do';" value="돌아가기">
<input type="button" onclick="location.href='../Page/pageList.do?idx=${ dto.bookNum }';" value="페이지보기">
</form>
</body>
</html>