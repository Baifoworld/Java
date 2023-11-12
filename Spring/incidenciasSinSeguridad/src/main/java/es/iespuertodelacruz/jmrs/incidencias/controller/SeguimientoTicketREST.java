package es.iespuertodelacruz.jmrs.incidencias.controller;

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

import es.iespuertodelacruz.jmrs.incidencias.dto.SeguimientoTicketPostDTO;
import es.iespuertodelacruz.jmrs.incidencias.entity.Gestor;
import es.iespuertodelacruz.jmrs.incidencias.entity.SeguimientoTicket;
import es.iespuertodelacruz.jmrs.incidencias.entity.Ticket;
import es.iespuertodelacruz.jmrs.incidencias.service.GestorService;
import es.iespuertodelacruz.jmrs.incidencias.service.SeguimientoTicketService;
import es.iespuertodelacruz.jmrs.incidencias.service.TicketService;

@RestController
@RequestMapping("/api/seguimientotickets")
public class SeguimientoTicketREST {

	@Autowired
	SeguimientoTicketService seguiTicketService;
	
	@Autowired
	GestorService gestorService;
	
	@Autowired
	TicketService ticketService;

	@GetMapping // si queremos subruta lleva paréntesis
	public ResponseEntity<?> getAll() {
		ArrayList<SeguimientoTicket> seguiTicket = new ArrayList<SeguimientoTicket>();
		// logger.info("si queremos hacer debug por ejemplo");
		seguiTicketService.findAll().forEach(p -> seguiTicket.add((SeguimientoTicket) p));
		return ResponseEntity.ok().body(seguiTicket);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id){
		Optional<SeguimientoTicket> seguiTickets = seguiTicketService.findById(id);
		if(seguiTickets.isPresent()) {
			SeguimientoTicket segui = seguiTickets.get();
			if(segui != null) {
				return ResponseEntity.ok().body(segui);
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ha habido un error");	
			}
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el id del registro "+
					"no existe");	
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteSeguiById(@PathVariable Integer id){
		Optional<SeguimientoTicket> optST = seguiTicketService.findById(id);
		
		if(optST.isPresent()) {
			seguiTicketService.deleteById(id);
			return ResponseEntity.ok("El Seguimiento de Ticket ha sido eliminado");
		}else {
		
			return
					ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el id del registro "+
							"no existe");
		}
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody SeguimientoTicketPostDTO stDTO){
		Optional<Ticket> opTicket = ticketService.findById(stDTO.getIdTicket());
		
		if(opTicket.isPresent()) {
			SeguimientoTicket st = new SeguimientoTicket();
			st.setTicket(opTicket.get());
			st.setComentario(stDTO.getComentario());
			st.setFecha(stDTO.getFecha());

			Optional<Gestor> gestorId = gestorService.findById(stDTO.getDniGestor());
			if(gestorId.isPresent()) {
				st.setGestore(gestorId.get());	
			}
			
			seguiTicketService.save(st);
			
			return ResponseEntity.ok().body(st);
	
		} else {
			return
					ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el id del registro "+
							"no existe");
		}
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, 
			@RequestBody SeguimientoTicket stDTO){
		Optional<SeguimientoTicket> optST = seguiTicketService.findById(id);
		
		if(optST.isPresent()) {
			SeguimientoTicket st = optST.get();
			st.setComentario(stDTO.getComentario());
			st.setFecha(stDTO.getFecha());
			return ResponseEntity.ok(seguiTicketService.save(st));
		
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El id del registro no existe");	
		}
		
	}

}
