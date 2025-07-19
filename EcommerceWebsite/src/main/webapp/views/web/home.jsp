<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="CartURL" value="/cart" />
<c:url var="cart" value="/gio-hang" />
<c:url var="APIurl" value="/api-admin-new" />
<c:url var="NewURL" value="/tin-tuc" />
<c:url var="productURL" value="/san-pham" />
<c:url var="categoryURL" value="/danh-muc" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div id="carouselExampleSlidesOnly" class="carousel slide"
					data-ride="carousel">
					<div class="carousel-inner">
						<div class="carousel-item active">
							<img class="d-block w-100"
								src="/EcommerceWebsite/uploads/1751880642151_slider_1.webp"
								alt="First slide">
						</div>
						<div class="carousel-item">
							<img class="d-block w-100" src="..." alt="Second slide">
						</div>
						<div class="carousel-item">
							<img class="d-block w-100" src="..." alt="Third slide">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</section>

	<section class="py-5 overflow-hidden">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-12">
					<div
						class="section-header d-flex flex-wrap justify-content-between mb-5">
						<h2 class="section-title">Danh mục nổi bật</h2>

						<div class="d-flex align-items-center">
							<a href="#" class="btn-link text-decoration-none">Xem thêm →</a>
							<div class="swiper-buttons">
								<button
									class="swiper-prev category-carousel-prev btn btn-yellow">❮</button>
								<button
									class="swiper-next category-carousel-next btn btn-yellow">❯</button>
							</div>
						</div>
					</div>

				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="category-carousel swiper">
						<div class="swiper-wrapper">
							<c:forEach var="item" items="${categoriesSlide}">
								<a href="${categoryURL }/${item.slug}"
									class="nav-link category-item swiper-slide"> <img
									src="${item.img}"
									alt="${item.name}">
									<h3 class="category-title">${item.name}</h3>
								</a>
							</c:forEach>
						</div>
					</div>

				</div>
			</div>
		</div>
	</section>

	<section class="section_flashsale flashsale container"
		style="-background-color: #ffffff; - -countdown-background: #ff4949; - -countdown-color: #ffffff; - -process-background: #f6c3c3; - -process-color1: #ff4949; - -process-color2: #ff4949; - -stock-color: #ffffff; - -heading-color: #000000; - -news-color: #000000;">
		<div class="flashsale__container container card border-0">
			<div
				class="title_module_main row heading-bar d-flex justify-content-between align-items-center py-0">
				<div
					class="d-flex align-items-center flex-wrap flashsale__header col-12 justify-content-between">
					<div style="display: flex; align-items: center; gap: 10px;">
						<h2 class="heading-bar__title flashsale__title m-0">
							<a class="link" href="san-pham-khuyen-mai-1"
								title="GIÁ SỐC HÔM NAY">GIÁ SỐC HÔM NAY</a>
						</h2>
						</div>
					<div class="flashsale__countdown-wrapper">
						<div class="countdown-container">
							Kết thúc sau
							<div class="countdown">
								<div class="countdown-box">
									<span id="hours">00</span> <small>Giờ</small>
								</div>
								<span class="colon">:</span>
								<div class="countdown-box">
									<span id="minutes">00</span> <small>Phút</small>
								</div>
								<span class="colon">:</span>
								<div class="countdown-box">
									<span id="seconds">00</span> <small>Giây</small>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row one-row justify-content-center">
				<c:forEach items="${specialPromotion.listProductPromotion }"
					var="product">
					<div class="col-6 col-md-3">
						<div class="product-card text-center h-100">
							<div class="image_thumb">
								<span class="discount-badge"><fmt:formatNumber
										value="${(product.price - product.pricesale) * 100 /  product.price}"
										type="number" maxFractionDigits="0" />%</span> <a
									href="${product.slug }"><img
									src="${product.productimg}"
									class="img-fluid"></a>
							</div>
							<div class="fw-semibold">
								<a href="${product.slug }" title="${product.name }">${product.name }</a>
							</div>
							<div>
								<span class="old-price my-3"><fmt:formatNumber
										value="${product.price }" type="number" />₫</span> <span
									class="text-success fw-bold"><fmt:formatNumber
										value="${product.pricesale }" type="number" />₫</span>
							</div>
							<c:url var="addCart" value="/cart">
								<c:param name="action" value="add" />
								<c:param name="productId" value="${product.id}" />
							</c:url>
							<button class="btn-cart btn btn-success btn-sm mt-2  " data-product-id="${product.id}">Thêm
								vào giỏ</button>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</section>
<c:forEach items="${listCateProduct}" var = "category">
	<section class="py">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-12">

					<div class="bootstrap-tabs product-tabs">
						<div
							class="tabs-header d-flex justify-content-between border-bottom my-5">
							<h3>${category.name }</h3>
							<nav>
								<div class="nav nav-tabs" id="nav-tab" role="tablist">
									<a href="#" class="nav-link text-uppercase fs-6 active"
										id="nav-all-tab" data-bs-toggle="tab"
										data-bs-target="#nav-all">All</a> <a href="#"
										class="nav-link text-uppercase fs-6" id="nav-fruits-tab"
										data-bs-toggle="tab" data-bs-target="#nav-fruits">Fruits &
										Veges</a> <a href="#" class="nav-link text-uppercase fs-6"
										id="nav-juices-tab" data-bs-toggle="tab"
										data-bs-target="#nav-juices">Juices</a>
								</div>
							</nav>
						</div>
						<div class="tab-content" id="nav-tabContent">
							<div class="tab-pane fade show active" id="nav-all"
								role="tabpanel" aria-labelledby="nav-all-tab">

								<div
									class="product-grid row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 row-cols-xl-5">
									<c:forEach items="${category.listProduct }" var="item">
										<div class="col">
											<div class="product-item">
												<div class="product-item swiper-slide">
													<div class="product-card text-center h-100">
														<span class="discount-badge"><fmt:formatNumber
																value="${(item.price - item.pricesale) * 100 /  item.price}"
																type="number" maxFractionDigits="0" />%</span> <img
															src="${item.productimg}"
															class="product-image w-100 mb-2" alt="Hành tây">
														<div class="fw-semibold">${item.name }</div>
														<div>
															<span class="old-price">${item.price }đ</span> <span
																class="text-success fw-bold">${item.pricesale }đ</span>
														</div>
														<button class="btn btn-success btn-sm mt-2">Thêm
															vào giỏ</button>
													</div>
												</div>
											</div>
										</div>
									</c:forEach>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</c:forEach>
	



	<section class="py-5 overflow-hidden"
		style="background-color: #f7f7f7;">
		<div class="container">
			<div class="row">
				<div class="section-header d-flex flex-wrap justify-content-between">
					<h2 class="section-title">Sản phẩm mới</h2>
					<div class="d-flex align-items-center">
						<div class="swiper-buttons">
							<button
								class="swiper-prev products-carousel-prev btn btn-primary">❮</button>
							<button
								class="swiper-next products-carousel-next btn btn-primary">❯</button>
						</div>
					</div>
				</div>

			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="products-carousel swiper">
					<div class="swiper-wrapper">
						<c:forEach items="${newproducts }" var="item">
							<div class="product-item swiper-slide">
								<div class="product-card text-center h-100">
									<span class="discount-badge"><fmt:formatNumber
											value="${(item.price - item.pricesale) * 100 /  item.price}"
											type="number" maxFractionDigits="0" />%</span> <img
										src="${item.productimg}"
										class="product-image w-100 mb-2" alt="Hành tây">
									<div class="fw-semibold">${item.name }</div>
									<div>
										<span class="old-price">${item.price }đ</span> <span
											class="text-success fw-bold">${item.pricesale }đ</span>
									</div>
									<button class="btn btn-success btn-sm mt-2">Thêm vào
										giỏ</button>
								</div>
							</div>
						</c:forEach>
						<!-- / products-carousel -->
					</div>
				</div>
			</div>
		</div>
		</div>
	</section>



	<section id="latest-blog" class="latest-blog py-5 ">
		<div class="container">
			<div class="row">
				<div
					class="section-header d-flex align-items-center justify-content-between">
					<h2 class="section-title">Tin mới nhất</h2>
					<div class="btn-wrap align-right">
						<a href="${NewURL}" class="d-flex align-items-center nav-link">Xem
							thêm <svg width="24" height="24">
								<use xlink:href="#arrow-right"></use></svg>
						</a>
					</div>
				</div>
			</div>
			<div class="blogs-container row">
				<c:forEach var="item" items="${recentBlogs}">
					<div class="col-md-4 d-flex mt-3">
						<article
							class="post-item card border-0 shadow-sm p-3 w-100 d-flex flex-column">
							<div class="image-holder zoom-effect">
								<a href="${pageContext.request.contextPath}/tin-tuc/${item.slug }" title="${item.title}"> <img
									src="${item.thumbnail}"
									alt="${item.title}" class="card-img-top">
								</a>
							</div>
							<div class="card-body d-flex flex-column">
								<div
									class="post-meta d-flex text-uppercase gap-3 my-2 align-items-center">
									<div class="meta-date">
										<svg width="16" height="16">
											<use xlink:href="#calendar"></use></svg>
										<span class="time-ago" data-time="${item.createdAt}"> </span>
									</div>
									<div class="meta-categories">
										<svg width="16" height="16">
											<use xlink:href="#category"></use></svg>
										tips & tricks
									</div>
								</div>
								<div class="post-header">
									<h3 class="post-title">
										<a
											href="${pageContext.request.contextPath}/tin-tuc/${item.slug }"
											class="text-decoration-none">${item.title}</a>
									</h3>
									<p>${item.shortDescription}</p>
								</div>
							</div>
						</article>
					</div>
				</c:forEach>
			</div>
		</div>
	</section>




	<section class="py-5">
		<div class="container-fluid">
			<div class="row row-cols-1 row-cols-sm-3 row-cols-lg-5">
				<div class="col">
					<div class="card mb-3 border-0">
						<div class="row">
							<div class="col-md-2 text-dark">
								<svg xmlns="http://www.w3.org/2000/svg" width="32" height="32"
									viewBox="0 0 24 24">
									<path fill="currentColor"
										d="M21.5 15a3 3 0 0 0-1.9-2.78l1.87-7a1 1 0 0 0-.18-.87A1 1 0 0 0 20.5 4H6.8l-.33-1.26A1 1 0 0 0 5.5 2h-2v2h1.23l2.48 9.26a1 1 0 0 0 1 .74H18.5a1 1 0 0 1 0 2h-13a1 1 0 0 0 0 2h1.18a3 3 0 1 0 5.64 0h2.36a3 3 0 1 0 5.82 1a2.94 2.94 0 0 0-.4-1.47A3 3 0 0 0 21.5 15Zm-3.91-3H9L7.34 6H19.2ZM9.5 20a1 1 0 1 1 1-1a1 1 0 0 1-1 1Zm8 0a1 1 0 1 1 1-1a1 1 0 0 1-1 1Z" /></svg>
							</div>
							<div class="col-md-10">
								<div class="card-body p-0">
									<h5>Miễn phí vận chuyển</h5>
									<p class="card-text">Hóa đơn trên 5 triệu</p>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col">
					<div class="card mb-3 border-0">
						<div class="row">
							<div class="col-md-2 text-dark">
								<svg xmlns="http://www.w3.org/2000/svg" width="32" height="32"
									viewBox="0 0 24 24">
									<path fill="currentColor"
										d="M19.63 3.65a1 1 0 0 0-.84-.2a8 8 0 0 1-6.22-1.27a1 1 0 0 0-1.14 0a8 8 0 0 1-6.22 1.27a1 1 0 0 0-.84.2a1 1 0 0 0-.37.78v7.45a9 9 0 0 0 3.77 7.33l3.65 2.6a1 1 0 0 0 1.16 0l3.65-2.6A9 9 0 0 0 20 11.88V4.43a1 1 0 0 0-.37-.78ZM18 11.88a7 7 0 0 1-2.93 5.7L12 19.77l-3.07-2.19A7 7 0 0 1 6 11.88v-6.3a10 10 0 0 0 6-1.39a10 10 0 0 0 6 1.39Zm-4.46-2.29l-2.69 2.7l-.89-.9a1 1 0 0 0-1.42 1.42l1.6 1.6a1 1 0 0 0 1.42 0L15 11a1 1 0 0 0-1.42-1.42Z" /></svg>
							</div>
							<div class="col-md-10">
								<div class="card-body p-0">
									<h5>Đổi trả miễn phí</h5>
									<p class="card-text">Trong vòng 7 ngày</p>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col">
					<div class="card mb-3 border-0">
						<div class="row">
							<div class="col-md-2 text-dark">
								<svg xmlns="http://www.w3.org/2000/svg" width="32" height="32"
									viewBox="0 0 24 24">
									<path fill="currentColor"
										d="M22 5H2a1 1 0 0 0-1 1v4a3 3 0 0 0 2 2.82V22a1 1 0 0 0 1 1h16a1 1 0 0 0 1-1v-9.18A3 3 0 0 0 23 10V6a1 1 0 0 0-1-1Zm-7 2h2v3a1 1 0 0 1-2 0Zm-4 0h2v3a1 1 0 0 1-2 0ZM7 7h2v3a1 1 0 0 1-2 0Zm-3 4a1 1 0 0 1-1-1V7h2v3a1 1 0 0 1-1 1Zm10 10h-4v-2a2 2 0 0 1 4 0Zm5 0h-3v-2a4 4 0 0 0-8 0v2H5v-8.18a3.17 3.17 0 0 0 1-.6a3 3 0 0 0 4 0a3 3 0 0 0 4 0a3 3 0 0 0 4 0a3.17 3.17 0 0 0 1 .6Zm2-11a1 1 0 0 1-2 0V7h2ZM4.3 3H20a1 1 0 0 0 0-2H4.3a1 1 0 0 0 0 2Z" /></svg>
							</div>
							<div class="col-md-10">
								<div class="card-body p-0">
									<h5>100% Hoàn tiền</h5>
									<p class="card-text">Nếu sản phẩm lỗi</p>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col">
					<div class="card mb-3 border-0">
						<div class="row">
							<div class="col-md-2 text-dark">
								<svg xmlns="http://www.w3.org/2000/svg" width="32" height="32"
									viewBox="0 0 24 24">
									<path fill="currentColor"
										d="M12 8.35a3.07 3.07 0 0 0-3.54.53a3 3 0 0 0 0 4.24L11.29 16a1 1 0 0 0 1.42 0l2.83-2.83a3 3 0 0 0 0-4.24A3.07 3.07 0 0 0 12 8.35Zm2.12 3.36L12 13.83l-2.12-2.12a1 1 0 0 1 0-1.42a1 1 0 0 1 1.41 0a1 1 0 0 0 1.42 0a1 1 0 0 1 1.41 0a1 1 0 0 1 0 1.42ZM12 2A10 10 0 0 0 2 12a9.89 9.89 0 0 0 2.26 6.33l-2 2a1 1 0 0 0-.21 1.09A1 1 0 0 0 3 22h9a10 10 0 0 0 0-20Zm0 18H5.41l.93-.93a1 1 0 0 0 0-1.41A8 8 0 1 1 12 20Z" /></svg>
							</div>
							<div class="col-md-10">
								<div class="card-body p-0">
									<h5>guaranteed savings</h5>
									<p class="card-text">Lorem ipsum dolor sit amet,
										consectetur adipi elit.</p>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col">
					<div class="card mb-3 border-0">
						<div class="row">
							<div class="col-md-2 text-dark">
								<svg xmlns="http://www.w3.org/2000/svg" width="32" height="32"
									viewBox="0 0 24 24">
									<path fill="currentColor"
										d="M18 7h-.35A3.45 3.45 0 0 0 18 5.5a3.49 3.49 0 0 0-6-2.44A3.49 3.49 0 0 0 6 5.5A3.45 3.45 0 0 0 6.35 7H6a3 3 0 0 0-3 3v2a1 1 0 0 0 1 1h1v6a3 3 0 0 0 3 3h8a3 3 0 0 0 3-3v-6h1a1 1 0 0 0 1-1v-2a3 3 0 0 0-3-3Zm-7 13H8a1 1 0 0 1-1-1v-6h4Zm0-9H5v-1a1 1 0 0 1 1-1h5Zm0-4H9.5A1.5 1.5 0 1 1 11 5.5Zm2-1.5A1.5 1.5 0 1 1 14.5 7H13ZM17 19a1 1 0 0 1-1 1h-3v-7h4Zm2-8h-6V9h5a1 1 0 0 1 1 1Z" /></svg>
							</div>
							<div class="col-md-10">
								<div class="card-body p-0">
									<h5>Daily offers</h5>
									<p class="card-text">Lorem ipsum dolor sit amet,
										consectetur adipi elit.</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="cart-overlay" class="overlay" onclick="closeProductPopup()"></div>
<div id="cart-popup" class="cart-popup  ">
  <div class="popup-header">
    <span class="check-icon">✔</span>
    <span class="popup-title">Sản phẩm vừa được thêm vào giỏ hàng</span>
    <button class="popup-close" onclick="closeProductPopup()">×</button>
  </div>
  <div class="popup-body">
    <img id="popup-image" src="###" alt="Sản phẩm">
    <div class="popup-info">
      <div id="popup-name" class="product-name">ádasdasd</div>
      <div id="popup-price" class="product-price">ádasdasdas</div>
    </div>
  </div>
  <div class="popup-cartcount">
    <button onclick="toggleCartCount()" class="cartcount-toggle">
      ▶ Giỏ hàng hiện có (<span id="popup-quantity">0</span>) sản phẩm
    </button>
  </div>
  <a href="/cart" class="btn-checkout">Tiến hành thanh toán</a>
</div>
	</section>
	<script>
			function startCountdown(durationInSeconds) {
			  const countdownEl = document.getElementById("countdown");
			  let remaining = durationInSeconds;
			
			  const interval = setInterval(() => {
			    const hours = String(Math.floor(remaining / 3600)).padStart(2, "0");
			    const minutes = String(Math.floor((remaining % 3600) / 60)).padStart(2, "0");
			    const seconds = String(remaining % 60).padStart(2, "0");
			
			    countdownEl.textContent = `${hours} : ${minutes} : ${seconds}`;
			
			    if (--remaining < 0) {
			      clearInterval(interval);
			      countdownEl.textContent = "Đã kết thúc";
			    }
			  }, 1000);
			}
			
			document.addEventListener("DOMContentLoaded", () => {
			  startCountdown(9 * 3600 + 23 * 60 + 17); // 9h23p17s
			});
			</script>
	<script>
		function timeAgo(dateString) {
		    const date = new Date(dateString);
		    const now = new Date();
		    const seconds = Math.floor((now - date) / 1000);
			console.log("data: " + date);
			console.log("now: " + now);
			console.log("seconds: " + seconds);
		    if (seconds < 60) return "Vừa xong";
		    else if (seconds < 3600) return Math.floor(seconds / 60) + " phút trước";
		    else if (seconds < 86400) return Math.floor(seconds / 3600) + " giờ trước";
		    else if (seconds < 604800) return Math.floor(seconds / 86400) + " ngày trước";
		    else return date.getDate() + " tháng " + (date.getMonth() + 1) + " " + date.getFullYear();
		}

		function updateAllTimeAgo() {
		    const elements = document.querySelectorAll('.time-ago');
		    elements.forEach(el => {
		        const timeStr = el.getAttribute('data-time');
		        el.textContent = timeAgo(timeStr);
		    });
		
		}
		
		
		
		 document.addEventListener("DOMContentLoaded", () => {
			    const endTime = new Date(${specialPromotion.end.time}); // model.end là Timestamp
			    const now = new Date();
			    const diffInSeconds = Math.floor((endTime - now) / 1000);

			    if (diffInSeconds > 0) {
			      startCountdown(diffInSeconds);
			    } else {
			      // Hết hạn thì hiện 00:00:00
			      document.getElementById("hours").textContent = "00";
			      document.getElementById("minutes").textContent = "00";
			      document.getElementById("seconds").textContent = "00";
			    }
			  });
		// Cập nhật ngay và mỗi phút
		updateAllTimeAgo();
		setInterval(updateAllTimeAgo, 60000); // mỗi 60 giây
		</script>
	<script>
		function startCountdown(durationInSeconds) {
		  let remaining = durationInSeconds;

		  const hoursEl = document.getElementById("hours");
		  const minutesEl = document.getElementById("minutes");
		  const secondsEl = document.getElementById("seconds");

		  function updateCountdown() {
		    if (remaining < 0) {
		      clearInterval(interval);
		      hoursEl.textContent = "00";
		      minutesEl.textContent = "00";
		      secondsEl.textContent = "00";
		      return;
		    }

		    const hours = String(Math.floor(remaining / 3600)).padStart(2, '0');
		    const minutes = String(Math.floor((remaining % 3600) / 60)).padStart(2, '0');
		    const seconds = String(remaining % 60).padStart(2, '0');

		    hoursEl.textContent = hours;
		    minutesEl.textContent = minutes;
		    secondsEl.textContent = seconds;

		    remaining--;
		  }

		  const interval = setInterval(updateCountdown, 1000);
		  updateCountdown();
		}
		</script>
		<script>
document.addEventListener('DOMContentLoaded', () => {
  document.body.addEventListener('click', function (e) {
    const target = e.target.closest('.btn-cart');
    if (!target) return;
    const productId = target.dataset.productId;
	console.log(productId);
	fetch('${CartURL}', {
	      method: 'POST',
	      headers: {
	        'Content-Type': 'application/x-www-form-urlencoded'
	      },
	      body: new URLSearchParams({
	        action: 'add',
	        productId: productId
	      })
	    })
	    .then(res => {
	      if (!res.ok) throw new Error('Network error');
	      return res.json();
	    })
	    .then(data => {
	      if (data.status === 'success') {
	        updateCartUI(data.totalQuanty);
	        showProductPopup({
	            image: data.product.image,
	            name: data.product.name,
	            price: data.product.price,
	            quantity: data.totalQuanty
	          });
	      } else {
	        showToast("❌ Thêm vào giỏ thất bại!", "error");
	      }
	    })
	    .catch(() => {
	      showToast("❌ Lỗi kết nối!", "error");
	    });
	  });
  
  function updateCartUI(total) {
    const badge = document.querySelector('.cart-count');
    if (badge) badge.textContent = total;
  }

  function showToast(message, type = 'success') {
    alert(message);
  }
});
</script>

<script>
  function showProductPopup({ image, name, price,quantity }) {
	  document.getElementById('popup-image').src = image;
	    document.getElementById('popup-name').textContent = name;
	    document.getElementById('popup-price').textContent =  price;
	    document.getElementById('popup-quantity').textContent =  quantity;
	    document.getElementById('cart-popup').classList.add('show');
	    document.getElementById('cart-overlay').classList.add('show');
  }

  function closeProductPopup() {
	  document.getElementById('cart-popup').classList.remove('show');
	  document.getElementById('cart-overlay').classList.remove('show');
  }
</script>
</body>
</html>