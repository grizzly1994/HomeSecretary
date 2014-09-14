$(function() {
    // Устанавливаем плагины
    $('#reports').tabs();
    $('#dialog').dialog({autoOpen : false, width : 'auto'});
    
    // Таблица меняет размер вместе с окном браузера
    function fit() {
        $('.reportGrid').setGridWidth($(window).width());
        $('.reportGrid').setGridHeight($(window).height() - 200);
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
    
    // Отображение диалога
    function edit() {
    	var action = $(this).attr('class');
    	var report = $(this).attr('data-report');
    	var id = $('#' + report + 'Grid').getGridParam('selrow');
    	if (action == 'add' || id == null)
    		id = -1;
    	var path = 'reports/edit/' + report + '/' + id;
        $('#dialog').load(path, function() {
            widgets();
            $('#dialog').dialog('open');
        });
    }
    
    $('.add').bind('click', edit);
    $('.edit').bind('click', edit);
    
    $('.remove').bind('click', function() {
    	var report = $(this).attr('data-report');
    	var id = $('#' + report + 'Grid').getGridParam('selrow');
    	if (id == null)
    		return;
    	var path = 'reports/remove/' + report + '/' + id;
    	$(location).attr('href', path);
    });
});
