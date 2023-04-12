<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/Common/link.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
    href="${pageContext.request.contextPath}/Resources/css/myPage.css">
<title>Request Post</title>
<style>
    .input-row {
        display: flex;
        flex-direction: row;
        margin-bottom: 1rem;
    }
    .input-label {
        margin-bottom: 0.5rem;
    }
    .input-field {
        width: 100%;
    }
    .required {
        color: red;
    }
    .btn-margin {
    	margin-left: 50.5rem;
    }
</style>
<script>
    function resetFields() {
        document.getElementsByName("title")[0].value = "";
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
    <jsp:include page="../menu.jsp" />

    <!-- body/main -->
    <div class="maper-body">
        <c:set var="selectedRequest" value="${requestScope.selectedRequest}" />
    
        <form
            action="${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyRequest.requestPostProcess"
            method="POST">
            <input type="hidden" name="command" value="WritePost">
            <div class="input-row">
                <label class="input-label" for="title">제목 <span class="required">*</span></label>
                <input class="input-field" type="text" name="title" id="title"
                value="${selectedRequest.title}" placeholder="게시글 제목을 입력하세요"
                required="required"> <input type="hidden"
                name="originalTitle" value="${selectedRequest.title}" />
            </div>
            <div class="input-row">
			    <label class="input-label">작성자 <span class="required">*</span></label>
			    <div>
			        <span>${sessionScope.userId}</span>
			        <input type="hidden" name="userId" value="${sessionScope.userId}" required="required">
			    </div>
			</div>
            <div class="input-row">
                <label class="input-label" for="content">내용 <span class="required">*</span></label>
                <textarea class="input-field" cols="90" rows="15" name="content" id="content"
                    required="required" placeholder=" 본문내용을 입력하세요">${selectedRequest.content}</textarea>
            </div>
            <br>
            <div class="btnArea">
                <button type="submit" class="custom-button btn-margin">작성 완료</button>
                <button type="reset" class="custom-button" onclick="resetFields()">다시
                    쓰기</button>
            </div>
        </form>
    </div>

    <!-- footer -->
    <%@ include file="/WEB-INF/views/Common/footer.jsp"%>
</body>
</html>
