<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/Common/link.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- header -->
	<%@ include file="/WEB-INF/views/Common/header.jsp"%>

	<!-- body -->
	<div class="table maper-body" id="pageTitle">
		<h2>회원가입</h2>
		<hr>
		<form action="" class="g-3 needs-validation" method="post" novalidate>
			<div class="col-md-4">
				<label for="validationCustom01" class="form-label">아이디</label> <input
					type="text" class="form-control" id="validationCustom01"
					name="userId" placeholder="아이디를 입력하세요." required>
				<div class="valid-feedback">Looks good!</div>
			</div>
			<br>
			<div class="col-md-4">
				<label for="validationCustom02" class="form-label">패스워드</label> <input
					type="password" class="form-control" id="validationCustom02"
					name="password" placeholder="패스워드를 입력하세요." required> <input
					type="password" class="form-control" id="validationCustom02"
					name="passwordCheck" placeholder="패스워드를 한번 더 입력하세요."
					style="margin-top: 5px;" required>
				<div class="valid-feedback">Looks good!</div>
			</div>
			<hr />
			<div class="col-md-4">
				<label for="validationCustom01" class="form-label">이름</label> <input
					type="text" class="form-control" id="validationCustom01"
					name="name" placeholder="아이디를 입력하세요." required>
				<div class="valid-feedback">Looks good!</div>
			</div>
			<br>
			<div class="col-md-4">
				<label for="validationCustom01" class="form-label">이메일</label> <input
					type="email" class="form-control" id="validationCustom01"
					name="email" placeholder="이메일을 입력하세요." required>
				<div class="valid-feedback">Looks good!</div>
			</div>
			<div class="row">
				<div class="col-md-4">
					<label for="validationCustom01" class="form-label">생년월일</label>
				</div>
			</div>
			<div class="col-md-4" style="width: 30%; margin-left: 0;">
				<div class="row" style="padding: 0;">
					<div class="col" style="padding: 0;">
						<select id="year" name="year"
							class="form-control  float: none; margin 0 auto">
							<option value="">year</option>
							<c:forEach var="i" begin="1940" end="2010">
								<option value="${i}">${i}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col" style="padding: 0;">
						<select id="month" name="month"
							class="form-control  float: none; margin 0 auto">
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
					<div class="col" style="padding: 0;">
						<select id="day" name="day"
							class="form-control  float: none; margin 0 auto">
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
			<br>
			<div class="col-12">
				<button class="btn btn-primary" type="submit">가입하기</button>
			</div>
		</form>
	</div>
</body>
</html>