<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>后台管理 -- 添加系统负责人</title>
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
<link
	href='http://fonts.useso.com/css?family=Roboto:400,100,300,500,700,900'
	rel='stylesheet' type='text/css'>
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
					<h3>系统负责人</h3>
					<div class="well1 white">
						<form
							class="form-floating ng-pristine ng-invalid ng-invalid-required ng-valid-email ng-valid-url ng-valid-pattern"
							novalidate="novalidate" ng-submit="submit()">
							<fieldset>
								<div class="form-group">
									<label class="control-label">用户名</label> <input type="text" name="username"
										class="form-control1 ng-invalid ng-invalid-required ng-touched"
										ng-model="model.name" required="">
								</div>
								<div class="form-group">
									<label class="control-label">密码 </label> <input
										type="password" name="password"
										class="form-control1 ng-invalid ng-invalid-required ng-touched"
										ng-model="model.password" required="" placeholder="至少6位数">
								</div>
								<div class="form-group">
									<label class="control-label">邮箱</label> <input type="email" name="email"
										class="form-control1 ng-invalid ng-valid-email ng-invalid-required ng-touched"
										ng-model="model.email" required="">
								</div>
								<div class="form-group">
									<label class="control-label">手机号</label> <input type="text" name="phone"
										class="form-control1 ng-invalid ng-valid-email ng-invalid-required ng-touched"
										ng-model="model.phone" required="">
								</div>
								
								<div class="form-group">
									<button type="button" class="btn btn-primary">Submit</button>
									<button type="reset" class="btn btn-default">Reset</button>
								</div>
							</fieldset>
						</form>
					</div>
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
	<script type="text/javascript" src="../js/Admin_add_client.js"></script>
</body>
</html>

