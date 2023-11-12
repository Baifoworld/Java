package repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;

import entities.Cliente;
import entities.Gestore;
import entities.Ticket;

public class TicketRepository implements ICRUD<Ticket, Integer>{

	private EntityManagerFactory emf;
	
	//Constructor De Ticket Repository
	public TicketRepository(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	@Override
	public List<Ticket> findAll() {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			List<Ticket> tickets = em.createNamedQuery("Ticket.findAll", Ticket.class).getResultList();
			em.getTransaction().commit();
			em.close();
			return tickets;
		} catch (RollbackException e) {
			return null;
		}
	}

	@Override
	public Ticket findById(Integer id) {
		
		try {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();

			Ticket ticket = em.createNamedQuery("Ticket.findById", Ticket.class).setParameter("idTicket", id)
					.getSingleResult();

			em.getTransaction().commit();
			em.close();

			return ticket;

		} catch (RollbackException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public Ticket save(Ticket obj) {
		EntityManager em= null;
		
		try {
			em = emf.createEntityManager();
			/*ClienteRepository clienteR = new ClienteRepository(emf);
			GestorRepository gestorR = new GestorRepository(emf);
			Cliente cliente = clienteR.findById(obj.getCliente().getIdCliente());
			Gestore gestor = null;
			List<Gestore> gestores = gestorR.findAll();
			
			if(cliente != null) {*/
			em.getTransaction().begin();
			em.persist(obj);
			em.getTransaction().commit();
			
			em.close();
			return obj;
			//}
			
		}catch (RollbackException ex) {
			System.out.println(ex.getMessage());
			return null;
		}
		
	}

	@Override
	public Ticket update(Ticket obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ticket findByName(Integer string) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

}
