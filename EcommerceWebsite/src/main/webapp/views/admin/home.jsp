<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="slide" value="/quan-tri/web/trinh-chieu" />
<c:url var="account" value="/admin/account" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
</head>
<body>
	<main class="app-main">
	<div class="app-content-header">
		<!--begin::Container-->
		<div class="container-fluid">
			<!--begin::Row-->
			<div class="row">
				<div class="col-sm-6">
					<h3 class="mb-0">Dashboard</h3>
				</div>
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-end">
						<li class="breadcrumb-item"><a href="#">Home</a></li>
						<li class="breadcrumb-item active" aria-current="page">Dashboard</li>
					</ol>
				</div>
			</div>
			<!--end::Row-->
		</div>
		<!--end::Container-->
	</div>
	<div class="app-content">
		<div class="container-fluid"></div>
		<div class="row">
			<!--begin::Col-->
			<div class="col-lg-3 col-6">
				<!--begin::Small Box Widget 1-->
				<div class="small-box text-bg-primary">
					<div class="inner">
						<h3><fmt:formatNumber value="${rpTotalInMonth}" type="number" />₫</h3>
						<p>Thu nhập trong tháng</p>
					</div>
					<svg class="small-box-icon" fill="currentColor" viewBox="0 0 24 24"
						xmlns="http://www.w3.org/2000/svg" aria-hidden="true">
                    <path
							d="M2.25 2.25a.75.75 0 000 1.5h1.386c.17 0 .318.114.362.278l2.558 9.592a3.752 3.752 0 00-2.806 3.63c0 .414.336.75.75.75h15.75a.75.75 0 000-1.5H5.378A2.25 2.25 0 017.5 15h11.218a.75.75 0 00.674-.421 60.358 60.358 0 002.96-7.228.75.75 0 00-.525-.965A60.864 60.864 0 005.68 4.509l-.232-.867A1.875 1.875 0 003.636 2.25H2.25zM3.75 20.25a1.5 1.5 0 113 0 1.5 1.5 0 01-3 0zM16.5 20.25a1.5 1.5 0 113 0 1.5 1.5 0 01-3 0z"></path>
                  </svg>
					<a href="#"
						class="small-box-footer link-light link-underline-opacity-0 link-underline-opacity-50-hover">
						More info <i class="bi bi-link-45deg"></i>
					</a>
				</div>
				<!--end::Small Box Widget 1-->
			</div>
			<div class="col-lg-3 col-6">
				<!--begin::Small Box Widget 2-->
				<div class="small-box text-bg-success">
					<div class="inner">
						<h3><fmt:formatNumber value="${rpTotalLastMonth}" type="number" />₫
						</h3>
						<p>Thu nhập tháng trước</p>
					</div>
					<svg class="small-box-icon" fill="currentColor" viewBox="0 0 24 24"
						xmlns="http://www.w3.org/2000/svg" aria-hidden="true">
                    <path
							d="M18.375 2.25c-1.035 0-1.875.84-1.875 1.875v15.75c0 1.035.84 1.875 1.875 1.875h.75c1.035 0 1.875-.84 1.875-1.875V4.125c0-1.036-.84-1.875-1.875-1.875h-.75zM9.75 8.625c0-1.036.84-1.875 1.875-1.875h.75c1.036 0 1.875.84 1.875 1.875v11.25c0 1.035-.84 1.875-1.875 1.875h-.75a1.875 1.875 0 01-1.875-1.875V8.625zM3 13.125c0-1.036.84-1.875 1.875-1.875h.75c1.036 0 1.875.84 1.875 1.875v6.75c0 1.035-.84 1.875-1.875 1.875h-.75A1.875 1.875 0 013 19.875v-6.75z"></path>
                  </svg>
					<a href="#"
						class="small-box-footer link-light link-underline-opacity-0 link-underline-opacity-50-hover">
						 <i class="bi bi-link-45deg"></i>
					</a>
				</div>
				<!--end::Small Box Widget 2-->
			</div>
			<!--end::Col-->
			<div class="col-lg-3 col-6">
				<!--begin::Small Box Widget 3-->
				<div class="small-box text-bg-warning">
					<div class="inner">
						<h3>${rpAccount }</h3>
						<p>Người dùng </p>
					</div>
					<svg class="small-box-icon" fill="currentColor" viewBox="0 0 24 24"
						xmlns="http://www.w3.org/2000/svg" aria-hidden="true">
                    <path
							d="M6.25 6.375a4.125 4.125 0 118.25 0 4.125 4.125 0 01-8.25 0zM3.25 19.125a7.125 7.125 0 0114.25 0v.003l-.001.119a.75.75 0 01-.363.63 13.067 13.067 0 01-6.761 1.873c-2.472 0-4.786-.684-6.76-1.873a.75.75 0 01-.364-.63l-.001-.122zM19.75 7.5a.75.75 0 00-1.5 0v2.25H16a.75.75 0 000 1.5h2.25v2.25a.75.75 0 001.5 0v-2.25H22a.75.75 0 000-1.5h-2.25V7.5z"></path>
                  </svg>
					<a href="${account }"
						class="small-box-footer link-dark link-underline-opacity-0 link-underline-opacity-50-hover">
						More info <i class="bi bi-link-45deg"></i>
					</a>
				</div>
				<!--end::Small Box Widget 3-->
			</div>
			<!--end::Col-->
			<div class="col-lg-3 col-6">
				<!--begin::Small Box Widget 4-->
				<div class="small-box text-bg-danger">
					<div class="inner">
						<h3>${rpCancelBill }</h3>
						<p>Đơn hàng đã hủy</p>
					</div>
					<svg class="small-box-icon" fill="currentColor" viewBox="0 0 24 24"
						xmlns="http://www.w3.org/2000/svg" aria-hidden="true">
                    <path clip-rule="evenodd" fill-rule="evenodd"
							d="M2.25 13.5a8.25 8.25 0 018.25-8.25.75.75 0 01.75.75v6.75H18a.75.75 0 01.75.75 8.25 8.25 0 01-16.5 0z"></path>
                    <path clip-rule="evenodd" fill-rule="evenodd"
							d="M12.75 3a.75.75 0 01.75-.75 8.25 8.25 0 018.25 8.25.75.75 0 01-.75.75h-7.5a.75.75 0 01-.75-.75V3z"></path>
                  </svg>
					<a href="#"
						class="small-box-footer link-light link-underline-opacity-0 link-underline-opacity-50-hover">
						More info <i class="bi bi-link-45deg"></i>
					</a>
				</div>
				<!--end::Small Box Widget 4-->
			</div>
			<!--end::Col-->
			<div class="row mt-4">
				
				<div class="col-12">
					<div id="curve_chart" style="width: 100%; height: 500px"></div>
				</div>
				<div class="col-12">
					<div id="myChart" style="max-width: 100%; height: 400px;"></div>
				</div>
			</div>
		</div>
	</div>

	</main>
	<script src="https://www.gstatic.com/charts/loader.js"></script>

	<!-- <script>
		google.charts.load('current', {
			packages : [ 'corechart' ]
		});
		google.charts.setOnLoadCallback(drawChart);

		// Your Function
		function drawChart() {
			// Set Data
			const data = google.visualization.arrayToDataTable([
					[ 'Thành phần', 'Tỉ trọng' ], <c:forEach var="item" items="${reportMonth}">[ '${item.time}' , ${item.value} , '#3366CC' ],</c:forEach>]);

			// Set Options
			const options = {
					title: 'Thống kê hóa đơn trong 7 ngày', 'width': '100%',
                    'height':300,
			        hAxis: {
			          title: 'Ngày',
			          format:'#,###%'
			        },
			        vAxis: {
			          title: 'Số hóa đơn'
			        }
			};
			// Draw Chart
			const chart = new google.visualization.LineChart(document
					.getElementById('myChart'));
			chart.draw(data, options);
		}
	</script> -->
	
	<script>
    google.charts.load('current', {'packages':['line']});
    google.charts.setOnLoadCallback(drawChart);

function drawChart() {
 
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'Loại');
	<c:forEach var="entry" items="${mapCate}">  
		data.addColumn('number', "${entry.value}"); 
	</c:forEach>


	 data.addRows([
	      <c:forEach var="item" items="${reportMonth}" varStatus="loop">
	        ['${item.time}'
	          <c:forEach var="entry" items="${mapCate}">
	            , ${item.mapValue[entry.key] != null ? item.mapValue[entry.key] : 0}
	          </c:forEach>
	        ]<c:if test="${!loop.last}">,</c:if>
	      </c:forEach>
	    ]);


    var options = {
            chart: {
              title: 'Số lượng bán được của sản phẩm trong 6 tháng',
              subtitle: 'Sản lượng',        
            },
          };
  var chart = new google.charts.Line(document.getElementById('curve_chart'));

  chart.draw(data, google.charts.Line.convertOptions(options));
}
</script>
</body>
</html>