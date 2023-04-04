package com.jsp.medlife.service.inter;





import java.time.LocalDateTime;
import java.util.List;

import com.jsp.medlife.Model.Entities.Schedule;
import com.jsp.medlife.SCHEDULER.SchedulerData;



public interface SchedulerServiceInter 
{

	public void scheduling(SchedulerData sche);
	
	public  Object findByEmailschedule(String yu,LocalDateTime local);
	


}
