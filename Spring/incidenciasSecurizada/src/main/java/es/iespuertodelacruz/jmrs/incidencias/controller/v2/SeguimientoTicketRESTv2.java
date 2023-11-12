package es.iespuertodelacruz.jmrs.incidencias.controller.v2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.jmrs.incidencias.entity.Cliente;
import es.iespuertodelacruz.jmrs.incidencias.entity.Gestore;
import es.iespuertodelacruz.jmrs.incidencias.entity.SeguimientoTicket;
import es.iespuertodelacruz.jmrs.incidencias.service.ClienteService;
import es.iespuertodelacruz.jmrs.incidencias.service.GestorService;
import es.iespuertodelacruz.jmrs.incidencias.service.SeguimientoTicketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v2/seguimientotickets")
@Api (
		tags = "Interfaz del Seguimiento Ticket",
		description = "Muestra todos los metodos de Seguimiento Ticket de parte del cliente logueado(v2)"
		)
public class SeguimientoTicketRESTv2 {

	@Autowired
	SeguimientoTicketService seguiTicketService;
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	GestorService gestorService;
	
	@GetMapping("/{id}")
	@ApiOperation(
			value = "Obtener Seguimientos Tickets de cliente",
			notes = "Devuelve todos los seguimientos tickets de un cliente en concreto por ID"
			)
	public ResponseEntity<?> findByClienteId(@PathVariable Integer id){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String nombreAutenticado = ((UserDetails)principal).getUsername();
		Optional<Cliente> optCliente = clienteService.findByNick(nombreAutenticado);
		
		if(optCliente.isPresent()) {
			Cliente c = optCliente.get();
			Optional<SeguimientoTicket> seguiTickets = seguiTicketService.findById(id);
			if(seguiTickets.isPresent()) {
				SeguimientoTicket segui = seguiTickets.get();
				if(segui != null) {
					if(c.getIdCliente().equals(segui.getTicket().getCliente().getIdCliente())) {
						return ResponseEntity.ok().body(segui);	
					}else {
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El id de seguimiento "
								+ "no corresponde al cliente");
					}
					
				}else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ha habido un error");	
				}
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el id del registro "+
						"no existe");	
			}
		}else {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el id del registro "+
				"no existe");	
		}
		
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(
			value = "Eliminar Seguimiento Ticket",
			notes = "Eliminacion del Seguimiento Ticket con el id y el id del cliente logueado"
			)
	public ResponseEntity<?> deleteSeguiById(@PathVariable Integer id){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String nombreAutenticado = ((UserDetails)principal).getUsername();
		Optional<Cliente> optCliente = clienteService.findByNick(nombreAutenticado);
		
		if(optCliente.isPresent()) {
			Cliente c = optCliente.get();
			Optional<SeguimientoTicket> seguiTickets = seguiTicketService.findById(id);
			if(seguiTickets.isPresent()) {
				SeguimientoTicket segui = seguiTickets.get();
				if(segui != null) {
					if(c.getIdCliente().equals(segui.getTicket().getCliente().getIdCliente())) {
						seguiTicketService.deleteById(id);
						return ResponseEntity.ok("El Seguimiento de Ticket ha sido eliminado");	
					}else {
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El id de seguimiento "
								+ "no corresponde al cliente");
					}
					
				}else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ha habido un error");	
				}
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el id del registro "+
						"no existe");	
			}
		}else {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("el id del registro "+
				"no existe");	
		}
	}
	
	@PostMapping
	@ApiOperation(
			value = "Creacion Seguimiento Ticket",
			notes = "Crear seguimiento Ticket por el Gestor"
			)
	public ResponseEntity<?> save(@RequestBody SeguimientoTicket stDTO){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String nombreAutenticado = ((UserDetails)principal).getUsername();
		Optional<Cliente> optCliente = clienteService.findByNick(nombreAutenticado);
		
		if(optCliente.isPresent()) {
			
			Cliente c = optCliente.get();
			if(c.getIdCliente().equals(stDTO.getTicket().getCliente().getIdCliente())) {
				SeguimientoTicket st = new SeguimientoTicket();
				st.setIdSeguimiento(stDTO.getIdSeguimiento());
				st.setComentario(stDTO.getComentario());
				st.setFecha(stDTO.getFecha());
				
				Gestore gestor = new Gestore();
				
				ArrayList<Gestore> gestores = new ArrayList<Gestore>();
				gestorService.findAll().forEach(p -> gestores.add((Gestore) p));
				
				//Recoge el primer gestor de la base de datos y lo añade a equipo trabajo
				if(!gestores.isEmpty()) {
					for (Gestore gestorAl : gestores) {
						gestor = gestorAl;
						break;
					}
				}
				st.setGestore(gestor);
				st.setTicket(stDTO.getTicket());
				seguiTicketService.save(st);
				return ResponseEntity.ok().body(st);
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El id de seguimiento "
						+ "no corresponde al cliente");
			}
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ha habido un error");
		}
	}
}
