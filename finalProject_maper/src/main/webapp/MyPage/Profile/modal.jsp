<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Image Choice - Upload Profile Image</title>
<link rel="stylesheet" href="/Resources/css/imageChoice.css">
<style>
.modal {
    display: none;
    position: fixed;
    z-index: 1;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    overflow: auto;
    background-color: rgba(0, 0, 0, 0.4);
}

.modal-content {
    background-color: #fefefe;
    margin: 15% auto;
    padding: 20px;
    border: 1px solid #888;
    width: 80%;
}
</style>
</head>
<body>
<div id="modalWrapper" class="modal">
    <div class="modal-content">
        <div class="container">
            <!-- left section for basic images -->
            <div class="section left-section">
                <h3>Choose from Basic Images</h3>
                <!-- Add your basic images here -->
            </div>
            
            <!-- vertical bold line -->
            <div class="separator"></div>
            
            <!-- right section for uploading profile image -->
            <div class="section right-section">
                <h3>Upload from Your Computer</h3>
                <form action="${pageContext.request.contextPath}/MyPage/MyPageFront?command=MyProfile.uploadImage" method="post" enctype="multipart/form-data">
                    <label for="profileImage">Choose a profile image:</label>
                    <input type="file" name="profileImage" id="profileImage" accept="image/*">
                    <br>
                    <input type="submit" value="Upload Image">
                </form>
            </div>
        </div>
    </div>
</div>

<script>
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
</script>
</body>
</html>
