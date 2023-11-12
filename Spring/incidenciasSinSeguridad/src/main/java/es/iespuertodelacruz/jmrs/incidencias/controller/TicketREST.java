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

import es.iespuertodelacruz.jmrs.incidencias.dto.TicketPostDTO;
import es.iespuertodelacruz.jmrs.incidencias.entity.Cliente;
import es.iespuertodelacruz.jmrs.incidencias.entity.Gestor;
import es.iespuertodelacruz.jmrs.incidencias.entity.Ticket;
import es.iespuertodelacruz.jmrs.incidencias.service.ClienteService;
import es.iespuertodelacruz.jmrs.incidencias.service.GestorService;
import es.iespuertodelacruz.jmrs.incidencias.service.SeguimientoTicketService;
import es.iespuertodelacruz.jmrs.incidencias.service.TicketService;

@RestController
@RequestMapping("/api/tickets")
public class TicketREST {

	@Autowired
	TicketService ticketService;
	
	@Autowired
	SeguimientoTicketService seguiTicketService;
	
	@Autowired
	GestorService gestorService;
	
	@Autowired 
	ClienteService clienteService;

	@GetMapping // si queremos subruta lleva paréntesis
	public ResponseEntity<?> getAll() {
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		// logger.info("si queremos hacer debug por ejemplo");
		ticketService.findAll().forEach(p -> tickets.add((Ticket) p));
		return ResponseEntity.ok().body(tickets);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id){
		Optional<Ticket> tickets = ticketService.findById(id);
		
		if(tickets.isPresent()) {
			Ticket ticket = tickets.get();
			if(ticket != null) {
				return ResponseEntity.ok().body(ticket);
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ha habido un error");	
			}
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el id del registro "+
					"no existe");	
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTicketById(@PathVariable Integer id){
		Optional<Ticket> optT = ticketService.findById(id);
		
		if(optT.isPresent()) {/*
			Ticket ticket = optT.get();
			List<SeguimientoTicket> lsST = seguiTicketService.findByIdTicket(ticket.getIdTicket());
			
			for (SeguimientoTicket seguimientoTicket : lsST) {
				seguiTicketService.deleteById(seguimientoTicket.getIdSeguimiento());
			}
			*/
			ticketService.deleteById(id);
			return ResponseEntity.ok("El Ticket ha sido eliminado");
		}else {
		
			return
					ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el id del registro "+
							"no existe");
		}
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody TicketPostDTO tDTO){
		
		Ticket t = new Ticket();
		t.setIdTicket(tDTO.getIdTicket());
		t.setDescripcion(tDTO.getDescripcion());
		t.setEstado(tDTO.getEstado());
		t.setFechaInicio(tDTO.getFechaInicio());
		t.setFechaFin(tDTO.getFechaFin());
		
		Optional<Cliente> clienteTicket = clienteService.findById(tDTO.getIdCliente());
		if(clienteTicket.isPresent()) {
			t.setCliente(clienteTicket.get());
		}
		
		Gestor gestor = new Gestor();
		Set<Gestor> equipoTrabajo = new HashSet<>();
		
		for(String dni : tDTO.getEquipoTrabajo()) {
			Optional<Gestor> gestores = gestorService.findById(dni);
			if(gestores.isPresent()) {
				gestor = gestores.get();
				equipoTrabajo.add(gestor);
			}
		
		
		}

		t.setGestores(equipoTrabajo);
		
		ticketService.save(t);
		
		return ResponseEntity.ok().body(t);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, 
			@RequestBody TicketPostDTO tDTO){
		
		Optional<Ticket> optT = ticketService.findById(id);
		if(optT.isPresent()) {
			Ticket t = optT.get();
			t.setDescripcion(tDTO.getDescripcion());
			t.setEstado(tDTO.getEstado());
			t.setFechaInicio(tDTO.getFechaInicio());
			t.setFechaFin(tDTO.getFechaFin());
			
			if(tDTO.getIdCliente() != null) {
				Optional<Cliente> optCliente = 
						clienteService.findById(tDTO.getIdCliente());
				if(optCliente.isPresent()) {
					t.setCliente(optCliente.get());
				}
			}
			
			Gestor gestor = new Gestor();
			Set<Gestor> equipoTrabajo = new HashSet<>();
			
			for(String dni : tDTO.getEquipoTrabajo()) {
				Optional<Gestor> optGestores = gestorService.findById(dni);
				if(optGestores.isPresent()) {
					gestor = optGestores.get();
					equipoTrabajo.add(gestor);
				}			
			}
			
			if(!equipoTrabajo.isEmpty())
				t.setGestores(equipoTrabajo);

			ticketService.save(t);
			
			return ResponseEntity.ok().body(t);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El id del registro no existe");	
		}	
	}

}
