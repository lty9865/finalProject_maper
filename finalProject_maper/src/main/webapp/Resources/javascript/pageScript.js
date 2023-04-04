function pageDelete(idx) {
	if (confirm('복원은 불가능합니다. 정말 삭제하시겠습니까?')) {
		alert('삭제되었습니다.');
		location.href = '../Page/page.do?command=pageDelete&idx=' + idx;
	}
}