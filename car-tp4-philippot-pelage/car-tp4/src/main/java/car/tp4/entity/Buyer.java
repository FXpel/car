/**
 * Class Buyer
 * @author Philippot Grégoire
 */
package car.tp4.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity(name = "buyer_table")
public class Buyer {
	//ATRIBUTS
	/* ID */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	/* liste d'achats */
	@Transient
	private Collection<Orders> orders = new ArrayList<Orders>();
	/* acheteur */
	private String buyer;
	
	//CONSTRUCTOR
	/**
	 * Buyer
	 */
	public Buyer() {}
	
	/**
	 * Create a buyer
	 * @param buyer name of buyer
	 */
	public Buyer(String buyer) {
		this.buyer = buyer;
	}

	//METHODS
	/**
	 * Add an order
	 * @param order order to add
	 */
	public void addOrder(Orders order) {
		if(!this.orders.contains(order)) {
			if(order.getBuyer() == null || order.getBuyer().id != this.id) {
				order.setBuyer(this);
			}
			this.orders.add(order);
		}
	}
	
	/**
	 * Remove an order
	 * @param order order to remove
	 */
	public void removeOrder(Orders order) {
		if(this.orders.contains(order)) {
			if(order.getBuyer() != null || order.getBuyer().id == this.id) {
				order.setBuyer(null);
			}
			this.orders.remove(order);
		}
	}
	
	
	// GETTER AND SETTER
	/**
	 * @return the orders
	 */
	public Collection<Orders> getOrders() {
		return orders;
	}

	/**
	 * @param orders the orders to set
	 */
	public void setOrders(Collection<Orders> orders) {
		this.orders = orders;
	}

	/**
	 * @return the buyer
	 */
	public String getBuyer() {
		return buyer;
	}

	/**
	 * @param buyer the buyer to set
	 */
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	
	/**
	 * @return the id
	 */
	public float getId() {
		return this.id;
	}
	

}
