<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 각종 링크 헤더 include -->
<%@ include file="/Common/link.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body class="maper-body-background">

	<!-- header -->
	<%@ include file="/WEB-INF/views/Common/header.jsp"%>

	<!-- body -->
	<form action="SignUp.do" method="post" name="frm">
		<div class="maper-body">
			<div class="maper-body-signup">
				<p class="maper-fontsize-1">Sign Up</p>
				<div class="maper-signup-rounded">
					<div class="maper-login-inputsize">

						<div class="input-group form-floating mb-3">
							<input type="text" name="userid" id="userid" class="form-control"
								placeholder="Enter UserId"> <label for="floatingInput">UserId</label>
							<input type="submit" type="button" value="check" onclick="idCheck()"
								class="btn btn-primary">
						</div>



						<div class="form-floating mb-3">
							<input type="password" name="password" class="form-control"
								id="password" placeholder="Enter Password"> <label
								for="floatingInput">Password</label>
						</div>


						<div class="form-floating mb-3">
							<input type="password" name="confirmPassword"
								class="form-control" id="confirmPassword"
								placeholder="Enter confirmPassword"> <label
								for="floatingInput">confirmPassword</label>
						</div>

						<div class="form-floating mb-3">
							<input type="text" name="email" class="form-control" id="email"
								placeholder="Enter Email"> <label for="floatingInput">Email</label>
						</div>


						<div class="form-floating mb-3">
							<div class="row">
								<div class="col">
									<select id="year" name="year"
										class="form-control  float: none; margin 0 auto"
										placeholder="Enter Year">
										<option value="">year</option>
										<c:forEach var="i" begin="1940" end="2010">
											<option value="${i}">${i}</option>
										</c:forEach>
									</select>
								</div>

								<div class="col">
									<select id="month" name="month"
										class="form-control  float: none; margin 0 auto"
										placeholder="Enter Month">
										<option value="">Month</option>
										<c:forEach var="i" begin="1" end="12">
											<c:choose>
												<c:when test="${i lt 10 }">
													<option value="0${i}">0${i}</option>
												</c:when>
												<c:otherwise>
													<option value="${i}">${i}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</select>
								</div>

								<div class="col">
									<select id="day" name="day"
										class="form-control  float: none; margin 0 auto"
										placeholder="Enter Day">
										<option value="">Day</option>
										<c:forEach var="i" begin="1" end="31">
											<c:choose>
												<c:when test="${i lt 10 }">
													<option value="0${i}">0${i}</option>
												</c:when>
												<c:otherwise>
													<option value="${i}">${i}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>

						<div class=colspan= "2" align="center">

							<input type="submit" value="확인" class="btn btn-primary" onclick="return joinCheck()">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="reset" value="취소"
								class="btn btn-primary">
						</div>
					</div>
				</div>
				<div style="margin: 15px auto; width: 80%;" align="center">
					<p>
						<b>MAPER</b> 함께 추억을 공유해보세요.
					</p>
				</div>
			</div>
		</div>
	</form>
	
<script type="text/javascript">

function idCheck() {
	if(document.frm.userid.value=="") {
		alert('아이디를 입력하여 주십시오.');
		document.frm.userid.focus();
		return;
	}	
	var url="IdCheck.do?userid="+ document.frm.userid.value;
	window.open(url, "_blank_1", "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=450, height=200");
}

function joinCheck() {
	if (document.frm.password.value == "") {
		alert("비밀번호를 써주세요.");
		frm.password.focus();
		return false;
	}

	if (document.frm.password.value.length < 8 || document.frm.password.value.length > 20) {
		alert("비밀번호를 8~20자 이내로 입력해주세요.");
		frm.password.focus();
		return false;
	}
	if (document.frm.email.value == "") {
		alert("이메일을 입력해주세요.");
		frm.email.focus();
		return false;
	}
	
	if (document.frm.year.value == "") {
		alert("생년월일을 정확히 입력해주세요.");
		frm.year.focus();
		return false;
	}
	if (document.frm.month.value == "") {
		alert("생년월일을 정확히 입력해주세요.");
		frm.month.focus();
		return false;
	}
	if (document.frm.day.value == "") {
		alert("생년월일을 정확히 입력해주세요.");
		frm.day.focus();
		return false;
	}

	if (document.frm.password.value != document.frm.confirmPassword.value) {
		alert("암호가 일치하지 않습니다.");
		frm.password.focus();
		return false;
	}

	return true;
}
</script>

<c:if test="${not empty Msg}">
		<script>
			alert("${Msg}");
		</script>
				</c:if>

</body>
</html>