package com.foodapp.servlet;

import com.foodapp.daoimpl.OrderItemsDAOImp;
import com.foodapp.model.CartItem;
import com.foodapp.model.Order;
import com.foodapp.model.OrderHistory;
import com.foodapp.model.OrderItems;
import com.foodapp.model.User;

import java.io.IOException;
import java.util.Map;

import com.foodapp.daoimpl.Cart;
import com.foodapp.daoimpl.OrderADAOImp;
import com.foodapp.daoimpl.OrderHistoryDAOImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class CheckoutServlet
 */
@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
    private OrderItemsDAOImp orderItemDaoImpl;
    private Cart cartDaoImpl;
    private OrderADAOImp orderDaoImpl;
    private OrderHistoryDAOImpl orderHistoryDaoImpl;

    @Override
    public void init() throws ServletException {
        cartDaoImpl = new Cart(); 
        orderItemDaoImpl = new OrderItemsDAOImp();
        orderDaoImpl = new OrderADAOImp();
        orderHistoryDaoImpl = new OrderHistoryDAOImpl(); 
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user == null) {
            resp.sendRedirect("login.jsp");
            return;
        }
        
        String paymentMethod = req.getParameter("paymentMethod");
        Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");
        System.out.println(cart);

        if (cart == null || cart.isEmpty()) {
            resp.sendRedirect("cart.jsp");
            return;
        }

        	int orderid=AutoGenerateNumber.generateUniqueOrderId();
            double totalAmount = 0; 

            for (CartItem cartItem : cart.values()) {
                double itemTotal = cartItem.getPrice() * cartItem.getQuantity();
                totalAmount += itemTotal; 
                
                Order order = new Order();
                order.setOrderId(orderid);
                order.setUserId(user.getUserId());
                order.setRestaurantId(cartItem.getRestaurantId());
                order.setTotalAmount((int) itemTotal); 
                order.setPaymentMode(paymentMethod); 
                order.setStatus("Pending"); 

                int n = orderDaoImpl.insert(order);
                if (n == 1) {
                    System.out.println("Order Insert success");
                } else {
                    System.out.println("Order Insert failure");
                }

                OrderItems orderItem = new OrderItems();
                orderItem.setOrderId(orderid); 
                orderItem.setMenuId(cartItem.getItemId()); 
                orderItem.setQuantity(cartItem.getQuantity()); 
                orderItem.setItemTotal((int) itemTotal); 

                int n1 = orderItemDaoImpl.insert(orderItem);
                if (n1 != 0) {
                    System.out.println("Order Item Insert success");
                } else {
                    System.out.println("Order Item Insert failure");
                }
            }

            OrderHistory orderHistory = new OrderHistory();
            orderHistory.setOrderId(orderid); 
            orderHistory.setUserId(user.getUserId());
            orderHistory.setTotalAmount((int) totalAmount); 
            orderHistory.setStatus("Pending");

            int orderHistoryInserted = orderHistoryDaoImpl.insert(orderHistory);
            if (orderHistoryInserted == 1) {
                System.out.println("Order History Insert success");
            } else {
                System.out.println("Order History Insert failure");
            }

            session.removeAttribute("cart");
            resp.sendRedirect("Confirm.jsp");
        }
    }

