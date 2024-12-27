package com.foodapp.daointerfaces;

import java.util.ArrayList;

import com.foodapp.model.Menu;

public interface MenuDAO {

	int insert(Menu m);
	ArrayList<Menu> fetchResMenu(int restaurantId);
	Menu fetchOne(int menuId);
	int update(int price,int menuId);
	int delete(int menuId);
}
