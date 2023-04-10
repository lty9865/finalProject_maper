document.getElementById("basic-images").addEventListener("click", function() {
    showModal();
    document.querySelector(".container.basic-images").style.display = "flex";
});

document.getElementById("file-chooser").addEventListener("click", function() {
    document.getElementById("profileImage").click();
});

function showModal() {
    var modal = document.getElementById("modalWrapper");
    modal.style.display = "block";
}

function closeModal() {
    var modal = document.getElementById("modalWrapper");
    modal.style.display = "none";
}

window.onclick = function(event) {
    var modal = document.getElementById("modalWrapper");
    if (event.target == modal) {
        closeModal();
    }
}

var basicImageCards = document.getElementsByClassName("basic-image-card");
for (var i = 0; i < basicImageCards.length; i++) {
    basicImageCards[i].addEventListener("click", function() {
        closeModal();
        document.querySelector(".profile-image").src = this.querySelector(".basic-image").src;
    });
}

document.getElementById("profileImage").addEventListener("change", function(event) {
    var reader = new FileReader();
    reader.onload = function(e) {
        document.querySelector(".profile-image").src = e.target.result;
    };
    reader.readAsDataURL(event.target.files[0]);
});
