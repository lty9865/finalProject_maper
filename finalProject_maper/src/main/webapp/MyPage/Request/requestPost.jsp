<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/Common/link.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/Resources/css/myPage.css">
<title>Request Post</title>
<script>
	function resetFields() {
		document.getElementsByName("title")[0].value = "";
		document.getElementsByName("content")[0].value = "";
	}

	window.onload = function() {
		var error = "${error}";
		if (error) {
			alert(error);
		}
	}
</script>
</head>
<body>
	<!-- header -->
	<%@ include file="/WEB-INF/views/Common/header.jsp"%>

	<!-- body/menu -->
	<jsp:include page="../menu.jsp" />

	<!-- body/main -->
	<c:set var="selectedRequest" value="${requestScope.selectedRequest}" />

	<form
		action="${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyRequest.requestPostProcess"
		method="POST">
		<input type="hidden" name="command" value="WritePost">
		<table class="table">
			<tr>
				<td>
					<h4>제목</h4> <input type="text" name="title"
					value="${selectedRequest.title}" placeholder="게시글 제목을 입력하세요"
					required="required"> <input type="hidden"
					name="originalTitle" value="${selectedRequest.title}" />
				</td>
			</tr>
			<tr>
				<td>
					<h4>작성자</h4> <input type="text" name="userId"
					value="${sessionScope.userId}" readonly required="required">
				</td>
			</tr>
			<tr>
				<td>
					<h4>본문 내용</h4> <textarea cols="90" rows="15" name="content"
						required="required" placeholder=" 본문내용을 입력하세요">${selectedRequest.content}</textarea>
				</td>
			</tr>
			<tr>
				<td>
					<div class="btnArea">
						<button type="submit" class="btn-success">작성 완료</button>
						<button type="reset" class="btn-warning" onclick="resetFields()">다시
							쓰기</button>
					</div>
				</td>
			</tr>
		</table>
	</form>

	<!-- footer -->
	<%@ include file="/WEB-INF/views/Common/footer.jsp"%>
</body>
</html>
