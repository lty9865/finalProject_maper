<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Email Verification</title>
<script>
function handleSubmitSuccess(message) {
    alert(message);
    window.close();
}

function submitVerificationCode() {
    let verificationCode = document.getElementById("verificationCode").value;
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            handleSubmitSuccess(xhr.responseText);
        }
    };
    xhr.open("POST", "${pageContext.request.contextPath}/Member/SignUp/EmailVerificationAction.do", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send("verificationCode=" + encodeURIComponent(verificationCode));
}
</script>
</head>
<body>
    <h1>Email Verification</h1>
    <p>Please check your email for the verification code.</p>
    <form action="${pageContext.request.contextPath}/Member/SignUp/EmailVerificationAction.do" method="post" onsubmit="event.preventDefault(); submitVerificationCode();">
        <label for="verificationCode">Verification Code:</label>
        <input type="text" id="verificationCode" name="verificationCode" required>
        <button type="submit">Submit</button>
    </form>
</body>
</html>
