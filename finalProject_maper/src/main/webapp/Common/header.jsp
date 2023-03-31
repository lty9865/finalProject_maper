<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>
	<!-- header -->
	<div class="maper-header">
		<div class="maper-header-font">
			<div class="left">
				<a class="maper-header-font-1" href="../Webmain/mainPage.do?command=main">MAPER</a>
			</div>
			<c:choose>
				<c:when test="${ empty sessionScope.userId }">
					<div class="right maper-header-font-2" align="right">
						<button type="button" class="btn btn-outline-primary" id="login"
							onclick="location.href='../Login/login.jsp'">로그인</button>
					</div>
				</c:when>
				<c:otherwise>
					<div class="right maper-header-font-2" align="right">
						<ul class="nav nav-pills justify-content-end">
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" data-bs-toggle="dropdown"
								href="#" role="button" aria-expanded="false">${ sessionScope.userId }</a>
								<ul class="dropdown-menu">
									<li><a class="dropdown-item" href="${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyProfile">마이페이지</a></li>
									<li><a class="dropdown-item"
										href="../Book/book.do?command=bookList">내 책장</a></li>
									<li><hr class="dropdown-divider"></li>
									<li><a class="dropdown-item" href="">로그아웃</a></li>
								</ul></li>
						</ul>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>
</html>