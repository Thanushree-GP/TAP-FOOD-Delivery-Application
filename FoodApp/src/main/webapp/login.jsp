<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login to TapFood</title>
 <style>
        body {
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-image: url('Images/Loogin.jpeg'); /* Relative path to your background image */
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
            font-family: Arial, sans-serif;
        }

        .form-container {
            border: 2px solid #000;
            border-radius: 10px;
            padding: 20px;
            width: 300px;
            background-color: rgba(255, 255, 255, 0.5); /* Slight transparency for form background */
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

        .form-container input {
            padding: 4px;
            font-size: 14px;
            border: 1px solid #ccc;
            border-radius: 4px;
            margin-bottom: 15px;
            width: 100%;
        }

        .form-container input[type="submit"] {
            background-color: #2F4F4F;
            color: #FAF0E6;
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
        <h1>Login to TapFood</h1>
        <form action="LoginServlet" method="post">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>

            <input type="submit" value="Login">

            <p>Don't have an account? <a href="register.html">Register here</a></p>
        </form>
    </div>
</body>
</html>