package com.app.SIGET.http;

import java.util.Map;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.SIGET.dominio.Manager;
import com.app.SIGET.excepciones.DiferentesContrasenasException;
import com.app.SIGET.excepciones.ErrorInesperadoException;

/**
 * @author Equipo1
 *
 */
@RestController
public class Controller {
	
	private static final String PASS = "password";

	/**
	 * @param credenciales
	 * @throws Exception
	 */
	@PostMapping("/login")
	public void login(@RequestBody Map<String, Object> credenciales) throws ErrorInesperadoException {
		JSONObject jso = new JSONObject(credenciales);
		String name = jso.getString("name");
		String password = jso.getString(PASS);
		Manager.get().login(name, password);

	}

	/** 
	 * @param credenciales
	 * @throws Exception
	 */
	@PostMapping("/register")
	public void register(@RequestBody Map<String, Object> credenciales) throws DiferentesContrasenasException {
		JSONObject jso = new JSONObject(credenciales);
		String password = jso.getString(PASS);
		String passwordConfirmacion = jso.getString(PASS);

		if (!password.equals(passwordConfirmacion)) {
			throw new DiferentesContrasenasException();
		}

		String name = jso.getString("name");
		String email = jso.getString("email");
		String rol = jso.getString("rol");

		Manager.get().register(name, email, password, rol);
	}

}
