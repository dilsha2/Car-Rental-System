package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface PaymentRepo extends JpaRepository<Payment,String> {
    @Query(value = "SELECT bill_id FROM payment ORDER BY bill_id DESC limit 1", nativeQuery = true)
    String generateReservationBillId();

    @Query(value = "SELECT SUM(total_payment) FROM payment WHERE pay_date BETWEEN ?1 AND ?2", nativeQuery = true)
    String getIncomeByDate(LocalDate first_date, LocalDate last_date);

    @Query(value = "SELECT SUM(total_payment) FROM payment WHERE pay_date=?1", nativeQuery = true)
    String getDailyIncome(LocalDate date);

    @Query(value = "SELECT * FROM payment WHERE pay_date=current_date()", nativeQuery = true)
    List<Payment> todayIncomeList();
}
