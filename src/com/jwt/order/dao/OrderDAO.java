package com.jwt.order.dao;

import java.sql.SQLException;

import com.jwt.order.dto.Order;

public interface OrderDAO {

	int craeteOrder(Order order) throws SQLException;

	Order read(int id) throws SQLException;

	int update(Order order) throws SQLException;

	int delete(int id) throws SQLException;
}
