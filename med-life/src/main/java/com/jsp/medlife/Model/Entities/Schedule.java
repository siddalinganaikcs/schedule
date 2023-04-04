package com.jsp.medlife.Model.Entities;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
 @Transactional
public class Schedule 
{
	 @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int schedule_Id;
	 
  @JsonFormat(pattern="yyyy-MM-dd HH-mm-ss")
  private LocalDateTime StrateTime;
  
  private String userEmail;
  private String userName;
  
  private String doctoreEmail;
  private String doctoreName;
  
  private String jobName;
  private String jobGroup;
  private int counter;
  private int gapDuration;
}
