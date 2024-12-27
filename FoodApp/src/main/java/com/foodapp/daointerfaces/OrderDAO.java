package com.foodapp.daointerfaces;

import java.util.ArrayList;

import com.foodapp.model.Order;

public interface OrderDAO {
	int insert(Order o);
	ArrayList<Order> fetchAll();
	Order fetchOne(int orderId);
	int update(String status,int orderId);
	int delete(int orderId);
}
