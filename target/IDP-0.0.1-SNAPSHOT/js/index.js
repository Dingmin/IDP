var name=false;
var password=false;
var sub=false;
var mit=false;

$(function(){
$('.username').bind('input propertychange', function() {
	var username=$('.username').val();
	if(username!=null&&username!=""&&username.length<=20&&username!='用户名/邮箱'){
		 name=true;
		  
	}else{
		name=false;
		sub=false;
	}
	checkIf();
});
$('.pwd').bind('input propertychange', function() {
	//console.log("sdafas");
	var pwd=$('.pwd').val();
	if(pwd!=null&&pwd!=""&&pwd.length>=6&&pwd.length<=13&&pwd!='密码至少6位'){
		password=true;
	}else {
		password=false;
		sub=false;
	}
	checkIf();
});
$(".username").keypress(function(evt){
  var cc = (evt.charCode) ? evt.charCode : ((evt.which) ? evt.which : evt.keyCode);
    if(cc==32||cc==32)
      return false;
  if($('.username').val().length>19){return false;}
  });
$(".pwd").keypress(function(evt){
  var cc = (evt.charCode) ? evt.charCode : ((evt.which) ? evt.which : evt.keyCode);

    if(cc==32)
      return false;
  if(cc==13){
  	checkIf();
  	if(sub){
  		$('button').trigger("click");
  	}
  }
   if($('.pwd').val().length>12){return false;}
  });
	$('button').click(function(){
		if(!mit&&sub){
			mit=true;
		//console.log('submit the form');
		$('form').submit();
	}
	});
});
function checkIf(){
	if(!sub){
		if(name&&password){
			sub=true;
		 $('button').css('background','#148EFF');
		 //console.log('could submit the form ');
		}
		else{
			 $('button').css('background','#B8B8B8');
		}
	}
}