<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 각종 링크 헤더 include -->
<%@ include file="../Common/link.jsp"%>
<title>Login</title>
</head>
<body class="maper-body-background">
	<!-- header -->
	<div class="maper-header">
		<div class="maper-header-font">
			<a class="maper-header-font-1" href="login.jsp">MAPER</a>
		</div>
	</div>
	<!-- body -->
	<form method="post">
		<div class="maper-body">
			<div class="maper-body-login">
				<p class="maper-fontsize-1">find PASSWORD</p>
				<div class="maper-login-rounded">

					<!-- username -->
					<div class="maper-login-inputsize">
						<div class="form-floating mb-3">
							<input type="text" class="form-control" id="floatinguserID"
								placeholder="Enter UseruserID"> <label
								for="floatinguserID">userID </label>
						</div>

						<!-- LicenseKey -->
						<div class="form-floating">
							<input type="text" class="form-control" id="floatingLicenseKey"
								placeholder="Enter LicenseKey"> <label
								for="floatingLicenseKey">LicenseKey</label>
						</div>

						<!-- button -->
						<div class="maper-login-buttonLocation">
							<div class="d-grid gap-2 col-6 mx-auto">
								<button type="submit" class="btn btn-primary" type="button">ok</button>
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
</body>
</html>