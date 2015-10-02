$(function() {
	$('.btn-primary').click(function() {
		
		var sso = $('form :input:checkbox:checked').val();
		if (sso == "on")
			sso = 1;
		else
			sso = 0;
		if ($('#A option:selected').val() == null) {
			alert("请选择已存在的系统");
			$('form input[name=linkA]').focus();
			return false;
		}
		if ($('#B option:selected').val() == null) {
			alert("请选择已存在的系统");
			$('form input[name=linkB]').focus();
			return false;
		}
		var link1 = $('#A option:selected').attr("title").split("_")[0];
		var link2 = $('#B option:selected').attr("title").split("_")[0];
		if(link1==link2){
			alert("两个系统不能为同一系统");
			return false;
		}
		var url = "../common/addSysConne";
		var args = {
			"flag" : sso,
			"sid_1" : link1,
			"sid_2" : link2,		
		};
		$.ajax({
			cache : false,
			type : "POST",
			url : url,
			data : args,
			async : false,
			error : function(request) {
				alert("该信任关系已存在或者服务器出现问题");
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
	$('form input[name=linkA]').blur(
			function() {
				var text = $('form input[name=linkA]').val();
				if (text != "" && text != null) {
					$("#A option[value='" + text + "']").attr("selected",
							true);
					$("#A option[value='" + text + "']").siblings().attr(
							"selected", false);
				}
			});
	$('form input[name=linkA]').keypress(
			function(evt) {
				var cc = (evt.charCode) ? evt.charCode
						: ((evt.which) ? evt.which : evt.keyCode);
				if (cc == 13)
					return false;
				if (cc != 32) {
					findLinkA($('form input[name=linkA]').val());
				}
			});
	$('form input[name=linkB]').blur(
			function() {
				var text = $('form input[name=linkB]').val();
				if (text != "" && text != null) {
					$("#B option[value='" + text + "']").attr("selected",
							true);
					$("#B option[value='" + text + "']").siblings().attr(
							"selected", false);
				}
			});
	$('form input[name=linkB]').keypress(
			function(evt) {
				var cc = (evt.charCode) ? evt.charCode
						: ((evt.which) ? evt.which : evt.keyCode);
				if (cc == 13)
					return false;
				if (cc != 32) {
					findLinkB($('form input[name=linkB]').val());
				}
			});
});

function findLinkA(name) {
	var url = "../common/findSys";
	var args = {
		"name" : name
	};
	$.post(url, args, function(data) {

		if (data != null && data.length != 0) {
			$("#A").empty();
			data.forEach(function(a) {

				$("#A").append(	'<option title="' + a.sid + "_" + a.userid
						+ '" value="' + a.sys_link + "(" + a.sysName
						+ ")" + '">' + '</option>');
			});
		}

	});
}

function findLinkB(name) {
	var url = "../common/findSys";
	var args = {
		"name" : name
	};
	$.post(url, args, function(data) {
		//console.log("bbb");
              console.log(data);
		if (data != null && data.length != 0) {
			$("#B").empty();
			data.forEach(function(a) {

				$("#B").append(	'<option title="' + a.sid + "_" + a.userid
						+ '" value="' + a.sys_link + "(" + a.sysName
						+ ")" + '">' + '</option>');
			});
		}

	});
}