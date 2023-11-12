package es.iespuertodelacruz.jmrs.incidencias.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import es.iespuertodelacruz.jmrs.incidencias.entity.Gestor;
import es.iespuertodelacruz.jmrs.incidencias.entity.Ticket;
import es.iespuertodelacruz.jmrs.incidencias.service.ClienteService;
import es.iespuertodelacruz.jmrs.incidencias.service.GestorService;
import es.iespuertodelacruz.jmrs.incidencias.service.TicketService;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "http://localhost:3000")
public class ClienteREST {

	@Autowired
	ClienteService clienteService;
	
	@Autowired
	TicketService ticketService;
	
	@Autowired
	GestorService gestorService;

	@GetMapping // si queremos subruta lleva paréntesis
	public ResponseEntity<?> getAll() {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		// logger.info("si queremos hacer debug por ejemplo");
		clienteService.findAll().forEach(p -> clientes.add((Cliente) p));
		return ResponseEntity.ok().body(clientes);
	}
	
	@GetMapping("/{id}")
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
	public ResponseEntity<?> getTicketsCliente(@PathVariable String id){
		Optional<Cliente> optCliente = clienteService.findById(id);
		if(optCliente.isPresent()) {
			return ResponseEntity.ok().body(optCliente.get().getTickets());
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El cliente no tiene tickets");	
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteClienteById(@PathVariable String id){
		Optional<Cliente> optC = clienteService.findById(id);
		
		if(optC.isPresent()) {
			clienteService.deleteById(id);
			return ResponseEntity.ok("El Cliente ha sido eliminado");
		}else {
		
			return
					ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el id del registro "+
							"no existe");
		}
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody Cliente cDTO){
		
		Cliente c = new Cliente();
		c.setIdCliente(cDTO.getIdCliente());
		c.setNombre(cDTO.getNombre());
		c.setDireccion(cDTO.getDireccion());
		c.setTelefono(cDTO.getTelefono());
		clienteService.save(c);
		
		return ResponseEntity.ok().body(c);
	}
	
	/**
	 * Metodo que guarda tickets nuevos con el id del cliente
	 * @param id
	 * @param ctDTO
	 * @return
	 */
	
	@PostMapping("{id}/tickets")
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
			
			Gestor gestor = new Gestor();
			Set<Gestor> equipoTrabajo = new HashSet<>();
			
			ArrayList<Gestor> gestores = new ArrayList<Gestor>();
			gestorService.findAll().forEach(p -> gestores.add((Gestor) p));
			
			//Recoge el primer gestor de la base de datos y lo añade a equipo trabajo
			if(!gestores.isEmpty()) {
				for (Gestor gestorAl : gestores) {
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
	
	@PutMapping("/{id}")
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
