var x = -1;
var y = -1;
var sid1 = -1;
var sid2 = -1;
AddUserSys();
$(function() {

	$('form input[name=linkB]').keypress(
			function(evt) {
				var cc = (evt.charCode) ? evt.charCode
						: ((evt.which) ? evt.which : evt.keyCode);
				if (cc == 13)
					return false;
				if (cc != 32) {
					AddUserSys2($('form input[name=linkB]').val());
				}
			});
	$('form input[name=linkB]').blur(
			function() {
				var text = $('form input[name=linkB]').val();
				if (text != "" && text != null) {
					$("#bb option[value='" + text + "']")
							.attr("selected", true);
					$("#bb option[value='" + text + "']").siblings().attr(
							"selected", false);
					var tar = $('#bb option:selected').attr("title");
					if (tar != "") {
						var resu = tar.split("_");
						y = resu[1];
						sid2 = resu[0];
					} else {
						y = -1;
						sid2 = -1;
					}
					check();
				} else
					y = -1;

			});
	$('form input[name=linkA]').blur(
			function() {
				var text = $('form input[name=linkA]').val();
				if (text != "" && text != null) {
					$("#aa option[value='" + text + "']")
							.attr("selected", true);
					$("#aa option[value='" + text + "']").siblings().attr(
							"selected", false);
					var tar = $('#aa option:selected').attr("title");
					if (tar != "") {
						var resu = tar.split("_");
						x = resu[1];
						sid1 = resu[0];
					} else {
						x = -1;
						sid1 = -1;
					}
					check();
				} else
					x = -1;

			});

	$('.btn-primary').click(function() {
		$('form input[name=linkA]').trigger('blur');
		$('form input[name=linkB]').trigger('blur');
		//console.log(x + "  " + y);
		if ($('form input[name=linkA]').val().trim() == "") {
			$('form input[name=linkA]').focus();
			return false;
		}
		if ($('form input[name=linkB]').val().trim() == "") {
			$('form input[name=linkB]').focus();
			return false;
		}
		if (sid1 != -1 && sid1 != -1 && sid1 == sid2) {
			alert("两个系统不能为同一系统");
			return false;
		}
		if (x == -1) {
			alert("找不到系统A");
			return false;
		}
		if (y == -1) {
			alert("找不到系统B");
			return false;
		}
		var A = $('#aa option:selected').attr("title").split("_")[0];
		var B = $('#bb option:selected').attr("title").split("_")[0];
		if (A == B) {
			alert("A,B不能为同一个系统");
			return false;
		} else {
			var url = "addConn";
			var flag;
			if ($('form input:checkbox:checked').val() == "on")
				flag = 1;
			else {
				flag = 0;
			}
			var args = {
				"sid_1" : sid1,
				"sid_2" : sid2,
				"flag" : flag
			};
			$.ajax({
				cache : false,
				type : "POST",
				url : url,
				data : args,
				async : false,
				error : function(request) {
					alert("该信任关系已存在");
				},
				success : function(data) {
					if (data) {
						alert("添加成功");
						$('form :button:reset').trigger('click');
						$('form input:checkbox').attr('checked', false);
						$('form input:checkbox').attr('disabled', true);
					} else {
						alert("离开太久啦，刷新下界面吧~");
					}
				}
			});
		}

	});
});

function AddUserSys(name) {
	var url = "findUserSys";
	var args = {
		"name" : name
	};
	$.post(url, args, function(data) {

		//console.log(data);
		$("#aa ").empty();
		if (data != null && data.length != 0) {
			data.forEach(function(a) {
				$("#aa ").append(
						'<option title="' + a.sid + "_" + a.userid
								+ '" value="' + a.sys_link + "(" + a.sysName
								+ ")" + '">' + '</option>');
			});
		}

	});
}
function AddUserSys2(name) {
	var url = "findUserSys";
	var args = {
		"name" : name
	};
	$.post(url, args, function(data) {

		if (data != null && data.length != 0) {
			$("#bb ").empty();
			data.forEach(function(a) {

				$("#bb").append(
						'<option title="' + a.sid + "_" + a.userid
								+ '" value="' + a.sys_link + "(" + a.sysName
								+ ")" + '">' + '</option>');
			});
		}

	});
}

function check() {
	if (x != -1
			&& y != -1
			&& x == y
			&& $('form input[name=linkB]').val() != $('form input[name=linkA]')
					.val()) {
		if (x == y)
			$('form input:checkbox').attr('checked', true);
		$('form input:checkbox').attr('disabled', false);
	} else {
		$('form input:checkbox').attr('checked', false);
		$('form input:checkbox').attr('disabled', true);
	}
}
