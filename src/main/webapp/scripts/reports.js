$(function() {
	$(window).resize(function() {
		$('#grid').setGridWidth($(window).width());
		$('#grid').setGridHeight($(window).height() - 48);
	}).trigger('resize');
	
	var canvas = $('#graph');
	
	if (!canvas.length)
		return;
	
	$.getJSON('budget/list', function(data) {
		var moments = data.map(function(element) {
			return element.momentString;
		});
		var values = data.map(function(element) {
			return element.balance;
		});
		var data = {
			labels : moments,
			datasets : [ {
				fillColor : "rgba(172,194,132,0.4)",
				strokeColor : "#ACC26D",
				pointColor : "#fff",
				pointStrokeColor : "#9DB86D",
				data : values
			} ]
		};
		new Chart(canvas.get(0).getContext('2d')).Line(data, {
			responsive : true
		});
	});
});