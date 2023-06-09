<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/Common/link.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
		<h2>공지사항</h2>
		<hr>
	</div>
	<form action="../Notice/notice.do?command=editView&idx=${ param.idx }"
		method="post">
		<table class="maper-body table table-striped-columns">
			<colgroup>
				<col width="15%" />
				<col width="35%" />
				<col width="15%" />
				<col width="*" />
			</colgroup>

			<tr>
				<td>번호</td>
				<td>${ dto.idx }</td>
				<td>작성자</td>
				<td>관리자</td>
			</tr>
			<tr>
				<td>작성일</td>
				<td>${ dto.postDate }</td>
				<td>조회수</td>
				<td>${ dto.visitCount }</td>
			</tr>
			<tr>
				<td>제목</td>
				<td colspan="3">${ dto.title }</td>
			</tr>
			<tr>
				<td>내용</td>
				<td colspan="3" height="100">${ dto.content }</td>
			</tr>
		</table>
		<table class="maper-body" style="margin-top: 30px;">
			<tr align="center">
				<td>
					<div class="btn-group" role="group" aria-label="Basic example">
						<button type="button" class="btn btn-primary"
							style="width: 150px;"
							onclick="location.href='../Notice/notice.do?command=list';">돌아가기</button>
						<c:if
							test="${fn:substringBefore(sessionScope.userId, '_') == 'admins' and fn:substringAfter(sessionScope.userId, '_') == '1'}">
							<c:choose>
								<c:when test="${ empty sessionScope.userId }">
									<button type="button" class="btn btn-primary"
										style="width: 150px;" onclick="emptySession()">수정하기</button>
								</c:when>
								<c:otherwise>
									<button type="submit" class="btn btn-primary"
										style="width: 150px;" onclick="return noticeBlank()">수정하기</button>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${ empty sessionScope.userId }">
									<button type="button" class="btn btn-primary"
										style="width: 150px;" onclick="emptySession()">삭제하기</button>
								</c:when>
								<c:otherwise>
									<button type="button" class="btn btn-primary"
										style="width: 150px;" onclick="noticeDelete(${param.idx})">삭제하기</button>
								</c:otherwise>
							</c:choose>
						</c:if>
					</div>
				</td>
			</tr>
		</table>
	</form>
	<script type="text/javascript" src="../Resources/javascript/notice.js"></script>
</body>
</html>