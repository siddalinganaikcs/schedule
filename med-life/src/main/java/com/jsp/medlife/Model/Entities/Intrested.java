package com.jsp.medlife.Model.Entities;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Transactional
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})//to set the property/value in mapping class in the json formate
public class Intrested                                         // the above line will requred compelsory
{
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int intId;
    private  List<String> diseces;
    
    
    
}
