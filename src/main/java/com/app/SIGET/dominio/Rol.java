package com.app.SIGET.dominio;

public enum Rol {
	ADMIN {
		@Override
		public String toString() {
			return "ADMIN";
		}
	},
	ASISTENTE {
		@Override
		public String toString() {
			return "ASISTENTE";
		}
	}
}
