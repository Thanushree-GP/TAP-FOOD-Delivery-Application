package com.foodapp.register;

import java.io.IOException;

import com.foodapp.daoimpl.UserDAOImpl;
import com.foodapp.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/Register")
public class Register extends HttpServlet 
{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String password=req.getParameter("password");
		String confirmPassword=req.getParameter("confirmPassword");
		
		if(password.equals(confirmPassword))
		{
			String username=req.getParameter("username");
			String email=req.getParameter("email");
			String address=req.getParameter("address");
			
			User u=new User(username,password,email,address);
			UserDAOImpl uo=new UserDAOImpl();
			int x=uo.insert(u);
			if(x==1)
			{
				resp.sendRedirect("login.html");
			}
			else
			{
				resp.sendRedirect("registerFailed.html");
			}
		}
		else
		{
			resp.sendRedirect("passwordMissmatch.html");
		}
	}

}
