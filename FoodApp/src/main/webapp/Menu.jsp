<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.foodapp.model.Menu" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menu Page</title>
    <style>
        * {
            padding: 0;
            margin: 0;
            font-family: sans-serif;
        }

        body {
            background: #1c2833;
            padding-top: 80px;
        }

        h1 {
            text-align: center;
            color:#b2babb ; /* Off-white */
            font-size: 60px;
            font-weight: 700;
            letter-spacing: 8px;
            margin-bottom: 20px;
        }

        .menu-container {
            display: flex;
            flex-direction: column;
            gap: 20px;
            align-items: center;
            margin-top: 100px;
            padding: 0 15px;
        }

        .menu-item {
            display: flex;
            justify-content: space-between;
            background-color: #b2babb;
            padding: 15px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 95%;
            max-width: 1000px;
            margin: 0 auto 20px auto;
        }

        .menu-info {
            flex: 1;
            margin-right: 20px;
            font-family: 'Times New Roman', Times, serif; /* Changed font */
        }

        .menu-info h2 {
            font-size: 24px;
            margin: 0 0 10px;
            color: #333;
        }

        .menu-info p {
            font-size: 16px;
            color: #222;
            margin: 5px 0;
        }

        .menu-info .price {
            font-size: 19px;
            font-weight: bold;
            color: #333;
        }

        .menu-info .availability {
            display: inline-block;
            font-size: 16px;
            font-weight: bold;
            padding: 5px 10px;
            color: #99a3a4; /* Text color */
            background-color: #34495e ; /* Chip style */
            border-radius: 13px; /* Chip style */
        }

        .menu-image {
            width: 220px;
            height: 220px;
            border-radius: 8px;
            overflow: hidden;
        }

        .menu-image img {
            width: 100%;
            height: 100%;
            object-fit: cover;
        }

        .menu-info input[type="number"] {
            width: 100px; /* Reduced length */
            padding: 5px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 4px;
            margin-top: 10px;
        }

        .menu-item .overlay {
            display: flex;
            justify-content: center; /* Center the button */
            align-items: center;
            width: 100%;
            margin-top: 10px;
        }

        .menu-item .overlay button {
            padding: 12px 30px;
            background-color: #34495e ; /* Green background */
            color: #99a3a4;
            border: none;
            border-radius: 10px;
            font-size: 16px;
            cursor: pointer;
            transition: transform 0.3s ease, background-color 0.3s ease;
        }

        .menu-item .overlay button:hover {
            background-color: #45a049;
            transform: scale(1.1); /* Slight zoom effect */
        }
    </style>
</head>
<body>

    <div class="content-wrapper">
        <h1>Menu Items</h1>

        <% 
            // Access the 'menuList' attribute from the request
            List<Menu> menuList = (List<Menu>) request.getAttribute("menuList");
            if (menuList != null && !menuList.isEmpty()) {
        %>

        <div class="menu-container">
            <% 
                // Loop through each menu item and display it
                for (Menu menu : menuList) {
            %>

            <div class="menu-item">
                <!-- Left side information -->
                <div class="menu-info">
                    <h2><%= menu.getName() %></h2>
                    <p><%= menu.getDescription() %></p>
                    <p class="price">₹ <%= menu.getPrice() %></p>
                    <p class="availability">Status: <%= menu.getIsAvailable().equalsIgnoreCase("Yes") ? "Yes" : "No" %></p>
                    
                    <form action="CartServlet" method="POST">
                        <!-- Hidden input to send menuId -->
                        <input type="hidden" name="itemId" value="<%= menu.getMenuId() %>">
                        
                        <!-- Hidden input to send price -->
                        <input type="hidden" name="price" value="<%= menu.getPrice() %>">

                        <!-- Quantity input with name attribute -->
                        <label>Quantity</label>
                        <input type="number" name="quantity" min="1" max="15" value="1">
                        
                        <div class="overlay">
                            <button type="submit">ADD</button>
                        </div>
                    </form>
                </div>

                <div class="menu-image">
                    <img src="<%= menu.getImagePath() %>" alt="<%= menu.getName() %>">
                </div>
            </div>

            <% 
                }
            %>

        </div>

        <% 
            } else {
        %>
            <p>No menu items available.</p>
        <% 
            }
        %>
    </div>

</body>
</html>
