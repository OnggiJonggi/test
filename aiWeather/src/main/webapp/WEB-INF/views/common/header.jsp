<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <meta charset="UTF-8">
    <title>헤더</title>
</head>
<body>
	<c:set var="root" value="${pageContext.request.contextPath}"/>
	
	<c:set var="loginUser" value="${loginUser}"/>
	
	<script>
		let msg = "${alertMsg}"
		if(msg!=""){
			alert(msg);
		}
	</script>
	<c:remove var="alertMsg"/>
	
	
	
	
	<c:choose>
		<c:when test="${sessionScope.loginUser == null }">
			<a href="${pageScope.root }/insert.me">회원가입</a>
		</c:when>
		<c:otherwise>
			${sessionScope.loginUser.userName}님 안녕하세요
			<br>
			<a href="${pageScope.root }/mypage.me">마이페이지</a>
		</c:otherwise>
	</c:choose>
	 
</body>
</html>