package com.foodapp.daointerfaces;

import java.util.ArrayList;

import com.foodapp.model.Restaurant;



public interface RestaurantDAO {
	int insert(Restaurant r);
	ArrayList<Restaurant> fetchAll();
	Restaurant fetchOne(int restaurantId);
	int update(float ratings,int restaurantId);
	int delete(int restaurantId);
}
