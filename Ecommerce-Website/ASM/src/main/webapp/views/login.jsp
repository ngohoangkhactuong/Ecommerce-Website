
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%-- Library bootstrap --%>
<%@include file="/common/bootstrap.jsp"%>
<link rel="shortcut icon"
	href="https://f7-zpc.zdn.vn/3653115574917346255/94b82f22fc9c25c27c8d.jpg"
	type="image/x-icon">
<title>Tuong Vi 's Shop</title>
</head>
<body class="container-fluid font-monospace">
	<div class="row">
		<div class="align-self-center p-5 col-12 col-md-6">
			<a class="navbar-brand fw-bold fs-2" href="#"> <!--  <img alt="logo" class="img-fluid" src="/ASM/public/images/logo.svg"> -->
				<img alt="banner"
				src="https://f7-zpc.zdn.vn/3653115574917346255/94b82f22fc9c25c27c8d.jpg"
				class="img-fluid" width="300px" height="300px">
			</a>
		</div>
		<div class="align-self-center p-5 col-12 col-md-6 mt-5">
			<form action="/ASM/login" method="post">
				<h1>Login</h1>
				<c:if test="${ !empty sessionScope.messageLg }">
					<div class="text-danger">${ sessionScope.messageLg }</div>
					<c:remove var="messageLg" scope="session" />
				</c:if>
				<div class="form-group">
					<label class="fw-bold">Email</label> <input
						class="form-control mb-2 mt-1 rounded-pill" type="text"
						name="emailLogin" value="${ email }" required="required"
						oninvalid="this.setCustomValidity('Không được để trống email')"
						oninput="this.setCustomValidity('')" />
					<c:if test="${ !empty sessionScope.isEmail }">
						<div class="text-danger">${ sessionScope.isEmail }</div>
						<c:remove var="isEmail" scope="session" />
					</c:if>
					<label class="fw-bold">Password</label> <input
						class="form-control mb-2 mt-1 rounded-pill" type="password"
						name="passwordLogin" value="${ password }" required="required"
						oninvalid="this.setCustomValidity('Không được để trống password')"
						oninput="this.setCustomValidity('')" />

					<div class="row p-2">
						<div class="form-check col-6">
							<input class="form-check-input" type="checkbox" name="remember" />
							<label class="form-check-label fw-bold" for="flexCheckDefault">
								Remember me </label>
						</div>
						<div class="col-6 text-primary text-end " role="button">
							<p>Forgot password ?</p>
						</div>
					</div>

					<div class="row px-3 mt-5">
						<button type="submit" class="btn btn-primary rounded-pill">LOG
							IN</button>
						<button type="button" class="btn btn-primary rounded-pill mt-3">
							<a href="/ASM/signup/index"
								class="text-decoration-none text-white">SIGN UP</a>
						</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>