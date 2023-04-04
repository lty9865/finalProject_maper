<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/Common/link.jsp"%>
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
	<form name="writeFrm" method="post" action="../Notice/notice.do?command=write" onsubmit="return noticeBlank()">
		<div class="maper-body">
			<div class="maper-book-write" style="margin:15px auto;">
				<input type="text" class="form-control" name="title" placeholder="제목">
				<div style="height: 10px;"></div>
  				<textarea class="form-control" rows="10" name="contents"></textarea>
				<div style="height: 10px;"></div>
				<div align="center" style="margin-top:15px;">
					<input type="submit" class="btn btn-primary btn-lg" style="width:120px;" value="작성완료"/>
					<button type="button" onclick="" class="btn btn-primary btn-lg" style="width:120px;" onclick="location.href='../Notice/notice.do?command=list'">Cancel</button>
				</div>
			</div>
		</div>
	</form>
	<script type="text/javascript" src="../Resources/javascript/notice.js"></script>
</body>
</html>