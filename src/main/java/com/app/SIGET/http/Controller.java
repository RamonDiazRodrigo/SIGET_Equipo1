package com.app.SIGET.http;

import java.util.Map;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.SIGET.dominio.Manager;
import com.app.SIGET.excepciones.DiferentesContrasenasException;

@RestController
public class Controller {
	
	private static final String PASS = "pwd";
	

	@PostMapping("/login")
	public void login(@RequestBody Map<String, Object> credenciales) {
		JSONObject jso = new JSONObject(credenciales);
		String name = jso.getString("userName");
		String password = jso.getString(PASS);
		Manager.get().login(name, password);

	}

	@PostMapping("/register")
	public void register(@RequestBody Map<String, Object> credenciales) throws DiferentesContrasenasException {
		JSONObject jso = new JSONObject(credenciales);
		String password = jso.getString(PASS);
		String passwordConfirmacion = jso.getString("pwd2");

		if (!password.equals(passwordConfirmacion)) {
			throw new DiferentesContrasenasException();
		}

		String name = jso.getString("userName");
		String email = jso.getString("email");
		String rol = jso.getString("rol");

		Manager.get().register(name, email, password, rol);
	}

}
