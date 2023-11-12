package entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Clientes database table.
 * 
 */
@Entity
@Table(name="Clientes")
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
@NamedQuery(name="Cliente.findById", query="SELECT c FROM Cliente c WHERE c.idCliente = :idCliente")
@NamedQuery(name="Cliente.findByName", query="SELECT c FROM Cliente c WHERE c.nombre = :nombre")
@NamedQuery(name="Cliente.findByNick", query="SELECT c FROM Cliente c WHERE c.usuario.nick = :nick")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_cliente")
	private String idCliente;

	private String direccion;

	private String nombre;

	private String telefono;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="nick")
	private Usuario usuario;

	public Cliente() {
	}

	public String getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}