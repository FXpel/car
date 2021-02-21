/**
 * Class Book 
 * @author Philippot Grégoire
 */
package car.tp4.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Book {
	// ATTRIBUTS
	/* ID of this book */
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	/* title */
	private String title;
	/* author */
	@ManyToOne
	private Author author;
	/* quantity */
	private int quantity;
	/* year */
	private int year;
	
	// CONSTRUCTOR
	/**
	 * Book with parameters
	 * @param author the author of this book
	 * @param title title of this book
	 * @param year year
	 * @param quantity quantity
	 */
	public Book(Author author, String title, int year, int quantity) {
		this.author = author;
		this.title = title;
		this.year = year;
		this.quantity = quantity;
	}
	
	/**
	 * Book
	 */
	public Book() {	}

	// GETTER AND SETTES
	/**
	 * @return id
	 */
	public long getId() {
		return this.id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the author
	 */
	public Author getAuthor() {
		return author;
	}
	
	/**
	 * @param author the author to set
	 */
	public void setAuthor(Author author) {
		this.author = author;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		Book book = (Book) o;
		
		if (id != book.id) return false;
		if (!author.equals(book.author)) return false;
		return title.equals(book.title);
	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + author.hashCode();
		result = 31 * result + title.hashCode();
		return result;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}
}
