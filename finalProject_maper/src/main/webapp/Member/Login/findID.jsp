<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/Common/link.jsp"%>
<title>Login</title>
<script type="text/javascript" src="../../javascript/Resources/Member.js"></script>
</head>
<body class="maper-body-background">
	<!-- header -->
	<div class="maper-header">
		<div class="maper-header-font">
			<a class="maper-header-font-1" href="login.jsp">MAPER</a>
		</div>
	</div>
	<!-- body -->
	<form action="FindId.do" method="post" name="frm">
	<div class="maper-body">
		<div class="maper-body-login">
			<p class="maper-fontsize-1">Find ID</p>
			<div class="maper-login-rounded">
				<div class="maper-login-inputsize">
                
                <!-- 라이센스 키 -->
				<tr>
				<td>licensekey</td>
				<td><input type="text" name="licensekey" id="licensekey" size="20">*</td>
			</tr>
					
					<!-- ��ư -->
					<div class="maper-login-buttonLocation">
						<div class="d-grid gap-2 col-6 mx-auto">
							<button type="submit" class="btn btn-primary" type="button" onclick="">ok</button>
						</div>
					</div>
					</div>
			</div>
			
			<div style="margin: 15px auto; width: 80%;" align="center">
				<p><b>MAPER</b> 함께 추억을 공유해보세요.</p>
			</div>
		</div>
	</div>
	</form>
	

</body>
</html>