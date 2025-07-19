<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="BillUrl" value="/admin/bill" />
<c:url var="listBillUrl" value="/admin/bill" />
<c:url var="APIchangeStatus" value="/api-admin-change-bill-status" />
<html>
<head>
<title>Chi tiết đơn hàng</title>
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
						<strong class="text-danger text-uppercase">Chi tiết đơn
							hàng</strong>
					</h3>
					<div class="card-tools">
						<form id="formSubmit" enctype="multipart/form-data" accept-charset="UTF-8">
						        <input type="hidden" name="id" value="${model.id }"/>
						        <input type="hidden" name="status" value="${model.status }"/>
						</form>
						<c:if test="${model.status != -1 && model.status != 4}">
							<a class="btn btn-sm btn-danger" href="${cancel }/${model.id}" id="btnCancel"
								onclick="return confirm('Bạn có chắc chắn muốn hủy đơn hàng?');">Hủy
								đơn hàng</a>
						</c:if>
						<c:if test="${model.status != -1 && model.status != 4}">
							<a class="btn btn-sm btn-info" id="btnChangeStatus" href="#"
								onclick="return confirm('Bạn có chắc chắn muốn chuyển trạng thái đơn hàng?');">Chuyển
								trạng thái đơn hàng</a>

						</c:if>
        <a class="btn btn-sm btn-danger" href="${listBillUrl }"><i
            class="fas fa-backward"></i> Quay lại</a>
    
					</div>
				</div>
				<div class="card-body">

					<table class="table table-bordered table-hover mb-4">
						<thead>
							<h3>Thông tin người nhận hàng</h3>
						</thead>
						<c:if test="${model.status == -1 }">
							<div class="alert alert-danger" role="alert">Đơn hàng đã
								hủy</div>
						</c:if>
						<c:if test="${model.status != -1 }">
							<div class="mb-3">
								<ul class="steps">
									<li
										class="step ${model.status > 0 ? 'step-success': (model.status == 0 ? 'step-active':'' ) }">
										<div class="step-content">
											<span class="step-circle" style="z-index: 4;"><i
												class="fas fa-clipboard"></i></span> <span class="step-text">Đơn
												hàng đã đặt</span>
										</div>
									</li>
									<li
										class="step ${model.status > 1 ? 'step-success': (model.status == 1 ? 'step-active':'' ) }">
										<div class="step-content">
											<span class="step-circle" style="z-index: 3;"><i
												class="fas fa-clipboard-check"></i></span> <span class="step-text">Đã
												xác nhận đơn hàng</span>
										</div>
									</li>
									<li
										class="step ${model.status > 2 ? 'step-success': (model.status == 2 ? 'step-active':'' ) }">
										<div class="step-content">
											<span class="step-circle" style="z-index: 2;"><i
												class="fas fa-truck"></i></span> <span class="step-text">Đã
												giao cho ĐVVC</span>
										</div>
									</li>
									<li
										class="step ${model.status > 3 ? 'step-success': (model.status == 3 ? 'step-active':'' ) }">
										<div class="step-content">
											<span class="step-circle" style="z-index: 1;"><i
												class="fas fa-hourglass-half"></i></span> <span class="step-text">Đơn
												hàng đang giao</span>
										</div>
									</li>
									<li
										class="step ${model.status > 4 ? 'step-success': (model.status == 4 ? 'step-active':'' ) }">
										<div class="step-content">
											<span class="step-circle" style="z-index: 0;"><i
												class="far fa-star"></i></span> <span class="step-text">Đơn
												hàng đã giao</span>
										</div>
									</li>
								</ul>
							</div>
						</c:if>
						<tbody>
							<tr>
								<th>Tên:</th>
								<td>${model.display_name }</td>
							</tr>
							<tr>
								<th>Số điện thoại:</th>
								<td>${model.phone}</td>
							</tr>
							<tr>
								<th>Email:</th>
								<td>${model.email}</td>
							</tr>
							<tr>
								<th>Địa chỉ nhận hàng:</th>
								<td>${model.address},${model.province },${model.district },
									${model.city }</td>
							</tr>
							<tr>
								<th>Ngày đặt hàng:</th>
								<td>${model.createdAt}</td>
							</tr>
							<tr>
								<th>Phiếu giảm giá:</th>
								<td>${model.coupon == 1?'Có':'Không'}</td>
							</tr>

						</tbody>
					</table>
					<table class="table table-bordered table-hover mb-4">
						<thead>
							<h3>Sản phẩm đã đặt</h3>
						</thead>
						<tbody>
							<tr>
								<th style="width: 350px;">Sản phẩm</th>
								<th style="width: 120px;">Mã sản phẩm</th>
								<th>Giá</th>
								<th>Số lượng</th>
								<th>Tổng</th>
							</tr>

							<c:forEach var="item" items="${ billdetail }">
								<tr>
									<td style="width: 350px;">${item.productinfo.name }</td>
									<td style="width: 120px;">${item.productinfo.id }</td>
									<td><fmt:formatNumber
											value="${item.productinfo.pricesale}" type="number" />đ</td>
									<td>${item.quanty}</td>
									<td><fmt:formatNumber value="${item.total }" type="number" />đ</td>
								</tr>
							</c:forEach>
							<tr>
								<td colspan="5" class="text-right pr-5">Phí vận chuyển: <fmt:formatNumber
										value="40000" type="number" />đ
								</td>
							</tr>
							<tr>
								<td colspan="5" class="text-right pr-5">Giảm giá coupon: <fmt:formatNumber
										value="${couponEntity.byPercent? couponEntity.pricesale:couponEntity.pricesale}"
										type="number" />
								</td>
							</tr>
							<tr>
								<td colspan="5" class="text-right pr-5">Tổng cộng: <fmt:formatNumber
										value="${model.total}" type="number" />đ
								</td>
							</tr>
							
						</tbody>
					</table>
				</div>
				<!-- /.card-body -->

			</div>
			<!-- /.card -->
		</div>
		<!--end::Container-->
	</div>
	<!--end::App Content--> </main>
	<!--end::App Main-->
	<script>
	
	$('#btnCancel').click(function(e) {
		e.preventDefault();
		var data={};
		var form = document.getElementById('formSubmit');
		var formData = $('#formSubmit').serializeArray();
		$.each(formData, function(i, v) {
			data[""+v.name+""] = v.value;
			console.log(v.name + ": " + v.value);
		});
		data["status"] = -1;
		updateTopic(data);
	});
	
	
$('#btnChangeStatus').click(function(e) {
	e.preventDefault();
	var data={};
	var form = document.getElementById('formSubmit');
	var formData = $('#formSubmit').serializeArray();
	$.each(formData, function(i, v) {
		data[""+v.name+""] = v.value;
		console.log(v.name + ": " + v.value);
	});
	updateTopic(data);
});

		function updateTopic(data) {
			$
					.ajax({
						url : '${APIchangeStatus}',
						type : 'PUT',
						data : JSON.stringify(data),
						contentType : "application/json", 
						dataType : 'json',
						success : function(result) {
						     if (result.status === 'success') {
				                    Swal.fire({
				                        icon: 'success',
				                        title: 'Thao tác thành công!',
				                        timer: 1500,
				                        showConfirmButton: false
				                    }).then(() => {
				                        window.location.href = "${BillUrl}?type=edit&id=" + encodeURIComponent(result.data.id);
				                    });
				                } else {
				                    Swal.fire({
				                        icon: 'error',
				                        title: 'Có lỗi xảy ra!',
				                        text: result.message
				                    });
				                }
				            },
				            error: function(xhr) {
				                Swal.fire({
				                    icon: 'error',
				                    title: 'Lỗi hệ thống!',
				                    text: xhr.responseText || 'Vui lòng thử lại sau.'
				                });
				            }
				        });
				    }
</script>
</body>
</html>
>
