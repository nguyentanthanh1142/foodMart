<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sản phẩm</title>
</head>
<body>
	<section class="clearfix bread-crumb">
		<span class="bread-cumb-border"></span>
		<div class="container">
			<div class="row">
				<div class="col-12">
					<ul class="breadcrumb">
						<li class="breadcrumb-item"><a
							href="<c:url value="/trang-chu"/>"><span>Trang chủ</span></a></li>
						<li class="breadcrumb-item active"><strong><span>Kết
									quả tìm kiếm</span></strong></li>
					</ul>
				</div>
			</div>
		</div>
	</section>
	<section class="clearfix mt-3">
		<div class="container">
			<div
				class="category-products products category-products-grids clearfix">
				<div class="sort-cate clearfix shadow-sm">
					<c:choose>
						<c:when test="${model.totalItem == 0}">
							<div class="row justify-content-center">
								<div class="col-lg-12 col-md-12 col-sm-12 mt-3">
									<h1
										class="title-head text-center margin-bottom-20 margin-top-0"
										style="font-size: 1.1em;">Không tìm thấy bất kỳ kết quả
										nào với từ khóa trên.</h1>
								</div>
								<div class="col-md-7 col-sm-12 col-lg-7">
									<div class="text-center mb-3">Vui lòng nhập từ khóa tìm
										kiếm khác</div>
									<form action='<c:url value="/search"/>' method="GET"
										class="search-form" style="width: 100%">
										<div class="input-group">
											<input type="text" class="form-control rounded-0"
												maxlength="70" name="q"
												placeholder="Nhập từ khóa tìm kiếm..." required> <input
												type="hidden" name="currentPage" value="1">
											<button class="btn-search btn rounded-0" type="submit">
												Tìm kiếm</button>
										</div>
									</form>
								</div>
							</div>

						</c:when>
						<c:otherwise>
							<div class="row">

								<div class="col-sm-6 col-12" style="margin-top: 5px;">
									<h3>Tìm thấy ${model.totalItem} kết quả với từ khóa ${model.name }</h3>
								</div>
								<div class="col-sm-6 col-12 sort-cate-left">
									<h3 class="sort-title">
										<svg id="Layer" enable-background="new 0 0 64 64" height="512"
											viewBox="0 0 64 64" width="512"
											xmlns="http://www.w3.org/2000/svg">
								<path
												d="m31.414 15.586-7-7c-.78-.781-2.048-.781-2.828 0l-7 7c-.781.781-.781 2.047 0 2.828.78.781 2.048.781 2.828 0l3.586-3.586v39.172c0 1.104.896 2 2 2s2-.896 2-2v-39.172l3.586 3.586c.39.391.902.586 1.414.586s1.024-.195 1.414-.586c.781-.781.781-2.047 0-2.828z" />
								<path
												d="m49.414 45.586c-.781-.781-2.047-.781-2.828 0l-3.586 3.586v-39.172c0-1.104-.896-2-2-2s-2 .896-2 2v39.172l-3.586-3.586c-.781-.781-2.048-.781-2.828 0-.781.781-.781 2.047 0 2.828l7 7c.391.391.902.586 1.414.586s1.023-.195 1.414-.586l7-7c.781-.781.781-2.047 0-2.828z" /></svg>
										Xếp theo
									</h3>
									<form class="form-inline form-viewpro order_bar">
										<div class="form-group" style="margin: 0;">
											<select class="form-control"
												onchange="if (this.value) window.location.href=this.value">
												<option
													value="<c:url value="/search?name=${model.name}&sortBy=ctime&sortName=desc"/>" ${sort =='ctime' && order == 'desc'?'selected':''  }>Mới
													nhất</option>
												<option
													value="<c:url value="/search?name=${model.name}&sortBy=ctime&sortName=asc"/>" ${sort =='ctime' && order == 'asc'?'selected':''  }>Cũ
													đến mới</option>
												<option
													value="<c:url value="/search?name=${model.name}&sortBy=price&sortName=asc"/>" ${sort =='price' && order == 'asc'?'selected':''  }>Giá
													tăng dần</option>
												<option
													value="<c:url value="/search?name=${model.name}&sortBy=price&sortName=desc"/>" ${sort =='price' && order == 'desc'?'selected':''  }>Giá
													giảm dần</option>
												<option
													value="<c:url value="/search?name=${model.name}&sortBy=alpha&sortName=asc"/>" ${sort =='alpha' && order == 'asc'?'selected':''  }>Từ
													A->Z</option>
												<option
													value="<c:url value="/search?name=${model.name}&sortBy=alpha&sortName=desc"/>" ${sort =='alpha' && order == 'desc'?'selected':''  }>Từ
													Z->A</option>

											</select>
										</div>
									</form>
								</div>
							</div>
				</div>
				<div class="product-item row">
					<c:forEach var="item" items="${model.listResult}" varStatus="index">
						<div class="col-lg-3 col-6">
							<div class="card product-box pt-3" style="width: 100%;">
								<!-- <div class="sale-flash">-48%</div> -->
								<a
									href="<c:url value='/chi-tiet-san-pham/${item.slug}'/>"
									title="${item.name}"> <img class="card-img-top"
									src="${pageContext.request.contextPath}/images/${item.productimg}"
									alt="" height="175"">
								</a>
								<div class="card-body text-center">
									<h6 class="card-title" title="${item.name}">
										<a class="product-item-name"
											href="<c:url value='/chi-tiet-san-pham/${item.slug}'/>"
											title="${item.name}">${item.name} </a>
									</h6>
									<p>
										<span class="price"><fmt:formatNumber
												value="${item.pricesale}" type="number" />đ</span><span
											class="oldprice"><fmt:formatNumber
												value="${item.price}" type="number" />đ</span>
									</p>
									<div class="group-action">
										<a class="btn-buy" title="Chi tiết sản phẩm"
											href="<c:url value='/chi-tiet-san-pham/${item.slug}'/>">
											<i class="fas fa-search"></i>
										</a> <a class="btn-detail" title="Thêm vào giỏ hàng"
											data-id="${item.id}"
											href="<c:url value="/AddCart/${item.id}"/>"><i
											class="fas fa-cart-plus"></i></a>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
				<nav aria-label="Page navigation example">
					<ul class="pagination justify-content-center">
						<!-- <c:forEach var="item" begin="1" end="${paginateInfo.totalPage}"
							varStatus="loop">
							<c:if test="${(loop.index)==paginateInfo.currentPage}">
								<li class="page-item active"><a class="page-link"
									href="<c:url value="/search?${rppart1 }${loop.index}${ rppart2}"/>">${loop.index}</a></li>
							</c:if>
							<c:if test="${(loop.index) != paginateInfo.currentPage}">
								<li class="page-item "><a class="page-link"
									href="<c:url value="/search?${rppart1 }${loop.index}${ rppart2}"/>">${loop.index}</a></li>
							</c:if>
						</c:forEach>-->
					</ul>
				</nav>
				</c:otherwise>
				</c:choose>

			</div>
		</div>
	</section>
	<script>
		toastr.options = {
			"closeButton" : true,
			"newestOnTop" : false,
			"progressBar" : false,
			"positionClass" : "toast-top-right",
			"preventDuplicates" : false,
			"onclick" : null,
			"showDuration" : "0",
			"hideDuration" : "1000",
			"timeOut" : "5000",
			"extendedTimeOut" : "1000",
			"showEasing" : "swing",
			"hideEasing" : "linear",
			"showMethod" : "fadeIn",
			"hideMethod" : "fadeOut"
		};
		$(document).ready(function onDocumentReady() {
			var msgAddCart = '${msgAddCart}';
			console.log(msgAddCart);
			if (msgAddCart) {
				toastr.success(msgAddCart);
			}
		});
	</script>
	<script>
	function getPageList(totalPages, page, maxLength) {
	    if (maxLength < 5) throw "maxLength must be at least 5";

	    function range(start, end) {
	        return Array.from(Array(end - start + 1), (_, i) => i + start); 
	    }

	    var sideWidth = maxLength < 9 ? 1 : 2;
	    var leftWidth = (maxLength - sideWidth*2 - 3) >> 1;
	    var rightWidth = (maxLength - sideWidth*2 - 2) >> 1;
	    if (totalPages <= maxLength) {
	        // no breaks in list
	        return range(1, totalPages);
	    }
	    if (page <= maxLength - sideWidth - 1 - rightWidth) {
	        // no break on left of page
	        return range(1, maxLength - sideWidth - 1)
	            .concat(0, range(totalPages - sideWidth + 1, totalPages));
	    }
	    if (page >= totalPages - sideWidth - 1 - rightWidth) {
	        // no break on right of page
	        return range(1, sideWidth)
	            .concat(0, range(totalPages - sideWidth - 1 - rightWidth - leftWidth, totalPages));
	    }
	    // Breaks on both sides
	    return range(1, sideWidth)
	        .concat(0, range(page - leftWidth, page + rightWidth),
	                0, range(totalPages - sideWidth + 1, totalPages));
	}

	// Below is an example use of the above function.
	$(function () {
	    // Number of items and limits the number of items per page
	    // Total pages rounded upwards
	    var totalPages = ${paginateInfo.totalPage};
	   	console.log(totalPages);
	    // Number of buttons at the top, not counting prev/next,
	    // but including the dotted buttons.
	    // Must be at least 5:
	    var paginationSize = 5; 
	    var currentPage;

	    function showPage(whichPage) {
	    	if(totalPages == 1) return false;
	        if (whichPage < 1 || whichPage > totalPages) return false;
	        currentPage = whichPage;
		    // Include the prev/next buttons:
		    $(".pagination").append(
		        $("<li>").addClass("page-item").attr({ id: "previous-page" }).append(
		            $("<a>").addClass("page-link").attr({
		                href: "javascript:void(0)"}).text("Prev")
		        ),
		        $("<li>").addClass("page-item").attr({ id: "next-page" }).append(
		            $("<a>").addClass("page-link").attr({
		                href: "javascript:void(0)"}).text("Next")
		        )
		    );
	        // Replace the navigation items (not prev/next):            
	        $(".pagination li").slice(1, -1).remove();
	        getPageList(totalPages, currentPage, paginationSize).forEach( item => {
	            $("<li>").addClass("page-item")
	                     .addClass(item ? "current-page" : "disabled")
	                     .toggleClass("active", item == whichPage).append(
	                $("<a>").addClass("page-link").attr({
	                    href: '<c:url value="/search?${rppart1 }"/>' + item +"${rppart2 }"}).text(item || "...")
	            ).insertBefore("#next-page");
	        });
	        // Disable prev/next when at first/last page:
	        $("#previous-page").toggleClass("disabled", currentPage === 1);
	        $("#next-page").toggleClass("disabled", currentPage === totalPages);
	        return true;
	    }


	    // Show the page links
	    showPage(${currentPage != null?currentPage :1});

	    // Use event delegation, as these items are recreated later    
	    $(document).on("click", ".pagination li.current-page:not(.active)", function () {
	        return showPage(+$(this).text());
	    });
	    $("#next-page").on("click", function () {
	    	if(currentPage<totalPages)
	    	{
	    		return window.location = '<c:url value="/search?${rppart1 }"/>' + (currentPage<totalPages?currentPage+1:currentPage) + "${rppart2}";
	    		
	    	}
	    	else return;
	    	
	    });

	    $("#previous-page").on("click", function () {
	    	if(currentPage>1)
	    	{
	    		return window.location = '<c:url value="/search?${rppart1 }"/>' + (currentPage>1?currentPage-1:currentPage)+ "${rppart2}";
	    	}
	    	else return;
	    });
	});
    </script>
	<script>
		$(".btn-detail").on("click", function(e) {
			var id = $(this).data("id");
			e.preventDefault();
			$.ajax({
				url : '${pageContext.request.contextPath}/AddCart/' + id,
				type : 'get',
				success : function(result) {
					var carttotal = $("#cart-total").text();
					$("#cart-total").text(parseInt(carttotal) + 1);
					toastr.success("Thêm vào giỏ hàng thành công");
				},
				error : function(request, status, error) {
					toastr.error("Có lỗi khi thêm vào giỏ hàng");
				}
			});
		});
	</script>
</body>
</html>