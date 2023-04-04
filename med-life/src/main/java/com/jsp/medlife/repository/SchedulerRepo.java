package com.jsp.medlife.repository;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.jsp.medlife.Model.Entities.Schedule;

@Repository
public interface SchedulerRepo extends CrudRepository<Schedule, Integer>
{

	@Query(value="select * from schedule where doctore_email=?1 and strate_time=?2 ",nativeQuery=true)
	public List<Schedule> findByDoctore_email(String email,LocalDateTime lovaldt);

	
	
}
