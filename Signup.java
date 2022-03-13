package com.signup;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Signup extends HttpServlet {

		public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");  
        PrintWriter out=response.getWriter(); 
int n=0; 
        Connection con=null; 
        String email=request.getParameter("email");  
        String password=request.getParameter("password");  
        String secret=request.getParameter("secret");  
       try{  
            Class.forName("oracle.jdbc.driver.OracleDriver");  
            con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","zoho","zoho");  
            PreparedStatement ps=con.prepareStatement("insert into signup values (?,?,?)");  
            ps.setString(1,email);  
            ps.setString(2,password);  
            ps.setString(3,secret);  
            n=ps.executeUpdate();  
              
            con.close();  
        }catch(Exception ex){ex.printStackTrace();}    
          
          
        if(n>0){  
            out.print("<p>Record saved successfully!</p>");  
            request.getRequestDispatcher("signin.html").forward(request, response);  
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
