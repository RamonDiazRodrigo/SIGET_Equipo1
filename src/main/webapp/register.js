let register = function() {
	const info = {
		type : 'Register',
		userName : $('#username').val(),
		email : $('#email').val(),
		pwd : $('#pwd1').val(),
		pwd2 : $('#pwd2').val(),
		rol : $('#rol').val()
	};
	const data = {
			data : JSON.stringify(info),
			url : 'register',
			type : 'post',
			contentType: 'application/json',
			error() {
				alert('REGISTER INCORRECTO');
			}
	};
	$.ajax(data);
};