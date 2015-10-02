<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<nav class="top1 navbar navbar-default navbar-static-top"
	role="navigation" style="margin-bottom: 0">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-collapse">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="index.html">SSO</a>
	</div>
	<!-- /.navbar-header -->
	<ul class="nav navbar-nav navbar-right">
		<li class="dropdown"><a href="#" class="dropdown-toggle avatar"
			data-toggle="dropdown">${admin}</a>
			<ul class="dropdown-menu">
				<li class="m_2"><a href="logout"><i class="fa fa-lock"></i>
						Logout</a></li>
			</ul></li>
	</ul>
	<div class="navbar-default sidebar" role="navigation">
		<div class="sidebar-nav navbar-collapse">
			<ul class="nav" id="side-menu">

				<li><a href="index"><i class="fa fa-laptop fa-fw nav_icon"></i>
						系统</a></li>
				<li><a href="#"><i class="fa fa-user fa-fw nav_icon"></i>用户<span
						class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="sysuser">系统负责人</a></li>
						<li><a href="user">普通用户</a></li>
						<li><a href="man">管理员</a></li>
					</ul> <!-- /.nav-second-level --></li>
				<li><a href="conn"><i
						class="fa fa-dashboard fa-fw nav_icon"></i> 信任关系</a></li>
				<li><a href="#"><i class="fa fa-plus fa-fw nav_icon"></i>添加<span
						class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="addsys">系统</a></li>

						<li><a href="adduser">普通用户</a></li>

						<li><a href="add_admin">管理员</a></li>
						<li><a href="addsys_user">系统负责人</a></li>
						<li><a href="addconn">信任关系</a></li>
					</ul> <!-- /.nav-second-level --></li>
				<li><a href="logout"><i
						class="fa fa-plane fa-fw nav_icon"></i>Logout<span
						class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="logout">退出</a></li>

					</ul> <!-- /.nav-second-level --></li>
			</ul>
		</div>
		<!-- /.sidebar-collapse -->
	</div>
	<!-- /.navbar-static-side -->
</nav>