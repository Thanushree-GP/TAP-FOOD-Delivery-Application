package com.foodapp.servlet;

import java.io.IOException;
import java.util.Map;

import com.foodapp.daoimpl.Cart;
import com.foodapp.model.CartItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class Update
 */
@WebServlet("/Update")
public class Update extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		Map<Integer, CartItem> cart = (Map<Integer, CartItem>) req.getSession().getAttribute("cart");
		  int menuId = Integer.parseInt(req.getParameter("itemId"));
	      int quantity = Integer.parseInt(req.getParameter("quantity"));
	      Cart cartDao = new Cart();
	      cart=cartDao.updateItem(menuId, quantity, cart);
	     req.getSession().setAttribute("cart",cart);
	     resp.sendRedirect("Cart.jsp");
	      
		
	}
	

}
