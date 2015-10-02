<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>SSO Client -- 系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="Modern Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript">
	
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 

</script>
<!-- Bootstrap Core CSS -->
<link href="../css/bootstrap.min.css" rel='stylesheet' type='text/css' />
<!-- Custom CSS -->
<link href="../css/style.css" rel='stylesheet' type='text/css' />
<link href="../css/font-awesome.css" rel="stylesheet">
<!-- logo -->
<link rel="shortcut icon"type="image/x-icon" href="../img/logo.jpg"media="screen" />
<!-- jQuery -->
<script src="../js/jquery.min.js"></script>
<!----webfonts--->

<!---//webfonts--->
<!-- Bootstrap Core JavaScript -->
<script src="../js/bootstrap.min.js"></script>
</head>
<body>
	<div id="wrapper">
		<!-- Navigation -->
		<jsp:include page="index.jsp"></jsp:include>
		<div id="page-wrapper">
			<div class="col-md-12 graphs">
				<div class="xs">

					<div class="bs-example4" data-example-id="contextual-table">
						<table class="table">
							<thead>
								<tr>
									<th>#</th>
									<th>系统名称</th>
									<th>链接地址</th>
									<th>使用单点登录</th>
									<th>删除</th>
								</tr>
							</thead>
							<tbody>
<!-- 								<tr> -->
<!-- 									<th scope="row">1</th> -->
<!-- 									<td>system name</td> -->
<!-- 									<td>link</td> -->
<!-- 									<td>change</td> -->
<!-- 									<td >delete</td>
<!-- 								</tr> -->
							</tbody>
						</table>
					</div>
					<jsp:include page="footer.jsp"></jsp:include>
				</div>
			</div>
		</div>
	</div>
	<!-- /#wrapper -->
	<!-- Nav CSS -->
	<link href="../css/custom.css" rel="stylesheet">
	<!-- Metis Menu Plugin JavaScript -->
	<script src="../js/metisMenu.min.js"></script>
	<script src="../js/custom.js"></script>
	<script type="text/javascript" src="../js/Client_sys.js"></script>
</body>
</html>

