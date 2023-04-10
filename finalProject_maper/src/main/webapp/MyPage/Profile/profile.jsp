<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../../Common/link.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Page - My Profile</title>
<link rel="stylesheet" href="../Resources/css/myPage.css">
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
            <div class="left-area">
                <div class="profile-img">
                    <form id="profile-image-form" action="imageChoice.jsp" method="post" enctype="multipart/form-data">
                        <div class="profile-image-container">
                            <c:choose>
                                <c:when test="${not empty sfile}">
                                    <img src="${pageContext.request.contextPath}/Uploads/Profile/${sfile}" alt="Profile Image">
                                </c:when>
                                <c:otherwise>
                                    <img src="${pageContext.request.contextPath}/Uploads/Profile/${ofile}" alt="Profile Image">
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </form>
                </div>
            </div>
            <div class="right-area">
                <h1 class="title">${userId}</h1>
                <hr class="blue-line">
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
                <p class="field">
                    <strong>생년월일:</strong> 
                </p>
                ${birth}
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
