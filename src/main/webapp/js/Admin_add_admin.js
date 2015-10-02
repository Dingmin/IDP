var flag = false;
clear();
$(function() {
	$('form input[name=realName]').blur(function() {
		
		if ($('form input[name=realName]').val() == "") {
			$('form input[name=realName]').focus();
			return false;
		}
	});
	$('form input[name=password]').blur(
			function() {
				if ($('form input[name=password]').val() == ""
						|| $('form input[name=password]').val().length < 6) {
					$('form input[name=password]').focus();
					return false;
				}
			});
	$('form input[name=phone]').blur(function() {
		if ($('form input[name=phone]').val() == "") {
			$('form input[name=phone]').focus();
			return false;
		} else {
			checkToken();
		}
	});
	$(".btn-primary").click(
			function() {
				$('form input[name=phone]').trigger("blur");
				$('form input[name=realName]').trigger('blur');
				$('form input[name=password]').trigger('blur');
				if (flag && $('form input[name=realName]').val() != ""
						&& $('form input[name=password]').val() != ""
						&& $('form input[name=password]').val().length > 5){
					var url ="../common/addAdmin";
					var args=$('form').serialize();
					$.ajax({
						cache : false,
						type : "POST",
						url : url,
						data :args,
						async : false,
						error : function(request) {
							alert("服务器出现问题了，稍后操作吧~");
						},
						success : function(data) {
							if(data){
								alert("添加成功");
								$('form :button:reset').trigger('click');
							}
							else{
								alert("添加失败");
							}
						}
					});
				}
					
			});
});

function clear() {
	$('.btn-default').trigger('click');
}
function checkToken() {
	var url = "../common/checkAdminPhone";
	var args = {
		"name" : $('form input[name=phone]').val()
	};
	$.post(url, args, function(data) {
		if (!data) {
			alert("手机号已占用");
			$('form input[name=phone]').focus();
			return false;
		} else {
			flag = true;
		}
	});
}