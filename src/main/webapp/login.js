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
		success : function() {
			window.location.href="admin.html";
		},
		error : function(response) {
			
			alert('LOGIN INCORRECTO');
		}
	};
	$.ajax(data);
};
