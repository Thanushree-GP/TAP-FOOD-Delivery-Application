package com.foodapp.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.foodapp.daointerfaces.OrderItemsDAO;
import com.foodapp.dbconnect.DBConnection;
import com.foodapp.model.OrderItems;



public class OrderItemsDAOImp implements OrderItemsDAO {
	private static Connection con;
	static ArrayList<OrderItems> orderItemsList=new ArrayList<OrderItems>();
	private static final String INSERTQUERY = "insert into orderItems(orderItemId,orderId,menuId,quantity,itemTotal) values(?,?,?,?,?)";
	private static final String FETCHALL = "select * from orderItems";
	private static final String FETCHONE = "select * from orderItems where orderItemId=?";
	private static final String UPDATE = "update orderItems set quantity=? where orderItemId=?";
	private static final String DELETE = "delete from orderItems where orderItemId=?";
	static 
	{
		con=DBConnection.connect();
	}

	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet resultSet;
	private OrderItems o;
	@Override
	public int insert(OrderItems o) {
		try
		{
			pstmt=con.prepareStatement(INSERTQUERY);
			pstmt.setInt(1, o.getOrderItemId());
			pstmt.setInt(2, o.getOrderId());
			pstmt.setInt(3, o.getMenuId());
			pstmt.setInt(4, o.getQuantity());
			pstmt.setInt(5, o.getItemTotal());
			return pstmt.executeUpdate();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public ArrayList<OrderItems> fetchAll() {
		try
		{
			stmt=con.createStatement();
			resultSet=stmt.executeQuery(FETCHALL);
			orderItemsList=getOrderItemsListFromDB(resultSet);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return orderItemsList;
	}
	
	@Override
	public OrderItems fetchOne(int orderItemId) {
		try
		{
			pstmt=con.prepareStatement(FETCHONE);
			pstmt.setInt(1, orderItemId);
			resultSet=pstmt.executeQuery();
			orderItemsList=getOrderItemsListFromDB(resultSet);
			o=orderItemsList.get(0);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return o;
	}
	public ArrayList<OrderItems> getOrderItemsListFromDB(ResultSet resultSet)
	{
		  try
		  {
			  while(resultSet.next())
				{
				  orderItemsList.add(new OrderItems(
					resultSet.getInt("orderItemId"),
					resultSet.getInt("orderId"),
					resultSet.getInt("menuId"),
					resultSet.getInt("quantity"),
					resultSet.getInt("itemTotal")));
				}
		  }
		  catch(Exception e)
			{
				e.printStackTrace();
			}
		   return orderItemsList;
	}
	@Override
	public int update(int quantity, int orderItemId) {
		try
		{
			pstmt=con.prepareStatement(UPDATE);
			pstmt.setInt(1, quantity);
			pstmt.setInt(2, orderItemId);
			return pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int delete(int orderItemId) {
		try
		{
			pstmt=con.prepareStatement(DELETE);
			pstmt.setInt(1, orderItemId);
			return pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}

}
