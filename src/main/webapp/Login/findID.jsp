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
			<p class="maper-fontsize-1">Find ID</p>
			<div class="maper-login-rounded">
				<div class="maper-login-inputsize">
				
				<!-- 이메일 -->
			<div class="input-group row g-1">
  <div class="col-md">
    <div class="form-floating mb-3">
      <input type="email" class="form-control" id="floatingInputGrid" placeholder="Email">
      <label for="floatingInputGrid">Email</label>
    </div>
  </div>
  <div class="col-md">
    <div class="form-floating mb-3">
      <select class="form-select" id="floatingSelectGrid" placeholder="Email">
        <option selected="#"></option>
        <option value="1">@naver.com</option>
        <option value="2">@gmail.com</option>
        <option value="3">@daum.net</option>
        <option value="4">@nate.com</option>
      </select>
      <label for="floatingSelectGrid">address</label>
    </div>
  </div>
</div>
                
                <!-- 라이센스 키 -->
					<div class="form-floating">
						<input type="text" class="form-control" id="floatingLicenseKey"
							placeholder="LicenseKey"> <label for="floatingLicenseKey">LicenseKey</label>
					</div>
					
					<!-- 버튼 -->
					<div class="maper-login-buttonLocation">
						<div class="d-grid gap-2 col-6 mx-auto">
							<button type="submit" class="btn btn-primary" type="button">ok</button>
						</div>
					</div>
					</div>
			</div>
			
			<div style="margin: 15px auto; width: 80%;" align="center">
				<p><b>MAPER</b> 와 함께 추억을 공유해보세요</p>
			</div>
		</div>
	</div>
	</form>
</body>
</html>