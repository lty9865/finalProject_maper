<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ include file="/Common/link.jsp"%>  

<script>
	function removePost() {
		if(confirm("게시글을 삭제하시겠습니까?")){
			document.removeForm.submit();
		} else {
			return;
		}
	}
	
	function updatePost() {
		if(confirm("게시글을 수정하시겠습니까??")){
			document.updateForm.submit();
		} else {
			return;
		}
	}
</script>


 <h3 align="left">${requestScope.rDTO.title}의 상세정보</h3>
 <ul class="list-group detailList">
   <li class="list-group-item"><strong>문의 번호</strong></li>
   <li class="list-group-item">${requestScope.rDTO.requestNum}</li>
   <li class="list-group-item"><strong>제목</strong></li>
   <li class="list-group-item">${requestScope.rDTO.title}</li>
   <li class="list-group-item"><strong>작성자</strong></li>
   <li class="list-group-item">${requestScope.rDTO.pDTO.userId}</li>
   <li class="list-group-item"><strong>작성일</strong></li>
   <li class="list-group-item">${requestScope.rDTO.postDate}</li>
   <li class="list-group-item"><strong>내용</strong></li>
   <li class="list-group-item"><pre>${requestScope.rDTO.content}</pre></li>
 
 <c:if test="${requestScope.rDTO.pDTO.id == sessionScope.pDTO.id}">
 	<form name="removeForm"
 		action="${pageContext.request.contextPath}/front/MyRequest" method="POST">
 		<input type="hidden" name="command" value="RemovePost">
 		<input type="hidden" name="no" value="${requestScope.rDTO.requestNum}">
	</form>

 	<form name="updateForm"
 		action="${pageContext.request.contextPath}/front/MyRequest" method="POST">
 		<input type="hidden" name="command" value="UpdatePostForm">
 		<input type="hidden" name="no" value="${requestScope.rDTO.requestNum}">
	</form>
	
 	<li class="list-group-item" align="right">
	<button type="button" class="btn btn-info" onclick="updatePost()">
		수정</button>
	<button type="button" class="btn btn-danger" onclick="removePost()">
		삭제</button>
 	</li>
 </c:if>
 
 </ul>