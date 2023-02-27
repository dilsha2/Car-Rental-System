package lk.ijse.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Time;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Rental {
    @Id
    private String rentalId;
    @ManyToOne
    private Customer nic;
    private Time pickupTime;
    private LocalDate pickupDate;
    private String pick_up_and_return_venue;
    private LocalDate returnDate;
    private int no_of_days;
    private String bank_slip_img;
    private String reservation_status;
    private String driver_status;

    @ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.DETACH})
    private Car registrationId;




}
