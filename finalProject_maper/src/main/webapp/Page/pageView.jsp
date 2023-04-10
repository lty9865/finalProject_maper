<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.mapers.book.model.BookDAO"%>
<%@ page import="com.mapers.book.model.BookDTO"%>
<!-- 각종 링크 헤더 include -->
<%@ include file="../Common/link.jsp"%>
<%
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);
response.setHeader("Cache-Control", "no-cache");
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width = 1050, user-scalable = no" />
<script type="text/javascript" src="extras/jquery.min.1.7.js"></script>
<script type="text/javascript" src="extras/modernizr.2.5.3.min.js"></script>
<link rel="stylesheet" type="text/css" href="../Page/css/pageStyle.css">
<style>
.star-ratings {
	color: #aaa9a9;
	position: relative;
	unicode-bidi: bidi-override;
	width: max-content;
	-webkit-text-fill-color: transparent;
	/* Will override color (regardless of order) */
	-webkit-text-stroke-width: 1.3px;
	-webkit-text-stroke-color: #2b2a29;
}

.star-ratings-fill {
	color: #fff58c;
	padding: 0;
	position: absolute;
	z-index: 1;
	display: flex;
	top: 0;
	left: 0;
	overflow: hidden;
	-webkit-text-fill-color: gold;
}

.star-ratings-base {
	z-index: 0;
	padding: 0;
}

.test_obj {
	position: relative;
	display: inline-block;
	width: 200px;
	box-shadow: 0 2px 6px rgba(0, 0, 0, .2);
}

.test_obj:before {
	content: "";
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background: linear-gradient(to right, rgba(0, 0, 0, 0.2) 0px,
		transparent 5%, transparent 95%, rgba(0, 0, 0, 0.2) 100%);
}
</style>
</head>
<body>
	<!-- header -->
	<%@ include file="/WEB-INF/views/Common/header.jsp"%>

	<!-- body -->
	<div class="maper-body" align="center">
		<div class="maper-page-rounded">
			<table style="width: 100%">
				<tr align="center">
				</tr>
				<tr align="left">
					<th width="8%"></th>
					<th width="3%"><img alt=""
						src="../Resources/assets/img/baseimg/person.png"
						style="width: 15px; height: 15px"></th>
					<th width="20%">${ bookDTO.userId }</th>
					<th width="3%"><img alt=""
						src="../Resources/assets/img/baseimg/glass.png"
						style="width: 20px; height: 20px"></th>
					<th width="20%">${ bookDTO.visitCount }</th>
					<td width="25%">
						<!-- 좋아요 ajax -->
						<form id="like_form" class="row">
							<c:choose>
								<c:when test="${ sessionScope.likeCheck eq 0 }">
									<button type="button"
										style="background-color: rgba(0, 0, 0, 0); border: none;"
										onclick="return like()">&#128420;</button>
								</c:when>
								<c:otherwise>
									<button type="button"
										style="background-color: rgba(0, 0, 0, 0); border: none;"
										onclick="return like()">&#10084;</button>
								</c:otherwise>
							</c:choose>
						</form>
					</td>
					<td width="25%">
						<!-- 신고 ajax -->
						<form id="report_form" class="row">
							<button type="button"
								style="background-color: rgba(0, 0, 0, 0); border: none;"
								onclick="return report()">신고</button>
						</form>
					</td>
					<th width="3%"><img alt=""
						src="../Resources/assets/img/baseimg/like.png"
						style="width: 15px; height: 15px"></th>
					<th width="20%">${ bookDTO.likesCount }</th>
					<th width="7%"></th>
				</tr>
			</table>
		</div>
	</div>
	<div class="flipbook-viewport">
		<div class="maper-body" align="center">
			<button type="button" class="btn btn-outline-primary"
				onclick="location.href='../Book/book.do?command=bookView&idx=${ bookDTO.bookNum }';">돌아가기</button>
		</div>
		<div class="container">
			<div class="flipbook">
				<div class="test_obj" align="center">
					<br> <img src="../Uploads/Book/${ bookDTO.sfile }" style="width: 60%; height: 40%; object-fit: cover; margin-bottom: 20px;">
					<p style="font-size: 30px;">${ bookDTO.title }</p>
				</div>
				<c:choose>
					<c:when test="${ empty pageList }">
						<div>
							<p>게시물이 없습니다.</p>
						</div>
					</c:when>
					<c:otherwise>
						<c:forEach items="${ pageList }" var="row" varStatus="loop">
							<c:set var="numCheck" value="${ loop.index }" />
							<div>
								<c:choose>
									<c:when test="${ numCheck } % 2 = 0">
										<div class="even"></div>
										<div class="gradient"></div>
									</c:when>
									<c:otherwise>
										<div class="odd"></div>
										<div class="gradient"></div>
									</c:otherwise>
								</c:choose>
								<div class="pageWrapper">
									<div class="pageItem">
										<div class="polaroid">
											<img src="../Uploads/Page/${ row.sfile }">
											<div class="caption">${row.subTitle}&nbsp;&nbsp;${row.postDate}</div>
										</div>
									</div>
								</div>
								<br />
								<div style="width: 380px; height: 150px; margin: auto"
									align="center">
									<p style="word-wrap: break-word;">${ row.content }</p>
								</div>
								<!-- 별 -->
								<div align="center">
									<div class="star-ratings">
										<div class="star-ratings-fill space-x-2 text-lg"
											style="width: ${row.percent}%;">
											<span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>
										</div>
										<div class="star-ratings-base space-x-2 text-lg">
											<span>★</span><span>★</span><span>★</span><span>★</span><span>★</span>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</c:otherwise>
				</c:choose>
				<div></div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function loadApp() {

			// Create the flipbook

			$('.flipbook').turn({
				// Width

				width : 922,

				// Height

				height : 600,

				// Elevation

				elevation : 50,

				// Enable gradients

				gradients : true,

				// Auto center this flipbook

				autoCenter : true

			});
		}

		// Load the HTML4 version if there's not CSS transform

		yepnope({
			test : Modernizr.csstransforms,
			yep : [ 'lib/turn.js' ],
			nope : [ 'lib/turn.html4.min.js' ],
			both : [ 'css/basic.css' ],
			complete : loadApp
		});
	</script>
	<script type="text/javascript"
		src="../Resources/javascript/pageScript.js"></script>

	<!-- 좋아요 기능 -->
	<script type="text/javascript">
		function like(){
			if(${empty sessionScope.userId}){
					LoginConfirmed();
			}else{
				
			$.ajax({
				url : "../Book/book.do?command=like",
				type : "POST",
				date : {
					bookNum : ${ bookDTO.bookNum },
					likeCheck : ${ sessionScope.likeCheck },
					userId : "${ sessionScope.userId }"
					},
					success:
					function(data){
					  location.reload();
					},
					
					error:
						function(request, status, error){
						alert("좋아요 실패");
					}
			});
			}
		}
	</script>
	<!-- 신고 기능 -->
	<script type="text/javascript">
		function report(){
			if(${empty sessionScope.userId}){
					LoginConfirmed();
			}else{
				
			$.ajax({
				url : "../Book/book.do?command=report",
				type : "POST",
				date : {
					bookNum : ${ bookDTO.bookNum },
					userId : "${ sessionScope.userId }"
					},
					success:
					function(data){
					  location.reload();
					},
					
					error:
						function(request, status, error){
						alert("신고 실패");
					}
			});
			}
		}
	</script>
</body>
</html>