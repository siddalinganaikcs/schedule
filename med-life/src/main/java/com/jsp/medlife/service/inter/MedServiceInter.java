package com.jsp.medlife.service.inter;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.jsp.medlife.Model.Entities.ImageStore;
import com.jsp.medlife.Model.Entities.User;

public interface MedServiceInter 
{
	//user
	public Object saveMed(User u); 
	public Object findByLogin(String email,String pass);
	public Object findByForgetPass(String email,String password,String confirmpass);
	public Object findByuserEditProfile(String email);
	public Object  updateUserEditprofile(User email);
	
	
	//image 
	public Object saveCompressImage(MultipartFile file) throws IOException;
	
	public  List<ImageStore> findByCompressedImage() throws IOException;
	
	
//	public Object sendEmail(String toEmail,String subject,String body);


}
