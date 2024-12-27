package com.foodapp.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.foodapp.daointerfaces.OrderHistoryDAO;
import com.foodapp.dbconnect.DBConnection;
import com.foodapp.model.OrderHistory;



public class OrderHistoryDAOImpl implements OrderHistoryDAO {
	private static Connection con;
	static ArrayList<OrderHistory> orderHistoryList=new ArrayList<OrderHistory>();
	private static final String INSERTQUERY = "insert into orderHistory(orderHistoryId,orderId,userId,totalAmount,status) values(?,?,?,?,?)";
	private static final String FETCHALL = "select * from orderHistory";
	private static final String FETCHONE = "select * from orderHistory where orderHistoryId=?";
	private static final String UPDATE = "update orderHistory set status=? where orderHistoryId=?";
	private static final String DELETE = "delete from orderHistory where orderHistoryId=?";
	static 
	{
		con=DBConnection.connect();
	}

	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet resultSet;
	private OrderHistory o;
	
	@Override
	public int insert(OrderHistory o) {
		try
		{
			pstmt=con.prepareStatement(INSERTQUERY);
			pstmt.setInt(1, o.getOrderHistoryId());
			pstmt.setInt(2, o.getOrderId());
			pstmt.setInt(3, o.getUserId());
			pstmt.setInt(4, o.getTotalAmount());
			pstmt.setString(5, o.getStatus());
			return pstmt.executeUpdate();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public ArrayList<OrderHistory> fetchAll() {
		try
		{
			stmt=con.createStatement();
			resultSet=stmt.executeQuery(FETCHALL);
			orderHistoryList=getOrderHistoryListFromDB(resultSet);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return orderHistoryList;
	}
	@Override
	public OrderHistory fetchOne(int orderHistoryId) {
		try
		{
			pstmt=con.prepareStatement(FETCHONE);
			pstmt.setInt(1, orderHistoryId);
			resultSet=pstmt.executeQuery();
			orderHistoryList=getOrderHistoryListFromDB(resultSet);
			o=orderHistoryList.get(0);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return o;
	}
	public ArrayList<OrderHistory> getOrderHistoryListFromDB(ResultSet resultSet)
	{
		  try
		  {
			  while(resultSet.next())
				{
				  orderHistoryList.add(new OrderHistory(
					resultSet.getInt("orderHistoryId"),
					resultSet.getInt("orderId"),
					resultSet.getInt("userId"),
					resultSet.getInt("totalAmount"),
					resultSet.getString("status")));
				}
		  }
		  catch(Exception e)
			{
				e.printStackTrace();
			}
		   return orderHistoryList;
	}
	

	@Override
	public int update(String status, int orderHistoryId) {
		try
		{
			pstmt=con.prepareStatement(UPDATE);
			pstmt.setString(1, status);
			pstmt.setInt(2, orderHistoryId);
			return pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
		
	}
	

	@Override
	public int delete(int orderHistoryId) {
		try
		{
			pstmt=con.prepareStatement(DELETE);
			pstmt.setInt(1, orderHistoryId);
			return pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}

}
