function add(report) {
	var path = report + '/add';
	$('#dialog').load(path, function() {
		widgets();
		$('#dialog').dialog('open');
	});
}

function update(report) {
	var id = $('#grid').getGridParam('selrow');
	if (id == null)
		return;
	var path = report + '/update/' + id;
	$('#dialog').load(path, function() {
		widgets();
		$('#dialog').dialog('open');
	});
}

function del(report) {
	var id = $('#grid').getGridParam('selrow');
	if (id == null)
		return;
	var path = report + '/delete/' + id;
	$(location).attr('href', path);
}

function widgets() {
	$('.datePicker').datepicker({
		dateFormat : 'dd.mm.yy',
		showButtonPanel : true,
		changeMonth : true,
		changeYear : true
	});

	$('.integerSpinner').spinner({
		min : 0,
		step : 1
	});
}

$(function() {
	$('#dialog').dialog({
		autoOpen : false,
		width : 'auto'
	});

	widgets();

	$(window).resize(function() {
		$('#grid').setGridWidth($(window).width());
		$('#grid').setGridHeight($(window).height() - 52);
	}).trigger('resize');
	
	Chart.defaults.global.responsive = true;
	var chart = new Chart(document.getElementById('graph').getContext('2d'));
	
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
		chart.Line(data);
	});
});