 package com.jsp.medlife.Model.Entities;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Transactional
@AllArgsConstructor
@NoArgsConstructor 
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="Address")
public class Address 
{
	

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="add_Id")
	private int addressId;
	private String city;
	private String street;
	private  String houseNo;
	private String state;
	private String country;
	private int zipCode; 

	@OneToOne(fetch = FetchType.EAGER     ,cascade =CascadeType.ALL)
	@JoinColumn(name="intId")
	private Intrested intrested;
}
