<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<title>MAPER</title>
<script type="text/javascript" src="../Common/LoginConfirmed.js"></script>
</head>
<body>


	<a href="${pageContext.request.contextPath}/Member/Login/login.jsp">로그인 페이지로 가기</a>
	<br>
	<a href="Notice/notice.do" onclick="return LoginConfirmed()">공지사항
		페이지로 가기</a>
	<br>
	<a href="Notice/notice.do?command=list">공지사항 페이지로 가기2</a>
	<br>
	<a href="Webmain/mainPage.do?command=main">메인 페이지로 가기</a>
	<br>
	<a href="Book/bookList.do">북 리스트로 가기</a>
	<br>
	<a href="Page/pageList.do">페이지 리스트로 가기</a>
</body>
</html>
