package es.iespuertodelacruz.jmrs.incidencias.controller.v3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import es.iespuertodelacruz.jmrs.incidencias.dto.ClienteTicketPostDTO;
import es.iespuertodelacruz.jmrs.incidencias.dto.RegisterDTO;
import es.iespuertodelacruz.jmrs.incidencias.entity.Cliente;
import es.iespuertodelacruz.jmrs.incidencias.entity.Gestore;
import es.iespuertodelacruz.jmrs.incidencias.entity.Ticket;
import es.iespuertodelacruz.jmrs.incidencias.entity.Usuario;
import es.iespuertodelacruz.jmrs.incidencias.service.ClienteService;
import es.iespuertodelacruz.jmrs.incidencias.service.GestorService;
import es.iespuertodelacruz.jmrs.incidencias.service.RolService;
import es.iespuertodelacruz.jmrs.incidencias.service.TicketService;
import es.iespuertodelacruz.jmrs.incidencias.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v3/clientes")
@Api (
		tags = "Interfaz de clientes para administradores",
		description = "Muestra todos los metodos del cliente para los administradores(v3)"
		)
public class ClienteRESTv3 {
	
	private static final Logger logger = LoggerFactory.getLogger(ClienteRESTv3.class);

	@Autowired
	ClienteService clienteService;
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	GestorService gestorService;
	
	@Autowired
	TicketService ticketService;
	
	@Autowired
	RolService rolService;

	@GetMapping // si queremos subruta lleva paréntesis
	@ApiOperation(
			value = "Obtener todos los clientes",
			notes = "Devuelve todos los clientes"
			)
	public ResponseEntity<?> getAll() {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		// logger.info("si queremos hacer debug por ejemplo");
		clienteService.findAll().forEach(p -> clientes.add((Cliente) p));
		return ResponseEntity.ok().body(clientes);
	}
	
	@GetMapping("/{id}")
	@ApiOperation(
			value = "Obtener cliente por ID",
			notes = "Devuelve un cliente que se busca por la id del cliente"
			)
	public ResponseEntity<?> findById(@PathVariable String id){
		Optional<Cliente> clientes = clienteService.findById(id);
		if(clientes.isPresent()) {
			Cliente cliente = clientes.get();
			if(cliente != null) {
				return ResponseEntity.ok().body(cliente);
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ha habido un error");	
			}
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el id del registro "+
					"no existe");	
		}
	}
	
	/**
	 * Metodo que recoge los tickets de un cliente en concreto
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}/tickets")
	@ApiOperation(
			value = "Obtener tickets cliente",
			notes = "Devuelve todos los tickets de un cliente con su id"
			)
	public ResponseEntity<?> getTicketsCliente(@PathVariable String id){
		Optional<Cliente> optCliente = clienteService.findById(id);
		if(optCliente.isPresent()) {
			return ResponseEntity.ok().body(optCliente.get().getTickets());
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El cliente no tiene tickets");	
		}
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(
			value = "Eliminar cliente",
			notes = "Eliminar el cliente con su id"
			)
	public ResponseEntity<?> deleteClienteById(@PathVariable String id){
		Optional<Cliente> optC = clienteService.findById(id);
		
		if(optC.isPresent()) {
			Cliente c = optC.get();
			Usuario u = usuarioService.findByNick(c.getUsuario().getUsername());
			clienteService.deleteById(id);
			
			if(u != null) {
				usuarioService.deleteById(u.getId());	
			}
			return ResponseEntity.ok("El Cliente ha sido eliminado");
		}else {
		
			return
					ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el id del registro "+
							"no existe");
		}
	}
	
	@PostMapping
	@ApiOperation(
			value = "Crear cliente",
			notes = "Creacion de un cliente"
			)
	public ResponseEntity<?> save(@RequestBody RegisterDTO rDTO){
		
		Usuario u = new Usuario();
		u.setUsername(rDTO.getUsername());
		u.setPassword(BCrypt.hashpw(rDTO.getPassword(), BCrypt.gensalt()));
		/*Role rol = new Role();
		rol.setId(2);
//		u.setRole(rol);*/
		u.setRole(rolService.findById(2).get());
		Usuario nu = usuarioService.save(u);
		
		logger.debug("******************************** " + rDTO.getidCliente());
		Cliente c = null;
		if(nu != null) {
			c = new Cliente();
			c.setIdCliente(rDTO.getidCliente());
			c.setNombre(rDTO.getNombre());
			c.setDireccion(rDTO.getDireccion());
			c.setTelefono(rDTO.getTelefono());
			c.setUsuario(nu);
			clienteService.save(c);
		}
		
		
		return ResponseEntity.ok().body(c);
		
	}
	
	@PostMapping("{id}/tickets")
	@ApiOperation(
			value = "Crear ticket de Cliente",
			notes = "Creacion de un ticket con el id del cliente"
			)
	public ResponseEntity<?> saveTicketCliente(@PathVariable String id, 
			@RequestBody ClienteTicketPostDTO ctDTO){
		Optional<Cliente> optCliente = clienteService.findById(id);
		
		if(optCliente.isPresent()) {
			
			Ticket t = new Ticket();
			t.setIdTicket(ctDTO.getIdTicket());
			t.setDescripcion(ctDTO.getDescripcion());
			t.setEstado(ctDTO.getEstado());
			t.setFechaInicio(ctDTO.getFechaInicio());
			t.setFechaFin(ctDTO.getFechaFin());
			t.setCliente(optCliente.get());
			
			Gestore gestor = new Gestore();
			Set<Gestore> equipoTrabajo = new HashSet<>();
			
			ArrayList<Gestore> gestores = new ArrayList<Gestore>();
			gestorService.findAll().forEach(p -> gestores.add((Gestore) p));
			
			//Recoge el primer gestor de la base de datos y lo añade a equipo trabajo
			if(!gestores.isEmpty()) {
				for (Gestore gestorAl : gestores) {
					equipoTrabajo.add(gestorAl);
					break;
				}
			}
			
			t.setGestores(equipoTrabajo);
			
			ticketService.save(t);
			
			return ResponseEntity.ok().body(t);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el id del registro "+
					"no existe");
		}
	}
	
	@GetMapping("/{id}/tickets/abiertos")
	@ApiOperation(
			value = "Obtener todos los tickets abiertos del cliente",
			notes = "Devuelve todos los tickets abiertos del cliente"
			)
	public ResponseEntity<?> getOpenTicketsClient(@PathVariable String id) {
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		// logger.info("si queremos hacer debug por ejemplo");
		ticketService.findOpenTicketsClient(id).forEach(p -> tickets.add((Ticket) p));
		return ResponseEntity.ok().body(tickets);
	}
	
	@PutMapping("/{id}")
	@ApiOperation(
			value = "Actualizar cliente",
			notes = "Actualizacion de los datos del cliente"
			)
	public ResponseEntity<?> update(@PathVariable String id, 
			@RequestBody Cliente cDTO){
		Optional<Cliente> optC = clienteService.findById(id);
		if(optC.isPresent()) {
			Cliente c = optC.get();
			c.setIdCliente(cDTO.getIdCliente());
			c.setNombre(cDTO.getNombre());
			c.setDireccion(cDTO.getDireccion());
			c.setTelefono(cDTO.getTelefono());
			c.setTickets(cDTO.getTickets());
			return ResponseEntity.ok(clienteService.save(c));
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El id del registro no existe");	
		}
		
	}

}
