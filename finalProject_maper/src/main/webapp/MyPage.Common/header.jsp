<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- header -->
	<div class="maper-header">
		<div class="maper-header-font">
			<div class="left">
				<a class="maper-header-font-1" href="login.jsp">MAPER</a>
			</div>
			<div class="right" align="right" style="">
				<input type="text" class="form-control" id="searchKeyword"
					placeholder="Enter Keyword"
					onKeypress="javascript:if(event.keyCode==13) {test()}">
				<script>
					function test() {
						alert("Enter Key 입력 감지 \n함수 실행.");
					}
				</script>
			</div>
		</div>
	</div>
</body>
</html>