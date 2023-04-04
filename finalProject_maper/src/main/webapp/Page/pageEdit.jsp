<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../Common/link.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/Resources/css/star.css">
</head>
<body>
	<p>
		세션 : ${ sessionScope.userId }
		<br>
		작성자: ${ bookDTO.userId }
		<br>
		북넘버 : ${ bookDTO.bookNum }
		<br>
		북제목 : ${ bookDTO.title }
		<br>
		허용 : ${ sessionScope.allow }
		<br>
		좋아요 : ${ bookDTO.likesCount }
		<br>
		조회수 : ${ bookDTO.visitCount }
	</p>
	<!-- header -->
	<%@ include file="/WEB-INF/views/Common/header.jsp"%>
	
	<!-- body -->
	<div class="maper-body">
		<div class="table maper-body" id="pageTitle">
			<h2>페이지 작성</h2>
			<hr>
		</div>
		<form action="../Page/page.do?command=pageEdit" method="post"
			enctype="multipart/form-data" name="pageWriteFrm" onsubmit="return pageBlank()">
		<input type="hidden" name="prevOfile" value="${ dto.ofile }" />
		<input type="hidden" name="prevSfile" value="${ dto.sfile }" />
		<input type="hidden" value="${ dto.pageNum }" name="pageNum">
			<div class="bookWrite">
				<div class="mapers-book-inputbar" style="padding-left: 20px;">
					<label class="main-inputbar">제목&nbsp;&nbsp;</label>
					<label class="main-inputbar" style="width: 85%; padding-left: 20px;">${ bookDTO.title }</label>
				</div>
				<div class="mapers-book-inputbar" style="padding-left: 20px;">
					<label class="main-inputbar" for="subTitle">부제목</label> <input
						class="main-inputbar" style="width: 85%; padding-left: 20px;"
						type="text" id="subTitle" name="subTitle" value="${ dto.subTitle }">
				</div>
				<div class="input-group mb-3" id="fileUploads">
					<label class="input-group-text" for="inputGroupFile01">Upload</label>
					<input type="file" class="form-control" id="inputGroupFile01"
						name="ofile" value="${ dto.ofile }">
				</div>
				<div class="mapers-book-inputbar" style="padding-left: 20px;">
					<label class="main-inputbar" for="rate">만족도</label>
					<div class="rating_box">
						<div class="rating">
							★★★★★ <span class="rating_star">★★★★★</span> <input type="range"
								value="${ dto.rate }" step="1" min="0" max="5" name="rate">
						</div>
					</div>
				</div>
				<div class="mapers-book-inputbar">
					<textarea rows="20" cols="80" class="main-inputbar"
						style="margin-left: 20px;" name="content">${ dto.content }</textarea>
				</div>
			</div>
			<button type="submit" class="btn btn-primary">전송</button>
			<button type="button" class="btn btn-primary">돌아가기</button>
		</form>
	</div>
	<script src="../Resources/javascript/star.js"></script>
	<script type="text/javascript" src="../Resources/javascript/pageScript.js"></script>
</body>
</html>