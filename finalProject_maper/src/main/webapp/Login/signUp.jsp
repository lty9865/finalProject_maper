<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="../Common/link.jsp"%>
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
			<div class="maper-body-signup">
				<p class="maper-fontsize-1">Sign up</p>
				<div class="maper-signup-rounded">
					<div class="maper-login-inputsize">

						<!-- userid  -->
						<div class="input-group form-floating mb-3">
							<input type="text" class="form-control" id="floatingInput"
								placeholder="Enter UserName"> <label for="floatingInput">USERID</label>
							<button type="submit" type="button"
								class="btn btn-primary btn-lg">check</button>
						</div>

						<!-- password  -->
						<div class="form-floating mb-3">
							<input type="password" class="form-control" id="floatingPassWord"
								placeholder="Enter UserPassWord"> <label
								for="floatingPassWord">PASSWORD</label>
						</div>

						<!-- password ��ȿ�� üũ -->
						<div class="input-group form-floating mb-3">
							<input type="password" class="form-control"
								id="floatingPassWordCheck" placeholder="Enter UserPassWordCheck">
							<label for="floatingPassWordCheck">PASSWORD CHECK</label>
							<button type="submit" type="button"
								class="btn btn-primary btn-lg">check</button>
						</div>

						<!-- email -->
						<div class="input-group form-floating mb-3">
							<input type="text" class="form-control" id="floatingEmail"
								placeholder="Enter UserEmail"> <label
								for="floatingEmail">EMAIL</label>
							<button type="submit" type="button"
								class="btn btn-primary btn-lg">check</button>
						</div>

						<!-- ������� -->
						<div class="form-floating">
							<div class="row">
								<div class="col">
									<input type="text" class="form-control" id="floatingYear"
										placeholder="Year"> <label for="floatingBirthOfDate"></label>
								</div>
								<div class="col">
									<input type="text" class="form-control" id="floatingMonth"
										placeholder="Month"> <label for="floatingBirthOfDate"></label>
								</div>
								<div class="col">
									<input type="text" class="form-control" id="floatingDay"
										placeholder="Day"> <label for="floatingBirthOfDate"></label>
								</div>
							</div>
						</div>

						<!-- ��ư -->
						<div class="d-grid gap-2 d-md-block col text-center">
							<button type="sumbit" class="btn btn-primary" type="button">signup</button>
							<button type="sumbit" class="btn btn-primary" type="button">cencel</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>