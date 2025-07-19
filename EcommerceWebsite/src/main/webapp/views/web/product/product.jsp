<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<c:url var="CartURL" value="/cart" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${product.name}</title>
<style>
   .product-content {
    max-height: 120px;
    overflow: hidden;
    transition: max-height 0.3s ease;
    position: relative;
}
.product-content.expanded {
    max-height: none;
}
.read-more-btn {
    display: inline-block;
    color: #007bff;
    cursor: pointer;
    margin-top: 8px;
}
.gradient-fade {
    position: absolute;
    bottom: 0;
    left: 0;
    width: 100%;
    height: 40px;
    background: linear-gradient(to bottom, transparent, white);
    pointer-events: none;
}
.product-content.expanded .gradient-fade {
    display: none;
}
</style>

</head>
<body>

	<section class="clearfix bread-crumb">
		<span class="bread-cumb-border"></span>
		<div class="container">
			<div class="row">
				<div class="col-12">
					<ul class="breadcrumb">
						<li class="breadcrumb-item"><a class="link"
							href="<c:url value="/trang-chu"/>"><span>Trang chủ</span></a></li>
						<li class="breadcrumb-item"><a
							href="<c:url value="/${product.name}"/>"><span>mapCate[product.product_catid]</span></a></li>
						<li class="breadcrumb-item active"><strong><span>${product.name}</span></strong></li>
					</ul>
				</div>
			</div>
		</div>
	</section>
	<section class="clearfix">
		<div class="container">
			<div class="shadow-sm row details-product product-bottom">
				<div class="col-lg-12 col-md-12 col-sm-12 col-12 top-product-name">
					<h1 class="title-head">${product.name}</h1>
				</div>
				<div class="col-lg-4 col-md-6 col-sm-12 col-12 no-padding">
					<div class="product-image-block">
						<div class="slider-for">
							<div class="product1">
								<img
									src="${pageContext.request.contextPath}/uploads/${product.productimg}"
									width="250px" height="auto">
							</div>
							<div class="product1">
										<img
											src="${pageContext.request.contextPath}/uploads/${product.productimg}"
											width="250px" height="auto">
									</div>
							<!--<c:forEach var="item" items="${listimgprod}">
								<c:if test="${product.product_id == item.product_id }">
									<div class="product1">
										<img
											src="${pageContext.request.contextPath}/images/${item.img}"
											width="250px" height="auto">
									</div>
								</c:if> 
							</c:forEach>-->
						</div>
						<div class="slider-nav">
							<div class="product2" style="width: 69.2px; margin-right: 10px;">
								<img
									src="${pageContext.request.contextPath}/images/${product.productimg}"
									width="250px" height="auto">
							</div>
							<!--<c:forEach var="item" items="${listimgprod}">
								<c:if test="${product.product_id == item.product_id }">
									<div class="product2"
										style="width: 69.2px; margin-right: 10px;">
										<img
											src="${pageContext.request.contextPath}/images/${item.img}">
									</div>
								</c:if>
							</c:forEach>-->.
							
							<div class="product2"
										style="width: 69.2px; margin-right: 10px;">
										<img
											src="${pageContext.request.contextPath}/uploads/${product.productimg}">
									</div>
									<div class="product2"
										style="width: 69.2px; margin-right: 10px;">
										<img
											src="${pageContext.request.contextPath}/uploads/${product.productimg}">
									</div>
									<div class="product2"
										style="width: 69.2px; margin-right: 10px;">
										<img
											src="${pageContext.request.contextPath}/uploads/${product.productimg}">
									</div>
						</div>
					</div>
				</div>
				<div class="col-lg-5 col-md-6 col-sm-12 col-12 details-pro">
					<div class="product-top clearfix">
						<h1 class="title-product">${product.name }</h1>
						<div class="sku-product clearfix">
							<span>Mô tả: <strong>${product.shortdesc }</strong></span><br>
							<span class="code">Mã sản phẩm: <strong>(Đang cập
									nhật...)</strong>
							</span>
						</div>
						<div class="price-box clearfix">
							<span class="special-price"><fmt:formatNumber
															value="${product.price}" type="number" />₫</span><span
								class="old-price">Giá niêm yết: <del
									class="product-price-old"> <fmt:formatNumber
															value="${product.pricesale }" type="number" />₫</del>

							</span> <span class="save-price"> Tiết kiệm: <span
								class="product-price-save"><fmt:formatNumber
															value="${product.price - product.pricesale }" type="number" />₫</span>
							</span>
						</div>
						<div class="form-product">
							<c:url var="addCart" value="/cart">
								<c:param name="action" value="add" />
								<c:param name="productId" value="${product.id}" />
							</c:url>
							<form enctype="multipart/form-data" id="add-to-cart-form"
								method="post" class="clearfix has-validation-callback"
								action="${addCart}">
								<div class="btn-mua">
									<button type="submit"
										class="btn btn-lg btn-gray btn-cart btn_buy add_to_cart">
										Mua ngay</button>
								</div>
							</form>
														<div class="mt-2">
								<div class="fb-like"
									data-href="http://localhost:8080/spring-mvc-doan/chi-tiet-san-pham/${product.slug}"
									data-width="" data-layout="standard" data-action="like"
									data-size="large" data-share="true"></div>
							</div>
							<div class="product-hotline">
								Gọi <a href="tel:${configweb.hotline}"
									title="${configweb.hotline}">${configweb.hotline}</a> để tư vấn
								mua hàng
							</div>
						</div>
					</div>
				</div>
				<div
					class="col-lg-3 col-md-12 col-sm-12 col-12 shop-feature no-padding">
					<div class="product-summary">
						<p>
							<b>Tình trạng</b><br>product.product_condition
						</p>
						<p>
							<b>Bảo hành</b><br>product.product_guarantee
						</p>
					</div>
					<div class="product-promotion">

						<div class="promotion-item">
							<img src="${pageContext.request.contextPath}/uploads/">
							<div class="text">
								<strong>item.name</strong>item.metadesc
							</div>

						</div>
					</div>
				</div>
				<div class="row mb-3">
					<div class="col-lg-7" style="padding-left: 0 !important;">
						<div class="product-details shadow-sm">
						    <div class="product-content" id="product-content">
						        <div class="product-detail-text">
						            <c:out value="${product.detail}" escapeXml="false"/>
						        </div>
						        <div class="gradient-fade"></div>
						    </div>
						    <div class="read-more-btn">Xem thêm</div>
						</div>
					</div>
					<div class="col-lg-5 product-fix-padding">
						<div class="shadow-sm specifications">
							<div class="title">Thông số kỹ thuật</div>
							<table id="tskt">
								<tbody>
									<tr>
										<td>Hãng sản xuất</td>
										<td>mapManu[product.manufacturer_id</td>
									</tr>
									<tr>
										<td>Kích thước</td>
										<td>${product.weight}</td>
									</tr>

									<tr>
										<td>Tiện ích</td>

									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
	</section>
	<section class="clearfix">
		<div class="container">
			<div class="fb-comments"
				data-href="http://localhost:8080/spring-mvc-doan/chi-tiet-san-pham/${product.slug}"
				data-width="" data-numposts="5"></div>
		</div>
	</section>
	<section class="clearfix">
		<div class="container">
			<div class="relate-product shadow-sm row">
				<div class="col-lg-12">
					<div class="module-header">
						<h1 class="title-head module-title border-title sidebar-title-1">
							Sản phẩm liên quan</h1>
						<div class="owl-carousel owl-theme">
						<c:set var="countList" value="${ relatedProducts.size() }" />
							<c:forEach var="item" items="${ relatedProducts }" begin="0"
								end="${countList}" varStatus="loop">
								<c:if
									test="${item.catid == product.catid && item.id != product.id}">
									<div class="item">
										<div class="card pt-3" style="width: 100%;">
											<a
												href="<c:url value='/san-pham/${item.slug}'/>"
												title="${item.name}"> <img class="card-img-top"
												src="${pageContext.request.contextPath}/uploads/${item.productimg}"
												alt="" height="175">
											</a>
											<div class="card-body text-center">
												<h6 class="card-title" title="${item.name}">
													<a class="product-item-name"
														href="<c:url value='/chi-tiet-san-pham/${item.slug}'/>"
														title="${item.name}">${item.name}</a>
												</h6>
												<p>
													<span class="price"><fmt:formatNumber
															value="${item.pricesale}" type="number" />đ</span><span
														class="oldprice"><fmt:formatNumber
															value="${item.price}" type="number" />đ</span>
												</p>
												<div class="group-action">
													<a class="btn-buy" title="Chi tiết sản phẩm"
														href="<c:url value='/chi-tiet-san-pham/${item.slug}'/>">
														<i class="fas fa-search"></i>
													</a> <a class="btn-detail" title="Thêm vào giỏ hàng"
														data-id="${item.id}"
														href="<c:url value="/AddCart/${item.id}"/>"> <i
														class="fas fa-cart-plus"></i></a>
												</div>
											</div>
										</div>
									</div>
								</c:if>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
	
		</div>
	</section>
	<script type="text/javascript">
				$(function() {
					$("#slider").slick({
						autoplay : true,
						speed : 1000,
						arrows : false,
						asNavFor : "#thumbnail_slider"
					});
					$("#thumbnail_slider").slick({
						slidesToShow : 3,
						speed : 1000,
						asNavFor : "#slider"
					});
				});
			</script>
			<script type="text/javascript">
				$(function() {
					$(document)
							.ready(
									function() {
										$('.owl-carousel')
												.owlCarousel(
														{
															loop : false,
															margin : 10,
															nav : true,
															navText : [
																	"<i class='fa fa-angle-left' aria-hidden='true'></i>",
																	"<i class='fa fa-angle-right' aria-hidden='true'></i>" ],
															dots : false,
															responsive : {
																0 : {
																	items : 2
																},
																600 : {
																	items : 3
																},
																1000 : {
																	items : 5
																}
															}
														})
									});
				});
			</script>
	<script type="text/javascript">
				$(function() {
					$(document)
							.ready(
									function() {
										$('.owl-carousel')
												.owlCarousel(
														{
															loop : false,
															margin : 10,
															nav : true,
															navText : [
																	"<i class='fa fa-angle-left' aria-hidden='true'></i>",
																	"<i class='fa fa-angle-right' aria-hidden='true'></i>" ],
															dots : false,
															responsive : {
																0 : {
																	items : 2
																},
																600 : {
																	items : 3
																},
																1000 : {
																	items : 5
																}
															}
														})
									});
				});
			</script>
	
	<script type="text/javascript">
				$(document).ready(function() {
					$('.slider-for').slick({
						slidesToShow : 1,
						slidesToScroll : 1,
						arrows : false,
						accessibility : false,
						swipe : false,
						asNavFor : '.slider-nav'
					});
					$('.slider-nav').slick({
						slidesToShow : 4,
						slidesToScroll : 1,
						asNavFor : '.slider-for',
						dots : false,
						infinite : false,
						swipe : true,
						focusOnSelect : true,
						responsive : [ {
							breakpoint : 1024,
							settings : {
								slidesToShow : 4,
								slidesToScroll : 1,
								infinite : false,
								dots : false,
							}
						}, {
							breakpoint : 600,
							settings : {
								slidesToShow : 4,
								slidesToScroll : 1,
								dots : false
							}
						}, {
							breakpoint : 480,
							settings : {
								slidesToShow : 4,
								slidesToScroll : 1,
								dots : false
							}
						}
						// You can unslick at a given breakpoint now by adding:
						// settings: "unslick"
						// instead of a settings object
						]
					});
				});
			</script>
	<script>
		$(".btn-detail").on("click", function(e) {
			var id = $(this).data("id");
			e.preventDefault();
			$.ajax({
				url : '${pageContext.request.contextPath}/AddCart/' + id,
				type : 'get',
				success : function(result) {
					var carttotal = $("#cart-total").text();
					$("#cart-total").text(parseInt(carttotal) + 1);
					toastr.success("Thêm vào giỏ hàng thành công");
				},
				error : function(request, status, error) {
					toastr.error("Có lỗi khi thêm vào giỏ hàng");
				}
			});
		});
	</script>
<script>
    document.addEventListener("DOMContentLoaded", function() {
        const content = document.getElementById('product-content');
        const btn = document.querySelector('.read-more-btn');

        // Nếu nội dung không đủ dài thì ẩn nút "Xem thêm"
        if (content.scrollHeight <= 120) {
            btn.style.display = "none";
        }

        btn.addEventListener("click", function () {
            content.classList.toggle('expanded');
            btn.textContent = content.classList.contains('expanded') ? 'Thu gọn' : 'Xem thêm';
        });
    });
</script>
</body>
</html>