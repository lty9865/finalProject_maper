<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="/Common/link.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Resources/css/myPage.css">
<title>Admins Request Reply</title>
<script>
function resetFields() {
  document.getElementsByName("content")[0].value = "";
}

window.onload = function() {
    var error = "${error}";
    if (error) {
        alert(error);
    }
}
</script>
<style type="text/css">
.reply-btn {
	margin-left: 50.5rem;
}
</style>
</head>
<body>
    <!-- header -->
    <%@ include file="/WEB-INF/views/Common/header.jsp"%>

    <!-- body/menu -->
    <jsp:include page="/MyPage/adminsMenu.jsp" />

    <!-- body/main -->
    <div class="maper-body">
        <h2>문의 번호 ${requestScope.requestNum}번 글의 답변</h2>
        <hr>

        <!-- Display requestNum and title from the requestScope -->
        
        <p><strong class="input-label">문의 번호: </strong>${requestScope.requestNum}</p>
        <p><strong class="input-label">제목: </strong>RE: ${requestScope.requestTitle}</p>
        <p><strong class="input-label">문의한 사람: </strong>${requestScope.requestUserId}</p>
        <p><strong class="input-label">문의 일자: </strong>${requestScope.requestDate}</p>
        <p><strong class="input-label">작성자: </strong>${fn:substringBefore(sessionScope.userId, '_')}</p>

        <!-- Reply form -->
        <form action="${pageContext.request.contextPath}/MyPage/MyPageFront?command=Admins.requestReplyProcess" method="POST">
            <input type="hidden" name="requestNum" value="${requestScope.requestNum}" />
            <input type="hidden" name="title" value="${requestScope.requestTitle}" />
            <input type="hidden" name="userId" value="${fn:substringBefore(sessionScope.userId, '_')}" />
            <input type="hidden" name="requestDate" value="${requestScope.requestDate}" />
            <div class="form-group">
                <label for="replyContent"><strong style="margin-left: 25px;">답변 내용:</strong></label>
                <textarea class="form-control" id="replyContent" name="replyContent" rows="10" required></textarea>
            </div>
            <br>
            <button type="submit" class="btn btn-primary reply-btn">답변 완료</button>
            <button type="reset" class="btn btn-primary" onclick="resetFields()">다시 쓰기</button>
        </form>
    </div>

    <!-- footer -->
    <%@ include file="/WEB-INF/views/Common/footer.jsp"%>
</body>
</html>
