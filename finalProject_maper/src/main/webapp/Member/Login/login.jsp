<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 각종 링크 헤더 include -->
<%@ include file="/Common/link.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<style>
    .error {
        color: red;
        font-size: 12px;
        height: 15px; 
        line-height: 15px; 
        display: block; 
    }
    
    .maper-login-buttonLocation {
       margin-top: 0px; 
    }

    .form-floating {
        margin-bottom: 15px; 
    }

    .maper-login-labels {
        width: 90%;
        margin: 15px auto 20px auto; 
    }
</style>
<script>
    function validateForm() {
        var userId = document.forms["frm"]["userId"].value;
        var password = document.forms["frm"]["password"].value;
        var error = false;

        if (userId === "") {
            document.getElementById("userIdError").innerHTML = "User ID is required";
            error = true;
        } else {
            document.getElementById("userIdError").innerHTML = "";
        }

        if (password === "") {
            document.getElementById("passwordError").innerHTML = "Password is required";
            error = true;
        } else {
            document.getElementById("passwordError").innerHTML = "";
        }

        if (error) {
            return false;
        }
    }
    
    function checkUserId() {
        var userId = document.forms["frm"]["userId"].value;
        if (userId === "") {
            document.getElementById("userIdError").innerHTML = "User ID is required";
        } else {
            document.getElementById("userIdError").innerHTML = "";
        }
    }

    function checkPassword() {
        var password = document.forms["frm"]["password"].value;
        if (password === "") {
            document.getElementById("passwordError").innerHTML = "Password is required";
        } else {
            document.getElementById("passwordError").innerHTML = "";
        }
    }
    

</script>
</head>
<body class="maper-body-background">

	<!-- header -->
	<%@ include file="/WEB-INF/views/Common/header.jsp"%>
	
	<!-- body -->
	<form action="login.do" method="post" name="frm" onsubmit="return validateForm();">
		<div class="maper-body">
			<div class="maper-body-login">
				<p class="maper-fontsize-1">Login</p>
				<div class="maper-login-rounded">
					<div class="maper-login-inputsize">
						<div class="form-floating mb-3">
						    <input type="text" class="form-control" name="userId" value="${userId }"
						        placeholder="Enter UserName" oninput="checkUserId()"> <label for="floatingInput">userId</label>
						    <span id="userIdError" class="error"></span>
						</div>
						<div class="form-floating">
						    <input type="password" class="form-control" name="password"
						        placeholder="Password" oninput="checkPassword()"> <label for="floatingPassword">Password</label>
						    <span id="passwordError" class="error"></span>
						</div>
			            
						<div class="maper-login-labels">
							<a href="${pageContext.request.contextPath}/Member/SignUp/signUpTest.jsp">회원가입</a>&nbsp;&nbsp;&nbsp; <a
								href="${pageContext.request.contextPath}/Member/Login/findID.jsp">아이디찾기</a>&nbsp;&nbsp;&nbsp; <a
								href="${pageContext.request.contextPath}/Member/Login/findPassword.jsp">패스워드찾기</a>
						</div>
						<div class="maper-login-buttonLocation">
							<div class="d-grid gap-2 col-6 mx-auto">
								<button type="submit" class="btn btn-primary">Log
									in</button>
							</div>
						</div>
					</div>
				</div>
				<div style="margin: 15px auto; width: 80%;" align="center">
					<p>
						<b>MAPER</b> 와 함께 추억을 공유해보세요
					</p>
				</div>
			</div>
		</div>
	</form>
	<c:if test="${not empty falseMsg}">
    <script>
        alert("${falseMsg}");
    </script>
    </c:if>
</body>
</html>