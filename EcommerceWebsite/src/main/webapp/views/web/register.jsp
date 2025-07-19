<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<c:url var="login" value="/dang-nhap?action=login" />
<!DOCTYPE html>
<html lang="en">
  <!--begin::Head-->
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Dang ky</title>
  </head>
  <!--end::Head-->
  <!--begin::Body-->
  <body class="login-page bg-body-secondary">
  <div class="login-box">
      <div class="login-logo">
        <a href="<c:url value="/trang-chu" />"><b>Tan Thanh </b>shop</a>
      </div>
      <!-- /.login-logo -->
      <div class="card">
        <div class="card-body login-card-body">
          <p class="login-box-msg">Đăng ký tài khoản mới</p>
          <form action="<c:url value="/dang-nhap"/>" method="post">
            <div class="input-group mb-3">
              <input type="text" class="form-control" placeholder="Email"  name = "userName"/>
              <div class="input-group-text"><span class="bi bi-envelope"></span></div>
            </div>
            <div class="input-group mb-3">
              <input type="password" class="form-control" placeholder="Password" name="password"/>
              <div class="input-group-text"><span class="bi bi-lock-fill"></span></div>
            </div>
            <div class="input-group mb-3">
              <input type="text" class="form-control" placeholder="Email"  name = "fullName"/>
              <div class="input-group-text"><span class="bi bi-envelope"></span></div>
            </div>
            
 			 <div class="input-group mb-3">
              <input type="text" class="form-control" placeholder="Phone Number" name="phone" id = "phone"/>
              <div class="input-group-text"><span class="bi bi-phone"></span></div>
            </div>
            <span id="phoneError" style="color: red; display:none;"></span>
            <!--begin::Row-->
            <div class="row">
              <div class="col-12">
                <div class="form-check">
                <input class="form-check-input" type="checkbox"  id="flexCheckDefault" name="remember"/>
                  <label class="form-check-label" for="flexCheckDefault"> Remember Me </label>
                </div>
              </div>
              <!-- /.col -->
              <div class="col-12">
                <div class="d-grid gap-2">
                 <input type="hidden" value="register" name = "action"/>
                  <button type="submit" class="btn btn-primary">Register</button>
                </div>
              </div>
              <!-- /.col -->
            </div>
            <!--end::Row-->
          </form>
         
          <!-- /.social-auth-links -->
          <p class="mb-1"><a href="${login }">Already have a account, let login</a></p>
          <br/>
        </div>
        <!-- /.login-card-body -->
      </div>
    </div>
        <script>
        $(document).ready(function () {
            $('#phone').on('blur', function () {
                let phone = $(this).val().trim();
                let regex = /^(0|\+84)(3[2-9]|5[6|8|9]|7[0|6-9]|8[1-9]|9[0-9])[0-9]{7}$/;

                if (!regex.test(phone)) {
                    $('#phoneError').text('Số điện thoại không hợp lệ!').fadeIn();
                    $(this).css('border-color', 'red');
                } else {
                    $('#phoneError').fadeOut();
                    $(this).css('border-color', '');
                }
            });
        });
    </script>
  </body>
  <!--end::Body-->
</html>