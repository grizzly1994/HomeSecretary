$(function() {
    // Для корректной работы плагина jqGrid у каждой таблицы должен быть уникальный id
    var count = 0;
    $(".reportTable").attr("id", "report" + count++);
    
    // Устанавливаем плагины
    tableToGrid(".reportTable");
    $('#reports').tabs();
    $('.dialog').dialog({autoOpen : false, width : 'auto'});
    
    // Таблица меняет размер вместе с окном браузера
    function fit() {
        $(".reportTable").setGridWidth($(window).width());
        $(".reportTable").setGridHeight($(window).height() - 230);
    }
            
    $(".ui-tabs-anchor").bind('click', fit);
    $(window).bind('resize', fit);
            
    fit();
    
    // Отображение диалога
    function edit() {
        $($(this).attr("href") + "Edit").dialog("open");
    }
    
    $(".add").bind('click', edit);
    $(".edit").bind('click', edit);
    
    // Виджеты для текстовых полей
    $('.datePicker').each(function() {
        $(this).datepicker({
            dateFormat : 'dd.mm.yy',
            showButtonPanel : true,
            changeMonth : true,
            changeYear : true
        });
    });
    
    $('.decimalSpinner').each(function() {
        $(this).spinner({
            min : 0,
            step : 10,
            numberFormat : "n",
            culture : "en-US"
        });
    });
    
    $('.integerSpinner').each(function() {
        $(this).spinner({
            min : 0,
            step : 1
        });
    });
});
