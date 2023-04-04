package com.jsp.medlife.SCHEDULER;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jsp.medlife.Model.Entities.Schedule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchedulerData 
{
	 
 @JsonFormat(pattern="yyyy-MM-dd HH-mm-ss")
 private LocalDateTime StrateTime;
 
 private String jobName;
 private String jobGroup;
 private int counter;
 private int gapDuration;
 
	private String userEmail;
	private String userName;

	private String doctoreEmail;
	private String doctoreName;

}
