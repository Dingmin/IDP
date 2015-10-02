<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>sso login</title>
	<link rel="stylesheet" type="text/css" href="../css/index.css">
	<link rel="stylesheet" type="text/css" href="../css/common.css">
	<link rel="shortcut icon"type="image/x-icon" href="../img/logo.jpg"media="screen" />
</head>
<body>
<div id="first">
<div class="content">
身份验证
</div>
	<div class="login">
		<form action='check' method='post'>
		帐&nbsp;&nbsp;&nbsp;号:<input type='text' name='name' class="username" placeholder='用户名/邮箱' autofocus  ><br>
		密&nbsp;&nbsp;&nbsp;码:<input type="password" name='pwd' class="pwd" placeholder='密码至少6位'><br>
		<input type="hidden" name="target" value="${target }">
		<input type="hidden" name="flag" value="${flag }">
		<button type="button">Submit</button>
		</form>
	</div>
</div>	
	<div id="footer">&copy;版权 SSO  designed by Carrot-Ding</div>	

	
</body>
<script type="text/javascript" src="../js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="../js/index.js"></script>
</html>