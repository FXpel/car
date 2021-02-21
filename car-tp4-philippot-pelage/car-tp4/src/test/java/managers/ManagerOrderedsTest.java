package managers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import car.tp4.entity.Author;
import car.tp4.entity.Book;
import car.tp4.entity.Buyer;
import car.tp4.entity.bean.AuthorBean;
import car.tp4.entity.bean.BookBean;
import car.tp4.entity.bean.BuyerBean;
import car.tp4.entity.bean.OrdersBean;
import car.tp4.managers.ManagerOrdereds;
import car.tp4.managers.ProjectManager;

public class ManagerOrderedsTest {
	private BookBean bookBean;
	private AuthorBean authorBean;
	private BuyerBean buyerBean;
	private OrdersBean ordersBean;
	private ManagerOrdereds ordereds;
	private ProjectManager projectManager;
	
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
		this.martin = new Author("Martin","George.R");
		this.got = new Book(this.martin,"Le trone de fer",1996,5);
	}
	
	@Test
	public void testAddPurchase() {
		Mockito.when(this.bookBean.findABookById(1)).thenReturn(this.got);
		assertTrue(this.ordereds.addPurchase(1, 2));
	}
	
	@Test
	public void testAddPurchaseWhenBookBeanCannotFindTheBook() {
		Mockito.when(this.bookBean.findABookById(Mockito.anyInt())).thenReturn(null);
		assertFalse(this.ordereds.addPurchase(1, 2));
	}
	
	@Test
	public void testAddPurchaseWhenBadBookID() {
		Mockito.when(this.bookBean.findABookById(1)).thenReturn(this.got);
		assertFalse(this.ordereds.addPurchase(2, 2));
	}
	
	@Test
	public void testAddPurchaseWhenQuantitySpecifiedIsNotGood() {
		Mockito.when(this.bookBean.findABookById(1)).thenReturn(this.got);
		assertFalse(this.ordereds.addPurchase(1, 10));
	}
		
	@Test
	public void testRemovePurchase() {
		Mockito.when(this.bookBean.findABookById(1)).thenReturn(this.got);
		this.ordereds.addPurchase(1, 2);
		assertTrue(this.ordereds.removePurchase(1, 3));
	}
	
	@Test
	public void testRemovePurchaseChangeQuantity() {
		Mockito.when(this.bookBean.findABookById(1)).thenReturn(this.got);
		this.ordereds.addPurchase(1, 2);
		assertTrue(this.ordereds.removePurchase(1, 1));
		Map<Book, Integer> ords = this.ordereds.getOrdereds();
		assertEquals(1,ords.get(this.got).intValue());
	}
	
	@Test
	public void testRemovePurchaseWhenCannotFindTheBook() {
		Mockito.when(this.bookBean.findABookById(Mockito.anyInt())).thenReturn(null);
		assertFalse(this.ordereds.removePurchase(1, 2));
	}
	
	@Test
	public void testConfirmPuchaseWhenEmpty() {
		assertNull(this.ordereds.confirmPurchase("test"));
	}
	
	@Test
	public void testConfirmPurchase() {
		Mockito.when(this.bookBean.findABookById(1)).thenReturn(this.got);
		this.ordereds.addPurchase(1, 2);
		Buyer buyer = this.ordereds.confirmPurchase("test");
		assertEquals("test",buyer.getBuyer());
		//Mockito.verify(this.buyerBean).createTicket(Mockito.any(), Mockito.any());
	}
	
	@Test
	public void testConfirmPurchaseWhenBookBeanCannotFindTheBook() {
		Mockito.when(this.bookBean.findABookById(1)).thenReturn(this.got);
		this.ordereds.addPurchase(1, 2);
		Mockito.when(this.bookBean.findABookById(1)).thenReturn(null);
		assertNull(this.ordereds.confirmPurchase("test"));		
	}
	
	@Test
	public void testConfirmPurchaseWhenQuantitySpecifiedIsNotGood() {
		Mockito.when(this.bookBean.findABookById(1)).thenReturn(this.got);
		this.ordereds.addPurchase(1, 2);
		this.got.setQuantity(1);
		assertNull(this.ordereds.confirmPurchase("test"));
	}
}
