<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<c:url var="topic" value="/admin/topic" />
<c:url var="banner" value="/quan-tri/web/anh-bia" />
<c:url var="topbar" value="/quan-tri/web/thanh-tren" />
<c:url var="slide" value="/quan-tri/web/trinh-chieu" />
<c:url var="post" value="/admin/new" />
<c:url var="page" value="/admin/page" />
<c:url var="service" value="/quan-tri/web/dich-vu" />
<c:url var="menu" value="/quan-tri/web/menu" />
<c:url var="account" value="/admin/account" />
<c:url var="customer" value="/admin/customer" />
<c:url var="coupon" value="/admin/coupon" />
<c:url var="configweb" value="/admin/configweb" />
<c:url var="home" value="/admin" />
<c:url var="manufacturer" value="/quan-tri/hang" />
<c:url var="bill" value="/admin/bill" />
<c:url var="category" value="/admin/category" />
<c:url var="search" value="/quan-tri/search" />
<c:url var="contact" value="/admin/contact" />
<c:url var="product" value="/admin/product" />
<c:url var="utilities" value="/quan-tri/tien-ich" />
<c:url var="optiongroup" value="/quan-tri/nhom-tuy-chon" />
<c:url var="option" value="/quan-tri/tuy-chon" />
<c:url var="prodoption" value="/quan-tri/tuy-chon-san-pham" />
<c:url var="accountinfo" value="/admin/profile" />
<c:url var="productimg" value="/quan-tri/hinh-anh-san-pham" />
<c:url var="smartpay" value="/quan-tri/web/nha-thanh-toan" />
<c:url var="socialnetwork" value="/admin/socialnetwork" />
<c:url var="note" value="/admin/note" />
<c:url var="logout" value="/thoat?action=logout" />
<nav class="app-header navbar navbar-expand bg-body">
	<!--begin::Container-->
	<div class="container-fluid">
		<!--begin::Start Navbar Links-->
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link"
				data-lte-toggle="sidebar" href="#" role="button"> <i
					class="bi bi-list"></i>
			</a></li>
			<li class="nav-item d-none d-md-block"><a href="${home }"
				class="nav-link">Home</a></li>
			<li class="nav-item d-none d-md-block"><a href="${contact }"
				class="nav-link">Contact</a></li>
		</ul>
		<!--begin::SEARCH-->
		
			<form class="form-inline ml-3" action="${search}" method="GET">
			<div class="input-group input-group-sm">
				<input class="form-control form-control-navbar" type="search"
					name="q" placeholder="Tìm kiếm đơn hàng" aria-label="Search">
				<input type="hidden" name="currentPage" value="1">
				<div class="input-group-append">
					<button class="btn btn-navbar" type="submit">
						<i class="bi bi-search"></i>
					</button>
				</div>
			</div>
		</form>
		<ul class="navbar-nav ms-auto">
			<!--end::Navbar Search-->
			<!--begin::Messages Dropdown Menu-->
			<li class="nav-item dropdown"><a class="nav-link"
				data-bs-toggle="dropdown" href="#"> <i class="bi bi-chat-text"></i>
					<span class="navbar-badge badge text-bg-danger">3</span>
			</a>
				<div class="dropdown-menu dropdown-menu-lg dropdown-menu-end">
					<a href="#" class="dropdown-item"> <!--begin::Message-->
						<div class="d-flex">
							<div class="flex-shrink-0">
								<img src="../../dist/assets/img/user1-128x128.jpg"
									alt="User Avatar" class="img-size-50 rounded-circle me-3" />
							</div>
							<div class="flex-grow-1">
								<h3 class="dropdown-item-title">
									Brad Diesel <span class="float-end fs-7 text-danger"><i
										class="bi bi-star-fill"></i></span>
								</h3>
								<p class="fs-7">Call me whenever you can...</p>
								<p class="fs-7 text-secondary">
									<i class="bi bi-clock-fill me-1"></i> 4 Hours Ago
								</p>
							</div>
						</div> <!--end::Message-->
					</a>
					<div class="dropdown-divider"></div>
					<a href="#" class="dropdown-item"> <!--begin::Message-->
						<div class="d-flex">
							<div class="flex-shrink-0">
								<img src="../../dist/assets/img/user8-128x128.jpg"
									alt="User Avatar" class="img-size-50 rounded-circle me-3" />
							</div>
							<div class="flex-grow-1">
								<h3 class="dropdown-item-title">
									John Pierce <span class="float-end fs-7 text-secondary">
										<i class="bi bi-star-fill"></i>
									</span>
								</h3>
								<p class="fs-7">I got your message bro</p>
								<p class="fs-7 text-secondary">
									<i class="bi bi-clock-fill me-1"></i> 4 Hours Ago
								</p>
							</div>
						</div> <!--end::Message-->
					</a>
					<div class="dropdown-divider"></div>
					<a href="#" class="dropdown-item"> <!--begin::Message-->
						<div class="d-flex">
							<div class="flex-shrink-0">
								<img src="../../dist/assets/img/user3-128x128.jpg"
									alt="User Avatar" class="img-size-50 rounded-circle me-3" />
							</div>
							<div class="flex-grow-1">
								<h3 class="dropdown-item-title">
									Nora Silvester <span class="float-end fs-7 text-warning">
										<i class="bi bi-star-fill"></i>
									</span>
								</h3>
								<p class="fs-7">The subject goes here</p>
								<p class="fs-7 text-secondary">
									<i class="bi bi-clock-fill me-1"></i> 4 Hours Ago
								</p>
							</div>
						</div> <!--end::Message-->
					</a>
					<div class="dropdown-divider"></div>
					<a href="#" class="dropdown-item dropdown-footer">See All
						Messages</a>
				</div></li>
			<!--end::Messages Dropdown Menu-->
			<!--begin::Notifications Dropdown Menu-->
			<li class="nav-item dropdown"><a class="nav-link"
				data-bs-toggle="dropdown" href="#"> <i class="bi bi-bell-fill"></i>
					<span class="navbar-badge badge text-bg-warning">15</span>
			</a>
				<div class="dropdown-menu dropdown-menu-lg dropdown-menu-end">
					<span class="dropdown-item dropdown-header">15 Notifications</span>
					<div class="dropdown-divider"></div>
					<a href="#" class="dropdown-item"> <i
						class="bi bi-envelope me-2"></i> 4 new messages <span
						class="float-end text-secondary fs-7">3 mins</span>
					</a>
					<div class="dropdown-divider"></div>
					<a href="#" class="dropdown-item"> <i
						class="bi bi-people-fill me-2"></i> 8 friend requests <span
						class="float-end text-secondary fs-7">12 hours</span>
					</a>
					<div class="dropdown-divider"></div>
					<a href="#" class="dropdown-item"> <i
						class="bi bi-file-earmark-fill me-2"></i> 3 new reports <span
						class="float-end text-secondary fs-7">2 days</span>
					</a>
					<div class="dropdown-divider"></div>
					<a href="#" class="dropdown-item dropdown-footer"> See All
						Notifications </a>
				</div></li>
			<!--end::Notifications Dropdown Menu-->
			<!--begin::Fullscreen Toggle-->
			<li class="nav-item"><a class="nav-link" href="#"
				data-lte-toggle="fullscreen"> <i data-lte-icon="maximize"
					class="bi bi-arrows-fullscreen"></i> <i data-lte-icon="minimize"
					class="bi bi-fullscreen-exit" style="display: none"></i>
			</a></li>
			<!--end::Fullscreen Toggle-->
			<!--begin::User Menu Dropdown-->
			<li class="nav-item dropdown user-menu"><a href="#"
				class="nav-link dropdown-toggle" data-bs-toggle="dropdown"> <img
					src="../../dist/assets/img/user2-160x160.jpg"
					class="user-image rounded-circle shadow" alt="User Image" />
					<span class="d-none d-md-inline">Alexander Pierce</span>
			</a>
				<ul class="dropdown-menu dropdown-menu-lg dropdown-menu-end">
					<!--begin::User Image-->
					<li class="user-header text-bg-primary"><img
						src="../../dist/assets/img/user2-160x160.jpg"
						class="rounded-circle shadow" alt="User Image" />
						<p>
							Alexander Pierce - Web Developer <small>Member since Nov.
								2023</small>
						</p></li>
					<!--end::User Image-->
					<!--begin::Menu Body-->
					<li class="user-body">
						<!--begin::Row-->
						<div class="row">
							<div class="col-4 text-center">
								<a href="#">Followers</a>
							</div>
							<div class="col-4 text-center">
								<a href="#">Sales</a>
							</div>
							<div class="col-4 text-center">
								<a href="#">Friends</a>
							</div>
						</div> <!--end::Row-->
					</li>
					<!--end::Menu Body-->
					<!--begin::Menu Footer-->
					<li class="user-footer"><a href="#"
						class="btn btn-default btn-flat">Profile</a> <a href="#"
						class="btn btn-default btn-flat float-end">Sign out</a></li>
					<!--end::Menu Footer-->
				</ul></li>
			<!--end::User Menu Dropdown-->
		</ul>
		<!--end::End Navbar Links-->
	</div>
	<!--end::Container-->
</nav>
<!--end::Header-->


<!--begin::Sidebar-->
<aside class="app-sidebar bg-body-secondary shadow" data-bs-theme="dark">
	<!--begin::Sidebar Brand-->
	<div class="sidebar-brand">
					<div class="image"></div>
				<div class="info">
					<a href="#" class="d-block">Chào ${USERMODEL.fullName }</a>
				</div>
	</div>
	<!--end::Sidebar Brand-->
	<!--begin::Sidebar Wrapper-->
	<div class="sidebar-wrapper">
		<nav class="mt-2">
			<!--begin::Sidebar Menu-->
			<ul class="nav sidebar-menu flex-column" data-lte-toggle="treeview"
				role="menu" data-accordion="false">
				<li class="nav-item menu-open"><a href="#"
					class="nav-link active"> <i class="nav-icon bi bi-speedometer"></i>
						<p>
							Sản phẩm <i class="nav-arrow bi bi-chevron-right"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
						<li class="nav-item"><a href="${product }"
							class="nav-link active"> <i class="nav-icon bi bi-circle"></i>
								<p>Danh sách sản phẩm</p>
						</a></li>
						<li class="nav-item"><a href="${category }" class="nav-link">
								<i class="nav-icon bi bi-circle"></i>
								<p>Danh mục sản phẩm</p>
						</a></li>
					</ul></li>
					<li class="nav-item has-treeview"><a href="#" class="nav-link">
							<i class="nav-icon fas fa-copy"></i>
							<p>
								Bài viết <i class="fas fa-angle-left right"></i>
							</p>
					</a>
						<ul class="nav nav-treeview">
							<li class="nav-item ml-2"><a href="${post}" class="nav-link">
									<i class="far fa-circle nav-icon text-danger"></i>
									<p>Danh sách bài viết</p>
							</a></li>
							<li class="nav-item ml-2"><a href="${topic}"
								class="nav-link"> <i
									class="far fa-circle nav-icon text-danger"></i>
									<p>Chủ đề bài viết</p>
							</a></li>
							<li class="nav-item ml-2"><a href="${page}" class="nav-link">
									<i class="far fa-circle nav-icon text-danger"></i>
									<p>Trang đơn</p>
							</a></li>
						</ul></li>
					<li class="nav-item"><a href="${bill}" class="nav-link"> <i
							class="far fa-circle nav-icon text-info"></i>
							<p>Đơn hàng</p>
					</a></li>
					<li class="nav-item"><a href="${customer }" class="nav-link">
							<i class="far fa-circle nav-icon text-success"></i>
							<p>Khách hàng</p>
					</a></li>
					<li class="nav-item"><a href="${contact}" class="nav-link">
							<i class="far fa-circle nav-icon text-danger"></i>
							<p>Liên hệ</p>
					</a></li>
				<li class="nav-item has-treeview"><a href="#" class="nav-link">
							<i class="nav-icon fas fa-chart-pie"></i>
							<p>
								Giao diện <i class="right fas fa-angle-left"></i>
							</p>
					</a>
						<ul class="nav nav-treeview">
							<li class="nav-item ml-2"><a href="${service}"
								class="nav-link"> <i
									class="far fa-circle nav-icon text-primary"></i>
									<p>Dịch vụ</p>
							</a></li>
							<li class="nav-item ml-2"><a href="${coupon}"
								class="nav-link"> <i
									class="far fa-circle nav-icon text-secondary"></i>
									<p>Mã khuyến mãi</p>
							</a></li>
							<li class="nav-item ml-2"><a href="${smartpay}"
								class="nav-link"> <i
									class="far fa-circle nav-icon text-success"></i>
									<p>Nhà thanh toán</p>
							</a></li>
							<li class="nav-item ml-2"><a href="${socialnetwork}"
								class="nav-link"> <i
									class="far fa-circle nav-icon text-warning"></i>
									<p>Mạng xã hội</p>
							</a></li>
							<li class="nav-item ml-2"><a href="${menu }"
								class="nav-link"> <i
									class="far fa-circle nav-icon text-info"></i>
									<p>Menu</p>
							</a></li>
							<li class="nav-item ml-2"><a href="${slide}"
								class="nav-link"> <i
									class="far fa-circle nav-icon text-primary"></i>
									<p>Slider</p>
							</a></li>
							<li class="nav-item ml-2"><a href="${banner}"
								class="nav-link"> <i
									class="far fa-circle nav-icon text-secondary"></i>
									<p>Banner</p>
							</a></li>
							<li class="nav-item ml-2"><a href="${topbar}"
								class="nav-link"> <i
									class="far fa-circle nav-icon text-success"></i>
									<p>Topbar</p>
							</a></li>
						</ul></li>
					<li class="nav-item has-treeview"><a href="#" class="nav-link">
							<i class="nav-icon fas fa-chart-pie"></i>
							<p>
								Hệ thống <i class="right fas fa-angle-left"></i>
							</p>
					</a>
						<ul class="nav nav-treeview">
							<li class="nav-item ml-2"><a href="${account }"
								class="nav-link"> <i
									class="far fa-circle nav-icon text-danger"></i>
									<p>Thành viên</p>
							</a></li>
							<li class="nav-item ml-2"><a href="${configweb }"
								class="nav-link"> <i
									class="far fa-circle nav-icon text-danger"></i>
									<p>Cấu hình website</p>
							</a></li>
							<li class="nav-item ml-2"><a href="${note }"
								class="nav-link"> <i
									class="far fa-circle nav-icon text-danger"></i>
									<p>Nhật ký quản trị</p>
							</a></li>
						</ul></li>
					<li class="nav-header">THÔNG TIN</li>
					<li class="nav-item ml-2"><a href="${logout}" class="nav-link">
							<i class="nav-icon far fa-circle text-danger"></i>
							<p class="text">Đăng xuất</p>
					</a></li>
					<li class="nav-item ml-2"><a href="${accountinfo }"
						class="nav-link"> <i
							class="nav-icon far fa-circle text-warning"></i>
							<p>Thông tin cá nhân</p>
					</a></li>
			</ul>
			<!--end::Sidebar Menu-->
		</nav>
	</div>
	<!--end::Sidebar Wrapper-->
</aside>