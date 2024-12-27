package com.foodapp.daoimpl;

import java.util.HashMap;
import java.util.Map;

import com.foodapp.model.CartItem;

public class Cart
{
	
	public Map<Integer, CartItem> addItem(CartItem newItem, Map<Integer, CartItem> items) {
	    int itemId = newItem.getItemId();
	    if (items.containsKey(itemId)) {
	        System.out.println("old item");
	        CartItem existingItem = items.get(itemId);
	        existingItem.setQuantity(existingItem.getQuantity() + newItem.getQuantity());
	    } else {
	        System.out.println("new item");
	        items.put(itemId, newItem);
	    }
	    return items; // Ensure this line ends with a semicolon
	}

	
	//update the quantity of an item in the cart
	public Map<Integer, CartItem> updateItem(int itemId, int quantity,Map<Integer, CartItem> items)
	{
		if(items.containsKey(itemId))
		{
			if(quantity <=0)
			{
				items.remove(itemId);
			}
			else
			{
				items.get(itemId).setQuantity(quantity);
			}
		}	
		return items;
	}

	
	
	
	
	//get all items in the cart
	public Map<Integer, CartItem> getItems(Map<Integer, CartItem> items)
	{
		return items;
	}
	
	
	
	
}
