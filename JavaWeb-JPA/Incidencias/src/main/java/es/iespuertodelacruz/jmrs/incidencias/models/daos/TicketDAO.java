package es.iespuertodelacruz.jmrs.incidencias.models.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Logger;

import es.iespuertodelacruz.jmrs.incidencias.contracts.EquiposTrabajoEntry;
import es.iespuertodelacruz.jmrs.incidencias.contracts.SeguimientoEntry;
import es.iespuertodelacruz.jmrs.incidencias.contracts.TicketEntry;
import es.iespuertodelacruz.jmrs.incidencias.models.entities.Cliente;
import es.iespuertodelacruz.jmrs.incidencias.models.entities.Gestor;
import es.iespuertodelacruz.jmrs.incidencias.models.entities.Ticket;
import es.iespuertodelacruz.jmrs.incidencias.models.utils.GestorConexionDDBB;

public class TicketDAO extends TicketEntry implements CRUD<Ticket, Integer>{

	GestorConexionDDBB gc;
	
	public TicketDAO(GestorConexionDDBB gc) {
		this.gc = gc;
	}
	
	public TicketDAO(String ddbb, String user, String pass) {
		this.gc = new GestorConexionDDBB(ddbb,user,pass);
	}
	
	@Override
	public Ticket save(Ticket dao) {
		Logger log = Logger.getLogger("debugmiapp");
		Ticket newTicket = null;
		ClienteDAO clienteDao = new ClienteDAO(gc);
		GestorDAO gestorDao = new GestorDAO(gc);
		Cliente cliente = clienteDao.findById(dao.getCliente().getIdCliente());
		Gestor gestor = null;
		ArrayList<Gestor> gestores = gestorDao.selectAll();
		ArrayList<Gestor> etrabajo =  new ArrayList<>();
		int crea = 0;
		
		if(cliente != null) {
			
			String queryTicket="INSERT INTO " + TABLE_NAME + 
					" (" + FECHA_INICIO + ", " 
					+ FECHA_FIN + ", " 
					+ DESCRIPCION + ", "
					+ ESTADO + ", "
					+ ID_CLIENTE + ") VALUES (?,?,?,?,?)";
			/*
			String querySeguimiento = "INSERT INTO " + SeguimientoEntry.TABLE_NAME + " ("
					+ SeguimientoEntry.ID_TICKET + ", "
					+ SeguimientoEntry.FECHA + ", "
					+ SeguimientoEntry.DNI_GESTOR + ") VALUES (?,?,?,?,?)";*/
			
			String queryEquipo = "INSERT INTO " + EquiposTrabajoEntry.TABLE_NAME + " ("
					+ EquiposTrabajoEntry.ID_TICKET + ", "
					+ EquiposTrabajoEntry.DNI + ") VALUES (?,?)";
			
			try (
					Connection cn = gc.getConnection();
					PreparedStatement psTicket = cn.prepareStatement(queryTicket, PreparedStatement.RETURN_GENERATED_KEYS);
					//PreparedStatement psSeguimiento = cn.prepareStatement(queryEquipo, PreparedStatement.RETURN_GENERATED_KEYS);
					PreparedStatement psEquipo = cn.prepareStatement(queryEquipo);
			){
					
				cn.setAutoCommit(false);
				
				if(dao.getFechaInicio() == null) {
					psTicket.setNull(1, java.sql.Types.TIMESTAMP);
				}
				else {
					Timestamp dateFormatedToTimeStamp = new Timestamp(dao.getFechaInicio().getTime());
					psTicket.setTimestamp(1, dateFormatedToTimeStamp);
				}
				
				if(dao.getFechaFin() == null) {
					psTicket.setNull(2, java.sql.Types.TIMESTAMP);
				}
				else {
					Timestamp dateFormatedToTimeStamp = new Timestamp(dao.getFechaFin().getTime());
					psTicket.setTimestamp(2, dateFormatedToTimeStamp);
				}

				psTicket.setString(3, dao.getDescripcion());
				psTicket.setString(4, dao.getEstado());				
				psTicket.setString(5, dao.getCliente().getIdCliente());
				int affectedRows = psTicket.executeUpdate();
				
				boolean ok = affectedRows > 0;
				
				ResultSet rs = psTicket.getGeneratedKeys();
				
				if(ok && rs.next()) {
					newTicket = new Ticket();
					newTicket.setIdTicket(rs.getInt(1));
				}
					newTicket.setFechaInicio(dao.getFechaInicio());
					newTicket.setFechaFin(dao.getFechaFin());
					newTicket.setDescripcion(dao.getDescripcion());
					newTicket.setEstado(dao.getEstado());
					newTicket.setCliente(dao.getCliente());
					newTicket.setEquipoTrabajo(dao.getEquipoTrabajo());
				
				if(newTicket != null) {
					etrabajo = newTicket.getEquipoTrabajo();
					boolean okEquipo = false;
					if(etrabajo.size() > 0) {
						for(Gestor check: etrabajo) {
							String ticketGestor = check.getDNI();
							gestor = gestorDao.findById(ticketGestor);
							if(gestor != null ) {
								psEquipo.setInt(1, newTicket.getIdTicket());
								psEquipo.setString(2, ticketGestor);
								crea = psEquipo.executeUpdate();
							}
							else {
								cn.rollback();
								newTicket = null;
								System.out.println("No existe el gestor con ese DNI y no se ha creado Equipo de Trabajo");
							}
							okEquipo = crea > 0;
						}
					}
					if(okEquipo) {
						cn.commit();
					} else {
						cn.rollback();
					}
				} else {
					cn.rollback();
				}
				
				log.info(queryTicket);				
				cn.setAutoCommit(true);
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return newTicket;
	}
	
	@Override
	public Ticket findById(Integer id) {
		ClienteDAO clienteDao = new ClienteDAO(gc);
		Cliente cliente;
		GestorDAO gestorDao = new GestorDAO(gc);
		Logger log = Logger.getLogger("debugmiapp");
		Ticket newTicket = null;
		
		String queryTicket="SELECT * FROM " + TABLE_NAME + 
				" WHERE " + ID_TICKET + " = ?";
		
		String queryEquipo = "SELECT * FROM ";
		
		String querySeguimiento = null;
		try (
				Connection cn = gc.getConnection();
				PreparedStatement ps = cn.prepareStatement(queryTicket);
		){
			
			
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery(); 
			
			while(rs.next()) {
				newTicket = new Ticket();
				
				newTicket.setIdTicket(rs.getInt(ID_TICKET)); 
				newTicket.setFechaInicio(rs.getTimestamp(FECHA_INICIO));
				newTicket.setFechaFin(rs.getTimestamp(FECHA_FIN));
				newTicket.setDescripcion(rs.getString(DESCRIPCION));
				newTicket.setEstado(rs.getString(ESTADO));
				cliente = clienteDao.findById(rs.getString(ID_CLIENTE));
				newTicket.setCliente(cliente);		
			}
			
			if(newTicket == null) {
				System.out.println("No se ha creado un nuevo Ticket");
			}
			log.info(queryTicket);	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return newTicket;
	}
	
	@Override
	public boolean update(Ticket dao) {
		GestorDAO gestorDao = new GestorDAO(gc);
		Gestor gestor = null;
		boolean okUpdate = false;
		int okDelete;
		int crea = 0;
		ArrayList<Gestor> etrabajo =  new ArrayList<>();
		Logger log = Logger.getLogger("debugmiapp");
		
		String queryDeleteEquipo = "DELETE FROM " + EquiposTrabajoEntry.TABLE_NAME 
				+ " WHERE " + EquiposTrabajoEntry.ID_TICKET + " = ?"; 
		
		String queryUpdateTicket = "UPDATE " + TABLE_NAME  
				+ " SET " + FECHA_INICIO + " = ?,"
				+ FECHA_FIN + " = ?,"
				+ DESCRIPCION + " = ?," 
				+ ESTADO + " = ?,"
				+ ID_CLIENTE + " = ?"
				+ " WHERE " + ID_TICKET + " = ?";
		
		String queryInsertEquipo = "INSERT INTO " + EquiposTrabajoEntry.TABLE_NAME + " ("
				+ EquiposTrabajoEntry.ID_TICKET + ", "
				+ EquiposTrabajoEntry.DNI + ") VALUES (?,?)";
				
		try (
				Connection cn = gc.getConnection();
				PreparedStatement psDeleteEquipo = cn.prepareStatement(queryDeleteEquipo);
				PreparedStatement psUpdateTicket = cn.prepareStatement(queryUpdateTicket);
				PreparedStatement psInsertEquipo = cn.prepareStatement(queryInsertEquipo);
		){
			
			cn.setAutoCommit(false);
			psDeleteEquipo.setInt(1, dao.getIdTicket());
			
			okDelete = psDeleteEquipo.executeUpdate();
			
			if(okDelete > 0) {
				
				if(dao.getFechaInicio() == null) {
					psUpdateTicket.setNull(1, java.sql.Types.TIMESTAMP);
				}
				else {
					Timestamp dateFormatedToTimeStamp = new Timestamp(dao.getFechaInicio().getTime());
					psUpdateTicket.setTimestamp(1, dateFormatedToTimeStamp);
				}
				
				if(dao.getFechaFin() == null) {
					psUpdateTicket.setNull(2, java.sql.Types.TIMESTAMP);
				}
				else {
					Timestamp dateFormatedToTimeStamp = new Timestamp(dao.getFechaFin().getTime());
					psUpdateTicket.setTimestamp(2, dateFormatedToTimeStamp);
				}
				
				psUpdateTicket.setString(3, dao.getDescripcion());
				psUpdateTicket.setString(4, dao.getEstado());
				psUpdateTicket.setString(5, dao.getCliente().getIdCliente());
				psUpdateTicket.setInt(6, dao.getIdTicket());
				
				int affectedRows = psUpdateTicket.executeUpdate();
				
				if(affectedRows > 0) {
					okUpdate = true;
				}
				else {
					okUpdate = false;
					throw new SQLException("No se ha podido actualizar el Ticket");
				}
				
				if(okUpdate) {
					if(dao.getEquipoTrabajo() != null) {
						etrabajo = dao.getEquipoTrabajo();
						boolean okEquipo = false;
						if(etrabajo.size() > 0) {
							for(Gestor check: etrabajo) {
								String ticketGestor = check.getDNI();
								gestor = gestorDao.findById(ticketGestor);
								if(gestor != null ) {
									psInsertEquipo.setInt(1, dao.getIdTicket());
									psInsertEquipo.setString(2, ticketGestor);
									crea = psInsertEquipo.executeUpdate();
								}
								else {
									cn.rollback();
									okUpdate = false;
									System.out.println("No existe el gestor con ese DNI y no se ha creado Equipo de Trabajo");
								}
								okEquipo = crea > 0;
							}
						}
						if(okEquipo) {
							cn.commit();
							okUpdate = true;
						} else {
							cn.rollback();
						}
					} else {
						cn.rollback();
					}
				}
			}	
			
			log.info(queryUpdateTicket);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return okUpdate;
		}
		return okUpdate;
	}
	
	@Override
	public boolean delete(Integer id) {
		Logger log = Logger.getLogger("debugmiapp");
		boolean ok = false;
		
		String queryTicket="DELETE FROM " + TABLE_NAME + " WHERE " + ID_TICKET + " = ?";
		
		String querySeguimiento = "DELETE FROM " + SeguimientoEntry.TABLE_NAME 
				+ " WHERE " + SeguimientoEntry.ID_TICKET + " = ?";
		
		String queryEquipo = "DELETE FROM " + EquiposTrabajoEntry.TABLE_NAME 
				+ " WHERE " + EquiposTrabajoEntry.ID_TICKET + " = ?";
		
		try (
				Connection cn = gc.getConnection();
				PreparedStatement psEquipo = cn.prepareStatement(queryEquipo);
				PreparedStatement psSeguimiento = cn.prepareStatement(querySeguimiento);
				PreparedStatement psTicket = cn.prepareStatement(queryTicket);
				
				
		){
			psSeguimiento.setInt(1, id);
			int arsegui = psSeguimiento.executeUpdate();
			
			psEquipo.setInt(1, id);
			int arEqui = psEquipo.executeUpdate();
			
			psTicket.setInt(1, id);
			int arTick = psTicket.executeUpdate();
			
			if(arsegui > 0 && arEqui > 0 && arTick > 0) {
				ok = true;
			}
			else {
				throw new SQLException("No se ha podido eliminar el Ticket");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ok;
		}
		return ok;
	}
	
	@Override
	public ArrayList<Ticket> selectAll() {
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		Ticket newTicket;
		Cliente cliente;
		ClienteDAO clienteDao = new ClienteDAO(gc);
		Logger log = Logger.getLogger("debugmiapp");
		
		String query="SELECT * FROM " + TABLE_NAME;
		
		try (
				Connection cn = gc.getConnection();
				PreparedStatement ps = cn.prepareStatement(query);
		){
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				newTicket = new Ticket();
				
				newTicket.setIdTicket(rs.getInt(ID_TICKET)); 
				newTicket.setFechaInicio(rs.getTimestamp(FECHA_INICIO));
				newTicket.setFechaFin(rs.getTimestamp(FECHA_FIN));
				newTicket.setDescripcion(rs.getString(DESCRIPCION));
				newTicket.setEstado(rs.getString(ESTADO));
				cliente = clienteDao.findById(rs.getString(ID_CLIENTE));
				newTicket.setCliente(cliente);
				
				tickets.add(newTicket);
			}
			log.info(query);
			
			if(tickets.size() <= 0)
				System.out.println("No hay tickets que mostrar");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tickets;
	}
}
