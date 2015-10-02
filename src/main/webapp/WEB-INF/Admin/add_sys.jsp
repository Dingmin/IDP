<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>后台管理 -- 添加系统</title>
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
					<h3>Add System</h3>
					<div class="well1 white">
						<form
							class="form-floating ng-pristine ng-invalid ng-invalid-required ng-valid-email ng-valid-url ng-valid-pattern"
							novalidate="novalidate" >
							<fieldset>
								<div class="form-group">
									<label class="control-label">系统名称</label> <input type="text" name="sys_name"
										class="form-control1 ng-invalid ng-invalid-required ng-touched"
										ng-model="model.name" required="" >
									
								</div>
								<div class="form-group">
									<label class="control-label">系统链接</label> <input type="url" name="sys_link"
										class="form-control1 ng-invalid ng-valid-email ng-invalid-required ng-touched"
										ng-model="model.email" required="" placeholder="url">
								</div>
<div class="form-group">
									<label class="control-label">所属负责人</label> <input type="text" name="client"
										class="form-control1 ng-invalid ng-invalid-required ng-touched"
										ng-model="model.name" required="" placeholder="输入关键字：用户名/邮箱/手机" list="sys">
										<datalist id="sys">
										</datalist>
								</div>
								<div class="form-group">
									<div class="checkbox1">
										<label> <input type="checkbox" ng-model="model.winner"
											required="" class="ng-invalid ng-invalid-required" checked>
											使用单点登录
										</label>
									</div>
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
	<script type="text/javascript" src="../js/Admin_add_sys.js"></script>
</body>
</html>

