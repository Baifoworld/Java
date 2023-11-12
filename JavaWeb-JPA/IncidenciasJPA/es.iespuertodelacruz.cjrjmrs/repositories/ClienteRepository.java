package repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import entities.Cliente;

public class ClienteRepository implements ICRUD<Cliente,String>{
	
	private EntityManagerFactory emf;

	public ClienteRepository(EntityManagerFactory emf) {
		this.emf = emf;
	}
		
	@Override
	public List<Cliente> findAll() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Cliente> lista = em.createNamedQuery("Cliente.findAll", Cliente.class)
			.getResultList();
		em.getTransaction().commit();
		em.close();
		return lista;
	}

	@Override
	public Cliente findById(String id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Cliente cliente = em.find(Cliente.class, id);
		em.getTransaction().commit();
		em.close();
		return cliente;
	}

	@Override
	public Cliente findByName(String nombre) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Cliente cliente = em.createNamedQuery("Cliente.findByName", Cliente.class)
			.setParameter("nombreCliente", nombre)
			.getSingleResult();

		em.getTransaction().commit();
		em.close();
		return cliente;
	}	
	
	
	@Override
	public Cliente save(Cliente obj) {
		EntityManager em = null;
		Cliente cliente = null;
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			obj.getIdCliente();
			em.persist(obj);
			em.getTransaction().commit();
			cliente = obj;
		} catch(Exception e) {
			
		}
		em.close();
		return cliente;
	}

	@Override
	public Cliente update(Cliente obj) {
		
		return null;
	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}

}
