<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>  

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


 <h3 align="left"><${requestScope.postVO.title}>의 상세정보</h3>
 <ul class="list-group detailList">
   <li class="list-group-item"><strong>게시물 번호</strong></li>
   <li class="list-group-item">${requestScope.postVO.no}</li>
   <li class="list-group-item"><strong>제목</strong></li>
   <li class="list-group-item">${requestScope.postVO.title}</li>
   <li class="list-group-item"><strong>작성자</strong></li>
   <li class="list-group-item">${requestScope.postVO.memberVO.name}</li>
   <li class="list-group-item"><strong>작성일시</strong></li>
   <li class="list-group-item">${requestScope.postVO.timePosted}</li>
   <li class="list-group-item"><strong>조회수</strong></li>
   <li class="list-group-item">${requestScope.postVO.hits}</li>
   <li class="list-group-item"><strong>줄거리</strong></li>
   <li class="list-group-item"><pre>${requestScope.postVO.content}</pre></li>
 
 <c:if test="${requestScope.postVO.memberVO.id == sessionScope.memberVO.id}">
 	<form name="removeForm"
 		action="${pageContext.request.contextPath}/front" method="POST">
 		<input type="hidden" name="command" value="RemovePost">
 		<input type="hidden" name="no" value="${requestScope.postVO.no}">
	</form>

 	<form name="updateForm"
 		action="${pageContext.request.contextPath}/front" method="POST">
 		<input type="hidden" name="command" value="UpdatePostForm">
 		<input type="hidden" name="no" value="${requestScope.postVO.no}">
	</form>
	
 	<li class="list-group-item" align="right">
	<button type="button" class="btn btn-info" onclick="updatePost()">
		수정</button>
	<button type="button" class="btn btn-danger" onclick="removePost()">
		삭제</button>
 	</li>
 </c:if>
 
 </ul>