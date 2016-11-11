function praise(id){
	var value = parseInt($('#btnDigg dd').text());
	$.ajax({
		url: 'blog/praise',
		type: 'post',
		data: {"bid":id},
		success : function (result){
			if(result == true){
				$('#btnDigg dd').text(value+1);
				swal("赞+1");
			} else {
				sweetAlert("Oops...", "点赞失败了，稍后再试吧", "error");
			}
		},
		error: function(){
			sweetAlert("Oops...", "出错了，稍后再试吧", "error");
		}
	});
}

function notpraise(id){
	var value = parseInt($('#btnBury dd').text());
	$.ajax({
		url: 'blog/notpraise',
		type: 'post',
		data: {"bid":id},
		success : function (result){
			if(result == true){
				$('#btnBury dd').text(value+1);
				swal("踩+1");
			} else {
				sweetAlert("Oops...", "踩失败了，稍后再试吧", "error");
			}
		},
		error: function(){
			sweetAlert("Oops...", "出错了，稍后再试吧", "error");
		}
	});
}

function childReply(e,pid,puid){
	var nickname = e.title;
	$('#replyform').find('.only').remove();
	$('#replyform').find('.puid').remove();
	$('.bookreply label').remove();
	$('#replyform').append('<input class="only" type="hidden" name="bfid" value="'+pid+'">');
	$('#replyform').append('<input class="puid" type="hidden" name="puid" value="'+puid+'">');
	$('.bookreply .img').before('<label style="color:#f00;">RE：'+nickname+'(请直接在下面输入框回复)'
			+'<a class="cancel" href="javascript:;" style="float:right;" title="取消回复" onclick="cancel();">取消</a></label>');
}

function cancel(){
	$('.bookreply label').remove();
	$('#replyform').find('.only').remove();
	$('#replyform').find('.puid').remove();
}






























