package com.jsp.medlife.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.medlife.Model.Entities.Doctore;
import com.jsp.medlife.Model.Entities.Marchent;
import com.jsp.medlife.Model.Entities.MarketPlace;
import com.jsp.medlife.Model.Entities.User;
import com.jsp.medlife.servece.impl.DoctoreServiceImpl;
import com.jsp.medlife.service.inter.MarchentServiceInter;

@Controller
@RestController
public class MarchantController 
{
	@Autowired
	private MarchentServiceInter marservicei;
	
	@PostMapping("/marsignup")
    ResponseEntity<Object> marchentSignup(@RequestBody Marchent doc)
	{
		Object dir = marservicei.marchentSignup(doc);
		
	   if(dir instanceof Marchent)
	   {
		   return ResponseEntity.status(200).body("Successfully login");
	   }
	   return ResponseEntity.status(400).body(dir);
	}
	
	
	 @GetMapping("/marlogin")
  	 ResponseEntity<Object>  login(@RequestHeader String password ,@RequestHeader String email)
  	{
         Object	oo= marservicei.findByLogin(email, password);
    	
  		if(!oo.toString().equals("User:INVALED EMAIL OR PASSWORD"))	
  		{
  			return ResponseEntity.status(200).body(oo);
  		}
           return ResponseEntity.status(400).body(marservicei.findByLogin(email,password));		
  		
  	}
	 
	 
	  @PutMapping("/marforgetpass")
	 	 ResponseEntity<Object>  frgetpass(@RequestHeader String password ,@RequestHeader String email,@RequestHeader String confirmPass)
	 	{
	    	Object ll = marservicei.findByForgetPass(email, password, confirmPass);
	    	if(ll instanceof Marchent)
	    	{
	    		return ResponseEntity.status(200).body(" PASSWORD UPDATED SUCCESSFULL");
	    	}
	    	return ResponseEntity.status(400).body(marservicei.findByForgetPass(email, password, confirmPass));
	 	}
	  
	  
	  @GetMapping("/marchenteditprofile")
	   ResponseEntity<Object> editProfile(@RequestHeader String email)
	   {
		  Object ki = marservicei.findByMarchentEditProfile(email);
		  System.out.println(email);
		  
		  if(!(ki.toString().equals("UserIS EXIST PLS TRY WITH DIFFRENT EMAIL AND USERNAME")))
		  {
			  return ResponseEntity.status(200).body(ki);
	   	}
	  	return ResponseEntity.status(400).body(marservicei.findByMarchentEditProfile(email));
	                                  
	   }
	   
	  
	   ResponseEntity<Object> updateEditProfile(@RequestBody Marchent u)
	   {
		  Object i = marservicei.updateMarchentEditprofile(u);
		  if(i instanceof Marchent)
		  {
			  return ResponseEntity.status(200).body(i);
	  	}
	  	return ResponseEntity.status(400).body(marservicei.updateMarchentEditprofile(u));

	   }
	   
	   @PostMapping("/addmed")
	    ResponseEntity<Object> marchentadd(@RequestBody MarketPlace mar)
		{
			Object dir = marservicei.addMedicine(mar);
			
		   if(dir instanceof MarketPlace)
		   {
			   return ResponseEntity.status(200).body("Successfully login");
		   }
		   return ResponseEntity.status(400).body(dir);
		}
	   
	   @GetMapping("/search/{mar}")
	    ResponseEntity<Object> marchentserch1(@PathVariable ("mar") int id)
		{
			Object r = marservicei.findByMarID(id);
			
		   if(!r.equals("MarketPlace IS EXIST PLS TRY WITH DIFFRENT EMAIL AND NAME"))
		   {
			   return ResponseEntity.status(200).body(r);
		   }
		   return ResponseEntity.status(400).body(r);
		}
	   
	   @DeleteMapping("/delete/{de}")
	    ResponseEntity<Object> marchentserch(@PathVariable ("de")  Integer id)
		{
			Object dir = marservicei.DeletByMedicine_id(id);
			
		   if(dir == null)
		   {
			   return ResponseEntity.status(200).body("Successfully DELETE");
		   }
		   return ResponseEntity.status(400).body(dir);
		}
	   
	   
	    

}
