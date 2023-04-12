<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../../Common/link.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Page - My Profile</title>
<link rel="stylesheet" href="../Resources/css/myPage.css">
<style>
.center-area {
    width: 70%;
    margin-left: 30px;
    margin-right: 30px;
}

.bottom-area {
    display: flex;
    justify-content: center;
    align-items: center;
}
</style>
</head>
<body>
    <!-- header -->
    <%@ include file="/WEB-INF/views/Common/header.jsp"%>

    <!-- body/menu + main -->
    <div class="body-main maper-body maper-body-background">
        <div class="top-area">
            <jsp:include page="../menu.jsp" />
        </div>
        <br>
        <br>
        <div class="bottom-area">
            <div class="center-area">
                <h1 class="title">${userId}</h1>
                <hr class="blue-line">
                <br>
                <p class="field">
                    <strong>아이디:</strong> 
                </p>
                ${userId}
                <hr class="blue-line">
                <p class="field">
                    <strong>비밀번호:</strong> 
                </p>
                ******
                <hr class="blue-line">
                <p class="field">
                    <strong>이메일:</strong> 
                </p>
                ${email}
                <hr class="blue-line">
                <br>
                <form
                    action="${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyProfile.checkRealAccount"
                    method="post">
                    <input type="submit" value="내 정보 수정" class="custom-button">
                </form>
            </div>
        </div>
    </div>

    <!-- notification message for empty password -->
    <c:if test="${not empty msg}">
        <script type="text/javascript">
            alert("{${msg}")
        </script>
    </c:if>

    <!-- footer -->
    <%@ include file="/WEB-INF/views/Common/footer.jsp"%>
</body>
</html>
