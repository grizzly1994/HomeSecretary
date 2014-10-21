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
});