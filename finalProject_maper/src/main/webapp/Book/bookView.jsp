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
		<%=session.getAttribute("bookNum") %>
		<%=session.getAttribute("title") %>
		어플리케이션 :
		<%=application.getAttribute("userId")%>
	</p>

	<!-- header -->
	<%@ include file="../Common/header.jsp"%>

	<form method="post">
		<input type="hidden" value="${ dto.bookNum }" name="bookNum">
		<input type="hidden" value="${ dto.title }" name="title">
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

		<c:if test="${ sessionScope.userId eq dto.userId }">
			<c:set scope="session" var="allow" value="1"></c:set>
			<input type="button" onclick="location.href='../Page/page.do?command=pageWriteView';" value="페이지 작성하기">
			<input type="button" onclick="location.href='../Book/book.do?command=bookEditView';" value="수정하기">
			<input type="button" onclick="bookDelete(${dto.bookNum})" value="삭제하기">
		</c:if>
		<input type="button" onclick="location.href='../Book/book.do?command=bookList';" value="돌아가기">
		<input type="button" onclick="location.href='../Page/page.do?command=pageList';" value="페이지리스트보기">
		<input type="button" onclick="location.href='../Page/page.do?command=pageView&idx=${dto.bookNum}';" value="페이지출력">
	</form>
	<script type="text/javascript" src="../Resources/javascript/book.js"></script>
</body>
</html>