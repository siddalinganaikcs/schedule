 package com.jsp.medlife.controller;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.medlife.Model.Entities.ImageStore;
import com.jsp.medlife.Model.Entities.User;
import com.jsp.medlife.servece.impl.MedServiceImpl;

import jakarta.websocket.server.PathParam;

@Controller
@RestController
public class medController 
{
	
    @Autowired
    private MedServiceImpl medser;

//======  user -------------->>>>
    
    
    @PostMapping("/signup")
	 ResponseEntity<Object>  saveUser(@RequestBody User ue)
	{
       Object	in= medser.saveMed(ue);
				
		if(in instanceof User)	
		{
			return ResponseEntity.status(200).body("SIGN UP SUCCESSFULL DONE");
		}
		
			return ResponseEntity.status(400).body(medser.saveMed(ue));		
	}
    
    
    
    @GetMapping("/login")
  	 ResponseEntity<Object>  login(@RequestHeader String password ,@RequestHeader String email)
  	{
         Object	oo= medser.findByLogin(email, password);
         System.out.println(email+" "+password);
  			System.out.println(oo.toString());	
  		if(!oo.toString().equals("User:INVALED EMAIL OR PASSWORD"))	
  		{
  			return ResponseEntity.status(200).body(oo);
  		}
  		
  		else
  		{
  			return ResponseEntity.status(400).body(medser.findByLogin(email,password));		
  		}
  	}
	
    @PutMapping("/forgetpass")
 	 ResponseEntity<Object>  frgetpass(@RequestHeader String password ,@RequestHeader String email,@RequestHeader String confirmPass)
 	{
    	Object ll = medser.findByForgetPass(email, password, confirmPass);
    	if(ll instanceof User)
    	{
    		return ResponseEntity.status(200).body(" PASSWORD UPDATED SUCCESSFULL");
    	}
    	return ResponseEntity.status(400).body(medser.findByForgetPass(email, password, confirmPass));
 	}
    
 //----------edit profile 
    
   @GetMapping("/editprofile")
   ResponseEntity<Object> editProfile(@RequestHeader String email)
   {
	  Object ki = medser.findByuserEditProfile(email);
	  System.out.println(email);
	  
	  if(!(ki.toString().equals("UserIS EXIST PLS TRY WITH DIFFRENT EMAIL AND USERNAME")))
	  {
		  return ResponseEntity.status(200).body(ki);
  	}
  	return ResponseEntity.status(400).body(medser.findByuserEditProfile(email));
                                  
   }
   
   @PutMapping("/updateeditprofile")
   ResponseEntity<Object> updateEditProfile(@RequestBody User u)
   {
	  Object i = medser.updateUserEditprofile(u);
	  if(i instanceof User)
	  {
		  return ResponseEntity.status(200).body(i);
  	}
  	return ResponseEntity.status(400).body(medser.updateUserEditprofile(u));

   }
    
//===============  image  ----------------->>>>>  
 
    @PostMapping(value="/image", consumes= {MediaType.MULTIPART_FORM_DATA_VALUE})
    ResponseEntity<?> saveCompresedImage(@RequestParam("image") MultipartFile file) throws Exception
    {
    	 Object compimage = medser.saveCompressImage(file);
    	 return ResponseEntity.status(HttpStatus.OK).body(compimage);
    }
    
  
    
    @GetMapping("/allimage")
    ResponseEntity<List<ImageStore>> findCompressImage() throws IOException
    {
    	 List<ImageStore> im = medser.findByCompressedImage();
    	if(im.size()>0)
    	{
    		System.out.println("size----->.    "+im.size());
    		return ResponseEntity.status(200).body(im);
    	}
    	else
    	{
    		return ResponseEntity.status(400).body(im);
    	}
//    	return ResponseEntity.status(HttpStatus.OK).body(im);
    }
    
  //------------------ ******* Arun *** ----------------
    
//    @PostMapping(value="/arunimage", consumes= {MediaType.MULTIPART_FORM_DATA_VALUE})
//    public ResponseEntity<ImageStore> saveimage (@RequestParam("image")  MultipartFile file) throws IOException
//	{
//    	ImageStore resimg= medser.saveimage(file);
//    	
//		
//		if(resimg!= null)
//		{
//			return ResponseEntity.status(200).body(resimg);
//		}
//		else
//		{
//			return ResponseEntity.status(400).body(resimg);
//		}
//	}

    
//------------------ ******* mail *** ----------------
    
//    @EventListener(ApplicationReadyEvent.class)
//    
//    @GetMapping("/sendemail")
//    ResponseEntity<Object> sendEmail(@RequestHeader String email)
//    {
// 	  Object ki = medser.findByuserEditProfile(email);
// 	  System.out.println(email);
// 	  
// 	 Random ran =new Random();
//        int Otp = ran.nextInt(1,1000000);
// 	 
// 	  if(!(ki.toString().equals("UserIS EXIST PLS TRY WITH DIFFRENT EMAIL AND USERNAME")))
// 	  {
// 		 
// 		   medser.sendEmail(email,"QCine hospitl", "Your one time password(OTP): "+Otp);
// 		   
// 		   return ResponseEntity.status(200).body("email sent successfully");
//      }
//         	return ResponseEntity.status(400).body(medser.findByuserEditProfile(email)); 
//
//    }
    
    
}
