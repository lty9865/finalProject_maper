function confirmDelete() {
    var password = document.getElementsByName("password")[0].value;
    if (password.trim() === "") {
        alert("비밀번호를 입력하세요");
        return;
    }
    if (confirm("정말로 계정을 삭제하시겠습니까? 이 결정은 되돌릴 수 없습니다.")) {
        document.forms[0].submit();
    }
}
