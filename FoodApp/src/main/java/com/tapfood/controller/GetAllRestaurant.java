package com.tapfood.controller;
import com.foodapp.daoimpl.RestaurantDAOImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.foodapp.daointerfaces.RestaurantDAO;
import com.foodapp.model.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/GetAllRestaurant")
public class GetAllRestaurant extends HttpServlet
{
	private ArrayList<Restaurant> restaurantList;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    HttpSession session = req.getSession();
	    List<Restaurant> restaurantList = (List<Restaurant>) session.getAttribute("restaurantList");

	    if (restaurantList == null || restaurantList.isEmpty()) 
	    {
	        RestaurantDAO rdao = new RestaurantDAOImpl();
	        restaurantList = rdao.fetchAll();
	        session.setAttribute("restaurantList", restaurantList);
//	        if (restaurantList != null && !restaurantList.isEmpty()) {
//                session.setAttribute("restaurantList", restaurantList);
//            }
	        
	    }

	    resp.sendRedirect("Home.jsp");
	}

}
