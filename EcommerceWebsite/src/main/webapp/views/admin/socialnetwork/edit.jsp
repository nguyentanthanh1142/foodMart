<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chỉnh sửa mạng xã hội</title>
</head>
<body>
	<c:url var="APIurl" value="/api-admin-socialnetwork" />
	<c:url var="list" value="/admin/socialnetwork" />
	<form action="${editsave}" enctype="multipart/form-data" accept-charset="UTF-8" method="POST" id="formSubmit">
		<div class="content-wrapper pt-3">
			<!-- Content Header (Page header) -->
			<!-- Main content -->
			<section class="content">
				<!-- Default box -->
				<div class="card">
					<div class="card-header">
						<h3 class="card-title">
							<strong class="text-uppercase text-danger">Chỉnh sửa
								mạng xã hội</strong>
						</h3>
						<div class="card-tools">
							<button type="submit" id="btnSubmit"
								onclick="return confirm('Bạn có chắc chắn thực hiện không?');"
								class="btn btn-sm btn-success">
								<i class="fas fa-save"></i> Lưu
							</button>
							<a class="btn btn-sm btn-danger" href="${list }"> Quay về
								danh sách</a>
						</div>
					</div>
					<div class="card-body">
						<div class="row">
							<input type="hidden"  name="id" value="${model.id}" id="id" />
							<div class="col-md-9">
								<div class="form-group">
									<label>Tên mạng xã hội</label>
									<input class="form-control" type="text" name="name"
										required="required"
										value="${ empty oldvalue.name ? model.name: oldvalue.name }" />
									<c:if test="${ not empty msgName}">
										<span class="form-error">${msgName}</span>
									</c:if>
								</div>
								<div class="form-group">
									<label>Địa chỉ mạng xã hội</label>
									<input class="form-control" type="text" name="address"
										required="required"
										value="${ empty oldvalue.address ? model.address: oldvalue.address }" />
								</div>
								<div class="form-group">
									<label>Icon</label>
									<input class="form-control" type="text" name="icon"
										required="required"
										value="${ empty oldvalue.icon ? model.icon: oldvalue.icon }" />
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label>Trạng thái</label>
									<select class="form-control" name="status">
										<option value="1" label="Xuất bản"></option>
										<option value="2" label="Chưa xuất bản"></option>
									</select>
								</div>
								<div class="form-group">
									<label>Hình đại diện</label> <input type="file"
										class="form-control-file" accept="image/*" name="image" id="image" />
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
		<script type="text/javascript">
			var status = document.getElementById("status123").value;
			if (status == 2) {
				var list = document.getElementById("status").lastElementChild.selected = true;
			}
		</script>
		<script type="text/javascript">id
		 $('#btnSubmit').click(function (e) {
		        e.preventDefault();
		        
		        var formDT = new FormData();
			     
		        var formData = $('#formSubmit').serializeArray();
		        $.each(formData, function (i, v) {
		        	formDT.append(v.name,v.value);
		            console.log(v.name + ": " + v.value);
		        });
		        
		        var inputfile=$('#image')[0].files[0];
		        console.log(inputfile);
		        if(inputfile){
		        	formDT.append("image",inputfile)
		        }
		        var id = $('#id').val();
		        console.log(id);
		        if (id == "") {
		        	addSocial(formDT);
		        	console.log(JSON.stringify(formData));
		        } else {
		        	updateSocial(formDT);
		        	console.log(JSON.stringify(formData));
		        }
		  
		    });
		        
		        
		      
		    function addSocial(data) {
		    	  $.ajax({
		              url: '${APIurl}',
		              type: 'POST',
		              data: data,
		              processData: false,  // Phải set là false để FormData hoạt động đúng
		              contentType: false,  // Phải set là false để FormData hoạt động đúng,
		              dataType: 'json',
		              success: function (result) {
		            	  console.log("Thanh cong");
		              	window.location.href = "${list}?type=edit&id="+result.id+"&message=insert_success";
		              },
		              error: function (error) {
		            	  console.log("That bai");
		              	window.location.href = "${list}?type=list&maxPageItem=2&page=1&message=error_system";
		              }
		          });
		    	   
		    }
		    function updateSocial(data) {
		        $.ajax({
		        	url: '${APIurl}',
		            type: 'PUT',
		            data: data,
		            processData: false,  // Phải set là false để FormData hoạt động đúng
		            contentType: false,  // Phải set là false để FormData hoạt động đúng,
		            success: function (result) {
		            	window.location.href = "${list}?type=edit&id="+result.id+"&message=update_success";
		            },
		            error: function (error) {
		            	window.location.href = "${list}?type=list&maxPageItem=2&page=1&message=error_system";
		            }
		        });
		    }
		</script>
		
</body>
</html>