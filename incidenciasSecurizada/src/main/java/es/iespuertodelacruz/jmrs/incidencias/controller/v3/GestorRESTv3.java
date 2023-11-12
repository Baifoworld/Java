package es.iespuertodelacruz.jmrs.incidencias.controller.v3;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.jmrs.incidencias.dto.RegisterAdminDTO;
import es.iespuertodelacruz.jmrs.incidencias.entity.Cliente;
import es.iespuertodelacruz.jmrs.incidencias.entity.Gestore;
import es.iespuertodelacruz.jmrs.incidencias.entity.Usuario;
import es.iespuertodelacruz.jmrs.incidencias.service.GestorService;
import es.iespuertodelacruz.jmrs.incidencias.service.RolService;
import es.iespuertodelacruz.jmrs.incidencias.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v3/gestores")
@Api (
		tags = "Interfaz del gestor",
		description = "Muestra todos los metodos del gestor(v3)"
		)
public class GestorRESTv3 {

	@Autowired
	GestorService gestorService;
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	RolService rolService;

	@GetMapping // si queremos subruta lleva paréntesis
	@ApiOperation(
			value = "Obtener Gestores",
			notes = "Devuelve todos los gestores"
			)
	public ResponseEntity<?> getAll() {
		ArrayList<Gestore> gestores = new ArrayList<Gestore>();
		// logger.info("si queremos hacer debug por ejemplo");
		gestorService.findAll().forEach(p -> gestores.add((Gestore) p));
		return ResponseEntity.ok().body(gestores);
	}
	
	@GetMapping("/{dni}")
	@ApiOperation(
			value = "Obtener Gestor con ID",
			notes = "Devuelve un gestor en concreto con su id o DNI"
			)
	public ResponseEntity<?> findById(@PathVariable String dni){
		Optional<Gestore> gestores = gestorService.findById(dni);
		
		if(gestores.isPresent()) {
			Gestore gestor = gestores.get();
			if(gestor != null) {
				return ResponseEntity.ok().body(gestor);
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ha habido un error");	
			}
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el id del registro "+
					"no existe");	
		}
	}
	
	@DeleteMapping("/{dni}")
	@ApiOperation(
			value = "Eliminar Gestor",
			notes = "Elimina un gestor que se le ha indicado con su DNI"
			)
	public ResponseEntity<?> deleteGestorById(@PathVariable String dni){
		Optional<Gestore> optG = gestorService.findById(dni);
		
		if(optG.isPresent()) {
			Gestore g = optG.get(); 
			Usuario u = usuarioService.findByNick(g.getUsuario().getUsername());
			gestorService.deleteById(dni);
			
			if(u != null) {
				usuarioService.deleteById(u.getId());
			}
			return ResponseEntity.ok("El Gestor ha sido eliminado");
		}else {
		
			return
					ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el dni del registro "+
							"no existe");
		}
	}
	
	@PostMapping
	@ApiOperation(
			value = "Guardar Gestor",
			notes = "Crear un gestor con todos los datos necesarios"
			)
	public ResponseEntity<?> save(@RequestBody RegisterAdminDTO gDTO){
		Usuario u = new Usuario();
		u.setUsername(gDTO.getUsername());
		u.setPassword(BCrypt.hashpw(gDTO.getPassword(), BCrypt.gensalt()));
		/*Role rol = new Role();
		rol.setId(2);
//		u.setRole(rol);*/
		u.setRole(rolService.findById(1).get());
		Usuario nu = usuarioService.save(u);
		Gestore g = new Gestore();
		g.setDni(gDTO.getDni());
		g.setNombre(gDTO.getNombre());
		g.setApellidos(gDTO.getApellidos());
		g.setUsuario(nu);
		gestorService.save(g);
		
		return ResponseEntity.ok().body(g);
		
	}
	
	@PutMapping("/{dni}")
	@ApiOperation(
			value = "Actualizar Gestor",
			notes = "Actualizar los datos de un gestor en concreto que se ha pasado el DNI"
			)
	public ResponseEntity<?> update(@PathVariable String dni, 
			@RequestBody Gestore gDTO){
		Optional<Gestore> optG = gestorService.findById(dni);
		if(optG.isPresent()) {
			Gestore g = optG.get();
			g.setDni(gDTO.getDni());
			g.setNombre(gDTO.getNombre());
			g.setApellidos(gDTO.getApellidos());
			g.setTickets(gDTO.getTickets());
			g.setSeguimientoTickets(gDTO.getSeguimientoTickets());
			return ResponseEntity.ok(gestorService.save(g));
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El dni del registro no existe");	
		}
	}
}
