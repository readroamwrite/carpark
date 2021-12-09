package net.codej.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.codej.WorkerserviceLog;

public interface WorkerserviceLogRepository extends JpaRepository<WorkerserviceLog,Integer> {

	List<WorkerserviceLog> findByWorkerID(int workerID);

	@Query("SELECT DISTINCT a.service FROM WorkerserviceLog a")
	List<String> findDistinctService();



}
