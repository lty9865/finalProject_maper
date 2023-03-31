<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 각종 링크 헤더 include -->
<%@ include file="../Common/link.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width = 1050, user-scalable = no" />
<script type="text/javascript" src="extras/jquery.min.1.7.js"></script>
<script type="text/javascript" src="extras/modernizr.2.5.3.min.js"></script>
</head>
<body>

	<div class="flipbook-viewport">
		<div class="container">
			<div class="flipbook">
				<c:choose>
					<c:when test="${ empty pageList }">
						<div>
							<p>게시물이 없습니다.</p>
						</div>
					</c:when>
					<c:otherwise>
						<c:forEach items="${ pageList }" var="row" varStatus="loop">
							<div style="background-image: url(../Uploads/Page/${ row.sfile })">
								<p>페이지넘버 : ${ row.pageNum }</p>
								<br />
								<p>부제목 : ${ row.subTitle }</p>
								<br />
								<p>내용 : ${ row.content }</p>
								<br />
								<p>작성일 : ${ row.postDate }</p>
								<br />
								<p>만족도 : ${ row.rate }</p>
								<br />
								<p>원본파일명 : ${ row.ofile }</p>
								<br />
								<p>저장파일명 : ${ row.sfile }</p>
								<br />
							</div>
						</c:forEach>
					</c:otherwise>
				</c:choose>

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

</body>
</html>