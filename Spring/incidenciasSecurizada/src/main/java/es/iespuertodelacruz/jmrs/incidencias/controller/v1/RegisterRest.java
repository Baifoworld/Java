package es.iespuertodelacruz.jmrs.incidencias.controller.v1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.jmrs.incidencias.dto.RegisterDTO;
import es.iespuertodelacruz.jmrs.incidencias.entity.Cliente;
import es.iespuertodelacruz.jmrs.incidencias.entity.Usuario;
import es.iespuertodelacruz.jmrs.incidencias.service.ClienteService;
import es.iespuertodelacruz.jmrs.incidencias.service.RolService;
import es.iespuertodelacruz.jmrs.incidencias.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/register")
@Api (
		tags = "Interfaz de Registro",
		description = "Registro en la API como cliente para poder obtener un nuevo cliente"
		)
public class RegisterRest {
	
	private static final Logger logger = LoggerFactory.getLogger(RegisterRest.class);

	@Autowired
	ClienteService clienteService;
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	RolService rolService;
	
	@PostMapping
	@ApiOperation(
			value = "Registrar Cliente",
			notes = "Registro de un nuevo usuario en el que se le da el rol de cliente"
			)
	public ResponseEntity<?> save(@RequestBody RegisterDTO rDTO){
		Usuario u = new Usuario();
		u.setUsername(rDTO.getUsername());
		u.setPassword(BCrypt.hashpw(rDTO.getPassword(), BCrypt.gensalt()));
		u.setRole(rolService.findById(2).get());
		Usuario nu = usuarioService.save(u);
		logger.info("id del usuario " +nu.getId());
		
		Cliente c = new Cliente();
		c.setIdCliente(rDTO.getIdcliente());
		c.setNombre(rDTO.getNombre());
		c.setDireccion(rDTO.getDireccion());
		c.setTelefono(rDTO.getTelefono());
		logger.info("id del cliente " + rDTO.getIdcliente());
		logger.info("nombre del cliente " + rDTO.getNombre());
		c.setUsuario(u);
		clienteService.save(c);
		
		return ResponseEntity.ok().body(u);
	}

}
