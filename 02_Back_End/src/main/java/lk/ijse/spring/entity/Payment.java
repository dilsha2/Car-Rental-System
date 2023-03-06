package lk.ijse.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Payment {
//    @Id
//    private String paymentId;
//    private LocalDate date;
//    private double amount;
//    private double damaged_cost;
//    private String damagedDescription;
//    private long extraMileage;
//    private long costPerExtraKm;
//    private String paymentStatus;
//    @OneToOne
//    private Rental rentalId;
@Id
private String bill_id;

    private Date pay_date;
    private int no_of_km;
    private BigDecimal rental_fee;
    private BigDecimal driver_fee;
    private BigDecimal damage_cost;
    private BigDecimal return_cost;
    private BigDecimal total_payment;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="reserve_id")
    private Rental carReservation;

}
