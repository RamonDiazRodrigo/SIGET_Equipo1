$('#visibilityHidden').click(function(e) {
	if ($('#hide-me').css('visibility') != 'hidden') {
		$('#hide-me').css('visibility', 'hidden');
	} else {
		$('#hide-me').css('visibility', 'visible');
	}
	$('#visibilityHidden').css('visibility', 'hidden');
});

$('#volver').click(function(e) {
	$('#hide-me').css('visibility', 'hidden');
	$('#visibilityHidden').css('visibility', 'visible');
});

$('#Reunion').click(function(e) {
	if ($('#hide_reunion').css('visibility') != 'hidden') {
		$('#hide_reunion').css('visibility', 'hidden');
	} else {
		$('#hide_reunion').css('visibility', 'visible');
	}
	$('#Reunion').css('visibility', 'hidden');
});

$('#volverReunion').click(function(e) {
	$('#hide_reunion').css('visibility', 'hidden');
	$('#Reunion').css('visibility', 'visible');
});

$('#btnModificarUsuario').click(function(e) {
	$('#gestionUsuarios').css('visibility', 'hidden');
	$('#modificarCredenciales').css('visibility', 'visible');
});


