<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://cdn.jsdelivr.net/npm/lodash@4.17.21/lodash.min.js"></script>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>

	<form action="${pageScope.root }/insert.me" id="insertForm" method='post'>
		*아이디 : <input type="text" id="userId" placeholder="이메일 주소" name="userId" required>@
				
				<input list = "domain" name="domain" id="domainList">
				<datalist  id="domain">
					<option value="naver.com">naver.com</option>
					<option value="goole.com">google.com</option>
				</datalist ><br>
		<div id="checkIdResult">아이디를 입력해주세요.</div><br>
		
		*비밀번호 : <input type="password" id="userPwd" placeholder="비밀번호" name="userPwd" required><br>
		*비밀번호 확인 : <input type="password" id="checkPwd" placeholder="비밀번호 재확인" required><br>
		<div id="checkPwdResult">비밀번호를 입력해주세요.</div>
		
		*이름 : <input type="text" id="userName" placeholder="닉네임" name="userName" required><br>
		
		<button type="button" id="insertSubmit" disabled>회원가입</button>
		<button type="button" id="reset">지워부려</button>
		<br>
		<div id="finalResult">뭘봐요</div>
	</form>
	
	
	<script type="text/javascript">
	
		//모든 검증이 true여야 sumbmit버튼 활성화됨
		let idPass = false;
		let pwdPass = false;
		let namePass = false;
		
		//아이디 검증
		document.getElementById("userId").addEventListener('input', function(){
			idConfirm();
		});
		
		//도메인이 바뀌면 idPass = false;
		document.getElementById("domainList").addEventListener('change', function(){
			idPass = false;
			toggleSubmit();
			idConfirm();
		});
		
		//아이디 확인
		function idConfirm(){
			const userId = document.getElementById("userId").value;
		    const regex = /^[A-Za-z0-9]{1,30}$/;
		    
		    if (regex.test(userId)) {
		    	if(document.getElementById("domainList").value !=""){
			    	debounce();
		    	}else{
		    		document.getElementById('checkIdResult').textContent = userId
		    	}
		    } else {
		    	document.getElementById('checkIdResult').textContent = "특수문자 제외 1 ~ 30글자로 입력해줘";
		    	idPass = false;
		    	toggleSubmit();
		    };
		}
		
		//_.debounce : 여러 번 호출되더라도 지정된 시간이 지나야 함수가 발동됨
		//ajax로 아이디 중복확인
		let debounce = _.debounce(function() {
			let userId = document.getElementById("userId").value;
			let domain = document.getElementById("domainList").value;
			$.ajax({
			      url: "checkUserId.me",
			      data: {userId : userId, domain : domain},
			      success: function(result) {
			    	console.log("아이디 검증 실행됨");
					if(result == "pass"){
    					document.getElementById("checkIdResult").innerHTML = "'"+userId+"@"+domain+"'라는 기합찬 아이디는 통과해도 좋다";
    					idPass = true;
    					toggleSubmit();
					}else if(result == 'noPass'){
						document.getElementById("checkIdResult").innerHTML = "'"+userId+"@"+domain+"'라는 흐접한 아이디는 이미 누군가가 쓰는군";
						idPass = false;
						toggleSubmit();
					}else{
						document.getElementById("checkIdResult").innerHTML = "서버 에러";
						idPass = false;
						toggleSubmit();
					}
			      },
			      error: function(){
					document.getElementById("checkIdResult").innerHTML = "통신 오류!";
					idPass = false;
					toggleSubmit();
			      }
		      });
		},300);
	
		//비밀번호가 4글자 이상, 특수문자 제외
		document.getElementById('userPwd').addEventListener('input', function() {
		    const pwd = this.value;
		    const regex = /^[A-Za-z0-9]{4,30}$/;
		    const checkPwdResult = document.getElementById('checkPwdResult');
		    
		    if (regex.test(pwd)) {
		    	confirmPwd();
		    } else {
		    	checkPwdResult.textContent = "특수문자 제외 4 ~ 30글자로 입력해줘";
		    	pwdPass = false;
		    	toggleSubmit();
		    }
		});
	
		//비밀번호와 비번확인이 동일한지 확인
		document.getElementById("checkPwd").addEventListener('input', confirmPwd);
		
		function confirmPwd(){
			let checkPwdResult = document.getElementById('checkPwdResult');
			let checkPwd = document.getElementById('checkPwd').value;
			let userPwd = document.getElementById('userPwd').value;
			
			if(checkPwd == ""){
				if(userPwd == ""){
					checkPwdResult.innerHTML = "비밀번호를 입력해주세요.";
				}else{
					checkPwdResult.innerHTML = "사용 가능한 비밀번호입니다.";
				}
			}else if(checkPwd == userPwd){
				checkPwdResult.innerHTML = "합격!";
				pwdPass = true;
				toggleSubmit();
			}else{
				checkPwdResult.innerHTML = "비밀번호가 일치하지 않습니다";
				pwdPass = false;
				toggleSubmit();
			}
		};
		
		//리셋 버튼과 확인을 누르면 리셋됨
		document.getElementById("reset").addEventListener('click', function(){
			if(confirm("진짜?")){
				document.getElementById("insertForm").reset();
				idPass = false;
				pwdPass = false;
				namePass = false;
				toggleSubmit();
			}
		});
		
		//엔터 버튼을 누르면 회원가입
		document.getElementById('insertForm').addEventListener('keydown', function(event) {
		  if (event.key === 'Enter'
				  && document.getElementById("insertSubmit").disabled == false
				  ) {
			  document.getElementById('insertForm').submit();
		  }
		});
		
		//제출 버튼을 클릭하면 회원가입
		document.getElementById('insertSubmit').addEventListener('click', function(event) {
			document.getElementById('insertForm').submit();
		});
		
		//닉네임 검증
		document.getElementById('userName').addEventListener('input', function() {
		    if(this.value.length > 30){
		    	document.getElementById("finalResult").innerHTML = "이름이 너무 길어요";
		    	namePass = false;
		    	toggleSubmit();
		    }else if(this.value.length < 1){
		    	document.getElementById("finalResult").innerHTML = "이름을 입력해 주세요";
		    	namePass = false;
		    	toggleSubmit();
		    }else{
		    	document.getElementById("finalResult").innerHTML = "뭘봐요";
		    	namePass = true;
		    	toggleSubmit();
		    }
		});
		
		
		//검증 완료되면 submit버튼 활성화
		function toggleSubmit(){
			if(idPass && pwdPass && namePass){
				document.getElementById("insertSubmit").disabled = false;
			}else{
				document.getElementById("insertSubmit").disabled = true;
			}
		};
		
		
	</script>
	
</body>
</html>