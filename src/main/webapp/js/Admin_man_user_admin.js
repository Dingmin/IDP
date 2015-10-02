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
				else{
					alert("无权限删除");
				}
			});
		}
		return false;
	});

});

function findSys() {
	var url ="findMan";
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
							alert("无管理员记录");
						//console.log("无管理员记录");
					} else {
						var i=index;
						index =index+data.length;				
						//console.log("sdfas:"+index);
						var roles;
						data
								.forEach(function(a) {
									i++;
									if(a.role==2){
										roles = "超级管理员";
									}
									else{
										roles ="普通管理员";
									}
									$('.table')
											.append(
													'<tr><td scope="row"><span name="'
															+ a.mid
															+ '">'
															+ i
															+ '</span></td><td>'
															+ a.realName
															+ '</td>'
															+ '<td><span>'
															+ a.phone
															+ '</span></td>'
															+ '<td><span>'
															+ roles
															+ '</span></td>'
															+ '<td><span class="del"><a  href="delMan?index='
															+ a.mid+'&role='+a.role
															+ '"'
															+ '">delete</a></span></td></tr>');
								});

					}
				}
			});
}