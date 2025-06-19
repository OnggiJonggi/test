<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8"/>
    <title>aiWeather</title>
  </head>
  <body>
  첫 시안은 YAMI가 되었습니다. 숭고한 희생...
    <hr>
    <form action="${root }/search.ai" method="post">
      입력해요 : <input type="text" name="content" required/> <br />
      <button type="submit">DORO!!</button>
    </form>
  </body>
</html>
