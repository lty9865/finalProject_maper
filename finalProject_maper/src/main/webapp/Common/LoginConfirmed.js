 function LoginConfirmed(){
	var result = true;
	if(sessionStorage.getItem("userId") == null){
		alert("로그인이 필요한 서비스입니다.");
		location.href="../Login/login.do";
		result = false;
	}
	return result;
}

