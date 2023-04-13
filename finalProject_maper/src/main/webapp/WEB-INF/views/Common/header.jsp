<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<body>
	<!-- header -->
	<div class="maper-header">
		<div class="maper-header-font">
			<div class="left">
				<a class="maper-header-font-1"
					href="${pageContext.request.contextPath}/Webmain/mainPage.do?command=main">MAPER</a>
			</div>
			<c:choose>
				<c:when test="${ empty sessionScope.userId }">
					<div class="right maper-header-font-2" align="right">
						<button type="button" class="btn btn-outline-primary" id="login"
							onclick="location.href='${pageContext.request.contextPath}/Member/Login/login.jsp';">로그인</button>
					</div>
				</c:when>
				<c:otherwise>
					<div class="right maper-header-font-2" align="right">
						<ul class="nav nav-pills justify-content-end">
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" data-bs-toggle="dropdown"
								href="#" role="button" aria-expanded="false">${ sessionScope.userId }</a>
								<ul class="dropdown-menu">
									<c:choose>
										<c:when test="${ empty sessionScope.userId }">
											<li><a class="dropdown-item" onclick="LoginConfirmed()">마이페이지</a></li>
										</c:when>
										<c:otherwise>
											<li><a class="dropdown-item" href="${pageContext.request.contextPath}/MyPage/myPage.jsp">마이페이지</a></li>
										</c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${ empty sessionScope.userId }">
											<li><a class="dropdown-item" onclick="LoginConfirmed()">내 책장</a></li>
										</c:when>
										<c:otherwise>
											<li><a class="dropdown-item"
												href="${pageContext.request.contextPath}/Book/book.do?command=bookList&mode=my">내 책장</a></li>
										</c:otherwise>
									</c:choose>
									<li><hr class="dropdown-divider"></li>
									<li><a class="dropdown-item"
										href="${pageContext.request.contextPath}/Common/logOutProcess.jsp">로그아웃</a></li>
								</ul></li>
						</ul>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<br>
</body>
</html>