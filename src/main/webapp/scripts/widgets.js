$(function() {
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
