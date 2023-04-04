package com.jsp.medlife.SCHEDULER;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class SchedulerConfig extends QuartzJobBean
{

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		JobDataMap mjdm = context.getMergedJobDataMap();
		for(String Key:mjdm.getKeys())
		{
			System.out.println("from schedule job"+mjdm.get(Key));
		}
		
	}

}
