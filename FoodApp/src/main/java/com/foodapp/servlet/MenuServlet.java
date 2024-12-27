package com.foodapp.servlet;

import java.io.IOException;
import java.util.ArrayList;

import com.foodapp.daoimpl.MenuDAOImpl;
import com.foodapp.model.Menu;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        int restaurantId = Integer.parseInt(req.getParameter("restaurantId"));
        String resName = req.getParameter("resName");

        // Set restaurant name in the session
        HttpSession session = req.getSession();
        session.setAttribute("name", resName);

        // Fetch menu for the restaurant
        MenuDAOImpl menuDAO = new MenuDAOImpl();
        ArrayList<Menu> menuList = menuDAO.fetchResMenu(restaurantId);
        	
       System.out.print(menuList);
        // Set the menuList in the request
        req.setAttribute("menuList", menuList);

        // Forward the request to Menu.jsp
        RequestDispatcher dispatcher = req.getRequestDispatcher("Menu.jsp");
       dispatcher.forward(req, resp);
       // resp.sendRedirect("Menu.jsp");
    }
}
