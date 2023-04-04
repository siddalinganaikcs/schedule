 package com.jsp.medlife.Model.Entities;



import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Transactional
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Users")

public class User {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int user_id;
	
	private String userName;
    private String userEmail;
	private String userPassword;
	private int userAge;
	private String userGender;
	private long userPhoneNo;
	private int user_Otp;
	
	@OneToOne( fetch = FetchType.EAGER , cascade =CascadeType.ALL )
	@JoinColumn(name="add_Id")
	private Address address;
	
	


}
