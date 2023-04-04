package com.jsp.medlife.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jsp.medlife.Model.Entities.Marchent;
import com.jsp.medlife.Model.Entities.MarketPlace;

@Repository
public interface MarketRepo extends JpaRepository<MarketPlace, Integer>
{

	
	@Query(value="select * from market_place where marchant_id=?1 ",nativeQuery=true)
	public List< MarketPlace> findByMarchent_id(int i);

	
	
}
