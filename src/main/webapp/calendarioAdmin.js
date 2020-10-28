let self;

function ViewModel() {
	self = this;
	self.listaReunionesL = ko.observableArray([]);
	self.listaReunionesM = ko.observableArray([]);
	self.listaReunionesX = ko.observableArray([]);
	self.listaReunionesJ = ko.observableArray([]);
	self.listaReunionesV = ko.observableArray([]);


	var url = "ws://" + window.location.host + "/SIGETEquipo1";
	self.sws = new WebSocket(url);

	self.sws.onopen = function() {
		let msg = {
			type: 'ready'
		};
		self.sws.send(JSON.stringify(msg));
	};

	self.sws.onmessage = function(event) {
		let data = event.data;
		data = JSON.parse(data);

		// Listar usuarios
		let reuniones = data.actividades.actividades;

		for (let i = 0; i < reuniones.length; i++) {
			let reunion = reuniones[i];
			let horaIn = reunion.HoraI.split(':');
			let horaFi = reunion.HoraF.split(':');
			let posTop = 0;
			let length = 0;

			//Si los minutajes son distintos
			if (horaIn[1] !== horaFi[1]) {
				if (horaIn[1] < horaFi[1]) {
					length = (parseInt(horaFi[0], 10) - parseInt(horaIn[0], 10) + 0.5) * 2 * 50;
				}
				else {
					length = (parseInt(horaFi[0], 10) - parseInt(horaIn[0], 10) - 0.5) * 2 * 50;
				}
			}
			else {
				length = (parseInt(horaFi[0], 10) - parseInt(horaIn[0], 10)) * 2 * 50;
			}


			if ('30' === horaIn[1]) {

				posTop = (parseInt(horaIn[0], 10) - 9 + 0.5) * 2 * 50;

			}
			else {
				posTop = (parseInt(horaIn[0], 10) - 9) * 2 * 50;
			}

			switch (reunion.dia) {
				case 'LUNES':
					if (self.listaReunionesL().some(r => r.name === reunion.name) === false) {
						self.listaReunionesL.push(new Reunion(reunion.name, reunion.dia, horaIn[0], horaIn[1], horaFi[0], horaFi[1], posTop, length));
					}

					var ul = document.getElementById("lunes");
					let itemsL = ul.getElementsByTagName("li");
					for (let i = 0; i < itemsL.length; i++) {
						if (itemsL[i].innerText === reunion.name) {
							itemsL[i].style.top = posTop.toString() + "px";
							itemsL[i].style.height = length.toString() + "px";
						}
					}
					break;

				case 'MARTES':
				if (self.listaReunionesM().some(r => r.name === reunion.name) === false) {
						self.listaReunionesM.push(new Reunion(reunion.name, reunion.dia, horaIn[0], horaIn[1], horaFi[0], horaFi[1], posTop, length));
					}

					var ul = document.getElementById("martes");
					let itemsM = ul.getElementsByTagName("li");
					for (let i = 0; i < itemsM.length; i++) {
						if (itemsM[i].innerText === reunion.name) {
							itemsM[i].style.top = posTop.toString() + "px";
							itemsM[i].style.height = length.toString() + "px";
						}
					}
					
					break;


				case 'MIERCOLES':
				if (self.listaReunionesX().some(r => r.name === reunion.name) === false) {
						self.listaReunionesX.push(new Reunion(reunion.name, reunion.dia, horaIn[0], horaIn[1], horaFi[0], horaFi[1], posTop, length));
					}

					var ul = document.getElementById("miercoles");
					let itemsX = ul.getElementsByTagName("li");
					for (let i = 0; i < itemsX.length; i++) {
						if (itemsX[i].innerText === reunion.name) {
							itemsX[i].style.top = posTop.toString() + "px";
							itemsX[i].style.height = length.toString() + "px";
						}
					}
					
					break;


				case 'JUEVES':
				if (self.listaReunionesJ().some(r => r.name === reunion.name) === false) {
						self.listaReunionesJ.push(new Reunion(reunion.name, reunion.dia, horaIn[0], horaIn[1], horaFi[0], horaFi[1], posTop, length));
					}

					var ul = document.getElementById("jueves");
					let itemsJ = ul.getElementsByTagName("li");
					for (let i = 0; i < itemsJ.length; i++) {
						if (itemsJ[i].innerText === reunion.name) {
							itemsJ[i].style.top = posTop.toString() + "px";
							itemsJ[i].style.height = length.toString() + "px";
						}
					}
					
					break;

				case 'VIERNES':
				if (self.listaReunionesV().some(r => r.name === reunion.name) === false) {
						self.listaReunionesV.push(new Reunion(reunion.name, reunion.dia, horaIn[0], horaIn[1], horaFi[0], horaFi[1], posTop, length));
					}

					var ul = document.getElementById("viernes");
					let itemsV = ul.getElementsByTagName("li");
					for (let i = 0; i < itemsV.length; i++) {
						if (itemsV[i].innerText === reunion.name) {
							itemsV[i].style.top = posTop.toString() + "px";
							itemsV[i].style.height = length.toString() + "px";
						}
					}
					
					break;


			}

		}
	};


	class Reunion {
		constructor(name, dia, horaI, minutosI, horaF, minutosF, posTop, length) {
			this.name = name;
			this.dia = dia;
			this.horaI = horaI;
			this.minutosI = minutosI;
			this.horaF = horaF;
			this.minutosF = minutosF;
			this.posTop = posTop;
			this.length = length;
		}
	}

}
let vm = new ViewModel();
ko.applyBindings(vm);
