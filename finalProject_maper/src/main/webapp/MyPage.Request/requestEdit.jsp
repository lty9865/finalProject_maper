<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ include file="/Common/link.jsp"%>  

 <form action="${pageContext.request.contextPath}/front/MyRequest" method="POST" >
	<input type="hidden" name="command" value="UpdatePost"></input>
	<input type="hidden" name="no" value="${rDTO.requestNum}"></input>
	<table class="table">
		<tr>
			<td>
				<strong>제목</strong>
			<br>
				<input type="text" name="title" value="${rDTO.title}"
					required="required">
			</td>
		</tr>
		
		<tr>
			<td>
				<strong>내용</strong>
			<br>
				<textarea cols="90" rows="15" name="content" 
					required="required">${rDTO.content}</textarea>
			</td>
		</tr>
		
		<tr>
			 <td colspan="2">
				<div class="btnArea">
					<button type="submit" class="btn btn-success">수정 완료</button>
					&nbsp;&nbsp; &nbsp;&nbsp;
					<button type="reset" class="btn btn-warning">글 초기화</button>
				</div>
			 </td>
		</tr>
	</table>
</form>