function change(img){
	img.src="getcode?"+new Date().getTime();
}  

var flag=true; //标记位
function FocusItem(obj){
	if($(obj).attr('name') == 'veryCode'){
		$(obj).next().next("span").html('').removeClass('error');
	}else{
		$(obj).next("span").html('').removeClass('error'); 
		flag=true;
	}
}
function CheckItem(obj){
	var msgBox=$(obj).next('span');
	
	switch($(obj).attr('name')){
		case "userName":
			if(obj.value == ""){
				msgBox.html('用户名ID不能为空');
				msgBox.addClass('error');
				flag=false;
			}

//			alert('userName');
			break;
		case "userPassword":
			if(obj.value ==""){
				msgBox.html('密码不能为空');
				msgBox.addClass('error');
				flag=false;
			}else{
				flag=true;
			}
			break;

		case "veryCode":
			
			var numshow =$(obj).next().next();
			
			if(obj.value == ""){
				numshow.html('验证码不能为空');
				numshow.addClass('error');
				flag=false;
			}else{
				var url="checkusernum?num="+encodeURI($(obj).val())+"&"+new Date().getTime();
				$.get(url, function(data){
//					alret(data);
					if(data =="false"){
						numshow.html("验证码输入有误!");
						numshow.addClass('error');
						flag=false;
					}
					if(data =="true"){
						numshow.html("验证码正确");
						numshow.addClass('error');
						flag=true;

					}
				});
			}
			break;
		
	}
}

function checkForm(frm){
	var els=frm.getElementsByTagName('input');
	//onblur 这个属性的才是需要验证的
	
	for(var i=0;i<els.length;i++){
		
		if(els[i] !=null){
			if(els[i].getAttribute("onblur")){
				CheckItem(els[i]);
			}
		}
	}
	return flag;
}

