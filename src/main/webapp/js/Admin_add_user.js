var flag = false;
clear();
$(function() {
	$('form input[name=username]').blur(function() {
		if ($('form input[name=username]').val() == "") {
			$('form input[name=username]').focus();
			return false;
		}
	});
	$('form input[name=pwd]').blur(
			function() {
				if ($('form input[name=pwd]').val() == ""
						|| $('form input[name=pwd]').val().length < 6) {
					$('form input[name=pwd]').focus();
					return false;
				}
			});
	$('form input[name=token]').blur(function() {
		if ($('form input[name=token]').val() == "") {
			$('form input[name=token]').focus();
			return false;
		} else {
			checkToken();
		}
	});
	$(".btn-primary").click(
			function() {
				$('form input[name=token]').trigger("blur");
				$('form input[name=username]').trigger('blur');
				$('form input[name=pwd]').trigger('blur');
				if (flag && $('form input[name=username]').val() != ""
						&& $('form input[name=pwd]').val() != ""
						&& $('form input[name=pwd]').val().length > 5){
					var url ="../common/addUser";
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
	var url = "../common/checkUserToken";
	var args = {
		"name" : $('form input[name=token]').val()
	};
	$.post(url, args, function(data) {
		if (!data) {
			alert("Token已占用");
			$('form input[name=token]').focus();
			return false;
		} else {
			flag = true;
		}
	});
}