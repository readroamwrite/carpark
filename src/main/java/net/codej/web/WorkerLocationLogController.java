package net.codej.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import net.codej.WorkerLocationLog;
import net.codej.service.WorkerLocationLogService;

@Controller
public class WorkerLocationLogController {

	@Autowired
	private WorkerLocationLogService service;
	@GetMapping("/deleteLocationLog/{id}")
	public String deleteWorkerLoc(@PathVariable(value = "id") int id){
		int a = service.getWorkerIDBySNo(id);
		service.deleteWorkerLocLog(id);
		String s=String.valueOf(a);
		String ret = "redirect:/Admin/workerDashboard/"+s;
		return ret;
	}

	@GetMapping("/showNewLocationLogForm/{id}")
	public String showNewLocationLogForm(@PathVariable (value = "id") int id, Model model) {
		WorkerLocationLog workerLocationLog = new WorkerLocationLog();
		workerLocationLog.setWorkerID(id);
		model.addAttribute("locations", workerLocationLog);
		return "new_locationlog";
	}

	@PostMapping("/saveLocationLog")
	public String saveLocationLog(@ModelAttribute("locations") WorkerLocationLog workerLocationLog) {
		service.saveLocationLog(workerLocationLog);
		int a = workerLocationLog.getWorkerID();
		String s=String.valueOf(a);
		String ret = "redirect:/Admin/workerDashboard/"+s;
		return ret;
	}
}
