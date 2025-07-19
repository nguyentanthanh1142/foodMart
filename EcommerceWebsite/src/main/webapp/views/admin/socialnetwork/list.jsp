<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nhà mạng xã hội</title>
</head>
<body>
	<c:url var="get" value="/quan-tri/web/mang-xa-hoi/edit" />
	<c:url var="deltrash" value="/quan-tri/web/mang-xa-hoi/deltrash" />
	<c:url var="trash" value="/quan-tri/web/mang-xa-hoi/thung-rac" />
	<c:url var="onoff" value="/quan-tri/web/mang-xa-hoi/status" />
	<c:url var="APIurl" value="/api-admin-socialnetwork"/>
	<c:url var ="socialURL" value="/admin/socialnetwork"/>
	<div class="content-wrapper pt-3">
		<!-- Content Header (Page header) -->
		<!-- Main content -->
		<section class="content">
			<!-- Default box -->
			<div class="card">
				<div class="card-header">
					<h3 class="card-title">
						<strong class="text-uppercase text-danger">Danh sách mạng
							xã hội</strong>
					</h3>
					<div class="card-tools">
						<a class="btn btn-sm btn-success"  href="${socialURL }"><i
							class="fas fa-plus"></i> Thêm</a> <a class="btn btn-sm btn-danger" id="btnDelete"
							href="" ><i class="fas fa-trash"></i> Xóa</a>
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
					<table class="table table-bordered border-hover ">
						<thead>
							<tr>
								<th><input type="checkbox" id="checkAll"></th>
								<th style="width: 40px;" class="text-center">ID</th>
								<th style="width: 200px;">Hình ảnh</th>
								<th>Tên mạng xã hội</th>
								<th>Địa chỉ</th>
								<th style="width: 10rem;">Chức năng</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${model.listResult.size()>0}">
								<c:forEach var="item" items="${ model.listResult }">
									<tr>
									<td><input type="checkbox" id="checkbox_${item.id}" value="${item.id}"/></td>
										<td>${item.id}</td>
										<td><img ;"
											src="${pageContext.request.contextPath}/images/${item.img}"></td>
										<td>${item.name}</td>
										<td>${item.address}(<a href="${item.address}" target="_blank">Link</a>)</td>
										<td><c:choose>
												<c:when test="${item.status == 1}">
													<a href="${onoff}/${item.id }"
														onclick="return confirm('Bạn có chắc chắn thực hiện không?');"
														class="btn btn-sm btn-info"><i
														class="fas fa-toggle-on"></i></a>
												</c:when>
												<c:otherwise>
													<a href="${onoff}/${item.id }"
														onclick="return confirm('Bạn có chắc chắn thực hiện không?');"
														class="btn btn-sm btn-danger"><i
														class="fas fa-toggle-off"></i></a>
												</c:otherwise>
											</c:choose>
											<c:url var="editURL" value="/admin/socialnetwork">
																<c:param name="type" value="edit" />
																<c:param name="id" value="${item.id}" />
															</c:url>
											 <a href="${editURL}" class="btn btn-sm btn-success"><i
												class="far fa-edit"></i></a> <a href="${deltrash}/${item.id }"
											onclick="return confirm('Bạn có chắc chắn thực hiện không?');"
											class="btn btn-sm btn-danger"><i class="fas fa-trash"></i></a></td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
											</table>
				</div>
				<nav aria-label="Page navigation example">
					<ul class="pagination justify-content-center">
						<c:forEach var="item" begin="1" end="${paginateInfo.totalPage}"
							varStatus="loop">
							<c:if test="${(loop.index)==paginateInfo.currentPage}">
								<li class="page-item active"><a class="page-link"
									href="<c:url value="/quan-tri/web/mang-xa-hoi/${loop.index}"/>">${loop.index}</a></li>
							</c:if>
							<c:if test="${(loop.index) != paginateInfo.currentPage}">
								<li class="page-item "><a class="page-link"
									href="<c:url value="/quan-tri/web/mang-xa-hoi/${loop.index}"/>">${loop.index}</a></li>
							</c:if>
						</c:forEach>
					</ul>
				</nav>
			</div>
			<!-- /.card -->
		</section>
		<!-- /.content -->
	</div>
	
	<script>
	
	var totalPages = ${model.totalPage};
	var currentPage = ${model.page};
	var limit = 5;
	$(function () {
		window.pagObj = $('#pagination').twbsPagination({
			totalPages: totalPages,
			visiblePages: 10,
			startPage: currentPage,
			onPageClick: function (event, page) {
				if (currentPage != page) {
					$('#maxPageItem').val(limit);
					$('#page').val(page);
					$('#sortName').val('title');
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
		deleteNew(data);
	});
	
	function deleteNew(data){
		$.ajax({
			url: '${APIurl}',
			type: 'DELETE',
			contentType: 'application/json',
			data: JSON.stringify(data),
            success: function (result) {
                window.location.href = "${socialURL}?type=list&message=delete_success";
            },
            error: function (error) {
            	window.location.href = "${socialURL}?type=list&page=1&message=error_system";
            }
        });
	};
	</script>
</body>
</html>