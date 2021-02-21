/**
 * Class Orders
 * @author Philippot Grégoire
 */
package car.tp4.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Orders {
	//ATRIBUTS
	/* ID */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	/* books */
	@ManyToOne
	private Book book;
	/* acheteurs */
	@ManyToOne
	private Buyer buyer;
	/**/
	private int quantity;
	
	// CONSTRUCTOR
	/**
	 * Orders
	 */
	public Orders() {}
	
	/**
	 * Create an orders
	 * @param book book
	 * @param buyer buyer
	 * @param quantity quantity's book
	 */
	public Orders(Book book, Buyer buyer, int quantity) {
		this.book = book;
		this.buyer = buyer;
		this.quantity = quantity;
	}
	
	// GETTER AND SETTER
	/**
	 * @return the id
	 */
	public float getId() {
		return this.id;
	}
	
	/**
	 * @return the book
	 */
	public Book getBook() {
		return this.book;
	}
	
	/**
	 * @param book book to set
	 */
	public void setBook(Book book) {
		this.book = book;
	}
	
	/**
	 * @return the buyer
	 */
	public Buyer getBuyer() {
		return this.buyer;
	}
	
	/**
	 * @param buyer the buyer to set
	 */
	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}
	
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return this.quantity;
	}

	/**
	 * @param quantity quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
