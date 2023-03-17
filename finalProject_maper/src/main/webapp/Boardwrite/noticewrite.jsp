<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../Common/link.jsp"%>
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
	<!-- body -->
	<form name="writeFrm" method="post" action="../noticewrite.do">
		<div class="maper-body">
			<div class="maper-book-write" style="margin-top:15px;">
				<input type="text" class="form-control" name="title" placeholder="제목">
				<div style="height: 10px;"></div>
  				<textarea class="form-control" rows="5" name="contents"></textarea>
				<div style="height: 10px;"></div>
				<div align="center" style="margin-top:15px;">
					<button type="submit" class="btn btn-primary btn-lg" style="width:120px;">OK</button>
					<button type="button" onclick="" class="btn btn-primary btn-lg" style="width:120px;">Cancel</button>
				</div>
			</div>
		</div>
	</form>
</body>
</html>