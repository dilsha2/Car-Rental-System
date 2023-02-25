package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepo extends JpaRepository<Car,String> {

    @Query(value = "SELECT * FROM car WHERE availability=?1", nativeQuery = true)
    List<Car> getUnavailableOrAvailableCarsByStatus(String status);

    @Query(value = "SELECT * FROM car WHERE availability='UnderMaintain'", nativeQuery = true)
    List<Car> getCarsUnderMaintain();

    @Query(value = "SELECT * FROM car WHERE MOD(mileage,5000) = 0 AND availability NOT IN('UnderMaintain')", nativeQuery = true)
    List<Car> getCarsNeedMaintain();

    @Query(value = "SELECT c FROM Car c WHERE (:transmission is null OR c.transmissionType=:transmission) AND (:brand is null OR c.brand=:brand) AND (:fuel is null OR c.fuelType=:fuel) AND (:type is null OR c.type=:type) AND (:no_of_passengers = 0 OR c.noOfPassenger=:no_of_passengers) order by c.registrationId asc ")
    List<Car> sortCarsByAttributes(@Param("transmission") String transmission, @Param("brand") String brand, @Param("type") String type,
                                   @Param("fuel") String fuel, @Param("no_of_passengers") int no_of_passengers);
}
