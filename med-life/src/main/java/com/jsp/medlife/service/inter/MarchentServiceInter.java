package com.jsp.medlife.service.inter;

import java.util.List;

import com.jsp.medlife.Model.Entities.Doctore;
import com.jsp.medlife.Model.Entities.Marchent;
import com.jsp.medlife.Model.Entities.MarketPlace;

public interface MarchentServiceInter 
{
	public Object marchentSignup(Marchent d);
	public Object findByLogin(String email,String pass);
	public Object findByForgetPass(String email,String password,String confirmpass);

	public Object findByMarchentEditProfile(String email);
	public Object  updateMarchentEditprofile(Marchent email);
	
	
	public Object addMedicine(MarketPlace med);

	Object findByMarID(int mp);

	
	public Object DeletByMedicine_id(int id);
	
	
}
