package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepo extends JpaRepository<Car,String> {

    @Query(value = "SELECT * FROM Car WHERE availability=?1", nativeQuery = true)
    List<Car> getUnavailableOrAvailableCarsByStatus(String status);

    @Query(value = "SELECT * FROM Car WHERE availability='UnderMaintain'", nativeQuery = true)
    List<Car> getCarsUnderMaintain();

    @Query(value = "SELECT * FROM Car WHERE MOD(mileage,5000) = 0 AND availability NOT IN('UnderMaintain')", nativeQuery = true)
    List<Car> getCarsNeedMaintain();

    @Query(value = "SELECT * FROM Car WHERE registrationId NOT IN (SELECT DISTINCT registrationId FROM Rental WHERE (reservation_status='Pending' OR reservation_status='Accept') AND (pick_up_date BETWEEN ?1 AND ?2 OR return_date BETWEEN ?1 AND ?2 )) AND availabilty='Available'", nativeQuery = true)
    List<Car> getAvailableCarsForReservation(String pick_date, String return_date);

    @Query(value = "SELECT * FROM Car WHERE availability='Available' AND registrationId NOT IN (SELECT DISTINCT registrationId FROM car_reservation WHERE (reservation_status='Pending' OR reservation_status='Accept') AND ?1 BETWEEN pick_up_date AND return_date) ", nativeQuery = true)
    List<Car> getAvailableCarsForReservationOnDay(String select_date);

    @Query(value = "SELECT * FROM Car WHERE registrationId IN (SELECT DISTINCT registrationId FROM Rental WHERE  (reservation_status='Accept') AND (pick_up_date BETWEEN ?1 AND ?2 OR return_date BETWEEN ?1 AND ?2)) AND status='Available'", nativeQuery = true)
    List<Car> getRentalCars(String pick_up_date, String return_date);

    @Query(value = "SELECT * FROM Car WHERE registrationId IN (SELECT DISTINCT registrationId FROM Rental WHERE (reservation_status='Pending' OR reservation_status='Accept') AND ?1 BETWEEN pick_up_date AND return_date) AND status='Available'", nativeQuery = true)
    List<Car> getRentalCarsOnDay(String select_date);

    @Query(value = "SELECT c FROM Car c WHERE (:transmission is null OR c.transmissionType=:transmission) AND (:brand is null OR c.brand=:brand) AND (:fuel is null OR c.fuelType=:fuel) AND (:type is null OR c.type=:type) AND (:no_of_passengers = 0 OR c.noOfPassenger=:no_of_passengers) order by c.registrationId asc ")
    List<Car> sortCarsByAttributes(@Param("transmission") String transmission, @Param("brand") String brand, @Param("type") String type,
                                   @Param("fuel") String fuel, @Param("no_of_passengers") int no_of_passengers);
}
