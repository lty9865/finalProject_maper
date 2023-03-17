<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../Common/link.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BookWrite</title>
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
						alert("Enter Key �Է� ���� \n�Լ� ����.");
					}
				</script>
			</div>
		</div>
	</div>
	<!-- body -->
	<form name="bookwriteForm" method="post" enctype="multipart/form-data">
		<div class="maper-body">
			<div class="maper-book-write">
				<input type="text" class="form-control" name="title" placeholder="����"><br>
				<input type="text" class="form-control" name="country" placeholder="����"><br>
				<input type="text" class="form-control" name="city" placeholder="����"><br>
				<input type="text" class="form-control" name="date" placeholder="����"><br>
				<input type="text" class="form-control" name="file" placeholder="���� ���ε�"><br>
				<button type=""></button>
			</div>
		</div>
	</form>
</body>
</html>