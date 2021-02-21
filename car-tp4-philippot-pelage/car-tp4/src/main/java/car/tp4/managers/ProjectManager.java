/**
 * Class ProjectManager
 * @author Philippot Grégoire
 */
package car.tp4.managers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import car.tp4.entity.Author;
import car.tp4.entity.Book;
import car.tp4.entity.bean.AuthorBean;
import car.tp4.entity.bean.BookBean;

@Stateless
public class ProjectManager {
	// ATTRIBUTS
	/* bookBean */
	@EJB
	private BookBean bookBean;
	/* authorBean */
	@EJB
	private AuthorBean authorBean;
	
	// METHODS
	/**
	 * Initialize the project by adding 4 authors and 10 books
	 */
	public void initProject() {
		System.out.println(this.bookBean);
		Author tolkien = new Author("J.R.R","Tolkien");
		Author martin = new Author("Martin","George.R");
		Author riodan = new Author("Riordan","Rick");
		
		Book sda = new Book(tolkien,"Le seigneur des anneaux",1954,5);
		Book hobbit = new Book(tolkien,"Le hobbit",1937,5);
		
		Book got = new Book(martin,"Le trone de fer",1996,5);
		
		Book pj1 = new Book(riodan,"Le voleur de foudre",2005,5);
		Book pj2 = new Book(riodan,"La mer des monstres",2006,5);
		Book pj3 = new Book(riodan,"Le sort du titan",2007,5);
		Book pj4 = new Book(riodan,"La bataille du labyrinthe",2008,5);
		Book pj5 = new Book(riodan,"Le dernier olympien",2009,5);
		
		this.authorBean.addAuthor(tolkien);
		this.authorBean.addAuthor(martin);
		this.authorBean.addAuthor(riodan);
		
		this.bookBean.addBook(sda);
		this.bookBean.addBook(hobbit);
		this.bookBean.addBook(got);
		this.bookBean.addBook(pj1);
		this.bookBean.addBook(pj2);
		this.bookBean.addBook(pj3);
		this.bookBean.addBook(pj4);
		this.bookBean.addBook(pj5);
	}
	
	/**
	 * Returns all the authors in the database from the books (if several books have the same author only keep one in the list)
	 * @return authors disctinct authors in Book
	 */
	public List<Author> getDistinctAuthorsFromBooks(){
		// https://blog.ippon.fr/2014/03/17/api-stream-une-nouvelle-facon-de-gerer-les-collections-en-java-8/
		//return this.bookBean.getAllBooks().stream().map(book -> book.getAuthor()).distinct().collect(Collectors.toList());
		List<Author> res = new ArrayList<Author>();
		List<Book> books = this.bookBean.getAllBooks();
		for(Book b : books) {
			if(!res.contains(b.getAuthor())) {
				res.add(b.getAuthor());
			}
		}
		return res;
	}
	
	/**
	 * Create a book if <code>bookID</code> equals to 0 or update a book
	 * @param bookID book id
	 * @param authorID author id
	 * @param title title's book
	 * @param year year's book
	 * @param quantity quantity's book
	 * @return <code>true</code> if the method went well else <code>false</code>
	 */
	public boolean createOrUpdateABook(int bookID, int authorID, String title, int year, int quantity) {
		Author author = this.authorBean.findAuthorById(authorID);
		if(author != null && !(title.trim().equals("")) && quantity >= 0) {
			if(bookID == 0) {
				Book book = new Book(author,title,year,quantity);
				this.bookBean.addBook(book);
				return true;
			}else {
				Book book = this.bookBean.findABookById(bookID);
				if(book != null) {
					book.setId(bookID);
					book.setAuthor(author);
					book.setTitle(title);
					book.setQuantity(quantity);
					book.setYear(year);
					this.bookBean.updateBook(book);
					return true;
				}
				return false;
			}
		}
		return false;
	}
	
	// GETTER AND SETTER
	/**
	 * @return the bookBean
	 */
	public BookBean getBookBean() {
		return this.bookBean;
	}
	
	/**
	 * @return the authorBean
	 */
	public AuthorBean getAuthorBean() {
		return this.authorBean;
	}
	
	/**
	 * @param bookBean bookBean to set
	 */
	public void setBookBean(BookBean bookBean) {
		this.bookBean = bookBean;
	}
	
	/**
	 * @param authorBean authorBean to set
	 */
	public void setAuthorBean(AuthorBean authorBean) {
		this.authorBean = authorBean;
	}
}
