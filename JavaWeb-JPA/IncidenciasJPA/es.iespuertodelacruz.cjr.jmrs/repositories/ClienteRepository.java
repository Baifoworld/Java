package repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;

import entities.Cliente;

public class ClienteRepository implements ICRUD<Cliente, String> {

	private EntityManagerFactory emf;

	public ClienteRepository(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public List<Cliente> findAll() {
		try {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			List<Cliente> lista = em.createNamedQuery("Cliente.findAll", Cliente.class).getResultList();
			em.getTransaction().commit();
			em.close();
			return lista;
		} catch (RollbackException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public Cliente findById(String idCliente) {
		EntityManager em = emf.createEntityManager();
		try {

			em.getTransaction().begin();
			Cliente cliente = em.createNamedQuery("Cliente.findById", Cliente.class)
					.setParameter("idCliente", idCliente).getSingleResult();

			em.getTransaction().commit();
			em.close();
			return cliente;
		} catch (RollbackException e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	@Override
	public Cliente findByName(String id_cliente) {
		EntityManager em = emf.createEntityManager();
		try {

			em.getTransaction().begin();
			Cliente cliente = em.createNamedQuery("Cliente.findByName", Cliente.class)
					.setParameter("id_cliente", id_cliente).getSingleResult();

			em.getTransaction().commit();
			em.close();
			return cliente;
		} catch (RollbackException e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	@Override

	public Cliente save(Cliente cliente) {
		EntityManager em = emf.createEntityManager();
		try {
			em.persist(cliente);
			em.getTransaction().begin();
			em.getTransaction().commit();
			em.close();
			return cliente;
		} catch (RollbackException e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	@Override
	public Cliente update(Cliente obj) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Cliente findByNick(String nick) {
		EntityManager em = emf.createEntityManager();
		try {

			em.getTransaction().begin();
			Cliente cliente = em.createNamedQuery("Cliente.findByNick", Cliente.class)
					.setParameter("nick",nick).getSingleResult();
			em.getTransaction().commit();
			em.close();
			return cliente;
		} catch (RollbackException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	@Override
	public boolean delete(String idCliente) {
		EntityManager em = null;
		boolean ok = false;
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			Cliente cliente = em.createNamedQuery("Cliente.findById", Cliente.class)
					.setParameter("idCliente", idCliente).getSingleResult();
			if (cliente != null) {
				em.remove(cliente);
				em.getTransaction().commit();
				ok = true;
			} else {
				ok = false;
			}
		} catch (RollbackException e) {
			System.out.println(e.getMessage());
			ok = false;
			em.close();
		}
		em.close();
		return ok;
	}

}
