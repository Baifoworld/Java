package repositories;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;


import entities.Gestore;
import entities.Cliente;
import entities.Usuario;

public class UsuarioRepository implements ICRUD<Usuario,String> {
	
	private EntityManagerFactory emf;

	public UsuarioRepository(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	@Override
	public List<Usuario> findAll() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Usuario> lista = em.createNamedQuery("Usuario.findAll", Usuario.class)
			.getResultList();
		em.getTransaction().commit();
		em.close();
		return lista;
	}

	@Override
	public Usuario findById(String nick) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Usuario usuario = em.createNamedQuery("Usuario.findById", Usuario.class)
			.setParameter("nick", nick)
			.getSingleResult();

		em.getTransaction().commit();
		em.close();
		return usuario;
	}


	@Override
	public Usuario save(Usuario obj) {
		EntityManager em = null;
		Usuario usuario = null; 
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(obj);
			usuario = obj;
			if(usuario != null)
				em.getTransaction().commit();
		} catch(Exception e) {
			
		}
		em.close();
		return usuario;
	}

	@Override
	public Usuario update(Usuario obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(String nick) {
		EntityManager em = null;
		boolean ok = false;
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			Usuario usuario = em.createNamedQuery("Usuario.findByName", Usuario.class)
				.setParameter("nick", nick)
				.getSingleResult();
			if(usuario != null) {
				em.remove(usuario);
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

	@Override
	public Usuario findByName(String nick) {
		try{
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			
			Usuario usuario = em.createNamedQuery("Usuario.findByName", Usuario.class)
				.setParameter("nick",nick)
				.getSingleResult();
			
			em.getTransaction().commit();
			em.close();
			
			return usuario;
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		
		
	}

}
