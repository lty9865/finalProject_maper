<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<form action="idCheck.do" method="get" name="frm">
		아이디 <input type="text" name="userid" value="${userid}">
		<input type="submit" value="중복 체크"> <br>
		<c:if test="${result==1}">
			${userid}는 이미 사용 중인 아이디 입니다.
		</c:if>
		<script type="text/javascript">
			function idok() {
				opener.frm.userid.value=document.frm.userid.value;
				opener.frm.reid.value=document.frm.userid.value;
				self.close();
			}
				opener.document.frm.userid.value="";
			</script>
		<c:if test="${result==-1}">
			${userid}는 사용 가능한 아이디입니다.
			<input type="button" value="사용" class="cancel" onclick="idok()">
		</c:if>
	</form>
			
</body>
</html>