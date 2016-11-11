/*	
	var username=$('#username').val();
	var nickname=$('#nickname').val();
	var password=$('#password').val();
	var rpassword=$('#rpassword').val();
	var mail=$('#mail').val();
	var validatacode = $('#validatacode').val();
	var usernameregex = /[^a-zA-Z0-9]/g;
	var nicknameregex = /^[A-Za-z0-9_\\-\\u4e00-\\u9fa5]+$/;
	var mailregex = /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/;
	

*/
$(function(){

	$('.verifycode').click(function(){
		$('.verifycode img').attr('src','validateCode.img?version='+new Date().getTime());
	});
	
	$('#username').blur(function(){
		var usernameregex = /[^a-zA-Z0-9]/g;
		if($(this).val() == null || $(this).val() == ''){
			$('.errorcontent').html('登录名不能为空');
			$(this).addClass('_error');
			return false;
		}else if($(this).val().length<3|| $(this).val().length>20){
			$('.errorcontent').html('登录名长度限制为3-20位');
			$(this).addClass('_error');
			return false;
		}else if($(this).val().match(usernameregex)){
			$('.errorcontent').html('登录名不合法');
			$(this).addClass('_error');
			return false;
		}else{
			$.ajax({
				url : 'account/dataExits',
				type : "get",
				dataType : 'text',
				data : {data:$(this).val()},
				contentType : "application/json",
				beforeSend : function(){
					$('.errorcontent').html('正在验证，请稍后...');
				},
				success : function (result){
					if(result == 'true'){
						$('.errorcontent').html('');
						$('#username').removeClass('_error');
						return true;
					}else{
						$('.errorcontent').html('登录名已存在');
						$(this).addClass('_error');
						return false;
					}
				}
			});
		}
	});
	
	$('#nickname').blur(function(){
		var regex = /^[A-Za-z0-9_\\u4e00-\\u9fa5]+$/;
		if($(this).val() == null || $(this).val() == ''){
			$('.errorcontent').html('昵称不能为空');
			$(this).addClass('_error');
			return false;
		}else if($(this).val().length<3|| $(this).val().length>20){
			$('.errorcontent').html('昵称长度限制为3-20位');
			$(this).addClass('_error');
			return false;
		}else if($(this).val().match(regex)){
			$('.errorcontent').html('昵称不合法');
			$(this).addClass('_error');
			return false;
		}else{
			$.ajax({
				url : 'account/dataExits',
				type : "get",
				dataType : 'text',
				data : {data:$(this).val()},
				contentType : "application/json",
				beforeSend : function(){
					$('.errorcontent').html('正在验证，请稍后...');
				},
				success : function (result){
					if(result == 'true'){
						$('.errorcontent').html('');
						$('#nickname').removeClass('_error');
						return true;
					}else{
						$('.errorcontent').html('昵称已存在');
						$(this).addClass('_error');
						return false;
					}
				}
			});
		}
	});
	
	$('#password').blur(function(){
		var password = $(this).val();
		if(password == '' || password == null){
			$(this).addClass('_error');
			$('.errorcontent').html('密码不能为空');
			return false;
		}else if(password.length<6||password.length>16){
			$(this).addClass('_error');
			$('.errorcontent').html('密码长度限制为6-16位');
			return false;
		}else{
			$('.errorcontent').html('');
			$(this).removeClass('_error');
			return true;
		}
	});
	
	$('#rpassword').blur(function(){
		var password = $('#password').val();
		var rpassword = $(this).val();
		if(rpassword == '' || rpassword == null){
			$(this).addClass('_error');
			$('.errorcontent').html('密码不能为空');
			return false;
		}else if(rpassword.length<6||rpassword.length>16){
			$(this).addClass('_error');
			$('.errorcontent').html('密码长度限制为6-16位');
			return false;
		}else if(rpassword != password){
			$(this).addClass('_error');
			$('.errorcontent').html('两次密码不一致');
			return false;
		}else{
			$('.errorcontent').html('');
			$(this).removeClass('_error');
			return true;
		}
	});
	
	$('#mail').blur(function(){
		var mail = $(this).val();
		var mailregex = /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/;
		if(mail == '' || mail == null){
			$('.errorcontent').html('邮箱不能为空');
			return false;
		}else if(mail.match(mailregex)){
			$('.errorcontent').html('邮箱不合法');
			return false;
		}else{
			$.ajax({
				url : 'account/dataExits',
				type : "get",
				dataType : 'text',
				data : {data:$(this).val()},
				contentType : "application/json",
				beforeSend : function(){
					$('.errorcontent').html('正在验证，请稍后...');
				},
				success : function (result){
					if(result == 'true'){
						$('.errorcontent').html('');
						$('#mail').removeClass('_error');
						return true;
					}else{
						$('.errorcontent').html('昵称已存在');
						$(this).addClass('_error');
						return false;
					}
				}
			});
		}
	});
	
	$('#validatacode').blur(function(){
		if($(this).val() == null || $(this).val() == ''){
			$('.errorcontent').html('验证码不能为空');
			$(this).addClass('_error');
			return false;
		}else if($(this).val().length != 4){
			$('.errorcontent').html('验证码只能为4位');
			$(this).addClass('_error');
			return false;
		}else{
			$.ajax({
				url : 'verifycode',
				type : "GET",
				data : {verifyCode:$(this).val()},
				contentType : "application/json",
				beforeSend : function(){
					$('.errorcontent').html('正在验证，请稍后...');
				},
				success : function (result){
					if(result == true){
						$('.errorcontent').html('');
						$(this).removeClass('_error');
						return true;
					}else{
						$('.errorcontent').html('验证码错误');
						$(this).addClass('_error');
						return false;
					}
				}
			});
		}
	});
	
});

function submitForm(){
	$('#username').trigger('blur');
	$('#nickname').trigger('blur');
	$('#password').trigger('blur');
	$('#rpassword').trigger('blur');
	$('#mail').trigger('blur');
	$('#validatacode').trigger('blur');
	
	var number = $('._error').length;
	if(number!=0){
		return false;
	}else{
		$('#reform').submit();
	}
}