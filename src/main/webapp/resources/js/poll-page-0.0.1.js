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
	/* resize banners to fill banner container */
	resizeBanners();
	$(window).resize(resizeBanners);
	
	/* program answer variants with relational info to hide/show relation info block */
	$(".on-choose-relative-info").each(function() {
		/* Assume that relative info provided for radio and checkboxes only */
		var relativeInfo = $(this);
		var prev = $(this).prev();
		if (prev.is(":radio")) {
			var radioName = prev.attr('name');
			//check type of quotes in name and construct request
			var radiosSearchRequest = radioName.indexOf("'") == -1 ? "input[name='" + radioName + "']" : 'input[name="' + radioName + '"]';
			$(radiosSearchRequest).each(function() {
				$(this).on('change', function() {
					if (prev.is(":checked")) {
						relativeInfo.slideDown();
					} else {
						relativeInfo.slideUp();
					}
				});
			});
			
		} else {
			prev.on('change', function() {
				if (prev.is(":checked")) {
					relativeInfo.slideDown();
				} else {
					relativeInfo.slideUp();
				}
			});
		}
		if (!prev.is(":checked")) relativeInfo.hide();
	});
	
	/* validation for required answers */
	$('.submit-button').click(function() {
		var correct = true;
		$('.required textarea').each(function() {
			if (!$(this).val().trim()) {
				var questionName = $(this).prev('h3').text().split(':')[0];
				alert('Запитання "' + questionName + '" обов’язкове для заповнення');
				correct = false;
			}
		});
		return correct;
	});
});