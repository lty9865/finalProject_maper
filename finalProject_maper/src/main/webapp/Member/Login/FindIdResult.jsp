<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/Common/link.jsp"%>
<title>아이디 찾기 결과</title>
<script type="text/javascript"
	src="../../Resources/javascript/Member.js"></script>
</head>
<body class="maper-body-background">
	<div class="maper-header mb-3">
		<div class="maper-header-font">
			<a class="maper-header-font-1" href="login.do">MAPER</a>
			<div class="maper-body">
				<div class="px-4 py-5 my-5 text-center">
					<h1>아이디 찾기 결과</h1>

					<%
					String userId = (String) request.getAttribute("userId");
					%>
					<%
					String errMsg = (String) request.getAttribute("errMsg");
					%>

					<%
					if (userId != null) {
					%>
					<p>
						회원님의 아이디는
						<%=userId%>입니다.
					</p>
					<%
					} else {
					%>
					<p><%=errMsg%></p>
					<%
					}
					%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>