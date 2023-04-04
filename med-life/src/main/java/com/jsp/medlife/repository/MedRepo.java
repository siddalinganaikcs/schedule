package com.jsp.medlife.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.medlife.Model.Entities.User;


public interface MedRepo extends JpaRepository<User,Integer> 
{
	@Query(value="select * from users where user_email=?1",nativeQuery=true)
  public String findByEmail(String ema);
	
	@Query(value="select * from users where user_name=?1",nativeQuery=true)
	  public String findByUserName(String user);
	

	@Query(value="select * from users where user_email=?1 ",nativeQuery=true)
	public   User findByPassword(String email);
	
	@Query(value="select * from users where user_email=?1",nativeQuery=true)
	public User findByEmailfp(String email);

	




}
