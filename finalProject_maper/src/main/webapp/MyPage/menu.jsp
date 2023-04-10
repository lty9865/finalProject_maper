<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/Common/link.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
          <a href="${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyProfile" class="<c:if test="${selectedMenuItem == 'MyProfile'}">selected</c:if>">
            내 정보
          </a>
        </li>
      </ul>
    </div>
    <div class="folder-myPage-menu-container">
      <ul class="folder-myPage-horizontal-menu">
        <li class="mapper-myPage-menu-item">
          <a href="${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyRequest&page=1" class="<c:if test="${selectedMenuItem == 'MyRequest'}">selected</c:if>">
            내 문의
          </a>
        </li>
      </ul>
    </div>
    <div class="folder-myPage-menu-container">
      <ul class="folder-myPage-horizontal-menu">
        <li class="mapper-myPage-menu-item">
          <a href="${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyLike" class="<c:if test="${selectedMenuItem == 'MyLike'}">selected</c:if>">
            내 마음에 든 책
          </a>
        </li>
      </ul>
    </div>
  </div>
  <br>
</body>
</html>
