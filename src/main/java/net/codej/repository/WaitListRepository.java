package net.codej.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.codej.WaitList;

public interface WaitListRepository extends JpaRepository<WaitList,Integer>{

	List<WaitList> findByUserID(int userID);
	List<WaitList> findByStatus(String status);

}
