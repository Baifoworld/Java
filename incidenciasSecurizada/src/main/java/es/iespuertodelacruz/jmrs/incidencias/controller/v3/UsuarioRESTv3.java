package es.iespuertodelacruz.jmrs.incidencias.controller.v3;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.jmrs.incidencias.entity.Usuario;
import es.iespuertodelacruz.jmrs.incidencias.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v3/usuarios")
@Api (
		tags = "Interfaz Usuario",
		description = "Muestra todos los metodos del usuario según el Gestor(v3)"
		)
public class UsuarioRESTv3 {

	@Autowired
	UsuarioService usuarioService;

	@GetMapping // si queremos subruta lleva paréntesis
	@ApiOperation(
			value = "Obtener Usuarios",
			notes = "Devuelve todos los usuarios"
			)
	public ResponseEntity<?> getAll() {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		// logger.info("si queremos hacer debug por ejemplo");
		usuarioService.findAll().forEach(p -> usuarios.add((Usuario) p));
		return ResponseEntity.ok().body(usuarios);
	}
	
	@GetMapping("/{id}")
	@ApiOperation(
			value = "Obtener Usuario Id",
			notes = "Devuelve un usuario que se le pasa un id"
			)
	public ResponseEntity<?> findById(@PathVariable Integer id){
		Optional<Usuario> usuarios = usuarioService.findById(id);
		if(usuarios.isPresent()) {
			Usuario usuario = usuarios.get();
			if(usuario != null) {
				return ResponseEntity.ok().body(usuario);
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ha habido un error");	
			}
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el id del registro "+
					"no existe");	
		}
		
	}
	
	@GetMapping("/{nick}")
	@ApiOperation(
			value = "Obtener Usuario Nick",
			notes = "Devuelve un usuario por un Nick"
			)
	public ResponseEntity<?> findByNick(@PathVariable String nick){
		Usuario usuario = usuarioService.findByNick(nick);
		if(usuario != null) {
				return ResponseEntity.ok().body(usuario);
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ha habido un error");	
			}
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(
			value = "Eliminar Usuario ID",
			notes = "Eliminar un usuario por el id"
			)
	public ResponseEntity<?> deleteUsuarioById(@PathVariable Integer id){
		Optional<Usuario> optU = usuarioService.findById(id);
		
		if(optU.isPresent()) {
			usuarioService.deleteById(id);
			return ResponseEntity.ok("El usuario ha sido eliminado");
		}else {
		
			return
					ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el id del registro "+
							"no existe");
		}
	}
	
	@PostMapping
	@ApiOperation(
			value = "Crear Usuario",
			notes = "Creacion de un usuario con un gestor"
			)
	public ResponseEntity<?> save(@RequestBody Usuario uDTO){
		
		Usuario u = new Usuario();
		u.setId(uDTO.getId());
		u.setPassword(uDTO.getPassword());
		u.setRole(uDTO.getRole());
		usuarioService.save(u);
		
		return ResponseEntity.ok().body(u);
	}
	
	@PutMapping("/{id}")
	@ApiOperation(
			value = "Actualizar Usuario",
			notes = "Actualizacion de los datos de un usuario"
			)
	public ResponseEntity<?> update(@PathVariable Integer id, 
			@RequestBody Usuario uDTO){
		
		Optional<Usuario> optU = usuarioService.findById(id);
		if(optU.isPresent()) {
			
			Usuario u = optU.get();
			u.setId(uDTO.getId());
			u.setPassword(uDTO.getPassword());
			u.setRole(uDTO.getRole());
			
			return ResponseEntity.ok(usuarioService.save(u));
		
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El id del registro no existe");	
		}		
	}
}
