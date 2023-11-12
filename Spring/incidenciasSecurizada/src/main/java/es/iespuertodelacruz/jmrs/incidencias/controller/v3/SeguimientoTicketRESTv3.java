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

import es.iespuertodelacruz.jmrs.incidencias.entity.SeguimientoTicket;
import es.iespuertodelacruz.jmrs.incidencias.service.SeguimientoTicketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v3/seguimientotickets")
@Api (
		tags = "Interfaz del Seguimiento Ticket",
		description = "Muestra todos los metodos de Seguimiento Ticket de parte del Gestor(v3)"
		)
public class SeguimientoTicketRESTv3 {

	@Autowired
	SeguimientoTicketService seguiTicketService;

	@GetMapping // si queremos subruta lleva paréntesis
	@ApiOperation(
			value = "Obtener Seguimientos Tickets",
			notes = "Devuelve todos los seguimientos tickets que hay en la Base de Datos"
			)
	public ResponseEntity<?> getAll() {
		ArrayList<SeguimientoTicket> seguiTicket = new ArrayList<SeguimientoTicket>();
		// logger.info("si queremos hacer debug por ejemplo");
		seguiTicketService.findAll().forEach(p -> seguiTicket.add((SeguimientoTicket) p));
		return ResponseEntity.ok().body(seguiTicket);
	}
	
	@GetMapping("/{id}")
	@ApiOperation(
			value = "Obtener Seguimientos Tickets de cliente",
			notes = "Devuelve todos los seguimientos tickets de un cliente en concreto por ID"
			)
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
	@ApiOperation(
			value = "Eliminar Seguimiento Ticket",
			notes = "Eliminacion del Seguimiento Ticket con el id"
			)
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
	@ApiOperation(
			value = "Creacion Seguimiento Ticket",
			notes = "Crear seguimiento Ticket por el Gestor"
			)
	public ResponseEntity<?> save(@RequestBody SeguimientoTicket stDTO){
		SeguimientoTicket st = new SeguimientoTicket();
		st.setIdSeguimiento(stDTO.getIdSeguimiento());
		st.setComentario(stDTO.getComentario());
		st.setFecha(stDTO.getFecha());
		st.setGestore(stDTO.getGestore());
		st.setTicket(stDTO.getTicket());
		seguiTicketService.save(st);
		
		return ResponseEntity.ok().body(st);
	}
	
	@PutMapping("/{id}")
	@ApiOperation(
			value = "Actualizar Seguimiento Ticket",
			notes = "Actualizacion de los datos de Seguimiento Ticket por parte del Gestor"
			)
	public ResponseEntity<?> update(@PathVariable Integer id, 
			@RequestBody SeguimientoTicket stDTO){
		
		Optional<SeguimientoTicket> optST = seguiTicketService.findById(id);
		
		if(optST.isPresent()) {
			SeguimientoTicket st = optST.get();
			st.setIdSeguimiento(stDTO.getIdSeguimiento());
			st.setComentario(stDTO.getComentario());
			st.setFecha(stDTO.getFecha());
			st.setGestore(stDTO.getGestore());
			st.setTicket(stDTO.getTicket());
			return ResponseEntity.ok(seguiTicketService.save(st));
		
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El id del registro no existe");	
		}
	}
}
