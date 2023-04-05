function resetForm() {
	let initialTitle = "${rDTO.title}";
	let initialContent = "${rDTO.content}";

    document.getElementById("title").value = initialTitle;
    document.getElementById("content").value = initialContent;
}
