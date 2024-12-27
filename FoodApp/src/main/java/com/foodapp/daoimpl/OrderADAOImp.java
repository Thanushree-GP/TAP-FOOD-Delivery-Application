package com.foodapp.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.foodapp.daointerfaces.OrderDAO;
import com.foodapp.dbconnect.DBConnection;
import com.foodapp.model.Order;


public class OrderADAOImp implements OrderDAO {
	private static Connection con;
	static ArrayList<Order> orderList=new ArrayList<Order>();
	private static final String INSERTQUERY = "insert into orders(orderId,userId,restaurantId,totalAmount,status,paymentMode) values(?,?,?,?,?,?)";
	private static final String FETCHALL = "select * from orders";
	private static final String FETCHONE = "select * from orders where orderId=?";
	private static final String UPDATE = "update orders set status=? where orderId=?";
	private static final String DELETE = "delete from orders where orderId=?";
	static 
	{
		con=DBConnection.connect();
	}

	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet resultSet;
	private Order o;
	@Override
	public int insert(Order o) {
		try
		{
			pstmt=con.prepareStatement(INSERTQUERY);
			pstmt.setInt(1, o.getOrderId());
			pstmt.setInt(2, o.getUserId());
			pstmt.setInt(3, o.getRestaurantId());
			pstmt.setInt(4, o.getTotalAmount());
			pstmt.setString(5, o.getStatus());
			pstmt.setString(6, o.getPaymentMode());
			return pstmt.executeUpdate();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public ArrayList<Order> fetchAll() {
		try
		{
			stmt=con.createStatement();
			resultSet=stmt.executeQuery(FETCHALL);
			orderList=getOrderListFromDB(resultSet);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return orderList;
	}

	@Override
	public Order fetchOne(int orderId) {
		try
		{
			pstmt=con.prepareStatement(FETCHONE);
			pstmt.setInt(1, orderId);
			resultSet=pstmt.executeQuery();
			orderList=getOrderListFromDB(resultSet);
			o=orderList.get(0);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return o;
	}
	public ArrayList<Order> getOrderListFromDB(ResultSet resultSet)
	{
		  try
		  {
			  while(resultSet.next())
				{
				    orderList.add(new Order(
					resultSet.getInt("orderId"),
					resultSet.getInt("userId"),
					resultSet.getInt("restaurantId"),
					resultSet.getInt("totalAmount"),
					resultSet.getString("status"),
					resultSet.getString("paymentMode")));
				}
		  }
		  catch(Exception e)
			{
				e.printStackTrace();
			}
		   return orderList;
	}
	@Override
	public int update(String status, int orderId) {
		try
		{
			pstmt=con.prepareStatement(UPDATE);
			pstmt.setString(1, status);
			pstmt.setInt(2, orderId);
			return pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int delete(int orderId) {

		try
		{
			pstmt=con.prepareStatement(DELETE);
			pstmt.setInt(1, orderId);
			return pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	public static int getLastOrderIdFromDatabase() {
	    int lastOrderId = 0; // Default if no orders exist
	    String query = "SELECT MAX(orderId) FROM orders"; // Replace 'orders' with your table name

	    try ( // Ensure DatabaseUtil handles the connection
	         PreparedStatement statement = con.prepareStatement(query);
	         ResultSet resultSet = statement.executeQuery()) {

	        if (resultSet.next()) {
	            lastOrderId = resultSet.getInt(1); // Retrieve MAX(orderId)
	        }

	    } catch (Exception e) {
	        System.err.println("Error fetching last order ID: " + e.getMessage());
	        e.printStackTrace();
	    }

	    return lastOrderId; // Returns 0 if table is empty or an error occurs
	
	}

}
