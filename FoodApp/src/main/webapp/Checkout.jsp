<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Form</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #17202a; /* Background color */
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh; 
            color: #2c3e50; /* Text color */
            flex-direction: column; 
        }

        .header {
            text-align: center;
            color: #99a3a4; /* Header color */
            margin-bottom: 20px;
            font-size: 28px;
            font-weight: bold;
        }

        form {
            width: 380px; 
            height: 500px; 
            background-color: #99a3a4; /* Background color of the form */
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1); 
            overflow-y: auto;
            font-size: 16px;
            transition: all 0.3s ease;
        }

        form:hover {
            box-shadow: 0 6px 18px rgba(0, 0, 0, 0.2); 
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            font-size: 16px;
            color: #2c3e50; /* Label color */
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
        }

        input, select {
            width: 100%; 
            padding: 12px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 6px;
            background-color: #273746; /* Updated input field color */
            margin-top: 5px;
            box-sizing: border-box;
            color: white; /* Text color inside the input */
        }

        input:focus, select:focus {
            outline: none;
            border-color: #2c3e50; /* Focus border color */
            box-shadow: 0 0 8px rgba(44, 62, 80, 0.4);
        }

        button {
            padding: 12px 20px;
            font-size: 18px;
            color: white;
            background-color: #2c3e50; /* Button background color */
            border: none;
            border-radius: 6px;
            cursor: pointer;
            width: 100%; 
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #1c2833; /* Darker shade for button hover */
        }

        .form-group p {
            font-size: 14px;
            color: #777;
            margin-top: 10px;
            text-align: center;
        }

        @media (max-width: 768px) {
            form {
                width: 90%;
                padding: 20px;
            }
        }
    </style>
</head>
<body>

<div class="header">
    <h2>Order Form</h2>
</div>

<form action="OrderConfirmation.jsp" method="POST">
    <!-- Plate Number -->
    <div class="form-group">
        <label for="plateNo">Plate No:</label>
        <input type="text" id="plateNo" name="plateNo" required>
    </div>
    
    <!-- Street -->
    <div class="form-group">
        <label for="street">Street:</label>
        <input type="text" id="street" name="street" required>
    </div>
    
    <!-- Pin Code -->
    <div class="form-group">
        <label for="pinCode">Pin Code:</label>
        <input type="text" id="pinCode" name="pinCode" required>
    </div>

    <!-- Payment Method -->
    <div class="form-group">
        <label for="paymentMethod">Mode of Payment:</label>
        <select id="paymentMethod" name="paymentMethod" required>
            <option value="Cash on Delivery">Cash on Delivery</option>
            <option value="Credit Card">Credit Card</option>
            <option value="Debit Card">Debit Card</option>
            <option value="UPI">UPI</option>
        </select>
    </div>

    <!-- Submit Button -->
    <div class="form-group">
        <button type="submit">Confirm</button>
    </div>
</form>

</body>
</html>
