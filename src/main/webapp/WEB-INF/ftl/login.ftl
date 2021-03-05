<!DOCTYPE html>
<html lang="zh">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
	<title>软帝OA</title>
	<link href="../../css/bootstrap.min.css" rel="stylesheet">
	<link href="../../css/materialdesignicons.min.css" rel="stylesheet">
	<link href="../../css/style.min.css" rel="stylesheet">
	<style>
		.lyear-wrapper {
			position: relative;
		}
		.lyear-login {
			display: flex !important;
			min-height: 100vh;
			align-items: center !important;
			justify-content: center !important;
		}
		.lyear-login:after{
			content: '';
			min-height: inherit;
			font-size: 0;
		}
		.login-center {
			background: #fff;
			min-width: 29.25rem;
			padding: 2.14286em 3.57143em;
			border-radius: 3px;
			margin: 2.85714em;
		}
		.login-center .has-feedback.feedback-left .form-control {
			padding-left: 38px;
			padding-right: 12px;
		}
		.login-center .has-feedback.feedback-left .form-control-feedback {
			left: 0;
			right: auto;
			width: 38px;
			height: 38px;
			line-height: 38px;
			z-index: 4;
			color: #dcdcdc;
		}
		.login-center .has-feedback.feedback-left.row .form-control-feedback {
			left: 15px;
		}
	</style>
</head>

<body>
<div class="row lyear-wrapper" style="background-image: url(../../images/login-bg-2.jpg); background-size: cover;">
	<div class="lyear-login">
		<div class="login-center">
			<form action="#!" method="post">
				<div class="form-group has-feedback feedback-left">
					<input type="text" placeholder="请输入您的用户名" class="form-control" name="username" id="username" />
					<span class="mdi mdi-account form-control-feedback" aria-hidden="true"></span>
				</div>
				<div class="form-group has-feedback feedback-left">
					<input type="password" placeholder="请输入密码" class="form-control" id="password" name="password" />
					<span class="mdi mdi-lock form-control-feedback" aria-hidden="true"></span>
				</div>
				<#--	验证码	-->
				<div id="verifyPart" class="form-group has-feedback feedback-left row" hidden="true">
					<div class="col-xs-7">
						<input id="j_captcha" type="text" name="j_captcha" class="form-control" placeholder="验证码">
						<span class="mdi mdi-fingerprint form-control-feedback" aria-hidden="true"></span>
					</div>
					<div class="col-xs-5">
						<img src="/Kaptcha" class="pull-right" id="captcha" style="cursor: pointer;" onclick="chageVerificationCode(this)" title="点击刷新" alt="captcha">
					</div>
				</div>
				<div class="form-group">
					<a id="submit" class="btn btn-block btn-primary" type="button" href="#">立即登录</a>
				</div>
			</form>
			<hr>
			<footer class="col-sm-12 text-center">
				<p class="m-b-0">Copyright © 2021 <a href="https://rd.ke.qq.com/">软帝在线</a>. All right reserved</p>
			</footer>
		</div>
	</div>
</div>
<script type="text/javascript" src="/js/jquery.min.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/admin/login.js"></script>
<!--消息提示-->
<script src="/js/bootstrap-notify.min.js"></script>
<script type="text/javascript" src="/js/lightyear.js"></script>
<script type="text/javascript">;</script>
</body>
</html>