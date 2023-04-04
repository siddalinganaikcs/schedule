package com.jsp.medlife.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jsp.medlife.Model.Entities.Doctore;
import com.jsp.medlife.Model.Entities.Marchent;
import com.jsp.medlife.Model.Entities.MarketPlace;

@Repository
public interface MarchantRepo extends JpaRepository<Marchent, Integer>
{
	@Query(value="select * from marchent where marchant_email=?1 and marchant_licence_no=?2",nativeQuery=true)
	public Marchent findByNameandLicence(String doctore_Email ,String doctpre_licenceNO);
	
	@Query(value="select * from marchent where marchant_email=?1 ",nativeQuery=true)
	public   Marchent findByEmail(String email);

	@Query(value="select * from marchent where marchant_email=?1 ",nativeQuery=true)
	public Marchent findByEmail(Marchent email);
	
	
	


	
	

}
