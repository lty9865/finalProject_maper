<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/Common/link.jsp"%>
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
	<div class="table maper-body" id="pageTitle">
		<h2>공지사항 작성</h2>
		<hr>
	</div>
	<div class="maper-body">
		<form name="writeFrm" method="post"
			action="../Notice/notice.do?command=write">
			<div class="maper-body">
				<div class="maper-book-write" style="margin: 15px auto;">
					<input type="text" class="form-control" name="title"
						placeholder="제목">
					<div style="height: 10px;"></div>
					<textarea class="form-control" rows="10" name="contents"></textarea>
					<div style="height: 10px;"></div>
					<div align="center" style="margin-top: 15px;">
						<c:choose>
							<c:when test="${ empty sessionScope.userId }">
								<button type="button" class="btn btn-primary btn-lg"
									style="width: 120px;" onclick="emptySession()">작성완료</button>
							</c:when>
							<c:otherwise>
								<button type="submit" class="btn btn-primary btn-lg"
									style="width: 120px;" onclick="return noticeBlank()">작성완료</button>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${ empty sessionScope.userId }">
								<button type="button" class="btn btn-primary btn-lg"
									style="width: 120px;" onclick="emptySession()">Cancel</button>
							</c:when>
							<c:otherwise>
								<button type="button" class="btn btn-primary btn-lg"
									style="width: 120px;"
									onclick="noticeDelete()">Cancel</button>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript" src="../Resources/javascript/notice.js"></script>
</body>
</html>