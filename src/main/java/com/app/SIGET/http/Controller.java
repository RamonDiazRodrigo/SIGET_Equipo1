package com.app.SIGET.http;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.SIGET.dominio.Manager;
import com.app.SIGET.dominio.User;


@RestController
public class Controller {
	
	@PostMapping("/login")
	public void login(@RequestBody Map<String, Object> credenciales) throws Exception {
		JSONObject jso = new JSONObject(credenciales);
		String name = jso.getString("userName");
		String password = jso.getString("pwd");
		Manager.get().login(name, password);

	}
	
	@PostMapping("/register")
	public void register(@RequestBody Map<String, Object> credenciales) throws Exception {
		JSONObject jso = new JSONObject(credenciales);
		String name = jso.getString("name");
		String email= jso.getString("email");
		String password = jso.getString("password");
		String password_confirmacion = jso.getString("password");
		String rol= jso.getString("rol");
		
		if (!password.equals(password_confirmacion))
			throw new Exception("Las password es incorrecta");
		
		Manager.get().register(name,email,password,rol);
	}

	
	//NO VALIDO
    /*
    @GetMapping("/leer")
    public JSONObject leer(HttpSession session){
        return Manager.get().leer();
    }
	*/
}
