function pageDelete(idx) {
	if (confirm('복원은 불가능합니다. 정말 삭제하시겠습니까?')) {
		alert('삭제되었습니다.');
		location.href = '../Page/page.do?command=pageDelete&idx=' + idx;
	}
}

function reportBtn(){
	if(confirm('해당 책을 신고하시겠습니까?')){
		alert('신고 했습니다.');
		return true;
	}else{
		return false;
	}
}

// 페이지 작성 시 공란 유효성 검사
function pageBlank() {
	if (document.pageWriteFrm.subTitle.value.length == 0) {
		alert('부제목을 입력하세요.');
		pageWriteFrm.subTitle.focus();
		return false;
	}
	if (document.pageWriteFrm.pageDate.value.length == 0) {
		alert('여행일자를 입력하세요.');
		pageWriteFrm.pageDate.focus();
		return false;
	}
	if (document.pageWriteFrm.ofile.value.length == 0) {
		alert('파일 첨부는 필수입니다.');
		pageWriteFrm.ofile.focus();
		return false;
	}
	if (document.pageWriteFrm.rate.value == 1) {
		if (confirm('만족도를 1점으로 선택하신게 맞습니까?')) {
		} else {
			pageWriteFrm.rate.focus();
			return false;
		}
	}
	if (document.pageWriteFrm.content.value.length == 0) {
		alert('내용을 입력하세요.');
		pageWriteFrm.content.focus();
		return false;
	}
	if (document.pageWriteFrm.content.value.length > 500) {
		alert('너무 많은 내용을 입력하셨습니다.', '현재 ' + document.pageWriteFrm.content.value.length);
		pageWriteFrm.content.focus();
		return false;
	}

	// 수정
	if (document.pageEditFrm.subTitle.value.length == 0) {
		alert('부제목을 입력하세요.');
		pageWriteFrm.subTitle.focus();
		return false;
	}
	if (document.pageWriteFrm.rate.value == 1) {
		if (confirm('만족도를 1점으로 선택하신게 맞습니까?')) {
		} else {
			pageWriteFrm.rate.focus();
			return false;
		}
	}
	if (document.pageEditFrm.content.value.length == 0) {
		alert('내용을 입력하세요.');
		pageWriteFrm.content.focus();
		return false;
	}
	return true;
}
