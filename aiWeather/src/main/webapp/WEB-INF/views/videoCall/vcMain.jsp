<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영상통화</title>
</head>
<body>
	<%@include file="/WEB-INF/views/common/header.jsp" %>

	<h3>새로운 방 생성</h3>
	<form id="createRoomForm" action="${pageScope.root}/videoCall/createRoom" method="post">
		<div>
			<label for="vcName"><b>방 이름</b></label><br>
			<input type="text" id="vcName" placeholder="방 이름" name="vcName" required>
			<div id="vcNameResult"></div>
		</div>
		<div>
			<label for="maxParticipants">최대 참여인원</label>
			<input type="number" id="maxParticipants" name="maxParticipants">
			<div id="mpResult"></div>
		</div>
		
		<div id="finalResult"></div>
		<div>
			<button type="submit" id="insertSubmit" disabled>만들기</button>
			<button type="button" id="insertReset">초기화</button>
		</div>
	</form>
	
	<hr>
	
	기존 방 참여하기
	
	
	<script type="text/javascript">
	//모든 검증이 true여야 sumbmit버튼 활성화됨
	let vcNamePass = false;
	let mpPass = false;
	
	//방 이름 검증
	document.getElementById('vcName').addEventListener('input', function() {
		let vcNameResult = document.getElementById("vcNameResult");
	    if(this.value.trim().length > 30){
	    	vcNameResult.innerHTML = "이름이 너무 길어요";
	    	vcNamePass = false;
	    }else if(this.value.trim().length < 1){
	    	vcNameResult.innerHTML = "이름을 입력해 주세요";
	    	vcNamePass = false;
	    }else{
	    	vcNameResult.innerHTML = "좋아요!";
	    	vcNamePass = true;
	    }
		toggleSubmit();
	});
	
	//참여인원 수 검증
	document.getElementById('maxParticipants').addEventListener('input', function() {
		let mpResult = document.getElementById("mpResult");
	    if(this.value <= 0 || this.value>10){
	    	mpResult.innerHTML = "최대 참여인원을 10명 이하로 작성해 주세요.";
	    	mpPass = false;
	    }else{
	    	mpResult.innerHTML = "좋아요!";
	    	mpPass = true;
	    }
		toggleSubmit();
	});
	
	document.getElementById("insertReset").addEventListener('click', function(){
		if(confirm("진짜?")){
			document.getElementById("createRoomForm").reset();
			vcNamePass = false;
			mpPass = false;
			
			document.getElementById("vcNameResult").innerHTML = "";
			document.getElementById("mpResult").innerHTML = "";
			document.getElementById("finalResult").innerHTML = "";
			toggleSubmit();
		}
	});
	
	let toggolePassed = false;
	function toggleSubmit(){
		let finalResult = document.getElementById("finalResult");
		if(vcNamePass && mpPass){
			setButtonState('active');
			toggolePassed = true;
		}else if(toggolePassed == true){
			setButtonState('disabled');
			toggolePassed = false;
		}
	};
	
	function setButtonState(state) {
		let insertSubmit = document.getElementById("insertSubmit");
		let finalResult = document.getElementById("finalResult");
	    
	    switch(state) {
	        case 'disabled':
				finalResult.innerHTML = "모든 필수 항목을 확인해 주세요";
	        	insertSubmit.disabled = true;
	            break;
	        case 'active':
				finalResult.innerHTML = "좋아요!";
	            insertSubmit.disabled = false;
	            break;
	    }
	}
	</script>
</body>
</html>
