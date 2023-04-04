package com.jsp.medlife.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jsp.medlife.Model.Entities.Doctore;
import com.jsp.medlife.Model.Entities.Schedule;
import com.jsp.medlife.Model.Entities.User;

@Repository
public interface DoctoreRepo extends JpaRepository<Doctore, Integer>
{
	@Query(value="select * from doctore where doctore_email=?1 and doctore_licence_no=?2",nativeQuery=true)
	public Doctore findByNameandLicence(String doctore_Email ,String doctpre_licenceNO);
	
	@Query(value="select * from doctore where doctore_email=?1 ",nativeQuery=true)
	public   Doctore findByEmail(String email);

	@Query(value="select * from doctore where doctore_email=?1 ",nativeQuery=true)
	public Doctore findByEmailfp(String doctore_Email);
	

}
