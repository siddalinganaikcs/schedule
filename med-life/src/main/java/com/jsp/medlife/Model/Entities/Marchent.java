package com.jsp.medlife.Model.Entities;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
@Builder
public class Marchent 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int    marchant_Id;
	private String marchant_Name;
	private String marchant_Email;
	private String marchant_Password;
	private String marchant_licence_No;
	private String marchant_Address;
	private String marchant_Company_Name;
	private int marchent_Otp;

}
