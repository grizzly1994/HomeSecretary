$(function() {
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
	
	$('#update').bind('click', function() {
		var report = $(this).attr('data-report');
		var id = $('#grid').getGridParam('selrow');
		if (id == null)
			return;
		var path = report + '/update/' + id;
		$(location).attr('href', path);
	});
	
	$('#delete').bind('click', function() {
		var report = $(this).attr('data-report');
		var id = $('#grid').getGridParam('selrow');
		if (id == null)
			return;
		var path = report + '/delete/' + id;
		$(location).attr('href', path);
	});
});

function View(selector, url) {
	
	this.grid = function(colNames, colModel) {
		$(selector).jqGrid({
			url : url,
			datatype : 'json',
			mtype : 'POST',
			colNames : colNames,
			colModel : colModel,
			pager : "#tools",
			viewrecords : true,
			gridview : true,
			loadonce: true,
			sortable: true
		});
		$(selector).jqGrid('navGrid', '#tools',
			{edit : false, add : false, del : false}, {}, {}, {},
			{multipleSearch : true, multipleGroup : true}
		);
		$(window).resize(function() {
			$(selector).setGridWidth($(window).width());
			$(selector).setGridHeight($(window).height() - 74);
		}).trigger('resize');
	}
	
	this.graph = function() {
		$.post(url, function(data) {
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
			new Chart($(selector).get(0).getContext('2d')).Line(data, {
				responsive : true
			});
		});
	}
}