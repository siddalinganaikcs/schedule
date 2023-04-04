package com.jsp.medlife.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.medlife.Model.Entities.Schedule;
import com.jsp.medlife.Model.Entities.User;
import com.jsp.medlife.SCHEDULER.SchedulerConfig;
import com.jsp.medlife.SCHEDULER.SchedulerData;
import com.jsp.medlife.service.inter.SchedulerServiceInter;
@Controller
@RestController
public class SchedulerController 
{
	
	@Autowired
	private SchedulerServiceInter scheduleinter;
	
	
	
	 @PostMapping("/schedule")
	   ResponseEntity<Object> SchedulerController(@RequestBody SchedulerData ldt)
	{
		 System.out.println(ldt+" controller");
        scheduleinter.scheduling(ldt);
			return ResponseEntity.status(HttpStatus.OK).body(ldt);		
	}
	 
	 @GetMapping("/appointment/start/dateTime")
	   ResponseEntity<Object> appointmentdoctore(@RequestParam("start") String str, @RequestParam(name = "dateTime") LocalDateTime dateTime)
	   {
		  Object i = scheduleinter.findByEmailschedule(str,dateTime);
		  System.out.println(str+dateTime);
		  if(i !=null)
		  {
			  return ResponseEntity.status(200).body(i);
	  	}
	  	      return ResponseEntity.status(400).body(scheduleinter.findByEmailschedule(str,dateTime));

	   }
    
    


}
