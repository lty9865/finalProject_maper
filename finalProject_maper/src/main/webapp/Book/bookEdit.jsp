<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

	<!-- body -->
	<div class="maper-body">
		<div class="table maper-body" id="pageTitle">
			<h2>책 수정</h2>
			<hr>
		</div>
		<form action="../Book/book.do?command=bookEdit&idx=${dto.bookNum}" name="bookEditFrm" method="post"
			enctype="multipart/form-data" onsubmit="">
			<!-- 임시계정 -->
			<input type="hidden" name="userId" value="green1234"> <input
				type="hidden" name="idx" value="${ dto.bookNum }" /> <input
				type="hidden" name="prevOfile" value="${dto.ofile }" /> <input
				type="hidden" name="prevSfile" value="${dto.sfile }" />
			<div class="bookWrite">
				<div class="mapers-book-inputbar" style="padding-left: 20px;">
					<label class="main-inputbar" for="title">제목</label> <input
						class="main-inputbar" style="width: 85%;" type="text" name="title"
						id="title" value="${ dto.title }">
				</div>
				<div class="parent">
					<div class="mapers-book-inputbar child" style="padding-left: 20px;">
						<label class="main-inputbar" for="country">국가</label>
						<select	name="country" style="width: 70%; padding-left: 20px;">
							<c:choose>
								<c:when test="${ empty dto.country }">
									<option selected></option>
								</c:when>
								<c:otherwise>
									<option selected>${ dto.country }</option>
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
							type="text" id="city" name="city" value="${ dto.city }">
					</div>
					&nbsp;
					<div class="mapers-book-inputbar child" style="padding-left: 20px;">
						<label class="main-inputbar" for="date">일자</label> <input
							class="main-inputbar" style="width: 70%; padding-left: 20px;"
							type="date" id="date" name="bookDate" value="${dto.bookDate }">
					</div>
				</div>
				<div class="input-group mb-3" id="fileUploads">
					<label class="input-group-text" for="inputGroupFile01">Upload</label>
					<input type="file" class="form-control" id="inputGroupFile01"
						name="ofile" />
				</div>
			</div>
			<button type="submit" class="btn btn-primary">전송</button>
			<button type="button" class="btn btn-primary" onclick="location.href='../Book/book.do?command=bookView&idx=${dto.bookNum}';">돌아가기</button>
		</form>
	</div>
</body>
</html>