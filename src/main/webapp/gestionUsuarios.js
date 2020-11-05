var self;
function ViewModel() {
	self = this;
	self.listaUsuarios = ko.observableArray([]);
	self.nombreUsuario = ko.observable("");
	self.usuariosSeleccionados = ko.observableArray([]);
	var url = "ws://"+window.location.host+"/SIGETEquipo1";
	self.sws = new WebSocket(url); 
	
	self.sws.onopen = function(event) {
		var msg = {
			type : "leer",
			nombre: sessionStorage.userName
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
    constructor(name, email, password, rol){
    	this.name = name;
    	this.email = email;
    	this.password = password;
    	this.rol = rol;
    }
    eliminarUsuario() {
    	var p = {
                type : "eliminar",
                nombre: this.name
            };
            self.sws.send(JSON.stringify(p));
            for (var i = 0; i < self.listaUsuarios().length; i++) {
            	if(self.listaUsuarios()[i].nombre === this.nombre)
            	self.listaUsuarios.splice(i, 1);
            }
    }
    
}
}
var vm = new ViewModel();
ko.applyBindings(vm);/**
						 * 
						 */