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
	var args = {
		'index' : index
	};
	$
			.ajax({
				cache : false,
				type : "POST",
				url : "findSys",
				data : args,
				async : false,
				error : function(request) {
					alert("服务器出现问题了，稍后操作吧~");
				},
				success : function(data) {
					//console.log(data);
					if (data == null || data.length == 0) {
						if(index==0)
							alert("无系统记录");
						//console.log("无系统记录");
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
															+ a.sid
															+ '">'
															+ i
															+ '</span></td><td>'
															+ a.sys_name
															+ '</td>'
															+ '<td><span>'
															+ a.sys_link
															+ '</span></td>'
															+ '<td>'+a.sso+'</td>'
															+ '<td><span class="del"><a  href="delSys?index='
															+ a.sid
															+ '"'
															+ '">delete</a></span></td></tr>');
								});

					}
				}
			});
}