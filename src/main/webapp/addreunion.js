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
	self.nombreUsuario = ko.observable('');
	self.usuariosSeleccionados = ko.observableArray([]);
	var url = "wss://" + window.location.host + "/SIGETEquipo1";
	self.sws = new WebSocket(url);

	self.sws.onmessage = function(event) {
		var data = event.data;
		data = JSON.parse(data);
		self.listaUsuarios.removeAll();
		var users = data;
		for (var i = 0; i < users.length; i++) {
			var usuario = users[i];
			if (self.listaUsuarios().some(u => u.name === usuario.name) === false) {
				self.listaUsuarios.push(new Usuario(usuario.name, usuario.email, usuario.password, usuario.rol));
			}
		}
	};


	self.check = function() {
		var dateInicio = $('#horaInicio').val().split(":");
		var dateFinal = $('#horaFinal').val().split(":");

		const info = {
			type: 'check',
			nombre: $('#actividad').val(),
			dia: document.getElementById("dia").options[document.getElementById("dia").selectedIndex].text,
			horaInicio: dateInicio[0],
			horaFinal: dateFinal[0],
			minutoInicio: dateInicio[1],
			minutoFinal: dateFinal[1]
		};
		self.sws.send(JSON.stringify(info));
	};

	self.addReunion = function() {
		for (var i = 0; i < self.listaUsuarios().length; i++) {
			if (document.getElementsByClassName("form-check-input")[i].checked === true) {
				self.usuariosSeleccionados.push(document.getElementsByClassName("form-check-label")[i].innerHTML);
			}
		}
		var dateInicio = $('#horaInicio').val().split(":");
		var dateFinal = $('#horaFinal').val().split(":");

		const info = {
			type: 'convocarReunion',
			nombre: $('#actividad').val(),
			dia: document.getElementById("dia").options[document.getElementById("dia").selectedIndex].text,
			horaInicio: dateInicio[0],
			horaFinal: dateFinal[0],
			minutoInicio: dateInicio[1],
			minutoFinal: dateFinal[1],
			usuarios: self.usuariosSeleccionados(),
			success: function() {
				alert('Se ha creado correctamente');
			},
			error: function() {

				alert('Se ha creado incorrectamente');
			}
		};
		self.sws.send(JSON.stringify(info));
	};

	class Usuario {
		constructor(name, email, password, rol) {
			this.name = name;
			this.email = email;
			this.password = password;
			this.rol = rol;
		}
	}
}
var vm = new ViewModel();
ko.applyBindings(vm);