function LoginConfirmed() {
	alert("로그인이 필요한 서비스입니다.");
	location.href = "/finalProject_maper_local/Common/logOutProcess.jsp";
}

function emptySession() {
	alert("로그인 정보가 만료되어 로그인페이지로 이동합니다.");
	location.href = "/finalProject_maper_local/Common/logOutProcess.jsp";
}
