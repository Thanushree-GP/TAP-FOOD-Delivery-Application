package com.foodapp.daointerfaces;

import java.util.ArrayList;

import com.foodapp.model.OrderHistory;


public interface OrderHistoryDAO {
	int insert(OrderHistory o);
	ArrayList<OrderHistory> fetchAll();
	OrderHistory fetchOne(int orderHistoryId);
	int update(String status,int orderHistoryId);
	int delete(int orderHistoryId);
}
