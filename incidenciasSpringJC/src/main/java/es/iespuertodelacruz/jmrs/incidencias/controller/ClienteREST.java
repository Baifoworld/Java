package es.iespuertodelacruz.jmrs.incidencias.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.jmrs.incidencias.entity.Cliente;
import es.iespuertodelacruz.jmrs.incidencias.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteREST {

	@Autowired
	ClienteService clienteService;

	@GetMapping // si queremos subruta lleva paréntesis
	public List<Cliente> getAll() {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		// logger.info("si queremos hacer debug por ejemplo");
		clienteService.findAll().forEach(p -> clientes.add((Cliente) p));
		return clientes;
	}
	
	@
	

}
