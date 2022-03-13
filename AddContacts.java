package com.contacts;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddContacts extends HttpServlet {

		public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			response.setContentType("text/html");  
	        PrintWriter out=response.getWriter(); 
	int n=0; 
	        Connection con=null; 
	String name=request.getParameter("name");
	String phone=request.getParameter("phone");
	        String email=request.getParameter("email");  
	          
	        
	       try{  
	            Class.forName("oracle.jdbc.driver.OracleDriver");  
	            con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","zoho","zoho");  
	            PreparedStatement ps=con.prepareStatement("insert into contacts values (?,?,?)");  
	            ps.setString(1,name);  
	            ps.setString(2,phone);  
	            ps.setString(3,email);  
	            n=ps.executeUpdate();  
	              
	            con.close();  
	        }catch(Exception ex){ex.printStackTrace();}    
	      if(n>0){  
	            out.print("<p>Record saved successfully!</p>");  
	            request.getRequestDispatcher("welcome.html").forward(request, response);  
	        }else{  
	            out.println("Sorry! unable to save record");  
	        }  
	          
	        out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}
}
