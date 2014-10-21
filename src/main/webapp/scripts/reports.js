$(function() {
	$('#dialog').dialog({
		autoOpen : false,
		width : 'auto'
	});
	
	function widgets() {
		$('.datePicker').datepicker({
			dateFormat : 'yy.mm.dd',
			showButtonPanel : true,
			changeMonth : true,
			changeYear : true
		});

		$('.integerSpinner').spinner({
			min : 0,
			step : 1
		});
	}
	
	widgets();
	
	$('.add').bind('click', function() {
		var report = $(this).attr('data-report');
		var path = report + '/add';
		$('#dialog').load(path, function() {
			widgets();
			$('#dialog').dialog('open');
		});
	});

	$('#update').bind('click', function() {
		var report = $(this).attr('data-report');
		var id = $('#grid').getGridParam('selrow');
		if (id == null)
			return;
		var path = report + '/update/' + id;
		$('#dialog').load(path, function(response, status) {
			if (status == 'error')
				return;
			widgets();
			$('#dialog').dialog('open');
		});
	});

	$('#delete').bind('click', function() {
		var report = $(this).attr('data-report');
		var id = $('#grid').getGridParam('selrow');
		if (id == null)
			return;
		var path = report + '/delete/' + id;
		$(location).attr('href', path);
	});
	
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