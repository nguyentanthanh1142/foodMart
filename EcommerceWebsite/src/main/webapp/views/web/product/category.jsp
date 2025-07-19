<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<c:url var="CategoryURL" value="/danh-muc" />
<c:set var="queryParams" value="" />
<c:if test="${not empty priceRange}">
	<c:forEach var="price" items="${priceRange}">
		<c:set var="queryParams" value="${queryParams}&priceRange=${price}" />
	</c:forEach>
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.product-card {
	border: 1px solid #ddd;
	border-radius: 8px;
	padding: 10px;
	text-align: center;
	height: 100%;
}

.product-image {
	max-height: 180px;
	object-fit: contain;
	margin-bottom: 10px;
}

.product-title {
	font-size: 16px;
	font-weight: 500;
	margin: 10px 0 5px;
}

.product-price {
	color: red;
	font-weight: bold;
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
						<li class="breadcrumb-item"><a
							href="${pageContext.request.contextPath}/uploads/${item.productimg }"><span>Trang
									chủ</span></a></li>
						<li class="breadcrumb-item active"><a
							href="<c:url value="/san-pham?page=1"/>"><strong><span>Tất
										cả sản phẩm</span></strong></a></li>
					</ul>
				</div>
			</div>
		</div>
	</section>
<body class="bg-light">
	<div class="container py-4">
	<form action="" id="formSubmit" method="get">
		<div class="row">
			<!-- Sidebar desktop -->
			<div class="col-md-3 d-none d-md-block">
				<div class="bg-white p-3 rounded mb-4">
					<h5 class="mb-3">Danh mục sản phẩm</h5>
					<ul class="list-unstyled">
						<c:forEach var = "item" items="${category.listResult}">
							<li><a href="${CategoryURL }/${item.slug}" class="text-decoration-none">${item.name }</a></li>
						</c:forEach>
					</ul>
					<hr>
					<h6 class="mb-2">Chọn mức giá</h6>
					<c:set var="priceRangeAsString"
							value="${fn:join(priceRange, ',')}" />
						<div class="form-check">
							<input class="form-check-input price-filter" type="checkbox"
								name="priceRange" value="0-100000"
								<c:if test="${fn:contains(priceRangeAsString, '0-100000')}">checked</c:if>>
							<label class="form-check-label">Dưới 100.000đ</label>
						</div>
						<div class="form-check">
							<input class="form-check-input price-filter" type="checkbox"
								name="priceRange" value="100000-200000"
								<c:if test="${fn:contains(priceRangeAsString, '100000-200000')}">checked</c:if>>
							<label class="form-check-label">100.000đ - 200.000đ</label>
						</div>
				</div>
			</div>

			<!-- Content -->
			<div class="col-md-9">
				<form action="" id="formSubmit" method="get">
					<div class="d-md-none mb-3">
						<button class="btn btn-outline-success" data-bs-toggle="offcanvas"
							data-bs-target="#filterOffcanvas">
							<i class="bi bi-funnel"></i> Bộ lọc sản phẩm
						</button>
					</div>
	
					<!-- Header -->
					<div class="d-flex justify-content-between align-items-center mb-3">
						<h4 class="mb-0">${category.name }</h4>
						<select class="form-select w-auto" onchange="if (this.value) window.location.href=this.value">
							<option
								value="<c:url value="${slug}?page=1&sortName=desc&sortBy=ctime${queryParams}"/>"
								${sort.sortBy =='ctime' && sort.sortName == 'desc'?'selected':''  }>Mới
								nhất</option>
							<option
								value="<c:url value="${slug}?page=1&sortName=asc&sortBy=ctime${queryParams}"/>"
								${sort.sortBy =='ctime' && sort.sortName == 'asc'?'selected':''  }>Cũ
								đến mới</option>
							<option
								value="<c:url value="${slug}?page=1&sortName=asc&sortBy=price${queryParams}"/>"
								${sort.sortBy =='price' && sort.sortName == 'asc'?'selected':''  }>Giá
								tăng dần</option>
							<option
								value="<c:url value="${slug}?page=1&sortName=desc&sortBy=price${queryParams}"/>"
								${sort.sortBy =='price' && sort.sortName == 'desc'?'selected':''  }>Giá
								giảm dần ${sort.sortBy} va ${sort.sortName}</option>
							<option
								value="<c:url value="${slug}?page=1&sortName=asc&sortBy=alpha${queryParams}"/>"
								${sort.sortBy =='alpha' && sort.sortName == 'asc'?'selected':''  }>Từ
								A->Z</option>
							<option
								value="<c:url value="${slug}?page=1&sortName=desc&sortBy=alpha${queryParams}"/>"
								${sort.sortBy =='alpha' && sort.sortName == 'desc'?'selected':''  }>Từ
								Z->A</option>
						</select>
					</div>
	
					<!-- Grid sản phẩm -->
					<div class="row g-3">
						<!-- Sản phẩm -->
						<c:forEach var="item" items="${model.listResult }">
							<div class="col-6 col-md-3">
								<div class="product-card text-center h-100">
									<span class="discount-badge"><fmt:formatNumber value="${(item.price - item.pricesale) * 100 /  item.price}" type="number" maxFractionDigits="0" />%</span> <img
										src="${pageContext.request.contextPath}/uploads/${item.productimg}"
										class="product-image w-100 mb-2" alt="Hành tây">
									<div class="fw-semibold">${item.name }</div>
									<span class="old-price">${item.price }đ</span>
									<span class="text-success fw-bold">${item.pricesale }đ</span>
									<button class="btn btn-success btn-sm mt-2">Thêm vào
										giỏ</button>
								</div>
							</div>
						</c:forEach>
						<ul class="pagination justify-content-center" id="pagination"></ul>
						<input type="hidden" value="${model.page}" id="page" name="page" />
						<!-- <input type="hidden" value="${model.totalPage}" id="maxPageItem" name="maxPageItem" /> -->
						<input type="hidden" value="${sort.sortName }" id="sortName"
							name="sortName" /> <input type="hidden" value="${sort.sortBy }"
							id="sortBy" name="sortBy" />
					</div>
			</div>
		</div>
		</form>
	</div>

	<!-- Offcanvas bộ lọc (mobile) -->
	<div class="offcanvas offcanvas-end" tabindex="-1" id="filterOffcanvas">
		<div class="offcanvas-header">
			<h5 class="offcanvas-title">Bộ lọc sản phẩm</h5>
			<button type="button" class="btn-close" data-bs-dismiss="offcanvas"></button>
		</div>
		<div class="offcanvas-body">
			<div class="mb-3">
				<h6>Danh mục sản phẩm</h6>
				<ul class="list-unstyled">
					<li><a href="#" class="text-decoration-none">Rau củ</a></li>
					<li><a href="#" class="text-decoration-none">Trái cây</a></li>
				</ul>
			</div>

			<div class="mb-3">
				<h6>
					Bạn chọn <a href="#" class="text-danger small ms-2">Bỏ hết</a>
				</h6>
				<div class="d-flex flex-wrap gap-1">
					<div class="filter-tag">
						Dưới 100.000đ
						<button class="remove">&times;</button>
					</div>
					<div class="filter-tag">
						100.000 - 200.000đ
						<button class="remove">&times;</button>
					</div>
				</div>
			</div>

			<div class="mb-3">
				<h6>Chọn mức giá</h6>
				<div class="form-check">
					<input class="form-check-input" type="checkbox" checked> <label
						class="form-check-label">Dưới 100.000đ</label>
				</div>
				<div class="form-check">
					<input class="form-check-input" type="checkbox" checked> <label
						class="form-check-label">100.000đ - 200.000đ</label>
				</div>
			</div>

			<div class="mb-3">
				<h6>Loại</h6>
				<div class="form-check">
					<input class="form-check-input" type="checkbox"> <label
						class="form-check-label">Rau ăn lá</label>
				</div>
				<div class="form-check">
					<input class="form-check-input" type="checkbox"> <label
						class="form-check-label">Rau ăn củ</label>
				</div>
			</div>
		</div>

	</div>

	<script>
	
		var totalPages = ${model.totalPage};
		var currentPage = ${model.page};
		const urlParams = new URLSearchParams(window.location.search);
		var limit = 5;
		$(function() {
			window.pagObj = $('#pagination').twbsPagination({
				totalPages : totalPages,
				visiblePages : 10,
				startPage : currentPage,
				prev: '<',
				next: '>',
				firstClass: 'd-none',
				lastClass: 'd-none',
				onPageClick : function(event, page) {
					if (currentPage != page) {
						$('#maxPageItem').val(limit);
						$('#page').val(page);
						
						const sortName = urlParams.get('sortName');
						if (sortName) {
							$('#sortName').val(sortName);
						} else {
							$('#sortName').remove(); // hoặc .val('') nếu bạn chỉ muốn để trống
						}
						const sortBy = urlParams.get('sortBy');
						if (sortBy) {
							$('#formSubmit #sortBy').val(sortBy);
						}
						else {
							$('#sortName').remove(); // hoặc .val('') nếu bạn chỉ muốn để trống
						}
						$('#formSubmit').submit();
					}
				}
			});
		});
	</script>
	<script>
		function applyFilters() {
			// Xoá tất cả input name=priceRange (cũ) trước khi thêm lại

			$('#formSubmit').submit();
		}
		$('.price-filter').on('change', applyFilters);
	</script>
</body>
</html>