<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 각종 링크 헤더 include -->
<%@ include file="/Common/link.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="../../Resources/javascript/signUp.js"></script>
<script>
	function verifyEmail() {
	    // Check if the email is already verified
	    let emailVerified = document.getElementById("emailVerified").value;
	    if (emailVerified === "true") {
	        alert("Your email has already been verified.");
	        return;
	    }

		
		let email = document.getElementById("Email").value;
		if (email.length == 0) {
			alert("이메일을 입력해주세요.");
		} else {
			let xhr = new XMLHttpRequest();
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4 && xhr.status == 200) {
					// Open a new window for the email verification form
					window
							.open(
									"${pageContext.request.contextPath}/Member/SignUp/verifyEmail.jsp",
									"EmailVerification", "width=500,height=300");
				}
			};
			xhr
					.open(
							"POST",
							"${pageContext.request.contextPath}/Member/SignUp/VerifyEmailServlet",
							true);
			xhr.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
			xhr.send("email=" + encodeURIComponent(email));
		}
	}
</script>
</head>
<body>
	<!-- header -->
	<%@ include file="/WEB-INF/views/Common/header.jsp"%>

	<!-- body -->
	<div class="maper-body">
		<h2>회원가입</h2>
		<hr>
		<div class="maper-body-signup"
			style="padding-top: 20px; padding-bottom: auto;">
			<form action="SignUp.do" method="post" name="signUpFrm"
				onsubmit="return signUpBlank()">
				<div>
					<label for="userId" class="form-label">ID</label>
					<div class="parent">
						<div class="child-2">
							<input type="text" id="userId" class="form-control"
								aria-labelledby="passwordHelpBlock" name="userId">
						</div>
						<div class="child">
							<input type="hidden" name="reid" size="20">
							<button class="btn btn-primary" type="button" onclick="idCheck()">중복확인</button>
						</div>
					</div>
					<div id="passwordHelpBlock" class="form-text">5글자 이상 10글자 이하
						입력해 주세요.</div>
					<hr>
					<label for="inputPassword" class="form-label">Password</label> <input
						type="password" id="inputPassword" class="form-control"
						aria-labelledby="passwordHelpBlock" name="PWD">
					<div id="passwordHelpBlock" class="form-text">10자리 ~ 20자리 이내로
						입력해주세요.</div>
					<div id="passwordHelpBlock" class="form-text">비밀번호는 공백 없이
						입력해주세요.</div>
					<div id="passwordHelpBlock" class="form-text">영문,숫자, 특수문자 중
						2가지 이상을 혼합하여 입력해주세요.</div>
					<br> <label for="inputPassword2" class="form-label">Confirm
						Password</label> <input type="password" id="inputPassword2"
						class="form-control" aria-labelledby="passwordHelpBlock"
						name="PWD2">
					<div id="passwordHelpBlock" class="form-text">다시 한번 비밀번호를
						입력해주세요.</div>
					<hr>
					<label for="Email" class="form-label">Email</label>
					<div class="parent">
						<div class="child-2">
							<input type="hidden" id="emailVerified" name="emailVerified" value="false"/>
 							<input type="email" id="Email" class="form-control"
								aria-labelledby="passwordHelpBlock" name="EMAIL">
						</div>
						<div class="child">
							<button class="btn btn-primary" type="button"
								onclick="verifyEmail()">인증하기</button>
						</div>
					</div>
					<div id="passwordHelpBlock" class="form-text">이메일을 입력해주세요.</div>
					<hr>
				</div>
				<div align="center">
					<button class="btn btn-primary" type="submit">가입완료</button>
					<button class="btn btn-primary" type="button"
						onclick="location.href='../Login/login.jsp';">가입취소</button>
				</div>
			</form>
		</div>
	</div>

	<!-- footer -->
	<%@ include file="/WEB-INF/views/Common/footer.jsp"%>
</body>
</html>