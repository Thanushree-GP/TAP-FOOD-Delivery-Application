<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.foodapp.model.CartItem,com.foodapp.daoimpl.Cart" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Confirmation</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color:#1c2833; /* Dark background color */
            color: #2c3e50; /* Text color */
            margin: 0;
            padding: 0;
        }

        .container {
            width: 80%;
            max-width: 1200px;
            background-color: #b2babb; /* Updated card background color */
            padding: 20px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
            margin: 0 auto;
            margin-top: 50px;
        }

        .header {
            background-color: #1c2833; /* Header background color */
            color: white;
            padding: 10px 0;
            text-align: center;
            border-radius: 10px 10px 0 0;
        }

        .order-details {
            margin-top: 20px;
            text-align: center;
        }

        .order-summary-header {
            text-align: center;
            color: #34495e;
        }

        table {
            width: 100%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: #1c2833; /* Table background color */
        }

        table, th, td {
            border: 1px solid black;
            border-radius: 5px;
        }

        th, td {
            padding: 10px;
            text-align: center;
            color: #fff;
        }

        .button-container {
            display: flex;
            justify-content: space-between;
            margin-top: 20px;
        }

        .btn {
            padding: 10px 20px;
            margin: 5px;
            cursor: pointer;
            border: none;
            border-radius: 5px;
            font-size: 16px;
        }

        .btn-confirm {
            background-color: #1c2833; /* Confirm button background color */
            color: white;
        }

        .btn-cancel {
            background-color: #641e16 ; /* Cancel button background color */
            color: white;
        }

        .btn:hover {
            opacity: 0.8; /* Button hover effect */
        }
    </style>
</head>
<body>

    <div class="container">
        <!-- Header Section -->
        <div class="header">
            <h2>Thank You for Your Order!</h2> <!-- Thank You message -->
        </div>

        <!-- Order Details Section -->
        <div class="order-summary-header">
            <h3>Your Order Summary:</h3>
        </div>

        <!-- Display cart items in a table -->
        <table>
            <thead>
                <tr>
                    <th>Item Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Total</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");
                    double grandTotal = 0;  
                    if (cart != null && !cart.isEmpty()) {
                        for (CartItem cartItem : cart.values()) {
                            double itemTotal = cartItem.getPrice() * cartItem.getQuantity();
                            grandTotal += itemTotal;
                %>
                <tr>
                    <td><%= cartItem.getName() %></td>
                    <td><%= cartItem.getPrice() %></td>
                    <td><%= cartItem.getQuantity() %></td>
                    <td><%= itemTotal %></td>
                </tr>
                <% 
                        }
                    }
                %>
                <tr>
                    <td colspan="3" style="text-align:right;"><strong>Grand Total:</strong></td>
                    <td><strong><%= grandTotal %></strong></td>
                </tr>
            </tbody>
        </table>

        <div class="button-container">
            
            <form action="CheckoutServlet" method="POST">
                <input type="hidden" name="paymentMethod" value="Credit Card"/> 
                <button type="submit" class="btn btn-confirm">Confirm Order</button>
            </form>

            <form action="Home.jsp" method="GET">
                <button type="submit" class="btn btn-cancel">Cancel Order</button>
            </form>
        </div>
    </div>

</body>
</html>
