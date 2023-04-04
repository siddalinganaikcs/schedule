package com.jsp.medlife.Model.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder    //this act like a setter
public class ImageStore 
{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  private int image_Id;
  private String image_Type;
  private String image_Name;
  
  @Lob//lob is larger object//? because image url can large 
  @Column(nullable=false, columnDefinition="blob")//blob -->binary lorge object-->if we store any image ,video,audio,then we 
  private byte[]  image_data;                    // store in database as a binarry to database
  



  
  
  
  
}
