<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Email Verification</title>
<script>
	function handleSubmitSuccess(message) {
		alert(message);
		if (message == "인증성공") {
			window.close();
		}
	}

	function submitVerificationCode() {
		let verificationCode = document.getElementById("verificationCode").value;
		let xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				handleSubmitSuccess(xhr.responseText);
			}
		};
		xhr
				.open(
						"POST",
						"${pageContext.request.contextPath}/Member/SignUp/EmailVerificationAction.do",
						true);
		xhr.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
		xhr.send("verificationCode=" + encodeURIComponent(verificationCode));
	}
	
	function setEmailVerified() {
		window.opener.document.getElementById("emailVerified").value = "true";
	}
</script>
</head>
<body>
	<h1>이메일 인증</h1>
	<p>작성하신 이메일로 인증번호를 보냈습니다. 하단에 입력해주세요.</p>
	<form
		action="${pageContext.request.contextPath}/Member/SignUp/EmailVerificationAction.do"
		method="post"
		onsubmit="event.preventDefault(); submitVerificationCode();">
		<label for="verificationCode">인증번호:</label> <input type="text"
			id="verificationCode" name="verificationCode" required>
		<button type="submit">인증</button>
	</form>
</body>
</html>
