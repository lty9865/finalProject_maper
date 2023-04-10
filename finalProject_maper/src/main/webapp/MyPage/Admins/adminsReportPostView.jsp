<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="/Common/link.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/Resources/css/myPage.css">
<title>Request Post View</title>
<script type="text/javascript">
	var contextPath = "${pageContext.request.contextPath}";
</script>
<script src="${pageContext.request.contextPath}/Resources/javascript/reportPostView.js"></script>
</head>
<body onload="showErrorAndGoBack()">
	<!-- error msg check -->
	<div style="display:none" id="error-message">
	   <c:out value="${requestScope.error}" />
	</div>
	
	<!-- header -->
	<%@ include file="/WEB-INF/views/Common/header.jsp"%>
	
	<!-- body/menu -->
	<jsp:include page="/MyPage/adminsMenu.jsp" />

	<!-- body/main -->
	<div class="maper-body">
		<h3 align="left">Details of ${wDTO.bookTitle}</h3>
		<ul class="list-group detailList">
			<li class="list-group-item"><strong>신고 번호</strong></li>
			<li class="list-group-item">${wDTO.reportNum}</li>
			<li class="list-group-item"><strong>책 제목</strong></li>
			<li class="list-group-item">${wDTO.bookTitle}</li>
			<li class="list-group-item"><strong>작성자</strong></li>
			<li class="list-group-item">${wDTO.userId}</li>
			<li class="list-group-item"><strong>신고 횟수</strong></li>
			<li class="list-group-item"><pre>${wDTO.count}</pre></li>
	
			<c:if test="${fn:substringBefore(sessionScope.userId_admins, '_') == 'admins' and fn:substringAfter(sessionScope.userId_admins, '_') == '1'}">
				<div class="text-end">
					<form id="deleteForm" class="d-inline-block"
						action="${pageContext.request.contextPath}/MyPage/MyPageFront?command=Admins.reportDelete"
						method="POST">
						<input type="hidden" name="reportNum" value="${wDTO.reportNum}" />
						<input type="button" class="btn btn-danger" value="처리 완료"
							onclick="confirmDelete();" />
					</form>
				</div>
			</c:if>
		</ul>
	</div>

	<!-- footer -->
	<%@ include file="/WEB-INF/views/Common/footer.jsp"%>
</body>
</html>
