<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="APIurl" value="/api-admin-page" />
<c:url var="PageURL" value="/admin/page" />
<html>
<head>
<meta charset="UTF-8">
<title>Chỉnh sửa trang đơn</title>
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
									<strong class="text-uppercase text-danger">
									<c:choose>
    <c:when test="${empty model.id}">
        Thêm trang đơn
    </c:when>    
    <c:otherwise>
       	Cập nhật trang đơn
    </c:otherwise>
</c:choose>
										
									</strong>
								</h3>
								<div class="card-tools">
									 <a class="btn btn-sm btn-danger"
										href="${PageURL }"><i class="fas fa-trash"></i> Quay lại</a>
								</div>
							</div>
						</div>
						<div class="col-xs-12 col-md-8 card-body">
							<c:if test="${not empty messageResponse}">
								<div class="alert alert-${alert}">${messageResponse}</div>
							</c:if>
							
								<div class="form-group">
									<label class="col control-label no-padding-right">Tiêu
										đề</label>
									<div class="col">
										<input type="text" class="form-control" id="title"
											name="title" value="${model.title}" />
									</div>
								</div>
								<div class="form-group">
									<label class=" col control-label no-padding-right">Slug</label>
									<div class="col">
										<input type="text" class="form-control" id="slug" name="slug"
											value="${model.slug}" />
									</div>
								</div>
								<div class="form-group">
									<label class="col control-label no-padding-right">Mô
										tả ngắn</label>
									<div class="col">
										<input type="text" class="form-control" id="shortDescription"
											name="shortDescription" value="${model.shortDescription}" />
									</div>
								</div>
								<div class="form-group">
									<label class="col control-label no-padding-right">Nội
										dung</label>
									<div class="col">
										<textarea rows="" cols="" id="content" name="content"
											style="width: 820px; height: 175px">${model.content}</textarea>
									</div>
								</div>
								<input type="hidden" value="${model.id}" id="id" name="id" />
							
						</div>
						<div class="col-xs-12 col-md-4 card-body">
								<div class="form-group">
									<label class="col control-label no-padding-right">Chủ đề</label>
										<div class="col">
										<select class="form-control" id="topicCode" name="topicCode">
									
											<option value="">Chọn loại bài viết</option>
											<c:if test="${empty model.topicCode}">
												<c:forEach var="item" items="${topics}">
													<option value="${item.slug}">${item.name}</option>
												</c:forEach>
											</c:if>
											<c:if test="${not empty model.topicCode}">
												<c:forEach var="item" items="${topics}">
													<option value="${item.slug}"
														<c:if test="${item.slug == model.topicCode }">selected = "selected"</c:if>>
														${item.name}</option>
												</c:forEach>
											</c:if>
										</select>
										</div>
								</div>
								<div class="form-group">
									<label class="col control-label no-padding-right">Trạng thái</label> <input type="hidden" id="status"
										value="${pageitem.page_status }">
										<div class="col">
									<select class="form-control" name="status">
										<option value="1" label="Xuất bản"/>
										<option value="2" label="Chưa xuất bản" />
									
									</select>
										</div>
								</div>
								<div class="form-group">
								<div class="col">
									<label class="control-label no-padding-right" >Hình đại diện</label> <input type="file"
										class="form-control-file" accept="image/*" id="thumbnail" name="imathumbnailge" />
								</div>
								</div>
							</div>
							<div class="col-xs-12  card-body">
								<div class="form-group">
									<div class="col-sm-12">
										<c:if test="${not empty model.id}">
											<input type="button"
												class="btn btn-white btn-warning btn-bold"
												value="Cập nhật " id="btnAddOrUpdatePage" />
										</c:if>
										<c:if test="${empty model.id}">
											<input type="button"
												class="btn btn-white btn-warning btn-bold"
												value="Thêm" id="btnAddOrUpdatePage" />
										</c:if>
									</div>
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

		$('#btnAddOrUpdatePage').click(function(e) {
			e.preventDefault();

			var formDT = new FormData();

			var formData = $('#formSubmit').serializeArray();
			$.each(formData, function(i, v) {
				formDT.append(v.name, v.value);
				console.log(v.name + ": " + v.value);
			});

			var inputfile = $('#thumbnail')[0].files[0];
			console.log(inputfile);
			if (inputfile) {
				formDT.append("thumbnail", inputfile)
			}
			formDT.append("content", editor.getData());
			var id = $('#id').val();
			if (id == "") {
				addNew(formDT);
				console.log(JSON.stringify(formData));
			} else {
				updateNew(formDT);
				console.log(JSON.stringify(formData));
			}
		});

		function addNew(data) {
			$
					.ajax({
						url : '${APIurl}',
						type : 'POST',
						data : data,
						processData : false, // Phải set là false để FormData hoạt động đúng
						contentType : false, // Phải set là false để FormData hoạt động đúng,
						dataType : 'json',
						success : function(result) {
							if (result.status === 'success') {
			                    Swal.fire({
			                        icon: 'success',
			                        title: 'Cập nhật topic thành công!',
			                        timer: 1500,
			                        showConfirmButton: false
			                    }).then(() => {
			                        window.location.href = "${PageURL}?type=edit&id=" + encodeURIComponent(result.data.id);
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
		function updateNew(data) {
			$
					.ajax({
						url : '${APIurl}',
						type : 'PUT',
						data : data,
						processData : false, // Phải set là false để FormData hoạt động đúng
						contentType : false, // Phải set là false để FormData hoạt động đúng,
						success : function(result) {
							if (result.status === 'success') {
			                    Swal.fire({
			                        icon: 'success',
			                        title: 'Cập nhật topic thành công!',
			                        timer: 1500,
			                        showConfirmButton: false
			                    }).then(() => {
			                        window.location.href = "${PageURL}?type=edit&id=" + encodeURIComponent(result.data.id);
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
