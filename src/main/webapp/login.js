let login  = function() {
	const info = {
		type: 'Login',
		userName: $('#username').val(),
		pwd: $('#password').val()
	};

	const data = {
		data: JSON.stringify(info),
		url: 'login',
		type: 'post',
		contentType: 'application/json',
		/*success : function() {
			window.location.href='perfil.html';
		},
		*/
		error() {
			alert('LOGIN INCORRECTO');
		}
	};
	$.ajax(data);
};
