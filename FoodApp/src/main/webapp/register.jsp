<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="jakarta.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register to TapFood</title>
<style>
        body {
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-image: url('Images/register.jpeg'); /* Replace 'background.jpg' with the path to your image */
            background-size: cover;
            background-position: center;
            font-family: Arial, sans-serif;
        }
        .form-container {
            border: 2px solid #000;
            border-radius: 10px;
            padding: 20px;
            width: 300px;
            background-color: rgba(255, 255, 255, 0.5); /* Slight transparency for the form background */
            text-align: center;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .form-container h1 {
            margin-bottom: 20px;
            font-size: 26px;
            color: #2F4F4F;
        }
        .form-container form {
            display: flex;
            flex-direction: column;
        }
        .form-container label {
            text-align: left;
            margin-bottom: 5px;
            font-size: 14px;
            color: #555;
        }
        .form-container input, .form-container textarea {
            padding: 4px;
            font-size: 14px;
            border: 1px solid #ccc;
            border-radius: 4px;
            margin-bottom: 15px;
            width: 100%;
        }
        .form-container input[type="submit"] {
            background-color: #2F4F4F;
            color: #fff;
            border: none;
            cursor: pointer;
            font-size: 16px;
        }
        .form-container input[type="submit"]:hover {
            background-color: #0056b3;
        }
        .form-container a {
            color: #007BFF;
            text-decoration: none;
        }
        .form-container a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
	 <div class="form-container">
        <h1>Register to TapFood</h1>
        <form action="Register" method="post">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>


            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
            
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>

            <label for="confirmPassword">Confirm Password:</label>
            <input type="password" id="confirmPassword" name="confirmPassword" required>

            <label for="address">Address:</label>
            <textarea id="address" name="address" rows="4" cols="30" required></textarea>

            <input type="submit" value="Register">

            <p>Already registered? <a href="login.jsp">Go to Login Page</a></p>
        </form>
    </div>
	<%--<h1>Hello <% out.println(session.getAttribute("name")); %></h1>
	<table border="1px solid black">
	<tr><td>Email </td><td><% out.println(session.getAttribute("email")); %></td></tr>
	<tr><td>Address</td> <td><% out.println(session.getAttribute("address")); %></td></tr>
	</table> --%>
</body>
</html>