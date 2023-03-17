<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
			<p class="maper-fontsize-1">Sign up</p>
			<div class="maper-login-rounded">
				<div class="maper-login-inputsize">
					<div class="form-floating mb-3">
						<input type="text" class="form-control" id="floatingInput"
							placeholder="Enter UserName"> <label for="floatingInput">USERID</label>
					</div>
					<div class="form-floating">
						<input type="password" class="form-control" id="floatingPassword"
							placeholder="Password"> <label for="floatingPassword">Password</label>
					</div>
										<div class="form-floating">
						<input type="password" class="form-control" id="floatingPassword-Check"
							placeholder="Password"> <label for="floatingPassword-Check">Password Check</label>
					</div>
					<div class="form-floating">
						<input type="email" class="form-control" id="floatingEmail"
							placeholder="Email"> <label for="floatingEmail">Email</label>
					</div>
					<div>birth
					</div>
					<div class="maper-login-buttonLocation">
						<div class="d-grid gap-2 col-6 mx-auto">
							<button type="submit" class="btn btn-primary" type="button">Log in</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</form>
</body>
</html>