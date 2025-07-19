<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${page.title}</title>
</head>
<body>
	<div class="container mb-5">
		<nav aria-label="breadcrumb">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="<c:url value="/trang-chu"/>">Trang chủ</a></li>
				<li class="breadcrumb-item active" aria-current="page">${page.title}</li>
			</ol>
		</nav>
		<div class="row justify-content-center">
			<div class="col-lg-8">
				<div class="my-4">${page.content}</div>
			</div>
		</div>
	</div>
</body>
</html>