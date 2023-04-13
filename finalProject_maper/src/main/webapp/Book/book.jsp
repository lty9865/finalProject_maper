<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../Common/link.jsp"%>

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
	<div class="maper-body">
		<div class="table maper-body" id="pageTitle">
			<c:choose>
				<c:when test="${ empty sessionScope.my }">
					<h2>책장</h2>
				</c:when>
				<c:otherwise>
					<h2>내 책장</h2>
				</c:otherwise>
			</c:choose>
			<hr>
		</div>
		<div class="container text-center">
			<div class="row row-cols-auto">
				<c:choose>
					<c:when test="${ empty bookList }">
						<div class="col">게시물이 없습니다.</div>
					</c:when>
					<c:otherwise>
						<c:forEach items="${ bookList }" var="row" varStatus="loop">
							<div class="col">
								<div class="card" style="width: 18rem; margin: 0 auto 10px auto">
									<img src="../Uploads/Book/${ row.sfile }" class="card-img-top"
										alt="...">
									<div class="card-body">
										<h5 class="card-title">${ row.title }</h5>
										<a
											href="../Book/book.do?command=bookView&idx=${ row.bookNum }"
											class="btn btn-primary">보러가기</a>
									</div>
								</div>
							</div>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<table class="table maper-body">
			<tr align="center">
				<td>${ map.pagingImg }</td>
			</tr>
		</table>
		<c:choose>
			<c:when test="${ empty sessionScope.my }">
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
			</c:when>
			<c:otherwise>
				<form action="../Book/book.do?command=bookList&mode=my"
					method="post">
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
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${ empty sessionScope.userId }">
				<input type="button" class="btn btn-primary"
					onclick="LoginConfirmed()" value="책작성하기">
			</c:when>
			<c:otherwise>
				<input type="button" class="btn btn-primary"
					onclick="location.href='../Book/book.do?command=bookWriteView';"
					value="책작성하기">
			</c:otherwise>
		</c:choose>
	</div>
	<hr>
	<!-- footer -->
	<%@ include file="/WEB-INF/views/Common/footer.jsp"%>
	
	<script type="text/javascript" src="../Resources/javascript/book.js"></script>
</body>
</html>