var url = "ws://" + window.location.host + "/SIGETEquipo1";
var sws = new WebSocket(url);


sws.onopen = function(event) {
	var msg = {
		type: "infoUsuarios"
	};
	sws.send(JSON.stringify(msg));
};
sws.onmessage = function(event) {
	var data = event.data;
	data = JSON.parse(data);
	sessionStorage.users = data.usuarios;
};

window.onbeforeunload = function() {
	sessionStorage.removeItem("users");
};

let register = function() {

	if (contrasenaValida($('#pwd1').val()) && usernameValido($('#username').val())) {
		const info = {
			type: 'Register',
			userName: $('#username').val(),
			email: $('#email').val(),
			pwd: $('#pwd1').val(),
			pwd2: $('#pwd2').val(),
			rol: $('#rol').val()
		};
		const data = {
			data: JSON.stringify(info),
			url: 'register',
			type: 'post',
			contentType: 'application/json',
			success: function() {
				document.getElementById("pwd1").style.backgroundColor = "green";
				document.getElementById("pwd2").style.backgroundColor = "green";
				document.getElementById("username").style.backgroundColor = "green";
				document.getElementById("email").style.backgroundColor = "green";
				registroCorrecto();
			},
			error: function() {
				document.getElementById("pwd1").style.backgroundColor = "red";
				document.getElementById("pwd2").style.backgroundColor = "red";
			}
		};
		$.ajax(data);
	}
};

function usernameValido(userName) {


	var users = sessionStorage.users;

	for (var j = 0; j < users.length; j++) {
		var usuario = users[j];
		if (usuario.name === userName) {
			return false;
		}
	}
	return true;
}

function contrasenaValida(pwd) {

	if (pwd.length > 4 && tiene_numeros(pwd) && tiene_minuscula_y_mayuscula(pwd)) {
		return true;
	} else {
		document.getElementById("pwd1").style.backgroundColor = "red";
		return false;
	}


}

function tiene_numeros(texto) {
	let numeros = "0123456789";
	for (i = 0; i < texto.length; i++) {
		if (numeros.indexOf(texto.charAt(i), 0) !== -1) {
			return true;
		}
	}
	return false;
}

function tiene_minuscula_y_mayuscula(pwd) {
	let m = false;
	let M = false;
	for (i = 0; i < pwd.length; i++) {
		if (!esNumero(pwd.charAt(i)) && pwd.charAt(i) === pwd.charAt(i).toUpperCase()) {
			M = true;
		}
		if (!esNumero(pwd.charAt(i)) && pwd.charAt(i) === pwd.charAt(i).toLowerCase()) {
			m = true;
		}
	}


	return M && m;

}

function esNumero(digito) {
	let numeros = "0123456789";
	if (numeros.indexOf(digito, 0) !== -1) {
		return true;
	}
	return false;


}


function registroCorrecto() {

	// When site loaded, load the Popupbox First
	loadPopupBox();

	$('#container').click(function() {
		unloadPopupBox();
	});

	function unloadPopupBox() {    // TO Unload the Popupbox
		$('#popup_box').fadeOut("slow");
		$("#container").css({ // this is just for style        
			"opacity": "1"
		});
		window.location.href = "index.html";
	}

	function loadPopupBox() {    // To Load the Popupbox

		var counter = 5;
		var id;
		$('#popup_box').fadeIn("slow");
		$("#container").css({ // this is just for style
			"opacity": "0.3"
		});

		id = setInterval(function() {
			counter--;
			if (counter < 0) {
				clearInterval(id);

				unloadPopupBox();
			} else {
				$("#countDown").text("it closed  after " + counter.toString() + " seconds.");
			}
		}, 500);

	}
}





