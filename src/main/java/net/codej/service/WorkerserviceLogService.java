package net.codej.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.codej.WorkerLocationLog;
import net.codej.WorkerserviceLog;
import net.codej.repository.WorkerserviceLogRepository;

@Service
public class WorkerserviceLogService {

	@Autowired
	private WorkerserviceLogRepository repository;
	public List<WorkerserviceLog> getServiceById(List <WorkerLocationLog> worker)
	{
		List<WorkerserviceLog> wsl= new ArrayList<WorkerserviceLog>();
		for(WorkerLocationLog wtt: worker)
				{
						
						List<WorkerserviceLog> w = repository.findByWorkerID(wtt.getWorkerID());
								 wsl.addAll(w); 
				}
		return wsl;
	}
	public List<String> getUniqueService()
	{
		
		return repository.findDistinctService();
	}
	public List<WorkerserviceLog> getServiceByWorkerId (int wid) {
		return repository.findByWorkerID(wid);
	}

	public int getWorkerIDBySNo(int id) {
		WorkerserviceLog a = repository.getById(id);
		return a.getWorkerID();
	}

	public void deleteWorkerSerLog(int id)
	{ repository.deleteById(id); }

	public void saveService(WorkerserviceLog workerserviceLog) {
		repository.save(workerserviceLog);
	}
	
}
