package com.foodapp.servlet;

import java.io.IOException;
import java.security.Provider.Service;
import java.util.Map;

import com.foodapp.model.CartItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class Clear
 */
@WebServlet("/Clear")
public class Clear extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		HttpSession session = req.getSession();
        Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");
        if (cart != null) 
        {
        	cart.clear();
           
        }
        session.setAttribute("cart",cart);
        resp.sendRedirect("Cart.jsp");
	}
}
