package com.jsp.medlife.Model.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Transactional
public class Doctore 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int doctore_Id;
	private String doctore_Name;
	private String doctore_Email;
	private String doctore_Password;
	private String doctore_phNo;
	private String doctore_LicenceNo;
	private String dovtore_Education;
	private String doctore_Department;
	private String doctore_Specialized;
	private String doctore_Experience;
	private String doctore_CurrentAddress;
	private String doctore_WorkLocation;
	private int doctore_Otp;

}
