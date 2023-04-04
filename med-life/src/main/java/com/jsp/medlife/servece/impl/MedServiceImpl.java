package com.jsp.medlife.servece.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.medlife.Model.Entities.ImageStore;
import com.jsp.medlife.Model.Entities.Intrested;
import com.jsp.medlife.Model.Entities.User;

import com.jsp.medlife.exception.ResourceNotFoundFoure;
import com.jsp.medlife.exception.ResourceNotFountException;
import com.jsp.medlife.exception.ResourceNotFountExcepyionTwo;
import com.jsp.medlife.exception.ResourceNotfoundExceptionthree;
import com.jsp.medlife.image.compress.ImageCompress;
import com.jsp.medlife.repository.ImageRepo;
import com.jsp.medlife.repository.MedRepo;
import com.jsp.medlife.service.inter.MedServiceInter;

import ch.qos.logback.core.net.SyslogOutputStream;

@Service
public class MedServiceImpl implements MedServiceInter {
	
	
	@Autowired
	private MedRepo medrepo;
	
	@Autowired
	private JavaMailSender mailsender;
	

//----------------->>>> SIGNUP	<<<-----------------
//-----------------<<<<  User    >>>-----------------
	@Override
	public Object saveMed(User u) {
		String em = medrepo.findByEmail(u.getUserEmail());
		String us = medrepo.findByUserName(u.getUserName());
		Random ran = new Random();
		int Otp = ran.nextInt(1, 1000000);

		if (us == null) {
			if (em == null) {
				SimpleMailMessage message = new SimpleMailMessage();

				message.setFrom("siddalinganaikcs@gmail.com");
				message.setTo(u.getUserEmail());
				message.setSubject(" QCine Medic Hospital");
				message.setText("your one time password(OTP) IS :" + Otp);

				mailsender.send(message);

				String pass = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%&=])(?=\\S+$).{8,20}$";// regular expretion
				if (u.getUserPassword().matches(pass)) {

					   u.setUser_Otp(Otp);

						return medrepo.save(u);
					} else {
						try {
							throw new ResourceNotFountException("User", "u", u);
						} catch (ResourceNotFountException e) {
							System.out.println(e);
							return e.getMessage();
						}
					}
				} else {
					return "password not strong ";
				}
			} else {
				try {
					throw new ResourceNotFountException("User", "u", u);
				} catch (ResourceNotFountException e) {
					System.out.println(e);
					return e.getMessage();
				}
			}
		
	}
	
//----------------->>>> LOGIN	<<<-----------------
//-----------------<<<< User    >>>-----------------
	@Override
	public Object findByLogin(String email, String password) {
		  User ema = medrepo.findByPassword(email);
			System.out.println(ema + "=================");
			 
			if (ema != null) {
				if (ema.getUserPassword().equals(password)) {
					
					
					SimpleMailMessage message= new SimpleMailMessage();
					message.setFrom("siddalinganaikcs@gmail.com");
					message.setTo(email);
					message.setText("QCine Medic Hospital ");
					message.setSubject("you are login Successfully");
					
					mailsender.send(message);
					
					Map<Object,Object> map=new HashMap<Object,Object>();
					map.put("User_Id", ema.getUser_id());
					map.put("User_Name", ema.getUserName());
					map.put("User_Email", ema.getUserEmail());
					map.put("User_Intrested", ema.getAddress().getIntrested().getDiseces());
					
					return map;
					
				} else {
					try {
						throw new ResourceNotFountExcepyionTwo("User", "ema", password, email);
					} catch (ResourceNotFountExcepyionTwo re) {
						System.out.println(re);
						return re.getMessage();
					}
				}
			} else {
				try {
					throw new ResourceNotFountExcepyionTwo("User", "u", email, password);
				} catch (ResourceNotFountExcepyionTwo tyu) {
					System.out.println(tyu);
					return tyu.getMessage();
				}
			}
		
	}

//----------------->>>> Forget Password	<<<-----------------
//-----------------<<<<      User       >>>-----------------
	@Override
	public Object findByForgetPass(String email, String password, String confirmpass) 
	{
		User ggg = medrepo.findByEmailfp(email);

		if (ggg != null) {
			if (ggg.getUserPassword() != password && password.equals(confirmpass)) {
				ggg.setUserPassword(password);
                 
				Random ran= new Random();
				int gh = ran.nextInt(1,1000000);
				
				SimpleMailMessage mail=new  SimpleMailMessage();
				
				mail.setFrom("siddalinganaikcs@gmail.com");
				mail.setTo(email);
				mail.setSubject("QCine Medical Hospitality");
				mail.setText("You are password successfully updated  And You are One Time OTP is :"+gh);
				
				mailsender.send(mail);
			   ggg.setUser_Otp(gh);
				
						return medrepo.save(ggg);
			} else {
				try {
					throw new ResourceNotfoundExceptionthree("user", "password", password);
				} catch (ResourceNotfoundExceptionthree e) {
					System.out.println(e);
					return e.getMessage();
				}
			}
		} else {
			try {
				throw new ResourceNotFoundFoure("user", "email", email);
			} catch (ResourceNotFoundFoure r) {
				System.out.println(r);
				return r.getMessage();
			}
		}

	}

 
//=========>>>  Editprofile
	@Override
	public Object findByuserEditProfile(String email)
	{ 
		
		User lid =medrepo.findByPassword(email);

		if(lid!= null)
		  {
				Map<Object,Object> map=new HashMap<Object,Object>();
			
				map.put("User_Name", lid.getUserName());
				map.put("User_Gender", lid.getUserGender());
				map.put("User_Age", lid.getUserAge());
				map.put("User_PhoneNo", lid.getUserPhoneNo());
				map.put("User_City", lid.getAddress().getCity());
				map.put("User_Country", lid.getAddress().getCountry());
				map.put("User_HouseNo", lid.getAddress().getHouseNo());
				map.put("User_state", lid.getAddress().getState());
				map.put("User_Street", lid.getAddress().getStreet());
				map.put("User_ZipCode", lid.getAddress().getZipCode());
				map.put("User_Intrested", lid.getAddress().getIntrested().getDiseces());
				return map;
		}
		else
		{
			
	       try {
					throw new ResourceNotFountException("User", "email",email);
			   }
	       catch (ResourceNotFountException e)
	         {
					System.out.println(e);
					return e.getMessage();
			}
				
		}
		
	}
  
	@Override
	public Object updateUserEditprofile(User email) 
	{
		      
		       
		User lio =medrepo.findByEmailfp(email.getUserEmail());
		
		 if(lio !=null)
		{
			 lio.setUserName(email.getUserName());
			 lio.setUserPhoneNo(email.getUserPhoneNo());
			 lio.setUserAge(email.getUserAge());
			 lio.setUserGender(email.getUserGender());
			 lio.getAddress().setCity(email.getAddress().getCity());
			 lio.getAddress().setCountry(email.getAddress().getCountry());
			 lio.getAddress().setHouseNo(email.getAddress().getHouseNo());
			 lio.getAddress().setState(email.getAddress().getState());
			 lio.getAddress().setZipCode(email.getAddress().getZipCode());
			 lio.getAddress().setStreet(email.getAddress().getStreet());
			 lio.getAddress().getIntrested().setDiseces(email.getAddress().getIntrested().getDiseces());
			  
			   medrepo.save(lio);
			  
			   System.out.println(lio);
			 return lio ;
			 
		 }
		 else
		 {
			   try {
					throw new ResourceNotFountException("User", "email", email);
			   }
	       catch (ResourceNotFountException e)
	         {
					System.out.println(e);
					return e.getMessage();
			}
		 }
		
	}

   
   
//--------->>> save  <<<-----------  
//---------<<< image >>>-----------
   
   
   @Autowired
   private ImageRepo imagerepo;
   
   public Object saveCompressImage(MultipartFile file) throws IOException
   
   {
   	     ImageStore imagStore = imagerepo.save(ImageStore.builder()
						   			   .image_Name(file.getOriginalFilename())
						   			   .image_Type(file.getContentType())
					   			   .image_data((file.getBytes())).build());
   	     

						   			if(imagStore != null)
								   		{
								   			return "file uploaded successfully"+file.getName();
								   		}
								   		return " Notnull";
   	   
   }

   @Override
    public List<ImageStore> findByCompressedImage() throws IOException 
   {
	   
	        List<ImageStore> fiall = imagerepo.findAll();
              System.out.println(fiall.size());
	            return fiall;
	
	    
 }

   
//------------->>>>  email   <<<<-------------
//-------------<<<<          >>>>------------- 



//	@Override
//	public Object sendEmail(String toEmail, String subject, String body) {
//		String yt = medrepo.findByEmail(toEmail);
//
//		if (yt != null) {
//
//			SimpleMailMessage message = new SimpleMailMessage();
//			message.setFrom("siddalinganaikcs@gmail.com");
//			message.setTo(toEmail);
//			message.setSubject(subject);
//			message.setText(body);
//
//			mailsender.send(message);
//			return yt;
//
//		}
//
//		else {
//
//			try {
//				throw new ResourceNotFountException("User", "email", yt);
//			} catch (ResourceNotFountException e) {
//				System.out.println(e);
//				return e.getMessage();
//			}
//
//		}
//
//	}



}		

	
	 	
	
