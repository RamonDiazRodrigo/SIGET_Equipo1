var self;
function ViewModel() {
	self = this;
	self.listaUsuarios = ko.observableArray([]);
	self.nombreUsuario = ko.observable("");
	self.usuariosSeleccionados = ko.observableArray([]);
	var url = "ws://"+window.location.host+"/SIGETEquipo1";
	self.sws = new WebSocket(url); 
	

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
	
	self.check  = function() {

		
		var dateInicio = $('#horaInicio').val().split(":");
		var dateFinal =$('#horaFinal').val().split(":");
		
		const info = {
			type: 'check',
			nombre: $('#actividad').val(),
			dia : document.getElementById("dia").options[document.getElementById("dia").selectedIndex].text,
			horaInicio: dateInicio[0],
			horaFinal: dateFinal[0],
			minutoInicio: dateInicio[1],
			minutoFinal:dateFinal[1]
		};
		self.sws.send(JSON.stringify(info));
	};

self.addReunion  = function() {

	for (var i = 0; i < self.listaUsuarios().length; i++) {
		if(document.getElementsByClassName("form-check-input")[i].checked === true){
			self.usuariosSeleccionados.push(document.getElementsByClassName("form-check-label")[i].innerHTML);
			// self.usuariosSeleccionados.push(document.getElementById("select").options[document.getElementById("select").selectedIndex].text);
	    }
	}
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
		usuarios: self.usuariosSeleccionados(),
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