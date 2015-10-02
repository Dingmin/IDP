var e = false;
var p = false;
$(function() {
	$('form input[name=username]').blur(function() {
		if ($('form input[name=username]').val().trim() == "") {
			$('form input[name=username]').focus();
		} 
	});
	$('form input[name=email]').blur(function() {
		if ($('form input[name=email]').val().trim() == "") {
			$('form input[name=email]').focus();
		} else {
			check($('form input[name=email]').val());

		}
	});
	$('form input[name=phone]').blur(function() {
		if ($('form input[name=phone]').val().length != 11) {
			alert("请输入正确的手机号");
			$('form input[name=phone]').focus();
		} else {
			checkphone($('form input[name=phone]').val());
		}

	});
	$('form input[name=password]').blur(function() {
		if ($('form input[name=password]').val().length < 6) {
			alert("密码至少6位");
			$('form input[name=password]').focus();
		} 
	});
	
	$('.btn-primary').click(function() {
		
			if(!e){
				alert("邮箱已占用");
				$('form input[name=email]').focus();
			}
			else{
				if(!p){
					alert("手机号已占用");
					$('form input[name=phone]').focus();
				}
				else{
					var url = "../common/addClient";
					var args = $('form').serialize();
					$.post(url, args, function(data) {
						if (data) {
							alert("添加成功");
							$('.btn-default').trigger('click');
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
			//console.log("cc" + cc);
			if (cc == 13||cc==32)
				return false;
		});
function check(name) {
	var url = "../common/checkClientEmail";
	var args = {
		"name" : name
	};
	$.post(url, args, function(data) {
		if (data) {
			$('form input[name=phone]').focus();
			e=true;
		} else {
			alert("邮箱已占用");
			$('form input[name=email]').focus();
			e=false;
		}
	});
}

function checkphone(name) {

	var url = "../common/checkClientPhone";
	var args = {
		"name" : name
	};
	$.post(url, args, function(data) {
		if (data) {
			$('form input[name=password]').focus();
			p=true;
		} else {
			alert("手机号已占用");
			$('form input[name=phone]').focus();
			p=false;
		}
	});
}