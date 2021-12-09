package net.codej.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.codej.WaitList;
import net.codej.repository.WaitListRepository;

@Service
public class WaitListService {
	@Autowired
	private WaitListRepository repository;
	public WaitList saveWaitList(WaitList w)
	{
			return repository.save(w);
		
	}
	public List<WaitList> getWaitList(int id)
	{
		return repository.findByUserID(id);
		
	}
	public List<WaitList> getWaitListByStatus(String s)
	{
		return repository.findByStatus(s);
		
	}
}
