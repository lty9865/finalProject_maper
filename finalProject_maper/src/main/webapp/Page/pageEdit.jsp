<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/Resources/css/star.css">
</head>
<body>
	<!-- header -->
	<%@ include file="/WEB-INF/views/Common/header.jsp"%>

	<!-- body -->
	<div class="maper-body">
		<div class="table maper-body" id="pageTitle">
			<h2>페이지 수정</h2>
			<hr>
		</div>
		<form action="../Page/page.do?command=pageEdit" method="post"
			enctype="multipart/form-data" name="pageEditFrm"
			onsubmit="return pageBlank()">
			<input type="hidden" name="prevOfile" value="${ dto.ofile }" /> <input
				type="hidden" name="prevSfile" value="${ dto.sfile }" /> <input
				type="hidden" value="${ dto.pageNum }" name="pageNum">
			<div class="bookWrite">
				<div class="mapers-book-inputbar" style="padding-left: 20px;">
					<label class="main-inputbar">제목&nbsp;&nbsp;</label> <label
						class="main-inputbar" style="width: 85%; padding-left: 20px;">${ bookDTO.title }</label>
				</div>
				<div class="parent">
					<div class="mapers-book-inputbar child" style="padding-left: 20px;">
						<label class="main-inputbar" for="subTitle">부제목</label> <input
							class="main-inputbar" style="width: 80%; padding-left: 20px;"
							type="text" id="subTitle" name="subTitle"
							value="${ dto.subTitle }">
					</div>
					&nbsp;
					<div class="mapers-book-inputbar child" style="padding-left: 20px;">
						<label class="main-inputbar" for="date">일자</label> <input
							class="main-inputbar" style="width: 80%; padding-left: 20px;"
							type="date" id="date" name="pageDate" value="${ dto.postDate }">
					</div>
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
			<br>
			<c:choose>
				<c:when test="${ empty sessionScope.userId }">
					<button type="button" class="btn btn-primary"
						onclick="emptySession()">전송</button>
				</c:when>
				<c:otherwise>
					<button type="submit" class="btn btn-primary">전송</button>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${ empty sessionScope.userId }">
					<button type="button" class="btn btn-primary"
						onclick="emptySession()">돌아가기</button>
				</c:when>
				<c:otherwise>
					<button type="button" class="btn btn-primary"
						onclick="location.href='../Page/page.do?command=pageList&idx=${ bookDTO.bookNum }';">돌아가기</button>
				</c:otherwise>
			</c:choose>
		</form>
	</div>
	<script src="../Resources/javascript/star.js"></script>
	<script type="text/javascript"
		src="../Resources/javascript/pageScript.js"></script>
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