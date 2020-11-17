let checkAccess = function() {
	const info = {
		type: 'CheckAccess',
		userName: sessionStorage.userName,
		token: sessionStorage.token,
		page: window.location.href
	};

	const data = {
		data: JSON.stringify(info),
		url: 'checkAccess',
		type: 'post',
		contentType: 'application/json',
		success: function() {
		},
		error: function(response) {
			sessionStorage.clear();
			window.location.href = 'index.html';
		}
	};
	$.ajax(data);
};
var self;
function ViewModel() {
	self = this;
	self.listaUsuarios = ko.observableArray([]);
	self.nombreUsuario = ko.observable("");
	self.usuariosSeleccionados = ko.observableArray([]);
	var url = "wss://"+window.location.host+"/SIGETEquipo1";
	self.sws = new WebSocket(url); 
	
	self.sws.onopen = function(event) {
		var msg = {
			type : "leer",
			nombre: sessionStorage.userName,
			vista: "addactividad"
		};
		self.sws.send(JSON.stringify(msg));
	}

	self.sws.onmessage = function(event) {
		var data= event.data;
		data = JSON.parse(data);
		var users = data.usuarios;
		for (var i = 0; i < users.length; i++) {
			var usuario = users[i];
			if(self.listaUsuarios().some(u=> u.name === usuario.name ) === false){
			self.listaUsuarios.push(new Usuario(usuario.name, usuario.email, usuario.password, usuario.rol));
			}
		}
	}

self.aniadirActividad  = function() {
	self.usuariosSeleccionados.push($('#select').val());
	var dateInicio = $('#horaInicio').val().split(":");
	var dateFinal =$('#horaFinal').val().split(":");
	
	const info = {
		type: 'insertar',
		nombre: $('#actividad').val(),
		dia : document.getElementById("dia").options[document.getElementById("dia").selectedIndex].text,
		horaInicio: dateInicio[0],
		horaFinal: dateFinal[0],
		minutoInicio: dateInicio[1],
		minutoFinal:dateFinal[1],
		usuarios: document.getElementById("select").options[document.getElementById("select").selectedIndex].text,
		success : function() {
			sessionStorage.userName = $('#username').val();
			alert('Se ha creado correctamente');
		},
		error : function(response) {
			
			alert('Se ha creado incorrectamente');
		}
	};
	self.sws.send(JSON.stringify(info));
};

class Usuario {
    constructor(name, email, password, rol){
    	this.name = name;
    	this.email = email;
    	this.password = password;
    	this.rol = rol;
    }
}
}
var vm = new ViewModel();
ko.applyBindings(vm);