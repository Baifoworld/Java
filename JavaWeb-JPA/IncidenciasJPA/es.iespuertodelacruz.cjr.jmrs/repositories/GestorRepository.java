package repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;

import entities.Cliente;
import entities.Gestore;
import entities.Usuario;

public class GestorRepository implements ICRUD<Gestore, String> {

	private EntityManagerFactory emf;

	public GestorRepository(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public List<Gestore> findAll() {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			List<Gestore> gestores = em.createNamedQuery("Gestore.findAll", Gestore.class).getResultList();
			em.getTransaction().commit();
			em.close();
			return gestores;
		} catch (RollbackException e) {
			return null;
		}

	}

	@Override
	public Gestore findById(String dni) {
		
		try {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();

			Gestore gestor = em.createNamedQuery("Gestore.findById", Gestore.class).setParameter("dni", dni)
					.getSingleResult();

			em.getTransaction().commit();
			em.close();

			return gestor;

		} catch (RollbackException e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	@Override
	public Gestore save(Gestore gestor) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(gestor);
			em.getTransaction().commit();

			em.close();
			return gestor;
		} catch (RollbackException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public Gestore update(Gestore obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gestore findByName(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(String dni) {
		EntityManager em = null;
		boolean ok = false;
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			Gestore gestor = em.createNamedQuery("Gestore.findById", Gestore.class)
				.setParameter("dni",dni)
				.getSingleResult();
			if(gestor != null) {
				em.remove(gestor);
				em.getTransaction().commit();
				ok = true;
			} else {
				ok = false;
			}
		}catch(Exception e) {
			ok = false;
			em.close();
		}
		em.close();
		return ok;
	}

}
