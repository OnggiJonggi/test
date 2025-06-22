<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"/>
	<title>aiWeather</title>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>

	첫 시안은 YAMI가 되었습니다. 숭고한 희생...
	<br>
	
	<div id="serverIpDiv">
		<button id="getIpBtn">서버 내부 IP 가져오기</button>
	</div>
  
    <hr>
    perplexity 써먹기
    <form action="${root}/search.ai" method="post">
      입력해요 : <input type="text" name="content" required/> <br />
      <button type="submit">DORO!!</button>
    </form>
    
    
    <hr>
    <form>
    	아이디 : <input type="text" name="userId" required/> <br />
    	비번 : <input type="text" name="userPwd" required/> <br />
		권한 :
		<select name="roleType" required>
			<option value="admin">관리자</option>
			<option value="normal">일반</option>
		</select> <br />
    	<button id="memberLogin" type="button">로그인</button>
    </form>
    <div id="memberLoginResult">
    로그인 해라. 안 그럼 영상통화/화면공유 못 한다.
    </div>
    
    
    <hr>
    <a href="${root}/videoCall/main">영상통화</a>
    <hr>
    <a href="${root}/screenShare/main">화면공유</a>
    
    
<script type="text/javascript">
$(document).ready(function(){
	
	//내부 ip주소 알아보기
    $('#getIpBtn').click(function(){
        $.ajax({
            url : "${root}/findServerPrivateIP",
            method : "post",
            data : {},
            success : function(result){
                $('#serverIpDiv').text('서버 내부 ip주소 : ' + result);
            },
            error : function(){
                console.log('통신 실패');
            }
        });
    });
    
	//로그인
    $('#memberLogin').click(function(){
		let userId = $("input[name='userId']").val();
		let userPwd = $("input[name='userPwd']").val();
		let roleType = $("input[name='roleType']").val();
        $.ajax({
            url : "${root}/login",
            method : "post",
            data : {userId,userPwd,roleType},
            success : function(result){
            	if(result == 'pass'){
	                $('#memberLoginResult')
	                	.text(userId+'야, 가라!');
            	}else{
	                $('#memberLoginResult')
	                	.text('로그인 실패!');
            	}
            },
            error : function(){
                console.log('통신 실패');
            }
        });
    });
});

</script>
    
  </body>
</html>
