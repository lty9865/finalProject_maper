function signUpBlank() {
	var pw = document.signUpFrm.PWD.value;
	var pw2 = document.signUpFrm.PWD2.value;
	var num = pw.search(/[0-9]/g);
	var eng = pw.search(/[a-z]/ig);
	var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

	if (document.signUpFrm.userId.value.length == 0) {
		alert('아이디를 입력하세요.');
		signUpFrm.userId.focus();
		return false;
	}
	if (document.signUpFrm.userId.value.length > 10) {
		alert('아이디는 10글자 이내로 입력해주세요.');
		signUpFrm.userId.focus();
		return false;
	}
	if (document.signUpFrm.reid.value.length == 0) {
		alert('아이디 중복확인을 해주세요.');
		signUpFrm.userId.focus();
		return false;
	}
	if (document.signUpFrm.PWD.value.length == 0) {
		alert('비밀번호를 입력하세요.');
		signUpFrm.PWD.focus();
		return false;
	}
	if (document.signUpFrm.PWD2.value.length == 0) {
		alert('비밀번호 확인란을 작성하세요.');
		signUpFrm.PWD2.focus();
		return false;
	}
	if (pw != pw2) {
		alert('비밀번호가 다릅니다.');
		signUpFrm.PWD2.focus();
		return false;
	}
	if (document.signUpFrm.EMAIL.value.length == 0) {
		alert('이메일을 입력하세요.');
		signUpFrm.EMAIL.focus();
		return false;
	}

	if (pw.length < 10 || pw.length > 20) {
		alert("10자리 ~ 20자리 이내로 입력해주세요.");
		return false;
	} else if (pw.search(/\s/) != -1) {
		alert("비밀번호는 공백 없이 입력해주세요.");
		return false;
	} else if ((num < 0 && eng < 0) || (eng < 0 && spe < 0) || (spe < 0 && num < 0)) {
		alert("영문,숫자, 특수문자 중 2가지 이상을 혼합하여 입력해주세요.");
		return false;
	} else {
		console.log("통과");
		return true;
	}
}

function idCheck() {
	if (document.signUpFrm.userId.value == "") {
		alert('아이디를 입력하여 주십시오.');
		document.signUpFrm.userId.focus();
		return false;
	}
	if (document.signUpFrm.userId.value.length > 10) {
		alert('아이디는 10글자 이내로 입력해주세요.');
		signUpFrm.userId.focus();
		return false;
	}
	var url = "idCheck.do?userId=" + document.signUpFrm.userId.value;
	window.open(url, "_blank_1", "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=450, height=200");
}

function idok() {
	opener.signUpFrm.userId.value = document.signUpFrm.userId.value;
	opener.signUpFrm.reid.value = document.signUpFrm.userId.value;
	self.close();
}