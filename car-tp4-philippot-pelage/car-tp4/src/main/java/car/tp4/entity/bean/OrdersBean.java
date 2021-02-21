/**
 * Class OrdersBean 
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
public class OrdersBean {
	@PersistenceContext(unitName = "book-pu")
	private EntityManager entityManager;
	
	/**
	 * Add <code>orders</code> to the database
	 * @param orders orders to add
	 */
	public void addOrders(Orders orders) {
		this.entityManager.persist(orders);
	}
	
	/**
	 * Remove <code>orders</code> to the database
	 * @param orders orders to remove
	 */
	public void removeOrders(Orders orders) {
		this.entityManager.remove(orders);
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * Find an orders with a buyer
	 * @param buyer buyer
	 * @return orders
	 */
	public List<Orders> findOrdersByBuyer(Buyer buyer){
		Query query = entityManager.createQuery("SELECT o FROM Orders AS o WHERE o.buyer = :buyer");
		return query.getResultList();
	}
}
