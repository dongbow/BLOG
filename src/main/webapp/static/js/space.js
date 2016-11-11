$(function(){
	
	$('.allmessage').click(function(){
		var chk = $(this).find(':checkbox');
		if(chk.prop('checked')){
			chk.removeAttr('checked','checked');
		}else{
			chk.prop('checked','checked');
		}
	});
	
	$('.userimage').mouseover(function(){
		$('.showtip').addClass('show');
	});
	
	$('.userimage').mouseout(function(){
		$('.showtip').removeClass('show');
	});
	
});