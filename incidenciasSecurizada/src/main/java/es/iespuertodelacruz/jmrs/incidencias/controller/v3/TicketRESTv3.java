package es.iespuertodelacruz.jmrs.incidencias.controller.v3;

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
import es.iespuertodelacruz.jmrs.incidencias.entity.Gestore;
import es.iespuertodelacruz.jmrs.incidencias.entity.Ticket;
import es.iespuertodelacruz.jmrs.incidencias.service.ClienteService;
import es.iespuertodelacruz.jmrs.incidencias.service.GestorService;
import es.iespuertodelacruz.jmrs.incidencias.service.SeguimientoTicketService;
import es.iespuertodelacruz.jmrs.incidencias.service.TicketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v3/tickets")
@Api (
		tags = "Interfaz de Tickets",
		description = "Muestra todos los metodos de los Tickets por parte del Gestor(v3)"
		)
public class TicketRESTv3 {

	@Autowired
	TicketService ticketService;
	
	@Autowired
	SeguimientoTicketService seguiTicketService;
	
	@Autowired
	GestorService gestorService;
	
	@Autowired 
	ClienteService clienteService;

	@GetMapping // si queremos subruta lleva paréntesis
	@ApiOperation(
			value = "Obtener Tickets",
			notes = "Devuelve todos los tickets que hay en la Base Datos"
			)
	public ResponseEntity<?> getAll() {
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		// logger.info("si queremos hacer debug por ejemplo");
		ticketService.findAll().forEach(p -> tickets.add((Ticket) p));
		return ResponseEntity.ok().body(tickets);
	}
	
	@GetMapping("/{id}")
	@ApiOperation(
			value = "Obtener Ticket ID",
			notes = "Devuelve toda la información del ticket por ID"
			)
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
	@ApiOperation(
			value = "Eliminar Ticket",
			notes = "Opcion para eliminar un ticket por ID"
			)
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
	@ApiOperation(
			value = "Crear Ticket",
			notes = "Creacion de ticket por el Gestor"
			)
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
		
		Gestore Gestore = new Gestore();
		Set<Gestore> equipoTrabajo = new HashSet<>();
		
		for(String dni : tDTO.getEquipoTrabajo()) {
			Optional<Gestore> Gestores = gestorService.findById(dni);
			if(Gestores.isPresent()) {
				Gestore = Gestores.get();
				equipoTrabajo.add(Gestore);
			}
		}

		t.setGestores(equipoTrabajo);
		
		ticketService.save(t);
		
		return ResponseEntity.ok().body(t);
	}
	
	@PutMapping("/{id}")
	@ApiOperation(
			value = "Actualizar Ticket",
			notes = "Actualizacion de ticket con un ID"
			)
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
			
			Gestore Gestore = new Gestore();
			Set<Gestore> equipoTrabajo = new HashSet<>();
			
			for(String dni : tDTO.getEquipoTrabajo()) {
				Optional<Gestore> optGestores = gestorService.findById(dni);
				if(optGestores.isPresent()) {
					Gestore = optGestores.get();
					equipoTrabajo.add(Gestore);
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
	
	@GetMapping("/tickets/abiertos")
	@ApiOperation(
			value = "Obtener todos los tickets abiertos",
			notes = "Devuelve todos los tickets abiertos"
			)
	public ResponseEntity<?> getOpenTickets() {
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		// logger.info("si queremos hacer debug por ejemplo");
		ticketService.findOpenTickets().forEach(p -> tickets.add((Ticket) p));
		return ResponseEntity.ok().body(tickets);
	}

}
