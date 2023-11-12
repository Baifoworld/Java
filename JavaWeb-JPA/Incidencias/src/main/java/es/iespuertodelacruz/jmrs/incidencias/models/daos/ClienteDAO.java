package es.iespuertodelacruz.jmrs.incidencias.models.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

import es.iespuertodelacruz.jmrs.incidencias.contracts.ClienteEntry;
import es.iespuertodelacruz.jmrs.incidencias.models.entities.Cliente;
import es.iespuertodelacruz.jmrs.incidencias.models.utils.GestorConexionDDBB;

public class ClienteDAO extends ClienteEntry implements CRUD<Cliente, String>{

GestorConexionDDBB gc;
	
	public ClienteDAO(GestorConexionDDBB gc) {
		this.gc = gc;
	}
	
	public ClienteDAO(String ddbb, String user, String pass) {
		this.gc = new GestorConexionDDBB(ddbb,user,pass);
	}
	
	@Override
	public Cliente save(Cliente cliente) {
		Logger log = Logger.getLogger("debugmiapp");
		String query="INSERT INTO " + TABLE_NAME 
				+ " (" + ID_CLIENTE + ", " 
				+ NOMBRE_CONTACTO + ", "
				+ DIRECCION + ", "
				+ TELEFONO + ") VALUES (?,?,?,?)";
		try (
				Connection cn = gc.getConnection();
				PreparedStatement ps = cn.prepareStatement(query);
		){
					
			//ps.setNull(1, java.sql.Types.INTEGER); //para autoincrementar en caso de que lo tenga en la base de datos
			ps.setString(1, cliente.getIdCliente());
			ps.setString(2, cliente.getNombreContacto());
			ps.setString(3, cliente.getDireccion());
			ps.setString(4, cliente.getTelefono());
			ps.executeUpdate();
			//int affectedRows = 
			log.info(query);
			ps.close();
			cn.close();
			/*if(affectedRows <= 0) {
				throw new SQLException("No se ha podido crear el Cliente");
			}*/
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			log.info(query);
			return cliente;
		}
	}
	
	@Override
	public Cliente findById(String id) {
		Cliente cliente = null;
		Logger log = Logger.getLogger("debugmiapp");
		String query="SELECT * FROM " + TABLE_NAME + " WHERE " + ID_CLIENTE + " = ?";
		try (
				Connection cn = gc.getConnection();
				PreparedStatement ps = cn.prepareStatement(query);
		){
			
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery(); 
			
			while(rs.next()) {
				cliente = new Cliente();
				cliente.setIdCliente(rs.getString(ID_CLIENTE)); 
				cliente.setNombreContacto(rs.getString(NOMBRE_CONTACTO));
				cliente.setDireccion(rs.getString(DIRECCION)); 
				cliente.setTelefono(rs.getString(TELEFONO));			
			}

			log.info(query);	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return cliente;
		}
		return cliente;
	}
	
	@Override
	public boolean update(Cliente cliente) {
		
		Logger log = Logger.getLogger("debugmiapp");
		
		String query="UPDATE " + TABLE_NAME + " SET "
				+ NOMBRE_CONTACTO + " = ?, "
				+ DIRECCION + " = ?, "
				+ TELEFONO + " = ? WHERE "
				+ ID_CLIENTE + " = ?";
		try (
				Connection cn = gc.getConnection();
				PreparedStatement ps = cn.prepareStatement(query);
		){
			
			ps.setString(1, cliente.getNombreContacto());
			ps.setString(2, cliente.getDireccion());
			ps.setString(3, cliente.getTelefono());
			ps.setString(4, cliente.getIdCliente());
			int affectedRows = ps.executeUpdate();
			log.info(query);
	
			if(affectedRows > 0) {
				return true;
			}
			else {
				throw new SQLException("No se ha podido actualizar el Cliente");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Override
	public boolean delete(String id) {
		
		boolean ok = false;
		Logger log = Logger.getLogger("debugmiapp");
		
		String query="DELETE FROM " + TABLE_NAME + " WHERE " + ID_CLIENTE + " = ?";
		try (
				Connection cn = gc.getConnection();
				PreparedStatement ps = cn.prepareStatement(query);
		){
			ps.setString(1, id);
			int affectedRows = ps.executeUpdate();

			if(affectedRows > 0) {
				ok = true;
			}
			else {
				throw new SQLException("No se ha podido eliminar el Cliente");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ok;
		}
		return ok;
	}
	
	@Override
	public ArrayList<Cliente> selectAll() {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		Logger log = Logger.getLogger("debugmiapp");
		String query="SELECT * FROM " + TABLE_NAME;
		
		try (
				Connection cn = gc.getConnection();
				
		){
			PreparedStatement ps = cn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				clientes.add(new Cliente(rs.getString(ID_CLIENTE),
						rs.getString(NOMBRE_CONTACTO),
						rs.getString(DIRECCION),
						rs.getString(TELEFONO)));
			}
			log.info(query);
			
			return clientes;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
