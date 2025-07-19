<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="APIurl" value="/api-admin-config" />
<c:url var="configURL" value="/admin/configweb" />
<c:url var="onOff" value="/admin/configweb/status" />
<html>
<head>
<title>Cấu hình website</title>
</head>
<body>
	<!--begin::App Main-->
	<main class="app-main">
	<div class="app-content mt-3">
		<!--begin::Container-->
		<div class="container-fluid">
			<div class="card">
				<div class="card-header">
					<h3 class="card-title">
						<strong class="text-uppercase text-danger">Chi tiết
							website</strong>
					</h3>
					<div class="card-tools">
						<c:choose>
							<c:when test="${config.status == 0}">
								<a class="btn btn-sm btn-danger" href="${onOff}/${config.id}">
									Tắt bảo trì</a>
							</c:when>
							<c:when test="${config.status == 1}">
								<a class="btn btn-sm btn-success" href="${onOff}/${config.id}">
									Bật bảo trì</a>
							</c:when>

						</c:choose>
						<a class="btn btn-sm btn-info" href="${configURL}?type=edit"><i
							class="fas fa-plus"></i> Chỉnh sửa</a>
					</div>
				</div>
				<div class="card-body">

					<c:if test="${not empty msg}">
						<div class="alert alert-success alert-dismissible" role="alert">
							<button type="button" class="close" data-dismiss="alert"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<strong>${msg}</strong>
						</div>
					</c:if>
					<c:if test="${not empty msgfail}">
						<div class="alert alert-danger alert-dismissible" role="alert">
							<button type="button" class="close" data-dismiss="alert"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<strong>${msgfail}</strong>
						</div>
					</c:if>
					<table class="table table-bordered table-hover mb-4">
						<thead>
							<h3>Thông tin website</h3>
						</thead>
						<tbody>
							<tr>
								<th>Tên:</th>
								<td>${config.webname}</td>
							</tr>
							<tr>
								<th>Mô tả:</th>
								<td>${config.web_detail}</td>
							</tr>
							<tr>
								<th>Hotline:</th>
								<td>${config.hotline}</td>
							</tr>
							<tr>
								<th>Email:</th>
								<td>${config.email}</td>
							</tr>
							<tr>
								<th>Địa chỉ:</th>
								<td>${config.address_store}</td>
							</tr>
							<tr>
								<th>Logo website:</th>
								<td><img class="img-logo"
									src="${pageContext.request.contextPath}/uploads/${config.logo}"
									alt=""></td>
							</tr>
							<tr>
								<th>Logo moblie:</th>
								<td><img class="img-logo"
									src="${pageContext.request.contextPath}/uploads/${config.logo}"
									alt=""></td>
							</tr>
							<tr>
								<th>Icon website:</th>
								<td><img class="img-logo"
									src="${pageContext.request.contextPath}/uploads/${config.icon}"
									alt=""></td>
							</tr>
						</tbody>
					</table>
					<!-- /.card-body -->
				</div>
			</div>
			<!-- /.card -->
		</div>
		<!--end::Container-->
	</div>
	<!--end::App Content--> </main>
	<!--end::App Main-->
</body>
</html>
>
