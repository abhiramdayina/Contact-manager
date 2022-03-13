package com.contacts;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewContacts extends HttpServlet {

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
	           PreparedStatement ps=con.prepareStatement("select * from contacts");  
	            ResultSet rs=ps.executeQuery(); 
	out.println("<table border=1><tr><th>Name</th><th>Phone No</th><th>Email</th></tr>"); 
	            while(rs.next()){ 
	                out.print("<tr><td>"+rs.getString(1)+"</td>");  
	                out.println("<td>"+rs.getString(2)+"</td>");  
	                out.println("<td>"+rs.getString(3)+"</td></tr>");  
	                
	            }  
	out.println("</table><br><br>");
	out.println("<a href=\"welcome.html\">BACK</a>");
	
	            con.close();  
	        }catch(Exception ex){ex.printStackTrace();}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

	
}
