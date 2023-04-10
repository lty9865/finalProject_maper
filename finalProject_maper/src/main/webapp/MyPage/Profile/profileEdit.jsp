<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../Common/link.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Page - Edit Profile</title>
<link rel="stylesheet" href="../Resources/css/myPage.css">
<style>
    .blue-line {
        border: 0;
        border-top: 1px solid blue;
        margin: 10px 0;
    }
</style>
</head>
<body>
    <!-- header -->
    <%@ include file="/WEB-INF/views/Common/header.jsp" %>

    <!-- body/menu + main -->
    <div class="body-main maper-body maper-body-background">
        <div class="top-area">
            <jsp:include page="../menu.jsp"/>
        </div>
        <div class="bottom-area">
            <div class="left-area">
                <p class="field"><strong>프로필 사진:</strong></p>
				<form id="image-upload-form" action="${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyProfile.profileImageChoice" method="post" enctype="multipart/form-data">
				    <img src="${empty sFile ? oFile : sFile}" alt="Profile Image" onclick="location.href='${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyProfile.profileImageChoice'">
				    <input type="file" name="image" form="image-upload-form">
				</form>
            </div>
            <div class="right-area">
                <form action="${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyProfile.profileEditProcess" method="post" enctype="multipart/form-data">
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
					
					<p class="field">
					    <span class="input-label"><strong>생년월일:</strong></span>
					    <input type="date" name="birth" value="${birth}">
					</p>
                    <hr class="blue-line">
                    <br>
                    
                    <input type="submit" value="정보 수정" class="custom-button">
					<input type="button" value="돌아가기" onclick="location.href='${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyProfile'" class="custom-button">
					<button type="button" onclick="location.href='${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyProfile.profileDelete';" class="custom-button">계정 삭제</button>
                </form>
            </div>
        </div>
    </div>

    <!-- footer -->
    <%@ include file="/WEB-INF/views/Common/footer.jsp" %>
</body>
</html>
