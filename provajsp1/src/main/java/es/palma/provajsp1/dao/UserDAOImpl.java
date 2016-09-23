package es.palma.provajsp1.dao;

import java.text.*;
import java.util.*;
import java.util.Date;

import es.palma.provajsp1.entities.Company;
import es.palma.provajsp1.entities.UserBean;

import java.sql.*;

public class UserDAOImpl implements UserDAO 	
{
   static Connection currentCon = null;
   static ResultSet rs = null;  
	
	
	
  

@Override
public UserBean login(UserBean bean) {
    //preparing some objects for connection 
    Statement stmt = null;    
	
    String userId = bean.getId();    
    String password = bean.getPassword();   
    
    if (userId.equals("bernat") && password.equals("1234")) {
       bean.setLl1("Ginard");
       bean.setValid(true);
    }
    else
    {
  	  bean.setValid(false);
    }
    /*	    
    String searchQuery =
          "select * from users where username='"
                   + username
                   + "' AND password='"
                   + password
                   + "'";
	    
 // "System.out.println" prints in the console; Normally used to trace the process
 System.out.println("Your user name is " + username);          
 System.out.println("Your password is " + password);
 System.out.println("Query: "+searchQuery);
	    
 try 
 {
    //connect to DB 
    currentCon = ConnectionManager.getConnection();
    stmt=currentCon.createStatement();
    rs = stmt.executeQuery(searchQuery);	        
    boolean more = rs.next();
	       
    // if user does not exist set the isValid variable to false
    if (!more) 
    {
       System.out.println("Sorry, you are not a registered user! Please sign up first");
       bean.setValid(false);
    } 
	        
    //if user exists set the isValid variable to true
    else if (more) 
    {
       String firstName = rs.getString("FirstName");
       String lastName = rs.getString("LastName");
	     	
       System.out.println("Welcome " + firstName);
       bean.setFirstName(firstName);
       bean.setLastName(lastName);
       bean.setValid(true);
    }
 } 

 catch (Exception ex) 
 {
    System.out.println("Log In failed: An Exception has occurred! " + ex);
 } 
	    
 //some exception handling
 finally 
 {
    if (rs != null)	{
       try {
          rs.close();
       } catch (Exception e) {}
          rs = null;
       }
	
    if (stmt != null) {
       try {
          stmt.close();
       } catch (Exception e) {}
          stmt = null;
       }
	
    if (currentCon != null) {
       try {
          currentCon.close();
       } catch (Exception e) {
       }

       currentCon = null;
    }
 } */

return bean;
}

@Override
public List<UserBean> getUserList() {
	// TODO Auto-generated method stub
	Company empresa=getCompany();
	return empresa.getUsuaris();
}

@Override
public void altaUsuario(UserBean usuari) {
    	
}	

public Company getCompany() {
	
	 ArrayList<UserBean> llista = new ArrayList<UserBean>();
	 	llista.add(new UserBean("bginardp@gmail.com","1234","Ginard","Prats","Bernat","123",new Date(System.currentTimeMillis())));
	 	llista.add(new UserBean("lginardl@gmail.com","1234","Ginard","Llabres","Laia", "345",new Date(System.currentTimeMillis())));
	 	llista.add(new UserBean("aginardr@gmail.com","1234","Ginard","Ripoll","Albert","345",new Date(System.currentTimeMillis())));
	 	llista.add(new UserBean("illabress@gmail.com","1234","Llabres","Serra","Isabel","345",new Date(System.currentTimeMillis())));
		
	Company empresa = new Company("Salpon SA",llista );
	return empresa;
	
}

}