package net.codej.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.codej.Worker;
import net.codej.WorkerLocationLog;
import net.codej.repository.WorkerRepository;

@Service
public class WorkerService {

	@Autowired
	private WorkerRepository repository;

	public List<Worker> getServiceById(List <WorkerLocationLog> worker)
	{
		List<Worker> wsl= new ArrayList<Worker>();
		for(WorkerLocationLog wtt: worker)
				{
						
						Worker w= repository.findById(wtt.getWorkerID()).orElse(null);
								 wsl.add(w); 
				}
		return wsl;
	}
	 public List<Worker> getAllWorkers() {
			return repository.findAll();
	    }

		public void saveWorker(Worker worker) {
			this.repository.save(worker);
		}

		public Worker getWorkerById(int id) {
			Optional<Worker> optional = repository.findById(id);
			Worker worker = null;
			if(optional.isPresent()) {
				worker = optional.get();
			} else {
				throw new RuntimeException(" Worker not found for id" + id);
			}
			return worker;
		}
		public void deleteWorkerById(int id) {
			this.repository.deleteById(id);
		}
	
}
