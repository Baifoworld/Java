package es.iespuertodelacruz.jmrs.incidencias.models.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Logger;

import es.iespuertodelacruz.jmrs.incidencias.contracts.GestorEntry;
import es.iespuertodelacruz.jmrs.incidencias.contracts.SeguimientoEntry;
import es.iespuertodelacruz.jmrs.incidencias.contracts.TicketEntry;
import es.iespuertodelacruz.jmrs.incidencias.models.entities.Gestor;
import es.iespuertodelacruz.jmrs.incidencias.models.entities.Seguimiento;
import es.iespuertodelacruz.jmrs.incidencias.models.entities.Ticket;
import es.iespuertodelacruz.jmrs.incidencias.models.utils.GestorConexionDDBB;

public class SeguimientoDAO extends SeguimientoEntry implements CRUD<Seguimiento, Integer>{

GestorConexionDDBB gc;
	
	public SeguimientoDAO(GestorConexionDDBB gc) {
		this.gc = gc;
	}
	
	public SeguimientoDAO(String ddbb, String user, String pass) {
		this.gc = new GestorConexionDDBB(ddbb,user,pass);
	}
	
	@Override
	public Seguimiento save(Seguimiento dao) {
		
		Logger log = Logger.getLogger("debugmiapp");
		//FALTA MEJORAR
		String querySegui = "INSERT INTO " + TABLE_NAME 
				+ " (" + ID_TICKET + ", "
				+ COMENTARIO + ", "
				+ FECHA + ", "
				+ DNI_GESTOR + ") VALUES (?,?,?,?)";
		try (
				Connection cn = gc.getConnection();
				PreparedStatement ps = cn.prepareStatement(querySegui, PreparedStatement.RETURN_GENERATED_KEYS);
		){
			
			//ps.setNull(1, java.sql.Types.INTEGER);
			Timestamp dftfecha = new Timestamp(dao.getFecha().getTime());
			ps.setInt(1, dao.getTicket().getIdTicket());
			ps.setString(2, dao.getComentario());
			ps.setTimestamp(3, dftfecha);
			ps.setString(4, dao.getGestor().getDNI());

			int affectedRows = ps.executeUpdate();
			
			log.info(querySegui);

			if(affectedRows <= 0) 
				throw new SQLException("No se ha podido crear el Seguimiento del Ticket");
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		} 
		return dao;
	}
	
	@Override
	public Seguimiento findById(Integer id) {
		GestorDAO gestorDao = new GestorDAO(gc);
		TicketDAO ticketDao = new TicketDAO(gc);
		Gestor gestor;
		Ticket ticket;
		Seguimiento seguimiento = null;
		Logger log = Logger.getLogger("debugmiapp");
		
		String querySeguimiento="SELECT * FROM " + TABLE_NAME + " WHERE " + ID_SEGUIMIENTO + " = ? ";
		String queryGestor = "SELECT * FROM " + GestorEntry.TABLE_NAME + " WHERE " + GestorEntry.DNI + " = ?";
		String queryTicket = "SELECT * FROM " + TicketEntry.TABLE_NAME + " WHERE " + TicketEntry.ID_TICKET + " = ?";
		try (
				Connection cn = gc.getConnection();
				PreparedStatement psSegui = cn.prepareStatement(querySeguimiento, PreparedStatement.RETURN_GENERATED_KEYS);
		){
			
			psSegui.setInt(1, id);
			ResultSet rs = psSegui.executeQuery(); 
			
			while(rs.next()) {
				seguimiento = new Seguimiento();
				seguimiento.setIdSeguimiento(rs.getInt(ID_SEGUIMIENTO));
				seguimiento.setFecha(rs.getTimestamp(FECHA));
				seguimiento.setComentario(rs.getString(COMENTARIO));
				ticket = ticketDao.findById(rs.getInt(ID_TICKET));
				seguimiento.setTicket(ticket);
				gestor = gestorDao.findById(rs.getString(DNI_GESTOR));
				seguimiento.setGestor(gestor);
			}

			if(seguimiento == null)
				System.out.println("No se ha creado correctamente seguimiento");
			log.info(querySeguimiento);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return seguimiento;
	}
	
	@Override
	public boolean update(Seguimiento seguimiento) {
		 
		boolean ok = false;
		Logger log = Logger.getLogger("debugmiapp");
		
		String querySegui="UPDATE " + TABLE_NAME + " SET "
				+ ID_TICKET + " = ?,"
				+ FECHA + " = ? ,"
				+ COMENTARIO + " = ? ,"
				+ DNI_GESTOR + " = ?  WHERE "
				+ ID_SEGUIMIENTO + " = ?";
		try (
				Connection cn = gc.getConnection();
				PreparedStatement ps = cn.prepareStatement(querySegui);
		){
			Timestamp dftfecha = new Timestamp(seguimiento.getFecha().getTime());
			ps.setNull(1, java.sql.Types.INTEGER);
			ps.setTimestamp(2, dftfecha);
			ps.setString(3, seguimiento.getComentario());
			ps.setString(4, seguimiento.getGestor().getDNI());
			ps.setInt(5, seguimiento.getIdSeguimiento());
			int affectedRows = ps.executeUpdate();
			log.info(querySegui);
			
			if(affectedRows > 0) {
				ok = true;
			}
			else {
				throw new SQLException("No se ha podido actualizar el Seguimiento");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ok;
		}
		
		return ok;
	}
	
	@Override
	public boolean delete(Integer id) {
		Logger log = Logger.getLogger("debugmiapp");
		boolean ok = false;
		
		String query="DELETE FROM " + TABLE_NAME + " WHERE " + ID_SEGUIMIENTO + "= ?";
		try (
				Connection cn = gc.getConnection();
				PreparedStatement ps = cn.prepareStatement(query);
		){
			ps.setInt(1, id);
			int affectedRows = ps.executeUpdate();

			if(affectedRows > 0) {
				ok = true;
			}
			else {
				throw new SQLException("No se ha podido eliminar el Seguimiento");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ok;
		}
		return ok;
	}
	
	@Override
	public ArrayList<Seguimiento> selectAll() {
		Seguimiento seguimiento = null;
		Ticket ticket = null;
		Gestor gestor = null;
		TicketDAO ticketDao = new TicketDAO(gc);
		GestorDAO gestorDao = new GestorDAO(gc);
		ArrayList<Seguimiento> seguimientos = new ArrayList<Seguimiento>();
		Logger log = Logger.getLogger("debugmiapp");
		String query="SELECT * FROM " + TABLE_NAME;
		
		try (
				Connection cn = gc.getConnection();
				PreparedStatement ps = cn.prepareStatement(query);
		){
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				seguimiento = new Seguimiento();
				seguimiento.setIdSeguimiento(rs.getInt(ID_SEGUIMIENTO));
				seguimiento.setFecha(rs.getTimestamp(FECHA));
				seguimiento.setComentario(rs.getString(COMENTARIO));
				ticket = ticketDao.findById(rs.getInt(ID_TICKET));
				seguimiento.setTicket(ticket);
				gestor = gestorDao.findById(rs.getNString(DNI_GESTOR));
				seguimiento.setGestor(gestor);
				seguimientos.add(seguimiento);
			}
			log.info(query);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return seguimientos;
	}
	
	public ArrayList<Seguimiento> findbyticketId(int id){
		
		GestorDAO gestorDao = new GestorDAO(gc);
		TicketDAO ticketDao = new TicketDAO(gc);
		ArrayList<Seguimiento> seguimientos = new ArrayList<Seguimiento>();
		Gestor gestor;
		Ticket ticket;
		Seguimiento seguimiento = null;
		
		Logger log = Logger.getLogger("debugmiapp");
		
		String querySeguimiento="SELECT * FROM " + TABLE_NAME + " WHERE " + ID_TICKET + " = ? ";
		String queryGestor = "SELECT * FROM " + GestorEntry.TABLE_NAME + " WHERE " + GestorEntry.DNI + " = ?";
		String queryTicket = "SELECT * FROM " + TicketEntry.TABLE_NAME + " WHERE " + TicketEntry.ID_TICKET + " = ?";
		
		try (
				Connection cn = gc.getConnection();
				PreparedStatement psSegui = cn.prepareStatement(querySeguimiento, PreparedStatement.RETURN_GENERATED_KEYS);
		){
			
			psSegui.setInt(1, id);
			ResultSet rs = psSegui.executeQuery(); 
			
			while(rs.next()) {
				seguimiento = new Seguimiento();
				seguimiento.setIdSeguimiento(rs.getInt(ID_SEGUIMIENTO));
				seguimiento.setFecha(rs.getTimestamp(FECHA));
				seguimiento.setComentario(rs.getString(COMENTARIO));
				ticket = ticketDao.findById(rs.getInt(ID_TICKET));
				seguimiento.setTicket(ticket);
				gestor = gestorDao.findById(rs.getString(DNI_GESTOR));
				seguimiento.setGestor(gestor);
				
				seguimientos.add(seguimiento);
			}

			if(seguimientos.size() <= 0)
				System.out.println("No se ha creado correctamente la lista de seguimientos");
			log.info(querySeguimiento);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return seguimientos;
	}
}
