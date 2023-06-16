<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%-- Library bootstrap --%>
<%@include file="/views/bootstrap.jsp"%>
<link rel="shortcut icon" href="/public/images/logo.svg"
	type="image/x-icon">
<title>Fashion</title>
</head>
<body class="container-fluid font-monospace">
	<div class="row mt-5">
		<div class="col-6 text-center">
			<img alt="banner" src="/public/images/banner.png"
				class="img-fluid">
		</div>
		<div class="col-6 text-end align-self-center px-5">
			<h1>Welcome my website</h1>
			<p>Member : <br>Name : Ngo Hoang Khac Tuong <br> Mssv : 20133112 <br> Name : Nguyen Thi Tuong Vi <br> Mssv : 20133113</p>
			<button type="button" class="btn btn-primary rounded-pill">
				<a href="/ASM/login" class="text-white text-decoration-none">GET
					STARTED</a>
			</button>
			<button type="button" class="btn btn-primary rounded-pill">
				<a href="/ASM/login" class="text-white text-decoration-none">WATCH
					MORE</a>
			</button>
		</div>
	</div>
</body>
</html>