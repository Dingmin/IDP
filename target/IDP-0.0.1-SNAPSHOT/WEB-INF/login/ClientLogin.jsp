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
SSO系统负责人登录
</div>
	<div class="login">
		<form action='loginCheck' method='post'>
		邮&nbsp;&nbsp;&nbsp;箱<input type='text' name='email' class="username" placeholder='邮箱' autofocus  ><br>
		密&nbsp;&nbsp;&nbsp;码<input type="password" name='pwd' class="pwd" placeholder='密码至少6位'><br>
		<button type="button">Submit</button>
		<p>还没账号？去<a href="sign">注册</a></p>
		</form>
	</div>
</div>	
	<div id="footer">&copy;版权 SSO  designed by Carrot-Ding</div>	

	
</body>
<script type="text/javascript" src="../js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="../js/index.js"></script>
</html>