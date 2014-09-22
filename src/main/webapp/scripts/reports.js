$(function() {
    // Устанавливаем плагины
    $('#reports').tabs();
    $('#dialog').dialog({autoOpen : false, width : 'auto'});
    
    // Таблица меняет размер вместе с окном браузера
    function fit() {
        $('.reportGrid').setGridWidth($(window).width());
        $('.reportGrid').setGridHeight($(window).height() - 190);
    }
    
    $('.ui-tabs-anchor').bind('click', fit);
    $(window).bind('resize', fit);
            
    fit();
    
    //Виджеты форм
    function widgets() {
        $('.datePicker').datepicker({
            dateFormat : 'dd.mm.yy',
            showButtonPanel : true,
            changeMonth : true,
            changeYear : true
        });
    
        $('.decimalSpinner').spinner({
            min : 0,
            step : 10,
            numberFormat : 'n',
            culture : 'en-US'
        });
    
        $('.integerSpinner').spinner({
            min : 0,
            step : 1
        });
    }
    
    // Действия с записями
    $('.add').bind('click', function() {
    	var report = $(this).attr('data-report');
    	var path = 'reports/' + report + 'add';
    	$('#dialog').load(path, function() {
    		widgets();
    		$('#dialog').dialog('open');
    	});
    });
    
    $('.edit').bind('click', function() {
    	var report = $(this).attr('data-report');
    	var id = $('#' + report + 'Grid').getGridParam('selrow');
    	if (id == null)
    		return;
    	var path = 'reports/' + report + '/edit/' + id;
    	$('#dialog').load(path, function() {
    		widgets();
    		$('#dialog').dialog('open');
    	});
    });
    
    $('.remove').bind('click', function() {
    	var report = $(this).attr('data-report');
    	var id = $('#' + report + 'Grid').getGridParam('selrow');
    	if (id == null)
    		return;
    	var path = 'reports/' + report + '/remove/' + id;
    	$(location).attr('href', path);
    });
});
