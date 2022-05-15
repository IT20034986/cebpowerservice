package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;


public class Inquiry {
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/electroservice?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertInquiry(String inqName, String inqAccount, String inqEmail, String inqReason, String inqPhone)  
	{   
		String output = ""; 	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for inserting."; } 
	 
			// create a prepared statement 
			String query = " insert into inquirym(`inqID`,`inqName`,`inqAccount`,`inqEmail`,`inqReason`,`inqPhone`)" + " values (?, ?, ?, ?, ?, ?)"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, inqName);
			 preparedStmt.setString(3, inqAccount);
			 preparedStmt.setString(4, inqEmail);
			 preparedStmt.setString(5, inqReason);
			 preparedStmt.setString(6, inqPhone);
			
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	   
			String newInquiry = readInquiry(); 
			output =  "{\"status\":\"success\", \"data\": \"" + newInquiry + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while inserting the Inquiry.\"}";  
			System.err.println(e.getMessage());   
		} 		
	  return output;  
	} 	
	
	public String readInquiry()  
	{   
		String output = ""; 
		try   
		{    
			Connection con = connect(); 
		
			if (con == null)    
			{
				return "Error while connecting to the database for reading."; 
			} 
	 
			// Prepare the html table to be displayed    
			output = "<table border=\'1\'><tr><th>Customer Name</th><th>Account No</th><th>Email</th><th>Reason</th><th>Phone No</th><th>Update</th><th>Remove</th></tr>";
	 
			String query = "select * from inquirym";    
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
	 
			// iterate through the rows in the result set    
			while (rs.next())    
			{     
				 String inqID = Integer.toString(rs.getInt("inqID"));
				 String inqName = rs.getString("inqName");
				 String inqAccount = rs.getString("inqAccount");
				 String inqEmail = rs.getString("inqEmail");
				 String inqReason = rs.getString("inqReason");
				 String inqPhone = rs.getString("inqPhone");
				 
				// Add into the html table 
				output += "<tr><td><input id=\'hidInquiryIDUpdate\' name=\'hidInquiryIDUpdate\' type=\'hidden\' value=\'" + inqID + "'>" 
							+ inqName + "</td>"; 
				output += "<td>" + inqAccount + "</td>";
				output += "<td>" + inqEmail + "</td>";
				output += "<td>" + inqReason + "</td>";
				output += "<td>" + inqPhone + "</td>";
	 
				// buttons     
				output +="<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"       
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-inquiryid='" + inqID + "'>" + "</td></tr>"; 
			
			}
			con.close(); 
	   
			output += "</table>";   
		}   
		catch (Exception e)   
		{    
			output = "Error while reading the Inquiry.";    
			System.err.println(e.getMessage());   
		} 	 
		return output;  
	}
	
	public String updateInquiry(String inqID, String inqName, String inqAccount, String inqEmail, String inqReason, String inqPhone)  
	{   
		String output = "";  
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for updating."; } 
	 
			// create a prepared statement    
			String query = "UPDATE inquirym SET inqName=?,inqAccount=?,inqEmail=?,inqReason=?,inqPhone=?"  + "WHERE inqID=?";  	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setString(1, inqName);
			 preparedStmt.setString(2, inqAccount);
			 preparedStmt.setString(3, inqEmail);
			 preparedStmt.setString(4, inqReason);
			 preparedStmt.setString(5, inqPhone);
			 preparedStmt.setInt(6, Integer.parseInt(inqID)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close();  
			String newInquiry = readInquiry();    
			output = "{\"status\":\"success\", \"data\": \"" + newInquiry + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while updating the Inquiry.\"}";   
			System.err.println(e.getMessage());   
		} 	 
	  return output;  
	} 
	
	public String deleteInquiry(String inqID)   
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{
				return "Error while connecting to the database for deleting."; 			
			} 
	 
			// create a prepared statement    
			String query = "delete from inquirym where inqID=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setInt(1, Integer.parseInt(inqID)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newInquiry = readInquiry();    
			output = "{\"status\":\"success\", \"data\": \"" +  newInquiry + "\"}";    
		}   
		catch (Exception e)   
		{    
			output = "Error while deleting the Inquiry.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	
}
