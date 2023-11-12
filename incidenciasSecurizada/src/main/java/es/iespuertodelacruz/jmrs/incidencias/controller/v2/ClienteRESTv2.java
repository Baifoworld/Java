package es.iespuertodelacruz.jmrs.incidencias.controller.v2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.jmrs.incidencias.dto.ClienteTicketPostDTO;
import es.iespuertodelacruz.jmrs.incidencias.entity.Cliente;
import es.iespuertodelacruz.jmrs.incidencias.entity.Gestore;
import es.iespuertodelacruz.jmrs.incidencias.entity.Role;
import es.iespuertodelacruz.jmrs.incidencias.entity.Ticket;
import es.iespuertodelacruz.jmrs.incidencias.entity.Usuario;
import es.iespuertodelacruz.jmrs.incidencias.service.ClienteService;
import es.iespuertodelacruz.jmrs.incidencias.service.GestorService;
import es.iespuertodelacruz.jmrs.incidencias.service.TicketService;
import es.iespuertodelacruz.jmrs.incidencias.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v2/clientes")
@Api (
		tags = "Interfaz del cliente logeado",
		description = "Muestra todos los metodos del cliente que se ha logeado(v2)"
		)
public class ClienteRESTv2 {
	
	private static final Logger logger = LoggerFactory.getLogger(ClienteRESTv2.class);

	@Autowired
	ClienteService clienteService;
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	GestorService gestorService;
	
	@Autowired
	TicketService ticketService;
	
	/**
	 * Metodo que recoge los tickets de un cliente en concreto
	 * @param id
	 * @return
	 */
	@GetMapping("/tickets")
	@ApiOperation(
			value = "Obtener los tickets del cliente",
			notes = "Recoge y muestra todos los tickets del cliente que esta logeado"
			)
	public ResponseEntity<?> getTicketsCliente(){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String nombreAutenticado = ((UserDetails)principal).getUsername();
		Optional<Cliente> optCliente = clienteService.findByNick(nombreAutenticado);
		if(optCliente.isPresent()) {
			return ResponseEntity.ok().body(optCliente.get().getTickets());
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El cliente no tiene tickets");	
		}
	}
	
	
	@PostMapping("/tickets")
	@ApiOperation(
			value = "Guardar nuevo ticket del cliente",
			notes = "Crea un nuevo ticket con el id del cliente que esta logeado"
			)
	public ResponseEntity<?> saveTicketCliente(@RequestBody ClienteTicketPostDTO ctDTO){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String nombreAutenticado = ((UserDetails)principal).getUsername();
		Optional<Cliente> optCliente = clienteService.findByNick(nombreAutenticado);
		
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
	
	@GetMapping("/tickets/abiertos")
	@ApiOperation(
			value = "Obtener todos los tickets abiertos del cliente",
			notes = "Devuelve todos los tickets abiertos del cliente"
			)
	public ResponseEntity<?> getOpenTicketsClient() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String nombreAutenticado = ((UserDetails)principal).getUsername();
		Optional<Cliente> optCliente = clienteService.findByNick(nombreAutenticado);
		
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		// logger.info("si queremos hacer debug por ejemplo");
		Cliente cliente = optCliente.get();
		ticketService.findOpenTicketsClient(cliente.getIdCliente()).forEach(p -> tickets.add((Ticket) p));
		return ResponseEntity.ok().body(tickets);
	}
	
	@DeleteMapping
	@ApiOperation(
			value = "Eliminar cliente",
			notes = "Elimina el cliente que esta logeado"
			)
	public ResponseEntity<?> deleteClienteById(){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String nombreAutenticado = ((UserDetails)principal).getUsername();
		Optional<Cliente> optCliente = clienteService.findByNick(nombreAutenticado);
		
		if(optCliente.isPresent()) {
			Cliente c = optCliente.get();
			Usuario u = usuarioService.findByNick(c.getUsuario().getUsername());
			clienteService.deleteById(c.getIdCliente());
			
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
	
	@PutMapping
	@ApiOperation(
			value = "Actualizar cliente",
			notes = "Actualiza los datos del cliente que esta logeado"
			)
	public ResponseEntity<?> update(@RequestBody Cliente cDTO){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String nombreAutenticado = ((UserDetails)principal).getUsername();
		Optional<Cliente> optCliente = clienteService.findByNick(nombreAutenticado);
		
		if(optCliente.isPresent()) {
			Cliente c = optCliente.get();
			c.setIdCliente(cDTO.getIdCliente());
			c.setNombre(cDTO.getNombre());
			c.setDireccion(cDTO.getDireccion());
			c.setTelefono(cDTO.getTelefono());
			return ResponseEntity.ok(clienteService.save(c));
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El id del registro no existe");	
		}
		
	}

}
