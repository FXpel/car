package managers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import car.tp4.entity.Author;
import car.tp4.entity.Book;
import car.tp4.entity.bean.AuthorBean;
import car.tp4.entity.bean.BookBean;
import car.tp4.entity.bean.BuyerBean;
import car.tp4.entity.bean.OrdersBean;
import car.tp4.managers.ManagerOrdereds;
import car.tp4.managers.ProjectManager;

public class ProjectManagerTest {
	private BookBean bookBean;
	private AuthorBean authorBean;
	private BuyerBean buyerBean;
	private OrdersBean ordersBean;
	private ManagerOrdereds ordereds;
	private ProjectManager projectManager;
	
	private List<Book> books;
	private Author martin;
	private Book got;
	
	@Before
	public void setUp() throws Exception{
		this.bookBean = Mockito.mock(BookBean.class);
		this.authorBean = Mockito.mock(AuthorBean.class);
		this.buyerBean = Mockito.mock(BuyerBean.class);
		this.ordersBean = Mockito.mock(OrdersBean.class);
		this.ordereds = new ManagerOrdereds();
		this.ordereds.setBookBean(bookBean);
		this.ordereds.setBuyerBean(buyerBean);
		this.ordereds.setOrdersBean(ordersBean);
		this.projectManager = new ProjectManager();
		this.projectManager.setBookBean(bookBean);
		this.projectManager.setAuthorBean(authorBean);
		this.books = new ArrayList<Book>();
		this.martin = new Author("Martin","George.R");
		this.got = new Book(this.martin,"Le trone de fer",1996,5);
	}
	
	@Test
	public void testInitProjectAddAuthor() {
		this.projectManager.initProject();
		Mockito.verify(this.authorBean,Mockito.times(3)).addAuthor((Author) Mockito.any());
	}
	
	@Test
	public void testInitProjectAddBook() {
		this.projectManager.initProject();
		Mockito.verify(this.bookBean,Mockito.times(8)).addBook((Book) Mockito.any());
	}
	
	@Test
	public void testGetDistinctAuthorsFromBooksWhenEmpty() {
		Mockito.when(this.bookBean.getAllBooks()).thenReturn(this.books);
		assertTrue(this.projectManager.getDistinctAuthorsFromBooks().isEmpty());
	}
	
	@Test
	public void testGetDistinctAuthorsFromBooks() {
		this.books.add(this.got);
		Author homere = new Author("Homere","");
		Book ody = new Book(homere,"odysse",-700,5);
		Book got2 = new Book(this.martin,"Le trone de fer 2",2000,5);
		this.books.add(ody);
		this.books.add(got2);
		Mockito.when(this.bookBean.getAllBooks()).thenReturn(this.books);
		assertEquals(this.martin,this.projectManager.getDistinctAuthorsFromBooks().get(0));
		assertEquals(this.projectManager.getDistinctAuthorsFromBooks().size(),2);
	}
		
	@Test
	public void testCreateOrUpdateABookWhenTitleIsBad() {
		assertFalse(this.projectManager.createOrUpdateABook(1, 1, "", 1, 1));
	}
	
	@Test
	public void testCreateOrUpdateABookWhenQuantityIsBad() {
		assertFalse(this.projectManager.createOrUpdateABook(1, 1, "test", 1, -1));
	}
	
	@Test
	public void testCreateOrUpdateABookWhenAuthorNotFound() {
		Mockito.when(this.authorBean.findAuthorById(Mockito.anyInt())).thenReturn(null);
		assertFalse(this.projectManager.createOrUpdateABook(1, 1, "test", 1, 1));
	}
	
	@Test
	public void testCreateOrUpdateABookWhenBookNotFound() {
		Mockito.when(this.authorBean.findAuthorById(1)).thenReturn(this.martin);
		Mockito.when(this.bookBean.findABookById(Mockito.anyInt())).thenReturn(null);
		assertFalse(this.projectManager.createOrUpdateABook(1, 1, "test", 1, 1));
	}
	
	@Test
	public void testCreateOrUpdateWhenCreateTheBook() {
		Mockito.when(this.authorBean.findAuthorById(1)).thenReturn(this.martin);
		assertTrue(this.projectManager.createOrUpdateABook(0, 1, "test", 1, 1));
	}
	
	@Test
	public void testCreateOrUpdateWhenCreateTheBookCheckAddBookCalled() {
		Mockito.when(this.authorBean.findAuthorById(Mockito.anyInt())).thenReturn(this.martin);
		assertTrue(this.projectManager.createOrUpdateABook(0, 1, "test", 1, 1));
		Mockito.verify(this.bookBean).addBook((Book) Mockito.any());
	}
	
	@Test
	public void testCreateOrUpdateWhenUpdateTheBook() {
		Mockito.when(this.authorBean.findAuthorById(1)).thenReturn(this.martin);
		Mockito.when(this.bookBean.findABookById(1)).thenReturn(this.got);
		assertTrue(this.projectManager.createOrUpdateABook(1, 1, "test", 1, 1));
		assertEquals("test",this.got.getTitle());
		assertEquals(1,this.got.getQuantity());
	}
	
	@Test
	public void testCreateOrUpdateWhenUpdateTheBookUpdateBookCalled() {
		Mockito.when(this.authorBean.findAuthorById(1)).thenReturn(this.martin);
		Mockito.when(this.bookBean.findABookById(1)).thenReturn(this.got);
		assertTrue(this.projectManager.createOrUpdateABook(1, 1, "test", 1, 1));
		Mockito.verify(this.bookBean).updateBook((Book) Mockito.any());
	}
}
