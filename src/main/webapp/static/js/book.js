$(function(){
	function toCkeditor(){
		var child = CKEDITOR.replace('childreplyeditor', { 
	   		removePlugins: 'elementspath',
	   		resize_enabled: false, 
	   		toolbar: 'reply', 
	   		height: '80px', 
	   		width: '590px',
	   		toolbarLocation:'bottom'
	    });
	}
	
	$('.replyclick').click(function(){
		var id = $(this).attr("id").split('_')[1];
		if($('#childreply_'+id).hasClass('onclick')){
			$('#childreply_'+id+' .cbe').remove();
			$('#childreply_'+id).removeClass('onclick');
		} else{
			$('body').find('.cbe').remove();
			$('body').find('.notclick').removeClass('onclick');
			$('#childreply_'+id+' .img').after(
					'<div class="cbe">'
					+'<script>$("body .replybtn").click(function(){'
					+'var content= CKEDITOR.instances.editor.document.getBody().getHtml();'
					+'if(content != null || content != ""){return true;}else{swal("回复内容不可以为空");return false;}});</script>'
					+'<form id="" action="book/doreply" method="post">'
					+'<input type="hidden" name="bfid" value='+id+'>'
					+'<textarea id="childreplyeditor" name="content" rows="12" cols="93"></textarea>'
					+'<button class="replybtn">回复</button>'
				+'</form>'
			+'</div>');
			toCkeditor();
			$('#childreply_'+id).addClass('onclick');
		}
	});
	
/*	$('body .replybtn').click(function(){
		swal("回复内容不可以为空");
		return false;
	});*/
	
});