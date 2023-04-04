function bookDelete(idx) {
	if (confirm('복원은 불가능합니다. 정말 삭제하시겠습니까?')) {
		alert('삭제되었습니다.');
		location.href = '../Book/book.do?command=bookDelete&idx=' + idx;
	}
}

// 페이지 작성 시 공란 유효성 검사
function bookBlank() {
	if (document.bookWriteFrm.title.value.length == 0) {
		alert('제목을 입력하세요.');
		bookWriteFrm.title.focus();
		return false;
	}

	if (document.bookWriteFrm.country.value.length == 0) {
		alert('국가를 입력하세요.');
		bookWriteFrm.country.focus();
		return false;
	}
	if (document.bookWriteFrm.city.value.length == 0) {
		alert('도시를 입력하세요.');
		bookWriteFrm.city.focus();
		return false;
	}
	if (document.bookWriteFrm.bookDate.value.length == 0) {
		alert('작성일자를 입력하세요.');
		bookWriteFrm.bookDate.focus();
		return false;
	}
		if (document.bookWriteFrm.ofile.value.length == 0) {
		alert('파일 첨부는 필수입니다.');
		bookWriteFrm.ofile.focus();
		return false;
	}
	return true;
}