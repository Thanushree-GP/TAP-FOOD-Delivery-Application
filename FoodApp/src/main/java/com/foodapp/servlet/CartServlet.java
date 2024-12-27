package com.foodapp.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import com.foodapp.daoimpl.Cart;
import com.foodapp.daoimpl.MenuDAOImpl;
import com.foodapp.model.CartItem;
import com.foodapp.model.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet 
{
			@Override
			protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
			{


				Map<Integer, CartItem> cart = (Map<Integer, CartItem>) req.getSession().getAttribute("cart");
	             if (cart == null) 
             {
	            cart = new HashMap<>();  
	            System.out.println("New cart created.");
	            req.getSession().setAttribute("cart", cart);
	        } 
             else {
	            System.out.println("Existing cart found.");
	        }
	        int menuId = Integer.parseInt(req.getParameter("itemId"));
	        int quantity = Integer.parseInt(req.getParameter("quantity"));
	      
	        MenuDAOImpl menuDao = new MenuDAOImpl();
	        Menu menu = menuDao.fetchOne(menuId);
	        CartItem ci = new CartItem(menuId, menu.getMenuId(), menu.getName(), quantity, menu.getPrice());
	        Cart cartDao = new Cart();
	        cart = cartDao.addItem(ci,cart);  
	        System.out.println("Added Item \n"+cart);
	        req.getSession().setAttribute("cart", cart);  
	        System.out.println("Cart after adding item: " + cart);
	        resp.sendRedirect("Cart.jsp");
	        
			}
}