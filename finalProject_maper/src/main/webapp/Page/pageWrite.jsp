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
		세션 :
		<%=session.getAttribute("userId")%>
		<%=session.getAttribute("bookNum") %>
		<%=session.getAttribute("title") %>
		어플리케이션 :
		<%=application.getAttribute("userId")%>
	</p>

	<!-- header -->
	<%@ include file="../Common/header.jsp"%>
	
	<!-- body -->
	<div class="maper-body">
		<div class="table maper-body" id="pageTitle">
			<h2>페이지 작성</h2>
			<hr>
		</div>
		<form action="../Page/page.do?command=pageWrite" method="post"
			enctype="multipart/form-data">
			<div class="bookWrite">
				<div class="mapers-book-inputbar" style="padding-left: 20px;">
					<input type="hidden" value="${ sessionScope.bookNum }" name="bookNum">
					<label class="main-inputbar">제목&nbsp;&nbsp;</label>
					<label class="main-inputbar" style="width: 85%; padding-left: 20px;" >${ sessionScope.title }</label>
				</div>
				<div class="mapers-book-inputbar" style="padding-left: 20px;">
					<label class="main-inputbar" for="subTitle">부제목</label> <input
						class="main-inputbar" style="width: 85%; padding-left: 20px;"
						type="text" id="subTitle" name="subTitle">
				</div>
				<div class="input-group mb-3" id="fileUploads">
					<label class="input-group-text" for="inputGroupFile01">Upload</label>
					<input type="file" class="form-control" id="inputGroupFile01"
						name="ofile">
				</div>
				<div class="mapers-book-inputbar" style="padding-left: 20px;">
					<label class="main-inputbar" for="subTitle">만족도</label>
					<div class="rating_box">
						<div class="rating">
							★★★★★ <span class="rating_star">★★★★★</span> <input type="range"
								value="1" step="1" min="0" max="5" name="rate">
						</div>
					</div>
				</div>
				<div class="mapers-book-inputbar">
					<textarea rows="20" cols="80" class="main-inputbar"
						style="margin-left: 20px;" name="content"></textarea>
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