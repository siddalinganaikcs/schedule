package com.jsp.medlife.servece.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jsp.medlife.Model.Entities.Doctore;
import com.jsp.medlife.Model.Entities.Marchent;
import com.jsp.medlife.Model.Entities.MarketPlace;
import com.jsp.medlife.exception.ResourceNotFoundFoure;
import com.jsp.medlife.exception.ResourceNotFountException;
import com.jsp.medlife.exception.ResourceNotFountExcepyionTwo;
import com.jsp.medlife.exception.ResourceNotfoundExceptionthree;
import com.jsp.medlife.repository.DoctoreRepo;
import com.jsp.medlife.repository.MarchantRepo;
import com.jsp.medlife.repository.MarketRepo;
import com.jsp.medlife.service.inter.MarchentServiceInter;

@Service
public class MarchantServiceImpl implements MarchentServiceInter
{
	@Autowired
	private MarchantRepo marchentrepo;
	
	@Autowired
	private JavaMailSender milsender;
   
	
	@Autowired
	private MarketRepo marketrepo;
	
	BCryptPasswordEncoder bc=new BCryptPasswordEncoder();
	
	@Override
	public Object marchentSignup(Marchent m) 
	{
		Marchent dec = marchentrepo.findByNameandLicence(m.getMarchant_Email(), m.getMarchant_licence_No());
		Random ran = new Random();
		int tg = ran.nextInt(1, 1000000);
		
	   String ju = bc.encode(m.getMarchant_Password());
      
		if (dec == null) {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("siddalinganaikcs@gmail.com");
			message.setTo(m.getMarchant_Email());
			message.setText("QCine medical Hospital");
			message.setSubject("yor one time Otp is :" + tg);

			milsender.send(message);
			m.setMarchent_Otp(tg);
			m.setMarchant_Password(ju);
			return marchentrepo.save(m);

		} else {
			try {
				throw new ResourceNotFountException("Marchent", "m", m);

			} catch (ResourceNotFountException e) {
				System.out.println(e);
				return e.getMessage();
			}
		}
		
	}

	@Override
	public Object findByLogin(String email, String pass) {
		Marchent ema = marchentrepo.findByEmail(email);

		if (ema != null) {
			if (ema.getMarchant_Password().equals(pass)) {

				SimpleMailMessage message = new SimpleMailMessage();
				message.setFrom("siddalinganaikcs@gmail.com");
				message.setTo(email);
				message.setText("QCine Medic Hospital ");
				message.setSubject("you are login Successfully");

				milsender.send(message);

				Map<Object, Object> map = new HashMap<Object, Object>();
				map.put("marchent_Id", ema.getMarchant_Id());
				map.put("marchent_Name", ema.getMarchant_Name());
				map.put("marchent_Email", ema.getMarchant_Email());
				map.put("marchent_medical", ema.getMarchant_Company_Name());

				return map;

			} else {
				try {
					throw new ResourceNotFountExcepyionTwo("Marchent", "ema", pass, email);
				} catch (ResourceNotFountExcepyionTwo re) {
					System.out.println(re);
					return re.getMessage();
				}
			}
		} else {
			try { 
				throw new ResourceNotFountExcepyionTwo("Marchent", "M", email, pass);
			} catch (ResourceNotFountExcepyionTwo tyu) {
				System.out.println(tyu);
				return tyu.getMessage();
			}
		}

	}

	@Override
	public Object findByForgetPass(String email, String password, String confirmpass) {
		Marchent ggg = marchentrepo.findByEmail(email);

		if (ggg != null) {
			if (ggg.getMarchant_Password() != password && password.equals(confirmpass)) {
				ggg.setMarchant_Password(password);

				Random ran = new Random();
				int gh = ran.nextInt(1, 1000000);

				SimpleMailMessage mail = new SimpleMailMessage();

				mail.setFrom("siddalinganaikcs@gmail.com");
				mail.setTo(email);
				mail.setSubject("QCine Medical Hospitality");
				mail.setText("You are password successfully updated  And You are One Time OTP is :" + gh);

				milsender.send(mail);
				ggg.setMarchent_Otp(gh);

				return marchentrepo.save(ggg);
			} else {
				try {
					throw new ResourceNotfoundExceptionthree("Merchent", "password", password);
				} catch (ResourceNotfoundExceptionthree e) {
					System.out.println(e);
					return e.getMessage();
				}
			}
		} else {
			try {
				throw new ResourceNotFoundFoure("Merchent", "email", email);
			} catch (ResourceNotFoundFoure r) {
				System.out.println(r);
				return r.getMessage();
			}
		}
	}

	@Override
	public Object findByMarchentEditProfile(String email) {
		Marchent lid = marchentrepo.findByEmail(email);

		if (lid != null) {
			Map<Object, Object> map = new HashMap<Object, Object>();

			map.put("Merchent_Address", lid.getMarchant_Address());
			map.put("Merchent_Company_Name", lid.getMarchant_Company_Name());
			map.put("Merchent_Name", lid.getMarchant_Name());
			map.put("Marchent_Licence", lid.getMarchant_licence_No());

			return map;
		} else {

			try {
				throw new ResourceNotFountException("Merchent", "email", email);
			} catch (ResourceNotFountException e) {
				System.out.println(e);
				return e.getMessage();
			}

		}
	}
	@Override
	public Object updateMarchentEditprofile(Marchent email) {
		Marchent lio = marchentrepo.findByEmail(email.getMarchant_Email());

		if (lio != null) {
			lio.setMarchant_Address(email.getMarchant_Address());
			lio.setMarchant_Company_Name(email.getMarchant_Company_Name());
			lio.setMarchant_Name(email.getMarchant_Name());
			lio.setMarchant_licence_No(email.getMarchant_licence_No());

			marchentrepo.save(lio);

			System.out.println(lio);
			return lio;

		} else {
			try {
				throw new ResourceNotFountException("Marchent", "email", email);
			} catch (ResourceNotFountException e) {
				System.out.println(e);
				return e.getMessage();
			}
		}
	}

	@Override
	public Object addMedicine(MarketPlace med) {

		Optional<Marchent> ok = marchentrepo.findById(med.getMarchant_Id());
		if (ok != null) {
			 
			return marketrepo.save(med);

		} else {
			try {
				throw new ResourceNotFountException("MarketPlace ", "email", med);
			} catch (ResourceNotFountException e) {
				System.out.println(e);
				return e.getMessage();
			}

		}

	}

	@Override
	public Object findByMarID(int mp) 
	{
		List<MarketPlace> jk =  marketrepo.findByMarchent_id(mp);
		
		System.out.println(marketrepo.findByMarchent_id(mp));
		System.out.println(mp);
		if(jk != null)
		{
			return jk;
		}
		else
		{
			try {
				throw new ResourceNotFountException("MarketPlace ", "email", mp);
			} catch (ResourceNotFountException e) {
				System.out.println(e);
				return e.getMessage();
			}
			
		}
	}

	@Override
	public Object DeletByMedicine_id(int id) 
	{
		if(marketrepo.existsById(id))
		{
			 marketrepo.deleteById(id);
		}
	
		return null;
	}


	
	

}
