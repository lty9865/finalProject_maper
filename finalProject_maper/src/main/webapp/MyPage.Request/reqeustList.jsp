<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ include file="/Common/link.jsp"%>  

<table class="table table-hover boardlist">
	<thead>
		<tr align="center" class="warning">
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
		</tr>
	</thead>
	<tbody>	
		<c:forEach items="${requestScope.lvo.list}" var="post">
		<tr>
			<td>${post.no}</td>
			<td>
			<c:choose>
				<c:when test="${sessionScope.pDTO != null}">
					<a href="${pageContext.request.contextPath}/front/request?command=PostDetail&no=${post.no}">
						${post.title}
					</a>
				</c:when>
				<c:otherwise>
					${post.title}
				</c:otherwise>
			</c:choose>
			</td>
			<td>${post.pDTO.name}</td>
			<td>${post.postDate}</td>
		</tr>
		</c:forEach>
	</tbody>
</table>


<%-- Paging 처리 --%>
<c:set var="pb" value="${requestScope.lvo.pagingBean}"/>
<div class="pagingArea">
<ul class="pagination">

<!-- 
	step2 
	
	1) 이전 페이지 그룹이 있으면 화살표 보여준다
       페이징빈의 previousPageGroup 이용 
    2) 이미지에 이전 그룹의 마지막 페이지번호를 링크한다. 
        hint) startPageOfPageGroup-1 하면 됨
    
    &laquo; : 왼쪽 화살표   
 -->  
 	<c:if test="${pb.previousPageGroup}">
		<li>
		<a href="front/request?command=home&pageNo=${pb.startPageOfPageGroup-1}">&laquo;</a>
		</li>
	</c:if>
<!-- 
	step1

	 1) 현 페이지 그룹의 startPage부터 endPage까지 forEach 를 이용해 출력한다

	 2) 현 페이지가 아니면 링크를 걸어서 서버에 요청할 수 있도록 한다.
        현 페이지이면 링크를 처리하지 않는다.  
        PagingBean의 nowPage - jstl choose 를 이용  
        예) <a href="front?command=List&pageNo=...">    
 -->
	<c:forEach var="i" begin="${pb.startPageOfPageGroup}" 
				end="${pb.endPageOfPageGroup}">
		<c:choose>
			<c:when test="${pb.nowPage!=i}">
				<li><a href="front/request?command=home&pageNo=${i}">${i}</a></li>
			</c:when>
			<c:otherwise>
				<li class="active"><a href="#">${i}</a></li>
			</c:otherwise>
		</c:choose>
	</c:forEach>

<!-- 
	step3 
	
	1) 다음 페이지 그룹이 있으면 화살표 보여준다. 
	   - 페이징빈의 nextPageGroup 이용 
    2) 이미지에 이전 그룹의 마지막 페이지번호를 링크한다. 
        hint)   endPageOfPageGroup+1 하면 됨
    
    &raquo; : 오른쪽 화살표 
 --> 
 	<c:if test="${pb.nextPageGroup}">
		<li>
			<a href="front/request?command=home&pageNo=${pb.endPageOfPageGroup+1}">&raquo;
			</a>
		</li>
	</c:if>
	
</ul>  
</div>