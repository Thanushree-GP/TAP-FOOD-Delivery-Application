package com.foodapp.daointerfaces;

import java.util.ArrayList;

import com.foodapp.model.OrderItems;


public interface OrderItemsDAO {
	int insert(OrderItems o);
	ArrayList<OrderItems> fetchAll();
	OrderItems fetchOne(int orderItemId);
	int update(int quantity,int orderItemId);
	int delete(int orderItemId);
}
