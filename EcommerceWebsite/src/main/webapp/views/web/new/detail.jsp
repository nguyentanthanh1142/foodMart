<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${news.title }</title>
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
						<li class="breadcrumb-item"><a
							href="<c:url value="/${product.name}"/>"><span>Tin tức</span></a></li>
						<li class="breadcrumb-item active" aria-current="page">${news.title}</li>
					</ul>
				</div>
			</div>
		</div>
	</section>
	<div class="container my-5">
		<div class="row justify-content-center">
			<div class="col-lg-8">

				<p class="text-uppercase text-muted fw-semibold small">Công nghệ</p>
				<h1 class="fw-bold mb-3">${news.title }</h1>

				<p class="text-muted mb-1">
					<strong>${news.createdBy }</strong> - <span class="time-ago" data-time="${news.createdAt}"> </span>
				</p>

				<p class="fs-5 mt-3">${shortDescription}</p>

				<div class="my-4">${news.content}</div>
			</div>
			<div class="col-lg-8">
				<h5>Bình luận</h5>
				<form id="commentForm">
					<input type="hidden" name="postId" value="${news.id}">
					<div class="mb-3">
						<textarea name="content" class="form-control"
							placeholder="Nội dung bình luận" rows="3" required></textarea>
					</div>
					<button type="submit" class="btn btn-primary">Gửi bình
						luận</button>
				</form>
				<div id="comment-list">
					<c:forEach var="comment" items="${comments}">
						<div class="mb-3 comment-item">
							<strong>${comment.username}</strong> <small class="text-muted">(${comment.createdAt})</small>
							<p>${comment.content}</p>
						</div>
					</c:forEach>
				</div>
				<button id="loadMoreComments"
					class="btn btn-outline-danger d-block mx-auto mt-3">Xem
					thêm ý kiến</button>

			</div>
		</div>
	</div>

	<script>
    document.addEventListener("DOMContentLoaded", function() {
        const comments = document.querySelectorAll(".comment-item");
        const loadMoreBtn = document.getElementById("loadMoreComments");
        let visibleCount = 3; // số lượng hiển thị ban đầu
        const step = 3; // mỗi lần bấm hiện thêm bao nhiêu

        if (comments.length == 0) {
            loadMoreBtn.classList.add("d-none");
        }
        
        function updateVisibility() {
            comments.forEach((comment, index) => {
                comment.style.display = index < visibleCount ? "block" : "none";
            });

            // Ẩn nút nếu đã hiển thị hết
            if (visibleCount >= comments.length) {
            	loadMoreBtn.classList.add("d-none");
            }
        }

        loadMoreBtn.addEventListener("click", function() {
            visibleCount += step;
            updateVisibility();
        });

        updateVisibility();
    });
</script>

	<script>
		const socket = new WebSocket("ws://localhost:8080/EcommerceWebsite/ws-comments/${news.id}");

		socket.onmessage = function(event) {
			const msg = JSON.parse(event.data);
			console.log("Dữ liệu WebSocket nhận được:", msg);
			if (msg.type === "new-comment") {
				renderComment(msg.comment);
			}
		};
		function renderComment(comment) {
			const container = document.createElement("div");
			container.classList.add("mb-3","comment-item");

			const html = '<strong>' + comment.username +'</strong> <small class="text-muted">" ' + comment.createdAt + '</small><p>'+ comment.content +'</p>';
			container.innerHTML = html;

			document.getElementById("comment-list").prepend(container);
		}

		$('#commentForm').on('submit', function(e) {
			e.preventDefault();
			var data = {};
			var formData = $(this).serializeArray();
			$.each(formData, function(i, v) {
				data["" + v.name + ""] = v.value;
				console.log(v.name + ": " + v.value);
			});

			$.ajax({
				url : '/EcommerceWebsite/api-comments',
				type : 'POST',
				data : JSON.stringify(data),
				contentType : "application/json",
				dataType : 'json',
				success : function(result) {
					$('#commentForm')[0].reset();
				},
				error : function(error) {
					console.error("Lỗi khi gửi bình luận:", error);
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