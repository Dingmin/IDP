$(function() {
	$('.btn-primary').click(function() {
		if ($('form input[name=sys_name]').val() == "") {
			$('form input[name=sys_name]').focus();
			return false;
		}
		var x = new RegExp('((^http)|(^https)):\/\/(\\w)+\.(\\w)+');
		if (!x.test($('form input[name=sys_link]').val())) {
			alert("url不合法");
			$('form input[name=sys_link]').focus();
			return false;
		}
		var sso = $('form :input:checkbox:checked').val();
		if (sso == "on")
			sso = 1;
		else
			sso = 0;
		if ($('#sys option:selected').val() == null) {
			alert("请选择已注册的系统负责人");
			$('form input[name=client]').focus();
			return false;
		}
		var client = $('#sys option:selected').attr("title").split("_")[0];
		//console.log(client);
		var url = "../common/addSys";
		var args = {
			"sso" : sso,
			"sys_link" : $('form input[name=sys_link]').val(),
			"sys_name" : $('form input[name=sys_name]').val(),
			"uid":client
		};
		$.ajax({
			cache : false,
			type : "POST",
			url : url,
			data : args,
			async : false,
			error : function(request) {
				alert("可能服务器太累了。。暂时无法服务");
			},
			success : function(data) {
				if (data) {
					alert("添加成功");
					$('form :button:reset').trigger('click');
					$('form input:checkbox').attr('checked', false);
					
				} else {
					alert("添加系统失败");
				}
			}
		});
	});
	$('form input[name=client]').blur(
			function() {
				var text = $('form input[name=client]').val();
				if (text != "" && text != null) {
					$("#sys option[value='" + text + "']").attr("selected",
							true);
					$("#sys option[value='" + text + "']").siblings().attr(
							"selected", false);
				}
			});
	$('form input[name=client]').keypress(
			function(evt) {
				var cc = (evt.charCode) ? evt.charCode
						: ((evt.which) ? evt.which : evt.keyCode);
				if (cc == 13)
					return false;
				if (cc != 32) {
					AddClient($('form input[name=client]').val());
				}
			});
});

function AddClient(name) {
	var url = "../common/findClient";
	var args = {
		"name" : name
	};
	$.post(url, args, function(data) {

		if (data != null && data.length != 0) {
			$("#sys").empty();
			data.forEach(function(a) {

				$("#sys").append(
						'<option title="' + a.uid + '" value="' + a.username
								+ "(" + a.email + '  ' + a.phone + '  ' + ")"
								+ '">' + '</option>');
			});
		}

	});
}