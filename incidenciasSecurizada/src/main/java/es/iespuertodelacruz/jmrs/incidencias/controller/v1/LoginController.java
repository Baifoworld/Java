package es.iespuertodelacruz.jmrs.incidencias.controller.v1;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.jmrs.incidencias.entity.Usuario;
import es.iespuertodelacruz.jmrs.incidencias.security.GestorDeJWT;
import es.iespuertodelacruz.jmrs.incidencias.service.UsuarioService;
import io.swagger.annotations.Api;

@RestController
@Api (
		tags = "Interfaz para loguearse",
		description = "Inicio de sesion en la API con username y password"
		)
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	/*  funciona el form urlencode */
	@PostMapping(path = "/api/v1/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<?> login(@RequestParam("username") String username, 
			@RequestParam("password") String pwd) {
		
		
		String token = getJWTToken(username,pwd);
		
		//token nulo si usuario/pass no es válido
		if( token != null) {
			return ResponseEntity.ok(token);
		}else
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("usuario/pass erróneos");
		
	}
	
	
	static class UsuarioJsonLogin{
		String username;
		String password;
		public String getUsername() { return username;};
		public String getPassword() {return password;};
		public void setName(String name ) {this.username = name;};
		public void setPassword(String password ) {this.password = password;};
			
	}
	
	/* json post */
	@PostMapping(path = "/api/v1/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> login(@RequestBody UsuarioJsonLogin usuarioJson) {
		
		
		String token = getJWTToken(usuarioJson.username, usuarioJson.password);
		
		//token nulo si usuario/pass no es válido
		if( token != null) {
			return ResponseEntity.ok(token);
		}else
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		
	}	

	@Autowired
	UsuarioService usuarioService;
	
	
	private String getJWTToken(String username, String passTextoPlanoRecibida) {
		
		String respuesta = null;
		
		GestorDeJWT gestorDeJWT = GestorDeJWT.getInstance();
		
		Usuario usuario = usuarioService.findByNick(username);

        
        String passwordUsuarioEnHash = "";
        boolean autenticado = false;
        
        if(usuario != null) { 
        	passwordUsuarioEnHash = usuario.getPassword();
        	
        	autenticado = BCrypt.checkpw(passTextoPlanoRecibida, passwordUsuarioEnHash);
        	logger.info(""+autenticado);
        }

		if(autenticado) {
			
			String rol = usuario.getRole().getName();
			List<String> roles = new ArrayList<String>(); 
			roles.add(rol);
			logger.info("los roles obtenidos: " + roles);
			

			int duracionMinutos = 600;
			
			String token = gestorDeJWT.generarToken(username, roles, duracionMinutos);
			
			respuesta = gestorDeJWT.BEARERPREFIX + token;			
		}
		
		return respuesta;

	}	

}