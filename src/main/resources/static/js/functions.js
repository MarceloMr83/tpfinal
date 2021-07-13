$(document).ready(function(){

	var total = Number($("#cost").val());
	$("#total").val(total);

	//formato de fecha
	$.datepicker.regional['es'] = {
		closeText: 'Cerrar',
		prevText: '< Ant',
		nextText: 'Sig >',
		currentText: 'Hoy',
		monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
		monthNamesShort: ['Ene','Feb','Mar','Abr', 'May','Jun','Jul','Ago','Sep', 'Oct','Nov','Dic'],
		dayNames: ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'],
		dayNamesShort: ['Dom','Lun','Mar','Mié','Juv','Vie','Sáb'],
		dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','Sá'],
		weekHeader: 'Sm',	
		dateFormat: 'dd-mm-yy',
		firstDay: 1,
		isRTL: false,
		showMonthAfterYear: false,
		yearSuffix: ''
	};
	$.datepicker.setDefaults($.datepicker.regional['es']);

	//deshabilitar los disa anteriores del calendario
	$(function() {
		$("#from").datepicker({
			minDate: 0
		});
		$("#to").datepicker({
			minDate: 0
		});	  

		$("#birthDate").datepicker({
			maxDate: 0,
			changeYear: true,
			yearRange: "1930:2010"
		});

		
	});

	$('#parking').mousedown(function() {
		if (!$(this).is(':checked')) {
			total=total + 200;
			$("#total").val(total);
		}
		else{
			total=total - 200;
			$("#total").val(total);
		}
	});

	$('#breakfastIncluded').mousedown(function() {
		if (!$(this).is(':checked')) {
			total=total + 500;
			$("#total").val(total);
		}
		else{
			total=total - 500;
			$("#total").val(total);
		}
	});

	$('#freeCancelation').mousedown(function() {
		if (!$(this).is(':checked')) {
			total=total + 100;
			$("#total").val(total);
		}
		else{
			total=total - 100;
			$("#total").val(total);
		}
	});

});
     
  