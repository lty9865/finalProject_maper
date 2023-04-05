<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 각종 링크 헤더 include -->
<%@ include file="/Common/link.jsp"%>
<title>회원 관리</title>
<script type="text/javascript"
	src="../../Resources/javascript/Member.js"></script>
</head>

<body class="maper-body-background">
	<div class="maper-header">
		<div class="maper-header-font">
			<a class="maper-header-font-1" href="login.jsp">MAPER</a>
		</div>
	</div>
	<form action="Join.do" method="post" name="frm">
	<div class="maper-body">
	<div class="maper-body-signup">
	<p class="maper-fontsize-1">Sign Up</p>
	<div class="maper-signup-rounded">
	<div class="maper-login-inputsize">
	
	<div class="input-group form-floating mb-3">
				<input type="input-group-text" name="userid" class="form-control" id="userid" placeholder="Enter UserId">
				<label for="floatingInput">UserId</label>
				
					<input type="hidden" name="reid"> 
					<input type="submit" value="Check" class="btn btn-primary" onclick="idCheck()">
				</div>
			
			
			
				<div class="form-floating mb-3">	
				<input type="password" name="password" class="form-control" id="password" placeholder="Enter Password">
				<label for="floatingInput">Password</label>
			</div>
			
			
			<div class="form-floating mb-3">
				<input type="password" name="confirmPassword" class="form-control" id="confirmPassword" placeholder="Enter confirmPassword">
				<label for="floatingInput">confirmPassword</label>
			</div>
			
			<div class="form-floating mb-3">
				<input type="text" name="email" class="form-control" id="email" placeholder="Enter Email">
				<label for="floatingInput">Email</label>
</div>


<div class="form-floating mb-3">
			<div class="row">
				<div class="col">
					<select id="year" name="year" class="form-control  float: none; margin 0 auto" placeholder="Enter Year">
						<option value="">year</option>
						<c:forEach var="i" begin="1940" end="2010">
							<option value="${i}">${i}</option>
						</c:forEach>
					</select>
				</div>

				<div class="col">
					<select id="month" name="month" class="form-control  float: none; margin 0 auto" placeholder="Enter Month">
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
					<select id="day" name="day" class="form-control  float: none; margin 0 auto" placeholder="Enter Day">
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
				
			<div class= colspan="2" align="center">
			<input type="submit" value="확인" class="btn btn-primary" onclick="return joinCheck()"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="reset" value="취소" class="btn btn-primary">
				</div>
				
			<tr>
				<td colspan="2">${message}</td>
			</tr>
		
		</div>
		</div>
				<div style="margin: 15px auto; width: 80%;" align="center">
				<p><b>MAPER</b> 함께 추억을 공유해보세요.</p>
			</div>
		</div>

		</div>
		
	</form>
		<c:if test="${not empty message}">
		<script>
			alert("${message}");
		</script>
				</c:if>
			
</body>
</html>