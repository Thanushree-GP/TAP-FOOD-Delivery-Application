package com.foodapp.daointerfaces;

import java.util.ArrayList;

import com.foodapp.model.User;


public interface UserDAO {
	int insert(User u);
	ArrayList<User> fetchAll();
	User fetchOne(int userId);
	int update(String email,int userId);
	int delete(int userId);
	User isValidate(String email);
}
