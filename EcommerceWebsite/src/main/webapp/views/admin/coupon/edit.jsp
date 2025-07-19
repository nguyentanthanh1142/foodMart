<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="APIurl" value="/api-admin-coupon" />
<c:url var="CouponUrl" value="/admin/coupon" />
<html>
<head>
<title>Chỉnh sửa mã khuyến mãi</title>

</head>
<body>
	<main class="app-main">
	<div class="main-content">
		<div class="main-content-inner">
			<div class="page-content container">
				<div class="row">
					<form id="formSubmit" enctype="multipart/form-data">
						<div class="row">
							<div class="col-xs-12">

								<div class="card-header">
									<h3 class="card-title">
										<strong class="text-uppercase text-danger"> <c:choose>
												<c:when test="${empty model.id}">
        Thêm mã khuyến mãi
    </c:when>
												<c:otherwise>
       	Cập mã khuyến mãi
    </c:otherwise>
											</c:choose>

										</strong>
									</h3>
									<div class="card-tools">
										<a class="btn btn-sm btn-danger" href="${CouponUrl }"><i
											class="fas fa-trash"></i> Quay lại</a>
									</div>
								</div>
							</div>
							<div class="col-xs-12 col-md-8 card-body">
								<c:if test="${not empty messageResponse}">
									<div class="alert alert-${alert}">${messageResponse}</div>
								</c:if>
								<h5>Thông tin cơ bản</h5>
								
								<div class="form-group row">
									<div class="col-xs-12 col-md-6">
										<label class="col-sm-3 control-label no-padding-right">Tên</label>
																			<div class="col-12">
										<input type="text" class="form-control" id=name name="name"
											value="${model.name}" />
									</div>
									</div>
									<div class="col-xs-12 col-md-6">
									<label class=" control-label no-padding-right">Mã
										khuyến mãi</label>
									<div class="col-sm-12">
										<input type="text" class="form-control" id="code" name="code"
											value="${model.code}" />
									</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right">Mô
										tả</label>
									<div class="col-sm-9">
										<textarea class="form-control" id="description"
											name="description" rows="3" cols="50">${model.description}
									</textarea>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right">Số
										lượng mã</label>
									<div class="col-sm-9">
										<input type="number" class="form-control" id="quantity"
											name="quantity" value="${model.quantity}" />
									</div>
								</div>
								<div class="form-group row">
									<div class="col-xs-12 col-md-6">
										<label class="col control-label no-padding-right">Loại
											giảm giá</label>
											<div class="col-12">
											<select name="byPercent" id="byPercent" class="form-control">
												<option value="true">Giảm giá theo phần trăm (%)</option>
												<option value="false"
													<c:if test="${!model.byPercent}">selected</c:if>>
													Giảm giá theo vnđ</option>
											</select>
										</div>
									</div>
									<div class="col-xs-12 col-md-6">
										<label class="col control-label no-padding-right">Loại
											giảm giá</label>
										<div class="col-12">
											<select name="byPercent" id="byPercent" class="form-control">
												<option value="true">Giảm giá theo phần trăm (%)</option>
												<option value="false"
													<c:if test="${!model.byPercent}">selected</c:if>>
													Giảm giá theo vnđ</option>
											</select>
										</div>
									</div>
								</div>
								<div class="form-group">
									<h3>Áp dụng cho sản phẩm cụ thể</h3>

									<div class="tag-box">
										<div class="tags" id="tagList"></div>
										<input type="text" id="tagInput" class="tag-input"
											placeholder="Nhập để tìm...">
										<div class="suggestions" id="suggestionBox"></div>
									</div>

								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right">Giá
										trị</label>
									<div class="col-sm-9">
										<input type="number" class="form-control" id="pricesale"
											name="pricesale" value="<fmt:formatNumber value="${model.pricesale}"
																		type="currency" minFractionDigits="0"
																		currencySymbol="" />" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right">Giới
										hạn giá trị</label>
									<div class="col-sm-9">
<input type="number" class="form-control" id="limitedDiscount"
       name="limitedDiscount"
       value="<fmt:formatNumber value='${model.limitedDiscount}' type='number' groupingUsed='false' maxFractionDigits='2' />" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right">Giá
										trị đơn hàng tối thiểu</label>
									<div class="col-sm-9">
										<input type="number" class="form-control" id="minValue"
											name="minValue" value="<fmt:formatNumber value="${model.minValue}"
																		type="currency" minFractionDigits="0"
																		currencySymbol="" />" />
									</div>
								</div>
								<fmt:formatDate value="${model.start}" pattern="yyyy-MM-dd"
									var="startDate" />
								<fmt:formatDate value="${model.end}" pattern="yyyy-MM-dd"
									var="endDate" />
								
							</div>
							<div class="col-xs-12 col-md-4 card-body">
								<div class="form-group">
									<label class="control-label no-padding-right">Trạng
										thái</label> <select name="status" id="status" class="form-control">
										<option value="0">Chưa xuất bản</option>
										<option value="1" <c:if test="${model.status == 1}">selected</c:if>>Xuất bản</option>
									</select>
								</div>

								<div class="form-group">
									<label class=" control-label no-padding-right">Bắt
										đầu khuyến mãi khóa</label>
									<div class="">
										<input class="form-control" type="date" name="start"
											value="${startDate}" />
									</div>
								</div>

								<div class="form-group">
									<label class=" control-label no-padding-right">Hết
										hạn khuyến mãi</label>
									<div class="">
										<input class="form-control" type="date" name="end"
											value="${endDate}" />
									</div>
								</div>
																<div class="form-group">
									<div class="col-sm-12">
										<c:if test="${not empty model.id}">
											<input type="button"
												class="btn btn-white btn-warning btn-bold"
												value="Cập nhật chủ đề" id="btnAddOrUpdateCoupon" />
										</c:if>
										<c:if test="${empty model.id}">
											<input type="button"
												class="btn btn-white btn-warning btn-bold"
												value="Thêm chủ đề" id="btnAddOrUpdateCoupon" />
										</c:if>
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
		$('#btnAddOrUpdateCoupon').click(function(e) {
			e.preventDefault();
			console.log(getSelectedIds());
			var data = {};
			var formData = $('#formSubmit').serializeArray();
			$.each(formData, function(i, v) {
				data["" + v.name + ""] = v.value;
				console.log(v.name + ": " + v.value);
			});
			var id = $('#id').val();
			data["listProduct"] = getSelectedIds();
			if (id == "") {
				addCoupon(data);
			} else {
				updateCoupon(data);
			}
		});

		function addCoupon(data) {
			$
					.ajax({
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
				                        window.location.href = "${CouponRUL}?type=edit&id=" + encodeURIComponent(result.data.id);
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
		function updateCoupon(data) {
			$
					.ajax({
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
				                        window.location.href = "${CouponURL}?type=edit&id=" + encodeURIComponent(result.data.id);
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
  const tagList = document.getElementById('tagList');
  const input = document.getElementById('tagInput');
  const suggestionBox = document.getElementById('suggestionBox');

  let productList = []; // [{ id, name }]
  let selectedTags = []; // [{ id, name }]
  
  let listProduct = JSON.parse('${fn:escapeXml(listProduct)}')?JSON.parse('${fn:escapeXml(listProduct)}'):[];
  console.log(listProduct);
  
  
  // Gọi API và xử lý dữ liệu
fetch('http://localhost:8080/EcommerceWebsite/api-admin-product')
  .then(res => res.json())
  .then(data => {
    productList = data.map(p => {
      if (listProduct && listProduct.includes(p.id)) {
        selectedTags.push({ id: p.id, name: p.name });
      }
      return { id: p.id, name: p.name };
    });

    updateSuggestions(); // gọi sau khi load xong
    renderTags()
  });

  function renderTags() {
    tagList.innerHTML = '';
    selectedTags.forEach((tag, index) => {
      const tagEl = document.createElement('div');
      tagEl.className = 'tag';
      tagEl.textContent = tag.name;

      const removeBtn = document.createElement('span');
      removeBtn.textContent = '×';
      removeBtn.onclick = () => {
        selectedTags.splice(index, 1);
        renderTags();
        updateSuggestions();
      };

      tagEl.appendChild(removeBtn);
      tagList.appendChild(tagEl);
    });
  }

  function updateSuggestions() {
    const search = input.value.trim().toLowerCase();
    const filtered = productList.filter(item =>
      item.name.toLowerCase().includes(search) &&
      !selectedTags.some(tag => tag.id === item.id)
    );

    suggestionBox.innerHTML = '';
    filtered.forEach(item => {
      const itemEl = document.createElement('div');
      itemEl.className = 'suggestion-item';
      itemEl.textContent = item.name;
      itemEl.onclick = () => {
        selectedTags.push({ id: item.id, name: item.name });
        input.value = '';
        renderTags();
        updateSuggestions();
      };
      suggestionBox.appendChild(itemEl);
    });
  }

  input.addEventListener('input', updateSuggestions);

  input.addEventListener('keydown', (e) => {
    if (e.key === 'Enter') {
      e.preventDefault();
      const value = input.value.trim().toLowerCase();
      const match = productList.find(
        item => item.name.toLowerCase() === value
      );
      if (match && !selectedTags.some(tag => tag.id === match.id)) {
        selectedTags.push({ id: match.id, name: match.name });
        input.value = '';
        renderTags();
        updateSuggestions();
        
      }
    }
  });

  // Nếu cần lấy danh sách ID khi submit form:
  function getSelectedIds() {
    return selectedTags.map(tag => tag.id);
  }

  // Có thể gán hàm getSelectedIds vào nút submit chẳng hạn
</script>
</body>
</html>
>
