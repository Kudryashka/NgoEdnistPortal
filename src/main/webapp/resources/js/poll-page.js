function resizeBanners() {
	console.log("Resize banners")
	var banner_container = $('#banner-container');
	var banner_container_width = banner_container.width();
	
	var banners = $('#banner-container img');
	var bannersMargins = 0;
	banners.each(function() {
		var leftMargin = parseInt($(this).css('margin-left'));
		var rightMargin = parseInt($(this).css('margin-right'));
		bannersMargins+=(leftMargin + rightMargin);
	});
	
	var banner_calc_width = (banner_container_width - bannersMargins) / banners.length;
	banner_calc_width = Math.floor(banner_calc_width);
	
	banners.each(function() {
		$(this).css('width', banner_calc_width);
	});
}

$(document).ready(function() {
	resizeBanners();
	$(window).resize(resizeBanners);
});