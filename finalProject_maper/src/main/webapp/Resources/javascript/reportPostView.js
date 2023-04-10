var contextPath;

function confirmDelete() {
    if (confirm("정말로 신고 글이 처리 완료됐습니까?")) {
        var deleteForm = document.getElementById("deleteForm");
        deleteForm.submit();
    }
}

function showErrorAndGoBack() {
    var errorMessage = document.getElementById("error-message").textContent.trim();
    if (errorMessage !== "") {
        alert(errorMessage);
        history.back();
    }
}
