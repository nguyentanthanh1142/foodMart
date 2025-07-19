<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="APIurl" value="/api-admin-configweb" />
<c:url var="ConfigURL" value="/admin/configweb" />
<html>
<head>
<title>Cấu hình website</title>
</head>
<body>
	<!--begin::App Main-->
	<main class="app-main">
	<div class="app-content mt-3">
		<!--begin::Container-->
	<form class = "formEditConfig" id = "formEditConfig" action="${editsave}" enctype="multipart/form-data" method="POST">
		<div class="content-wrapper pt-3">
			<!-- Content Header (Page header) -->
			<!-- Main content -->
			<section class="content">
				<!-- Default box -->
				<div class="card">
					<div class="card-header">
						<h3 class="card-title">
							<strong class="text-uppercase text-danger">Cấu hình
								website</strong>
						</h3>
						<div class="card-tools">
							<button type="submit"
								onclick="return confirm('Bạn có chắc chắn thực hiện không?');"
								class="btn btn-sm btn-success " id = "btnSubmitForm">
								<i class="fas fa-save"></i> Lưu
							</button>
							<a class="btn btn-sm btn-danger" href="${ConfigURL }"> Quay về</a>
						</div>
					</div>
					<div class="card-body">
						<div class="row">
							<div class="col-md-9">
								<input name = "id" type="hidden" value=${config.id }>
								<div class="form-group">
									<label>Tên website</label>
									<input class="form-control" type="text" name = "webname"
										required="required" value="${config.webname}" >
								</div>
								<div class="form-group">
									<label>Mô tả</label>
									<input name="web_detail" class="form-control" type="text"
										value="${config.web_detail }" >
								</div>
								<div class="form-group">
									<label>Hotline</label>
									<input name="hotline" class="form-control" type="text"
										value="${config.hotline }" >
								</div>
								<div class="form-group">
									<label>Email</label>
									<input name="email" class="form-control" type="text"
										 value="${config.email }" >
								</div>
								<div class="form-group">
									<label>Địa chỉ</label>
									<input name="address_store" class="form-control" type="text"
										 value="${config.address_store }" >
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label>Logo website</label> <input type="file"
										class="form-control-file" accept="image/*" name="logo" id="logo"/>
								</div>
								<div class="form-group">
									<label>Logo mobile</label> <input type="file"
										class="form-control-file" accept="image/*" name="image1" />
								</div>
								<div class="form-group">
									<label>Icon website</label> <input type="file"
										class="form-control-file" accept="image/*" name="icon" id = "icon"/>
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
	</div>
	<!--end::App Content--> </main>
	<!--end::App Main-->
	<script>
	$("#btnSubmitForm").click(function (e){
		e.preventDefault();
        var formDT = new FormData();
	 var formData = $('#formEditConfig').serializeArray();
     $.each(formData, function (i, v) {
     	formDT.append(v.name,v.value);
         console.log(v.name + ": " + v.value);
     });
     
     var inputfile=$('#logo')[0].files[0];
     console.log(inputfile);
     if(inputfile){
     	formDT.append("logo",inputfile)
     }
     var inputfile2=$('#icon')[0].files[0];
     console.log(inputfile2);
     if(inputfile){
     	formDT.append("icon",inputfile2)
     }
     updatePro(formDT);
     
 });
    function updatePro(data) {
        $.ajax({
        	url: '${APIurl}',
            type: 'POST',
            data: data,
            processData: false,  // Phải set là false để FormData hoạt động đúng
            contentType: false,  // Phải set là false để FormData hoạt động đúng,
            success: function (result) {
            	window.location.href = "${ConfigURL}?type=edit&message=update_success";
            },
            error: function (error) {
            	window.location.href = "${ConfigURL}?type=list&maxPageItem=2&page=1&message=error_system";
            }
        });
    }
	
	</script>
</body>
</html>
>
