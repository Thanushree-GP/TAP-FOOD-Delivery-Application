package com.foodapp.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.foodapp.daointerfaces.MenuDAO;
import com.foodapp.dbconnect.DBConnection;
import com.foodapp.model.Menu;


public class MenuDAOImpl implements MenuDAO {

	private static Connection con;
	static ArrayList<Menu> menuList=new ArrayList<Menu>();
	private static final String INSERTQUERY = "insert into menu(menuId,restaurantId,name,description,price,isAvailable) values(?,?,?,?,?,?)";
	private static final String FETCHALL = "select * from menu where restaurantId=?";
	private static final String FETCHONE = "select * from menu where menuId=?";
	private static final String UPDATE = "update menu set price=? where menuId=?";
	private static final String DELETE = "delete from menu where menuId=?";
	static 
	{
		con=DBConnection.connect();
	}

	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet resultSet;
	private Menu m;
	@Override
	public int insert(Menu m) {
		try
		{
			pstmt=con.prepareStatement(INSERTQUERY);
			pstmt.setInt(1, m.getMenuId());
			pstmt.setInt(2, m.getRestaurantId());
			pstmt.setString(3, m.getName());
			pstmt.setString(4, m.getDescription());
			pstmt.setInt(5, m.getPrice());
			pstmt.setString(6, m.getIsAvailable());
			return pstmt.executeUpdate();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}
    @Override
	public ArrayList<Menu> fetchResMenu(int restaurantId) {
		try
		{
			pstmt=con.prepareStatement(FETCHALL);
			pstmt.setInt(1, restaurantId);
			resultSet=pstmt.executeQuery();
			menuList.clear();

			menuList=getMenuListFromDB(resultSet);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return menuList;
	}

	@Override
	public Menu fetchOne(int menuId) {
		try
		{
			pstmt=con.prepareStatement(FETCHONE);
			pstmt.setInt(1, menuId);
			resultSet=pstmt.executeQuery();
			menuList=getMenuListFromDB(resultSet);
			m=menuList.get(0);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return m;
	}

	public ArrayList<Menu> getMenuListFromDB(ResultSet resultSet)
	{
		 menuList.clear();
		  try
		  {
			  while(resultSet.next())
				{
				    menuList.add(new Menu(
					resultSet.getInt("menuId"),
					resultSet.getInt("restaurantId"),
					resultSet.getString("name"),
					resultSet.getString("description"),
					resultSet.getInt("price"),
					resultSet.getString("isAvailable"),
					resultSet.getString("imagePath")));
				}
		  }
		  catch(Exception e)
			{
				e.printStackTrace();
			}
		   return menuList;
	}
	
	@Override
	public int update(int price, int menuId) {
		try
		{
			pstmt=con.prepareStatement(UPDATE);
			pstmt.setInt(1, price);
			pstmt.setInt(2, menuId);
			return pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int delete(int menuId) {
		try
		{
			pstmt=con.prepareStatement(DELETE);
			pstmt.setInt(1, menuId);
			return pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}

}
