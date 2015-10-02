var index =0;
findReq();

$(window).scroll(function() {
	if ($(document).scrollTop() >= $(document).height() - $(window).height()) {
		//console.log(index);
		findReq();
	}
});

$(function() {

	$(".rev a").click(function() {
		var link = $(this).attr("href");
		var row = $(this).parent().parent().parent();
		row.fadeOut("slow");
		$.post(link, null, function(data) {
			if (data) {
				row.remove();
			}
		});
		return false;
	});
	$(".del a").click(function() {
		var link = $(this).attr("href");
		var target = $(this).parent().parent().parent();
		if (confirm("确定拒绝吗")) {
			target.fadeOut("slow");
			$.post(link, null, function(data) {
				if (data) {
					target.remove();
				}
			});
		}
		return false;
	});

});

function findReq() {
	var url="findConneReq";
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
							alert("无系统信任请求");
						
					} else {
						var i=index;
						index =index+data.length;
						//console.log("sdfas:"+index);
						data
								.forEach(function(a) {
									i++;
									if(a.sso==1){
										a.sso="yes";
									}
									else{
										a.sso="no";
									}
									
									$('.table')
											.append(
													'<tr><td scope="row"><span name="'
															+ a.link_id
															+ '">'
															+ i
															+ '</span></td><td>'
															+ a.sys1+"("+a.link1+")"
															+ '</td>'
															+ '<td><span>'
															+ a.sys2+"("+a.link2+")"
															+ '</span></td>'
															+ '<td>'+a.username+'('+a.email+')'+'</td>'+
															 '<td><span class="rev"><a  href="rev?index='
															+ a.link_id													
															+ '">recieve</a></span></td>'
															+ '<td><span class="del"><a  href="reject?index='
															+ a.link_id															
															+ '">reject</a></span></td></tr>');
								});

					}
				}
			});
}