<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8"/>
    <title>안아달라고.</title>
  </head>
  <body>
    <%@include file="/WEB-INF/views/common/header.jsp"%>
	
    <form action="${root }/sendEmail" method="post">
      이메일 : <input type="email" name="toAddress" required/> <br />
      제목 : <input type="text" name="subject" required/> <br />
      내용 : <input type="text" name="content" required/> <br />
      <button type="submit">DORO!</button>
    </form>
    
    <hr>
    <form action="${root }/search.ai" method="post">
      입력해요 : <input type="text" name="content" required/> <br />
      <button type="submit">DORO!!</button>
    </form>
    
    
  </body>
</html>
