<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로필 이미지 수정</title>
<link rel="stylesheet" href="/Resources/css/imageChoice.css">
<script src="/Resources/js/imageChoice.js"></script>
</head>
<body>
	<h1>프로필 이미지 수정</h1>
	<div class="profile-image-container">
	    <img src="CURRENT_PROFILE_IMAGE_URL" alt="Profile image" class="profile-image">
	</div>
	<div class="button-container">
	    <button class="image-choice-button" id="basic-images">기본 이미지</button>
	    <button class="image-choice-button" id="file-chooser">파일 찾기</button>
	</div>
	
	<div id="modalWrapper" class="modal">
	    <div class="modal-content">
	        <h1>프로필 이미지 수정</h1>
	        <div class="container basic-images" style="display: none;">
	            <!-- Add your basic images here -->
	            <div class="basic-image-card">
	                <img src="BASIC_IMAGE_URL" alt="Basic image" class="basic-image">
	            </div>
	            <div class="container basic-images" style="display: none;">
			    	<!-- Add your basic images here -->
				    <div class="basic-image-card">
				        <img src="BASIC_IMAGE_URL_1" alt="Basic image 1" class="basic-image">
				    </div>
				    <div class="basic-image-card">
				        <img src="BASIC_IMAGE_URL_2" alt="Basic image 2" class="basic-image">
				    </div>
				    <div class="basic-image-card">
				        <img src="BASIC_IMAGE_URL_3" alt="Basic image 3" class="basic-image">
				    </div>
			    	<!-- Add more basic-image-card divs for each basic image -->
				</div>
	        </div>
	    </div>
	</div>
</body>
</html>
