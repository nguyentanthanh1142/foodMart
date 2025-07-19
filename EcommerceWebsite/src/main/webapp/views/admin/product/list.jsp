<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<c:url var="APIurl" value="/api-admin-product"/>
<c:url var ="ProductUrl" value="/admin/product"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách sản phẩm</title>
</head>
<body>
	<main class="app-main">
	<div class="container">
		<form action="${ProductUrl }" id="formSubmit"
			method="get" accept-charset="UTF-8">
			<div class="main-content-inner">
				<div class="container-fluid">
					<div class="card">
						<div class="card-header">
							<h3 class="card-title">
								<strong class="text-uppercase text-danger">Danh sách sản phẩm</strong>
							</h3>
							<div class="card-tools">
								<a class="btn btn-sm btn-success" href="${ProductUrl}?type=edit"><i
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
													<th> <input type="checkbox" id="checkAll"></th>
													<th>Tên sản phẩm</th>
													<th> Tình trạng</th>
													<th> Danh mục</th>
													<th> Cập nhật</th>
													<th> Thao tác</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="item" items="${model.listResult}">
													<tr>
														<td><input type="checkbox" id="checkbox_${item.id}"
															value="${item.id}"></td>
														<td>${item.name}</td>
														<td>${item.status==1?"Công khai":"Không công khai"}</td>
														<td>${mapCate[item.catid]}</td>
														<td><fmt:formatDate value="${item.modifiedAt}" pattern="d/M 'vào lúc' HH:mm" /></td>
														<td><c:url var="editURL" value="/admin/product">
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

	$('input:checkbox').not('#checkAll').change(function() {
	    var totalCheckboxes = $('input:checkbox').not('#checkAll').length;
	    var checkedCheckboxes = $('input:checkbox').not('#checkAll:checked').length;
	    
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
		var ids = $('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
		data['ids'] = ids;
		console.log(data)
		deleteNew(data);
	});
	
	function deleteNew(data){
		$.ajax({
			url: '${APIurl}',
			type: 'DELETE',
			contentType: 'application/json',
			data: JSON.stringify(data),
            success: function (result) {
            	if (result.status === 'success') {
                    Swal.fire({
                        icon: 'success',
                        title: 'Xóa thành công!',
                        timer: 1500,
                        showConfirmButton: false
                    }).then(() => {
                        window.location.href = "${ProductUrl}";
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
<script>
  $(document).ready(function () {
	  $('#lTable').DataTable({
		  language: {
		    lengthMenu: "Hiển thị _MENU_ sản phẩm mỗi trang",
		    zeroRecords: "Không tìm thấy sản phẩm nào",
		    info: "Hiển thị _START_ đến _END_ của _TOTAL_ sản phẩm",
		    infoEmpty: "Không có dữ liệu",
		    infoFiltered: "(lọc từ tổng cộng _MAX_ sản phẩm)",
		    search: "Tìm kiếm:",
		    paginate: {
		      first: "Đầu",
		      last: "Cuối",
		      next: "Sau",
		      previous: "Trước"
		    }
		  }
		});
  });
</script>
</body>
</html>