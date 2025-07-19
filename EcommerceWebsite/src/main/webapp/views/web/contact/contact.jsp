<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liên hệ</title>
</head>
<body>
	<section class="clearfix bread-crumb">
		<span class="bread-cumb-border"></span>
		<div class="container">
			<div class="row">
				<div class="col-12">
					<ul class="breadcrumb">
						<li class="breadcrumb-item"><a
							href="<c:url value="/trang-chu"/>"><span>Trang chủ</span></a></li>
						<li class="breadcrumb-item active"><strong><span>Liên
									hệ</span></a></strong></li>
					</ul>
				</div>
			</div>
		</div>
	</section>

	<section class="contact-section">
		<div class="container">
			<div class="row">
				<div class="container">
					<c:if test="${not empty msgSs}">
						<div class="alert alert-success mt-3">${ msgSs}</div>
					</c:if>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12">
					<div class="container mt-1">
						<iframe
							src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d1745.6040240629839!2d106.7748480652859!3d10.830554180529228!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31752701a34a5d5f%3A0x30056b2fdf668565!2zVHLGsOG7nW5nIENhbyDEkOG6s25nIEPDtG5nIFRoxrDGoW5nIFRQLkhDTQ!5e0!3m2!1svi!2s!4v1619953996719!5m2!1svi!2s"
							width="100%" height="450" style="border: 0;" allowfullscreen=""
							loading="lazy"></iframe>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 your-message mb-2">
					<c:url var="send" value="/lien-he/send" />
					<form id="contact" action="<c:url var="APIurl" value="/lien-he" />" method="post">
						<div class="form-row">
							<div class="form-group col-md-6">
								<label>Họ và tên</label> <input type="text" class="form-control"
									name="name" required>
							</div>
							<div class="form-group col-md-6">
								<label>Email</label> <input type="text" class="form-control"
									name="email" required>
							</div>
						</div>
						<div class="form-group">
							<label>Đia chỉ</label> <input type="text" class="form-control"
								name="address" required>
						</div>
						<div class="form-group">
							<label>Điện thoại</label> <input type="text" class="form-control"
								name="phone" required>
						</div>
						<div class="form-group">
							<label>Tiêu đề</label> <input type="text" class="form-control"
								name="subject" required>
						</div>
						<div class="form-group">
							<label>Nội dung</label>
							<textarea class="form-control" rows="3"
								name="content" required="required"></textarea>
						</div>
						<button type="submit" class="btn btn-primary"
							style="float: right;">Gửi tin nhắn</button>
					</form>
				</div>
			</div>
		</div>
	</section>
	<section>
	
  <!-- Danh sách chi nhánh -->
  <div class="branch-list">
    <div class="branch" onclick="changeMap('Tầng 3, 70 Lữ Gia, Phường 15, Quận 11, Thành phố Hồ Chí Minh')">
      <strong>Bean Hồ Chí Minh</strong><br>
      Địa chỉ: Tầng 3, 70 Lữ Gia, Phường 15, Quận 11, TP.HCM<br>
      Hotline: 1900 6750
    </div>

    <div class="branch" onclick="changeMap('169 / 34 Nguyễn Hữu Cảnh, Phường Phú Thọ, TP.Thủ Dầu Một, Tỉnh Bình Dương')">
      <strong>Bean Bình Dương</strong><br>
      Địa chỉ: 169 / 34 Nguyễn Hữu Cảnh, Phường Phú Thọ, TP.Thủ Dầu Một, Bình Dương<br>
      Hotline: 1900 6750
    </div>

    <div class="branch" onclick="changeMap('81 đường Phan Huy Chú, KDC Thới Nhựt I, Quận Ninh Kiều, Tp Cần Thơ')">
      <strong>Bean Cần Thơ</strong><br>
      Địa chỉ: 81 Phan Huy Chú, KDC Thới Nhựt I, Ninh Kiều, Cần Thơ<br>
      Hotline: 1900 6750
    </div>

    <div class="branch" onclick="changeMap('Tầng 6 - 266 Đội Cấn, Phường Liễu Giai, Quận Ba Đình, Hà Nội')">
      <strong>Bean Hà Nội</strong><br>
      Địa chỉ: Tầng 6 - 266 Đội Cấn, Phường Liễu Giai, Quận Ba Đình, Hà Nội<br>
      Hotline: 1900 6750
    </div>
  </div>

  <!-- Google Map -->
  <div class="map-container">
    <iframe
      id="mapFrame"
      src="https://www.google.com/maps?q=Tầng+3,+70+Lữ+Gia,+Phường+15,+Quận+11,+Thành+phố+Hồ+Chí+Minh&output=embed"
      allowfullscreen
      loading="lazy"
      referrerpolicy="no-referrer-when-downgrade">
    </iframe>
  </div>

  <!-- Script đổi map -->
  <script>
    function changeMap(address) {
      const encoded = encodeURIComponent(address);
      const newSrc = `https://www.google.com/maps?q=${encoded}&output=embed`;
      document.getElementById("mapFrame").src = newSrc;
    }
  </script>
	
	
	</section>
	<script>
		function setInputFilter(textbox, inputFilter) {
			[ "input", "keydown", "keyup", "mousedown", "mouseup", "select",
					"contextmenu", "drop" ].forEach(function(event) {
				textbox.addEventListener(event, function() {
					if (inputFilter(this.value)) {
						this.oldValue = this.value;
						this.oldSelectionStart = this.selectionStart;
						this.oldSelectionEnd = this.selectionEnd;
					} else if (this.hasOwnProperty("oldValue")) {
						this.value = this.oldValue;
						this.setSelectionRange(this.oldSelectionStart,
								this.oldSelectionEnd);
					} else {
						this.value = "";
					}
				});
			});
		}
		setInputFilter(document.getElementById("inputPhone"), function(value) {
			return /^\d*\.?\d*$/.test(value); // Allow digits and '.' only, using a RegExp
		});
	</script>
</body>
</html>