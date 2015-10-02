var e = false;
var p = false;

$(function() {
	$('.username').blur(function() {
		if ($('.username').val().trim() == "") {
			$('.username').focus();
		} 
	});
	$('.email').blur(function() {
		if ($('.email').val().trim() == "") {
			$('.email').focus();
		} else {
			check($('.email').val());

		}
	});
	$('.pwd').blur(function() {
		if (($('.pwd').val()).length < 6) {
			alert("密码至少6位");
			$('pwd').focus();
		} else {
			$('.con_pwd').focus();
		}
	});
	$('.con_pwd').bind('input propertychange', function() {
		if ($('.con_pwd').val().length > 0) {
			$('form button').css('background', '#148EFF');
		} else {
			$('form button').css('background', '#B8B8B8');
		}
	});
	$('form button').click(function() {
		if ($('.pwd').val() != $('.con_pwd').val()) {
			alert("两次密码不一致");
		} else {
			// send...
			if(!e){
				alert("账号ID已占用");
				$('.email').focus();
			}
			else{
				
					var url = "sign";
					var args = $('form').serialize();
					$.post(url, args, function(data) {
						if (data) {
							$("#reg").fadeOut();
							$("#sign_up").fadeIn("slow");
						}
					});
				}
			
			

		}
	});
});
$("form input").keypress(
		function(evt) {
			var cc = (evt.charCode) ? evt.charCode : ((evt.which) ? evt.which
					: evt.keyCode);
			////console.log("cc" + cc);
			if (cc == 13||cc==32)
				return false;
		});
function check(name) {
	var url = "checkToken";
	var args = {
		"name" : name
	};
	$.post(url, args, function(data) {
		if (data) {
			$('.phone').focus();
			e=true;
		} else {
			alert("账号ID已占用");
			$('.email').focus();
			e=false;
		}
	});
}
