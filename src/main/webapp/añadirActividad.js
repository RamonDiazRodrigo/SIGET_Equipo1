var self;

function ViewModel() {
	self = this;
	self.listaUsuarios = ko.observableArray([]);
	self.nombreUsuario = ko.observable("");


	var url = "ws://"+window.location.host+"/SIGETEquipo1";
	self.sws = new WebSocket(url); 
	
	self.sws.onopen = function(event) {
		var msg = {
			type : "ready"
		};
		self.sws.send(JSON.stringify(msg));
	}

	self.sws.onmessage = function(event) {
		var data= event.data;
		data = JSON.parse(data);
		
		// Listar usuarios
		var users = data.usuarios;
		for (var i = 0; i < users.length; i++) {
			var usuario = users[i];
			if(self.listaUsuarios().some(u=> u.name === usuario.name ) === false){
			self.listaUsuario.push(new Usuario(usuario.name, usuario.email, usuario.password, usuario.rol));
			
			}
		}
	}


let añadirActividad  = function() {
	const info = {
		type: 'insertarActividad',
		actividad: $('#actividad').val(),
		dateInicio: $('#dateinicio').val(),
		dateFinal: $('#datefinal').val(),
		usuario: document.getElementsById("select").text
	};

	const data = {
		data: JSON.stringify(info),
		url: 'administrador',
		type: 'post',
		contentType: 'application/json'
	};
	$.ajax(data);
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