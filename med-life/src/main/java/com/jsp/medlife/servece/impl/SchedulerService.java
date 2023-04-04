package com.jsp.medlife.servece.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.core.QuartzScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.jsp.medlife.Model.Entities.Schedule;
import com.jsp.medlife.SCHEDULER.SchedulerConfig;
import com.jsp.medlife.SCHEDULER.SchedulerData;
import com.jsp.medlife.exception.ResourceNotFountExcepyionTwo;
import com.jsp.medlife.repository.SchedulerRepo;
import com.jsp.medlife.service.inter.SchedulerServiceInter;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Service
public class SchedulerService implements SchedulerServiceInter
{

	@Autowired
	private SchedulerRepo schedlerepo;
	
	@Autowired
	private Scheduler sconfig;
	
    @Autowired
    private JavaMailSender  mailsender;
	
	
	
	
	
	
	@PostConstruct public void post()
	{
		try
		{
			sconfig.start();
		}
		catch(SchedulerException exception)
		{
			System.out.println("scheduler throws the exception"+exception);
		}
		
	}
	
	
	@PreDestroy public void destroy()
	{
		try
		{
			sconfig.shutdown();
		}
		catch(SchedulerException exception)
		{
			System.out.println("scheduler throws the exception"+exception);
		}
		
	}

	@Override
	public void scheduling(SchedulerData sche) {
		 
		 int counter = sche.getCounter();
		 int gapDuration = sche.getGapDuration();
		 String jobGroup = sche.getJobGroup();
		 String jobName = sche.getJobName();
		 String userEmail=sche.getUserEmail();
	     String userName=sche.getUserName();
		 String doctoreEmail=sche.getDoctoreEmail();
		 String doctoreName=sche.getUserName();
		 
		
		 
		 ZonedDateTime zdt= ZonedDateTime.of(sche.getStrateTime() , ZoneId.of("Asia/Kolkata" ));
		 
		 JobDataMap  datamap=new JobDataMap();
		 datamap.put("Test", "this is for testing the schedule");
		 
		 Schedule sch=new Schedule();
		 
		 sch.setCounter(sche.getCounter());
		 sch.setGapDuration(sche.getGapDuration());
		 sch.setJobGroup(sche.getJobGroup());
		 sch.setStrateTime(sche.getStrateTime());
		 sch.setJobName(sche.getJobName());
		 sch.setUserEmail(sche.getUserEmail());
		 sch.setUserName(sche.getUserName());
		 sch.setDoctoreEmail(sche.getDoctoreEmail());
		 sch.setDoctoreName(sche.getDoctoreName());
		 schedlerepo.save(sch);
		 
		
		 SimpleMailMessage message=new SimpleMailMessage();
		 message.setFrom("siddalinganaikcs@gmail.com");
		 message.setTo(sche.getDoctoreEmail());
		 message.setCc(sche.getUserEmail());
		 message.setSubject("QCine Hospital ");
		 message.setText("Appointment added  :"+sche);
		 mailsender.send(message);
	

		JobDetail detail= JobBuilder.newJob(SchedulerConfig.class)
				.withIdentity(jobName, jobGroup)
				.usingJobData(datamap).storeDurably(false).build();
		
		Trigger trigger=TriggerBuilder.newTrigger().withIdentity(jobName, jobGroup)
				.startAt(Date.from(zdt.toInstant()))
				.withSchedule(SimpleScheduleBuilder.simpleSchedule()
				.withIntervalInMinutes(gapDuration).withRepeatCount(counter)).build();
		
		try {
			 sconfig.scheduleJob(detail, trigger);
			
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			 e.printStackTrace();
		}
	}


	@Override
	public Object findByEmailschedule(String em,LocalDateTime local) 
	{
		List<Schedule> gt = schedlerepo.findByDoctore_email(em,local);
		System.out.println(gt);
		if(gt!=null)
		{
			return gt;
		}
		else
		{
			
		return null;
//			try {
//				throw new ResourceNotFountExcepyionTwo("Schedule", "ema", em, em);
//			} catch (ResourceNotFountExcepyionTwo re) {
//				System.out.println(re);
//				return re.getMessage();
//			}

			
		}
		
	}
	

}
