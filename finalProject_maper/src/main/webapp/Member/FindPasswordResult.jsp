<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>비밀번호 찾기 결과</title>
    <script type="text/javascript" src="../script/Member.js"></script>
</head>
<body>
    <h1>비밀번호 찾기 결과</h1>
    
    <% String password = (String)request.getAttribute("password"); %>
    
    <% if (password != null) { %>
        <p>회원님의 비밀번호는 <%=password%>입니다.</p>
    <% } else { %>
        <p>입력하신 정보에 해당하는 회원 정보가 없습니다.</p>
    <% } %>
</body>
</html>