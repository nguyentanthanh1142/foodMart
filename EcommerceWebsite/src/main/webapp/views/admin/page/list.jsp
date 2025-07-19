<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<c:url var="APIurl" value="/api-admin-new" />
<c:url var="PageURL" value="/admin/page" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang đơn</title>
</head>
<body>
	<main class="app-main">
	<div class="container">
		<form action="${ProductUrl }" id="formSubmit" method="get"
			accept-charset="UTF-8">
			<div class="main-content-inner">
				<div class="container-fluid">
					<div class="card">
						<div class="card-header">
							<h3 class="card-title">
								<strong class="text-uppercase text-danger">Danh sách
									sản phẩm</strong>
							</h3>
							<div class="card-tools">
								<a class="btn btn-sm btn-success" href="${PageURL}?type=edit"><i
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
												<table class="table table-bordered" id="lTable">
													<thead>
														<tr>
															<th><input type="checkbox" id="checkAll"></th>
															<th>ID</th>
															<th>Hình ảnh</th>
															<th>Tên bài viết</th>
															<th>Mô tả ngắn</th>
															<th>Thao tác</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach var="item" items="${model.listResult}">
															<tr>
																<td><input type="checkbox" id="checkbox_${item.id}"
																	value="${item.id}"></td>
																<td>${item.id}</td>
																<th style="width: 200px;"><img
																	src="${pageContext.request.contextPath}/uploads/${item.thumbnail}"
																	width="250px" height="auto"></th>
																<td>${item.title}</td>
																<td>${item.shortDescription}</td>
																<td><c:url var="editURL" value="/admin/page">
																		<c:param name="type" value="edit" />
																		<c:param name="id" value="${item.id}" />
																	</c:url> <a class="btn btn-sm btn-primary btn-edit"
																	data-toggle="tooltip" title="Cập nhật bài viết"
																	href='${editURL}'><i class="fa fa-pencil-square"
																		aria-hidden="true"></i></a></td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
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

		$("#btnDelete").click(function(e) {
			var data = {};
			var ids = $('tbody input[type=checkbox]:checked').map(function() {
				return $(this).val();
			}).get();
			data['ids'] = ids;
			deleteNew(data);
		});

		function deleteNew(data) {
			$
					.ajax({
						url : '${APIurl}',
						type : 'DELETE',
						contentType : 'application/json',
						data : JSON.stringify(data),
						success : function(result) {
							window.location.href = "${NewURL}?type=list&message=delete_success";
						},
						error : function(error) {
							window.location.href = "${NewURL}?type=list&page=1&message=error_system";
						}
					});
		};
	</script> 
	<script>
		$(document).ready(function() {
			$('#lTable').DataTable({
				language : {
					lengthMenu : "Hiển thị _MENU_ sản phẩm mỗi trang",
					zeroRecords : "Không tìm thấy sản phẩm nào",
					info : "Hiển thị _START_ đến _END_ của _TOTAL_ sản phẩm",
					infoEmpty : "Không có dữ liệu",
					infoFiltered : "(lọc từ tổng cộng _MAX_ sản phẩm)",
					search : "Tìm kiếm:",
					paginate : {
						first : "Đầu",
						last : "Cuối",
						next : "Sau",
						previous : "Trước"
					}
				}
			});
		});
	</script>
</body>
</html>