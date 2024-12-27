<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.foodapp.model.Restaurant" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        background-color: #b2babb;
        color: white;
    }

    .navbar {
        display: flex;
        align-items: center;
        justify-content: space-between;
        background-color: #1b2631;
        padding: 10px 20px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3);
        position: sticky;
        top: 0;
        z-index: 1000;
    }

    .navbar .profile-icon {
        display: flex;
        align-items: center;
    }

    .navbar .profile-icon img {
        border: 2px solid white;
        border-radius: 50%;
        width: 50px;
        height: 50px;
        margin-right: 10px;
    }

    .navbar .profile-icon img[src*="404"] {
        content: url('https://i.pinimg.com/736x/55/ca/7d/55ca7d73290fe15140a4da56c06aca7f.jpg');
    }

    .navbar .profile-icon span {
        color: white;
    }

    .navbar .cuisines {
        display: flex;
        align-items: center;
        overflow: hidden;
        position: relative;
        flex: 1;
        margin-left: 20px;
    }

    .navbar .cuisines .scroll-container {
        display: flex;
        gap: 80px; /* Increased spacing between cuisines */
        animation: scroll-left 10s linear infinite;
    }

    @keyframes scroll-left {
        0% {
            transform: translateX(100%);
        }
        100% {
            transform: translateX(-100%);
        }
    }

    .navbar .cuisines a {
        color: white;
        text-decoration: none;
        display: flex;
        align-items: center;
        flex-direction: column;
        flex-shrink: 0;
    }

    .navbar .cuisines a .circle {
        display: flex;
        align-items: center;
        justify-content: center;
        width: 50px;
        height: 50px;
        border-radius: 50%;
        overflow: hidden;
    }

    .navbar .cuisines a .circle img {
        width: 100%;
        height: 100%;
        object-fit: cover;
    }

    .header {
        background-image: url('https://i.pinimg.com/736x/b2/65/b2/b265b29ad182b306694348f028fdf093.jpg');
        background-size: cover;
        background-position: center;
        color: #e5e8e8 ;
        padding: 60px 20px;
        text-align: center;
        font-size: 48px; /* Increased font size */
        font-weight: bold;
        border-radius: 20px;
        margin: 20px auto;
        width: 90%;
        box-shadow: 0 4px 10px rgba(0, 0, 0, 0.5);
    }

    .container {
        display: flex;
        flex-wrap: wrap;
        justify-content: space-around;
        gap: 20px;
        padding: 20px;
    }

    .card {
        background-color: #1b2631;
        color: #fff;
        border-radius: 8px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3);
        width: 30%;
        padding: 15px;
        box-sizing: border-box;
        transition: transform 0.3s ease, box-shadow 0.3s ease;
    }

    .card img {
        width: 100%;
        height: 150px;
        object-fit: cover;
        border-radius: 8px;
        margin-bottom: 10px;
    }

    .card h2 {
        font-size: 18px;
        margin: 10px 0;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    .card h2 .rating {
        display: inline-block;
        background-color: #0e6251;
        color: white;
        padding: 2px 8px;
        border-radius: 16px;
        font-size: 12px;
        font-weight: bold;
    }

    .card h2 .rating::before {
        content: '⭐ ';
    }

    .card .chip {
        display: inline-block;
        padding: 5px 10px;
        font-size: 14px;
        background-color: #444;
        border-radius: 16px;
        margin: 10px 0;
        color: #00ff00;
    }

    .card .address {
        display: flex;
        align-items: center;
        gap: 5px;
    }

    .card .address::before {
        content: '📍';
        color: red;
    }

    .card .view-menu-btn {
        display: block; /* Ensures the button takes up its own line */
        margin: 10px auto; /* Centers the button horizontally */
        padding: 10px 20px; /* Makes it rectangular */
        font-size: 14px;
        background-color: #444;
        color: #00ff00;
        border-radius: 4px;
        text-align: center;
        cursor: pointer;
        border: none;
        transition: background-color 0.3s ease;
    }

    .card .view-menu-btn:hover {
        background-color: #555;
    }

    .card:hover {
        transform: scale(1.1);
        box-shadow: 0 8px 20px rgba(0, 0, 0, 0.8);
        z-index: 100;
    }
</style>
</head>
<body>
    <!-- Header -->
    <div class="header">
        Welcome to TapFoodApp!
    </div>

    <!-- Navigation Bar -->
    <div class="navbar">
        <div class="profile-icon">
            <img src="<%= session.getAttribute("profilePic") != null ? session.getAttribute("profilePic") : "https://i.pinimg.com/736x/55/ca/7d/55ca7d73290fe15140a4da56c06aca7f.jpg" %>" alt="Profile Icon">
            <span>Hi, <%= session.getAttribute("name") %></span>
        </div>
        <div class="cuisines">
            <div class="scroll-container">
                <a href="#">
                    <div class="circle">
                        <img src="https://media-assets.swiggy.com/swiggy/image/upload/fl_lossy,f_auto,q_auto/PC_Mweb/South%20Indian.png" alt="Indian Cuisine">
                    </div>
                    Indian
                </a>
                <a href="#">
                    <div class="circle">
                        <img src="https://media-assets.swiggy.com/swiggy/image/upload/fl_lossy,f_auto,q_auto/PC_Mweb/Pizza.png" alt="European Cuisine">
                    </div>
                    European
                </a>
                <a href="#">
                    <div class="circle">
                        <img src="https://media-assets.swiggy.com/swiggy/image/upload/fl_lossy,f_auto,q_auto/PC_Mweb/Pasta.png" alt="Japanese Cuisine">
                    </div>
                    Japanese
                </a>
                <a href="#">
                    <div class="circle">
                        <img src="https://media-assets.swiggy.com/swiggy/image/upload/fl_lossy,f_auto,q_auto/PC_Mweb/Burger.png" alt="American Cuisine">
                    </div>
                    American
                </a>
                <a href="#">
                    <div class="circle">
                        <img src="https://media-assets.swiggy.com/swiggy/image/upload/fl_lossy,f_auto,q_auto/PC_Mweb/Noodles.png" alt="Italian Cuisine">
                    </div>
                    Italian
                </a>
                <a href="#">
				    <div class="circle">
				        <img src="https://media-assets.swiggy.com/swiggy/image/upload/fl_lossy,f_auto,q_auto/PC_Mweb/Salad.png" alt="Chinese Cuisine">
				    </div>
				    Chinese
				</a>
				<a href="#">
				    <div class="circle">
				        <img src="https://media-assets.swiggy.com/swiggy/image/upload/fl_lossy,f_auto,q_auto/PC_Mweb/Momo.png" alt="Thai Cuisine">
				    </div>
				    Thai
				</a>
				<a href="#">
				    <div class="circle">
				        <img src="https://media-assets.swiggy.com/swiggy/image/upload/fl_lossy,f_auto,q_auto/PC_Mweb/Shawarma.png" alt="Arabic Cuisine">
				    </div>
				    Arabic
				</a>
				<a href="#">
				    <div class="circle">
				        <img src="https://media-assets.swiggy.com/swiggy/image/upload/fl_lossy,f_auto,q_auto/PC_Mweb/Pure%20Veg.png" alt="Mexican Cuisine">
				    </div>
				    Mexican
				</a>
                
                <a href="#">
                    <div class="circle">
                        <img src="https://media-assets.swiggy.com/swiggy/image/upload/fl_lossy,f_auto,q_auto/PC_Mweb/Shake.png" alt="Cafe">
                    </div>
                    Cafe
                </a>
                <a href="#">
                    <div class="circle">
                        <img src="https://media-assets.swiggy.com/swiggy/image/upload/fl_lossy,f_auto,q_auto/PC_Mweb/Ice%20Cream.png" alt="Ice Cream">
                    </div>
                    Ice-cream
                </a>
                <!-- Add more cuisines here as needed -->
            </div>
        </div>
    </div>

    <!-- Cards -->
    <div class="container">
        <% 
            List<Restaurant> rList = (List<Restaurant>) session.getAttribute("restaurantList");
            if (rList == null || rList.isEmpty()) {
                response.sendRedirect("GetAllRestaurant");
                return;
            }
            for (Restaurant r : rList) { 
        %>
        <div class="card">
            <img src="<%= r.getImagePath() %>" alt="Restaurant Image">
            <h2>
                <span><%= r.getName() %></span>
                <span class="rating"><%= r.getRatings() %></span>
            </h2>
            <p class="chip"><%= r.getCuisineType() %></p>
            <p class="address"><%= r.getAddress() %></p>
            <form action="MenuServlet" method="post">
                <input type="hidden" name="restaurantId" value="<%= r.getRestaurantId() %>">
                <button type="submit" class="view-menu-btn">View Menu</button>
            </form>
        </div>
        <% } %>
    </div>
</body>
</html>
