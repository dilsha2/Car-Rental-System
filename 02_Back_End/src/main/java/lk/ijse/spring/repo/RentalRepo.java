package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RentalRepo extends JpaRepository<Rental,String> {
    @Query(value = "SELECT rentalId FROM Rental ORDER BY rentalId DESC limit 1", nativeQuery = true)
    String generateReservationId();

    @Query(value = "SELECT * FROM Rental WHERE reservation_status='Pending'", nativeQuery = true)
    List<Rental> getAllPendingReservation();

    @Query(value = "SELECT * FROM Rental WHERE reserve_date=current_date()", nativeQuery = true)
    List<Rental> getAllTodayReservation();

    @Query(value = "SELECT * FROM Rental WHERE pick_up_date=current_date() AND reservation_status='Accept'", nativeQuery = true)
    List<Rental> getAllTodayPickUps();

    @Query(value = "SELECT * FROM Rental WHERE nic=?1 AND reservation_status=?2", nativeQuery = true)
    List<Rental> getCustomerReservationByStatus(String id, String status);

    @Query(value = "SELECT * FROM Rental WHERE (nic=?1) AND (reservation_status='Accept') AND (current_date() BETWEEN pick_up_date AND return_date)", nativeQuery = true)
    Rental checkTodayCustomerInReservationOrNot(String id);
}
