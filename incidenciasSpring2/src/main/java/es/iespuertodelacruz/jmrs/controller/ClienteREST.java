package es.iespuertodelacruz.jmrs.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.jmrs.entity.Cliente;
import es.iespuertodelacruz.jmrs.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteREST {

	@Autowired
	ClienteService clienteService;
	
	@GetMapping //si queremos subruta lleva paréntesis
	public ResponseEntity<?> getAll(){
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		//logger.info("si queremos hacer debug por ejemplo");
		clienteService.findAll().forEach(p -> clientes.add((Cliente) p) );
		
		return ResponseEntity.ok().body(clientes);
	}
	
}
