<%@ page import="com.Inquiry"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inquiry</title>
<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<script src="Components/jquery-3.4.1.min.js"></script> 
<script src="Components/inquiry.js"></script> 
</head>
<body>
<div class="container"> 
	<div class="row">  
		<div class="col-6"> 
			<h1>Inquiry Manage</h1>
				<form id="formInquiry" name="formInquiry" method="post" action="InquiryUI.jsp">  
					Customer Name:  
 	 				<input id="inqName" name="inqName" type="text"  class="form-control form-control-sm">
					<br>Account No:   
  					<input id="inqAccount" name="inqAccount" type="text" class="form-control form-control-sm">   
  					<br>Email:   
  					<input id="inqEmail" name="inqEmail" type="text"  class="form-control form-control-sm">
  					<br>Reason:   
  					<input id="inqReason" name="inqReason" type="text"  class="form-control form-control-sm">
  					<br>Phone No:   
  					<input id="inqPhone" name="inqPhone" type="text"  class="form-control form-control-sm">
					<br>  
					<input id="btnSave" name="btnSave" type="button" value="SAVE" class="btn btn-primary">  
					<input type="hidden" id="hidInquiryIDSave" name="hidInquiryIDSave" value=""> 
				</form>
				
				<div id="alertSuccess" class="alert alert-success"> </div>
				
			   <div id="alertError" class="alert alert-danger"></div>
				
			   <br>
				<div id="divInquiryGrid">
					<%
					    Inquiry InquiryObj = new Inquiry();
						out.print(InquiryObj.readInquiry());
					%>
				</div>
				
				 
			</div>
		</div>
</div>
 
</body>
</html>