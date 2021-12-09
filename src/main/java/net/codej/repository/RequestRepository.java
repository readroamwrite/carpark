package net.codej.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.codej.Request;

public interface RequestRepository extends JpaRepository<Request,Integer>{

	List<Request> findByUserID(int userID);

	List<Request> findByStatus(String status);


}
