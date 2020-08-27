package com.jwt.order.bo;

import com.jwt.order.dto.Order;
import com.jwt.order.exception.BOException;

public interface OrderBO {

	boolean placeOrder(Order order) throws BOException;

	boolean cancelOrder(int id) throws BOException;

	boolean deleteOrder(int id) throws BOException;

}
