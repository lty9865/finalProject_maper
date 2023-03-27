<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>  
<jsp:forward page="/MyPage/MyProfile.do">
	<jsp:param value="home" name="command"/>
</jsp:forward>