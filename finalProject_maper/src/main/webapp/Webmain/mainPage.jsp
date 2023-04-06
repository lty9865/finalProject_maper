<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 각종 링크 헤더 include -->
<%@ include file="../Common/link.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>MAPER</title>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script type="text/javascript" src="/Common/LoginConfirmed.js"></script>
</head>
<body>
	<p>
		세션 :
		<%=session.getAttribute("userId")%>
		어플리케이션 :
		<%=application.getAttribute("userId")%>
	</p>

	<!-- header -->
	<%@ include file="/WEB-INF/views/Common/header.jsp"%>

	<!-- body -->
	<div class="maper-body">
		<form action="../Book/book.do?command=bookList" method="post">
			<div class="input-group" style="margin-top: 10px;">
				<select class="form-select circle" id="inputGroupSelect04"
					aria-label="Example select with button addon" name="searchField">
					<option value="title">제목</option>
					<option value="place">장소</option>
				</select> <input class="form-control" type="text" name="searchWord"
					style="width: 75%" placeholder="검색어를 입력하세요." />
				<button class="btn btn-outline-secondary circle" type="submit"
					style="width: 10%">검색</button>
			</div>
		</form>
		<hr>
		<div style="height: 400px">
			<div id="carouselExampleCaptions" class="carousel slide">
				<div class="carousel-indicators">
					<button type="button" data-bs-target="#carouselExampleCaptions"
						data-bs-slide-to="0" class="active" aria-current="true"
						aria-label="Slide 1"></button>
					<button type="button" data-bs-target="#carouselExampleCaptions"
						data-bs-slide-to="1" aria-label="Slide 2"></button>
					<button type="button" data-bs-target="#carouselExampleCaptions"
						data-bs-slide-to="2" aria-label="Slide 3"></button>
				</div>
				<div class="carousel-inner">
					<div class="carousel-item active">
						<img src="../Resources/assets/img/thumnail/drink.jpg"
							class="d-block w-100" alt="...">
						<div class="carousel-caption d-none d-md-block">
							<h1>Title</h1>
							<p>place</p>
							<p>date</p>
							<p>Some representative placeholder content for the first
								slide.</p>
						</div>
					</div>
					<div class="carousel-item">
						<img src="../Resources/assets/img/thumnail/notice.jpg"
							class="d-block w-100" alt="...">
						<div class="carousel-caption d-none d-md-block">
							<h1>First slide label</h1>
							<p>Some representative placeholder content for the first
								slide.</p>
							<p>Some representative placeholder content for the first
								slide.</p>
							<p>Some representative placeholder content for the first
								slide.</p>
						</div>
					</div>
					<div class="carousel-item">
						<img src="../Resources/assets/img/thumnail/travel.jpg"
							class="d-block w-100" alt="...">
						<div class="carousel-caption d-none d-md-block">
							<h1>First slide label</h1>
							<p>Some representative placeholder content for the first
								slide.</p>
							<p>Some representative placeholder content for the first
								slide.</p>
							<p>Some representative placeholder content for the first
								slide.</p>
						</div>
					</div>
				</div>
				<button class="carousel-control-prev" type="button"
					data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
					<span class="carousel-control-prev-icon" aria-hidden="true"></span>
					<span class="visually-hidden">Previous</span>
				</button>
				<button class="carousel-control-next" type="button"
					data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
					<span class="carousel-control-next-icon" aria-hidden="true"></span>
					<span class="visually-hidden">Next</span>
				</button>
			</div>
		</div>
		<br> <br>
		<!-- 인기글 -->
		<h3>인기글</h3>
		<hr>
		<div class="container text-center">
			<div class="row row-cols-auto">
				<c:choose>
					<c:when test="${ empty popularBook }">
						<div class="col">게시물이 없습니다.</div>
					</c:when>
					<c:otherwise>
						<c:forEach items="${ popularBook }" var="row" varStatus="loop">
							<div class="col">
								<div class="card" style="width: 18rem; margin: 0 auto 10px auto">
									<img src="../Uploads/Book/${ row.sfile }" Style="height: 300px"
										class="card-img-top" alt="..." id="mainMenu">
									<div class="card-body">
										<h5 class="card-title">${ row.title }</h5>
										<a
											href="../Book/book.do?command=bookView&idx=${ row.bookNum }"
											class="btn btn-primary">Go somewhere</a>
									</div>
								</div>
							</div>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<br>
		<!-- 최신글 -->
		<h3>최신글</h3>
		<hr>
		<div class="container text-center">
			<div class="row row-cols-auto">
				<c:choose>
					<c:when test="${ empty searchBook }">
						<div class="col">게시물이 없습니다.</div>
					</c:when>
					<c:otherwise>
						<c:forEach items="${ searchBook }" var="row" varStatus="loop">
							<div class="col">
								<div class="card" style="width: 18rem; margin: 0 auto 10px auto">
									<img src="../Uploads/Book/${ row.sfile }" Style="height: 300px"
										class="card-img-top" alt="..." id="mainMenu">
									<div class="card-body">
										<h5 class="card-title">${ row.title }</h5>
										<a
											href="../Book/book.do?command=bookView&idx=${ row.bookNum }"
											class="btn btn-primary">Go somewhere</a>
									</div>
								</div>
							</div>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<br>
		<!-- 공지사항 -->
		<h3>공지사항</h3>
		<hr>
		<table border="1" class="table table-hover">
			<thead>
				<tr>
					<th scope="col">제목</th>
					<th scope="col">작성자</th>
					<th scope="col">조회수</th>
					<th scope="col">작성일</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${ empty mainNoticeList }">
						<tr>
							<th colspan="7" align="center">게시물이 없습니다.</th>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${ mainNoticeList }" var="row" varStatus="loop">
							<tr>
								<td>
									<a href="../Notice/notice.do?command=view&idx=${ row.idx }">${ row.title }</a>
								</td>
								<td>관리자</td>
								<td>${ row.visitCount }</td>
								<td>${ row.postDate }</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
	<hr>
	<!-- footer -->
	<div class="maper-footer"></div>
</body>
</html>