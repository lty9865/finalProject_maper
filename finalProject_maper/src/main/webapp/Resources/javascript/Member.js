/**
 * 
 */
function idCheck() {
	if (document.frm.userId.value == "") {
		alert('아이디를 입력하여 주십시오.');
		document.frm.userId.focus();
		return;
	}
	var url = "idCheck.do?userId=" + document.frm.userId.value;
	window.open(url, "_blank_1", "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=450, height=200");
}

function idok() {
	opener.frm.userId.value = document.frm.userId.value;
	opener.frm.reid.value = document.frm.userId.value;
	self.close();
}

function joinCheck() {
	if (document.frm.userId.value == "") {
		alert("아이디를 써주세요.");
		frm.userId.focus();
		return false;
	}
	if (document.frm.userId.value.length < 5 || document.frm.userId.value.length > 12) {
		alert("아이디는 5자 이상 12자 이하로 입력해주세요.");
		frm.userId.focus();
		return false;
	}
	if (document.frm.password.value == "") {
		alert("비밀번호를 써주세요.");
		frm.password.focus();
		return false;
	}

	if (document.frm.password.value.length < 8 || document.frm.password.value.length > 20) {
		alert("비밀번호를 8~20자 이내로 입력해주세요.");
		frm.password.focus();
		return false;
	}
	if (document.frm.email.value == "") {
		alert("이메일을 입력해주세요.");
		frm.email.focus();
		return false;
	}

	if (document.frm.year.value == "") {
		alert("생년월일을 정확히 입력해주세요.");
		frm.year.focus();
		return false;
	}
	if (document.frm.month.value == "") {
		alert("생년월일을 정확히 입력해주세요.");
		frm.month.focus();
		return false;
	}
	if (document.frm.day.value == "") {
		alert("생년월일을 정확히 입력해주세요.");
		frm.day.focus();
		return false;
	}

	if (document.frm.password.value != document.frm.confirmPassword.value) {
		alert("암호가 일치하지 않습니다.");
		frm.password.focus();
		return false;
	}

	if (document.frm.reid.value.length == 0) {
		alert("중복 체크를 하지 않았습니다.");
		frm.userId.focus();
		return false;
	}
	return true;
}
