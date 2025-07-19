<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="APIurl" value="/api-admin-slide" />
<c:url var="SlideURL" value="/admin/slide" />

<html>
<head>
<title>Chỉnh sửa Slide</title>
</head>
<body>
	<main class="app-main">
	<div class="main-content">
		<div class="main-content-inner">
			<div class="page-content">
				<div class="row">
					<form id="formSubmit" enctype="multipart/form-data"
						accept-charset="UTF-8">
						<div class="row">
							<div class="col-xs-12">

								<div class="card-header">
									<h3 class="card-title">
										<strong class="text-uppercase text-danger"> <c:choose>
												<c:when test="${empty model.id}">
        Thêm slide
    </c:when>
												<c:otherwise>
       	Cập nhật slide
    </c:otherwise>
											</c:choose>

										</strong>
									</h3>
									<div class="card-tools">
										<a class="btn btn-sm btn-danger" href="${SlideURL }"><i
											class="fas fa-trash"></i> Quay lại</a>
									</div>
								</div>
							</div>
							<div class="col-xs-12 col-md-8 card-body">
								<c:if test="${not empty messageResponse}">
									<div class="alert alert-${alert}">${messageResponse}</div>
								</c:if>
									<div class="form-group">
										<label class="col control-label no-padding-right">Tên
											slide</label>
										<div class="col">
											<input type="text" class="form-control" id="caption" name="caption"
												value="${model.caption}" />
										</div>
									</div>

														<div class="form-group">
									<label class="col control-label no-padding-right">Hình đại diện</label> 
									<div class="col">
									<input
										 type="file" class="form-control" id="img" name="img" />
										</div>
								</div>
									<input type="hidden" value="${model.id}" id="id" name="id" />
							</div>
							<div class="col-xs-12 col-md-4 card-body">
																	<div class="form-group">
										<label class="col control-label no-padding-right">Trạng
											thái</label> <input type="hidden" id="status"
											value="${pageitem.page_status }">
										<div class="col">
											<select class="form-control" name="status">
												<option value="1" label="Xuất bản" />
												<option value="2" label="Chưa xuất bản" />

											</select>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div class="col-xs-12  card-body">
							<div class="form-group">
								<div class="col-sm-12">
									<c:if test="${not empty model.id}">
										<input type="button"
											class="btn btn-white btn-warning btn-bold" value="Cập nhật "
											id="btnAddOrUpdateTopic" />
									</c:if>
									<c:if test="${empty model.id}">
										<input type="button"
											class="btn btn-white btn-warning btn-bold" value="Thêm"
											id="btnAddOrUpdateTopic" />
									</c:if>
								</div>
							</div>
						</div>
				
				</div>
				
			</div>
		
		</div>
	</main>
	<script>
				$('#btnAddOrUpdateTopic').click(function(e) {
			e.preventDefault();
			var formDT = new FormData();

			var formData = $('#formSubmit').serializeArray();
			$.each(formData, function(i, v) {
				formDT.append(v.name,v.value);
				console.log(v.name + ": " + v.value);
			});
			var inputfile=$('#img')[0].files[0];
	        console.log(inputfile);
	        if(inputfile){
	        	formDT.append("img",inputfile)
	        }
			var id = $('#id').val();
			if (id == "") {
				addSlide(formDT);
			} else {
				updateSlide(formDT);
			}
		});

		function addSlide(data) {
			$.ajax({
						url : '${APIurl}',
						type : 'POST',
			              data: data,
			              processData: false,  // Phải set là false để FormData hoạt động đúng
			              contentType: false,  // Phải set là false để FormData hoạt động đúng,
			              dataType: 'json',
						success : function(result) {
						     if (result.status === 'success') {
				                    Swal.fire({
				                        icon: 'success',
				                        title: 'Thao tác thành công!',
				                        timer: 1500,
				                        showConfirmButton: false
				                    }).then(() => {
				                        window.location.href = "${SlideURL}?type=edit&id=" + encodeURIComponent(result.data.id);
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
		function updateSlide(data) {
			$.ajax({
						url : '${APIurl}',
						type : 'PUT',
			            data: data,
			            processData: false,  // Phải set là false để FormData hoạt động đúng
			            contentType: false,  // Phải set là false để FormData hoạt động đúng,
						success : function(result) {
						     if (result.status === 'success') {
				                    Swal.fire({
				                        icon: 'success',
				                        title: 'Cập nhật topic thành công!',
				                        timer: 1500,
				                        showConfirmButton: false
				                    }).then(() => {
				                        window.location.href = "${TopicURL}?type=edit&id=" + encodeURIComponent(result.data.id);
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
