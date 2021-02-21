/**
 * Class Author 
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

@Entity
public class Author {
	// ATTRIBUTS
	/* id of this author */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	/* nom */
	private String lastname;
	/* prnom */
	private String firstname;
	/* book's write by author*/
	@Transient
	private Collection<Book> books = new ArrayList<Book>();
	
	// CONTRUCTOR
	/**
	 * Create an author
	 */
	public Author() {
	}
	
	/**
	 * 
	 * @param l the lastname
	 * @param f the firstname
	 */
	public Author(String l, String f) {
		this.lastname = l;
		this.firstname = f;
	}

	// GETTER AND SETTER
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
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the books
	 */
	public Collection<Book> getBooks() {
		return books;
	}

	/**
	 * @param books the books to set
	 */
	public void setBooks(Collection<Book> books) {
		this.books = books;
	}
	
	// METHODS
	/**
	 * Add a book to the <code>books</code> collection
	 * @param book the book to add to the <code>books</code> collection
	 */
	public void addBook(Book book) {
		if(book.getAuthor() == null || book.getAuthor().getId() != this.id) {
			book.setAuthor(this);
		}
		this.books.add(book);
	}
	
	/**
	 * Remove a book to the <code>books</code> collection
	 * @param book the book to remove to the <code>books</code> collection
	 */
	public void removeBook(Book book) {
		if(book.getAuthor() == null || book.getAuthor().getId() != this.id) {
			book.setAuthor(this);
		}
		this.books.remove(book);
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Author author = (Author) o;
		if(id == author.getId() && this.lastname.equals(author.getLastname()) && this.firstname.equals(author.getFirstname())) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return this.getFirstname() + " " + this.getLastname();
	}
}
