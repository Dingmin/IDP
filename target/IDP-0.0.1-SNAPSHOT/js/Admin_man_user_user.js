var index =0;
findSys();

$(window).scroll(function() {
	if ($(document).scrollTop() >= $(document).height() - $(window).height()) {
		//console.log(index);
		findSys();
	}
});

$(function() {

	$(".change a").click(function() {
//		var link = $(this).attr("href");
//		var row = $(this).parent().parent().parent();
//		
		return false;
	});
	$(".del a").click(function() {
		var link = $(this).attr("href");
		var target = $(this).parent().parent().parent();
		if (confirm("确定删除吗")) {
			$.post(link, null, function(data) {
				if (data) {
					target.remove();
				}
			});
		}
		return false;
	});

});

function findSys() {
	var url ="findUser";
	var args = {
		'index' : index
	};
	$
			.ajax({
				cache : false,
				type : "POST",
				url : url,
				data : args,
				async : false,
				error : function(request) {
					alert("服务器出现问题了，稍后操作吧~");
				},
				success : function(data) {
					//console.log(data);
					if (data == null || data.length == 0) {
						if(index==0)
							alert("无用户记录");
						//console.log("无用户记录");
					} else {
						var i=index;
						index =index+data.length;
						//console.log("sdfas:"+index);
						data
								.forEach(function(a) {
									i++;
									
									$('.table')
											.append(
													'<tr><td scope="row"><span name="'
															+ a.userid
															+ '">'
															+ i
															+ '</span></td><td>'
															+ a.username
															+ '</td>'
															+ '<td><span>'
															+ a.token
															+ '</span></td>'
															
															+ '<td><span class="del"><a  href="delUser?index='
															+ a.userid
															+ '"'
															+ '">delete</a></span></td></tr>');
								});

					}
				}
			});
}