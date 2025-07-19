<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<c:url var="APIurl" value="/api-checkout" />
<c:url var="BillUrl" value="/checkout/thankyou/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thanh toán</title>
</head>
<body>
	<header class="banner">
		<div class="wrap">
			<div class="logo-center">
				<h1 class="shop_name">Thành Vinh Shop</h1>
			</div>
		</div>
	</header>
	<aside>
		<button class="order-summary-toggle" data-toggle="#order-summary"
			data-toggle-class="order-summary--is-collapsed">
			<span class="wrap"> <span class="order-summary-toggle__inner">
					<span class="order-summary-toggle__text expandable"> Đơn
						hàng ( <c:choose>
							<c:when test="${not empty Cart}">${TotalQuantyCart}
								</c:when>
							<c:otherwise>0
								</c:otherwise>
						</c:choose> sản phẩm)
				</span> <span class="order-summary-toggle__total-recap">65.000.000đ</span>
			</span>
			</span>
		</button>
	</aside>
	<div class="content">
		<c:url var="checkout" value="/checkout" />
		<form action="${checkout}" id="checkoutForm" method="post">
			<div class="wrap">
				<div class="main-co">
					<div class="main-co-header">
						<div class="logo logo-center">
							<h1 class="shop_name">Thành Vinh Shop</h1>
						</div>
					</div>
					<div class="main-co-content">
						<article class=" row">
							<div class="col-full col-half">
								<section class="section">
									<div class="section-co-header">
										<div class="layout-flex">
											<h2 class="section-co-title layout-flex-item ">Thông tin
												nhận hàng</h2>
										</div>
									</div>
									<div class="section-co-content">
										<div class="fieldset ">
											<div
												class="field ${empty bill.email?'':'field-show-floating-label'}">
												<div class="field-input-wrapper">
													<label for="email" class="field-label"> Email </label> <input
														type="email" class="field-input" id="email" name="email"
														required />
												</div>
											</div>
											<div
												class="field ${empty bill.display_name?'':'field-show-floating-label'}">
												<div class="field-input-wrapper">
													<label for="billingName" class="field-label">Họ và
														tên</label> <input type="text" class="field-input"
														id="display_name" name="display_name" required />
												</div>

											</div>

											<div
												class="field ${empty bill.phone?'':'field-show-floating-label'}">
												<div class="field-input-wrapper">
													<label for="billingPhone" class="field-label"> Số
														điện thoại (tùy chọn) </label> <input type="text"
														class="field-input" id="phone" name="phone" required />
												</div>

											</div>


											<div class="field">
												<div class="field-input-wrapper">
													<label for="billingAddress" class="field-label">
														Địa chỉ (tùy chọn) </label> <input type="text" class="field-input"
														id="address" name="address" required />
												</div>

											</div>


											<div class="field field-show-floating-label ">
												<div
													class="field-input-wrapper field-input-wrapper--select2">
													<label for="billingProvince" class="field-label">Tỉnh
														thành</label> <select id="billingProvince" size="1"
														onChange="myNewFunction(this);"
														class="field-input field-input--select"
														required="required">
													</select> <input type="hidden" class="field-input" id="city"
														name="city" />
												</div>

											</div>

											<div class="field field-show-floating-label">
												<div
													class="field-input-wrapper field-input-wrapper--select2">
													<label for="billingDistrict" class="field-label">
														Quận huyện (tùy chọn) </label> <select id="billingDistrict"
														size="1" onChange="myNewFunction1(this);"
														class="field-input field-input--select disabled-select">
													</select> <input type="hidden" class="field-input" id="district"
														name="district" />
												</div>
											</div>
											<div class="field field-show-floating-label">
												<div
													class="field-input-wrapper field-input-wrapper--select2">
													<label for="billingWard" class="field-label">
														Phường xã (tùy chọn) </label> <select id="billingWard" size="1"
														class="field-input field-input--select disabled-select"
														onChange="myNewFunction2(this);">
													</select> <input type="hidden" class="field-input" id="province"
														name="province" />
												</div>
											</div>
										</div>
									</div>
								</section>

								<div class="fieldset">
									<div class="field ">
										<div class="field-input-wrapper">
											<label for="note" class="field-label"> Ghi chú (tùy
												chọn) </label> <input type="text" class="field-input" id="note"
												name="note" required />
										</div>
									</div>
								</div>
							</div>
							<div class="col-full col-half">
								<section class="section">
									<div class="section-co-header">
										<div class="layout-flex">
											<h2 class="section-co-title">Vận chuyển</h2>
										</div>
									</div>
									<div class="section-co-content">
										<div class="content-box">
											<div class="content-box-row">
												<div class="radio-wrapper">
													<div class="radio-input">
														<input type="radio" class="input-radio"
															id="shippingMethod1" checked>
													</div>
													<label for="shippingMethod1" class="radio-label"> <span
														class="radio-label-primary">Giao hàng tận nơi</span> <span
														class="radio-label-accessory"> <span
															class="content-box-emphasis"> 40.000₫ </span>
													</span>
													</label>
												</div>
											</div>

										</div>

									</div>
								</section>
								<section class="section">
									<div class="section-co-header">
										<div class="layout-flex">
											<h2
												class="section-co-title layout-flex-item layout-flex-item--stretch">
												Thanh toán</h2>
										</div>
									</div>
									<div class="section__content">
										<div class="content-box">

											<div class="content-box-row">
												<div class="radio-wrapper">
													<div class="radio-input">
														<input id="paymentMethod-491087" type="radio"
															class="input-radio" data-bind="" value="491087" checked>
													</div>
													<label for="paymentMethod-491087" class="radio-label">
														<span class="radio-label-primary">Thanh toán khi
															giao hàng (COD)</span> <span class="radio-label-accessory">
															<span class="radio__label__icon"> <i
																class="fas fa-money-bill-wave"></i>
														</span>
													</span>

													</label>
												</div>
												<div class="radio-wrapper">
													<button type="submit"></button>
												</div>
											</div>
										</div>
									</div>
								</section>
							</div>
						</article>
						<div
							class="field__input-btn-wrapper field__input-btn-wrapper-vertical hide-on-desktop">
							<button type="submit" class="btn btn-info btn-checkout spinner"
								id="">
								<span class="spinner-label">ĐẶT HÀNG</span>
							</button>

							<a href="/cart" class="previous-link"> <i
								class="fas fa-chevron-left"></i> <span
								class="previous-link__content">Quay về giỏ hàng</span>
							</a>

						</div>
					</div>
				</div>
				<aside class="sidebar-co">
					<div class="sidebar-co-header">
						<h2 class="sidebar-co-title">
							Đơn hàng (
							<c:choose>
								<c:when test="${not empty Cart}">${TotalQuantyCart}
								</c:when>
								<c:otherwise>0
								</c:otherwise>
							</c:choose>
							sản phẩm)
						</h2>
					</div>
					<div class="sidebar-co-content">
						<div id="order-summary"
							class="order-summary order-summary--is-collapsed">
							<div class="order-summary-sections">
								<div
									class="order-summary__section order-summary__section--product-list order-summary-scroll order-summary--collapse-element">
									<table class="product-table">
										<thead class="product-table-header">
											<tr>
												<th><span class="visually-hidden">Ảnh sản phẩm</span></th>
												<th><span class="visually-hidden">Mô tả</span></th>
												<th><span class="visually-hidden">Sổ lượng</span></th>
												<th><span class="visually-hidden">Đơn giá</span></th>
											</tr>
										</thead>
										<tbody>
											<c:if test="${not empty Cart }">
												<c:forEach var="item" items="${Cart}">
													<tr class="product">
														<td class="product__image">
															<div class="product-thumbnail">
																<div class="product-thumbnail__wrapper" data-tg-static>

																	<img
																		src="${item.value.product.productimg}"
																		alt="" class="product-thumbnail__image">

																</div>
																<span class="product-thumbnail__quantity">${item.value.quanty}</span>
															</div>
														</td>
														<th class="product__description"><span
															class="product__description__name">${item.value.product.name}
														</span></th>
														<td class="product__quantity visually-hidden"><em>Số
																lượng:</em>${item.value.quanty}</td>
														<td class="product__price"><fmt:formatNumber
																value="${item.value.quanty * item.value.product.pricesale}"
																type="number" />đ</td>
													</tr>
												</c:forEach>
											</c:if>
										</tbody>
									</table>
								</div>
								<div
									class="order-summary__section order-summary__section--discount-code"
									id="discountCode">
									<div class="edit_checkout animate-floating-labels">
										<div class="fieldset">
											<div class="field  ">
												<div class="field-input-btn-wrapper"
													style="border: 1px solid #ccc; padding: 10px; border-radius: 5px; display: flex; justify-content: space-between; align-items: center;">
													<div class="field-input-wrapper">
														<label for="reductionCode" class="field-label">Nhập
															mã giảm giá</label> <input name="reductionCode"
															id="reductionCode" type="text" class="field-input"
															autocomplete="off" style="margin: 0px">
													</div>
													<button
														class="field-input-btn btn btn-info spinner btn-apply"
														type="button">
														<span class="spinner-label">Áp dụng</span>
													</button>
												</div>
												<p class="text-danger" id="msg-error"></p>
												<p class="text-success" id="msg-success"></p>
												<!-- Xuat loi o day  vd ma da su dung, kh ton tai-->
											</div>
										</div>
									</div>
								</div>
								<div
									class="order-summary__section order-summary__section--total-lines"
									id="orderSummary">
									<table class="total-line-table">
										<caption class="visually-hidden">Tổng giá trị</caption>
										<thead>
											<tr>
												<td><span class="visually-hidden">Mô tả</span></td>
												<td><span class="visually-hidden">Giá tiền</span></td>
											</tr>
										</thead>
										<tbody class="total-line-table__tbody">
											<tr class="total-line total-line--subtotal">
												<th class="total-line__name">Tạm tính</th>
												<td class="total-line__price" id="subTotal"><fmt:formatNumber
														value="${ TotalPriceCart }" type="number" />₫</td>
											</tr>

											<tr class="total-line total-line--shipping-fee">
												<th class="total-line__name">Phí vận chuyển</th>
												<td class="total-line__price">40,000đ</td>
											</tr>
											<tr class="total-line total-line--subtotal"
												id="total-line-body">

											</tr>
										</tbody>
										<tfoot class="total-line-table__footer">
											<tr class="total-line payment-due" id="total-line-total">
												<th class="total-line__name"><span
													class="payment-due__label-total"> Tổng cộng </span></th>
												<td class="total-line__price"><span
													class="payment-due-price"><fmt:formatNumber
															value="${ TotalPriceCart + 40000 }" type="number" />₫</span></td>
											</tr>
										</tfoot>
									</table>
								</div>
								<div
									class="order-summary__nav field__input-btn-wrapper hide-on-mobile layout-flex--row-reverse">
									<button type="submit" class="btn btn-info btn-checkout spinner"
										id="btnCheckout">
										<span class="spinner-label">ĐẶT HÀNG</span>
									</button>


									<a href="<c:url value="/gio-hang"/>" class="previous-link">
										<i class="fas fa-chevron-left"></i> <span
										class="previous-link__content">Quay về giỏ hàng</span>
									</a>

								</div>
								<input type="hidden" name="totalPrice" id="totalPrice"
									value="${TotalPriceCart}" />
							</div>
						</div>
					</div>
				</aside>
			</div>
		</form>
	</div>
	<script>
		$('#btnCheckout').click(function(e) {
			e.preventDefault();
			var data = {};
			var formData = $('#checkoutForm').serializeArray();
			$.each(formData, function(i, v) {
				if(v.name != "totalPrice"){
				data["" + v.name + ""] = v.value;
				console.log(v.name + ": " + v.value);
			}
			});
			addCoupon(data)
		});

		function addCoupon(data) {
			$.ajax({
						url : '${APIurl}',
						type : 'POST',
						data : JSON.stringify(data),
						contentType : "application/json",
						dataType : 'json',
						success : function(result) {
							window.location.href = "${BillUrl}" + result.code;
						},
						error : function(error) {
							window.location.href = "${BillUrl}?type=list&maxPageItem=2&page=1&message=error_system";
						}
					});
		}
		function updateCoupon(data) {
			$
					.ajax({
						url : '${APIurl}',
						type : 'PUT',
						contentType : 'application/json',
						data : JSON.stringify(data),
						dataType : 'json',
						success : function(result) {
							window.location.href = "${CouponUrl}?type=edit&id="
									+ result.id + "&message=update_success";
						},
						error : function(error) {
							window.location.href = "${CouponUrl}?type=list&maxPageItem=2&page=1&message=error_system";
						}
					});
		}
	</script>


	<script>
	// Floating label
	function checkForInput(element) {
		var $label = $(element).parent().parent();
		if (!$(element).hasClass("field-input--select")) {
			if ($(element).val().length > 0) {
				$label.addClass("field-show-floating-label");
			} else {
				$label.removeClass("field-show-floating-label");
			}
		}
	}	
	
	
document.addEventListener("DOMContentLoaded", function () {
	// Load tỉnh/thành phố
	fetch("https://provinces.open-api.vn/api/p/")
		.then(response => response.json())
		.then(provinces => {
			const provinceSelect = document.getElementById("billingProvince");
			provinceSelect.innerHTML = '<option value="">-- Chọn tỉnh/thành --</option>';
			provinces.forEach(province => {
				const option = document.createElement("option");
				option.value = province.code;
				option.textContent = province.name;
				provinceSelect.appendChild(option);
			});
		});

	// Reset select
	function resetSelect(id, placeholderText) {
		const select = document.getElementById(id);
		select.innerHTML = `<option value="">${placeholderText}</option>`;
		select.classList.remove("disabled-select");
	}

	// Chọn tỉnh/thành
	window.myNewFunction = function (element) {
		const provinceCode = element.value;
		const selectedText = element.options[element.selectedIndex].text;
		document.getElementById("city").value = selectedText;
		console.log("selectedText:", selectedText);
		console.log("provinceCode:", provinceCode);
		resetSelect("billingDistrict", "-- Chọn quận/huyện --");

		if (!provinceCode) 
			{
			document.getElementById("billingDistrict").classList.add("disabled-select");
				return;
			}
		fetch("https://provinces.open-api.vn/api/p/"+provinceCode + "?depth=2")
			.then(response => response.json())
			.then(data => {
				console.log(`https://provinces.open-api.vn/api/p/${provinceCode}?depth=2`);
				console.log("Dữ liệu trả về:", data); // 👈 kiểm tra thật sự có `districts` không
				if (!Array.isArray(data.districts)) {
					console.error("districts không tồn tại hoặc không phải mảng:", data);
					return;
				}
				const districtSelect = document.getElementById("billingDistrict");
				data.districts.forEach(district => {
					const option = document.createElement("option");
					option.value = district.code;
					option.textContent = district.name;
					districtSelect.appendChild(option);
				});
				districtSelect.disabled = false;
			});
	};

	// Chọn quận/huyện
	window.myNewFunction1 = function (element) {
		const districtCode = element.value;
		const selectedText = element.options[element.selectedIndex].text;
		document.getElementById("district").value = selectedText; // 🔥 fix lỗi chính tả id

		resetSelect("billingWard", "-- Chọn phường/xã --");

		if (!districtCode)
			{
			document.getElementById("billingWard").classList.add("disabled-select");
			return}

		fetch("https://provinces.open-api.vn/api/d/"+districtCode+"depth=2")
			.then(response => response.json())
			.then(data => {
				const wardSelect = document.getElementById("billingWard");
				data.wards.forEach(ward => {
					const option = document.createElement("option");
					option.value = ward.code;
					option.textContent = ward.name;
					wardSelect.appendChild(option);
				});
				wardSelect.disabled = false;
			});
	};

	// Chọn phường/xã
	window.myNewFunction2 = function (element) {
		const selectedText = element.options[element.selectedIndex].text;
		document.getElementById("province").value = selectedText;
	};
	$('.field-input').on('change keyup', function () {
		checkForInput(this);
	});
});
</script>
	<script>
document.addEventListener("DOMContentLoaded", function () {
    const applyBtn = document.querySelector('.btn-apply');
    if (applyBtn) {
        applyBtn.addEventListener('click', applyCoupon);
    }
    const subTotal = document.getElementById('subTotal');
	console.log(subTotal);
});




function applyCoupon() {
    const codeInput = document.getElementById('reductionCode');
    const code = codeInput.value.trim();
    const totalPriceInput = document.getElementById('totalPrice');
    const totalPrice = totalPriceInput.value.trim();
    const msgSuccess = document.getElementById('msg-success');
    const msgError = document.getElementById('msg-error');
    const totalLine = document.getElementById('total-line-body');
    // Reset message
    msgSuccess.textContent = '';
    msgError.textContent = '';

    if (!code) {
        msgError.textContent = 'Vui lòng nhập mã khuyến mãi.';
        return;
    }

    // Gửi AJAX request
    fetch('http://localhost:8080/EcommerceWebsite/api-apply-coupon', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ code: code , totalPrice: totalPrice})
    })
    .then(response => response.json())
    .then(data => {
        if (data.status === 'success') {
            msgSuccess.textContent = data.message;
            
            
            console.log("Coupon info:", data.data.promotion); // chứa model coupon (value, type, code...)
            document.getElementById('discountCode').innerHTML = `<div style="border: 1px solid #ccc; padding: 10px; border-radius: 5px; display: flex; justify-content: space-between; align-items: center;">
                  <div>
                    <div><strong>Mã:</strong> `+ data.data.coupon.code + `</div>
                    <input type = "hidden" name = "reductionCode" value = "`+ data.data.coupon.code + `">
                    <div><strong>Giảm: </strong></div>
                  </div>
                  <button onclick="removeCoupon()" class="btn btn-danger">Xóa</button>
                </div>`;
                
            document.getElementById('total-line-body').innerHTML =`<th class="total-line__name">Phí vận chuyển</th>
				<td class="total-line__price" > -`+ Number(data.data.promotion).toLocaleString('vi-VN') + '₫' + ` </td> `;
				
            document.getElementById('total-line-total').innerHTML =`<th class="total-line__name"><span
				class="payment-due__label-total"> Tổng cộng </span></th>
				<td class="total-line__price"><span
					class="payment-due-price">`+ Number(data.data.priceAfterPromotion + 40000).toLocaleString('vi-VN') + '₫' + `</span></td> `;	
        } else {
            msgError.textContent = data.message || 'Có lỗi xảy ra.';
        }
    })
    .catch(err => {
        msgError.textContent = 'Lỗi khi kết nối đến server.';
        console.error(err);
    });
};
function removeCoupon() {
	document.getElementById('discountCode').innerHTML = `
		<div class="edit_checkout animate-floating-labels">
		<div class="fieldset">
			<div class="field  ">
				<div class="field-input-btn-wrapper" style="border: 1px solid #ccc; padding: 10px; border-radius: 5px; display: flex; justify-content: space-between; align-items: center;">
					<div class="field-input-wrapper">
						<label for="reductionCode" class="field-label">Nhập
							mã giảm giá</label> <input name="reductionCode"
							id="reductionCode" type="text" class="field-input"
							autocomplete="off" style="margin: 0px">
					</div>
					<button
						class="field-input-btn btn btn-info spinner btn-apply"
						type="button">
						<span class="spinner-label">Áp dụng</span>
					</button>
				</div>
				<p class="text-danger" id="msg-error"></p>
				<p class="text-success" id="msg-success"></p>
				<!-- Xuat loi o day  vd ma da su dung, kh ton tai-->
			</div>
		</div>
	</div>
	  `;
	document.getElementById('total-line-body').innerHTML = ``;
	  // Gắn lại sự kiện click cho nút Áp dụng sau khi render lại
	            document.getElementById('total-line-total').innerHTML =`<th class="total-line__name"><span
				class="payment-due__label-total"> Tổng cộng </span></th>
				<td class="total-line__price"><span
					class="payment-due-price"><fmt:formatNumber
					value="${ TotalPriceCart + 40000 }" type="number" />₫</span></td> `
	      const newApplyBtn = document.querySelector('.btn-apply');
    if (newApplyBtn) {
        newApplyBtn.addEventListener('click', applyCoupon);
        
    }

    // Xóa hidden input nếu có
    const couponInput = document.getElementById('couponHiddenInput');
    if (couponInput) {
        couponInput.remove();
    }
    $('.field-input').on('change keyup', function () {
        checkForInput(this);
    });

  };
  
</script>

</body>
</html>