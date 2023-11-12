package es.iespuertodelacruz.jmrs.incidencias.models.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

import es.iespuertodelacruz.jmrs.incidencias.contracts.GestorEntry;
import es.iespuertodelacruz.jmrs.incidencias.models.entities.Gestor;
import es.iespuertodelacruz.jmrs.incidencias.models.utils.GestorConexionDDBB;

public class GestorDAO extends GestorEntry implements CRUD<Gestor, String>{

	GestorConexionDDBB gc;
	
	public GestorDAO(GestorConexionDDBB gc) {
		this.gc = gc;
	}
	
	public GestorDAO(String ddbb, String user, String pass) {
		this.gc = new GestorConexionDDBB(ddbb,user,pass);
	}
	
	@Override
	public Gestor save(Gestor gestor) {
		Logger log = Logger.getLogger("debugmiapp");
		String query="INSERT INTO " + TABLE_NAME 
				+ " (" + DNI + ", "
				+ NOMBRE + ", " 
				+ APELLIDOS + ") VALUES (?,?,?)";
		try (
				Connection cn = gc.getConnection();
				PreparedStatement ps = cn.prepareStatement(query);
		){
					
			ps.setString(1, gestor.getDNI());
			ps.setString(2, gestor.getNombre());
			ps.setString(3, gestor.getApellidos());
			int affectedRows = ps.executeUpdate();
			
			log.info(query);

			if(affectedRows <= 0) {
				throw new SQLException("No se ha podido crear el Gestor");
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			log.info(query);
			return gestor;
		}
	}
	
	@Override
	public Gestor findById(String id) {
		
		Logger log = Logger.getLogger("debugmiapp");
		String query="SELECT * FROM " + TABLE_NAME + " WHERE " + DNI + " = ?";
		try (
				Connection cn = gc.getConnection();
				PreparedStatement ps = cn.prepareStatement(query);
		){
			
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery(); 
			
			while(rs.next()) {
				return new Gestor(rs.getString(DNI), rs.getString(NOMBRE),rs.getString(APELLIDOS));
				
			}
			log.info(query);	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	@Override
	public boolean update(Gestor gestor) {
		
		Logger log = Logger.getLogger("debugmiapp");
		String query="UPDATE " + TABLE_NAME 
				+ " SET " + NOMBRE + "= ?, " 
				+ APELLIDOS + "= ? WHERE "
				+ DNI + "= ?";
		try (
				Connection cn = gc.getConnection();
				PreparedStatement ps = cn.prepareStatement(query);
		){

			ps.setString(1, gestor.getNombre());
			ps.setString(2, gestor.getApellidos());
			ps.setString(3, gestor.getDNI());
			int affectedRows = ps.executeUpdate();
			log.info(query);
			if(affectedRows > 0) {
				return true;
			}
			else {
				throw new SQLException("No se ha podido actualizar el Gestor");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Override
	public boolean delete(String id) {
		Logger log = Logger.getLogger("debugmiapp");
		String query="DELETE FROM " + TABLE_NAME + " WHERE " + DNI + "= ?";
		try (
				Connection cn = gc.getConnection();
				PreparedStatement ps = cn.prepareStatement(query);
		){
			ps.setString(1, id);
			int affectedRows = ps.executeUpdate();
			ps.close();
			cn.close();
			if(affectedRows > 0) {
				return true;
			}
			else {
				throw new SQLException("No se ha podido eliminar el Gestor");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public ArrayList<Gestor> selectAll() {
		ArrayList<Gestor> gestores = new ArrayList<Gestor>();
		Logger log = Logger.getLogger("debugmiapp");
		String query="SELECT * FROM " + TABLE_NAME;
		
		try (
				Connection cn = gc.getConnection();
				
		){
			PreparedStatement ps = cn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				gestores.add(new Gestor(rs.getString(DNI),rs.getString(NOMBRE),rs.getString(APELLIDOS)));
			}
			log.info(query);
			ps.close();
			cn.close();
			return gestores;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
