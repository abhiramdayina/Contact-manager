package com.signin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Signin extends HttpServlet {

		public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			response.setContentType("text/html");  
	        PrintWriter out=response.getWriter();  
	        Connection con=null; 
	        String email=request.getParameter("email");  
	        String password=request.getParameter("password");  
	        
	       try{  
	            Class.forName("oracle.jdbc.driver.OracleDriver");  
	            con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","zoho","zoho");  
	            PreparedStatement ps=con.prepareStatement("select email,password from signup where email=?");
	            ps.setString(1,email);
	            ResultSet rs=ps.executeQuery();
	            
	            if(rs.next())
	            {
	            if(email.equals(rs.getString(1)) && password.equals(rs.getString(2))){  
	              request.getRequestDispatcher("welcome.html").forward(request, response);     
	            }  
	             else
	            {
	             out.print("<p>email/password invalid</p>"); 
	             request.getRequestDispatcher("signin.html").forward(request, response); 
	            }
	            }
	            con.close();  
	            
	        }catch(Exception ex){ex.printStackTrace();}            
	        out.close();  

	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

}
