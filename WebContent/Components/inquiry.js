$(document).ready(function() 
{  
		$("#alertSuccess").hide();  
	    $("#alertError").hide(); 
}); 
 
 
// SAVE ============================================ 
$(document).on("click", "#btnSave", function(event) 
{  
	// Clear alerts---------------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 
 
	// Form validation-------------------  
	var status = validateInquiryForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 
 
	// If valid------------------------  
	var type = ($("#hidInquiryIDSave").val() == "") ? "POST" : "PUT"; 

	$.ajax( 
	{  
			url : "InquiryManage",   
			type : type,  
			data : $("#formInquiry").serialize(),  
			dataType : "text",  
			complete : function(response, status)  
			{   
				onInquirySaveComplete(response.responseText, status);  
			} 
	}); 
}); 


function onInquirySaveComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			$("#alertSuccess").text("Successfully saved.");    
			$("#alertSuccess").show(); 

			$("#divInquiryGrid").html(resultSet.data);   
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		} 

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while saving.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while saving..");   
		$("#alertError").show();  
	} 

	$("#hidInquiryIDSave").val("");  
	$("#formInquiry")[0].reset(); 
} 

 
// UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
{     
	$("#hidInquiryIDSave").val($(this).closest("tr").find('#hidInquiryIDUpdate').val());     
	$("#inqName").val($(this).closest("tr").find('td:eq(0)').text());     
	$("#inqAccount").val($(this).closest("tr").find('td:eq(1)').text()); 
	$("#inqEmail").val($(this).closest("tr").find('td:eq(2)').text());
	$("#inqReason").val($(this).closest("tr").find('td:eq(3)').text());   
	$("#inqPhone").val($(this).closest("tr").find('td:eq(4)').text()); 
}); 




//REMOVE===========================================
$(document).on("click", ".btnRemove", function(event) 
{  
	$.ajax(  
	{   
		url : "InquiryManage",   
		type : "DELETE",   
		data : "inqID=" + $(this).data("inquiryid"),   
		dataType : "text",   
		complete : function(response, status)   
		{    
			onInquiryDeleteComplete(response.responseText, status);   
		}  
	}); 
}); 

function onInquiryDeleteComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			
			$("#alertSuccess").text("Successfully deleted.");    
			$("#alertSuccess").show(); 
		
			$("#divInquiryGrid").html(resultSet.data); 
			
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		}
		

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while deleting.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while deleting..");   
		$("#alertError").show();  
	}
}
 
// CLIENT-MODEL========================================================================= 
function validateInquiryForm() 
{  
	// NAME-----------------------
	if ($("#inqName").val().trim() == "")  
	{   
		return "Insert Name.";  
	}
	
	
	// ACCOUNT NO--------------------------- 
	var tmpAccNo = $("#inqAccount").val().trim();
	if (!$.isNumeric(tmpAccNo)) 
	 {
	 return "Insert Account No.";
	 }
	
	
	// EMAIL---------------------------  
	if ($("#inqEmail").val().trim() == "")  
	{   
		return "Insert Email.";  
	}

	
	// REASON------------------------------
	if ($("#inqReason").val().trim() == "")  
	{   
		return "Insert Reason.";  
	}
	
	// PHONE-------------------------------
	 var tmpPhone = $("#inqPhone").val().trim();
	if (!$.isNumeric(tmpPhone)) 
	 {
	 return "Insert Phone No.";
	 }
	
	
	return true; 
}