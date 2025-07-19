<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="APIurl" value="/api-admin-category" />
<c:url var="CategoryUrl" value="/admin/category" />
<html>
<head>
<meta charset="UTF-8">
<title>Chỉnh sửa danh mục</title>
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
											        Thêm danh mục
											    </c:when>
												<c:otherwise>
											       	Cập nhật danh mục
											    </c:otherwise>
											</c:choose>
										</strong>
									</h3>
									<div class="card-tools">
										<button type="button" class="btn btn-sm btn-info btn-bold"
											id="btnAddOrUpdateNew">
											<i class="fas fa-save"></i> Lưu
										</button>
										<a class="btn btn-sm btn-danger" href="${CategoryUrl }"><i
											class="fas fa-trash"></i> Quay lại</a>
									</div>
								</div>
							</div>
							<div class="col-xs-12 col-md-8 card-body">
								<div class="form-group">
									<label class=" control-label no-padding-right">Tên</label>
									<div>
										<input type="text" class="form-control" id="name" name="name"
											value="${model.name}" required/>
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
									<label class="control-label no-padding-right">Mô tả</label>
									<div>
										<input type="text" class="form-control" id="metadesc"
											name="metadesc" value="${model.metadesc}" required/>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label no-padding-right">Từ khóa</label>
									<div>
										<input type="text" class="form-control" id="metakey"
											name="metakey" value="${model.metakey}" />
									</div>
								</div>
							</div>
							<div class="col-xs-12 col-md-4 card-body">
								<div class="form-group">
									<label class="control-label no-padding-right">Cấp cha</label>
									<div>
										<select name="parentId" id="status" class="form-control">
											<option value="0">--------</option>
											<c:forEach items="${listCate}" var="item">
												<option value="${item.id}"
													<c:if test="${item.id == model.parentId}">selected</c:if>>${item.name }</option>
											</c:forEach>
										</select>
									</div>
								</div>
								
								<div class="form-group">
									<label class="control-label no-padding-right">Trạng
										thái</label>
									<div>
										<select name="status" id="status" class="form-control">
											<option value="1">Công khai</option>
											<option value="0" ${model.status==0?"selected":"" }>Không
												công khai</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label no-padding-right">Nổi bật</label>
									<div>
										<select name="hotCate" id="hotCate" class="form-control">
											<option value="false">Không hiển thị</option>
											<option value="true" ${model.hotCate == true?"selected":"" }>Hiển thị</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label no-padding-right">Hiện thị sản phẩm ở trang chủ</label>
									<div>
										<select name="showProductsHomePage" id="showProductsHomePage" class="form-control">
											<option value="false">Không hiển thị</option>
											<option value="true" ${model.showProductsHomePage == true?"selected":"" }>Hiển thị</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label no-padding-right">Hình ảnh</label>
									<div>
										<input type="file" class="form-control" id="img" name="img">
									</div>
								</div>
 <div class="form-group">
                            		 <img id="preview" src="${model.img}"" alt="Ảnh xem trước" style="width: 100%; ${model.id != null?"":"display:none"}"/>
                             </div>
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
		$('#btnAddOrUpdateNew').click(function(e) {
			e.preventDefault();
			var formDT = new FormData();
			var form = document.getElementById('formSubmit');
			var formData = $('#formSubmit').serializeArray();
			$.each(formData, function(i, v) {
				formDT.append(v.name, v.value);
				console.log(v.name + ": " + v.value);
			});
			var inputfile = $('#img')[0].files[0];
			if (inputfile) {
				formDT.append("img", inputfile)
			}
			var id = $('#id').val();
			if (form.checkValidity()) {
				if (id == "") {
					addNew(formDT);
				} else {
					updateNew(formDT);
				}
	          } else {
	            form.reportValidity(); 
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
				                        window.location.href = "${CategoryUrl}?type=edit&id=" + encodeURIComponent(result.data.id);
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
				                        window.location.href = "${CategoryUrl}?type=edit&id=" + encodeURIComponent(result.data.id);
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
    const name = document.getElementById('name').value;
    const slug = generateSlug(name);
    document.getElementById('slug').value = slug;
  });
</script>
<script>
    const input = document.getElementById('img');
    const preview = document.getElementById('preview');
    //preview.style.display = 'none';
    input.addEventListener('change', function () {
      const file = this.files[0];
      if (file) {
        const reader = new FileReader();
        reader.onload = function (e) {
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
</body>
</html>
>
