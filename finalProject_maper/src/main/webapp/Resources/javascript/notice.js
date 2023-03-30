// 공지사항 작성 시 공란 유효성 검사
function noticeBlank() {
	if (document.writeFrm.title.value.length == 0) {
		alert('제목을 입력하세요.');
		writeFrm.title.focus();
		return false;
	}
	if (document.writeFrm.contents.value.length == 0) {
		alert('내용을 입력하세요.');
		writeFrm.contents.focus();
		return false;
	}
	return true;
}

function noticeDelete(idx) {
	if (confirm('복원은 불가능합니다. 정말 삭제하시겠습니까?')) {
		alert('삭제되었습니다.');
		location.href = '../Notice/notice.do?command=delete&idx=' + idx;
	}
}