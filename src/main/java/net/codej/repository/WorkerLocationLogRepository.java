package net.codej.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.codej.WorkerLocationLog;

public interface WorkerLocationLogRepository extends JpaRepository<WorkerLocationLog,Integer> {

	
	List<WorkerLocationLog> findByLocation(String location);

	@Query("SELECT DISTINCT a.location FROM WorkerLocationLog a")
	List<String> findDistinctLocation();

	List<WorkerLocationLog> findByWorkerID(int wid);
}
