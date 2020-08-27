package com.jwt.order.bo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.jwt.order.dao.OrderDAO;
import com.jwt.order.dto.Order;
import com.jwt.order.exception.BOException;

public class OrderBOImplTest {

	@Mock
	OrderDAO orderDAO;
	private OrderBOImpl bo;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		bo = new OrderBOImpl();
		bo.setOrderDAO(orderDAO);
	}

	@Test
	public void testPlaceOrder_should_create_order() throws SQLException, BOException {
		Order order = new Order();
		when(orderDAO.craeteOrder(order)).thenReturn(new Integer(1));
		boolean result = bo.placeOrder(order);
		assertTrue(result);
		verify(orderDAO).craeteOrder(order);
	}

	@Test
	public void testPlaceOrder_should_not_create_order() throws SQLException, BOException {
		Order order = new Order();
		when(orderDAO.craeteOrder(order)).thenReturn(new Integer(0));
		boolean result = bo.placeOrder(order);
		assertFalse(result);
		verify(orderDAO).craeteOrder(order);
	}

	@Test(expected = BOException.class)
	public void testPlaceOrder_should_throw_BOException() throws SQLException, BOException {
		Order order = new Order();
		when(orderDAO.craeteOrder(order)).thenThrow(SQLException.class);
		boolean result = bo.placeOrder(order);
		assertFalse(result);
		verify(orderDAO).craeteOrder(order);
	}

	@Test
	public void testCancelOrder_should_cancel_order() throws SQLException, BOException {
		Order order = new Order();
		when(orderDAO.read(123)).thenReturn(order);
		when(orderDAO.update(order)).thenReturn(1);
		boolean result = bo.cancelOrder(123);
		assertTrue(result);
		verify(orderDAO).read(123);
		verify(orderDAO).update(order);
	}

	@Test
	public void testCancelOrder_should_not_cancel_order() throws SQLException, BOException {
		Order order = new Order();
		when(orderDAO.read(123)).thenReturn(order);
		when(orderDAO.update(order)).thenReturn(0);
		boolean result = bo.cancelOrder(123);
		assertFalse(result);
		verify(orderDAO).read(123);
		verify(orderDAO).update(order);

	}

	@Test(expected = BOException.class)
	public void testCancelOrder_should_throw_BOException_On_Read() throws SQLException, BOException {
		when(orderDAO.read(123)).thenThrow(SQLException.class);
		bo.cancelOrder(123);
	}
	
	@Test(expected = BOException.class)
	public void testCancelOrder_should_throw_BOException_On_Update() throws SQLException, BOException {
		Order order = new Order();
		when(orderDAO.read(123)).thenReturn(order);
		when(orderDAO.update(order)).thenThrow(BOException.class);
		bo.cancelOrder(123);
	}

	@Test
	public void testDeleteOrder_should_delete__order() throws SQLException, BOException {
		when(orderDAO.delete(123)).thenReturn(new Integer(1));
		boolean result = bo.deleteOrder(123);
		assertTrue(result);
		verify(orderDAO).delete(123);
	}
	
	@Test
	public void testDeleteOrder_should_not_delete__order() throws SQLException, BOException {
		when(orderDAO.delete(anyInt())).thenReturn(0);
		boolean result = bo.deleteOrder(anyInt());
		assertFalse(result);
		verify(orderDAO).delete(anyInt());
		
	}
	
	@Test(expected = BOException.class)
	public void testDeleteOrder_should_throw_exception() throws SQLException, BOException {
		when(orderDAO.delete(123)).thenThrow(SQLException.class);
		bo.deleteOrder(123);
	}

	@Test
	public void testGetOrderDAO() {
	}

	@Test
	public void testSetOrderDAO() {
	}

}
