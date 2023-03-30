function validateForm() {
    var password = document.getElementById("password").value;
    if (password === null || password === "") {
        alert("비밀번호를 입력하세요.");
        return false;
    }
    return true;
}

window.onload = function() {
    document.getElementById("password").value = "";
}
