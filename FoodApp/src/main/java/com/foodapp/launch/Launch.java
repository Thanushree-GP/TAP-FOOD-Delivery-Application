package com.foodapp.launch;




import java.util.ArrayList;

import com.foodapp.daoimpl.MenuDAOImpl;
import com.foodapp.daoimpl.UserDAOImpl;
import com.foodapp.daointerfaces.UserDAO;
import com.foodapp.model.Menu;

public class Launch {
public static void main(String[] args) {
	
	 MenuDAOImpl menuDAO = new MenuDAOImpl();
     ArrayList<Menu> menuList = menuDAO.fetchResMenu(1);
     System.out.println(menuList);

}
}
