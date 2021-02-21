/**
 * Class BookBean 
 * @author Philippot Grégoire
 */
package car.tp4.entity.bean;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import car.tp4.entity.Author;
import car.tp4.entity.Book;

@Stateless
@LocalBean
public class BookBean {
	/**/
	@PersistenceContext(unitName = "book-pu")
	private EntityManager entityManager;

	/**
	 * Add <code>book</code> to the database
	 * @param book book to add
	 */
	public void addBook(Book book) {
		entityManager.persist(book);
	}
  
	/**
	 * Remove <code>book</code> to the database
	 * @param id id's book to remove
	 */
	public void removeBook(long id) {
		this.entityManager.createQuery("DELETE FROM Book AS m WHERE m.id = :id").setParameter("id", id).executeUpdate();
	}
  
	/**
	 * Update <code>book</code> in the database
	 * @param book book to update
	 */
	public void updateBook(Book book) {
		this.entityManager.persist(book);
	}
  
	/**
	 * Return all books in the database
	 * @return list of book
	 */
	@SuppressWarnings("unchecked")
	public List<Book> getAllBooks() {
		Query query = entityManager.createQuery("SELECT m from Book as m");
		return query.getResultList();
	}
	
	/**
	 * Return all books in the database with year DESC
	 * @return list of book
	 */
	@SuppressWarnings("unchecked")
	public List<Book> getAllBooksByDESCYear() {
		Query query = entityManager.createQuery("SELECT m from Book as m ORDER BY m.year DESC");
		return query.getResultList();
	}
	
	/**
	 * Return all books in the database with year ASC
	 * @return list of book
	 */
	@SuppressWarnings("unchecked")
	public List<Book> getAllBooksByASCYear() {
		Query query = entityManager.createQuery("SELECT m from Book as m ORDER BY m.year ASC");
		return query.getResultList();
	}
  
	/**
	 * Find a book with an <code>id</code>
	 * @param id id 
	 * @return book
	 */
	public Book findABookById(long id) {
		Query query = this.entityManager.createQuery("SELECT m FROM Book AS m WHERE m.id = :id").setParameter("id", id);
		return (Book) query.getSingleResult();
	}
	
	/**
	 * Find books with a title
	 * @param title title
	 * @return books
	 */
	@SuppressWarnings("unchecked")
	public List<Book> findBooksByTitle(String title){
		if(title == null) {
			return null;
		}
		Query query = this.entityManager.createQuery("SELECT m FROM Book AS m WHERE m.title LIKE :title").setParameter("title", "%"+title+"%");
		return query.getResultList();
	}
	
	/**
	 * Find books with a title
	 * @param title title
	 * @return books
	 */
	@SuppressWarnings("unchecked")
	public List<Book> findBooksByTitleByDESCYear(String title){
		if(title == null) {
			return null;
		}
		Query query = this.entityManager.createQuery("SELECT m FROM Book AS m WHERE m.title LIKE :title ORDER BY m.year DESC").setParameter("title", "%"+title+"%");
		return query.getResultList();
	}
	
	/**
	 * Find books with a title
	 * @param title title
	 * @return books
	 */
	@SuppressWarnings("unchecked")
	public List<Book> findBooksByTitleByASCYear(String title){
		if(title == null) {
			return null;
		}
		Query query = this.entityManager.createQuery("SELECT m FROM Book AS m WHERE m.title LIKE :title ORDER BY m.year ASC").setParameter("title", "%"+title+"%");
		return query.getResultList();
	}
	
	/**
	 * Find books with an author
	 * @param author author
	 * @return books
	 */
	@SuppressWarnings("unchecked")
	public List<Book> findBooksByAuthor(Author author){
		if(author == null) {
			return null;
		}
		Query query = this.entityManager.createQuery("SELECT m FROM Book AS m WHERE m.author = :author").setParameter("author", author);
		return query.getResultList();
	}
	
	/**
	 * Find books with an author
	 * @param author author
	 * @return books
	 */
	@SuppressWarnings("unchecked")
	public List<Book> findBooksByAuthorByDESCYear(Author author){
		if(author == null) {
			return null;
		}
		Query query = this.entityManager.createQuery("SELECT m FROM Book AS m WHERE m.author = :author ORDER BY m.year DESC").setParameter("author", author);
		return query.getResultList();
	}
	
	/**
	 * Find books with an author
	 * @param author author
	 * @return books
	 */
	@SuppressWarnings("unchecked")
	public List<Book> findBooksByAuthorByASCYear(Author author){
		if(author == null) {
			return null;
		}
		Query query = this.entityManager.createQuery("SELECT m FROM Book AS m WHERE m.author = :author ORDER BY m.year ASC").setParameter("author", author);
		return query.getResultList();
	}
	
	/**
	 * DON'T WORK
	 * Returns all the authors in the database from the books (if several books have the same author only keep one in the list)
	 * @return distinct authors in table Book
	 */
	@SuppressWarnings("unchecked")
	public List<Author> getDistinctAuthorsFromBooks(){
		Query query = entityManager.createQuery("SELECT m.Author FROM Book AS m GROUP BY m.Author");
		//Query query = entityManager.createQuery("SELECT DISTINCT m.Author FROM Book AS m");
		return query.getResultList();
	}
}