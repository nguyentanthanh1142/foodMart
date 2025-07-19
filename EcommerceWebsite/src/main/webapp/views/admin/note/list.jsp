<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<c:url var="APIurl" value="/api-admin-note" />
<c:url var="noteURL" value="/admin/note" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nhật ký chỉnh sửa</title>
<style>
.pagination li.active a {
	background-color: #17a2b8 !important; /* Màu xanh đẹp */
	border-color: #17a2b8 !important;
	color: white !important;
}
</style>
</head>
<body>
	<main class="app-main">
	<div class="content-wrapper pt-3">
		<!-- Content Header (Page header) -->

		<!-- Main content -->
		<section class="content">

			<!-- Default box -->
			<div class="card">
				<div class="card-header">
					<h3 class="card-title">
						<strong class="text-uppercase text-danger">Nhật ký chỉnh
							sửa</strong>
					</h3>

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
					<div class="row">
						<div class="col" style="margin-bottom: 1rem;">
							<form id = "formSubmit" class="" action="${noteURL}" method="GET">
								Từ: <input class="" type="date" name="to" value="${to }">
								đến: <input class="" type="date" name="from" value="${from }">
	<input type="hidden" value="1" id="page" name="page" /> <input
												type="hidden" value="5" id="maxPageItem" name="maxPageItem" />
											<input type="hidden" value="" id="sortName" name="sortName" />
											<input type="hidden" value="" id="sortBy" name="sortBy" /> <input
												type="hidden" value="" id="type" name="type" />
												<input type="hidden" value="search" id="action" name="action" />
								<button type="submit" class="btn btn-info">Tìm</button>
							</form>
						</div>
						<div class="col text-end">

							<button id="btnDelete"
								onclick="return confirm('Bạn có chắc chắn thực hiện không?');"
								type="button"
								class="dt-button buttons-html5 btn btn-white btn-danger btn-bold"
								data-toggle="tooltip" title='Xóa bài viết'>
								<span> <i class="fas fa-trash bigger-110 pink"></i>
								</span>
							</button>
						</div>
					</div>
					<table class="table table-bordered border-hover pt-3	">
						<thead>
							<tr>
								<th><input type="checkbox" id="checkAll"></th>
								<th style="width: 20px;" class="text-center">ID</th>
								<th>Nội dung</th>
								<th>Người chỉnh sửa</th>
								<th>Thời gian</th>
								<th style="width: 10rem;">Chức năng</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${ model.listResult }">
								<tr>
									<td><input type="checkbox" id="checkbox_${item.id}"
										value="${item.id}"></td>
									<td>${item.id}</td>
									<td>${item.content}</td>
									<td>${item.createdBy}</td>
									<td>${item.createdAt}</td>
									<td></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<ul class="pagination justify-content-center mt-3" id="pagination"></ul>
					<input type="hidden" value="" id="page" name="page" /> <input
						type="hidden" value="" id="maxPageItem" name="maxPageItem" /> <input
						type="hidden" value="" id="sortName" name="sortName" /> <input
						type="hidden" value="" id="sortBy" name="sortBy" /> <input
						type="hidden" value="" id="type" name="type" />
				</div>
			</div>

			<!-- /.card -->
		</section>
		<!-- /.content -->
	</div>
	</main>
	<script>
		var totalPages = ${model.totalPage};
		var currentPage = ${model.page};
		console.log(totalPages);
		console.log(currentPage);
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
			deleteNote(data);
		});
		function deleteNote(data){
			$.ajax({
				url: '${APIurl}',
				type: 'DELETE',
				contentType: 'application/json',
				data: JSON.stringify(data),
	            success: function(result) {
				     if (result.status === 'success') {
		                    Swal.fire({
		                        icon: 'success',
		                        title: 'Xóa thành công!',
		                        timer: 1500,
		                        showConfirmButton: false
		                    }).then(() => {
		                        window.location.href = "${Note}";
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