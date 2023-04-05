function confirmEdit() {
    return confirm("정말로 이 글을 수정하시겠습니까?");
}

function confirmDelete() {
    if (confirm("정말로 이 글을 삭제하시겠습니까?")) {
        var deleteForm = document.getElementById("deleteForm");
        var requestNum = deleteForm.querySelector("input[name='requestNum']").value;
        var xhr = new XMLHttpRequest();
        var url = deleteForm.getAttribute("action");
        var params = "requestNum=" + requestNum;

        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                // Success message and redirection
                alert("삭제가 완료됐습니다.");
                // Use contextPath variable in the URL
                window.location.href = contextPath + "/MyPage/MyPageFront?command=MyRequest";
            }
        };

        xhr.open("POST", url, true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.send(params);
    }
}


function showErrorAndGoBack() {
    var errorMsg = document.getElementById('error-message').innerText.trim();
    if (errorMsg) {
        alert(errorMsg);
        window.history.back();
    }
}
