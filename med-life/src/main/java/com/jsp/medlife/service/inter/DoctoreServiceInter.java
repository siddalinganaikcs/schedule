package com.jsp.medlife.service.inter;

import org.springframework.stereotype.Service;

import com.jsp.medlife.Model.Entities.Doctore;
import com.jsp.medlife.Model.Entities.User;


public interface DoctoreServiceInter 
{
	public Object doctoreSignup(Doctore d);
	public Object findByLogin(String email,String pass);
	public Object findByForgetPass(String email,String password,String confirmpass);
	
	public Object findByDoctoreEditProfile(String email);
	public Object  updateDoctoreEditprofile(Doctore email);
	
	
}
