<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 HOME</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/myhome.css"/>

</head>
<body>
	<div class="container-fluid">
	<%-- "row", "col-[size]-[num] : 한 행의 열을 잡아줌 --%>
		<%-- Header 영역 --%>
		<div class="row header">
			<%-- JSTL import --%>
			<c:import url="/MyPage.Common/header.jsp"></c:import>
		</div>
		<%-- Main 화면 영역 --%>
		<div class="row main">
			<%-- Main 동작 영역 --%>			
			<div class="col-sm-8 col-sm-offset-2">
			<%--
				메인 화면에 대한 view 정보(url or jsp파일명)를 컨트롤러에서
				동적으로 할당받는다 
			 --%>
			 <c:import url="${requestScope.url}"/>
			</div>
		</div>
	</div>
</body>
</html>