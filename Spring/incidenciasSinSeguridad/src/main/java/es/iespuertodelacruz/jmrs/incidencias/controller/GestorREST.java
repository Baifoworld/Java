package es.iespuertodelacruz.jmrs.incidencias.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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

import es.iespuertodelacruz.jmrs.incidencias.dto.GestorTicketPostDTO;
import es.iespuertodelacruz.jmrs.incidencias.entity.Cliente;
import es.iespuertodelacruz.jmrs.incidencias.entity.Gestor;
import es.iespuertodelacruz.jmrs.incidencias.entity.Ticket;
import es.iespuertodelacruz.jmrs.incidencias.service.ClienteService;
import es.iespuertodelacruz.jmrs.incidencias.service.GestorService;
import es.iespuertodelacruz.jmrs.incidencias.service.TicketService;

@RestController
@RequestMapping("/api/gestores")
public class GestorREST {

	@Autowired
	GestorService gestorService;
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	TicketService ticketService;

	@GetMapping // si queremos subruta lleva paréntesis
	public ResponseEntity<?> getAll() {
		ArrayList<Gestor> gestores = new ArrayList<Gestor>();
		// logger.info("si queremos hacer debug por ejemplo");
		gestorService.findAll().forEach(p -> gestores.add((Gestor) p));
		return ResponseEntity.ok().body(gestores);
	}
	
	@GetMapping("/{dni}")
	public ResponseEntity<?> findById(@PathVariable String dni){
		Optional<Gestor> gestores = gestorService.findById(dni);
		
		if(gestores.isPresent()) {
			Gestor gestor = gestores.get();
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
	
	/**
	 * Metodo que recoge los tickets de un cliente en concreto
	 * @param dni
	 * @return
	 */
	@GetMapping("/{dni}/tickets")
	public ResponseEntity<?> getTicketsGestor(@PathVariable String dni){
		Optional<Gestor> optGestor = gestorService.findById(dni);
		if(optGestor.isPresent()) {
			return ResponseEntity.ok().body(optGestor.get().getTickets());
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El gestor no existe");	
		}
	}
	
	@DeleteMapping("/{dni}")
	public ResponseEntity<?> deleteGestorById(@PathVariable String dni){
		Optional<Gestor> optG = gestorService.findById(dni);
		
		if(optG.isPresent()) {
			gestorService.deleteById(dni);
			return ResponseEntity.ok("El Gestor ha sido eliminado");
		}else {
		
			return
					ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el dni del registro "+
							"no existe");
		}
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody Gestor gDTO){
		Gestor g = new Gestor();
		g.setDni(gDTO.getDni());
		g.setNombre(gDTO.getNombre());
		g.setApellidos(gDTO.getApellidos());
		g.setTickets(gDTO.getTickets());
		g.setSeguimientoTickets(gDTO.getSeguimientoTickets());
		gestorService.save(g);
		
		return ResponseEntity.ok().body(g);
	}
	
	@PostMapping("{dni}/tickets")
	public ResponseEntity<?> saveTicketCliente(@PathVariable String dni, 
			@RequestBody GestorTicketPostDTO gtDTO){
		Optional<Gestor> optGestor = gestorService.findById(dni);
		
		if(optGestor.isPresent()) {
			
			Ticket t = new Ticket();
			t.setIdTicket(gtDTO.getIdTicket());
			t.setDescripcion(gtDTO.getDescripcion());
			t.setEstado(gtDTO.getEstado());
			t.setFechaInicio(gtDTO.getFechaInicio());
			t.setFechaFin(gtDTO.getFechaFin());
			
			Optional<Cliente> optCliente = clienteService.findById(gtDTO.getIdCliente());
			if(optCliente.isPresent()) {
				t.setCliente(optCliente.get());
			}
			
			Set<Gestor> equipoTrabajo = new HashSet<>();
			
			ArrayList<Gestor> gestores = new ArrayList<Gestor>();
			equipoTrabajo.add(optGestor.get());
			
			t.setGestores(equipoTrabajo);
			
			ticketService.save(t);
			
			return ResponseEntity.ok().body(t);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el id del registro "+
					"no existe");
		}
	}
	
	@PutMapping("/{dni}")
	public ResponseEntity<?> update(@PathVariable String dni, 
			@RequestBody Gestor gDTO){
		Optional<Gestor> optG = gestorService.findById(dni);
		if(optG.isPresent()) {
			Gestor g = optG.get();
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
