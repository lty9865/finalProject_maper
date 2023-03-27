<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/Common/link.jsp"%>

<form action="${pageContext.request.contextPath}/front/MyRequest" method="POST">
	<input type="hidden" name="command" value="WritePost">
	<table class="table">
		<tr>
			<td>
				<h4>제목</h4>
				<input type="text" name="title" 
					placeholder="게시글 제목을 입력하세요" 
					required="required">
			</td>
		</tr>
		<tr>
			<td>
				<h4>본문 내용</h4>
				<textarea cols="90" rows="15" name="content" 
					required="required" 
					placeholder=" 본문내용을 입력하세요"></textarea>
			</td>
		</tr>
		
		<tr>
			 <td>
				<div class="btnArea">
					<button type="submit" class="btn-success">게시글 올리기</button>
					&nbsp;&nbsp; &nbsp;&nbsp;
					<button type="reset" class="btn-warning">다시 쓰기</button>
				</div>
			 </td>
		</tr>
	</table>
</form>