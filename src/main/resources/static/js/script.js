( function( $ ) {
$( document ).ready(function() {
$('#cssmenu').prepend('<div id="menu-button">Menu</div>');
	$('#cssmenu #menu-button').on('click', function(){
		var menu = $(this).next('ul');
		if (menu.hasClass('open')) {
			menu.removeClass('open');
		}
		else {
			menu.addClass('open');
		}
	});
});
$.get("/dict",{'dictType':'图片'},function(data){
	var dictPage = data.data;
	var $ul = $("<ul></ul>");
	for (var i = 0;i<dictPage.records.length;i++){
		$ul.append("<li><a href='/picture/index?picType="+dictPage.records[i].dictName+"'>"+dictPage.records[i].dictName+"</a>");
	}
	$("#dict").append($ul);
});
} )( jQuery );
