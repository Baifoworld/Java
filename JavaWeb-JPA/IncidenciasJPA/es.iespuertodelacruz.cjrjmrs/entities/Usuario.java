package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the Usuarios database table.
 * 
 */
@Entity
@Table(name="Usuarios")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=25)
	private String nick;

	@Column(nullable=false, length=100)
	private String email;

	@Column(nullable=false, length=200)
	private String password;

	@Column(nullable=false, length=13)
	private String rol;

	//bi-directional many-to-one association to Cliente
	@OneToMany(mappedBy="usuario")
	private Set<Cliente> clientes;

	//bi-directional many-to-one association to Gestore
	@OneToMany(mappedBy="usuario")
	private Set<Gestore> gestores;

	public Usuario() {
	}

	public String getNick() {
		return this.nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRol() {
		return this.rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public Set<Cliente> getClientes() {
		return this.clientes;
	}

	public void setClientes(Set<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Cliente addCliente(Cliente cliente) {
		getClientes().add(cliente);
		cliente.setUsuario(this);

		return cliente;
	}

	public Cliente removeCliente(Cliente cliente) {
		getClientes().remove(cliente);
		cliente.setUsuario(null);

		return cliente;
	}

	public Set<Gestore> getGestores() {
		return this.gestores;
	}

	public void setGestores(Set<Gestore> gestores) {
		this.gestores = gestores;
	}

	public Gestore addGestore(Gestore gestore) {
		getGestores().add(gestore);
		gestore.setUsuario(this);

		return gestore;
	}

	public Gestore removeGestore(Gestore gestore) {
		getGestores().remove(gestore);
		gestore.setUsuario(null);

		return gestore;
	}

}