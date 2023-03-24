<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/Common/link.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- header -->
	<div class="maper-header">
		<div class="maper-header-font">
			<div class="left">
				<a class="maper-header-font-1" href="login.jsp">MAPER</a>
			</div>
			<div class="right" align="right" style="">
				<input type="text" class="form-control" id="searchKeyword"
					placeholder="Enter Keyword"
					onKeypress="javascript:if(event.keyCode==13) {test()}">
				<script>
					function test() {
						alert("Enter Key 입력 감지 \n함수 실행.");
					}
				</script>
			</div>
		</div>
	</div>
	<!-- body -->
	<table class="maper-body table table-striped-columns">
		<colgroup>
			<col width="15%" />
			<col width="35%" />
			<col width="15%" />
			<col width="*" />
		</colgroup>

		<tr>
			<td>번호</td>
			<td>${ dto.idx }</td>
			<td>작성자</td>
			<td>관리자</td>
		</tr>
		<tr>
			<td>작성일</td>
			<td>${ dto.postdate }</td>
			<td>조회수</td>
			<td>${ dto.visitCount }</td>
		</tr>
		<tr>
			<td>제목</td>
			<td colspan="3">${ dto.title }</td>
		</tr>
		<tr>
			<td>내용</td>
			<td colspan="3" height="100">${ dto.content }</td>
		</tr>
	</table>
	<table class="maper-body" style="margin-top: 30px;">
		<tr align="center">
			<td>
				<div class="btn-group" role="group" aria-label="Basic example">
					<button type="button" class="btn btn-primary" style="width: 150px;" onclick="location.href='../Notice/notice.do';">돌아가기</button>
					<button type="button" class="btn btn-primary" style="width: 150px;" onclick="location.href='../Notice/noticeedit.do?mode=edit&idx=${ param.idx }';">수정하기</button>
					<button type="button" class="btn btn-primary" style="width: 150px;" onclick="noticeDelete(${dto.idx})">삭제하기</button>
				</div>
			</td>
		</tr>
	</table>
</body>
</html>