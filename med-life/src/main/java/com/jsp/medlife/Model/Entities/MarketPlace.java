package com.jsp.medlife.Model.Entities;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Transactional
@Builder
public class MarketPlace 
{
	    
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
		private int    medecine_Id;
	   
//	    @OneToOne(cascade = CascadeType.ALL)
	    private Integer marchant_Id;
	    
		private String medecine_Name;
		private String medecine_Price;
		private String medecine_Company_Name;
		private String medecine_Discription;
		private String medecine_Expairy_Date;

}

