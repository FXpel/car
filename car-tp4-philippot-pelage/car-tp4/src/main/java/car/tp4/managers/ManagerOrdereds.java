/**
 * Class ManagerOrdereds 
 * @author Philippot Grégoire
 */
package car.tp4.managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Remove;
import javax.ejb.Stateful;

import car.tp4.entity.Book;
import car.tp4.entity.Buyer;
import car.tp4.entity.Orders;
import car.tp4.entity.bean.BookBean;
import car.tp4.entity.bean.BuyerBean;
import car.tp4.entity.bean.OrdersBean;

@Stateful
@LocalBean
public class ManagerOrdereds {
	// ATTRIBUTS
	public static final String SESSION_KEY = "ordereds-session";
	/* book bean */
	@EJB
	private BookBean bookBean;
	/* buyer bean */
	@EJB
	private BuyerBean buyerBean;
	/* orders bean */
	@EJB
	private OrdersBean ordersBean;
	/* purchase item */
	private Map<Integer, Integer> purchase = new HashMap<Integer, Integer>();
	
	// METHODS
	/**
	 * Add the <code>quantity</code> of a book desired by its <code>bookId</code>
	 * @param bookId bookId of the book
	 * @param quantity quantity to add
	 * @return <code>true</code> if the addition was successful else <code>false</code>
	 */
	public boolean addPurchase(int bookId, int quantity) {
		Book book = this.bookBean.findABookById(bookId);
		if(book == null || book.getQuantity() < quantity) {
			return false;
		}
		this.purchase.put(bookId, quantity);
		return true;
	}
	
	/**
	 * Remove the <code>quantity</code> of a book desired by its <code>bookId</code>
	 * @param bookId bookId of the book
	 * @param quantity quantity to remove
	 * @return <code>true</code> if the deletion was successful else <code>false</code>
	 */
	public boolean removePurchase(int bookId, int quantity) {
		if(this.purchase.containsKey(bookId)) {
			int quantityInPurchase = this.purchase.get(bookId);
			if(quantityInPurchase <= quantity) {
				this.purchase.remove(bookId);
			}else {
				this.purchase.put(bookId, quantityInPurchase - quantity);
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Returns a map of book with their quantity to buy
	 * @return orders
	 */
	public Map<Book, Integer> getOrdereds(){
		Map<Book, Integer> ordereds = new HashMap<Book, Integer>();
		for(Entry<Integer, Integer> ord : this.purchase.entrySet()) {
			Book book = this.bookBean.findABookById(ord.getKey());
			if(book == null) {
				continue;
			}
			ordereds.put(book, ord.getValue());
		}
		return ordereds;
	}
	
	/**
	 * Return a buyer if their purchase list is valid
	 * @param email the mail of buyer
	 * @return the buyer or null 
	 */
	public Buyer confirmPurchase(String email) {
		if(!this.purchase.isEmpty()) {
			Buyer buyer = new Buyer(email);
			List<Orders> orders = new ArrayList<Orders>();
			for(Entry<Integer, Integer> ord : this.purchase.entrySet()) {
				Book book = this.bookBean.findABookById(ord.getKey());
				if(book == null || book.getQuantity() < ord.getValue()) {
					return null;
				}
				Orders ords = new Orders(book, buyer, ord.getValue());
				orders.add(ords);
			}
			this.buyerBean.createTicket(buyer, orders);
			this.end();
			return buyer;
		}
		return null;
	}
	
	@Remove
	public void end() {	}
	
	// GETTER AND SETTER
	/**
	 * @return the bookBean
	 */
	public BookBean getBookBean() {
		return this.bookBean;
	}
	
	/**
	 * @param bookBean bookBean to set
	 */
	public void setBookBean(BookBean bookBean) {
		this.bookBean = bookBean;
	}
	
	/**
	 * @return the buyerBean
	 */
	public BuyerBean getBuyerBean() {
		return this.buyerBean;
	}

	/**
	 * @param buyerBean buyerBean to set
	 */
	public void setBuyerBean(BuyerBean buyerBean) {
		this.buyerBean = buyerBean;
	}
	
	/**
	 * @return the ordersBean
	 */
	public OrdersBean getOrdersBean() {
		return this.ordersBean;
	}
	
	/**
	 * @param ordersBean ordersBean to set
	 */
	public void setOrdersBean(OrdersBean ordersBean) {
		this.ordersBean = ordersBean;
	}
}
