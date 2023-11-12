package entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Gestores database table.
 * 
 */
@Entity
@Table(name="Gestores")
@NamedQuery(name="Gestore.findAll", query="SELECT g FROM Gestore g")
@NamedQuery(name="Gestore.findById", query="SELECT g FROM Gestore g WHERE g.dni = :dni")
public class Gestore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String dni;

	private String apellidos;

	private String nombre;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="nick")
	private Usuario usuario;

	public Gestore() {
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String toString() {
		String mostrarJson = "Dni gestor: " + dni + "\n" 
				+ "Nombre: " + nombre + "\n"
				+ "Apellidos: " + apellidos + "\n"
				+ "nick: " +  usuario.getNick() + "\n";
		return mostrarJson;
		
	}

}