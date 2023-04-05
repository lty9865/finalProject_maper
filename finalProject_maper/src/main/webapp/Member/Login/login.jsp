<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 각종 링크 헤더 include -->
<%@ include file="/Common/link.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<script type="text/javascript" src="../../Resources/javascript/Member.js"></script>
</head>
<body class="maper-body-background">

	<!-- header -->
	<%@ include file="/WEB-INF/views/Common/header.jsp"%>
	
	<!-- body -->
	<form action="login.do" method="post" name="frm">
		<div class="maper-body">
			<div class="maper-body-login">
				<p class="maper-fontsize-1">Login</p>
				<div class="maper-login-rounded">
					<div class="maper-login-inputsize">
						<div class="form-floating mb-3">
							<input type="text" class="form-control" name="userid" value="${userid }"
								placeholder="Enter UserName"> <label for="floatingInput">UserName</label>
						</div>
						<div class="form-floating">
							<input type="password" class="form-control" name="password"
								placeholder="Password"> <label for="floatingPassword">Password</label>
						</div>
						<div class="maper-login-buttonLocation">
							<div class="d-grid gap-2 col-6 mx-auto">
								<button type="submit" class="btn btn-primary" type="button" onclick="return loginCheck()">Log
									in</button>
							</div>
						</div>
						<div class="maper-login-labels">
							<a href="Join.do">회원가입</a>&nbsp;&nbsp;&nbsp; <a
								href="findID.do">아이디찾기</a>&nbsp;&nbsp;&nbsp; <a
								href="findPassword.do">패스워드찾기</a>
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