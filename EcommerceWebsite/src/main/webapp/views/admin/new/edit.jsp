<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="APIurl" value="/api-admin-new" />
<c:url var="NewURL" value="/admin/new" />
<html>
<head>
<meta charset="UTF-8">
<title>Bài viết</title>
      <style>
    #preview {
      margin-top: 10px;
      max-width: 200px;
      max-height: 200px;
      display: none;
      border: 1px solid #ccc;
    }
  </style>
</head>
<body>
	<main class="app-main">
	<div class="main-content">
		<div class="main-content-inner">
			<div class="page-content container">
				<div class="row">
					<form id="formSubmit" enctype="multipart/form-data"
						accept-charset="UTF-8">
						<div class="row">
							<div class="col-xs-12">
								<div class="card-header">
									<h3 class="card-title">
										<strong class="text-uppercase text-danger"> <c:choose>
												<c:when test="${empty model.id}">
											        Thêm bài viết
											    </c:when>
												<c:otherwise>
											       	Cập nhật bài viết
											    </c:otherwise>
											</c:choose>
										</strong>
									</h3>
									<div class="card-tools">
										<button type="button" class="btn btn-sm btn-info btn-bold"
											id="btnAddOrUpdateNew">
											<i class="fas fa-save"></i> Lưu
										</button>
										<a class="btn btn-sm btn-danger" href="${NewURL }"><i
											class="fas fa-trash"></i> Quay lại</a>
									</div>
								</div>
							</div>
							<div class="col-xs-12 col-md-8 card-body">
								<div class="form-group">
									<label class="control-label no-padding-right">Tiêu
										đề</label>
									<div>
										<input type="text" class="form-control" id="title"
											name="title" value="${model.title}" required/>
									</div>
								</div>
								<div class="form-group">
									<label class=" control-label no-padding-right">Slug</label>
									<div class="input-group mb-3 form-group">
										<input type="text" class="form-control" id="slug" name="slug" value="${model.slug}" required>
										<div class="input-group-append">
											<button type="button" class="input-group-text" id="btnGenerateSlug"">Tạo mã</button>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label no-padding-right">Mô
										tả ngắn</label>
									<div>
										<input type="text" class="form-control" id="shortDescription"
											name="shortDescription" value="${model.shortDescription}"  required/>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label no-padding-right">Nội
										dung</label>
									<div >
										<textarea rows="" cols="" id="content" name="content"
											style="width: 820px; height: 175px">${model.content}</textarea>
									</div>
								</div>
							</div>
							<div class="col-xs-12 col-md-4 card-body">
								<div class="form-group">
									<label class="control-label no-padding-right">Thể
										loại</label>
									<div>
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
									<label class="control-label no-padding-right">Hình ảnh</label>
									<div>
										<input type="file" class="form-control" id="thumbnail"
											name="thumbnail">
									</div>
								</div>
								<div class="form-group">
                            		 <img id="preview" src="${model.thumbnail}" alt="Ảnh xem trước" style="width: 100%; ${model.id != null?"":"display:none"}"/>
                            	 </div>
								<input type="hidden" value="${model.id}" id="id" name="id" />
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

		$('#btnAddOrUpdateNew').click(function(e) {
			e.preventDefault();

			var formDT = new FormData();
	        var form = document.getElementById('formSubmit');

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
			
		       if (form.checkValidity()) {
					if (id == "") {
						addNew(formDT);
						console.log(JSON.stringify(formData));
					} else {
						updateNew(formDT);
						console.log(JSON.stringify(formData));
					}
		          } else {
		            form.reportValidity(); // Hiển thị lỗi required nếu chưa đúng
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
				                        title: 'Thao tác thành công!',
				                        timer: 1500,
				                        showConfirmButton: false
				                    }).then(() => {
				                        window.location.href = "${NewURL}?type=edit&id=" + encodeURIComponent(result.data.id);
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
						processData : false,
						contentType : false, 
						success : function(result) {
						     if (result.status === 'success') {
				                    Swal.fire({
				                        icon: 'success',
				                        title: 'Cập nhật topic thành công!',
				                        timer: 1500,
				                        showConfirmButton: false
				                    }).then(() => {
				                        window.location.href = "${NewURL}?type=edit&id=" + encodeURIComponent(result.data.id);
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
	<script>
		const input = document.getElementById('thumbnail');
		const preview = document.getElementById('preview');
		//preview.style.display = 'none';
		input.addEventListener('change', function() {
			const file = this.files[0];
			if (file) {
				const reader = new FileReader();
				reader.onload = function(e) {
					preview.src = e.target.result;
					preview.style.display = 'block';
				};
				reader.readAsDataURL(file);
			} else {
				preview.src = '#';
				preview.style.display = 'none';
			}
		});
	</script>
		<script>
	  function generateSlug(str) {
	    return str.toLowerCase()
	      .normalize('NFD')                     
	      .replace(/[\u0300-\u036f]/g, '')      
	      .replace(/[^a-z0-9\s-]/g, '')         
	      .replace(/\s+/g, '-')                 
	      .replace(/-+/g, '-')                 
	      .replace(/^-+|-+$/g, '');            
	  }
	
	  document.getElementById('btnGenerateSlug').addEventListener('click', function () {
	    const name = document.getElementById('title').value;
	    const slug = generateSlug(name);
	    document.getElementById('slug').value = slug;
	  });
	</script>
</body>
</html>
>
