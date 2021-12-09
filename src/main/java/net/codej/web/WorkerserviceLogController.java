package net.codej.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import net.codej.WorkerserviceLog;
import net.codej.service.WorkerserviceLogService;

@Controller
public class WorkerserviceLogController {

	@Autowired
	private WorkerserviceLogService service;
	@GetMapping("/deleteServiceLog/{id}")
	public String deleteWorkerSer(@PathVariable(value = "id") int id){
		int a = service.getWorkerIDBySNo(id);
		service.deleteWorkerSerLog(id);
		String s=String.valueOf(a);
		String ret = "redirect:/Admin/workerDashboard/"+s;
		return ret;
	}

	@GetMapping("/showNewServiceForm/{id}")
	public String showNewserviceForm(@PathVariable (value = "id") int id, Model model) {
		WorkerserviceLog workerserviceLog = new WorkerserviceLog();
		workerserviceLog.setWorkerID(id);
		model.addAttribute("services", workerserviceLog);
		return "new_service";
	}

	@PostMapping("/saveService")
	public String saveServicem(@ModelAttribute("services") WorkerserviceLog workerserviceLog) {
		service.saveService(workerserviceLog);
		int a = workerserviceLog.getWorkerID();
		String s=String.valueOf(a);
		String ret = "redirect:/Admin/workerDashboard/"+s;
		return ret;
	}

}
