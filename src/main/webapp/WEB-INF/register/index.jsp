<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>user register</title>
	<link rel="stylesheet" type="text/css" href="css/index.css">
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="shortcut icon"type="image/x-icon" href="../img/logo.jpg"media="screen" />
</head>
<body>
<div id="first">
<div class="sign">
用户注册
</div>
	<div class="login" id="reg">
		<form action='#' method='post'>

     	 用户名&nbsp;&nbsp;&nbsp;<input type='text' name='username' class="username"
		 placeholder='用户名' autofocus  ><br>
		帐号ID&nbsp;&nbsp;&nbsp;<input type='text' name='token' class="email"
		 placeholder='邮箱/学号...' autofocus  ><br>
		密&nbsp;&nbsp;&nbsp;码&nbsp;&nbsp;&nbsp;<input type="password" name='pwd' class="pwd" placeholder='密码至少6位'><br>
		确认密码<input type="password" name='con_pwd' class="con_pwd" placeholder='重复输入密码'><br>
		<button type="button">Submit</button>
		</form>
	</div>
	<div  id="sign_up" style="display:none;">
	<br>
		<p>注册成功</p>
		
	</div>
</div>	
	<div id="footer">&copy;版权 SSO  designed by Carrot-Ding</div>	

	
</body>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/user_sign.js"></script>
</html>