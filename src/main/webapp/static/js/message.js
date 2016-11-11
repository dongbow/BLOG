$(document).ready(message());

function message(){
	$.ajax({
		url : 'message/ajax',
		type : "POST",
		dataType : 'text',
		contentType : "application/json",
		success : function (count){
			if(count != 0){
				$('#message span').css('background','#f00');
				$('#msgtip').html(
						'<a href="/space/${user.uid}/message">'
						+'<span>你有'+count+'条新消息，点击查看</span></a>');
				$('#header_panel').css("left",'74%');
				$('#header_panel').css('width','170px');
				$('#msgtip').show();
			}else{
				$('#message span').css('background','#09f');
				$('#msgtip').html('');
				$('#header_panel').css("left",'79%');
				$('#header_panel').css('width','76px');
				$('#msgtip').hide();
			}
			$('#m_count').text(count);
		},
		error: function () { 
			$('#message span').css('background','#09f');
			$('#msgtip').html('');
			$('#header_panel').css("left",'79%');
			$('#header_panel').css('width','76px');
			$('#msgtip').hide();
			$('#m_count').text('0');
		},
		async: true
	});
	setTimeout("message()", 1000*60*2);
}