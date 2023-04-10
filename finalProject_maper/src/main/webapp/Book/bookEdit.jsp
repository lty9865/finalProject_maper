<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../Common/link.jsp"%>
<%
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);
response.setHeader("Cache-Control", "no-cache");
%>
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
	<div class="maper-body">
		<div class="table maper-body" id="pageTitle">
			<h2>책 수정</h2>
			<hr>
		</div>
		<form action="../Book/book.do?command=bookEdit&idx=${bookDTO.bookNum}"
			name="bookEditFrm" method="post" enctype="multipart/form-data"
			onsubmit="return bookBlank()">
			<input type="hidden" name="prevOfile" value="${ bookDTO.ofile }" />
			<input type="hidden" name="prevSfile" value="${ bookDTO.sfile }" />
			<div class="bookWrite">
				<div class="mapers-book-inputbar" style="padding-left: 20px;">
					<label class="main-inputbar" for="title">제목</label> <input
						class="main-inputbar" style="width: 85%;" type="text" name="title"
						id="title" value="${ bookDTO.title }">
				</div>
				<div class="parent">
					<div class="mapers-book-inputbar child" style="padding-left: 20px;">
						<label class="main-inputbar" for="country">국가</label> <select
							name="country" style="width: 70%; padding-left: 20px;">
							<c:choose>
								<c:when test="${ empty bookDTO.country }">
									<option selected></option>
								</c:when>
								<c:otherwise>
									<option selected>${ bookDTO.country }</option>
								</c:otherwise>
							</c:choose>
							<option>국가1</option>
							<option>국가2</option>
							<option>국가3</option>
						</select>
					</div>
					&nbsp;
					<div class="mapers-book-inputbar child" style="padding-left: 20px;">
						<label class="main-inputbar" for="city">도시</label> <input
							class="main-inputbar" style="width: 70%; padding-left: 20px;"
							type="text" id="city" name="city" value="${ bookDTO.city }">
					</div>
				</div>
				<div class="input-group mb-3" id="fileUploads">
					<label class="input-group-text" for="inputGroupFile01">Upload</label>
					<input type="file" class="form-control" id="inputGroupFile01"
						name="ofile" value="${ bookDTO.ofile }" />
				</div>
			</div>
			<br>
			<c:choose>
				<c:when test="${ empty sessionScope.userId }">
					<button type="button" class="btn btn-primary"
						onclick="LoginConfirmed()">작성완료</button>
				</c:when>
				<c:otherwise>
					<button type="submit" class="btn btn-primary">작성완료</button>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${ empty sessionScope.userId }">
					<button type="button" class="btn btn-primary"
						onclick="emptySession()">돌아가기</button>
				</c:when>
				<c:otherwise>
					<button type="button" class="btn btn-primary"
						onclick="location.href='../Book/book.do?command=bookView&idx=${bookDTO.bookNum}';">돌아가기</button>
				</c:otherwise>
			</c:choose>
		</form>
	</div>
	<script type="text/javascript" src="../Resources/javascript/book.js"></script>
	<c:if test="${ empty sessionScope.userId }">
		<script type="text/javascript">
			function CheckSession() {
				if (sessionStorage.getItem("userId") == null) {
					alert("로그인 정보가 만료되어 로그인페이지로 이동합니다.");
					window.location = "/Common/logOutProcess.jsp";
				}
			}

			setInterval(CheckSession(), 100);
		</script>
	</c:if>
</body>
</html>