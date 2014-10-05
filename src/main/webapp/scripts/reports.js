$(function() {
	
    // Устанавливаем плагины
	
    $('#dialog').dialog({autoOpen : false, width : 'auto'});
    
    // Таблица меняет размер вместе с окном браузера
    
    function fit() {
	    $('#grid').setGridWidth($(window).width());
	    $('#grid').setGridHeight($(window).height() - 52);
    }
    
    $(window).on('resize', fit);
            
    fit();
    
    //Виджеты форм
    
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
    
    widgets();
    
    // Действия с записями
    
    function add(report) {
    	var path = report + '/add';
    	$('#dialog').load(path, function() {
    		widgets();
    		$('#dialog').dialog('open');
    	});
    }
    
    $('#addPay').on('click', function() {
    	add('pay');
    });
    
    $('#addDebt').on('click', function() {
    	add('debt');
    });
    
    $('.update').on('click', function() {
    	var report = $(this).attr('data-report');
    	var id = $('#' + report + 'Grid').getGridParam('selrow');
    	if (id == null)
    		return;
    	var path = report + '/update/' + id;
    	$('#dialog').load(path, function() {
    		widgets();
    		$('#dialog').dialog('open');
    	});
    });
    
    $('.delete').on('click', function() {
    	var report = $(this).attr('data-report');
    	var id = $('#' + report + 'Grid').getGridParam('selrow');
    	if (id == null)
    		return;
    	var path = report + '/delete/' + id;
    	$(location).attr('href', path);
    });
});
