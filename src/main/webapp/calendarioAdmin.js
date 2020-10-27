let self;

function ViewModel() {
	self = this;
	self.listaReunionesL = ko.observableArray([]);
	self.listaReunionesM = ko.observableArray([]);
	self.listaReunionesX = ko.observableArray([]);
	self.listaReunionesJ = ko.observableArray([]);
	self.listaReunionesV = ko.observableArray([]);


	let url = 'ws://" + window.location.host + "/SIGETEquipo1';
	self.sws = new WebSocket(url);

	self.sws.onopen = function() {
		let msg = {
			type: 'ready'
		};
		self.sws.send(JSON.stringify(msg));
	};

	self.sws.onmessage = function(event) {
		//let data = event.data;
		//data = JSON.parse(data);
		let reuniones = new Array();

		let reunion1 = {
			name: 'Daily',
			dia: 'LUNES',
			horaI: '12:30',
			horaF: '13:30'
		};
		reuniones.push(reunion1);


		// Listar usuarios
		//let reuniones = data.reuniones;

		for (let i = 0; i < reuniones.length; i++) {
			let reunion = reuniones[i];
			let horaIn = reunion.horaI.split(':');
			let horaFi = reunion.horaF.split(':');
			let posTop = 0;
			let length = 0;

			if (horaIn[1] !== horaFi[1]) { //Si los minutajes son distintos
				if (horaIn[1] < horaFi[1]) {
					length = (parseInt(horaFi[0], 10) - parseInt(horaIn[0], 10) + 0.5) * 2 * 50
				}
				else {
					length = (parseInt(horaFi[0], 10) - parseInt(horaIn[0], 10) - 0.5) * 2 * 50
				}
			}
			else {
				length = (parseInt(horaFi[0], 10) - parseInt(horaIn[0], 10)) * 2 * 50;
			}


			if ('30' === horaIn[1]) {

				posTop = (parseInt(horaIn[0], 10) - 9 + 0.5) * 2 * 50

			}
			else {
				posTop = (parseInt(horaIn[0], 10) - 9) * 2 * 50;
			}



			switch (reunion.dia) {
				case 'LUNES':
					if (self.listaReunionesL().some(r => r.name === reunion.name) === false) {
						self.listaReunionesL.push(new Reunion(reunion.name, reunion.dia, horaIn[0], horaIn[1], horaFi[0], horaFi[1], posTop, length));
					}

				case 'MARTES':


				case 'MIERCOLES':


				case 'JUEVES':

				case 'VIERNES':


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
