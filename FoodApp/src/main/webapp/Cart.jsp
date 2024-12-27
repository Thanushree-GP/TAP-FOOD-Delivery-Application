<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.foodapp.daoimpl.Cart,java.util.*, com.foodapp.model.CartItem"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Shopping Cart</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color:#212f3d;
            color: #99a3a4;
        }

        header {
            text-align: center;
            padding: 20px;
            background-color: #212f3d;
            color: #99a3a4;
        }

        h1 {
            margin: 0;
            font-size: 3em;
        }

        .container {
            display: flex;
            flex-direction: column;
            align-items: center;
            padding: 20px;
        }

        .card {
            background-color: #99a3a4;
            color: #2c3e50;
            border-radius: 8px;
            padding: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 80%;
            max-width: 800px;
            margin-bottom: 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .card h3 {
            margin: 0 0 10px;
            font-size: 1.5em;
        }

        .card p {
            margin: 0 0 10px;
            font-size: 1.1em;
        }

        .card .details {
            flex: 1;
        }

        .actions {
            display: flex;
            flex-direction: column; /* Stack buttons vertically */
            align-items: flex-end; /* Align to the right edge */
            gap: 10px;
            justify-content: flex-start;
            width: 30%; /* Adjust width to fit the buttons */
        }

        .actions button,
        .actions input[type="number"] {
            width: 100%; /* Ensure buttons and inputs have the same width */
            background-color: #2c3e50;
            color: #99a3a4;
            border: none;
            border-radius: 4px;
            padding: 10px 15px;
            font-size: 1em;
            cursor: pointer;
            transition: background-color 0.3s, transform 0.2s;
            box-sizing: border-box; /* Ensure padding doesn't affect layout */
        }

        /* Add movement effect to buttons */
        .actions button:hover {
            background-color: #1c2833;
            transform: scale(1.1);
            transition: all 0.3s ease-in-out;
        }

        /* Smaller width for the input field */
        .actions input[type="number"] {
            width: 60px; /* Decreased width of the input */
            height: 40px; /* Same height to make it square */
            text-align: center; /* Center number input */
            margin-right: 10px; /* Space between the input box and button */
        }

        .actions form {
            display: flex;
            justify-content: flex-start;
            align-items: center;
            gap: 10px;
            width: 100%;
        }

        .clear-cart-btn, .proceed-checkout-btn {
            display: block;
            margin: 20px auto;
            background-color: #99a3a4;
            color: #212f3d;
            border: none;
            border-radius: 4px;
            padding: 12px 20px;
            font-size: 1em;
            cursor: pointer;
            transition: background-color 0.3s, transform 0.2s;
        }

        /* Make Proceed to Checkout button the same as Clear Cart button */
        .clear-cart-btn:hover, .proceed-checkout-btn:hover {
            background-color: #1c2833;
            color: #99a3a4;
            transform: scale(1.1);
        }

        #cart {
            text-align: center;
            margin-top: 50px;
        }

        #cart img {
            width: 150px;
            opacity: 0.6;
        }

        .hidden-cart {
            display: none; /* Hide cart image initially */
        }
    </style>
</head>
<body>
    <header>
        <h1>Your Cart</h1>
    </header>

    <form action="Clear" method="post">
        <button type="submit" class="clear-cart-btn">Clear Cart</button>
    </form>

    <%
        Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
    %>
        <div id="cart" class="hidden-cart">
            <img src="https://cdn-icons-png.freepik.com/256/3550/3550640.png?semt=ais_hybrid" alt="Empty Cart Image">
        </div>
    <% 
        } else {
    %>
        <div class="container">
            <%
                for (CartItem item : cart.values()) {
            %>
                <div class="card">
                    <div class="details">
                        <h3><%= item.getName() %></h3>
                        <p><strong>Price:</strong> $<%= item.getPrice() %></p>
                        <p><strong>Quantity:</strong> <%= item.getQuantity() %></p>
                    </div>
                    <div class="actions">
                        <form action="Update" method="post">
                            <input type="number" id="quantity_<%= item.getItemId() %>" name="quantity" value="<%= item.getQuantity() %>" min="1" required>
                            <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
                            <button type="submit">Update</button>
                        </form>
                        <form action="Delete" method="post">
                            <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
                            <button type="submit">Delete</button>
                        </form>
                        <form action="MenuServlet" method="get">
                            <input type="hidden" name="restaurantId" value="<%= item.getRestaurantId() %>">
                            <button type="submit">Add More</button>
                        </form>
                    </div>
                </div>
            <% 
                }
            %>
        </div>
        <div class="container">
            <form action="Checkout.jsp" method="post">
                <button type="submit" class="proceed-checkout-btn">Proceed to Checkout</button>
            </form>
        </div>
    <% 
        }
    %>
</body>
</html>
