let cerrarSesion = function() {
	sessionStorage.removeItem("token");
	const info = {
		type: 'CerrarSesion',
		userName: sessionStorage.userName
	};

	const data = {
		data: JSON.stringify(info),
		url: 'cerrarSesion',
		type: 'post',
		contentType: 'application/json',
		success: function() {
			window.location.href = 'index.html';
		},
		error: function(response) {
			alert('NO SE PUDO CERRAR SESION');
		}
	};
	$.ajax(data);
};
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
	var url = "wss://" + window.location.host + "/SIGETEquipo1";
	self.sws = new WebSocket(url);

	self.sws.onopen = function(event) {

		var msg = {
			type: "leer",
			nombre: sessionStorage.userName,
			vista: "gestionUsuarios"
		};
		self.sws.send(JSON.stringify(msg));
	}

self.sws.onmessage = function(event) {
			
			
			var data = event.data;
			data = JSON.parse(data);
			var users = data.usuarios;
			

			for (var i = 0; i < users.length; i++) {
				var usuario = users[i];
				if (self.listaUsuarios().some(u => u.name === usuario.name) === false) {
					self.listaUsuarios.push(new Usuario(usuario.name, usuario.email, usuario.password, usuario.rol));
				}
				if (sessionStorage.userName === usuario.name) {
					if(usuario.rol === "ASISTENTE"){
					   document.getElementById('username').placeholder = sessionStorage.userName;
					}
				}
				
			}


			for (var j = 0; j < users.length; j++) {
				var usuario = users[j];
				if (usuario.name === self.nombreUsuario()) {

					document.getElementById('username').placeholder = usuario.name;
					
				}
			}




			self.modificarUsuario = function() {
				let nombre;
				if (self.nombreUsuario() === '') {
					nombre = sessionStorage.userName;
				} else {
					nombre = self.nombreUsuario();
				}
				var p = {
					type: "modificar",
					nombre: nombre,
					pwd: document.getElementById("pwdn").value,
					email: document.getElementById("email").value
				};
				self.sws.send(JSON.stringify(p));

			}
			
			self.ascender = function() {
				var p = {
					type: "ascender",
					nombre: self.nombreUsuario()
					
				};
				self.sws.send(JSON.stringify(p));

			}



		}
		/*
		 * self.eliminarUsuario = function() {
		 * 
		 * 
		 * const info = { type: 'eliminar', nombre: $('#actividad').val(), usuario:
		 * document.getElementById("eliminar").options[document.getElementById("eliminar").selectedIndex].text,
		 * success : function() { sessionStorage.userName = $('#username').val();
		 * alert('Se ha eliminado correctamente'); }, error : function(response) {
		 * 
		 * alert('No se ha eliminado'); } }; self.sws.send(JSON.stringify(info)); };
		 */
		class Usuario {
			constructor(name, email, password, rol) {
				this.name = name;
				this.email = email;
				this.password = password;
				this.rol = rol;
			}
			eliminarUsuario() {
				var p = {
					type: "eliminar",
					nombre: this.name
				};
				self.sws.send(JSON.stringify(p));
				for (var i = 0; i < self.listaUsuarios().length; i++) {
					if (self.listaUsuarios()[i].nombre === this.nombre)
						self.listaUsuarios.splice(i, 1);
				}
			}

			infoUsuarios() {
				var p = {
					type: "infoUsuarios",
					nombre: this.name
				};
				self.nombreUsuario(this.name);
				self.sws.send(JSON.stringify(p));

			}



		}
	
}
var vm = new ViewModel();
ko.applyBindings(vm);
