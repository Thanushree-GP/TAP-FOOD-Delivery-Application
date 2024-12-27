package com.foodapp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.foodapp.daoimpl.UserDAOImpl;
import com.foodapp.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet
{
	String url="jdbc:mysql://localhost:3306/foodapplication";
	String dbun="root";
	String dbpwd="root";
	private Connection con;
	private String checkEmail="select * from user where email=?";
	private PreparedStatement pstmt;
	private ResultSet res;
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		
		UserDAOImpl u=new UserDAOImpl();
		User user=u.isValidate(email);
		System.out.println(user);
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url, dbun, dbpwd);
			pstmt=con.prepareStatement(checkEmail);
			pstmt.setString(1, email);
			res=pstmt.executeQuery();
			if(res.next())
			{
				if(password.equals(res.getString("password")))
				{
					String name=res.getString("username");
					HttpSession session=req.getSession();
					
					String emaill=res.getString("email");
					String address=res.getString("address");
					
					
					session.setAttribute("user", user);
					
					session.setAttribute("name", name);
					session.setAttribute("email", emaill);
					session.setAttribute("address", address);
					resp.sendRedirect("GetAllRestaurant");
				//	resp.getWriter().println("Hello "+res.getString("username"));
			//		resp.sendRedirect("loginSuccess.html");
				}
				else
				{
					resp.sendRedirect("passwordMissmatch.html");
				}
			}
			else
			{
				resp.sendRedirect("invalidUser.html");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
