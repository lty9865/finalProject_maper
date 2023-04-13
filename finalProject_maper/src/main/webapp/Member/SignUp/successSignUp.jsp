<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/Common/link.jsp"%>
<title>SuccessSingUp</title>
<script type="text/javascript"
	src="../../Resources/javascript/Member.js"></script>
</head>
<body class="maper-body-background">
	<!-- header -->
	<%@ include file="/WEB-INF/views/Common/header.jsp"%>

	<!-- body -->
	<div class="maper-body">
		<div align="center" style="margin: 200px;">
			<h1 class="display-5 fw-bold">회원가입을 축하합니다!</h1>
			<br>MAPER와 함께 추억을 공유해보세요.<br>
			<br>
			<hr style="border: solid 1px black;">
			<div class="col-lg-6 mx-auto">
				<p class="lead mb-4">LICENSE KEY</p>
				<p class="mb-3">${licenseKey}<br>
					<br>계정정보 분실 시 사용될 KEY입니다.
				</p>
				<div class="d-grid gap-2 col-7 mx-auto">
					<button type="button" class="btn btn-primary btn-lg"
						onclick="location.href='${pageContext.request.contextPath}/Member/Login/login.jsp';">로그인</button>

				</div>
			</div>
		</div>
	</div>
</body>
</html>