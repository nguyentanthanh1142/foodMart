<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<c:url var="APIurl" value="/api-admin-menu" />
<c:url var="MenuURL" value="/admin/menu" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm tùy chọn</title>
</head>
<body>
	<c:url var="save" value="/quan-tri/web/menu/save" />
	<c:url var="list" value="/quan-tri/web/menu" />
	<form action="${save}" enctype="multipart/form-data" method="POST" id="formSubmit">
		<div class="content-wrapper pt-3">
			<!-- Content Header (Page header) -->
			<!-- Main content -->
			<section class="content">
				<!-- Default box -->
				<div class="card">
					<div class="card-header">
						<h3 class="card-title">
							<strong class="text-uppercase text-danger">Thêm tùy chọn</strong>
						</h3>
						<div class="card-tools">
							<button type="submit"
								onclick="return confirm('Bạn có chắc chắn thực hiện không?');"
								class="btn btn-sm btn-success" id ="btnAddOrUpdateMenu">
								<i class="fas fa-save"></i> Lưu
							</button>
							<a class="btn btn-sm btn-danger" href="${MenuURL}"> Quay về danh
								sách</a>
						</div>
					</div>
					<div class="card-body">
						<div class="row">
							<div class="col-md-9">
								<div class="form-group">
									<label>Tên menu</label> <input class="form-control" type="text"
										name="name" required="required"
										value="${model.name }" />
									<c:if test="${ not empty msgName}">
										<span class="form-error">${msgName}</span>
									</c:if>
								</div>
								<div class="form-group">
									<label for="">Slug</label> <input class="form-control"
										type="text" placeholder="Nhập tên" name="slug"
										required="required"
										value="${ model.slug }" />
									<c:if test="${ not empty msgSlug}">
										<span class="form-error">${msgSlug}</span>
									</c:if>
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label>Trạng thái</label> <select name="status"
										class="form-control">
										<option value="2" label="Chưa xuất bản"></option>
										<option value="1" label="Xuất bản" <c:if test="${model.status ==1}">
		selected
									</c:if>> </option>
									</select>
								</div>
								<div class="form-group">
									<label>Cấp cha</label> <select name="parent_id" id="parent_id"
										class="form-control">
										<option value="0">...</option>
										<c:forEach var="item" items="${ listmenu }">
											<option value="${item.id }">${item.name }</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group">
									<label>Thứ tự</label> <select name="orders"
										class="form-control" id="orders">
									</select>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- /.card -->
			</section>
			<!-- /.content -->
		</div>
	</form>
	
	<script>
		$('#btnAddOrUpdateMenu').click(function(e) {
			e.preventDefault();

			var data = {};

			var formData = $('#formSubmit').serializeArray();
			$.each(formData, function(i, v) {
				data["" + v.name + ""] = v.value;
				console.log(v.name + ": " + v.value);
			});
			var id = $('#id').val();
			console.log(data)
				addMenu(data);
		});

		function addMenu(data) {
			$.ajax({
						url : '${APIurl}',
						type : 'POST',
						data : JSON.stringify(data),
						contentType : "application/json",
						dataType : 'json',
						success : function(result) {
							window.location.href = "${CouponUrl}?type=edit&id="
									+ result.id + "&message=insert_success";
						},
						error : function(error) {
							window.location.href = "${CouponUrl}?type=list&maxPageItem=2&page=1&message=error_system";
						}
					});
		}
		function updateCoupon(data) {
			$
					.ajax({
						url : '${APIurl}',
						type : 'PUT',
						contentType : 'application/json',
						data : JSON.stringify(data),
						dataType : 'json',
						success : function(result) {
							window.location.href = "${CouponUrl}?type=edit&id="
									+ result.id + "&message=update_success";
						},
						error : function(error) {
							window.location.href = "${CouponUrl}?type=list&maxPageItem=2&page=1&message=error_system";
						}
					});
		}
	</script>
	
	<script>
	$(document).ready(function () {
		$select = $('#orders');
		$.ajax({
		 	url: "${APIurl}?parent_id=0" ,

 	        method: "GET",
 	        dataType: 'json',
 	        success:function(data) {
 	        	$("#orders").html('<option value="0">---</options>');
 	            $.each(data, function(key, val) {
	 	            	$("#orders").append('<option value="' + val.orders + '">' +"Sếp sau " + val.name + '</option>');	 	            		

 	            })
 	        },
 	        error: function(){
 	            console.log("sai2");
 	        }
    	});
		
		
		 $('#parent_id').change(()=>{    
		    	$("#orders").empty();
				var pi = $("#parent_id").val();
		    	 if($("#parent_id").val()!=""){
		 	    	$.ajax({
		    		 	url: "${APIurl}?parent_id=" + pi,
			
			 	        method: "GET",
			 	        dataType: 'json',
			 	        success:function(data) {
			 	        	$("#orders").html('<option value="0">---</options>');
			 	            $.each(data, function(key, val) {
				 	            	$("#orders").append('<option value="' + val.orders + '">' +"Sếp sau " + val.name + '</option>');	 	            		

			 	            })
			 	        },
			 	        error: function(){
			 	            console.log("sai2");
			 	        }
			    	});
		    	 }

			});
	});
	</script>
</body>
</html>