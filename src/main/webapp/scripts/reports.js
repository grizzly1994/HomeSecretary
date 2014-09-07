$(function() {
    // Для корректной работы плагина jqGrid у каждой таблицы должен быть уникальный id
    var count = 0;
    $(".reportTable").each(function() {
        $(this).attr("id", "report" + count++);
    });
    
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
    $('.datePicker').datepicker({
        dateFormat : 'dd.mm.yy',
        showButtonPanel : true,
        changeMonth : true,
        changeYear : true
    });
    
    $('.decimalSpinner').spinner({
        min : 0,
        step : 10,
        numberFormat : "n",
        culture : "en-US"
    });
    
    $('.integerSpinner').spinner({
        min : 0,
        step : 1
    });
});
