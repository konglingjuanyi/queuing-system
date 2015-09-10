$(document).ready(function () {
	$(function(){  //生成验证码         
	    $('#kaptchaImage').click(function () {  
	    $(this).hide().attr('src', '/code/captcha-image?' + Math.floor(Math.random()*100) ).fadeIn(); });      
	});   
	 
	window.onbeforeunload = function(){  
	    //关闭窗口时自动退出  
	    if(event.clientX>360&&event.clientY<0||event.altKey){     
	        alert(parent.document.location);  
	    }  
	};  
	  
	$('#changeKaptcha').click(function() {  //刷新
	    $('#kaptchaImage').hide().attr('src', '/code/captcha-image?' + Math.floor(Math.random()*100) ).fadeIn();  
//	    event.cancelBubble=true;  
	});
});
 
