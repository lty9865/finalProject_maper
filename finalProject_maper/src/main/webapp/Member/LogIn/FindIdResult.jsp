<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>아이디 찾기 결과</title>
    <script type="text/javascript" src="../../Resources/javascript/Member.js"></script>
</head>
<body>
    <h1>아이디 찾기 결과</h1>
    
    <% String userid = (String)request.getAttribute("userid"); %>
    
    <% if (userid != null) { %>
        <p>회원님의 아이디는 <%=userid%>입니다.</p>
    <% } else { %>
        <p>입력하신 정보에 해당하는 회원 정보가 없습니다.</p>
    <% } %>
</body>
</html>