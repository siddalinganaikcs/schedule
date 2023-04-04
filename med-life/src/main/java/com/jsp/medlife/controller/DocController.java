package com.jsp.medlife.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.medlife.Model.Entities.Doctore;
import com.jsp.medlife.Model.Entities.User;
import com.jsp.medlife.servece.impl.DoctoreServiceImpl;

@Controller
@RestController
public class DocController 
{
	@Autowired
	private DoctoreServiceImpl docserviceimpl;
	
	@PostMapping("/docsignup")
    ResponseEntity<Object> DocSignup(@RequestBody Doctore doc)
	{
		Object dir = docserviceimpl.doctoreSignup(doc);
		
	   if(dir instanceof Doctore)
	   {
		   return ResponseEntity.status(200).body("Successfully login");
	   }
	   return ResponseEntity.status(400).body(dir);
	}
	
	
	 @GetMapping("/doclogin")
  	 ResponseEntity<Object>  login(@RequestHeader String password ,@RequestHeader String email)
  	{
         Object	oo= docserviceimpl.findByLogin(email, password);
    	
  		if(!oo.toString().equals("User:INVALED EMAIL OR PASSWORD"))	
  		{
  			return ResponseEntity.status(200).body(oo);
  		}
           return ResponseEntity.status(400).body(docserviceimpl.findByLogin(email,password));		
  		
  	}
	 
	 
	  @PutMapping("/docforgetpass")
	 	 ResponseEntity<Object>  frgetpass(@RequestHeader String password ,@RequestHeader String email,@RequestHeader String confirmPass)
	 	{
	    	Object ll = docserviceimpl.findByForgetPass(email, password, confirmPass);
	    	if(ll instanceof User)
	    	{
	    		return ResponseEntity.status(200).body(" PASSWORD UPDATED SUCCESSFULL");
	    	}
	    	return ResponseEntity.status(400).body(docserviceimpl.findByForgetPass(email, password, confirmPass));
	 	}
	    
	  
	  @GetMapping("/doceditprofile")
	   ResponseEntity<Object> editProfile(@RequestHeader String email)
	   {
		  Object ki = docserviceimpl.findByDoctoreEditProfile(email);
		  System.out.println(email);
		  
		  if(!(ki.toString().equals("UserIS EXIST PLS TRY WITH DIFFRENT EMAIL AND USERNAME")))
		  {
			  return ResponseEntity.status(200).body(ki);
	  	}
	  	return ResponseEntity.status(400).body(docserviceimpl.findByDoctoreEditProfile(email));
	                                  
	   }
	   
	   @PutMapping("/docupdateeditprofile")
	   ResponseEntity<Object> updateEditProfile(@RequestBody Doctore u)
	   {
		  Object i = docserviceimpl.updateDoctoreEditprofile(u);
		  if(i instanceof User)
		  {
			  return ResponseEntity.status(200).body(i);
	  	}
	  	return ResponseEntity.status(400).body(docserviceimpl.updateDoctoreEditprofile(u));

	   }
	   
	  
}
