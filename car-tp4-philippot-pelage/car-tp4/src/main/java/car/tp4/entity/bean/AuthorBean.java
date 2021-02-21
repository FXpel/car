/**
 * Class AuthorBean
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

@Stateless
@LocalBean
public class AuthorBean {
	/**/
	@PersistenceContext(unitName = "book-pu")
	private EntityManager entityManager;
	
	/**
	 * Add the <code>author</code> in the database
	 * @param author the author to add
	 */
	public void addAuthor(Author author) {
		this.entityManager.persist(author);
	}
	
	/**
	 * Remove the specified <code>author</code> from the database
	 * @param id id's author to remove
	 */
	public void removeAuthor(long id) {
		this.entityManager.createQuery("DELETE FROM Author AS a WHERE a.id = :id").setParameter("id", id).executeUpdate();
	}
	
	/**
	 * Update the <code>author</code> in the database
	 * @param author the author to update
	 */
	public void updateAuthor(Author author) {
		this.entityManager.persist(author);
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * Return all authors in the database
	 * @return list of author
	 */
	public List<Author> getAllAuthors(){
		Query query = this.entityManager.createQuery("SELECT a FROM Author AS a");
		return query.getResultList();
	}
	
	/**
	 * Find an author with an <code>id</code>
	 * @param id id 
	 * @return author
	 */
	public Author findAuthorById(long id) {
		Query query = this.entityManager.createQuery("SELECT a FROM Author AS a WHERE a.id = :id").setParameter("id", id);
		return (Author) query.getSingleResult();
	}
	
	/**
	 * Create an author if <code>id</code> equals to 0 or update a author
	 * @param id id's author
	 * @param f firstname's author
	 * @param l lastname's author
	 * @return <code>true</code> if the method went well else <code>false</code>
	 */
	public boolean createOrUpdateAnAuthor(long id, String f, String l) {
		if(f == null || l == null) {
			return false;
		}
		Author author = null;
		if(id == 0) {
			author = new Author(l,f);
			this.addAuthor(author);
		}else {
			author = this.findAuthorById(id);
			if(author == null) {
				return false;
			}
			author.setFirstname(f);
			author.setLastname(l);
			this.updateAuthor(author);
		}
		return true;
	}
}
