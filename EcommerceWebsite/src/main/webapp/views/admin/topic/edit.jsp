<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="APIurl" value="/api-admin-topic" />
<c:url var="TopicURL" value="/admin/topic" />
<html>
<head>
<title>Chỉnh sửa chủ đề</title>
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
        Thêm Chủ dề
    </c:when>
												<c:otherwise>
       	Cập nhật chủ đề
    </c:otherwise>
											</c:choose>

										</strong>
									</h3>
									<div class="card-tools">
										<a class="btn btn-sm btn-danger" href="${TopicURL }"><i
											class="fas fa-trash"></i> Quay lại</a>
									</div>
								</div>
							</div>
							<div class="col-xs-12 col-md-8 card-body">
								<c:if test="${not empty messageResponse}">
									<div class="alert alert-${alert}">${messageResponse}</div>
								</c:if>
								<form id="formSubmit" enctype="multipart/form-data">
									<div class="form-group">
										<label class="col control-label no-padding-right">Tên
											chủ đề</label>
										<div class="col">
											<input type="text" class="form-control" id="name" name="name" required
												value="${model.name}" />
										</div>
									</div>

									<div class="form-group">
										<label class="col control-label no-padding-right">Slug</label>
										<div class="col">
											<input type="text" class="form-control" id="slug" name="slug" required
												value="${model.slug}" />
										</div>
									</div>

									<div class="form-group">
										<label class="col control-label no-padding-right">Mô
											tả</label>
										<div class="col">
											<input type="text" class="form-control" id="metadesc" required
												name="metadesc" value="${model.metadesc}" />
										</div>
									</div>

									<div class="form-group">
										<label class="col control-label no-padding-right">Từ
											khóa</label>
										<div class="col">
											<input type="text" class="form-control" id="metakey" required
												name="metakey" value="${model.metakey}" />
										</div>
									</div>
									<input type="hidden" value="${model.id}" id="id" name="id" />
								</form>
							</div>
							<div class="col-xs-12 col-md-4 card-body">
								<div class="form-group">
									<label class="col control-label no-padding-right">Hiện
										thị ở footer web(0-1)</label>
									<div class="col">
										<select class="form-control" name="showFooter">
											<option value="0" label="Không hiển thị" />
											<option value="1" ${model.showFooter == 1?"selected":"" } label="Hiển thị" />

										</select>
									</div>
									<div class="form-group">
										<label class="col control-label no-padding-right">Trạng
											thái</label> <input type="hidden" id="status"
											value="${pageitem.page_status }">
										<div class="col">
											<select class="form-control" name="status">
												<option value="1" label="Xuất bản" />
												<option value="0" ${model.status == 0?"selected":"" } label="Chưa xuất bản" />

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
						</form>
				</div>
			</div>
		</div>
	</div>

	</main>
	<script>
		var editor = '';
		$(document)
				.ready(
						function() {
							editor = CKEDITOR
									.replace(
											'content',
											{
												filebrowserBrowseUrl : '${pageContext.request.contextPath}/libraries/ckfinder/ckfinder.html',
												filebrowserImageBrowseUrl : '${pageContext.request.contextPath}/libraries/ckfinder/ckfinder.html?type=Images',
												filebrowserFlashBrowseUrl : '${pageContext.request.contextPath}/libraries/ckfinder/ckfinder.html?type=Flash',
												filebrowserUploadUrl : '${pageContext.request.contextPath}/libraries/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files',
												filebrowserImageUploadUrl : '${pageContext.request.contextPath}/libraries/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images',
												filebrowserFlashUploadUrl : '${pageContext.request.contextPath}/libraries/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash',
												filebrowserWindowWidth : '1000',
												filebrowserWindowHeight : '700'
											});
						});

		$('#btnAddOrUpdateTopic').click(function(e) {
			e.preventDefault();

			var data={};
			var form = document.getElementById('formSubmit');
			var formData = $('#formSubmit').serializeArray();
			$.each(formData, function(i, v) {
				data[""+v.name+""] = v.value;
				console.log(v.name + ": " + v.value);
			});
			var id = $('#id').val();
			if (form.checkValidity()) {
				if (id == "") {
					addTopic(formDT);
				} else {
					updateTopic(formDT);
				}
	          } else {
	            form.reportValidity(); 
	          }
		});

		function addTopic(data) {
			$.ajax({
						url : '${APIurl}',
						type : 'POST',
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
		function updateTopic(data) {
			$.ajax({
						url : '${APIurl}',
						type : 'PUT',
						contentType : 'application/json',
						data : JSON.stringify(data),
						dataType : 'json',
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
>
