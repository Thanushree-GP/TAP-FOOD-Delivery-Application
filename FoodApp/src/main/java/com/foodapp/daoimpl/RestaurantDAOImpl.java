package com.foodapp.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.foodapp.daointerfaces.RestaurantDAO;
import com.foodapp.dbconnect.DBConnection;
import com.foodapp.model.Restaurant;


public class RestaurantDAOImpl implements RestaurantDAO {

	
	private static Connection con;
	static ArrayList<Restaurant> restaurantList=new ArrayList<Restaurant>();
	private static final String INSERTQUERY = "insert into restaurant(restaurantId,name,cuisineType,deliveryTime,address,ratings,isActive) values(?,?,?,?,?,?,?)";
	private static final String FETCHALL = "select * from restaurant";
	private static final String FETCHONE = "select * from restaurant where restaurantId=?";
	private static final String UPDATE = "update restaurant set ratings=? where restaurantId=?";
	private static final String DELETE = "delete from restaurant where restaurantId=?";
	static 
	{
		con=DBConnection.connect();
	}

	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet resultSet;
	private Restaurant r;
	
	@Override
	public int insert(Restaurant r) {
		try
		{
			pstmt=con.prepareStatement(INSERTQUERY);
			pstmt.setInt(1, r.getRestaurantId());
			pstmt.setString(2, r.getName());
			pstmt.setString(3, r.getCuisineType());
			pstmt.setInt(4, r.getDeliveryTime());
			pstmt.setString(5, r.getAddress());
			pstmt.setFloat(6, r.getRatings());
			pstmt.setString(7, r.getIsActive());
	
			return pstmt.executeUpdate();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
		
	}

	@Override
	public ArrayList<Restaurant> fetchAll() {
		try
		{
			stmt=con.createStatement();
			resultSet=stmt.executeQuery(FETCHALL);
			restaurantList=getRestaurantListFromDB(resultSet);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return restaurantList;
	}
	public ArrayList<Restaurant> getRestaurantListFromDB(ResultSet resultSet)
	{
		  try
		  {
			  while(resultSet.next())
				{
				  restaurantList.add(new Restaurant(
					resultSet.getInt("restaurantId"),
					resultSet.getString("name"),
					resultSet.getString("cuisineType"),
					resultSet.getInt("deliveryTime"),
					resultSet.getString("address"),
					resultSet.getFloat("ratings"),
					resultSet.getString("isActive"),
					resultSet.getString("imagePath")));
				}
		  }
		  catch(Exception e)
			{
				e.printStackTrace();
			}
		   return restaurantList;
	}
	@Override
	public Restaurant fetchOne(int restaurantId) {
		try
		{
			pstmt=con.prepareStatement(FETCHONE);
			pstmt.setInt(1, restaurantId);
			resultSet=pstmt.executeQuery();
			restaurantList=getRestaurantListFromDB(resultSet);
			r=restaurantList.get(0);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return r;
	
	}
	@Override
	public int update(float ratings, int restaurantId) {
		try
		{
			pstmt=con.prepareStatement(UPDATE);
			pstmt.setFloat(1, ratings);
			pstmt.setInt(2, restaurantId);
			return pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
		
	}

	@Override
	public int delete(int restaurantId) {
		try
		{
			pstmt=con.prepareStatement(DELETE);
			pstmt.setInt(1, restaurantId);
			return pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
		
	}

	

}
