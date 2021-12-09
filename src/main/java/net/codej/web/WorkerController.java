package net.codej.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import net.codej.Worker;
import net.codej.service.WorkerLocationLogService;
import net.codej.service.WorkerService;
import net.codej.service.WorkerserviceLogService;

@Controller
public class WorkerController {

	@Autowired
	private WorkerService service;
	@Autowired
	WorkerserviceLogService wsls;

	@Autowired
	WorkerLocationLogService wlls;

	@GetMapping("Admin/Workers")
	public String viewParkingSlots(Model model) {
		model.addAttribute("listWorkers",service.getAllWorkers());
		return "workerindex";
	}

	@GetMapping("/Admin/showNewWorkerForm")
	public String showNewWorkerForm(Model model) {
		Worker worker = new Worker();
		model.addAttribute("worker", worker);
		return "new_worker";
	}

	@PostMapping("/saveWorker")
	public String saveworker(@ModelAttribute("worker") Worker worker) {
		service.saveWorker(worker);
		return "redirect:/Admin/Workers";
	}

	@GetMapping("/Admin/showFormForWorkerUpdate/{id}")
	public String showFormForWorkerUpdate(@PathVariable(value = "id") int id, Model model) {

		Worker worker = service.getWorkerById(id);
		model.addAttribute("worker", worker);
		return "update_worker";

	}

	@GetMapping("/Admin/deleteWorker/{id}")
	public String deleteWorker(@PathVariable (value = "id") int id){
		service.deleteWorkerById(id);
		return "redirect:/Admin/Workers";
	}

	@GetMapping("Admin/workerDashboard/{id}")
	public String listserloc(@PathVariable(value = "id") int id, Model model) {
		model.addAttribute("listDetails",service.getWorkerById(id));
		model.addAttribute("listServices",wsls.getServiceByWorkerId(id));
		model.addAttribute("listLocations",wlls.getLocByWorkerId(id));
		return "workerDashboardIndex";
	}


}
