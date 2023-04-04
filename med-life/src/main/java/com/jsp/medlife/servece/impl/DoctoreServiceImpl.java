package com.jsp.medlife.servece.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.stereotype.Service;

import com.jsp.medlife.Model.Entities.Doctore;
import com.jsp.medlife.Model.Entities.User;
import com.jsp.medlife.exception.ResourceNotFoundFoure;
import com.jsp.medlife.exception.ResourceNotFountException;
import com.jsp.medlife.exception.ResourceNotFountExcepyionTwo;
import com.jsp.medlife.exception.ResourceNotfoundExceptionthree;
import com.jsp.medlife.repository.DoctoreRepo;
import com.jsp.medlife.service.inter.DoctoreServiceInter;

@Service
public class DoctoreServiceImpl implements DoctoreServiceInter
{
	@Autowired
	private DoctoreRepo docrepo;
	
	@Autowired
	private JavaMailSender milsender;
	
	

	@Override
	public Object doctoreSignup(Doctore d) {
		Doctore dec = docrepo.findByNameandLicence(d.getDoctore_Email(), d.getDoctore_LicenceNo());
		Random ran = new Random();
		int tg = ran.nextInt(1, 1000000);

		if (dec == null) {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("siddalinganaikcs@gmail.com");
			message.setTo(d.getDoctore_Email());
			message.setText("QCine medical Hospital");
			message.setSubject("yor one time Otp is :" + tg);

			milsender.send(message);
			d.setDoctore_Otp(tg);
			return docrepo.save(d);

		} else {
			try {
				throw new ResourceNotFountException("Doctore", "d", d);

			} catch (ResourceNotFountException e) {
				System.out.println(e);
				return e.getMessage();
			}
		}

	}
	@Override	
	public Object findByLogin(String email, String password) {
		  Doctore ema = docrepo.findByEmail(email);
			 
			if (ema != null) {
				if (ema.getDoctore_Password().equals(password)) {
					
					
					SimpleMailMessage message= new SimpleMailMessage();
					message.setFrom("siddalinganaikcs@gmail.com");
					message.setTo(email);
					message.setText("QCine Medic Hospital ");
					message.setSubject("you are login Successfully");
					
					milsender.send(message);
					
					Map<Object,Object> map=new HashMap<Object,Object>();
					map.put("Doctore_Id", ema.getDoctore_Id());
					map.put("Doctore_Name", ema.getDoctore_Name());
					map.put("Doctore_Email", ema.getDoctore_Email());
					map.put("Doctore_Spceliazetaion", ema.getDoctore_Specialized());
					
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
	@Override
	public Object findByForgetPass(String email, String password, String confirmpass) {
		Doctore ggg = docrepo.findByEmail(email);

		if (ggg != null) {
			if (ggg.getDoctore_Password() != password && password.equals(confirmpass)) {
				ggg.setDoctore_Password(password);
                 
				Random ran= new Random();
				int gh = ran.nextInt(1,1000000);
				
				SimpleMailMessage mail=new  SimpleMailMessage();
				
				mail.setFrom("siddalinganaikcs@gmail.com");
				mail.setTo(email);
				mail.setSubject("QCine Medical Hospitality");
				mail.setText("You are password successfully updated  And You are One Time OTP is :"+gh);
				
				milsender.send(mail);
			   ggg.setDoctore_Otp(gh);
				
						return docrepo.save(ggg);
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
	
	@Override
	public Object findByDoctoreEditProfile(String email) 
	{

		Doctore lid =docrepo.findByEmail(email);

		if(lid!= null)
		  {
				Map<Object,Object> map=new HashMap<Object,Object>();
			
				map.put("Doctore_Name", lid.getDoctore_Name());
				map.put("Doctore_Address", lid.getDoctore_CurrentAddress());
				map.put("Doctore_Experience", lid.getDoctore_Experience());
				map.put("Doctore_PhoneNo", lid.getDoctore_phNo());
				map.put("Doctore_education", lid.getDovtore_Education());
				map.put("Doctore_Department", lid.getDoctore_Department());
				map.put("Doctore_Worklocation", lid.getDoctore_WorkLocation());
			
			
				return map;
		}
		else
		{
			
	       try {
					throw new ResourceNotFountException("Doctore", "email",email);
			   }
	       catch (ResourceNotFountException e)
	         {
					System.out.println(e);
					return e.getMessage();
			}
				
		}
		
	
	}
	@Override
	public Object updateDoctoreEditprofile(Doctore email) 
	{
		Doctore lio =docrepo.findByEmailfp(email.getDoctore_Email());
		
		 if(lio !=null)
		{
			 lio.setDoctore_CurrentAddress(email.getDoctore_CurrentAddress());
			 lio.setDoctore_Department(email.getDoctore_Department());
			 lio.setDoctore_Experience(email.getDoctore_Experience());
			 lio.setDoctore_Name(email.getDoctore_Name());
			 lio.setDoctore_phNo(email.getDoctore_phNo());
			 lio.setDoctore_Specialized(email.getDoctore_Specialized());
			 lio.setDovtore_Education(email.getDovtore_Education());
			  
			 docrepo.save(lio);
			  
			   System.out.println(lio);
			 return lio ;
			 
		 }
		 else
		 {
			   try {
					throw new ResourceNotFountException("Doctore", "email", email);
			   }
	       catch (ResourceNotFountException e)
	         {
					System.out.println(e);
					return e.getMessage();
			}
		 }
		
	}
	

		
	
}
