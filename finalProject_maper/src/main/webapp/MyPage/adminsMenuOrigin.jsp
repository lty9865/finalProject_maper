<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/Common/link.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>My Page</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Resources/css/myPage.css">
</head>
<body>
  <div class="container maper-body myPage-menu-wrapper">
    <div class="folder-myPage-menu-container">
      <ul class="folder-myPage-horizontal-menu">
        <li class="mapper-myPage-menu-item">
          <a href="${pageContext.request.contextPath}/MyPage/MyPageFront?command=Admins.memberBoard">
            회원 리스트
          </a>
        </li>
      </ul>
    </div>
    <div class="folder-myPage-menu-container">
      <ul class="folder-myPage-horizontal-menu">
        <li class="mapper-myPage-menu-item">
          <a href="${pageContext.request.contextPath}/MyPage/MyPageFront?command=Admins.requestBoard">
            문의 리스트
          </a>
        </li>
      </ul>
    </div>
    <div class="folder-myPage-menu-container">
      <ul class="folder-myPage-horizontal-menu">
        <li class="mapper-myPage-menu-item">
          <a href="${pageContext.request.contextPath}/MyPage/MyPageFront?command=Admins.reportBoard">
          신고 리스트
          </a>
        </li>
      </ul>
    </div>
  </div>
  <br>
</body>
</html>
