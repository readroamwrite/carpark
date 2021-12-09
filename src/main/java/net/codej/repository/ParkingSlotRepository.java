package net.codej.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.codej.config.ParkingSlot;

public interface ParkingSlotRepository extends JpaRepository<ParkingSlot,Integer> {
	
	List<ParkingSlot> findByLocation(String location);

}
