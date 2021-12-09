package net.codej.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.codej.TUser;
import net.codej.repository.TUserRepository;

@Service
public class TUserService {

	@Autowired
	private TUserRepository repository;
	public TUser saveUser(TUser user)
	
	{		

		return repository.save(user);
		
	}
	public TUser addMoney(double amount, int id)
	
	{		
		TUser existingUser =  repository.findById(id).orElse(null);
		existingUser.setWallet(existingUser.getWallet()+amount);
		return repository.save(existingUser);
		
	}
	public TUser sendRequest(String s, int id)
	
	{		
		TUser existingUser =  repository.findById(id).orElse(null);
		existingUser.setRequest(existingUser.getRequest()+ '/' + s);
		return repository.save(existingUser);
		
	}
	public TUser modiflyDetails(TUser p)
	
	{		
		TUser existingUser =  repository.findById(p.getId()).orElse(null);
		existingUser.setCarRegNum(p.getCarRegNum());
		existingUser.setEmail(p.getEmail());
		existingUser.setMobileNum(p.getMobileNum());
		existingUser.setRequest(p.getRequest());
		return repository.save(existingUser);
		
		
	}
	public TUser viewInfo(int id)
	
	{		
		TUser existingUser =  repository.findById(id).orElse(null);
		return existingUser;
		
	}
}
