package com.foodapp.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.foodapp.dbconnect.DBConnection;
import com.foodapp.model.User;
import com.foodapp.daointerfaces.UserDAO;


public class UserDAOImpl implements UserDAO {

	private static Connection con;
	static ArrayList<User> userList=new ArrayList<User>();
	private static final String INSERTQUERY = "insert into user(username,password,email,address) values(?,?,?,?)";
	private static final String FETCHALL = "select * from user";
	private static final String FETCHONE = "select * from user where userId=?";
	private static final String UPDATE = "update user set email=? where userId=?";
	private static final String DELETE = "delete from user where userId=?";
	private static final String SELECT_BY_EMAIL = "select * from user where email=?";
	static 
	{
		con=DBConnection.connect();
	}

	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet resultSet;
	private User u;
	
	@Override
	public int insert(User u)
	{
		try
		{
			pstmt=con.prepareStatement(INSERTQUERY);
			pstmt.setString(1, u.getUsername());
			pstmt.setString(2, u.getPassword());
			pstmt.setString(3, u.getEmail());
			pstmt.setString(4, u.getAddress());
			return pstmt.executeUpdate();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public ArrayList<User> fetchAll() 
	{
		try
		{
			stmt=con.createStatement();
			resultSet=stmt.executeQuery(FETCHALL);
			userList=getUserListFromDB(resultSet);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return userList;
	}

	@Override
	public User fetchOne(int userId) 
	{
		try
		{
			pstmt=con.prepareStatement(FETCHONE);
			pstmt.setInt(1, userId);
			resultSet=pstmt.executeQuery();
			userList=getUserListFromDB(resultSet);
			u=userList.get(0);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return u;
		
	}
	public ArrayList<User> getUserListFromDB(ResultSet resultSet)
	{
		  try
		  {
			  while(resultSet.next())
				{
				    userList.add(new User(
					resultSet.getString("username"),
					resultSet.getString("password"),
					resultSet.getString("email"),
					resultSet.getString("address")));
				}
		  }
		  catch(Exception e)
			{
				e.printStackTrace();
			}
		   return userList;
	}
	@Override
	public int update(String email, int userId)
	{
		try
		{
			pstmt=con.prepareStatement(UPDATE);
			pstmt.setString(1, email);
			pstmt.setInt(2, userId);
			return pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
		
	}

	@Override
	public int delete(int userId) 
	{
		try
		{
			pstmt=con.prepareStatement(DELETE);
			pstmt.setInt(1, userId);
			return pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	public User isValidate(String email) {
	    User user = null;
	    try (PreparedStatement stmt = con.prepareStatement(SELECT_BY_EMAIL)) {
	        stmt.setString(1, email);
	        System.out.println(email);
	        ResultSet rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	     
	            int id = rs.getInt("userId");
	            String name = rs.getString("username");
	            String password =rs.getString("password");
	            String address = rs.getString("address");
	          //  System.out.println(id+" "+name+" "+password+" "+ email+" "+address);
	            user = new User(id, name, password, email, address);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
//	    System.out.println(user);
	    
	    return user; 
	}
}
 
