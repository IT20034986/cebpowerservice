package com;

import com.Inquiry;
import java.io.IOException; 
import java.util.HashMap; 
import java.util.Map; 
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/InquiryManage")
public class InquiryManage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Inquiry InquiryObj = new Inquiry();

    public InquiryManage() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String output = InquiryObj.insertInquiry(
				request.getParameter("inqName"),      
				request.getParameter("inqAccount"),
				request.getParameter("inqEmail"),
				request.getParameter("inqReason"),
				request.getParameter("inqPhone")); 
				response.getWriter().write(output);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method

		Map paras = getParasMap(request); 
		 
		 String output = InquiryObj.updateInquiry(
				    paras.get("hidInquiryIDSave").toString(),     
		    		paras.get("inqName").toString(),     
		    		paras.get("inqAccount").toString(),
		    		paras.get("inqEmail").toString(),
		    		paras.get("inqReason").toString(),
		    		paras.get("inqPhone").toString()); 		 
		 			response.getWriter().write(output);
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 Map paras = getParasMap(request); 		 
		 String output = InquiryObj.deleteInquiry(paras.get("inqID").toString());  		 
		 response.getWriter().write(output);
	}
	
	// Convert request parameters to a Map
		private static Map getParasMap(HttpServletRequest request)
		{
		 Map<String, String> map = new HashMap<String, String>();
		try
		 { 
		 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
		 String queryString = scanner.hasNext() ?
		 scanner.useDelimiter("\\A").next() : "";
		 scanner.close();
		 String[] params = queryString.split("&");
		 for (String param : params)
		 { 
		
		String[] p = param.split("=");
		 map.put(p[0], p[1]);
		 }
		 }
		catch (Exception e)
		 {
		 }
		return map;
		}

}
