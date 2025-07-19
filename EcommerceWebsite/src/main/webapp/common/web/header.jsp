<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<%@ page import="com.ecommercewebsite.model.ConfigWebModel"%>
<%@ page import="com.ecommercewebsite.model.MenuModel"%>
<%@ page import="java.util.List"%>
<c:url var="cart" value="/gio-hang" />
<c:url var="home" value="/trang-chu" />
<c:url var="search" value="/search" />
<c:url var="category" value="/danh-muc" />
<c:url var="loagout" value="/danh-muc" />
<c:url var="login" value="/dang-nhap" />
<%
	ConfigWebModel configweb = (ConfigWebModel) application.getAttribute("configweb");
	List<MenuModel> menu = (List<MenuModel>) application.getAttribute("menu");
%>
<c:url var="slide" value="/quan-tri/web/trinh-chieu" />
<style>
.dropdown-item:hover {
	background-color: #f8f9fa;
}
</style>
<style>
.search-wrapper {
	position: relative;
}

#search-suggestions {
	position: absolute;
	top: 100%;
	left: 0;
	right: 0;
	z-index: 1000;
	display: none;
	max-height: 300px;
	overflow-y: auto;
	border-radius: 0.5rem;
	border: 1px solid #dee2e6;
	background-color: #fff;
}

#search-suggestions.show {
	display: block;
}
</style>
<header>
	<a class="image-header" href=""> <img
		src="<c:url value="/template/web/images/banner-8737-3849.png"/>"
		alt="He thong foodmart">
	</a>
	<div class="container">
		<div class="row align-items-center">

			<div class="col-4 header-right d-lg-none d-block">
				<div
					class="toggle-nav btn menu-bar mr-4 ml-0 p-0  d-lg-none d-flex text-white">
					<button class="navbar-toggler" type="button"
						data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar"
						aria-controls="offcanvasNavbar">
						<span class="navbar-toggler-icon"></span>
					</button>
					<button class="btn d-lg-none" type="button"
						data-bs-toggle="offcanvas" data-bs-target="#offcanvasMenu"
						aria-controls="offcanvasMenu">
						<i class="fas fa-bars fa-lg"></i>
					</button>
				</div>
			</div>

			<div class="col-4 col-lg-2 header-right">
				<div class="main-logo">
					<a href="${home}"> <img
						src="${pageContext.request.contextPath}/uploads/${configweb.logo}"
						alt="logo" class="img-fluid">
					</a>
				</div>
			</div>
			<div class="col-4 header-right d-lg-none d-block">
				<div class="mini-cart text-xs-center">
					<a class="img_hover_cart float-right" href="${cart }"
						title="Giỏ hàng"> <i class="fas fa-shopping-bag"></i> <span
						class="mx-2 d-md-block d-none">Giỏ hàng</span> <span
						class="count_item count_item_pr">${Cart.size() }</span>
					</a>
				</div>
			</div>
			<div class="col-xl-5 col-lg-4 col-12 header-center">
				<div class="search-bar row bg-light p-2 my-2 rounded-4">
					<div class="col-11 search-wrapper">
						<form id="search-form" class="text-center" action="${search}"
							method="get">
							<input type="text" id="search-input"
								class="form-control border-0 bg-transparent" name="name"
								placeholder="Tìm sản phẩm..." autocomplete="off">
						</form>
						<div id="search-suggestions" class="dropdown-menu"></div>
					</div>

					<div class="col-1 d-flex align-items-center justify-content-center">
						<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
							viewBox="0 0 24 24">
				      <path fill="currentColor"
								d="M21.71 20.29L18 16.61A9 9 0 1 0 16.61 18l3.68 3.68a1 1 0 0 0 1.42 0a1 1 0 0 0 0-1.39ZM11 18a7 7 0 1 1 7-7a7 7 0 0 1-7 7Z" />
				    </svg>
					</div>
				</div>
			</div>
			<div class="col-4 col-xl-5 col-lg-6 ">
				<ul
					class="header-left mb-0 float-right list-unstyled  d-flex align-items-center">
					<li class="media d-lg-flex d-none hotline "><i
						class="fas fa-user-circle mr-3 align-self-center"></i>
						<div class="media-body d-md-flex flex-column d-none ">
							<span>Hỗ trợ khách hàng</span> <a
								class="font-weight-bold d-block" href="tel:19006750"
								title="19006750">${configweb.hotline}</a>
						</div></li>
					<li class="ml-4 mr-4 mr-md-3 ml-md-3 media d-lg-flex d-none">
						<i class="fas fa-user-circle mr-3 align-self-center"></i>
						<div class="media-body d-md-flex flex-column d-none ">
							<a href="">Tài khoản</a> <small> <c:choose>
									<c:when test="${not empty USERMODEL.userName}">
										<a href="${login}?action=logout">Đăng xuất</a>
									</c:when>
									<c:otherwise>
										<a href="${login}?action=login">Đăng nhập</a>
									</c:otherwise>
								</c:choose>
							</small>
						</div>
					</li>
					<li class="cartgroup  ml-0 mr-2 mr-md-0 d-lg-flex d-none">
						<div class="mini-cart text-xs-center">
							<a class="img_hover_cart" href="${cart }" title="Giỏ hàng"> <i
								class="fas fa-shopping-bag"></i> <span
								class="mx-2 d-md-block d-none">Giỏ hàng</span> <span
								class="count_item count_item_pr">${Cart.size() }</span>
							</a>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div
				class="d-flex  justify-content-center justify-content-sm-between align-items-center">
				<nav class="main-menu d-flex navbar navbar-expand-lg"
					style="width: 100%">
					<div class="offcanvas offcanvas-end" tabindex="-1"
						id="offcanvasNavbar" aria-labelledby="offcanvasNavbarLabel">
						<div class="offcanvas-header justify-content-center">
							<button type="button" class="btn-close"
								data-bs-dismiss="offcanvas" aria-label="Close"></button>
						</div>
						<div class="offcanvas-body">
							<div class="menu-wrapper">
								<div class="main-menu-toggle">☰ Danh mục sản phẩm</div>
								<div class="menu-container">
									<div class="aside-content">
										<ul class="menu-level-1">
											<c:forEach var="level1" items="${listCategoryShow}">
												<li><a href="${category}/${level1.slug}">${level1.name}</a>
													<c:if test="${not empty level1.listChild}">
														<div class="menu-level-2">
															<ul>
																<c:forEach var="level2" items="${level1.listChild}">
																	<li>${level2.name}<c:if
																			test="${not empty level2.listChild}">
																			<ul class="menu-level-3">
																				<c:forEach var="level3" items="${level2.listChild}">
																					<li>${level3.name}</li>
																				</c:forEach>
																			</ul>
																		</c:if>
																	</li>
																</c:forEach>
															</ul>
														</div>
													</c:if></li>
											</c:forEach>
										</ul>
									</div>
								</div>
							</div>

							<ul
								class="navbar-nav justify-content-end menu-list list-unstyled d-flex gap-md-3 mb-0"
								style="width: 100%">
								<c:forEach var="item" items="${menu }">
									<li class="nav-item active"><a href="${item.slug }"
										class="nav-link">${item.name }</a></li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</nav>
				<div class="offcanvas offcanvas-start" tabindex="-1"
					id="offcanvasMenu" aria-labelledby="offcanvasMenuLabel">
					<div class="offcanvas-header bg-danger text-white">
						<h5 class="offcanvas-title" id="offcanvasMenuLabel">
							<i class="fas fa-user-circle me-2"></i> Tài khoản
						</h5>
						<button type="button" class="btn-close btn-close-white"
							data-bs-dismiss="offcanvas" aria-label="Close"></button>
					</div>

					<div class="offcanvas-body p-0">
						<ul class="list-group list-group-flush">

							<li class="list-group-item"><a href="#"
								class="d-flex justify-content-between align-items-center"> <span><i
										class="fas fa-mobile-alt me-2"></i> Điện thoại, máy tính bảng</span>
									<i class="fas fa-chevron-right"></i>
							</a></li>

							<li class="list-group-item"><a href="#"
								class="d-flex justify-content-between align-items-center"> <span><i
										class="fas fa-heartbeat me-2"></i> Làm đẹp - Sức khỏe</span> <i
									class="fas fa-chevron-right"></i>
							</a></li>

							<li class="list-group-item"><a href="#"
								class="d-flex justify-content-between align-items-center"> <span><i
										class="fas fa-baby me-2"></i> Đồ chơi, Mẹ và bé</span> <i
									class="fas fa-chevron-right"></i>
							</a></li>

							<li class="list-group-item"><a href="#"
								class="d-flex justify-content-between align-items-center"> <span><i
										class="fas fa-apple-alt me-2"></i> Hàng tiêu dùng, thực phẩm</span> <i
									class="fas fa-chevron-right"></i>
							</a></li>

							<li class="list-group-item"><a href="#"
								class="d-flex justify-content-between align-items-center"> <span><i
										class="fas fa-tv me-2"></i> Điện tử - Điện lạnh</span> <i
									class="fas fa-chevron-right"></i>
							</a></li>

							<!-- Các mục khác tương tự -->

						</ul>

						<hr class="my-2">

						<ul class="list-group list-group-flush small text-muted">
							<li class="list-group-item"><i
								class="fas fa-certificate me-2"></i> Sản phẩm chính hãng</li>
							<li class="list-group-item"><i
								class="fas fa-shipping-fast me-2"></i> Kiểm tra khi nhận hàng</li>
							<li class="list-group-item"><i class="fas fa-undo me-2"></i>
								Hoàn tiền 111% nếu giả</li>
						</ul>

						<div class="d-flex justify-content-around py-3 border-top">
							<a href="tel:${configweb.hotline}"
								class="btn btn-outline-primary btn-sm"> <i
								class="fas fa-phone-alt me-1"></i> Gọi điện
							</a> <a href="#" class="btn btn-outline-success btn-sm"> <i
								class="fas fa-comment-dots me-1"></i> Nhắn tin
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
  const searchInput = document.getElementById('search-input');
  const suggestionBox = document.getElementById('search-suggestions');

  searchInput.addEventListener('keyup', function () {
    const keyword = this.value.trim();

    if (keyword.length === 0) {
      suggestionBox.style.display = 'none';
      return;
    }

    fetch('http://localhost:8080/EcommerceWebsite/api-product-search?name=' + encodeURIComponent(keyword))
      .then(res => res.json())
      .then(data => {
        if (data.length === 0) {
          suggestionBox.style.display = 'none';
          return;
        }

        suggestionBox.innerHTML = '';

        data.forEach(product => {
          const item = document.createElement('a');
          item.className = 'dropdown-item';
          item.href = '/product/detail?id=' + product.id;
          item.innerHTML = product.name;
          suggestionBox.appendChild(item);
        });

        suggestionBox.style.display = 'block';
      })
      .catch(err => {
        console.error(err);
        suggestionBox.style.display = 'none';
      });
  });

  // Ẩn dropdown khi click ra ngoài
  document.addEventListener('click', function (e) {
    if (!searchInput.contains(e.target) && !suggestionBox.contains(e.target)) {
      suggestionBox.style.display = 'none';
    }
  });
</script>

</header>