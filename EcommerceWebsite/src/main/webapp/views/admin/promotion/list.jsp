<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<c:url var="APIurl" value="/api-admin-promotion" />
<c:url var="PromtionURL" value="/admin/promotion" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mã khuyến mãi</title>
</head>
<body>
	<main class="app-main">
	<div class="container">
		<form action="${PromtionURL}" id="formSubmit" method="get">
			<div class="main-content-inner">
				<div class="container-fluid">
					<div class="card">
						<div class="card-header">
							<h3 class="card-title">
								<strong class="text-uppercase text-danger">Nhật ký
									chỉnh sửa</strong>
							</h3>
							<div class="card-tools">
								<a class="btn btn-sm btn-success" href="${TopicURL}?type=edit"><i
									class="fas fa-plus"></i> Thêm</a> <a class="btn btn-sm btn-danger"
									id="btnDelete"><i class="fa fa-trash"></i> Xóa</a>

							</div>
						</div>
						<div class="col-xs-12">
							<div class="col-xs-12">
								<c:if test="${not empty messageResponse}">
									<div class="alert alert-${alert}">${messageResponse}</div>
								</c:if>
								<div class="widget-box table-filter ">
									<div class="table-btn-controls">
										<div class="pull-right tableTools-container float-right">
										</div>
									</div>
								</div>

								<div class="card-body">
									<div class="row">
										<div class="col-xs-12">
											<div class="table-responsive">
												<table class="table table-bordered">
													<thead>
														<tr>
															<th><input type="checkbox" id="checkAll"></th>
															<th>Tên</th>
															<th>Số lần sử dụng</th>
															<th>Giá trị</th>
															<th>Giới hạn giảm giá</th>
															<th>Giá trị tối thiểu</th>
															<th>Thao tác</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach var="item" items="${model.listResult}">
															<tr>
																<td><input type="checkbox" id="checkbox_${item.id}"
																	value="${item.id}"></td>
																<td>${item.name}</td>
																<td>${item.quantity - item.available}/${item.quantity}</td>
																<td><fmt:formatNumber value="${item.pricesale}"
																		type="currency" minFractionDigits="0"
																		currencySymbol="" /> ${item.byPercent?"%":"đ" }</td>
																<td><fmt:formatNumber
																		value="${item.limitedDiscount}" type="currency"
																		minFractionDigits="0" currencySymbol="" /></td>
																<td><fmt:formatNumber value="${item.minValue}"
																		type="currency" minFractionDigits="0"
																		currencySymbol="" /></td>
																<td><c:url var="editURL" value="/admin/promotion">
																		<c:param name="type" value="edit" />
																		<c:param name="id" value="${item.id}" />
																	</c:url> <a class="btn btn-sm btn-primary btn-edit"
																	data-toggle="tooltip" title="Cập nhật bài viết"
																	href='${editURL}'><i class="fa fa-pencil-square"
																		aria-hidden="true"></i> </a></td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
												<ul class="pagination justify-content-center" id="pagination"></ul>
												<input type="hidden" value="" id="page" name="page" /> <input
													type="hidden" value="" id="maxPageItem" name="maxPageItem" />
												<input type="hidden" value="" id="sortName" name="sortName" />
												<input type="hidden" value="" id="sortBy" name="sortBy" />
												<input type="hidden" value="" id="type" name="type" />
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
	</main>
	<script>
		var totalPages = ${model.totalPage};
		var currentPage = ${model.page};
		var limit = 5;
		$(function() {
			window.pagObj = $('#pagination').twbsPagination({
				totalPages : totalPages,
				visiblePages : 10,
				startPage : currentPage,
				onPageClick : function(event, page) {
					if (currentPage != page) {
						$('#maxPageItem').val(limit);
						$('#page').val(page);
						$('#sortName').val('createdAt');
						$('#sortBy').val('desc');
						$('#type').val('list');
						$('#formSubmit').submit();
					}
				}
			});
		});

		$("#checkAll").click(function() {
			$('input:checkbox').not(this).prop('checked', this.checked);
		});

		$('input:checkbox').not('#checkAll')
				.change(
						function() {
							var totalCheckboxes = $('input:checkbox').not(
									'#checkAll').length;
							var checkedCheckboxes = $('input:checkbox').not(
									'#checkAll:checked').length;

							// If all checkboxes are checked, check the #checkAll checkbox
							if (totalCheckboxes === checkedCheckboxes) {
								$('#checkAll').prop('checked', true);
							} else {
								// Otherwise, uncheck the #checkAll checkbox
								$('#checkAll').prop('checked', false);
							}
						});

		$("#btnDelete").click(function() {
			var data = {};
			var ids = $('tbody input[type=checkbox]:checked').map(function() {
				return $(this).val();
			}).get();
			data['ids'] = ids;
			deleteNew(data);
		});

		function deleteNew(data) {
			$.ajax({
						url : '${APIurl}',
						type : 'DELETE',
						contentType : 'application/json',
						data : JSON.stringify(data),
						success : function(result) {
							if (result.status === 'success') {
			                    Swal.fire({
			                        icon: 'success',
			                        title: 'Xóa thành công!',
			                        timer: 1500,
			                        showConfirmButton: false
			                    }).then(() => {
			                        window.location.href = "${PromtionURL}";
			                    });
			                } else {
			                    Swal.fire({
			                        icon: 'error',
			                        title: 'Có lỗi xảy ra!',
			                        text: result.message
			                    });
			                }
			            },
			        error: function (error) {
			        	Swal.fire({
			                icon: 'error',
			                title: 'Lỗi hệ thống!',
			                text: error.responseText || 'Vui lòng thử lại sau.'
			            });
			        }
			    });
			};	
							

	</script>
</body>
</html>