var url = "ws://"+window.location.host+"/SIGETEquipo1";
var sws = new WebSocket(url); 
	
sws.onopen = function(event) {
	var msg = {
		type : "ready"
	};
	sws.send(JSON.stringify(msg));
}

sws.onmessage = function(event) {
	var data= event.data;
	data = JSON.parse(data);
	
	if(data.rol=="ADMIN"){
		window.location.href="admin.html";
	}
	if(data.rol=="ASISTENTE"){
		window.location.href="usuario.html";
	}
	if(data.rol=="ORGANIZADOR") {
		window.location.href="organizador.html";
	}

}

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
		},
		error : function(response) {
			
			alert('LOGIN INCORRECTO');
		}
	};
	$.ajax(data);
};
