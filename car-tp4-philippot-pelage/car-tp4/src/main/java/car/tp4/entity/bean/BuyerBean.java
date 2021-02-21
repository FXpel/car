/**
 * Class BuyerBean 
 * @author Philippot Grégoire
 */
package car.tp4.entity.bean;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import car.tp4.entity.Buyer;
import car.tp4.entity.Orders;

@Stateless
@LocalBean
public class BuyerBean {
	@PersistenceContext(unitName = "book-pu")
	private EntityManager entityManager;
	
	/**
	 * Add <code>buyer</code> to the database
	 * @param buyer buyer to add
	 */
	public void addBuyer(Buyer buyer) {
		this.entityManager.persist(buyer);
	}
	
	/**
	 * Remove <code>buyer</code> to the database
	 * @param buyer buyer to remove
	 */
	public void removeBuyer(Buyer buyer) {
		this.entityManager.remove(buyer);
	}
	
	/**
	 * Create a ticket 
	 * @param buyer buyer
	 * @param orders orders of <code>buyer</code>
	 */
	public void createTicket(Buyer buyer, List<Orders> orders) {
		this.entityManager.persist(buyer);
		for(Orders ord : orders) {
			ord.getBook().setQuantity(ord.getBook().getQuantity()-ord.getQuantity());
			this.entityManager.persist(ord.getBook());
			this.entityManager.persist(ord);
		}
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * Return all buyers
	 * @return buyers
	 */
	public List<Buyer> getAllOrders(){
		Query query = entityManager.createQuery("SELECT b FROM buyer_table AS b ORDER BY b.id DESC");
		return query.getResultList();
	}
	
	/**
	 * Find a buyer with an <code>id</code>
	 * @param id id 
	 * @return buyer
	 */
	public Buyer findBuyerById(long id) {
		Query query = this.entityManager.createQuery("SELECT b FROM buyer_table AS b WHERE b.id = :id").setParameter("id", id);
		return (Buyer) query.getSingleResult();
	}
}
