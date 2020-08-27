package com.jwt.order.bo;

import java.sql.SQLException;

import com.jwt.order.dao.OrderDAO;
import com.jwt.order.dto.Order;
import com.jwt.order.exception.BOException;

public class OrderBOImpl implements OrderBO {

	private OrderDAO orderDAO;

	@Override
	public boolean placeOrder(Order order) throws BOException {
		try {
			int result = orderDAO.craeteOrder(order);
			if (result == 0) {
				return false;
			}
		} catch (SQLException e) {
			throw new BOException(e);
		}
		return true;
	}

	@Override
	public boolean cancelOrder(int id) throws BOException {
		try {
			Order order = orderDAO.read(id);
			order.setStatus("cancelled");
			int result = orderDAO.update(order);
			if (result == 0) {
				return false;
			}
		} catch (SQLException e) {
			throw new BOException(e);
		}
		return true;
	}

	@Override
	public boolean deleteOrder(int id) throws BOException {
		try {
			int result = orderDAO.delete(id);
			if (result == 0) {
				return false;
			}
		} catch (SQLException e) {
			throw new BOException(e);
		}
		return true;
	}

	public OrderDAO getOrderDAO() {
		return orderDAO;
	}

	public void setOrderDAO(OrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}

}
