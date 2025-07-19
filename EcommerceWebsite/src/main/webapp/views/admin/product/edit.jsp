<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<c:url var="APIurl" value="/api-admin-product"/>
<c:url var ="ProductURl" value="/admin/product"/>
<html>
<head>
<meta charset="UTF-8">
    <title>Cập nhật sản phẩm</title>
      <style>
    #preview {
      margin-top: 10px;
      max-width: 200px;
      max-height: 200px;
      display: none;
      border: 1px solid #ccc;
    }
  .preview-item {
    position: relative;
    margin: 5px;
  }

  .preview-item img {
    max-width: 150px;
    border: 1px solid #ddd;
    border-radius: 5px;
  }

  .remove-btn {
    position: absolute;
    top: 2px;
    right: 2px;
    background-color: rgba(0, 0, 0, 0.6);
    color: white;
    border: none;
    border-radius: 50%;
    width: 22px;
    height: 22px;
    text-align: center;
    cursor: pointer;
    font-size: 14px;
  }
  </style>
  
</head>
<body>
	<main class="app-main">
	<div class="main-content">
		<div class="main-content-inner">
			<div class="page-content container">
				<div class="row">
                       <form id="formSubmit" enctype="multipart/form-data" accept-charset="UTF-8">
						<div class="row">
							<div class="col-xs-12">

								<div class="card-header">
									<h3 class="card-title">
										<strong class="text-uppercase text-danger"> <c:choose>
												<c:when test="${empty model.id}">
											        Thêm sản phẩm
											    </c:when>
												<c:otherwise>
											       	Cập nhật sản phẩm
											    </c:otherwise>
											</c:choose>
										</strong>
									</h3>
									<div class="card-tools">
                                        <button  type="button" class="btn btn-sm btn-info btn-bold"  id="btnAddOrUpdateNew"/><i class="fas fa-save"></i> Lưu</button >
										<a class="btn btn-sm btn-danger" href="${ProductURl }"><i
											class="fas fa-trash"></i> Quay lại</a>
									</div>
								</div>
							</div>
               			 <div class="col-xs-12 col-md-8 card-body">
                                                        <div class="form-group">
                                <label class="control-label no-padding-right">Tên sản phẩm</label>
                                <div>
                                    <input type="text" class="form-control" id="name" name="name" value="${model.name}" required />
                                </div>
                            </div>
                                                        <div class="form-group">
                                <label class="control-label no-padding-right">Slug</label>
                                <div ">
                                    <input type="text" class="form-control" id="slug" name="slug" value="${model.slug}" required />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label no-padding-right">Mô tả ngắn</label>
                                <div>
                                    <input type="text" class="form-control" id="shortdesc" name="shortdesc" value="${model.shortdesc}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class=" control-label no-padding-right">Chi tiết sản phẩm</label>
                                <div>                                 
                                    <textarea rows="" cols="" id="detail" name="detail" style="width: 820px;height: 175px">${model.detail}</textarea>
                                </div>
                            </div>
                            </div>
                            <div class="col-xs-12 col-md-4 card-body"> 
                            <div class="form-group">
                                <label class="control-label no-padding-right">Trạng thái</label>
                                <div >
                                    <select class="form-control" id="status" name="status">
                                    <option value="1">Công khai</option>
	                                <option value="0" ${model.status==0?"selected":"" }>Không công khai</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label no-padding-right">Loại sản phẩm</label>
                                <div >
                                    <select class="form-control" id="catid" name="catid">
                                    <option value="0">Chọn loại sản phẩm</option>
                                      <c:if test="${empty model.catid}">
	                                      <c:forEach var="item" items="${categories}">
	                                      		 <option value="${item.id}">${item.name}</option>
	                                      </c:forEach>
                                      </c:if>
                                     <c:if test="${not empty model.catid}">
	                                      <c:forEach var="item" items="${categories}">
	                                      		 <option value="${item.id}" <c:if test="${item.id == model.catid }">selected = "selected"</c:if>>
	                                      		 ${item.name}</option>
	                                      </c:forEach>
                                      </c:if>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label no-padding-right">Giá gốc</label>
                                <div>
                                    <input type="text" class="form-control" id="price" name="price" value="${model.price}" required />
                                </div>
                            <div class="form-group">
                                <label class="control-label no-padding-right">Giá bán</label>
                                <div>
                                    <input type="text" class="form-control" id="pricesale" name="pricesale" value="${model.pricesale} " required />
                                </div>
                            </div>
                             <div class="form-group">
                                <label class="control-label no-padding-right">Số lượng</label>
                                <div>
                                    <input type="text" class="form-control" id="available" name="available" value="${model.available}" required />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label no-padding-right">Hình ảnh</label>
                                <div >                                 
                             		  <input type="file" class="form-control" id="productimg" name="productimg" accept="image/*">
                                </div>
                            </div>
                             <br>
                             <div class="form-group">
                            		 <img id="preview" src="${model.productimg}"" alt="Ảnh xem trước" style="width: 100%; ${model.id != null?"":"display:none"}"/>
                             </div>
                            </div>
                            <input type="hidden" value="${model.id}" id="id" name="id"/>
                            
                             <label>Chọn ảnh phụ (tối đa 3 ảnh):</label><br>
							  	<input type="file" id="imageUpload" name="images" multiple accept="image/*">
						 	 <div id="multiPreview" style="display: flex; flex-wrap: wrap; margin-top: 10px;"></div>

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
	$(document).ready(function(){
	editor = CKEDITOR.replace(
			'detail',
			{
				height: 400,
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
	
	const imageInput = document.getElementById('imageUpload');
	const multiPreviewContainer = document.getElementById('multiPreview');
	let selectedFiles = [];

	imageInput.addEventListener('change', function(event) {
	    const files = Array.from(event.target.files);
	    
	    files.forEach(file => {
	        if (!file.type.startsWith('image/')) return;
	        selectedFiles.push(file);
	    });

	    renderPreview();
	    imageInput.value = ''; // reset lại input
	});

	function renderPreview() {
	    multiPreviewContainer.innerHTML = ''; // Xóa các ảnh preview cũ
	    selectedFiles.forEach((file, index) => {
	        const reader = new FileReader();
	        reader.onload = function(e) {
	            const wrapper = document.createElement('div');
	            wrapper.classList.add('preview-item');

	            const img = document.createElement('img');
	            img.src = e.target.result;

	            const removeBtn = document.createElement('button');
	            removeBtn.classList.add('remove-btn');
	            removeBtn.innerHTML = '&times;';
	            removeBtn.addEventListener('click', () => {
	                selectedFiles.splice(index, 1);
	                renderPreview(); // Vẽ lại sau khi xóa
	            });

	            wrapper.appendChild(img);
	            wrapper.appendChild(removeBtn);
	            multiPreviewContainer.appendChild(wrapper);
	        };
	        reader.readAsDataURL(file);
	    });
	}
	
	
	
	
	
    $('#btnAddOrUpdateNew').click(function (e) {
        e.preventDefault();
        var form = document.getElementById('formSubmit');
        var formDT = new FormData();
        var formData = $('#formSubmit').serializeArray();
        $.each(formData, function (i, v) {
        	formDT.append(v.name,v.value);
            console.log(v.name + ": " + v.value);
        });
        
        var inputfile=$('#productimg')[0].files[0];
        console.log(inputfile);
        if(inputfile){
        	
        	formDT.append("productimg",inputfile)
        }
        
        selectedFiles.forEach(file => {
        	console.log(file)
            formDT.append("images", file); // images[] nếu bên BE nhận dạng mảng
        });
        
        formDT.append("detail",editor.getData());
        console.log(JSON.stringify(formData));
		var id = $('#id').val();
		
	       if (form.checkValidity()) {
				if (id == "") {
					addPro(formDT);
					console.log(JSON.stringify(formData));
				} else {
					updatePro(formDT);
					console.log(JSON.stringify(formData));
				}
	          } else {
	            form.reportValidity(); // Hiển thị lỗi required nếu chưa đúng
	          }
    });
        
        
      
    function addPro(data) {
    	  $.ajax({
              url: '${APIurl}',
              type: 'POST',
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
		                        window.location.href = "${ProductURl}?type=edit&id=" + encodeURIComponent(result.data.id);
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
    function updatePro(data) {
        $.ajax({
        	url: '${APIurl}',
            type: 'PUT',
            data: data,
            processData: false,  // Phải set là false để FormData hoạt động đúng
            contentType: false,  // Phải set là false để FormData hoạt động đúng,
            success: function (result) {
			     if (result.status === 'success') {
	                    Swal.fire({
	                        icon: 'success',
	                        title: 'Cập nhật topic thành công!',
	                        timer: 1500,
	                        showConfirmButton: false
	                    }).then(() => {
	                        window.location.href = "${ProductURl}?type=edit&id=" + encodeURIComponent(result.data.id);
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
    const input = document.getElementById('productimg');
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
<script>

</script>
</body>
</html>>