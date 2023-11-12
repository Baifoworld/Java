package es.iespuertodelacruz.jmrs.incidencias.models.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import es.iespuertodelacruz.jmrs.incidencias.models.daos.ClienteDAO;
import es.iespuertodelacruz.jmrs.incidencias.models.daos.GestorDAO;
import es.iespuertodelacruz.jmrs.incidencias.models.daos.SeguimientoDAO;
import es.iespuertodelacruz.jmrs.incidencias.models.daos.TicketDAO;
import es.iespuertodelacruz.jmrs.incidencias.models.entities.Cliente;
import es.iespuertodelacruz.jmrs.incidencias.models.entities.Gestor;
import es.iespuertodelacruz.jmrs.incidencias.models.entities.Seguimiento;
import es.iespuertodelacruz.jmrs.incidencias.models.entities.Ticket;

public class Main {

	public static void main(String[] args) {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		ArrayList<Gestor> gestores = new ArrayList<Gestor>();
		Cliente cliente;
		Gestor gestor;
		Ticket ticket;
		GestorConexionDDBB gc = new GestorConexionDDBB("incidencias","root","1q2w3e4r");
		ClienteDAO clienteDao = new ClienteDAO(gc);
		GestorDAO gestorDao = new GestorDAO(gc);
		Scanner sc = new Scanner(System.in);
		int aux= -1;
	
		//guardarTicket();
		//encontrarTicket();
		actualizarTicket();
		//mostrarTickets();
		//eliminarTicket();
		//actualizarSeguimiento();
		//crearSeguimiento();
		//eliminarSeguimiento();
		//encontrarCliente();
		
		while(aux != 0) {
			//estado = false;
			System.out.println("Introduce una opcion:\n "
					+ "1: añadir un cliente\n "
					+ "2: modificar un cliente\n"
					+ "3: Mostrar clientes\n"
					+ "4: Eliminar cliente \n"
					+ "5: añadir gestor\n"
					+ "6: modificar un gestor\n"
					+ "7: Mostrar gestores\n"
					+ "8: Eliminar cliente \n"
					+ "0: Salir");
			aux = sc.nextInt();
			switch(aux) {
				case 1:
					sc.nextLine(); 
					System.out.println("ID cliente quieres añadir:\n"
							+ "id del cliente:");
					String idCliente = sc.nextLine();
					System.out.println("nombre:");
					String nombre = sc.nextLine();
					System.out.println("direccion");
					String direccion = sc.nextLine();
					System.out.println("telefono");
					String telefono = sc.nextLine();
					cliente = new Cliente(idCliente, nombre, direccion,telefono);
					clienteDao.save(cliente);
					
					System.out.println("Cliente creado correctamente");
					break;
				case 2:
					sc.nextLine(); 
					System.out.println("ID cliente quieres añadir:\n"
							+ "id del cliente:");
					String idClientemodi = sc.nextLine();
					System.out.println("nombre:");
					String nombremodi = sc.nextLine();
					System.out.println("direccion");
					String direccionmodi = sc.nextLine();
					System.out.println("telefono");
					String telefonomodi = sc.nextLine();
					cliente = new Cliente(idClientemodi, nombremodi, direccionmodi,telefonomodi);
					clienteDao.update(cliente);
					System.out.println("cliente modificado");
					/*System.out.println("Introduce el id del lapiz que quieres eliminar:\n"
							+ "id del lapiz:");
					int idElim = sc.nextInt();
					sc.nextLine(); //limpiar el scanner
					System.out.println("El lapiz que quieres eliminar es el " + idElim + "\n"
							+ "Estas Seguro?(si/no):");
					String confirmar = sc.nextLine();
					if(confirmar.equalsIgnoreCase("si")) {
						estado = gl.eliminarLapiz(idElim);
						if(estado) {
							System.out.println("El lapiz con id: " + idElim + " se ha eliminado con exito");
						}
						else {
							System.out.println("El lapiz con id: " + idElim + " NO se ha podido eliminar");
						}
					}
					else {
						System.out.println("Se ha cancelado la eliminación del Lapiz con id: " + idElim);
					}*/
					break;
				case 3:
					clientes = clienteDao.selectAll();
					for (Cliente cliente2 : clientes) {
						System.out.println(cliente2.getIdCliente()+ "\n"
								+ cliente2.getNombreContacto() + "\n"
								+cliente2.getDireccion() + "\n"
								+cliente2.getTelefono() + "\n");
					}
					break;
				case 4:
					sc.nextLine();
					System.out.println("Introduce el id del cliente que quieres eliminar:\n"
							+ "id del cliente:");
					String idclienteElim = sc.nextLine();
					System.out.println("El cliente que quieres eliminar es el " + idclienteElim + "\n"
							+ "Estas Seguro?(si/no):");
					String confirmar = sc.nextLine();
					if(confirmar.equalsIgnoreCase("si")) {
						boolean estado = clienteDao.delete(idclienteElim);
						
						if(estado) {
							System.out.println("El lapiz con id: " + idclienteElim + " se ha eliminado con exito");
						}
						else {
							System.out.println("El lapiz con id: " + idclienteElim + " NO se ha podido eliminar");
						}
					}
					else {
						System.out.println("Se ha cancelado la eliminación del Lapiz con id: " + idclienteElim);
					}
					break;
				case 5:
					sc.nextLine(); 
					System.out.println("DNI gestor quieres añadir:\n"
							+ "dni del gestor:");
					String dniGestor = sc.nextLine();
					System.out.println("nombre:");
					String nombreGestor = sc.nextLine();
					System.out.println("apellidos");
					String apellidos = sc.nextLine();
					gestor = new Gestor(dniGestor, nombreGestor, apellidos);
					gestorDao.save(gestor);
					break;
				case 6:
					sc.nextLine(); 
					System.out.println("DNI gestor quieres añadir:\n"
							+ "dni del gestor:");
					String dnigestorModi = sc.nextLine();
					System.out.println("nombre:");
					String nombreModi = sc.nextLine();
					System.out.println("apellido");
					String apellidoModi = sc.nextLine();
					gestor = new Gestor(dnigestorModi, nombreModi, apellidoModi);
					gestorDao.update(gestor);
					System.out.println("gestor modificado");
					break;
				case 7:
					gestores = gestorDao.selectAll();
					for (Gestor gestor2 : gestores) {
						System.out.println(gestor2.getDNI()+ "\n"
								+ gestor2.getNombre() + "\n"
								+gestor2.getApellidos() + "\n");
					}
					break;
				case 8:
					sc.nextLine();
					System.out.println("Introduce el dni del gestor que quieres eliminar:\n"
							+ "dni del gestor:");
					String idgestorElim = sc.nextLine();
					System.out.println("El gestor que quieres eliminar es el " + idgestorElim + "\n"
							+ "Estas Seguro?(si/no):");
					String confirmar2 = sc.nextLine();
					if(confirmar2.equalsIgnoreCase("si")) {
						boolean estado = gestorDao.delete(idgestorElim);
						
						if(estado) {
							System.out.println("El lapiz con id: " + idgestorElim + " se ha eliminado con exito");
						}
						else {
							System.out.println("El lapiz con id: " + idgestorElim + " NO se ha podido eliminar");
						}
					}
					else {
						System.out.println("Se ha cancelado la eliminación del Lapiz con id: " + idgestorElim);
					}
					break;
				case 0:
					System.out.println("Ha finalizado la app, buen dia.");
					break;
			}
			
		}
	}
	
	public static void guardarTicket() {
		GestorConexionDDBB gc = new GestorConexionDDBB("incidencias","root","1q2w3e4r");
		ClienteDAO clienteDao = new ClienteDAO(gc);
		GestorDAO gestorDao = new GestorDAO(gc);
		TicketDAO ticketDao = new TicketDAO(gc);
		Scanner sc = new Scanner(System.in); ;
		Cliente cliente;
		Ticket ticket;
		Gestor gestor;
		ArrayList<Gestor> equipoTrabajo = new ArrayList<>();
		
		System.out.println("fecha inicio");
		String inicio = sc.nextLine();
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date dateInicio = null;
		try {
			dateInicio = formatter.parse(inicio);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		System.out.println("descripcion");
		String descripcion = sc.nextLine();
		System.out.println("estado");
		String estado = sc.nextLine();
		System.out.println("cliente");
		String idCliente = sc.nextLine();
		cliente = clienteDao.findById(idCliente);
		System.out.println("dni Gestores");
		String dnigestor = sc.nextLine();
		String[] gestores = dnigestor.split(",");
		for(int i = 0; i < gestores.length ; i++) {
			gestor = gestorDao.findById(gestores[i]);
			if(gestor != null)
				equipoTrabajo.add(gestor);
		}
		
		ticket = new Ticket(dateInicio, descripcion, estado, cliente, equipoTrabajo);
		ticketDao.save(ticket);
	}
	
	public static void encontrarTicket() {
		GestorConexionDDBB gc = new GestorConexionDDBB("incidencias","root","1q2w3e4r");
		ClienteDAO clienteDao = new ClienteDAO(gc);
		GestorDAO gestorDao = new GestorDAO(gc);
		TicketDAO ticketDao = new TicketDAO(gc);
		Scanner sc = new Scanner(System.in); ;
		Cliente cliente;
		Ticket ticket;
		
		System.out.println("añadir id del ticket");
		int id = sc.nextInt();
		ticket = ticketDao.findById(id);
		System.out.println();
		System.out.println(ticket.getIdTicket()+ " ," + ticket.getFechaInicio() +", "
		+ ticket.getFechaFin()+"," + ticket.getDescripcion() + ","
				+ ticket.getCliente().getIdCliente());
		
	}
	
	public static void actualizarTicket() {
		
		GestorConexionDDBB gc = new GestorConexionDDBB("incidencias","root","1q2w3e4r");
		ClienteDAO clienteDao = new ClienteDAO(gc);
		GestorDAO gestorDao = new GestorDAO(gc);
		TicketDAO ticketDao = new TicketDAO(gc);
		Scanner sc = new Scanner(System.in); ;
		Cliente cliente;
		Ticket ticket;
		Gestor gestor;
		ArrayList<Gestor> equipoTrabajo = new ArrayList<>();
		
		System.out.println("id ticket quieres modificar:\n"
				+ "id ticket:");
		int idModi = sc.nextInt();
		sc.nextLine();
		System.out.println("inicio:");
		String inicioModi = sc.nextLine();
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date dateInicio = null;
		try {
			dateInicio = formatter.parse(inicioModi);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		System.out.println("final");
		String finModi = sc.nextLine();
		Date dateFin = null;
		try {
			dateFin = formatter.parse(finModi);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		System.out.println("descripcion");
		String descripcion = sc.nextLine();
		System.out.println("estado");
		String estado = sc.nextLine();
		System.out.println("cliente");
		String idCliente = sc.nextLine();
		cliente = clienteDao.findById(idCliente);
		System.out.println("dni Gestores");
		String dnigestor = sc.nextLine();
		String[] gestores = dnigestor.split(",");
		for(int i = 0; i < gestores.length ; i++) {
			gestor = gestorDao.findById(gestores[i]);
			if(gestor != null)
				equipoTrabajo.add(gestor);
		}
		ticket = new Ticket(idModi, dateInicio, dateFin, descripcion, estado, cliente, equipoTrabajo);
		boolean ok = ticketDao.update(ticket);
		if(ok)
		System.out.println("ticket modificado");
		else{
			System.out.println("no se ha modificado");
		}
	}
	
	public static void mostrarTickets() {
		GestorConexionDDBB gc = new GestorConexionDDBB("incidencias","root","1q2w3e4r");
		TicketDAO ticketDao = new TicketDAO(gc);
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		tickets = ticketDao.selectAll();
		for (Ticket ticket : tickets) {
			System.out.println(ticket.getIdTicket()+ "\n"
					+ ticket.getNormalFechaInicio() + "\n"
					+ticket.getNormalFechaFin() + "\n"
					+ticket.getDescripcion() + "\n"
					+ticket.getEstado() + "\n"
					+ticket.getCliente().getIdCliente() + "\n");
		}
	}
	
	public static void eliminarTicket() {
		GestorConexionDDBB gc = new GestorConexionDDBB("incidencias","root","1q2w3e4r");
		Scanner sc = new Scanner(System.in); 
		ClienteDAO clienteDao = new ClienteDAO(gc);
		GestorDAO gestorDao = new GestorDAO(gc);
		TicketDAO ticketDao = new TicketDAO(gc);
		Cliente cliente;
		Ticket ticket;
		System.out.println("Introduce id ticket eliminar:\n");
		
		int idticketElim = sc.nextInt();
		sc.nextLine();
		System.out.println("El ticket que quieres eliminar es el " + idticketElim + "\n"
				+ "Estas Seguro?(si/no):");
		String confirmar2 = sc.nextLine();
		if(confirmar2.equalsIgnoreCase("si")) {
			boolean estado = ticketDao.delete(idticketElim);
			
			if(estado) {
				System.out.println("El ticket con id: " + idticketElim + " se ha eliminado con exito");
			}
			else {
				System.out.println("El ticket con id: " + idticketElim + " NO se ha podido eliminar");
			}
		}
		else {
			System.out.println("Se ha cancelado la eliminación del Lapiz con id: " + idticketElim);
		}
	}
	
	//FIn tickets
	//comienzo de seguimientos
	
	public static void actualizarSeguimiento() {
		
		GestorConexionDDBB gc = new GestorConexionDDBB("incidencias","root","1q2w3e4r");
		SeguimientoDAO seguimientoDao = new SeguimientoDAO(gc);
		GestorDAO gestorDao = new GestorDAO(gc);
		TicketDAO ticketDao = new TicketDAO(gc);
		Scanner sc = new Scanner(System.in); ;
		Seguimiento seguimiento;
		Gestor gestor;
		Ticket ticket = null;
		
		System.out.println("id seguimiento quieres modificar:\n"
				+ "id seguimiento:");
		int idModi = sc.nextInt();
		sc.nextLine();
		System.out.println("fecha:");
		String fechaModi = sc.nextLine();
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date fecha = null;
		try {
			fecha = formatter.parse(fechaModi);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		System.out.println("comentario");
		String comentario = sc.nextLine();
		System.out.println("idTicket");
		int idTicketMOdi = sc.nextInt();
		ticket = ticketDao.findById(idTicketMOdi);
		System.out.println("dni gestor");
		String dniGestorModi = sc.nextLine();
		gestor = gestorDao.findById(dniGestorModi);
		seguimiento = new Seguimiento(idModi, fecha, comentario, gestor, ticket);
		boolean ok = seguimientoDao.update(seguimiento);
		if(ok)
		System.out.println("seguimiento modificado");
		else{
			System.out.println("no se ha modificado");
		}
	}
	
	public static void crearSeguimiento() {
		
		GestorConexionDDBB gc = new GestorConexionDDBB("incidencias","root","1q2w3e4r");
		SeguimientoDAO seguimientoDao = new SeguimientoDAO(gc);
		GestorDAO gestorDao = new GestorDAO(gc);
		TicketDAO ticketDao = new TicketDAO(gc);
		Scanner sc = new Scanner(System.in); ;
		Seguimiento seguimiento;
		Gestor gestor;
		Ticket ticket;
		
		System.out.println("id ticket  quieres crear seguimiento :\n"
				+ "id ticket:");
		int idTicketCrear = sc.nextInt();
		ticket = ticketDao.findById(idTicketCrear);
		sc.nextLine();
/*
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date fecha = null;
		try {
			fecha = formatter.parse(fechaCrear);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			*/
		System.out.println("comentario");
		String comentario = sc.nextLine();
		System.out.println("dni gestor");
		String dniGestor = sc.nextLine();
		gestor = gestorDao.findById(dniGestor);
		seguimiento = new Seguimiento(ticket.getFechaInicio(), comentario, gestor, ticket);
		seguimientoDao.save(seguimiento);
		
	}
	
	public static void eliminarSeguimiento() {
		
		GestorConexionDDBB gc = new GestorConexionDDBB("incidencias","root","1q2w3e4r");
		Scanner sc = new Scanner(System.in); 
		SeguimientoDAO seguimientoDao = new SeguimientoDAO(gc);
		GestorDAO gestorDao = new GestorDAO(gc);
		TicketDAO ticketDao = new TicketDAO(gc);
		Seguimiento seguimiento;
		Ticket ticket;
		System.out.println("Introduce id seguimiento eliminar:\n");
		
		int idseguiElim = sc.nextInt();
		sc.nextLine();
		System.out.println("El seguimiento que quieres eliminar es el " + idseguiElim + "\n"
				+ "Estas Seguro?(si/no):");
		String confirmar2 = sc.nextLine();
		if(confirmar2.equalsIgnoreCase("si")) {
			boolean estado = seguimientoDao.delete(idseguiElim);
			
			if(estado) {
				System.out.println("El seguimiento con id: " + idseguiElim + " se ha eliminado con exito");
			}
			else {
				System.out.println("El ticket con id: " + idseguiElim + " NO se ha podido eliminar");
			}
		}
		else {
			System.out.println("Se ha cancelado la eliminación del Lapiz con id: " + idseguiElim);
		}
	}
	
	//fin seguimientos
	//comienzo clientes
	
	public static void encontrarCliente() {
		GestorConexionDDBB gc = new GestorConexionDDBB("incidencias","root","1q2w3e4r");
		ClienteDAO clienteDao = new ClienteDAO(gc);
		Cliente cliente;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("id de cliente a buscar");
		String idCliente = sc.nextLine();
		
		cliente = clienteDao.findById(idCliente);
		
		System.out.println(cliente.getIdCliente() + " " 
		+ cliente.getNombreContacto() + " "
		+ cliente.getDireccion() + " " 
		+ cliente.getTelefono());
	}

}
