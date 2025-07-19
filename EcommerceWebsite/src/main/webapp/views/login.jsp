<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<!--begin::Head-->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Đăng nhập</title>
</head>
<!--end::Head-->
<!--begin::Body-->
<body class="login-page bg-body-secondary">
	<div class="login-box">
		<c:url var="resetPasword" value="/dang-nhap?action=resetpassword" />
		<c:url var="register" value="/dang-nhap?action=register" />
		<!-- /.login-logo -->
		<div class="alert alert-${alert}">${message }</div>
		<div class="card">
			<div class="card-body login-card-body">
				<p class="login-box-msg">Đăng nhập tài khoản</p>

				<form action="<c:url value="/dang-nhap"/>" method="post">


					<div class="input-group mb-3">
						<input type="text" class="form-control" placeholder="Email"
							name="userName" />
						<div class="input-group-text">
							<span class="bi bi-envelope"></span>
						</div>
					</div>
					<div class="input-group mb-3">
						<input type="password" class="form-control" placeholder="Mật khẩu"
							name="password" />
						<div class="input-group-text">
							<span class="bi bi-lock-fill"></span>
						</div>
					</div>
					<!--begin::Row-->
					<div class="row">
						<div class="col-7">
							<div class="form-check">
								<input class="form-check-input" type="checkbox"
									id="flexCheckDefault" name="remember" /> <label
									class="form-check-label" for="flexCheckDefault">
									Nhớ mật khẩu </label>
							</div>
						</div>
						<!-- /.col -->
						<div class="col-5">
							<div class="d-grid gap-2">
								<input type="hidden" value="login" name="action" />
								<button type="submit" class="btn btn-primary">Đăng nhập</button>
							</div>
						</div>
						<!-- /.col -->
					</div>
					<!--end::Row-->
				</form>
				<div class="social-auth-links text-center mb-3 d-grid gap-2">
					<p>- OR -</p>
					<a href="https://accounts.google.com/o/oauth2/auth?response_type=code&redirect_uri=http://localhost:8080/EcommerceWebsite/oauth2callback&client_id=16254816110-43212mlei2kqqton2v8rfbsfha666s2p.apps.googleusercontent.com&scope=email+profile&access_type=online&approval_prompt=auto" class="btn btn-primary"> <i
						class="bi bi-facebook me-2"></i> Đăng nhập với Facebook
					</a> <a href="${oauthURL }" class="btn btn-danger"> <i
						class="bi bi-google me-2"></i> Đăng nhập với Google+
					</a>
				</div>
				<!-- /.social-auth-links -->
				<p class="mb-1">
					<a href="${resetPasword }">Quên mật khẩu</a>
				</p>
				<p class="mb-0">
					<a href="${register}" class="text-center"> Đăng ký tài khoản mới</a>
				</p>
			</div>
			<!-- /.login-card-body -->
		</div>
	</div>
</body>
<!--end::Body-->
</html>