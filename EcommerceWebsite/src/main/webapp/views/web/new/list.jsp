<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="newURL" value="/tin-tuc" />
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Tin tức</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
.news-img {
	width: 100%;
	height: auto;
	border-radius: 8px;
}

.highlight-box {
	background-color: #f8f9fa;
	border-radius: 10px;
	padding: 15px;
	margin-bottom: 20px;
}

.section-title {
	font-weight: bold;
	font-size: 1.25rem;
	margin-bottom: 10px;
}
</style>
</head>
<body>
<section class="clearfix bread-crumb">
		<span class="bread-cumb-border"></span>
		<div class="container">
			<div class="row">
				<div class="col-12">
					<ul class="breadcrumb">
						<li class="breadcrumb-item"><a class="link"
							href="<c:url value="/trang-chu"/>"><span>Trang chủ</span></a></li>
						<li class="breadcrumb-item active"><span>Tin tức</span></li>
					</ul>
				</div>
			</div>
		</div>
	</section>
	<div class="container mt-4">
		<div class="row">
			<!-- Cột trái -->
			<div class="col-md-8">
				<!-- Tin bài -->
				<c:forEach var="item" items="${model.listResult}">
					<div class="row mb-3">

						<div class="col-md-4">
							<a href="${pageContext.request.contextPath}/tin-tuc/${item.slug}">
							<img
								src="${item.thumbnail }"
								class="news-img" alt="Google Pixel 9A" style="width: 100%;">
								</a>
						</div>
						<div class="col-md-8">
							<h6>${item.title}</h6>
							<p class="text-muted">
								 <span class="time-ago" data-time="${item.createdAt}"> </span>
							</p>
							<p>${item.shortDescription}</p>
						</div>

					</div>
				</c:forEach>
					<form id = "formSubmit" class="" action="${noteURL}" method="GET">
				<ul class="pagination justify-content-center mt-3" id="pagination"></ul>
					<input type="hidden" value="" id="page" name="page" /> <input
						type="hidden" value="" id="maxPageItem" name="maxPageItem"/>
						</form>
			</div>





			<!-- Cột phải -->
			<div class="col-md-4">
				<div class="highlight-box text-center bg-warning text-white mb-3">
					<h5>GIẢM 10%</h5>
					<p>(Tối đa 300.000đ)</p>
					<button class="btn btn-light">Nhận ngay</button>
				</div>

				<div class="highlight-box mb-3">
					<div class="section-title">#CHỦ ĐỀ HOT</div>
					<ul class="list-group list-group-flush">
						<li class="list-group-item">iPhone 15 series</li>
						<li class="list-group-item">Samsung Galaxy S24</li>
						<li class="list-group-item">Mở bán realme</li>
					</ul>
				</div>

				<div class="highlight-box mb-3">
					<div class="section-title">#SỰ KIỆN</div>
					<ul class="list-group list-group-flush">
						<li class="list-group-item">Sự kiện Computex 2025</li>
						<li class="list-group-item">Sự kiện Google I/O 2025</li>
						<li class="list-group-item">Sự kiện WWDC 2025</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/moment@2.29.4/moment.min.js"></script>

<script>
var totalPages = ${model.totalPage};
var currentPage = ${model.page};
console.log(totalPages);
console.log(currentPage);
var limit = 5;
$(function() {
	window.pagObj = $('#pagination').twbsPagination({
		totalPages : totalPages,
		visiblePages : 10,
		startPage : currentPage,
		onPageClick : function(event, page) {
			if (currentPage != page) {
				$('#maxPageItem').val(limit);
				$('#page').val(page);
				$('#sortBy').val('desc');
				$('#type').val('list');
				$('#formSubmit').submit();
			}
		}
	});
});

</script>

<script>
// Cập nhật ngay và mỗi phút
updateAllTimeAgo();
setInterval(updateAllTimeAgo, 60000); // mỗi 60 giây
function timeAgo(dateString) {
    const date = new Date(dateString);
    const now = new Date();
    const seconds = Math.floor((now - date) / 1000);
    const weekdays = ['Chủ nhật', 'Thứ Hai', 'Thứ Ba', 'Thứ Tư', 'Thứ Năm', 'Thứ Sáu', 'Thứ Bảy'];
    const weekdayName = weekdays[date.getDay()];
	console.log("data: " + date);
	console.log("now: " + now);
	console.log("seconds: " + seconds);
    if (seconds < 60) return "Vừa xong";
    else if (seconds < 3600) return Math.floor(seconds / 60) + " phút trước";
    else if (seconds < 86400) return Math.floor(seconds / 3600) + " giờ trước";
    else if (seconds < 604800) return Math.floor(seconds / 86400) + " ngày trước";
    else return weekdayName+", " + date.getDate() + " tháng " + (date.getMonth() + 1) + " " + date.getFullYear();
}

function updateAllTimeAgo() {
    const elements = document.querySelectorAll('.time-ago');
    elements.forEach(el => {
        const timeStr = el.getAttribute('data-time');
        el.textContent = timeAgo(timeStr);
    });

}</script>
</body>
</html>