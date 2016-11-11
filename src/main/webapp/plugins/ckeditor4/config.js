/**
 * @license Copyright (c) 2003-2013, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.html or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	config.plugins = 'dialogui,dialog,about,a11yhelp,dialogadvtab,basicstyles,bidi,blockquote,clipboard,button,panelbutton,panel,floatpanel,colorbutton,colordialog,templates,menu,contextmenu,div,resize,toolbar,elementspath,list,indent,enterkey,entities,popup,filebrowser,find,fakeobjects,flash,floatingspace,listblock,richcombo,font,forms,format,htmlwriter,horizontalrule,iframe,wysiwygarea,image,smiley,justify,link,liststyle,magicline,maximize,newpage,pagebreak,pastetext,pastefromword,preview,print,removeformat,save,selectall,showblocks,showborders,sourcearea,specialchar,menubutton,scayt,stylescombo,tab,table,tabletools,undo,wsc,syntaxhighlight';
	config.skin = 'moono';
	config.syntaxhighlight_lang = 'java';
	config.syntaxhighlight_hideControls = true;
	config.language = 'zh-cn';
	config.uiColor = '#FAFAFA';
	config.image_previewText=" ";
	config.width = 940;
	config.height = 400;
	config.shiftEnterMode = CKEDITOR.ENTER_P;
	config.enterMode = CKEDITOR.ENTER_BR; 
	
	//config.smiley_images  =  ['0.gif', '1.gif', '2.gif', '3.gif', '4.gif', '5.gif', '6.gif', '7.gif', '8.gif', '9.gif', '10.gif', '11.gif', '12.gif', '13.gif', '14.gif', '15.gif', '16.gif', '17.gif', '18.gif', '19.gif', '20.gif', '21.gif', '22.gif', '23.gif', '24.gif', '25.gif', '26.gif', '27.gif', '28.gif', '29.gif', '30.gif', '31.gif', '32.gif', '33.gif', '34.gif', '35.gif', '36.gif', '37.gif', '38.gif', '39.gif', '40.gif', '41.gif', '42.gif', '43.gif', '44.gif', '45.gif', '46.gif', '47.gif', '48.gif', '49.gif', '50.gif', '51.gif', '52.gif', '53.gif', '54.gif', '55.gif', '56.gif', '57.gif', '58.gif', '59.gif', '60.gif', '61.gif', '62.gif', '63.gif', '64.gif', '65.gif', '66.gif', '67.gif', '68.gif', '69.gif', '70.gif', '71.gif', '72.gif', '73.gif', '74.gif', '75.gif', '76.gif', '77.gif', '78.gif', '79.gif', '80.gif', '81.gif', '82.gif', '83.gif', '84.gif', '85.gif', '86.gif', '87.gif', '88.gif', '89.gif'];  
	config.filebrowserImageUploadUrl='admin/upload';
	//config.filebrowserUploadUrl = 'upload.do?type=File';  
    //config.filebrowserImageUploadUrl = 'upload.do?type=Image';  
    config.filebrowserFlashUploadUrl = 'admin/upload'; 
	
	config.toolbar = 'reply'; 
	config.toolbar_reply = [ 
		['Smiley']
	];
	
	config.toolbar = 'mood'; 
	config.toolbar_mood = [ 
		['Link','Unlink'],
		['Undo','Redo'], 
		['Blockquote'], 
		['JustifyLeft','JustifyCenter','JustifyRight'],   
		['FontSize'],
		['Bold','Italic','Underline'], 
		['TextColor','BGColor'],
		['Image','Smiley'],
		['Preview', 'Maximize']
	];
	//		['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'], 
	config.toolbar = 'post'; 
	config.toolbar_post = [ 
	    ['Source'],
		['Link','Unlink'],
		['Undo','Redo'], 
		['NumberedList','BulletedList','Blockquote'],   
		['FontSize'],
		['Bold','Italic','Underline','Strike'], 
		['TextColor','BGColor'], 
		['PasteFromWord', '-', 'Table'],
		['Image','Flash','Smiley','SpecialChar'],
		['Syntaxhighlight'],
		['Preview', 'Maximize']
	];
	
	  config.smiley_path= "plugins/ckeditor4/plugins/smiley/self/";  
      config.smiley_columns = 10;  
      config.smiley_images = ['image001.png','image002.png','image003.png','image004.png','image005.png',
                            'image006.png','image007.png','image008.png','image009.png','image010.png', 
                            'image011.png','image012.png','image013.png','image014.png','image015.png',
                            'image016.png','image017.png','image018.png','image019.png','image020.png', 
                            'image021.png','image022.png','image023.png','image024.png','image025.png',
                            'image026.png','image027.png','image028.png','image029.png','image030.png',  
                            'image031.png','image032.png','image033.png','image034.png','image035.png',
                            'image036.png','image037.png','image038.png','image039.png','image040.png',  
                            'image041.png','image042.png','image043.png','image044.png','image045.png',
                            'image046.png','image047.png','image048.png','image049.png','image050.png']; 
      config.smiley_descriptions = [];
};

CKEDITOR.on('dialogDefinition',function(ev){

    var dialogName=ev.data.name;
    var dialogDefinition=ev.data.definition;
    if(dialogName=='image'){
        var tabs = dialogDefinition.contents;
        tabs[3]=null;
    }
    if(dialogName == 'flash'){
    	var tabs = dialogDefinition.contents;
    	tabs[2]=null;
        tabs[3]=null;
    }
    if(dialogName == 'link'){
    	var tabs = dialogDefinition.contents;
    	tabs[2]=null;
        tabs[3]=null;
    }
    if(dialogName == 'table'){
    	var tabs = dialogDefinition.contents;
    	tabs[1]=null;
    }
});
