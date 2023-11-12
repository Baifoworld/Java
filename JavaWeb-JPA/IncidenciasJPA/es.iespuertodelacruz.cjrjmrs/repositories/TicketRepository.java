package repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import entities.Cliente;
import entities.Ticket;

public class TicketRepository implements ICRUD<Ticket,Integer>{

	private EntityManagerFactory emf;

	public TicketRepository(EntityManagerFactory emf) {
		this.emf = emf;
	}
		
	@Override
	public List<Ticket> findAll() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Ticket> lista = em.createNamedQuery("Ticket.findAll", Ticket.class)
			.getResultList();
		em.getTransaction().commit();
		em.close();
		return lista;
	}

	@Override
	public Ticket findById(Integer id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Ticket ticket = em.find(Ticket.class, id);
		em.getTransaction().commit();
		em.close();
		return ticket;
	}
	
	@Override
	public Ticket findByName(Integer nombre) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Ticket ticket = null;

		em.getTransaction().commit();
		em.close();
		return ticket;
	}	
	
	@Override
	public Ticket save(Ticket obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ticket update(Ticket obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}
}
