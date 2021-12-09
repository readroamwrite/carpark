package net.codej.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.codej.Request;
import net.codej.repository.RequestRepository;

@Service
public class RequestService {
	@Autowired
	private RequestRepository repository;
	public Request saveRequest(Request b)
	{
		return repository.save(b);
		
	}
	public List<Request> getRequest(int userID)
	{
		return repository.findByUserID(userID);
		
	}
	public Request getRequestByRef(int bookRefNum)
	{
		return repository.findById(bookRefNum).orElse(null);
		
	}
	
	public String deleteRequest(int BookRefNum)
	{
		repository.deleteById(BookRefNum);
		return "Request Removed :" + BookRefNum;
	}
	public List<Request> getRequestByStatus(String status) {
		return repository.findByStatus(status);
	}
	public List<Request> getAllRequests() {
		return repository.findAll();
	}

	public void requestApproval(int bookRefNum, String status) {
		Request existingRequest = repository.findById(bookRefNum).orElse(null);
		existingRequest.setStatus(status);
	}
}

