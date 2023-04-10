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
  <title>Admins Request Answer</title>
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
</head>
<body>
  <!-- header -->
  <%@ include file="/WEB-INF/views/Common/header.jsp"%>

  <!-- body/menu -->
  <jsp:include page="/MyPage/adminsMenu.jsp" />

  <!-- body/main -->
  <c:set var="selectedRequest" value="${requestScope.selectedRequest}" />
  
  <form action="${pageContext.request.contextPath}/MyPage/MyPageFront?command=Admins.requestReplyProcess" method="POST">
    <input type="hidden" name="command" value="WritePost">
    <table class="table">
      <tr>
        <td>
          <h4>제목</h4>
          <input type="text" name="title" value="RE: ${selectedRequest.title}"
            readonly required="required">
          <input type="hidden" name="originalTitle" value="${selectedRequest.title}" />
        </td>
      </tr>
      <tr>
        <td>
          <h4>작성자</h4> 
          <c:choose>
                <c:when test="${fn:substringBefore(sessionScope.userId_admins, '_') == 'admins' and fn:substringAfter(sessionScope.userId_admins, '_') == '1'}">
                    <input type="text" name="userId" value="admins" readonly required="required">
                </c:when>
                <c:otherwise>
                    <input type="text" name="userId" value="${sessionScope.userId}" readonly required="required">
                </c:otherwise>
            </c:choose>
        </td>
      </tr>
      <tr>
        <td>
          <h4>본문 내용</h4>
          <textarea cols="90" rows="15" name="content" required="required" 
            placeholder="본문내용을 입력하세요"></textarea>
        </td>
      </tr>
      <tr>
        <td>
          <div class="btnArea">
            <button type="submit" class="btn-success">작성 완료</button>
            <button type="reset" class="btn-warning" onclick="resetFields()">다시 쓰기</button>
          </div>
        </td>
      </tr>
    </table>
  </form>
  
  <!-- footer -->
  <%@ include file="/WEB-INF/views/Common/footer.jsp"%>
</body>
</html>
