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
  <div class="container">
    <div class="mapper-myPage-menu-container">
      <ul class="mapper-myPage-horizontal-menu">
        <li class="mapper-myPage-menu-item">
          <a href="${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyProfile">
            내 정보
          </a>
        </li>
      </ul>
    </div>
    <div class="mapper-myPage-menu-container">
      <ul class="mapper-myPage-horizontal-menu">
        <li class="mapper-myPage-menu-item">
          <a href="${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyRequest&page=1">
            내 문의
          </a>
        </li>
      </ul>
    </div>
    <div class="mapper-myPage-menu-container">
      <ul class="mapper-myPage-horizontal-menu">
        <li class="mapper-myPage-menu-item">
          <a href="${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyLike">
            내 마음에 든 책
          </a>
        </li>
      </ul>
    </div>
  </div>
</body>
</html>
