$(function(){
var focus_width = 290
var focus_height = 200
var text_height = 20
var swf = 'images/pixviewer.swf';
var swf_height = focus_height + text_height

var pics = 'images/slideimage/photo8.jpg|images/slideimage/photo1.jpg|images/slideimage/photo2.jpg|images/slideimage/photo3.jpg|images/slideimage/photo4.jpg';
var links = '';//'链接1|链接2|链接3|链接4';
var texts = '';//'显示文字1|显示文字2|显示文字3|显示文字4';

var obj = '';
obj += ('<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0" width="'
				+ focus_width + '" height="' + swf_height + '">');
obj += ('<param name="allowScriptAccess" value="sameDomain"><param name="movie" value="'+swf+'"><param name="quality" value="high"><param name="bgcolor" value="#DADADA">');
obj += ('<param name="menu" value="false"><param name=wmode value="opaque">');
obj += ('<param name="FlashVars" value="pics=' + pics + '&links='
				+ links + '&texts=' + texts + '&borderwidth=' + focus_width
				+ '&borderheight=' + focus_height + '&textheight='
				+ text_height + '">');
obj += ('<embed src="'+swf+'" wmode="opaque" FlashVars="pics='
				+ pics
				+ '&links='
				+ links
				+ '&texts='
				+ texts
				+ '&borderwidth='
				+ focus_width
				+ '&borderheight='
				+ focus_height
				+ '&textheight='
				+ text_height
				+ '" menu="false" bgcolor="#DADADA" quality="high" width="'
				+ focus_width
				+ '" height="'
				+ swf_height
				+ '" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer"/>');
obj += ('</object>');
$('#content_img').html(obj);
});