package com.foodapp.servlet;

import com.foodapp.daoimpl.OrderADAOImp;

public class AutoGenerateNumber {
    public static int generateUniqueOrderId() {
        int maxOrderId = OrderADAOImp.getLastOrderIdFromDatabase();
        System.out.println("Max Order ID from DB: " + maxOrderId);

        if (maxOrderId == 0) {
            return 1; // Start with order ID 1 if no previous orders exist
        } else {
            return maxOrderId + 1;
        }
    }
}
