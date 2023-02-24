package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepo extends JpaRepository<Car,String> {

    @Query(value = "SELECT * FROM car WHERE availability=?1", nativeQuery = true)
    List<Car> getUnavailableOrAvailableCarsByStatus(String status);

    @Query(value = "SELECT * FROM car WHERE availability='UnderMaintain'", nativeQuery = true)
    List<Car> getCarsUnderMaintain();

    @Query(value = "SELECT * FROM car WHERE MOD(mileage,5000) = 0 AND availability NOT IN('UnderMaintain')", nativeQuery = true)
    List<Car> getCarsNeedMaintain();
}
