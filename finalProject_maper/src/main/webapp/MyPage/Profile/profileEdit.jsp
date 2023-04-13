<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../Common/link.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Page - Edit Profile</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/Resources/css/myPage.css">
<script>
	function submitForms() {
	    document.getElementById('image-upload-form').submit();
	    document.getElementById('profile-edit-form').submit();
	}
</script>
</head>
<body>
    <!-- header -->
    <%@ include file="/WEB-INF/views/Common/header.jsp" %>

    <!-- body/menu + main -->
    <div class="body-main maper-body maper-body-background">
        <div class="top-area">
            <jsp:include page="../menu.jsp"/>
        </div>
        <br>
		<h2>프로필 수정</h2>
		<hr class="title-line">
        <div class="bottom-area">
            <div class="center-area">
                <form id="profile-edit-form" action="${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyProfile.profileEditProcess" method="post" enctype="multipart/form-data">
                    <h1 class="title">${userId}</h1>
                    <hr class="blue-line">

                    <p class="field">
                    	<span class="input-label">
	                    	<strong>아이디:</strong>
					    </span>
	                    ${userId}
                    </p>
                    <hr class="blue-line">
                    
                    <p class="field">
					    <span class="input-label">
					    	<strong>비밀번호:</strong>
					    </span>
					    <input type="password" name="password" value="${password}">
					</p>
					<hr class="blue-line">
					
					<p class="field">
						<span class="input-label"><strong>이메일:</strong></span>
						<input type="text" name="emailFront" placeholder="이메일 아이디" class="email-input"> @
						<input list="domainList" name="emailBack" placeholder="도메인 주소" class="email-input">
						<datalist id="domainList">
						    <option value="gmail.com">
						    <option value="naver.com">
						    <option value="hanmail.com">
						    <option value="nate.com">
				    	</datalist>
					</p>
					<hr class="blue-line">
					
                    <input type="submit" value="정보 수정" class="custom-button" onclick="submitForm();">
					<input type="button" value="돌아가기" onclick="location.href='${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyProfile'" class="custom-button">
					<button type="button" onclick="location.href='${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyProfile.profileDelete'" class="custom-button">계정 삭제</button>
                </form>
            </div>
        </div>
    </div>
    
    <!-- the result of edit -->
    <c:if test="${not empty msg}">
        <script type="text/javascript">
            alert("{${msg}")
        </script>
    </c:if>

    <!-- footer -->
    <%@ include file="/WEB-INF/views/Common/footer.jsp" %>
</body>
</html>
