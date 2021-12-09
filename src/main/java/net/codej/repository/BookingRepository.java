package net.codej.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.codej.Booking;

public interface BookingRepository extends JpaRepository<Booking,Integer> {

	List<Booking> findByUserID(int userID);
	List<Booking> findByParkingSlotAndStatus(String parkingSlot, String status);
	List<Booking> findByUserIDAndStatus(int userID, String status);
	List<Booking> findByWorkerIDContainingAndStatus(String workerID, String status);
	List<Booking> findByStatus(String status);

}
