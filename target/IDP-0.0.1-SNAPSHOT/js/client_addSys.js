$(function(){
	$('form .sub').click(function(){
	//	alert("dfg");
		sub();
	});
});

function sub(){
	 var sys_name = $('form').find('input[name=sys_name]').val();
	 var sys_link = $('form').find('input[name=sys_link]').val();
	 if(sys_name==null||sys_name==""){		 
		 $('form').find('input[name=sys_name]').focus();
		 return false;
	 }
	
	var sso =  $('form :input:checkbox:checked').val();
	if(sso=="on") sso=1;
	else sso=0;
	var args={"sys_name":sys_name,"sys_link":sys_link,"sso":sso};
	$.ajax({
		cache : false,
		type : "POST",
		url : "addSystem",
		data :args,
		async : false,
		error : function(request) {
			alert("该系统链接已存在");
		},
		success : function(data) {
			if(data){
				alert("添加成功");
				$('form :button:reset').trigger('click');
			}
			else{
				alert("离开太久啦，刷新下界面吧~");
			}
		}
	});
	
}

function check(){
	var sys_link = $('form').find('input[name=sys_link]').val();
	 if(sys_link==null||sys_link==""){
		 $('form').find('input[name=sys_link]').focus();
		 return false;
		 
	 }
	else{
		var x =new RegExp('((^http)|(^https)):\/\/(\\w)+\.(\\w)+');
		if(!x.test(sys_link)){
			alert("url不合法");
			$('form').find('input[name=sys_link]').focus();
			return false;
		}
		sub();
//			
//	 $.ajax({
//	        type: "GET",
//	        cache: false,
//	        url: sys_link,
//	        data: "html",
//	        success: function() {
//	        sub();
//	        },
//	        error: function() {
//	            alert("访问不到该系统地址");
//	        }
//	    });
	 }
}